package chess.piece;

import chess.board.Board;
import chess.move.Move;

/**
 * class Rook describes how bishop chess piece is behavior.
 * 
 * @author XJasonX
 * @since February 28, 2017
 */
@SuppressWarnings("serial")
public class Rook extends Piece {

    /**
     * Constructor for objects of class Rook.
     */
    public Rook(final boolean black) {
        super(black);
        this.setImage('r');
    }

    /**
     * Checks valid move for Rook.
     */
    @Override
    public boolean validMove(Move move) {
        final int currentRow = move.getSourceTile().getRow();
        final int currentCol = move.getSourceTile().getCol();
        final int newRow = move.getTargetTile().getRow();
        final int newCol = move.getTargetTile().getCol();

        if (move.rowDifference() != 0 && move.colDifference() != 0) {
            return false;
        }
        int offset = 0;
        if (currentRow != newRow) {
            if (currentRow < newRow) {
                offset = 1;
            } else {
                offset = -1;
            }

            for (int row = currentRow + offset; row != newRow; row += offset) {
                if (Board.getSquare(row, currentCol).isOccupied()) {
                    return false;
                }
            }
        }
        if (currentCol != newCol) {
            if (currentCol < newCol) {
                offset = 1;
            } else {
                offset = -1;
            }

            for (int col = currentCol + offset; col != newCol; col += offset) {
                if (Board.getSquare(currentRow, col).isOccupied()) {
                    System.out.println("col: " + col);
                    return false;
                }
            }
        }
        return true;
    }
}
