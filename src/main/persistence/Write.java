package persistence;

import org.json.JSONObject;

public interface Write {
    // EFFECTS: returns this as JSON object
    JSONObject toJson();
}
