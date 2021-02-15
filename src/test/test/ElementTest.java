package test;

import model.Choreography;
import model.Element;
import model.Jump;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

public class ElementTest {

    private Element element;

    @BeforeEach
    void runBefore() {
        element = new Jump("3Lo", 5.20, 0.15, "Jump", 3);
    }

    @Test
    void testName() {
        assertEquals("", element.getElementName());
        element.setElementName("3F");
        assertEquals("3F", element.getElementName());
    }

    @Test
    void testGetBasePoint() {
        assertEquals(5.20, element.getBasePoint());
    }

    @Test
    void testGOE() {
        assertEquals(0.15, element.getGOE());
        element.addGOE(0.33);
        assertEquals(0.48, element.getGOE());
    }

    @Test
    void testElementType() {
        assertEquals("Jump", element.getElementType());
        element.setElementType("Spin");
        assertEquals("Spin", element.getElementName());
    }

}
