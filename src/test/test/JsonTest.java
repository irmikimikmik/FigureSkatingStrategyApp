package test;

import model.Element;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {
    protected void checkElement(String name, double basePoint, double goe, String type, Element element) {
        assertEquals(name, element.getElementName());
        assertEquals(basePoint, element.getBasePoint());
        assertEquals(goe, element.getGOE());
        assertEquals(type, element.getElementType());
    }
}
