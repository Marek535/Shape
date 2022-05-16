package ShapeProject.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Wheel extends Shape {
    private double radius;

    @Override
    public double field() {
        return Math.PI * radius * radius;
    }

    @Override
    public double circuit() {
        return Math.PI * 2 * radius;
    }
}
