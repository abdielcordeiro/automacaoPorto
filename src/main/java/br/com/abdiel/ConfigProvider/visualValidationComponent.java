package br.com.abdiel.ConfigProvider;

import br.com.abdiel.ConfigProvider.Model.ignorableRect;
import br.com.abdiel.Enum.displayName;
import br.com.abdiel.Managers.FileReaderManager;
import br.com.abdiel.Managers.DriverManager;
import br.com.abdiel.Util.sleeper;
import com.github.romankh3.image.comparison.ImageComparison;
import com.github.romankh3.image.comparison.ImageComparisonUtil;
import com.github.romankh3.image.comparison.model.ImageComparisonResult;
import com.github.romankh3.image.comparison.model.ImageComparisonState;
import com.github.romankh3.image.comparison.model.Rectangle;
import lombok.SneakyThrows;
import org.apache.commons.io.file.PathUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class visualValidationComponent extends sleeper<visualValidationComponent> {

    private static final int EXCLUDED_RECTANGLE_DRAW_WIDTH = 2;

    // Porcentagem de diferenca entre as cores dos pixels
    private float pixelDifferenceTolerance = 0.15f;

    // Distancia permita entre pixels de cores diferentes
    private int pixelThresholdAllowed = 8;

    private br.com.abdiel.ConfigProvider.visualValidationConfiguration visualValidationConfiguration;


    public visualValidationComponent() {
        visualValidationConfiguration = new visualValidationConfiguration();
    }

    /**
     * Compara a imagem de apenas um elemento com uma imagem salva anteriormente, caso a imagem na exista, ela sera criada.
     * A imagem criada sera salva numa pasta com o nome do Device que validou, para uma lista
     * completa veja de devices possiveis veja {@link displayName}
     *
     * @param elementImage   Wrapper do MobileElement para ser convertido em BufferedImage
     * @param stepName       Nome do passo a ser registrado.
     * @param ignorableRects Lista de areas a serem ignoradas ao validar as duas imagens.
     * @see displayName
     */
    public boolean checkpoint(elementImage elementImage, String stepName, ignorableRect... ignorableRects) {
        return validate(elementImage.getBufferedImage(), stepName, ignorableRectArrayToListOfRectangle(ignorableRects));
    }

    private boolean validate(BufferedImage actual, String stepName, List<Rectangle> excludedAreas) {
        File baselineFile = getBaselineFile(stepName);
        Optional<BufferedImage> expected = fileToBufferedImage(baselineFile);

        if (expected.isPresent()) {
            ImageComparisonResult result = getComparisonResult(actual, expected.get());
                return imagesMatchs(result);
        } else {
            ImageComparisonUtil.saveImage(baselineFile, actual);
        }
        return false;
    }

    private boolean imagesMatchs(ImageComparisonResult imageComparisonResult) {
        return imageComparisonResult.getImageComparisonState().equals(ImageComparisonState.MATCH);
    }

    private File getBaselineFile(String stepName) {
        File baselineForDevice = new File(FileReaderManager.getInstance().getConfigReader().getInfo("baseLine"));
        return new File(baselineForDevice, stepName + ".png");
    }

    private Optional<BufferedImage> fileToBufferedImage(File file) {
        return Optional.of(file)
                .filter(this::isNotEmptyFile)
                .map(File::getAbsolutePath)
                .map(ImageComparisonUtil::readImageFromResources);
    }

    private boolean isNotEmptyFile(File file) {
        try {
            return file.exists() || !PathUtils.isEmptyFile(file.toPath());
        } catch (IOException e) {
            return false;
        }
    }

    private List<Rectangle> addDefaultExcludedAreas(ignorableRect... ignorableRects) {
        ignorableRect[] ir = ArrayUtils.addAll(ignorableRects, br.com.abdiel.Enum.ignorableRects.DEFAULT_IGNORABLE_RECTS);
        return Arrays.stream(ir)
                .filter(ignorableRect -> ignorableRect.getDisplayName().equals(FileReaderManager.getInstance().getConfigReader().getInfo("navegador")))
                .map(ignorableRect::getRectangle)
                .collect(Collectors.toList());
    }

    private ImageComparisonResult getComparisonResult(BufferedImage actual, BufferedImage expected, List<Rectangle> excludedAreas) {
        ImageComparison imageComparison = new ImageComparison(actual, expected);

        imageComparison.setExcludedAreas(excludedAreas);
        imageComparison.setDrawExcludedRectangles(true);
        imageComparison.setRectangleLineWidth(EXCLUDED_RECTANGLE_DRAW_WIDTH);
        imageComparison.setThreshold(pixelThresholdAllowed);
        imageComparison.setPixelToleranceLevel(pixelDifferenceTolerance);

        return imageComparison.compareImages();
    }

    private ImageComparisonResult getComparisonResult(BufferedImage actual, BufferedImage expected) {
        ImageComparison imageComparison = new ImageComparison(actual, expected);

        imageComparison.setRectangleLineWidth(EXCLUDED_RECTANGLE_DRAW_WIDTH);
        imageComparison.setThreshold(pixelThresholdAllowed);
        imageComparison.setPixelToleranceLevel(pixelDifferenceTolerance);

        return imageComparison.compareImages();
    }

    public List<Rectangle> ignorableRectArrayToListOfRectangle(ignorableRect... ignorableRects) {
        return Arrays.stream(ignorableRects)
                .filter(ignorableRect -> ignorableRect.getDisplayName().equals(FileReaderManager.getInstance().getConfigReader().getInfo("navegador")))
                .map(ignorableRect::getRectangle)
                .collect(Collectors.toList());
    }

    @SneakyThrows
    private BufferedImage getEntireScreenImage() {
        return ImageIO.read(new ByteArrayInputStream(((TakesScreenshot) DriverManager.getDriver()).getScreenshotAs(OutputType.BYTES)));
    }
}
