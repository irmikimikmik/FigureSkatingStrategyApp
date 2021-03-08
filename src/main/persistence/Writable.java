package persistence;

import org.json.JSONObject;

// taken from JSONSerializationDemo: https://github.com/stleary/JSON-java.git
public interface Writable {
    // EFFECTS: returns this as JSON object
    JSONObject toJson();
}
