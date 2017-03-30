package chess.piece;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * class Player assigns new player with a name, 
 * pick a color between black or white, 
 * and initialize all player's pieces.
 * 
 * @author XJasonX
 * @since February 28, 2017
 */
public class Player implements Serializable {
    // player serial ID
    private static final long serialVersionUID = 3790112742722696919L;
    // instance variables - replace the example below with your own
    private String name;
    private boolean black;
    private Map<String, Piece> pieces = new HashMap<String, Piece>();

    /**
     * Constructor for objects of class Player.
     */
    public Player(boolean black) {
        this.black = black;
        initializePieces();
    }
    
    // Creates and initializes the player's pieces
    private void initializePieces() {
        pieces.put("p01", new Pawn(this.isBlack()));
        pieces.put("p02", new Pawn(this.isBlack()));
        pieces.put("p03", new Pawn(this.isBlack()));
        pieces.put("p04", new Pawn(this.isBlack()));
        pieces.put("p05", new Pawn(this.isBlack()));
        pieces.put("p06", new Pawn(this.isBlack()));
        pieces.put("p07", new Pawn(this.isBlack()));
        pieces.put("p08", new Pawn(this.isBlack()));
        
        pieces.put("k01", new King(this.isBlack()));
        pieces.put("q01", new Queen(this.isBlack()));
        
        pieces.put("r01", new Rook(this.isBlack()));
        pieces.put("r02", new Rook(this.isBlack()));
        
        pieces.put("n01", new Knight(this.isBlack()));
        pieces.put("n02", new Knight(this.isBlack()));
        
        pieces.put("b01", new Bishop(this.isBlack()));
        pieces.put("b02", new Bishop(this.isBlack()));
    }
    
    /**
     * Resets all pieces to their default values.
     */
    public void resetPieces() {
        for (String id : pieces.keySet()) {
            pieces.get(id).setDefault();
            
            switch (id.charAt(0)) {
              case 'p':
                  //((Pawn) pieces.get(id)).setFirstMove(true);
                  System.out.println(((Pawn) pieces.get(id)).isFirstMove());
                  break;
              case 'k':
                  //((King) pieces.get(id)).setCastled(false);
                  break;
              default:
                    // should not be here
            }
        }
    }
    
    /**
     * Gets a collection of pieces from this player.
     * @return Map - a collection of pieces
     */
    public Map<String, Piece> getPieces() {
        return pieces;
    }
    
    /**
     * Gets a piece in this player's collection.
     * @param id - id of that piece
     * @return Piece - a piece that matches the id
     */
    public Piece getPiece(String id) {
        return pieces.get(id);
    }

    /**
     * Sets the name of this player.
     * @param name - name of this player
     */
    public void setName(String name) {
        this.name = name;
    }
    
    /**
     * Gets the name of this player.
     * @return String - name of this player
     */
    public String getName() {
        return name;
    }
    
    /**
     * Gets whether the player picks black or white.
     * @return boolean - picks black or white pieces
     */
    public boolean isBlack() {
        return black;
    }
    
    /**
     * get the character of the color.
     * @return char 'b' or 'w'
     */
    public char getColor() {
        return this.isBlack() ? 'b' : 'w';
    }
}
