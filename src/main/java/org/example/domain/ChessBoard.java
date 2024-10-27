package org.example.domain;

import org.example.domain.pieces.ChessPiece;
import org.example.domain.pieces.King;
import org.example.domain.pieces.Rook;

public class ChessBoard {
    public ChessPiece[][] board = new ChessPiece[8][8]; // creating a field for game
    Colour nowPlayer;

    public ChessBoard(Colour nowPlayer) {
        this.nowPlayer = nowPlayer;
    }

    public Colour nowPlayerColour() {
        return this.nowPlayer;
    }

    public boolean moveToPosition(int startLine, int startColumn, int endLine, int endColumn) {
        if (checkPos(startLine) && checkPos(startColumn)) {
            if (!nowPlayer.equals(board[startLine][startColumn].getColour())) return false;

            if (board[startLine][startColumn].canMoveToPosition(this, startLine, startColumn, endLine, endColumn)) {
                board[endLine][endColumn] = board[startLine][startColumn]; // if piece can move, we moved a piece
                board[startLine][startColumn].checkFirstMove = false;
                board[startLine][startColumn] = null; // set null to previous cell
                this.nowPlayer = this.nowPlayerColour().equals(Colour.WHITE) ? Colour.BLACK : Colour.WHITE;

                return true;
            } else return false;
        } else return false;
    }

    public void printBoard() {  //print board in console
        System.out.println("Turn " + nowPlayer);
        System.out.println();
        System.out.println("Player 2(Black)");
        System.out.println();
        System.out.println("\t0\t1\t2\t3\t4\t5\t6\t7");

        for (int i = 7; i > -1; i--) {
            System.out.print(i + "\t");
            for (int j = 0; j < 8; j++) {
                if (board[i][j] == null) {
                    System.out.print(".." + "\t");
                } else {
                    System.out.print(board[i][j].getSymbol() + board[i][j].getColour().name().substring(0, 1).toLowerCase() + "\t");
                }
            }
            System.out.println();
            System.out.println();
        }
        System.out.println("Player 1(White)");
    }

    public boolean checkPos(int pos) {
        return pos >= 0 && pos <= 7;
    }

    public boolean castling0() {
        Colour colour = nowPlayerColour();
        int line = switch (nowPlayerColour()) {
            case WHITE -> 0;
            case BLACK -> 7;
        };
        if (!checkCastling0(line, colour)) {
            return false;
        }

        //move king
        board[line][4] = null;
        board[line][2] = new King(colour);
        board[line][2].checkFirstMove = false;

        //move rook
        board[line][0] = null;
        board[line][3] = new Rook(colour);
        board[line][3].checkFirstMove = false;

        return true;
    }

    public boolean castling7() {
        Colour colour = nowPlayerColour();
        int line = switch (colour) {
            case WHITE -> 0;
            case BLACK -> 7;
        };
        if (!checkCastling7(line, colour)) {
            return false;
        }

        //move king
        board[line][4] = null;
        board[line][6] = new King(colour);
        board[line][6].checkFirstMove = false;

        //move rook
        board[line][7] = null;
        board[line][5] = new Rook(colour);
        board[line][5].checkFirstMove = false;

        return true;
    }

    private boolean checkCastling0(int line, Colour colour) {
        // add check for first move
        if (!(board[line][0] != null
                && board[line][0].getColour().equals(colour)
                && board[line][0] instanceof Rook
                && board[line][0].checkFirstMove
        )) {
            return false;
        }

        if (!(board[line][4] != null
                && board[line][4].getColour().equals(colour)
                && board[line][4] instanceof King
                && board[line][4].checkFirstMove
        )) {
            return false;
        }

        if (((King) board[line][4]).isUnderAttack(this, line, 4)) {
            return false;
        }
        for (int i = 1; i < 4; i++) {
            if (board[line][i] != null) {
                return false;
            }
        }
        return true;
    }

    private boolean checkCastling7(int line, Colour colour) {
        if (!(board[line][7] != null
                && board[line][7].getColour().equals(colour)
                && board[line][7] instanceof Rook
                && board[line][7].checkFirstMove
        )) {
            return false;
        }

        if (!(board[line][4] != null
                && board[line][4].getColour().equals(colour)
                && board[line][4] instanceof King
                && board[line][4].checkFirstMove
        )) {
            return false;
        }
        if (((King) board[line][4]).isUnderAttack(this, 0, 4)) {
            return false;
        }
        for (int i = 5; i < 7; i++) {
            if (board[line][i] != null) {
                return false;
            }
        }
        return true;
    }
}