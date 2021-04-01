package test;

import model.*;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ChoreographyTest {

    // !!!
    // THIS IS AN EXAMPLE TEST FOR CHOREOGRAPHY CLASS, A CLASS THAT ONLY HAS GETTERS AND SETTERS AS ITS METHODS.
    // ELEMENT, JUMP, SPIN AND STEP CLASSES ONLY HAVE GETTERS AND SETTERS AS WELL.
    // SINCE THESE METHODS DON'T CONTAIN ANY LOGIC, THE TESTS WEREN'T ABLE TO REFLECT WHETHER THE METHODS WERE DESIGNED
    //       POORLY OR NOT. THUS, TEST CLASSES FOR ELEMENT, JUMP, SPIN AND STEP CLASSES WILL NOT BE CREATED UNLESS
    //       METHODS BESIDES GETTERS AND SETTERS WILL BE IMPLEMENTED.

    private Choreography choreography;
    private Element element;

    @BeforeEach
    void runBefore() {
        choreography = new Choreography("My choreography", 0.0, 0, 0.0,
                true, 0.0, new ArrayList<>());
        element = new Jump("3F", 5.20, 0.15, "Jump", 3);
    }

    @Test
    void testDeductions() {
        assertEquals(0.0, choreography.getDeductions());
        choreography.setDeductions(1.5);
        assertEquals(1.5, choreography.getDeductions());
    }

    @Test
    void testFalls() {
        assertEquals(0, choreography.getFalls());
        choreography.addFalls(4);
        assertEquals(4, choreography.getFalls());
    }

    @Test
    void testName() {
        assertEquals("My choreography", choreography.getChoreographyName());
        choreography.setChoreographyName("Hi");
        assertEquals("Hi", choreography.getChoreographyName());
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

    @Test
    void testReturnTypeAsString() {
        choreography.setType(true);
        assertTrue(choreography.getType());
        assertEquals("short", choreography.returnTypeAsString());

        choreography.setType(false);
        assertFalse(choreography.getType());
        assertEquals("free", choreography.returnTypeAsString());
    }

    @Test
    void testDeductionCounter() {
        assertEquals(0.0, choreography.deductionCounter(choreography.getFalls()));
        choreography.addFalls(1);
        assertEquals(0.5, choreography.deductionCounter(choreography.getFalls()));
        choreography.addFalls(1);
        assertEquals(1, choreography.deductionCounter(choreography.getFalls()));
        choreography.addFalls(1);
        assertEquals(2, choreography.deductionCounter(choreography.getFalls()));
        choreography.addFalls(7);
        assertEquals(9, choreography.deductionCounter(choreography.getFalls()));
    }

    @Test
    void testIsEligibleChoreographyWithCorrectFreeProgram() {
        Element element1 = new Jump("3F", 5.20, 0.15, "Jump", 3);
        Element element2 = new Jump("3F", 5.20, 0.15, "Jump", 3);
        Element element3 = new Jump("3F", 5.20, 0.15, "Jump", 3);
        Element element4 = new Jump("3F", 5.20, 0.15, "Jump", 3);
        Element element5 = new Jump("3F", 5.20, 0.15, "Jump", 3);
        Element element6 = new Jump("3F", 5.20, 0.15, "Jump", 3);
        Element element7 = new Jump("3F", 5.20, 0.15, "Jump", 3);

        Element element8 = new Step("StSq1", 1.80, 0.30, "Step", 1);
        Element element9 = new Step("StSq1", 1.80, 0.30, "Step", 1);

        Element element10 = new Spin("FCCoSp4", 3.50, -0.10, "Spin", 4);
        Element element11 = new Spin("FCCoSp4", 3.50, -0.10, "Spin", 4);
        Element element12 = new Spin("FCCoSp4", 3.50, -0.10, "Spin", 4);

        choreography.setType(false);
        choreography.addElement(element1);
        choreography.addElement(element2);
        choreography.addElement(element3);
        choreography.addElement(element4);
        choreography.addElement(element5);
        choreography.addElement(element6);
        choreography.addElement(element7);
        choreography.addElement(element8);
        choreography.addElement(element9);
        choreography.addElement(element10);
        choreography.addElement(element11);
        choreography.addElement(element12);

        assertTrue(choreography.isEligibleChoreography());

    }

    @Test
    void testIsEligibleChoreographyWithWrongFreeProgram() {
        Element element1 = new Jump("3F", 5.20, 0.15, "Jump", 3);
        Element element2 = new Jump("3F", 5.20, 0.15, "Jump", 3);
        Element element3 = new Jump("3F", 5.20, 0.15, "Jump", 3);
        Element element4 = new Jump("3F", 5.20, 0.15, "Jump", 3);
        Element element5 = new Jump("3F", 5.20, 0.15, "Jump", 3);
        Element element6 = new Jump("3F", 5.20, 0.15, "Jump", 3);
        Element element7 = new Jump("3F", 5.20, 0.15, "Jump", 3);
        Element element8 = new Jump("3F", 5.20, 0.15, "Jump", 3);

        Element element9 = new Step("StSq1", 1.80, 0.30, "Step", 1);

        Element element10 = new Spin("FCCoSp4", 3.50, -0.10, "Spin", 4);
        Element element11 = new Spin("FCCoSp4", 3.50, -0.10, "Spin", 4);
        Element element12 = new Spin("FCCoSp4", 3.50, -0.10, "Spin", 4);

        choreography.setType(false);
        choreography.addElement(element1);
        choreography.addElement(element2);
        choreography.addElement(element3);
        choreography.addElement(element4);
        choreography.addElement(element5);
        choreography.addElement(element6);
        choreography.addElement(element7);
        choreography.addElement(element8);
        choreography.addElement(element9);
        choreography.addElement(element10);
        choreography.addElement(element11);
        choreography.addElement(element12);

        assertFalse(choreography.isEligibleChoreography());

    }

    @Test
    void testIsEligibleChoreographyWithCorrectShortProgram() {
        Element element1 = new Jump("3F", 5.20, 0.15, "Jump", 3);
        Element element2 = new Jump("3F", 5.20, 0.15, "Jump", 3);
        Element element3 = new Jump("3F", 5.20, 0.15, "Jump", 3);

        Element element4 = new Step("StSq1", 1.80, 0.30, "Step", 1);

        Element element5 = new Spin("FCCoSp4", 3.50, -0.10, "Spin", 4);
        Element element6 = new Spin("FCCoSp4", 3.50, -0.10, "Spin", 4);
        Element element7 = new Spin("FCCoSp4", 3.50, -0.10, "Spin", 4);

        choreography.setType(true);
        choreography.addElement(element1);
        choreography.addElement(element2);
        choreography.addElement(element3);
        choreography.addElement(element4);
        choreography.addElement(element5);
        choreography.addElement(element6);
        choreography.addElement(element7);

        assertTrue(choreography.isEligibleChoreography());

    }

    @Test
    void testIsEligibleChoreographyWithWrongShortProgram() {
        Element element1 = new Jump("3F", 5.20, 0.15, "Jump", 3);
        Element element2 = new Jump("3F", 5.20, 0.15, "Jump", 3);
        Element element3 = new Jump("3F", 5.20, 0.15, "Jump", 3);
        Element element4 = new Jump("3F", 5.20, 0.15, "Jump", 3);

        Element element5 = new Spin("FCCoSp4", 3.50, -0.10, "Spin", 4);
        Element element6 = new Spin("FCCoSp4", 3.50, -0.10, "Spin", 4);
        Element element7 = new Spin("FCCoSp4", 3.50, -0.10, "Spin", 4);

        choreography.setType(true);
        choreography.addElement(element1);
        choreography.addElement(element2);
        choreography.addElement(element3);
        choreography.addElement(element4);
        choreography.addElement(element5);
        choreography.addElement(element6);
        choreography.addElement(element7);

        assertFalse(choreography.isEligibleChoreography());

    }

    @Test
    void testIsEligibleDurationShortProgram() {
        choreography.setType(true);
        choreography.setDuration(2.20);
        assertFalse(choreography.isEligibleDuration());

        choreography.setDuration(2.30);
        assertTrue(choreography.isEligibleDuration());

        choreography.setDuration(2.50);
        assertTrue(choreography.isEligibleDuration());

        choreography.setDuration(3.20);
        assertFalse(choreography.isEligibleDuration());
    }

    @Test
    void testIsEligibleDurationFreeProgram() {
        choreography.setType(false);
        choreography.setDuration(2.20);
        assertFalse(choreography.isEligibleDuration());

        choreography.setDuration(3.50);
        assertTrue(choreography.isEligibleDuration());

        choreography.setDuration(4.10);
        assertTrue(choreography.isEligibleDuration());

        choreography.setDuration(4.20);
        assertFalse(choreography.isEligibleDuration());
    }

    @Test
    void testDetermineSecondHalfElementsShortProgramWithEnoughElements() {
        Element element1 = new Jump("3F", 5.20, 0.00, "Jump", 3);
        Element element2 = new Jump("3F", 5.20, 0.00, "Jump", 3);
        Element element3 = new Jump("3F", 5.20, 0.00, "Jump", 3);

        Element element4 = new Step("StSq1", 1.80, 0.00, "Step", 1);

        Element element5 = new Spin("FCCoSp4", 3.50, 0.00, "Spin", 4);
        Element element6 = new Spin("FCCoSp4", 3.50, 0.00, "Spin", 4);
        Element element7 = new Spin("FCCoSp4", 3.50, 0.00, "Spin", 4);

        choreography.setType(true);
        choreography.addElement(element1);
        choreography.addElement(element2);
        choreography.addElement(element3);
        choreography.addElement(element4);
        choreography.addElement(element5);
        choreography.addElement(element6);
        choreography.addElement(element7);

        assertTrue(choreography.getType());
        choreography.determineSecondHalfElements();

        assertEquals(element1.getGOE(), 0.00);
        assertEquals(element2.getGOE(), 0.00);
        assertEquals(element3.getGOE(), 0.00);
        assertEquals(element4.getGOE(), 0.00);
        assertEquals(element5.getGOE(), element5.getBasePoint()*0.1);
        assertEquals(element6.getGOE(), element6.getBasePoint()*0.1);
        assertEquals(element7.getGOE(), element7.getBasePoint()*0.1);
    }

    @Test
    void testDetermineSecondHalfElementsShortProgramWithNotEnoughElements() {
        Element element1 = new Jump("3F", 5.20, 0.00, "Jump", 3);
        Element element2 = new Jump("3F", 5.20, 0.00, "Jump", 3);

        Element element3 = new Step("StSq1", 1.80, 0.00, "Step", 1);

        Element element4 = new Spin("FCCoSp4", 3.50, 0.00, "Spin", 4);
        Element element5 = new Spin("FCCoSp4", 3.50, 0.00, "Spin", 4);

        choreography.setType(true);
        choreography.addElement(element1);
        choreography.addElement(element2);
        choreography.addElement(element3);
        choreography.addElement(element4);
        choreography.addElement(element5);

        assertTrue(choreography.getType());
        choreography.determineSecondHalfElements();

        assertEquals(element1.getGOE(), 0.00);
        assertEquals(element2.getGOE(), 0.00);
        assertEquals(element3.getGOE(), 0.00);
        assertEquals(element4.getGOE(), 0.00);
        assertEquals(element5.getGOE(), 0.00);
    }


    @Test
    void testDetermineSecondHalfElementsFreeProgram() {
        Element element1 = new Jump("3F", 5.20, 0.00, "Jump", 3);
        Element element2 = new Jump("3F", 5.20, 0.00, "Jump", 3);
        Element element3 = new Jump("3F", 5.20, 0.00, "Jump", 3);
        Element element4 = new Jump("3F", 5.20, 0.00, "Jump", 3);
        Element element5 = new Jump("3F", 5.20, 0.00, "Jump", 3);
        Element element6 = new Jump("3F", 5.20, 0.00, "Jump", 3);
        Element element7 = new Jump("3F", 5.20, 0.00, "Jump", 3);

        Element element8 = new Step("StSq1", 1.80, 0.00, "Step", 1);
        Element element9 = new Step("StSq1", 1.80, 0.00, "Step", 1);

        Element element10 = new Spin("FCCoSp4", 3.50, 0.00, "Spin", 4);
        Element element11 = new Spin("FCCoSp4", 3.50, 0.00, "Spin", 4);
        Element element12 = new Spin("FCCoSp4", 3.50, 0.00, "Spin", 4);

        choreography.setType(false);
        choreography.addElement(element1);
        choreography.addElement(element2);
        choreography.addElement(element3);
        choreography.addElement(element4);
        choreography.addElement(element5);
        choreography.addElement(element6);
        choreography.addElement(element7);
        choreography.addElement(element8);
        choreography.addElement(element9);
        choreography.addElement(element10);
        choreography.addElement(element11);
        choreography.addElement(element12);

        choreography.determineSecondHalfElements();

        assertEquals(element1.getGOE(), 0.00);
        assertEquals(element2.getGOE(), 0.00);
        assertEquals(element3.getGOE(), 0.00);
        assertEquals(element4.getGOE(), 0.00);
        assertEquals(element5.getGOE(), 0.00);
        assertEquals(element6.getGOE(), 0.00);
        assertEquals(element7.getGOE(), element7.getBasePoint()*0.1);
        assertEquals(element8.getGOE(), element8.getBasePoint()*0.1);
        assertEquals(element9.getGOE(), element9.getBasePoint()*0.1);
        assertEquals(element10.getGOE(), element10.getBasePoint()*0.1);
        assertEquals(element11.getGOE(), element11.getBasePoint()*0.1);
        assertEquals(element12.getGOE(), element12.getBasePoint()*0.1);

    }

    @Test
    void testToJson() {

        Element element1 = new Jump("3F", 5.20, 0.00, "Jump", 3);
        Element element2 = new Jump("3F", 5.20, 0.00, "Jump", 3);
        Element element3 = new Jump("3F", 5.20, 0.00, "Jump", 3);

        Element element4 = new Step("StSq1", 1.80, 0.00, "Step", 1);

        Element element5 = new Spin("FCCoSp4", 3.50, 0.00, "Spin", 4);
        Element element6 = new Spin("FCCoSp4", 3.50, 0.00, "Spin", 4);
        Element element7 = new Spin("FCCoSp4", 3.50, 0.00, "Spin", 4);

        choreography.setType(true);
        choreography.addElement(element1);
        choreography.addElement(element2);
        choreography.addElement(element3);
        choreography.addElement(element4);
        choreography.addElement(element5);
        choreography.addElement(element6);
        choreography.addElement(element7);

        JSONObject jsonChoreography = choreography.toJson();

        assertEquals(jsonChoreography.get("name"), choreography.getChoreographyName());
        assertEquals(jsonChoreography.get("deductions"), choreography.getDeductions());
        assertEquals(jsonChoreography.get("falls"), choreography.getFalls());
        assertEquals(jsonChoreography.get("duration"), choreography.getDuration());
        assertEquals(jsonChoreography.get("type"), choreography.getType());
        assertEquals(jsonChoreography.get("skatingSkillsComponent"), choreography.getSkatingSkillsComponent());

    }

    @Test
    void testElementsToJson() {

        Element element1 = new Jump("3F", 5.20, 0.00, "Jump", 3);
        Element element2 = new Jump("3F", 5.20, 0.00, "Jump", 3);
        Element element3 = new Jump("3F", 5.20, 0.00, "Jump", 3);

        Element element4 = new Step("StSq1", 1.80, 0.00, "Step", 1);

        Element element5 = new Spin("FCCoSp4", 3.50, 0.00, "Spin", 4);
        Element element6 = new Spin("FCCoSp4", 3.50, 0.00, "Spin", 4);
        Element element7 = new Spin("FCCoSp4", 3.50, 0.00, "Spin", 4);

        choreography.setType(true);
        choreography.addElement(element1);
        choreography.addElement(element2);
        choreography.addElement(element3);
        choreography.addElement(element4);
        choreography.addElement(element5);
        choreography.addElement(element6);
        choreography.addElement(element7);

        JSONArray jsonElements = choreography.elementsToJson();

        assertEquals(jsonElements.length(), choreography.size());
    }

    @Test
    void testAddElement() {

        assertEquals(choreography.size(), 0);

        Element element1 = new Jump("3F", 5.20, 0.00, "Jump", 3);
        Element element2 = new Jump("3F", 5.20, 0.00, "Jump", 3);
        Element element3 = new Jump("3F", 5.20, 0.00, "Jump", 3);

        Element element4 = new Step("StSq1", 1.80, 0.00, "Step", 1);

        Element element5 = new Spin("FCCoSp4", 3.50, 0.00, "Spin", 4);
        Element element6 = new Spin("FCCoSp4", 3.50, 0.00, "Spin", 4);
        Element element7 = new Spin("FCCoSp4", 3.50, 0.00, "Spin", 4);

        choreography.setType(true);
        choreography.addElement(element1);
        choreography.addElement(element2);
        choreography.addElement(element3);
        choreography.addElement(element4);
        choreography.addElement(element5);
        choreography.addElement(element6);
        choreography.addElement(element7);

        List<Element> elements = choreography.getListOfElements();

        assertEquals(elements.get(0), element1);
        assertEquals(elements.get(3), element4);
        assertEquals(elements.get(5), element6);
    }

    @Test
    void testTypeFinder() {

        assertEquals("Step", choreography.typeFinder("StSq4"));
        assertEquals("Step", choreography.typeFinder("ChSq1"));
        assertEquals("Jump", choreography.typeFinder("2A"));
        assertEquals("Jump", choreography.typeFinder("3T"));
        assertEquals("Spin", choreography.typeFinder("FCCoSp4V"));
        assertEquals("Spin", choreography.typeFinder("CoSpB"));

    }

    @Test
    void testBasePointFinderWithInvalidInput() {
        try {
            assertEquals(0.0, choreography.basePointFinder("abc"));
            fail("This was an invalid element. There should've been an exception.");
        } catch (IOException e) {
            // do nothing
        }
    }

    @Test
    void testBasePointFinderWithValidInput() {

        try {
            assertEquals(3.90, choreography.basePointFinder("StSq4"));
            assertEquals(3.00, choreography.basePointFinder("ChSq1"));
            assertEquals(3.30, choreography.basePointFinder("2A"));
            assertEquals(4.20, choreography.basePointFinder("3T"));
            assertEquals(2.63, choreography.basePointFinder("FCCoSp4V"));
            assertEquals(1.50, choreography.basePointFinder("CoSpB"));
        } catch (IOException e) {
            fail("We shouldn't have gotten here.");
        }
    }

    @Test
    void testRotationOrLevelFinder() {

        assertEquals("4", choreography.rotationOrLevelFinder("StSq4", "Step"));
        assertEquals("1", choreography.rotationOrLevelFinder("ChSq1", "Step"));
        assertEquals("2", choreography.rotationOrLevelFinder("2A", "Jump"));
        assertEquals("3", choreography.rotationOrLevelFinder("3T", "Jump"));
        assertEquals("4", choreography.rotationOrLevelFinder("FCCoSp4V", "Spin"));
        assertEquals("0", choreography.rotationOrLevelFinder("CoSpB", "Spin"));

    }
}
