package chessValidator.models;

import java.util.List;
import java.util.Set;

public class King extends Piece{
    public King(PieceColor color) {
        super(color, 'K');
    }

    @Override
    public Set<List<Integer>> getValidPositionsFrom(int[] start, int[] end, Piece[][] chessboard) throws InvalidMoveException {
        return null;
    }
}
