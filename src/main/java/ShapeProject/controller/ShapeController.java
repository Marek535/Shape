package ShapeProject.controller;

import ShapeProject.exception.ShapeNotFoundExcpetion;
import ShapeProject.model.Command.CreateShapeCommand;
import ShapeProject.model.Dto.ShapeDto;
import ShapeProject.model.Rectangle;
import ShapeProject.model.Shape;
import ShapeProject.service.ShapeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/shape")
@RequiredArgsConstructor
public class ShapeController {
    private final ShapeService shapeService;

    @PostMapping
    public ResponseEntity addShape(@RequestBody CreateShapeCommand command) {
        return new ResponseEntity(toDto(shapeService.save(command)), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity getShape() {
        return ResponseEntity.ok(shapeService.getShape().stream().map(this::toDto).collect(Collectors.toList()));
    }

    @GetMapping("/{type}")
    public ResponseEntity getByType(@PathVariable String type) {
        return ResponseEntity.ok(shapeService.findByType(type).stream().map(this::toDto).collect(Collectors.toList()));
    }

    private ShapeDto toDto(Shape shape) {
        ShapeDto dto = new ShapeDto();
        dto.setField(shape.field());
        dto.setCirciut(shape.circuit());
        return dto;
    }
}
