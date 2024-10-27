package org.example.domain.pieces;

import org.example.domain.ChessBoard;
import org.example.domain.Colour;

public abstract class ChessPiece {
    public boolean checkFirstMove = true;
    protected Colour colour;

    protected ChessPiece(Colour colour) {
        this.colour = colour;
    }

    public Colour getColour() {
        return colour;
    }

    public boolean canMoveToPosition(
            ChessBoard board,
            int line, int column,
            int toLine, int toColumn
    ) {
        return isAProperPiece(board, line, column)
                && insideBoard(toLine, toColumn)
                && !isInitialPosition(line, column, toLine, toColumn)
                && canMove(board, line, column, toLine, toColumn)
                && !hasFigureInAWay(board, line, column, toLine, toColumn);
    }

    public abstract boolean canMove(
            ChessBoard board,
            int line, int column,
            int toLine, int toColumn
    );

    public abstract String getSymbol();

    protected boolean isInitialPosition(int line, int column, int toLine, int toColumn) {
        return line == toLine && column == toColumn;
    }

    protected abstract boolean isAProperPiece(ChessBoard board, int line, int column);

    protected boolean insideBoard(int line, int column) {
        return line >= 0 && line <= 7 && column >= 0 && column <= 7;
    }

    protected abstract boolean hasFigureInAWay(ChessBoard board, int line, int column, int toLine, int toColumn);

}