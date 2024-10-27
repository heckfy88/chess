package org.example.domain.pieces;

import org.apache.commons.lang3.ArrayUtils;
import org.example.domain.ChessBoard;
import org.example.domain.Colour;
import org.example.domain.Piece;

import java.util.stream.IntStream;

public class Bishop extends ChessPiece {
    public Bishop(Colour colour) {
        super(colour);
    }

    @Override
    public String getSymbol() {
        return Piece.BISHOP.getSymbol();
    }

    @Override
    public boolean canMove(ChessBoard board, int line, int column, int toLine, int toColumn) {
        return canMoveLikeBishop(line, column, toLine, toColumn);
    }

    @Override
    protected boolean isAProperPiece(ChessBoard board, int line, int column) {
        return board.board[line][column] instanceof Bishop;
    }

    @Override
    protected boolean hasFigureInAWay(ChessBoard board, int line, int column, int toLine, int toColumn) {
        int[] lineIndexes;
        int[] columnIndexes;
        if (toLine - line > 0) {
            lineIndexes = IntStream.range(line + 1, toLine).toArray();
        } else {
            lineIndexes = IntStream.range(toLine + 1, line).toArray();
            ArrayUtils.reverse(lineIndexes);
        }

        if (toColumn - column > 0) {
            columnIndexes = IntStream.range(column + 1, toColumn).toArray();
        } else {
            columnIndexes = IntStream.range(toColumn + 1, column).toArray();
            ArrayUtils.reverse(columnIndexes);
        }

        for (int index = 0; index < lineIndexes.length; index++) {
            if (board.board[lineIndexes[index]][columnIndexes[index]] != null) {
                return true;
            }
        }

        return board.board[toLine][toColumn] != null
                && board.board[toLine][toColumn].colour == board.nowPlayerColour();
    }

    private boolean canMoveLikeBishop(int line, int column, int toLine, int toColumn) {
        return Math.abs(toLine - line) == Math.abs(toColumn - column);
    }
}
