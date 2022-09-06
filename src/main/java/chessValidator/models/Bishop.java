package chessValidator.models;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Bishop extends Piece {
    public Bishop(PieceColor color) {
        super(color, 'B');
    }

    @Override
    public Set<List<Integer>> getValidPositionsFrom(int[] start, int[] end, Piece[][] chessboard) throws InvalidMoveException {
        Set<List<Integer>> validPos = new HashSet<>();

        return validPos;
    }
}
