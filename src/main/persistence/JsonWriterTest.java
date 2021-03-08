package persistence;

import model.Choreography;
import model.Element;
import model.Jump;
import model.Spin;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class JsonWriterTest extends JsonTest {
    //NOTE TO CPSC 210 STUDENTS: the strategy in designing tests for the JsonWriter is to
    //write data to a file and then use the reader to read it back in and check that we
    //read in a copy of what was written out.

    // taken from JSONSerializationDemo: https://github.com/stleary/JSON-java.git
    @Test
    void testWriterInvalidFile() {
        try {
            Choreography ch = new Choreography("My choreography", 0.0, 0, 0.0,
                    true, 0.0, new ArrayList<>());
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    // taken from JSONSerializationDemo: https://github.com/stleary/JSON-java.git
    @Test
    void testWriterEmptyChoreography() {
        try {
            Choreography ch = new Choreography("My choreography", 0.0, 0, 0.0,
                    true, 0.0, new ArrayList<>());
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyChoreography.json");
            writer.open();
            writer.write(ch);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyChoreography.json");
            ch = reader.read();
            assertEquals("My choreography", ch.getChoreographyName());
            assertEquals(0, ch.size());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    // taken from JSONSerializationDemo: https://github.com/stleary/JSON-java.git
    @Test
    void testWriterGeneralChoreography() {
        try {
            Choreography ch = new Choreography("My choreography", 0.0, 0, 0.0,
                    true, 0.0, new ArrayList<>());
            ch.addElement(new Jump("3Lo", 4.90, 0.00, "Jump", 3));
            ch.addElement(new Spin("FSSp3", 2.60, 0.00, "Spin", 3));
            ch.addElement(new Spin("StSq2", 2.60, 0.00, "Step", 2));
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralChoreography.json");
            writer.open();
            writer.write(ch);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralChoreography.json");
            ch = reader.read();
            assertEquals("My choreography", ch.getChoreographyName());
            List<Element> elements = ch.getListOfElements();
            assertEquals(3, elements.size());
            checkElement("3Lo", 4.90, 0.00, "Jump", elements.get(0));
            checkElement("FSSp3", 2.60, 0.00, "Spin", elements.get(1));
            checkElement("StSq2", 2.60, 0.00, "Step", elements.get(2));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}