package trello.manager;

import trello.exceptions.BoardIdNotFound;
import trello.exceptions.CardListAlreadyExists;
import trello.exceptions.CardListNotFoundInBoard;
import trello.models.Board;
import trello.models.CardList;
import trello.models.Privacy;
import trello.models.User;
import trello.services.BoardService;
import trello.services.ListService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

//stateful
public class UseCaseManager {


    private List<Board> boards;
    private HashMap<String, User> usersMap;

    public UseCaseManager() {
        boards = new ArrayList<>();
        usersMap = new HashMap<>();
    }

    //user use cases

    public void addUser(String userId, String email, String name) {
        User toAdd = new User(userId, email, name);
        usersMap.put(userId, toAdd);
    }

    //Board use cases

    public String createBoard(String name) {
        Board newBoard = BoardService.create(name);
        boards.add(newBoard);
        return newBoard.getId();
    }

    public Board changeBoardName(String boardId, String newName) throws BoardIdNotFound {
        Board board = getBoardElementById(boardId);
        BoardService.changeName(board, newName);
        return board;
    }

    public Board changeBoardPrivacy(String boardId, String newVisibility) throws BoardIdNotFound {
        Board board = getBoardElementById(boardId);
        Privacy newPrivacy = Privacy.valueOf(newVisibility);
        BoardService.changePrivacy(board, newPrivacy);
        return board;
    }

    public Board addMember(String boardId, String userId) throws BoardIdNotFound {
        Board board = getBoardElementById(boardId);
        User userToAdd = usersMap.get(userId);
        BoardService.addMember(board, userToAdd);
        return board;
    }

    public Board removeMember(String boardId, String userId) throws BoardIdNotFound {
        Board board = getBoardElementById(boardId);
        BoardService.removeMember(board, usersMap.get(userId));
        return board;
    }

    public String deleteBoard(String id) throws BoardIdNotFound {
        Board board = getBoardElementById(id);
        BoardService.deleteBoard(board, boards);
        return board.getId();
    }

    public List<Board> getAllBoards() {
        return boards;
    }

    //List usecases

    public String createList(String boardId, String listName) throws BoardIdNotFound, CardListAlreadyExists {
        Board board = getBoardElementById(boardId);
        if (!getCardListByName(board, listName).isEmpty()) throw new CardListAlreadyExists();
        return ListService.create(board, listName);
    }

    public void deleteCardList(String boardId, String cardListId) throws BoardIdNotFound, CardListNotFoundInBoard {
        Board board = getBoardElementById(boardId);
        List<CardList> cardLists = getCardListById(board, cardListId);
        if (cardLists.isEmpty()) throw new CardListNotFoundInBoard();
        ListService.delete(board, cardLists.get(0));
    }

    //Utils

    private List<CardList> getCardListByName(Board board, String cardListName) {
        return board.getCardLists().stream().filter(cardList -> cardList.getName().equalsIgnoreCase(cardListName)).collect(Collectors.toList());
    }

    private List<CardList> getCardListById(Board board, String cardListId) {
        return board.getCardLists().stream().filter(cardList -> cardList.getId().equalsIgnoreCase(cardListId)).collect(Collectors.toList());
    }

    private Board getBoardElementById(String id) throws BoardIdNotFound {
        List<Board> retrieved = boards.stream().filter(board -> board.getId().equalsIgnoreCase(id)).collect(Collectors.toList());
        if (retrieved.isEmpty()) throw new BoardIdNotFound();
        return retrieved.get(0);
    }

}
