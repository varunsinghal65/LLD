package chessValidator.models;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Knight extends Piece {
    public Knight(PieceColor color) {
        super(color, 'N');
    }

    @Override
    public Set<List<Integer>> getValidPositionsFrom(int[] start, int[] end, Piece[][] chessboard) throws InvalidMoveException {
        HashSet<List<Integer>> validPos = new HashSet<>();

        //2 cols left 1 up and 1 down
        if (start[1] - 2 >= 0 && start[0] + 1 < 8) validPos.add(Arrays.asList(start[0] + 1, start[1] - 2));
        if (start[1] - 2 >= 0 && start[0] - 1 >= 0) validPos.add(Arrays.asList(start[0] - 1, start[1] - 2));

        //2 cols right 1 up and 1 down
        if (start[1] + 2 < 8 && start[0] - 1 >= 0) validPos.add(Arrays.asList(start[0] - 1, start[1] + 2));
        if (start[1] + 2 < 8 && start[0] + 1 < 8) validPos.add(Arrays.asList(start[0] + 1, start[1] + 2));

        //2 rows down 1 left and 1 right
        if (start[0] + 2 < 8 && start[1] - 1 >= 0) validPos.add(Arrays.asList(start[0] + 2, start[1] - 1));
        if (start[0] + 2 < 8 && start[1] + 1 < 8) validPos.add(Arrays.asList(start[0] + 2, start[1] + 1));

        //2 rows up 1 left and 1 right
        if (start[0] - 2 >= 0 && start[1] - 1 >= 0) validPos.add(Arrays.asList(start[0] - 2, start[1] - 1));
        if (start[0] - 2 >= 0 && start[1] + 1 < 8) validPos.add(Arrays.asList(start[0] - 2, start[1] + 1));

        //filter out all positions that are not null and have same color
        return validPos.stream().filter(pos -> {
            if (chessboard[pos.get(0)][pos.get(1)] == null) {
                return true;
            } else {
                return !chessboard[pos.get(0)][pos.get(1)].getColor().equals(this.color);
            }
        }).collect(Collectors.toSet());
    }
}
