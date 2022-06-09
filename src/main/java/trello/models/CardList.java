package trello.models;

import java.util.List;

public class CardList {
    private List<Card> cards;
    private final String id;
    private String name;

    public CardList(List<Card> cards, String name) {
        this.cards = cards;
        this.name = name;
        this.id = String.valueOf(this.hashCode());
    }

    public String getId() {
        return id;
    }

    public List<Card> getCards() {
        return cards;
    }

    public void setCards(List<Card> cards) {
        this.cards = cards;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
