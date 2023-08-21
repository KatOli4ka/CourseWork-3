package pro.sky.coursework3.model;

import org.springframework.lang.Nullable;

public enum Color {
    RED,
    BLUE,
    YELLOW,
    BLACK,
    WHITE;

    @Nullable
    public static Color parce(String color) {
        for (Color oneColor : values()) {
            if (oneColor.name().equals(color)) {
                return oneColor;
            }
        }
        return null;
    }
}
