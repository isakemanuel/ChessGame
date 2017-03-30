package chess.piece;

import chess.board.Board;
import chess.move.Move;

/**
 * class King describes how bishop chess piece is behavior.
 * 
 * @author XJasonX
 * @since February 28, 2017
 */
@SuppressWarnings("serial")
public class King extends Piece {
    private boolean castled;

    /**
     * Constructor for objects of class King.
     */
    public King(final boolean black) {
        super(black);
        this.setImage('k');
        castled = false;
    }

    /**
     * Checks valid move for King.
     */
    @Override
    public boolean validMove(Move move) {
        final int currentRow = move.getSourceTile().getRow();
        final int currentCol = move.getSourceTile().getCol();
        final int newRow = move.getTargetTile().getRow();

        if (move.rowDifference() > 1 || move.colDifference() > 1) {
            if (move.colDifference() == 2 && currentRow == newRow) {
                if (Board.getSquare(newRow, currentCol + 1).isOccupied()
                        || Board.getSquare(newRow, currentCol + 2).isOccupied()) {
                    castled = false;
                    return false;
                }
            } else if (move.colDifference() == 3 && currentRow == newRow) {
                if (Board.getSquare(newRow, currentCol - 1).isOccupied()
                        || Board.getSquare(newRow, currentCol - 2).isOccupied()
                        || Board.getSquare(newRow, currentCol - 3).isOccupied()) {
                    castled = false;
                    return false;
                }
            } else {
                castled = false;
                return false;
            }
            castled = true;
        }
        return true;
    }
    
    /**
     * Sets castled to be false.
     */
    @Override
    public void setDefault() {
        castled = false;
    }

    /**
     * Gets if the king can castle or not.
     * @return the castled
     */
    public boolean isCastled() {
        return castled;
    }

    /**
     * Sets if the king can castle or not.
     * @param castled the castled to set
     */
    public void setCastled(boolean castled) {
        this.castled = castled;
    }
}
