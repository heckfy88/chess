package org.example.domain;

public enum Piece {
    PAWN("P"),
    HORSE("H"),
    BISHOP("B"),
    ROOK("R"),
    KING("K"),
    QUEEN("Q"),
    ;
    private final String symbol;
    Piece(String symbol) {
        this.symbol = symbol;
    }

    public String getSymbol() {
        return symbol;
    }

    public static Piece fromSymbol(String symbol) {
        for (Piece piece : Piece.values()) {
            if (piece.getSymbol().equals(symbol)) {
                return piece;
            }
        }
        return null;
    }
}
