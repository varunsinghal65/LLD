package trello.models;

import java.util.*;

public class Board {

    private List<CardList> cardLists;
    private final String id;
    private String name;
    private Privacy visibility;
    private String url;
    private Set<User> members;
    private static final String urlPrefix = "www.jinghalala.com/boards/";

    public Board(String name) {
        this.cardLists = new ArrayList<>();
        this.name = name;
        this.visibility = Privacy.PUBLIC;
        this.members = new HashSet<>();
        this.id = String.valueOf(this.hashCode());
        this.url = urlPrefix + this.id;
    }

    public String getId() {
        return id;
    }

    public List<CardList> getCardLists() {
        return cardLists;
    }

    public void setCardLists(List<CardList> cardLists) {
        this.cardLists = cardLists;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Privacy getVisibility() {
        return visibility;
    }

    public void setVisibility(Privacy visibility) {
        this.visibility = visibility;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Set<User> getMembers() {
        return members;
    }

    public void setMembers(Set<User> members) {
        this.members = members;
    }

    @Override
    public String toString() {
        return "Board{" +
                "cardLists=" + cardLists +
                ", id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", visibility=" + visibility +
                ", url='" + url + '\'' +
                ", members=" + getPrintableCollection(members) +
                '}';
    }

    private String getPrintableCollection(Collection<?> items) {
        StringBuilder sbr = new StringBuilder();
        items.forEach(item-> sbr.append(item.toString()).append(","));
        sbr.append(" ");
        return sbr.toString();
    }

}
