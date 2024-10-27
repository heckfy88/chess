package org.example.domain.pieces;

import org.example.domain.ChessBoard;
import org.example.domain.Colour;
import org.example.domain.Piece;

public class Horse extends ChessPiece {
    public Horse(Colour colour) {
        super(colour);
    }

    @Override
    public String getSymbol() {
        return Piece.HORSE.getSymbol();
    }

    @Override
    public boolean canMove(ChessBoard board, int line, int column, int toLine, int toColumn) {
        return canMoveLikeHorse(line, column, toLine, toColumn);
    }

    @Override
    protected boolean isAProperPiece(ChessBoard board, int line, int column) {
        return board.board[line][column] instanceof Horse;
    }

    @Override
    protected boolean hasFigureInAWay(ChessBoard board, int line, int column, int toLine, int toColumn) {
        return board.board[toLine][toColumn] != null
                && board.board[toLine][toColumn].colour == this.colour;
    }

    private boolean canMoveLikeHorse(int line, int column, int toLine, int toColumn) {
        return (Math.abs(toLine - line) == 1 && Math.abs(toColumn - column) == 2) ||
                (Math.abs(toLine - line) == 2 && Math.abs(toColumn - column) == 1);
    }
}
