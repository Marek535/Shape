package ShapeProject.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Rectangle extends Shape {
    private double length;
    private double width;

    public Rectangle(){
        super(ShapeType.Rectangle);
    }

    @Override
    public double field() {
        return length*width;
    }

    @Override
    public double circuit() {
        return (2*length)+(2*width);
    }
}
