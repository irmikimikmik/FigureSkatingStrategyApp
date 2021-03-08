package persistence;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import model.*;
import org.json.*;

// Represents a reader that reads choreography from JSON data stored in file
public class JsonReader {
    private String source;

    // taken from JSONSerializationDemo: https://github.com/stleary/JSON-java.git
    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // taken from JSONSerializationDemo: https://github.com/stleary/JSON-java.git
    // EFFECTS: reads choreography from file and returns it;
    // throws IOException if an error occurs reading data from file
    public Choreography read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseChoreography(jsonObject);
    }

    // taken from JSONSerializationDemo: https://github.com/stleary/JSON-java.git
    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses choreography from JSON object and returns it
    private Choreography parseChoreography(JSONObject jsonObject) {
        double duration = jsonObject.getDouble("duration");
        int deductions = jsonObject.getInt("deductions");
        int falls = jsonObject.getInt("falls");
        boolean type = jsonObject.getBoolean("type");
        double skatingSkillsComponent = jsonObject.getDouble("skatingSkillsComponent");
        List<Element> listOfElement = new ArrayList<>();

        Choreography choreography = new Choreography("My choreography", deductions,
                falls, duration, type, skatingSkillsComponent, listOfElement);

        addElements(choreography, jsonObject);

        return choreography;
    }

    // taken from JSONSerializationDemo: https://github.com/stleary/JSON-java.git
    // MODIFIES: ch
    // EFFECTS: parses elements from JSON object and adds them to choreography
    private void addElements(Choreography ch, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("elements");
        for (Object json : jsonArray) {
            JSONObject nextElement = (JSONObject) json;
            addElement(ch, nextElement);
        }
    }

    // MODIFIES: ch
    // EFFECTS: parses element from JSON object and adds it to choreography
    private void addElement(Choreography ch, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        double basePoint = jsonObject.getDouble("basePoint");
        double goe = jsonObject.getDouble("goe");
        String type = jsonObject.getString("type");

        if ("Jump".equals(type)) {
            double rotations = jsonObject.getDouble("rotations");
            Element element = new Jump(name, basePoint, goe, "Jump", rotations);
            ch.addElement(element);
        } else if ("Spin".equals(type)) {
            int level = jsonObject.getInt("level");
            Element element = new Spin(name, basePoint, goe, "Spin", level);
            ch.addElement(element);
        } else if ("Step".equals(type)) {
            int level = jsonObject.getInt("level");
            Element element = new Step(name, basePoint, goe, "Step", level);
            ch.addElement(element);
        }
    }
}
