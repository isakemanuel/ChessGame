package chess.board;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JPanel;

import chess.piece.Piece;

/**
 * class Square sets basic color of square and size of the square.
 * checks to see if there is a piece on the square.
 * 
 * @author XJasonX
 * @since February 28, 2017
 */
@SuppressWarnings("serial")
public class Square extends JPanel {
    private final int row;
    private final int col;
    private final int length;
    private final Color originalColor;
    private Piece piece;
    
    /**
     * Constructor for objects of class Square.
     */
    public Square(final int length, final int row, final int col, final Color color) {
        this.piece = null;
        this.row = row;
        this.col = col;
        this.originalColor = color;
        this.length = length;
        createSquare();
    }
    
    /**
     * Sets and adds a chess piece in the square.
     * @param piece - the piece the wants to put on
     */
    public void setPiece(Piece piece) {
        if (piece != null) {
            this.piece = piece;
            piece.setPosition(this);
            this.add(piece);
            this.repaint();
        }
    }
    
    /**
     * Removes the chess piece within this square if this squares has one.
     */
    public void removePiece() {
        if (this.piece != null) {
            piece.setPosition(null);
            this.remove(piece);
            piece = null;
        }
    }
    
    /**
     * Gets the piece in this square.
     * @return Piece - the piece on this square 
     */
    public Piece getPiece() {
        return piece;
    }

    /**
     * Checks if this square has a piece on it or not.
     * @return boolean - true if there is a piece on this square; otherwise, false
     */
    public boolean isOccupied() {
        if (piece != null) {
            return true;
        }
        return false;
    }
    
    /**
     * Gets original color of this square.
     * @return Color - the original color of this square
     */
    public Color getOriginalColor() {
        return this.originalColor;
    }
    
    /**
     * Gets length of this square.
     * @return int - length of this square
     */
    public int getLength() {
        return this.length;
    }
    
    /**
     * Gets the row of this square.
     * @return int - row of this square
     */
    public int getRow() {
        return this.row;
    }
    
    /**
     * Gets the column of this square.
     * @return int - column of this square
     */
    public int getCol() {
        return this.col;
    }
    
    /**
     * Turns row number to String.
     * @return String - row number
     */
    public String rowToString() {
        return Integer.toString(row);
    }
    
    /**
     * Turns column number to from A to H.
     * @return String - column in String
     */
    public String colToString() {
        String strCol = "unknow";
        switch (col) {
          case 0:
              strCol = "A";
              break;
          case 1:
              strCol = "B";
              break;
          case 2:
              strCol = "C";
              break;
          case 3:
              strCol = "D";
              break;
          case 4:
              strCol = "E";
              break;
          case 5:
              strCol = "F";
              break;
          case 6:
              strCol = "G";
              break;
          case 7:
              strCol = "H";
              break;
          default:
                // should not be here
        }
        
        return strCol;
    }

    // create the square by calling the method in JPanel to set size and color.
    private void createSquare() {
        this.setBackground(this.originalColor);
        this.setPreferredSize(new Dimension(this.length, this.length));
    }
}
