package com.chess.engine.classic.pieces;

import com.chess.engine.classic.Alliance;
import com.chess.engine.classic.board.Board;
import com.chess.engine.classic.board.Move;

import java.util.Collection;

public abstract class Piece {
    protected final int piecePosition;
    protected final Alliance pieceAlliance;
    protected final boolean isFirstMove;
    protected final PieceType pieceType;
    private final int cachedHashCode;

    /*Piece(final int piecePosition,
          final Alliance pieceAlliance,
          final PieceType pieceType) {

        this.piecePosition = piecePosition;
        this.pieceAlliance = pieceAlliance;
        this.pieceType = pieceType;
        //TODO more work here!
        this.isFirstMove = true;
        this.cachedHashCode = computeHashCode();
    }*/
    Piece(final int piecePosition,
          final Alliance pieceAlliance,
          final PieceType pieceType, final boolean isFirstMove) {

        this.piecePosition = piecePosition;
        this.pieceAlliance = pieceAlliance;
        this.pieceType = pieceType;
        //TODO more work here!
        this.isFirstMove = isFirstMove;
        this.cachedHashCode = computeHashCode();
    }

    private int computeHashCode() {
        int result = pieceType.hashCode();
        result = 31 * result + pieceAlliance.hashCode();
        result = 31 * result + piecePosition;
        result =  31 * result + (isFirstMove ? 1: 0);
        return  result;
    }

    @Override
    public boolean equals(final Object other) {
        if(this == other) {
            return true;
        }
        if(!(other instanceof Piece)) {
            return false;
        }
        final Piece otherPiece = (Piece) other;
        return piecePosition == otherPiece.getPiecePosition() &&
                pieceType == otherPiece.getPieceType() &&
                pieceAlliance == otherPiece.getPieceAlliance() &&
                isFirstMove == otherPiece.isFirstMove();
    }

    @Override
    public int hashCode() {
        return this.cachedHashCode;
    }

    public Alliance getPieceAlliance() {
        return pieceAlliance;
    }

    public int getPieceValue() {
        return this.pieceType.getPieceValue();
    }

    public boolean isFirstMove() {
        return this.isFirstMove;
    }

    public abstract Collection<Move> calculateLegalMoves(final Board board);

    public abstract Piece movePiece(Move move);

    public int getPiecePosition() {
        return this.piecePosition;
    }

    public PieceType getPieceType() {
        return pieceType;
    }

    public enum PieceType {
        PAWN("P", 100) {
            @Override
            public boolean isKing() {
                return false;
            }
        },
        KNIGHT("N", 300) {
            @Override
            public boolean isKing() {
                return false;
            }
        },
        BISHOP("B", 300) {
            @Override
            public boolean isKing() {
                return false;
            }
        },
        ROOK("R", 500) {
            @Override
            public boolean isKing() {
                return false;
            }
        },
        QUEEN("Q", 900) {
            @Override
            public boolean isKing() {
                return false;
            }
        },
        KING("K", 10_000) {
            @Override
            public boolean isKing() {
                return true;
            }
        };

        private final String pieceName;
        private final int pieceValue;

        PieceType(final String pieceName, final int pieceValue) {
            this.pieceName = pieceName;
            this.pieceValue = pieceValue;
        }

        @Override
        public String toString() {
            return this.pieceName;
        }
        public abstract boolean isKing();
/*
        public boolean isKing() {
            return this == KING;
        }
         */

        public boolean isRook() {
            return this == ROOK;
        }

        public int getPieceValue() {
            return this.pieceValue;
        };
    }


}
