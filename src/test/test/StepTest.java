package test;

import model.Element;
import model.Step;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class StepTest {

    private Element element;

    @BeforeEach
    void runBefore() {
        element = new Step("ChSq1", 3.00, 0.15, "Spin", 1.0);
    }

    @Test
    void testName() {
        assertEquals(1.0, element.getRotationOrLevel());
        element.setRotationOrLevel(2.0);
        assertEquals(2.0, element.getRotationOrLevel());
    }
}
