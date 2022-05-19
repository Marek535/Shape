package ShapeProject.controller;

import ShapeProject.ShapeMain;
import ShapeProject.model.Command.CreateShapeCommand;
import ShapeProject.model.Shape;
import ShapeProject.model.ShapeType;
import ShapeProject.model.Square;
import ShapeProject.model.Wheel;
import ShapeProject.repository.ShapeRepository;
import ShapeProject.service.ShapeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest(classes = ShapeMain.class)
class ShapeControllerTest {
    @Autowired
    ShapeRepository shapeRepository;

    @Autowired
    MockMvc mockMvc;
    @Autowired
    ShapeService shapeService;

    @Autowired
    ObjectMapper objectMapper;

    @BeforeEach
    void clean() {
        shapeRepository.getShapeList().clear();
    }

    @Test
    void shouldReturnRectangleField() throws Exception {
        //given
        CreateShapeCommand command = new CreateShapeCommand();
        command.setName("Rectangle");
        command.setParameters(Arrays.asList(5.0, 10.0));

        String commandJson = objectMapper.writeValueAsString(command);

        List<Shape> shapeList = shapeService.getShape();
        //when
        mockMvc.perform(MockMvcRequestBuilders.post("/shape")
                .content(commandJson)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
        //then
        assertEquals(50.0, shapeList.get(0).field());
    }

    @Test
    void shouldReturnWheelField() throws Exception {
        //given
        CreateShapeCommand command = new CreateShapeCommand();
        command.setName("Wheel");
        command.setParameters(Arrays.asList(10.00));

        String commandJson = objectMapper.writeValueAsString(command);
        List<Shape> shapeList = shapeService.getShape();

        //when
        mockMvc.perform(MockMvcRequestBuilders.post("/shape")
                .content(commandJson)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
        //then
        assertEquals(314.1592653589793, shapeList.get(0).field());
    }

    @Test
    void shouldReturnSquareField() throws Exception {
        //given
        CreateShapeCommand command = new CreateShapeCommand();
        command.setName("Square");
        command.setParameters(Arrays.asList(10.00));

        String commandJson = objectMapper.writeValueAsString(command);
        List<Shape> shapeList = shapeService.getShape();

        //when
        mockMvc.perform(MockMvcRequestBuilders.post("/shape")
                .content(commandJson)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
        //then
        assertEquals(100.00, shapeList.get(0).field());
    }

    @Test
    void shouldReturnRectangleCirciut() throws Exception {
        //given
        CreateShapeCommand command = new CreateShapeCommand();
        command.setName("Rectangle");
        command.setParameters(Arrays.asList(5.0, 10.0));

        String commandJson = objectMapper.writeValueAsString(command);

        List<Shape> shapeList = shapeService.getShape();
        //when
        mockMvc.perform(MockMvcRequestBuilders.post("/shape")
                .content(commandJson)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
        //then
        assertEquals(30.0, shapeList.get(0).circuit());
    }

    @Test
    void shouldReturnWheelCircuit() throws Exception {
        //given
        CreateShapeCommand command = new CreateShapeCommand();
        command.setName("Wheel");
        command.setParameters(Arrays.asList(10.00));

        String commandJson = objectMapper.writeValueAsString(command);
        List<Shape> shapeList = shapeService.getShape();

        //when
        mockMvc.perform(MockMvcRequestBuilders.post("/shape")
                .content(commandJson)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
        //then
        assertEquals(62.83185307179586, shapeList.get(0).circuit());
    }

    @Test
    void shouldReturnSquareCircuit() throws Exception {
        //given
        CreateShapeCommand command = new CreateShapeCommand();
        command.setName("Square");
        command.setParameters(Arrays.asList(10.00));

        String commandJson = objectMapper.writeValueAsString(command);
        List<Shape> shapeList = shapeService.getShape();

        //when
        mockMvc.perform(MockMvcRequestBuilders.post("/shape")
                .content(commandJson)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
        //then
        assertEquals(40.00, shapeList.get(0).circuit());

    }

    @Test
    void shouldReturnListOfShape() throws Exception {
        // given
        Wheel wheel = new Wheel();
        wheel.setRadius(10.00);

        Square square = new Square();
        square.setSide(5.00);

        List<Shape> shapeList = shapeService.getShape();
        shapeList.add(wheel);
        shapeList.add(square);

        //when

        mockMvc.perform(MockMvcRequestBuilders.get("/shape"))
                .andExpect(status().isOk());
        //then
        assertEquals(2, shapeList.size());
    }

    @Test
    void shouldReturnShapeByType() throws Exception {
        //given
        Wheel wheel = new Wheel();
        wheel.setRadius(10.00);

        Wheel wheelSecond = new Wheel();
        wheel.setRadius(10.00);

        Square square = new Square();
        square.setSide(5.00);

        String type = "Wheel";

        List<Shape> shapeList = shapeService.getShape();
        shapeList.add(wheel);
        shapeList.add(square);
        shapeList.add(wheelSecond);

        List<Shape> shapeByType = shapeService.findByType(ShapeType.Wheel);

        //then
        assertEquals(2,shapeByType.size());

        mockMvc.perform(MockMvcRequestBuilders.get("/shape/" + type))
               // .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));
    }
}