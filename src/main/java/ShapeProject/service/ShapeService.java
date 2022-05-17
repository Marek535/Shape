package ShapeProject.service;

import ShapeProject.model.Command.CreateShapeCommand;
import ShapeProject.model.Rectangle;
import ShapeProject.model.Shape;
import ShapeProject.model.Square;
import ShapeProject.model.Wheel;
import ShapeProject.repository.ShapeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ShapeService {

    private final ShapeRepository shapeRepository;


    private final Map<String, ShapeCreator> mapOfShape = new HashMap<>();

    public List<Shape> getShape() {
        return shapeRepository.getShapeList();
    }

    public List<Shape> findByType(String type) {
        Class<? extends Shape> clazz = getShapeClass(type);
        return findByType(clazz);
    }

    public List<Shape> findByType(Class<? extends Shape> clazz) {
        List<Shape> allShapes = shapeRepository.getShapeList();
        return allShapes.stream().filter(x -> clazz.isInstance(x)).collect(Collectors.toList());
    }

    protected Class<? extends Shape> getShapeClass(String type) {
        Class<? extends Shape> clazz;
        try {
            clazz = (Class<? extends Shape>) Class.forName(Shape.class.getPackageName() + '.' + type);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e); //TODO exception handling
        }
        return clazz;
    }
    @FunctionalInterface
    interface ShapeCreator {
        Shape shape(CreateShapeCommand nameOfShape);
    }

    @PostConstruct
    public void init() {
        mapOfShape.put("Rectangle", this::createNewRectangle);
        mapOfShape.put("Square", this::createNewSqaure);
        mapOfShape.put("Wheel", this::createNewWheel);
    }

    public Shape save(CreateShapeCommand command) {
        shapeRepository.getShapeList().add(mapOfShape.get(command.getName()).shape(command));
        return mapOfShape.get(command.getName()).shape(command);
    }

    public Wheel createNewWheel(CreateShapeCommand command) {
        Wheel wheel = new Wheel();
        wheel.setRadius(command.getParameters().get(0));

        return wheel;
    }

    public Rectangle createNewRectangle(CreateShapeCommand command) {
        Rectangle rectangle = new Rectangle();
        rectangle.setLength(command.getParameters().get(0));
        rectangle.setWidth(command.getParameters().get(1));
        return rectangle;
    }

    public Square createNewSqaure(CreateShapeCommand command) {
        Square square = new Square();
        square.setSide(command.getParameters().get(0));
        return square;
    }


//    public double getField(CreateShapeCommand command) {
//
//    }
}
