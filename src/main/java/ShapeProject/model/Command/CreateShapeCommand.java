package ShapeProject.model.Command;

import ShapeProject.model.Shape;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CreateShapeCommand {
    private String name;
    private List<Double> parameters;
}
