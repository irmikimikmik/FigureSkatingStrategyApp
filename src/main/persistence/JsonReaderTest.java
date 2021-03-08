package persistence;

import model.Choreography;
import model.Element;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class JsonReaderTest extends JsonTest {

    // taken from JSONSerializationDemo: https://github.com/stleary/JSON-java.git
    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            Choreography ch = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    // taken from JSONSerializationDemo: https://github.com/stleary/JSON-java.git
    @Test
    void testReaderEmptyChoreography() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyChoreography.json");
        try {
            Choreography ch = reader.read();
            assertEquals("My choreography", ch.getChoreographyName());
            assertEquals(0, ch.size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    // taken from JSONSerializationDemo: https://github.com/stleary/JSON-java.git
    @Test
    void testReaderGeneralChoreography() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralChoreography.json");
        try {
            Choreography ch = reader.read();
            assertEquals("My choreography", ch.getChoreographyName());
            List<Element> elements = ch.getListOfElements();
            assertEquals(4, ch.size());
            checkElement("3T", 4.20, 0.00, "Jump", elements.get(0));
            checkElement("3Lo", 4.9, 0.03, "Jump", elements.get(1));
            checkElement("FSSp3", 2.6, -0.03, "Spin", elements.get(2));
            checkElement("StSq2", 2.6, 0.00, "Step", elements.get(3));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}