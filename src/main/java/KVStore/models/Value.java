package KVStore.models;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Value {

    private Map<String, AttributeValue> attributePairs;

    public Value() {
        this.attributePairs = new HashMap<>();
    }

    public Map<String, AttributeValue> getAttributePairs() {
        return Collections.unmodifiableMap(attributePairs);
    }

    public void setAttributePairs(Map<String, AttributeValue> attributePairs) {
        this.attributePairs = attributePairs;
    }
}
