package test;

import model.Element;
import model.Jump;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class JumpTest {

    private Element element;

    @BeforeEach
    void runBefore() {
        element = new Jump("3Lo", 5.20, 0.15, "Jump", 3.0);
    }

    @Test
    void testName() {
        assertEquals(3.0, element.getRotationOrLevel());
        element.setRotationOrLevel(2.0);
        assertEquals(2.0, element.getRotationOrLevel());
    }

    @Test
    void testToJson() {

        JSONObject jsonElement = element.toJson();

        assertEquals(jsonElement.get("name"), element.getElementName());
        assertEquals(jsonElement.get("basePoint"), element.getBasePoint());
        assertEquals(jsonElement.get("type"), element.getElementType());
        assertEquals(jsonElement.get("goe"), element.getGOE());
        assertEquals(jsonElement.get("rotations"), element.getRotationOrLevel());

    }
}
