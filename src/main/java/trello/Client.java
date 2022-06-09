package trello;

import trello.exceptions.BoardIdNotFound;
import trello.exceptions.CardListAlreadyExists;
import trello.exceptions.CardListNotFoundInBoard;
import trello.manager.UseCaseManager;
import trello.models.Board;

import java.util.List;

public class Client {

    private static final UseCaseManager ucManager = new UseCaseManager();
    private static final String ID_DOES_NOT_EXIST = "Board does not exist with id :";

    public static void main(String[] args) {

        //add users
        ucManager.addUser("user1", "varun@gmail.com", "varun");
        ucManager.addUser("user2", "user2@gmail.com", "USER2NAME");
        ucManager.addUser("user3", "user3@gmail.com", "USER3NAME");

        //create and delete board
        String boardId = ucManager.createBoard("work@tech");
        System.out.println("Board created with id: " + boardId);
        try {
            String boardId2 = ucManager.createBoard("varunBoard");
            System.out.println("Board created with id: " + boardId2);
            System.out.println("Board was deleted with id:" + ucManager.deleteBoard(boardId2));
        } catch (BoardIdNotFound boardIdNotFound) {
            boardIdNotFound.printStackTrace();
        }
        // change board name
        try {
            System.out.println(ucManager.changeBoardName(boardId, "workat.tech"));
        } catch (BoardIdNotFound boardIdNotFound) {
            System.out.println(ID_DOES_NOT_EXIST + boardId);
        }
        // change board privacy
        try {
            System.out.println(ucManager.changeBoardPrivacy(boardId, "PRIVATE"));
        } catch (BoardIdNotFound boardIdNotFound) {
            System.out.println(ID_DOES_NOT_EXIST + boardId);
        }
        // add/remove members in the board
        try {
            ucManager.addMember(boardId, "user1");
            ucManager.addMember(boardId, "user2");
            System.out.println(ucManager.addMember(boardId, "user3"));
            System.out.println(ucManager.removeMember(boardId, "user3"));
        } catch (BoardIdNotFound boardIdNotFound) {
            System.out.println(ID_DOES_NOT_EXIST + boardId);
        }

        //show all boards
        List<Board> boards = ucManager.getAllBoards();
        System.out.println("Showing all boards now");
        boards.forEach(System.out::println);

        //create and delete a list
        try {
            String listId = ucManager.createList(boardId, "list1");
            System.out.println("new list created in board id " + boardId + " with id: " + listId);
            System.out.println("Showing all boards now");
            boards.forEach(System.out::println);
            ucManager.deleteCardList(boardId, listId);
            System.out.println("card list deleted in board id " + boardId + " with id: " + listId);
            boards.forEach(System.out::println);
            listId = ucManager.createList(boardId, "list1");
        } catch (BoardIdNotFound e) {
            System.out.println(ID_DOES_NOT_EXIST + boardId);
        } catch (CardListAlreadyExists e) {
            System.out.println("list1 already exists in the board id: " + boardId);
        } catch (CardListNotFoundInBoard cardListNotFoundInBoard) {
            cardListNotFoundInBoard.printStackTrace();
        }


    }



}
