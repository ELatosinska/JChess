package com.chess;

import com.chess.engine.classic.board.Board;
import com.chess.gui.Table;

public class JChess {
    public static void main(String[] args) {
        Board board = Board.createStandardBoard();
        Table.get().show();
    }
}
