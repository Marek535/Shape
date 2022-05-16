package ShapeProject.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Square extends Shape {
    private double side;

    @Override
    public double field() {
        return side*side;
    }

    @Override
    public double circuit() {
        return 4*side;
    }
}
