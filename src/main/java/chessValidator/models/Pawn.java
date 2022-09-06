package chessValidator.models;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Pawn extends Piece {

    public Pawn(PieceColor color) {
        super(color, 'P');
    }

    @Override
    public Set<List<Integer>> getValidPositionsFrom(int[] start, int[] end, Piece[][] chessboard) throws InvalidMoveException {
        HashSet<List<Integer>> validPos = new HashSet<>();
        if (color.equals(PieceColor.BLACK)) {
            //move 1 down
            if (start[0] + 1 < 8 && chessboard[start[0] + 1][start[1]] == null) {
                validPos.add(Arrays.asList(start[0] + 1, start[1]));
            }
            //move 2 down
            if (start[0] == 1 && chessboard[start[0] + 2][start[1]] == null) {
                validPos.add(Arrays.asList(start[0] + 2, start[1]));
            }
            //move down diagnoally to the left
            if (start[0] + 1 < 8 && start[1] - 1 >= 0
                    && chessboard[start[0] + 1][start[1] - 1] != null
                    && chessboard[start[0] + 1][start[1] - 1].color.equals(PieceColor.WHITE)) {
                validPos.add(Arrays.asList(start[0] + 1, start[1] - 1));
            }
            //move diagnoally to the right
            if (start[0] + 1 < 8 && start[1] + 1 < 8
                    && chessboard[start[0] + 1][start[1] + 1] != null
                    && chessboard[start[0] + 1][start[1] + 1].color.equals(PieceColor.WHITE)) {
                validPos.add(Arrays.asList(start[0] + 1, start[1] + 1));
            }
        } else {
            //move 1 up
            if (start[0] - 1 >= 0 && chessboard[start[0] - 1][start[1]] == null) {
                validPos.add(Arrays.asList(start[0] - 1, start[1]));
            }
            //move 2 up
            if (start[0] == 6 && chessboard[start[0] - 2][start[1]] == null) {
                validPos.add(Arrays.asList(start[0] - 2, start[1]));
            }
            //move up diagnolly to the left
            if (start[0] - 1 >= 0 && start[1] - 1 >= 0
                    && chessboard[start[0] - 1][start[1] - 1] != null
                    && chessboard[start[0] - 1][start[1] - 1].color.equals(PieceColor.BLACK)) {
                validPos.add(Arrays.asList(start[0] - 1, start[1] - 1));
            }
            //move up diagnolly to the right
            if (start[0] - 1 >= 0 && start[1] + 1 < 8
                    && chessboard[start[0] - 1][start[1] + 1] != null
                    && chessboard[start[0] - 1][start[1] + 1].color.equals(PieceColor.BLACK)) {
                validPos.add(Arrays.asList(start[0] - 1, start[1] + 1));
            }
        }
        return validPos;
    }

}
