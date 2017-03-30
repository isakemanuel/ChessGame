package chess.piece;

import chess.move.Move;

/**
 * class Knight describes how bishop chess piece is behavior.
 * 
 * @author XJasonX
 * @since February 28, 2017
 */
@SuppressWarnings("serial")
public class Knight extends Piece {    
    /**
     * Constructor for objects of class Knight.
     */
    public Knight(final boolean black) {
        super(black);
        this.setImage('n');
    }

    /**
     * Checks valid move for Knight.
     */
    @Override
    public boolean validMove(Move move) {
        if (move.rowDifference() == 2 && move.colDifference() == 1) {
            return true;
        } else if (move.rowDifference() == 1 && move.colDifference() == 2) {
            return true;
        }
        return false;
    }
}
