package org.example.domain.pieces;

import org.example.domain.ChessBoard;
import org.example.domain.Colour;
import org.example.domain.Piece;

public class King extends ChessPiece {
    public King(Colour colour) {
        super(colour);
    }

    @Override
    public String getSymbol() {
        return Piece.KING.getSymbol();
    }

    @Override
    public boolean canMove(ChessBoard board, int line, int column, int toLine, int toColumn) {
        return canMoveLikeKing(line, column, toLine, toColumn)
                && (!isUnderAttack(board, line, column) || !isUnderAttack(board, toLine, toColumn));
    }

    public boolean isUnderAttack(ChessBoard board, int line, int column) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (board.board[i][j] != null
                        && board.board[i][j].colour != this.colour
                        && board.board[i][j].canMove(board, i, j, line, column)
                        && !board.board[i][j].hasFigureInAWay(board, i, j, line, column)
                ) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    protected boolean isAProperPiece(ChessBoard board, int line, int column) {
        return board.board[line][column] instanceof King;
    }

    @Override
    protected boolean hasFigureInAWay(ChessBoard board, int line, int column, int toLine, int toColumn) {
        return board.board[toLine][toColumn] != null && board.board[toLine][toColumn].colour == this.colour;
    }

    private boolean canMoveLikeKing(int line, int column, int toLine, int toColumn) {
        return Math.abs(toLine - line) <= 1 && Math.abs(toColumn - column) <= 1;
    }
}
