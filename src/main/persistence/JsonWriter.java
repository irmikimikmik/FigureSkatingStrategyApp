package persistence;

import model.Choreography;

import org.json.JSONObject;

import java.io.*;

// Represents a writer that writes JSON representation of choreography to file
public class JsonWriter {
    private static final int TAB = 4;
    private PrintWriter writer;
    private String destination;

    // taken from JSONSerializationDemo: https://github.com/stleary/JSON-java.git
    // EFFECTS: constructs writer to write to destination file
    public JsonWriter(String destination) {
        this.destination = destination;
    }

    // taken from JSONSerializationDemo: https://github.com/stleary/JSON-java.git
    // MODIFIES: this
    // EFFECTS: opens writer; throws FileNotFoundException if destination file cannot
    // be opened for writing
    public void open() throws FileNotFoundException {
        writer = new PrintWriter(new File(destination));
    }

    // taken from JSONSerializationDemo: https://github.com/stleary/JSON-java.git
    // MODIFIES: this
    // EFFECTS: writes JSON representation of choreography to file
    public void write(Choreography ch) {
        JSONObject json = ch.toJson();
        saveToFile(json.toString(TAB));
    }

    // taken from JSONSerializationDemo: https://github.com/stleary/JSON-java.git
    // MODIFIES: this
    // EFFECTS: closes writer
    public void close() {
        writer.close();
    }

    // taken from JSONSerializationDemo: https://github.com/stleary/JSON-java.git
    // MODIFIES: this
    // EFFECTS: writes string to file
    private void saveToFile(String json) {
        writer.print(json);
    }
}
