package ShapeProject.model;

public abstract class Shape {

    private final ShapeType type;

    protected Shape(ShapeType type) {
        this.type = type;
    }

    public abstract double field();

    public abstract double circuit();

    public ShapeType getType() {
        return type;
    }
}
