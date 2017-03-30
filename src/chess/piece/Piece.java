package chess.piece;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import chess.board.Square;
import chess.move.Move;

/**
 * Abstract class ChessPiece - piece class setups image icon for the chess piece
 * and sets color of that piece.
 * 
 * @author XJasonX
 * @since February 28, 2017
 */
@SuppressWarnings("serial")
public abstract class Piece extends JLabel {
    // total chess pieces are created
    private static int chessPiecesCount = 0;
    
    private final boolean black;
    private Square position;
    private ImageIcon icon;
    
    /**
     * Constructor for objects of class ChessPiece.
     */
    public Piece(final boolean black) {
        this.black = black;
        chessPiecesCount++;
    }
    
    /**
     * Sets the piece back to default.
     */
    public void setDefault() {
        // reset the piece
    }
    
    /**
     * Sets image icon for the piece.
     * @param type - 'w' or 'b' represent white and black
     */
    public void setImage(char type) {
        icon = new ImageIcon(
                this.getClass().getResource("/imagePieces/" + this.getColor() + type + ".png"));
        this.setIcon(icon);
        this.repaint();
    }
    
    /**
     * Checks chess piece valid move.
     */
    public abstract boolean validMove(Move move);
    
    /**
     * get the character of the color.
     * @return char 'b' or 'w'
     */
    public char getColor() {
        return this.isBlack() ? 'b' : 'w';
    }
    
    /**
     * Gets whether the piece is black or white.
     * @return boolean - is this piece black
     */
    public boolean isBlack() {
        return black;
    }
    
    /**
     * Gets whether this piece is alive or not.
     * @return boolean - is this piece alive
     */
    public boolean isAlive() {
        return position != null;
    }
    
    /**
     * Gets the position of this piece.
     * @return Square - position
     */
    public Square getPosition() {
        return position;
    }
    
    /**
     * Sets the position of this piece.
     * @param position - Square at row and column
     */
    public void setPosition(Square position) {
        this.position = position;
    }
        
    /**
     * get the total count of chess pieces.
     * @return chessPieceCount - total pieces
     */
    public static int getChessPieceCount() {
        return chessPiecesCount;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "Piece [black=" + black + ", position=" + position + ", icon=" + icon + "]";
    }
}
