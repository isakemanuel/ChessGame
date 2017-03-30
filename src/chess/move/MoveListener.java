package chess.move;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import chess.Game;
import chess.board.Square;
import chess.piece.Pawn;
import chess.piece.Piece;
import chess.piece.Player;

/**
 * MoveListener class listens from user mouse input.
 * 
 * @author XJasonX
 * @since February 28, 2017
 */
public class MoveListener extends MouseAdapter {

    // private static Piece selectedPiece = null;
    private Piece piece;

    /**
     * Clicks to select a piece and move to desired location.
     */
    @Override
    public void mouseClicked(MouseEvent event) {
        // gets the player by turn
        String playerTurn = "";
        Player player = Game.getPlayerTurn();
        if (Game.getTurn()) {
            playerTurn = "player 1's turn";
        } else {
            playerTurn = "player 2's turn";
        }

        System.out.println(playerTurn);

        Square pressedSquare = (Square) event.getSource();
        System.out.println("ROW: " + pressedSquare.getRow() + ", COL: " 
                                   + pressedSquare.getCol() + " is clicked");

        if (pressedSquare.isOccupied() && piece == null) {
            // try to select a piece
            if (pressedSquare.getPiece().getColor() == player.getColor()) {
                piece = getSelectedPiece(pressedSquare);
                System.out.println("A piece is selected");
            } else {
                // Warning message: picks another player's piece
                System.err.println("Error: it is " + playerTurn);
                Game.displayMsg("Error: it is " + playerTurn);
                /*
                JOptionPane.showMessageDialog(null, playerTurn, 
                                              "Warning: Piece", JOptionPane.WARNING_MESSAGE);
                */
            }
        } else if (pressedSquare.isOccupied() && piece != null) {
            // try to select another piece
            Square previousSquare = piece.getPosition();
            if (pressedSquare.getPiece().getColor() == player.getColor()) {
                // select a different piece with same color
                System.out.println("Select another piece");
                previousSquare.setBackground(previousSquare.getOriginalColor());
                piece = getSelectedPiece(pressedSquare);
            } else {
                // move to eat opponent's piece
                if (piece.validMove(new Move(previousSquare, pressedSquare))) {
                    System.out.println("Move to eat");
                    pressedSquare.removePiece();
                    previousSquare.setBackground(previousSquare.getOriginalColor());
                    movePiece(previousSquare, pressedSquare);
                    Game.setTurn(!Game.getTurn());
                    System.out.println(Game.getTurn());
                    piece = null;
                    Game.setSaved(false);
                }
            }
        } else if (!pressedSquare.isOccupied() && piece != null) {
            // move a piece to an empty square
            Square previousSquare = piece.getPosition();
            if (piece.validMove(new Move(previousSquare, pressedSquare))) {
                System.out.println("move to that square");
                // rule that deals with Pawn
                if (piece instanceof Pawn) {
                    ((Pawn) piece).setFirstMove(false);
                }
                previousSquare.setBackground(previousSquare.getOriginalColor());
                movePiece(previousSquare, pressedSquare);
                Game.setTurn(!Game.getTurn());
                piece = null;
                Game.setSaved(false);
            }
        }
        
        System.out.println("------------------------------");   // line separator
    }

    /**
     * Gets a selected piece.
     * 
     * @param square
     *            - square is selected
     * @return Piece - selected piece
     */
    private Piece getSelectedPiece(Square square) {
        Piece selectedPiece = square.getPiece();
        if (selectedPiece != null) {
            if (selectedPiece != piece) {
                square.setBackground(Color.YELLOW);
            } else {
                selectedPiece = null;
            }

        } else if (selectedPiece == piece) {
            square.setBackground(square.getOriginalColor());
            selectedPiece = null;
        }
        return selectedPiece;
    }

    /**
     * Moves a piece to the location.
     * 
     * @param source
     *            - original square that the piece was on
     * @param destination
     *            - desired square that the piece wants to move to
     */
    private void movePiece(Square source, Square destination) {
        source.removePiece();
        destination.setPiece(piece);
        source.repaint();
        destination.repaint();
    }

    /**
     * Gets selected Piece.
     * @return the piece
     */
    public Piece getPiece() {
        return piece;
    }

    /**
     * Sets selected Piece.
     * @param piece the piece to set
     */
    public void setPiece(Piece piece) {
        this.piece = piece;
    }
}
