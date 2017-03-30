package chess.piece;

import chess.board.Board;
import chess.move.Move;

/**
 * class Pawn describes how bishop chess piece is behavior.
 * 
 * @author XJasonX
 * @since February 28, 2017
 */
@SuppressWarnings("serial")
public class Pawn extends Piece {

    private boolean firstMove;
    
    /**
     * Constructor for objects of class Pawn.
     */
    public Pawn(final boolean black) {
        super(black);
        this.setImage('p');
        this.firstMove = true;

    }

    /**
     * Checks valid move for Pawn.
     */
    @Override
    public boolean validMove(Move move) {
        int currentRow = move.getSourceTile().getRow();
        int currentCol = move.getSourceTile().getCol();
        int newRow = move.getTargetTile().getRow();
        int newCol = move.getTargetTile().getCol();

        if (this.isBlack()) {
            if (currentRow > newRow) {
                return false;
            }
        } else {
            if (currentRow < newRow) {
                return false;
            }
        }

        if (move.colDifference() == 1) {
            if (this.isBlack()) {
                if (!Board.getSquare(currentRow + 1, newCol).isOccupied()) {
                    return false;
                }
            } else {
                if (!Board.getSquare(currentRow - 1, newCol).isOccupied()) {
                    return false;
                }
            }
        }

        if (currentCol == newCol) {
            if (move.rowDifference() > 2) {
                return false;
            } else if (move.rowDifference() == 2) {
                return firstMove;
            }

            if (this.isBlack()) {
                if (Board.getSquare(currentRow + 1, currentCol).isOccupied()) {
                    return false;
                }
            } else {
                if (Board.getSquare(currentRow - 1, currentCol).isOccupied()) {
                    return false;
                }
            }
        } else {
            if (move.rowDifference() != 1 || move.colDifference() != 1) {
                return false;
            }
        }
        return true;
    }

    /**
     * Sets the pawns's first move to be true.
     */
    @Override
    public void setDefault() {
        firstMove = true;
    }

    /**
     * Gets if this pawn made first move or not.
     * 
     * @return the firstMove
     */
    public boolean isFirstMove() {
        return firstMove;
    }

    /**
     * Sets if this pawn can make the first move or not.
     * 
     * @param firstMove
     *            the firstMove to set
     */
    public void setFirstMove(boolean firstMove) {
        this.firstMove = firstMove;
    }

}
