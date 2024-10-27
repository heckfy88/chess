package org.example.domain.pieces;

import org.example.domain.ChessBoard;
import org.example.domain.Colour;
import org.example.domain.Piece;

import java.util.stream.IntStream;

public class Rook extends ChessPiece {
    public Rook(Colour colour) {
        super(colour);
    }

    @Override
    public String getSymbol() {
        return Piece.ROOK.getSymbol();
    }

    @Override
    public boolean canMove(ChessBoard board, int line, int column, int toLine, int toColumn) {
        return canMoveLikeRook(line, column, toLine, toColumn);
    }

    @Override
    protected boolean isAProperPiece(ChessBoard board, int line, int column) {
        return board.board[line][column] instanceof Rook;
    }

    @Override
    protected boolean hasFigureInAWay(ChessBoard board, int line, int column, int toLine, int toColumn) {
        if (toLine - line == 0) {
            int[] columnIndexes = IntStream.range(column + 1, toColumn).toArray();
            for (int columnIndex : columnIndexes) {
                if (board.board[line][columnIndex] != null) {
                    return true;
                }
            }
        }
        if (toColumn - column == 0) {
            int[] lineIndexes = IntStream.range(line + 1, toLine).toArray();
            for (int lineIndex : lineIndexes) {
                if (board.board[lineIndex][column] != null) {
                    return true;
                }
            }
        }

        return board.board[toLine][toColumn] != null
                && board.board[toLine][toColumn].colour == board.nowPlayerColour();
    }

    private boolean canMoveLikeRook(int line, int column, int toLine, int toColumn) {
        return ((toLine - line == 0) && (toColumn - column != 0)) ||
                ((toColumn - column == 0) && (toLine - line != 0));
    }
}
