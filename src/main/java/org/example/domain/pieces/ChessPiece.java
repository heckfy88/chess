package org.example.domain.pieces;

import org.apache.commons.lang3.tuple.Pair;
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
                && !isCheck(board, line, column, toLine, toColumn)
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

    protected boolean isCheck(ChessBoard board, int line, int column, int toLine, int toColumn) {
        boolean isUnderAttack = switch (board.nowPlayerColour()) {
            case WHITE ->
                    new King(Colour.WHITE).isUnderAttack(board, board.whiteKingPosition.getLeft(), board.whiteKingPosition.getRight());
            case BLACK ->
                    new King(Colour.BLACK).isUnderAttack(board, board.blackKingPosition.getLeft(), board.blackKingPosition.getRight());
        };
        if (!isUnderAttack) return false;

        ChessPiece initialFromToPiece = board.board[line][column];
        ChessPiece initialToPiece = board.board[toLine][toColumn];
        Pair<Integer, Integer> initialWhiteKingPosition = board.whiteKingPosition;
        Pair<Integer, Integer> initialBlackKingPosition = board.blackKingPosition;

        if (board.board[line][column] instanceof King) {
            board.board[line][column] = null;
            board.board[toLine][toColumn] = new King(board.nowPlayerColour());
            isUnderAttack = switch (board.nowPlayerColour()) {
                case WHITE -> {
                    board.whiteKingPosition = Pair.of(toLine, toColumn);
                    yield ((King) board.board[board.whiteKingPosition.getLeft()][board.whiteKingPosition.getRight()])
                            .isUnderAttack(board, board.whiteKingPosition.getLeft(), board.whiteKingPosition.getRight());
                }
                case BLACK -> {
                    board.blackKingPosition = Pair.of(toLine, toColumn);
                    yield ((King) board.board[board.blackKingPosition.getLeft()][board.blackKingPosition.getRight()])
                            .isUnderAttack(board, board.blackKingPosition.getLeft(), board.blackKingPosition.getRight());
                }
            };
        } else {
            // insert a fake pawn at the position of the move to check whether the king is still under attack, then remove it
            board.board[toLine][toColumn] = new Pawn(board.nowPlayerColour());
            isUnderAttack = switch (board.nowPlayerColour()) {
                case WHITE ->
                        ((King) board.board[board.whiteKingPosition.getLeft()][board.whiteKingPosition.getRight()])
                                .isUnderAttack(board, board.whiteKingPosition.getLeft(), board.whiteKingPosition.getRight());
                case BLACK ->
                        ((King) board.board[board.blackKingPosition.getLeft()][board.blackKingPosition.getRight()])
                                .isUnderAttack(board, board.blackKingPosition.getLeft(), board.blackKingPosition.getRight());
            };
        }
        board.board[line][column] = initialFromToPiece;
        board.board[toLine][toColumn] = initialToPiece;
        board.whiteKingPosition = initialWhiteKingPosition;
        board.blackKingPosition = initialBlackKingPosition;
        return isUnderAttack;
    }
}