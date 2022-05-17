package ShapeProject.service;

import ShapeProject.model.*;
import ShapeProject.model.Command.CreateShapeCommand;
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


    private final Map<ShapeType, ShapeCreator> mapOfShape = new HashMap<>();

    public List<Shape> getShape() {
        return shapeRepository.getShapeList();
    }

    public List<Shape> findByType(ShapeType type) {

        return shapeRepository.getShapeList().stream().filter(x -> x.getType().equals(type)).collect(Collectors.toList());
    }

    @FunctionalInterface
    interface ShapeCreator {
        Shape shape(CreateShapeCommand nameOfShape);
    }

    @PostConstruct
    public void init() {
        mapOfShape.put(ShapeType.Rectangle, this::createNewRectangle);
        mapOfShape.put(ShapeType.Square, this::createNewSqaure);
        mapOfShape.put(ShapeType.Wheel, this::createNewWheel);
    }

    public Shape save(CreateShapeCommand command) {
        ShapeType shapeType = ShapeType.valueOf(command.getName());
        shapeRepository.getShapeList().add(mapOfShape.get(shapeType).shape(command));
        return mapOfShape.get(shapeType).shape(command);
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
