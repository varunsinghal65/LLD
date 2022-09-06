package chessValidator.manager;

import chessValidator.models.*;
import chessValidator.services.ChessboardService;

import java.util.Arrays;

public class UcManager {

    private Piece[][] chessboard;
    private PieceColor nextTurn;
    private ChessboardService chessboardService;

    public UcManager() {
        chessboard = new Piece[8][8];
        nextTurn = PieceColor.WHITE;
        chessboardService = new ChessboardService();
    }

    public void initBoard() {
        //black
        for (int j = 0; j < 8; j++) {
            if (j == 0 || j == 7) {
                chessboard[0][j] = new Rook(PieceColor.BLACK);
            } else if (j == 1 || j == 6) {
                chessboard[0][j] = new Knight(PieceColor.BLACK);
            } else if (j == 2 || j == 5) {
                chessboard[0][j] = new Bishop(PieceColor.BLACK);
            } else if (j == 3) {
                chessboard[0][j] = new Queen(PieceColor.BLACK);
            } else {
                chessboard[0][j] = new King(PieceColor.BLACK);
            }
        }
        for (int j = 0; j < 8; j++) {
            Arrays.fill(chessboard[1], new Pawn(PieceColor.BLACK));
        }
        //white
        for (int j = 0; j < 8; j++) {
            if (j == 0 || j == 7) {
                chessboard[7][j] = new Rook(PieceColor.WHITE);
            } else if (j == 1 || j == 6) {
                chessboard[7][j] = new Knight(PieceColor.WHITE);
            } else if (j == 2 || j == 5) {
                chessboard[7][j] = new Bishop(PieceColor.WHITE);
            } else if (j == 3) {
                chessboard[7][j] = new Queen(PieceColor.WHITE);
            } else {
                chessboard[7][j] = new King(PieceColor.WHITE);
            }
        }
        for (int j = 0; j < 8; j++) {
            Arrays.fill(chessboard[6], new Pawn(PieceColor.WHITE));
        }
    }

    public String[][] getBoardForPrinting() {
        return chessboardService.getRowsForPrinting(chessboard);
    }

    public void move(String start, String end) throws InvalidMoveException {
        //compute start and end positions in the grid
        int startCol = start.charAt(0) - 'a';
        int startRow = 8 - Character.getNumericValue(start.charAt(1));
        int endCol = end.charAt(0) - 'a';
        int endRow = 8 - Character.getNumericValue(end.charAt(1));
        int[] startPos = new int[]{startRow, startCol};
        int[] endPos = new int[]{endRow, endCol};
        System.out.println("Attempting a move from: [" + startPos[0] + "," + startPos[1] + "] " +
                "to [" + endPos[0] + "," + endPos[1] + "]");
        //check for the right player
        if (!chessboard[startPos[0]][startPos[1]].getColor().equals(nextTurn))
            throw new InvalidMoveException("Wrong colored piece chosen for movement");
        //attempt move
        chessboardService.move(chessboard, startPos, endPos);
        //update the next color
        nextTurn = nextTurn == PieceColor.BLACK ? PieceColor.WHITE : PieceColor.BLACK;
    }
}
