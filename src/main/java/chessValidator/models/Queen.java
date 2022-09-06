package chessValidator.models;

import java.util.List;
import java.util.Set;

public class Queen extends Piece{
    public Queen(PieceColor color) {
        super(color, 'Q');
    }

    @Override
    public Set<List<Integer>> getValidPositionsFrom(int[] start, int[] end, Piece[][] chessboard) throws InvalidMoveException {
        return null;
    }
}
