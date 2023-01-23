package pro.sky.coursework3.model;

import com.fasterxml.jackson.annotation.JsonValue;
import org.springframework.lang.Nullable;

public enum Size {
    S36(36),
    S37(37),
    S37_5(37.5F),
    S38(38),
    S39(39),
    S39_5(39.5F),
    S40(40);
    @JsonValue
    private final float size;

    Size(float size) {
        this.size = size;
    }

    public float getSize() {
        return size;
    }

    @Nullable
    public static Size parce(float size) {
        for (Size oneSize : values()) {
            if (Float.compare(oneSize.size, size) == 0) {
                return oneSize;
            }
        }
        return null;
    }

}
