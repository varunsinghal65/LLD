package chessValidator.models;

import java.util.List;
import java.util.Set;

public abstract class Piece {
    protected PieceColor color;
    protected Character letterDesignation;

    public Piece(PieceColor color, Character letterDesignation) {
        this.color = color;
        this.letterDesignation = letterDesignation;
    }

    public PieceColor getColor() {
        return color;
    }

    public Character getLetterDesignation() {
        return letterDesignation;
    }

    public abstract Set<List<Integer>> getValidPositionsFrom(int[] start, int[] end, Piece[][] chessboard) throws InvalidMoveException;
}
