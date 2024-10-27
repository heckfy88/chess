package org.example.domain.pieces;

import org.example.domain.ChessBoard;
import org.example.domain.Colour;
import org.example.domain.Piece;

import java.util.List;

public class Pawn extends ChessPiece {
    public Pawn(Colour colour) {
        super(colour);
    }

    @Override
    public String getSymbol() {
        return Piece.PAWN.getSymbol();
    }

    @Override
    public boolean canMove(ChessBoard board, int line, int column, int toLine, int toColumn) {
        return canMoveLikePawn(line, column, toLine, toColumn);
    }

    @Override
    protected boolean isAProperPiece(ChessBoard board, int line, int column) {
        return board.board[line][column] instanceof Pawn;
    }

    private boolean canMoveLikePawn(int line, int column, int toLine, int toColumn) {
        // can either move forward 1 or 2 spaces or attack diagonally
        return isFirstMove(line, column, toLine, toColumn)
                || isAttackOrMove(line, column, toLine, toColumn);

    }

    @Override
    protected boolean hasFigureInAWay(ChessBoard board, int line, int column, int toLine, int toColumn) {
        if (toColumn - column == 0) {
            if (toLine - line == 1) {
                return board.board[toLine][toColumn] != null;
            }
            if (toLine - line == 2) {
                return (board.board[toLine][toColumn] != null || board.board[toLine - 1][toColumn] != null);
            }
        }


        return board.board[toLine][toColumn] != null
                && board.board[toLine][toColumn].colour == this.colour;
    }

    private boolean isFirstMove(int line, int column, int toLine, int toColumn) {
        return (line == 1 && (List.of(2, 3).contains(toLine)) && column == toColumn)
                || (line == 6 && (List.of(4, 5).contains(toLine)) && column == toColumn);
    }

    private boolean isAttackOrMove(int line, int column, int toLine, int toColumn) {
        return
                // white pawns move up, black - down
                (switch (this.colour) {
                    case WHITE -> (toLine - line == 1);
                    case BLACK -> (line - toLine == 1);
                })
                        // can either attack diagonally or move on the same column
                        && List.of(0, 1).contains(toColumn - column);
    }
}