package chessValidator.models;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Rook extends Piece {
    public Rook(PieceColor color) {
        super(color, 'R');
    }

    @Override
    public Set<List<Integer>> getValidPositionsFrom(int[] start, int[] end, Piece[][] chessboard) throws InvalidMoveException {
        HashSet<List<Integer>> validPos = new HashSet<>();

        //move up
        int pointer = start[0] - 1;
        while (pointer >= 0) {
            validPos.add(Arrays.asList(pointer, start[1]));
            if (chessboard[pointer][start[1]] != null)
                break;
            pointer--;
        }
        //move right
        pointer = start[1] + 1;
        while (pointer < 8) {
            validPos.add(Arrays.asList(start[0], pointer));
            if (chessboard[start[0]][pointer] != null)
                break;
            pointer++;
        }
        //move down
        pointer = start[0] + 1;
        while (pointer < 8) {
            validPos.add(Arrays.asList(pointer, start[1]));
            if (chessboard[pointer][start[1]] != null)
                break;
            pointer++;
        }
        //move left
        pointer = start[1] - 1;
        while (pointer >= 0) {
            validPos.add(Arrays.asList(start[0], pointer));
            if (chessboard[start[0]][pointer] != null)
                break;
            pointer--;
        }

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
