package com.chess.engine.classic.player.ai;

import com.chess.engine.classic.board.Board;
import com.chess.engine.classic.pieces.Piece;
import com.chess.engine.classic.player.Player;

public class StandardBoardEvaluator implements BoardEvaluator {
    private static final int CHECK_BONUS = 50;
    private static final int CHECK_MATE_BONUS = 10_000;
    private static final int DEPTH_BONUS = 100;
    private static final int CASTLED_BONUS = 60;

    @Override
    public int evaluate(Board board,
                        int depth) {
        return scorePlayer(board, board.whitePlayer(), depth) -
               scorePlayer(board, board.blackPlayer(), depth);
    }

    private int scorePlayer(final Board board,
                            final Player player,
                            final int depth) {
        return pieceValue(player) +
               mobility(player) +
               check(player) +
               checkmate(player, depth) +
               castled(player);
    }

    private static int castled(final Player player) {
        return player.isCastled() ? CASTLED_BONUS : 0;
    }

    private static int checkmate(final Player player, int depth) {
        return player.getOpponent().isInCheckMate() ? CHECK_MATE_BONUS * depthBonus(depth) : 0;
    }

    private static int depthBonus(final int depth) {
        return depth == 0 ? 1 : DEPTH_BONUS * depth;
    }

    private static  int check(final Player player) {
        return player.getOpponent().isInCheck() ? CHECK_BONUS : 0;
    }

    private static int mobility(final Player player) {
        return player.getLegalMoves().size();
    }

    private static int pieceValue(final Player player) {
        int pieceValueScore = 0;
        for(final Piece piece : player.getActivePieces()) {
            pieceValueScore += piece.getPieceValue();
        }
        return pieceValueScore;
    }
}
