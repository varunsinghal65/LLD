package trello.models;

import java.util.List;

public class Card {

    private final String id;
    private String name;
    private String desc;
    private String userId;
    private List<String> tags;

    public Card(String name, String desc, String userId, List<String> tags) {
        this.name = name;
        this.desc = desc;
        this.userId = userId;
        this.tags = tags;
        this.id = String.valueOf(this.hashCode());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }
}
