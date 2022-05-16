package ShapeProject.repository;

import ShapeProject.model.Rectangle;
import ShapeProject.model.Shape;
import ShapeProject.model.Square;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Repository
public class ShapeRepository {
    private List<Shape> shapeList = Collections.synchronizedList(new ArrayList<>());

    public List<Shape> getShapeList() {
        return shapeList;
    }

    public Shape save(Shape shape) {
        shapeList.add(shape);
        return shape;
    }
}
