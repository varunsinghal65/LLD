package trello.services;

import trello.models.Board;
import trello.models.CardList;

import java.util.ArrayList;

public class ListService {
    public static String create(Board board, String listName) {
        CardList cl = new CardList(new ArrayList<>(), listName);
        board.getCardLists().add(cl);
        return cl.getId();
    }

    public static void delete(Board board, CardList cardList) {
        board.getCardLists().remove(cardList);
    }
}
