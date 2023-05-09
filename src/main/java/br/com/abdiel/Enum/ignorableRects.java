package br.com.abdiel.Enum;

import br.com.abdiel.ConfigProvider.Model.ignorableRect;
import com.github.romankh3.image.comparison.model.Rectangle;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ignorableRects {

    public static final ignorableRect GOOGLE_CHROME = new ignorableRect(
            displayName.CHROME,
            new Rectangle(0, 0, 0, 0));


    public static final ignorableRect[] DEFAULT_IGNORABLE_RECTS = new ignorableRect[] {
            GOOGLE_CHROME
    };
}
