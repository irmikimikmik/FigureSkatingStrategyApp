package test;

import model.Element;
import model.Spin;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SpinTest {

    private Element element;

    @BeforeEach
    void runBefore() {
        element = new Spin("FCoSp4V", 2.25, 0.15, "Spin", 4.0);
    }

    @Test
    void testName() {
        assertEquals(4.0, element.getRotationOrLevel());
        element.setRotationOrLevel(2.0);
        assertEquals(2.0, element.getRotationOrLevel());
    }
}
