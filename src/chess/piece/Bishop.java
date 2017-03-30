package chess.piece;

import chess.board.Board;
import chess.move.Move;

/**
 * class Bishop describes how bishop chess piece is behavior.
 * 
 * @author XJasonX
 * @since February 28, 2017
 */
@SuppressWarnings("serial")
public class Bishop extends Piece {

    /**
     * Constructor for objects of class Bishop.
     */
    public Bishop(final boolean black) {
        super(black);
        this.setImage('b');
    }

    /**
     * Checks valid move for Bishop.
     */
    @Override
    public boolean validMove(Move move) {
        final int currentRow = move.getSourceTile().getRow();
        final int currentCol = move.getSourceTile().getCol();
        final int newRow = move.getTargetTile().getRow();
        final int newCol = move.getTargetTile().getCol();
        
        if (move.rowDifference() != move.colDifference()) {
            return false;
        }
        
        int rowOffset = 0;
        int colOffset = 0;
        if (currentRow < newRow) {
            rowOffset = 1;
        } else {
            rowOffset = -1;
        }
        
        if (currentCol < newCol) {
            colOffset = 1;
        } else {
            colOffset = -1;
        }
        
        int col = currentCol + colOffset;
        for (int row = currentRow + rowOffset; row != newRow; row += rowOffset) {
            if (Board.getSquare(row, col).isOccupied()) {
                return false;
            }
            col += colOffset;
        }
        
        return true;
    }   
}
