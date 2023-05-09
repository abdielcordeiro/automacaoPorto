package br.com.abdiel.ConfigProvider.Model;

import com.github.romankh3.image.comparison.model.Rectangle;
import lombok.Getter;
import lombok.RequiredArgsConstructor;


@Getter
@RequiredArgsConstructor
public class ignorableRect {

    public static ignorableRect to(br.com.abdiel.Enum.displayName displayName, int startX, int startY, int endX, int endY) {
        return new ignorableRect(displayName, new Rectangle(startX, startY, endX, endY));
    }

    private final br.com.abdiel.Enum.displayName displayName;
    private final Rectangle rectangle;
}
