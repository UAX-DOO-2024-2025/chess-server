package com.chess.logic;

public class ChessGame {
    private String[][] board;

    public ChessGame() {
        this.board = new String[8][8];
    }

    public void initializeBoard() {
        String[] backRow = {"R", "N", "B", "Q", "K", "B", "N", "R"};
        String[] pawns = {"P", "P", "P", "P", "P", "P", "P", "P"};

        board[0] = backRow.clone();
        board[1] = pawns.clone();
        board[6] = pawns.clone();
        board[7] = backRow.clone();

        for (int i = 2; i < 6; i++) {
            board[i] = new String[8];
        }
    }

    public String[][] getBoard() {
        return board;
    }
}
