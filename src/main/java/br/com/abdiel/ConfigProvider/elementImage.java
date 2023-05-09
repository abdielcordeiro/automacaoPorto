package br.com.abdiel.ConfigProvider;

import lombok.SneakyThrows;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;

public class elementImage {

    private final WebElement element;

    public elementImage(WebElement element) {
        this.element = element;
    }

    @SneakyThrows
    public BufferedImage getBufferedImage() {
        return ImageIO.read(new ByteArrayInputStream(element.getScreenshotAs(OutputType.BYTES)));
    }
}
