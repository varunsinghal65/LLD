package trello.services;

import trello.models.Board;
import trello.models.Privacy;
import trello.models.User;

import java.util.List;

public class BoardService {

    private BoardService() {
    }

    public static Board create(String name) {
        return new Board(name);
    }

    public static void changeName(Board board, String newName) {
        board.setName(newName);
    }

    public static void changePrivacy(Board board, Privacy newVisibility) {
        board.setVisibility(newVisibility);
    }

    public static void addMember(Board board, User toAdd) {
        board.getMembers().add(toAdd);
    }

    public static void removeMember(Board board, User toRemove) {
        board.getMembers().remove(toRemove);
    }

    public static void deleteBoard(Board toDelete, List<Board> boards) {
        boards.remove(toDelete);
    }
}
