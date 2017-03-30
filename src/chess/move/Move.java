package chess.move;

import chess.board.Square;

/**
 * Move class gets the move that the piece wants to go to.
 * 
 * @author XJasonX
 * @since February 28, 2017
 */
public class Move {
    private Square sourceTile;
    private Square targetTile;
    
    public Move(Square sourceTile, Square targetTile) {
        this.sourceTile = sourceTile;
        this.targetTile = targetTile;
    }
    
    /**
     * Gets source tile.
     * @return the sourceTile
     */
    public Square getSourceTile() {
        return sourceTile;
    }

    /**
     * Sets source Tile.
     * @param sourceTile the sourceTile to set
     */
    public void setSourceTile(Square sourceTile) {
        this.sourceTile = sourceTile;
    }

    /**
     * Gets target tile.
     * @return the targetTile
     */
    public Square getTargetTile() {
        return targetTile;
    }

    /**
     * Sets target tile.
     * @param targetTile the targetTile to set.
     */
    public void setTargetTile(Square targetTile) {
        this.targetTile = targetTile;
    }
    
    /**
     * Difference between source and target rows.
     * @return int difference of the row
     */
    public int rowDifference() {
        return Math.abs(sourceTile.getRow() - targetTile.getRow());
    }
    
    /**
     * difference between source and target column.
     * @return int difference of the column
     */
    public int colDifference() {
        return Math.abs(sourceTile.getCol() - targetTile.getCol());
    }

    /**
     * Converts the move to String.
     * @return String source tile -> target tile
     */
    @Override
    public String toString() {
        return "Source: " + sourceTile.colToString() + sourceTile.rowToString() 
             + " -> Target: " + targetTile.colToString() + targetTile.rowToString();
    }
}
