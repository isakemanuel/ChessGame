package chess.piece;

import chess.move.Move;

/**
 * class Queen describes how bishop chess piece is behavior.
 * 
 * @author XJasonX
 * @since February 28, 2017
 */
@SuppressWarnings("serial")
public class Queen extends Piece {
    /**
     * Constructor for objects of class Queen.
     */
    public Queen(final boolean black) {
        super(black);
        this.setImage('q');
    }

    /**
     * Checks valid move for Queen.
     */
    @Override
    public boolean validMove(Move move) {
        return new Rook(this.isBlack()).validMove(move) 
                || new Bishop(this.isBlack()).validMove(move);
    }
}
