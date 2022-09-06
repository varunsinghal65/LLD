package chessValidator.services;

import chessValidator.models.InvalidMoveException;
import chessValidator.models.Piece;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

public class ChessboardService {

    public String[][] getRowsForPrinting(Piece[][] chessboard) {
        String[][] toReturn = new String[chessboard.length][chessboard.length];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (chessboard[i][j] == null) {
                    toReturn[i][j] = "--";
                } else {
                    Piece piece = chessboard[i][j];
                    toReturn[i][j] = piece.getColor().toString().toUpperCase().charAt(0) +
                            "" + piece.getLetterDesignation();
                }
            }
        }
        return toReturn;
    }

    public void move(Piece[][] chessboard, int[] start, int[] end) throws InvalidMoveException {
        if (!isWithinBounds(start, end)) throw new InvalidMoveException("out of bounds");
        //get candidate pos
        Piece currPiece = chessboard[start[0]][start[1]];
        Set<List<Integer>> validPos = chessboard[start[0]][start[1]].getValidPositionsFrom(start, end, chessboard);
        //check if end is a valid move
        if (!validPos.contains(Arrays.asList(end[0], end[1])))
            throw new InvalidMoveException("In bounds.However, move not allowed for this piece");
        //execute the move
        chessboard[start[0]][start[1]] = null;
        chessboard[end[0]][end[1]] = currPiece;
    }

    private boolean isWithinBounds(int[] start, int[] end) {
        return start[0] >= 0 && start[0] < 8 && end[0] >= 0 && end[1] < 8;
    }
}
