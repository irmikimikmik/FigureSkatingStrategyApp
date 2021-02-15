package test;

import model.Choreography;
import model.Element;
import model.Jump;
import org.junit.jupiter.api.*;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class ChoreographyTest {

    private Choreography choreography;
    private Element element;

    @BeforeEach
    void runBefore() {
        choreography = new Choreography(0.0,
            true, 0, 0.00, true, 00.00);
        element = new Jump("3F", 5.20, 0.15, "Jump", 3);
    }

    @Test
    void testDeductions() {
        assertEquals(0.0, choreography.getDeductions());
        choreography.setDeductions(1.5);
        assertEquals(1.5, choreography.getDeductions());
    }

    @Test
    void testCategory() {
        assertTrue(choreography.getSkaterCategory());
        choreography.setSkaterCategory(false);
        assertFalse(choreography.getSkaterCategory());
    }

    @Test
    void testFalls() {
        assertEquals(0, choreography.getFalls());
        choreography.addFalls(4);
        assertEquals(4, choreography.getFalls());
    }

    @Test
    void testDuration() {
        assertEquals(0.00, choreography.getDuration());
        choreography.setDuration(3.15);
        assertEquals(3.15, choreography.getDuration());
    }

    @Test
    void testType() {
        assertTrue(choreography.getType());
        choreography.setType(false);
        assertFalse(choreography.getType());
    }

    @Test
    void testSkatingComponent() {
        assertEquals(00.00, choreography.getSkatingSkillsComponent());
        choreography.setSkatingSkillsComponent(30.15);
        assertEquals(30.15, choreography.getSkatingSkillsComponent());
    }

    @Test
    void testSize() {
        assertEquals(0, choreography.size());
        assertEquals(new ArrayList<Element>(), choreography.getListOfElements());
        choreography.addElement(element);
        assertEquals(1, choreography.size());
        choreography.addElement(element);
        assertEquals(2, choreography.size());

    }

}
