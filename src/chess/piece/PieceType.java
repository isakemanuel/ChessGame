package chess.piece;

/**
 * Determines type of piece.
 * 
 * @author XJasonX
 * @since February 28, 2017
 */
public enum PieceType {
    BISHOP('b'), PAWN('p'), QUEEN('q'), KING('k'), KNIGHT('n'), ROOK('r');
    
    private final char type;
    
    // construct PieceType with char type
    private PieceType(char type) {
        this.type = type;
    }
    
    /**
     * Get type of the piece.
     * @return char piece type
     */
    public char getType() {
        return type;
    }
}
