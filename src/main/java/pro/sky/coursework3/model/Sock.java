package pro.sky.coursework3.model;

import lombok.*;

@Data
@NoArgsConstructor

public class Sock {
    private Color color;
    private Size size;
    private int cottonPart;


    public Sock(Color color, Size size, int cottonPart) {
        this.color = color;
        this.size = size;
        this.cottonPart = cottonPart;
    }
}
