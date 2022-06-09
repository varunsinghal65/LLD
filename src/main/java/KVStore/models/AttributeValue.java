package KVStore.models;

import java.util.regex.Pattern;

public class AttributeValue {

    private AttributeType type;
    private String val;

    public AttributeValue(String val) {
        this.val = val;
        detectAndSetType();
    }

    private void detectAndSetType() {
        Pattern intPattern = Pattern.compile("\\d+");
        Pattern doublePattern = Pattern.compile("\\d+\\.\\d+");
        if (val.equalsIgnoreCase("true") ||
                val.equalsIgnoreCase("false")) {
            type = AttributeType.BOOLEAN;
        } else if (intPattern.matcher(val).matches()) {
            type = AttributeType.INTEGER;
        } else if (doublePattern.matcher(val).matches()) {
            type = AttributeType.DOUBLE;
        } else {
            type = AttributeType.STRING;
        }
    }

    public AttributeType getType() {
        return type;
    }

    public String getVal() {
        return val;
    }
}
