package chess.board;

import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.JPanel;

import chess.Game;
import chess.move.MoveListener;
import chess.piece.Piece;

/**
 * class Board initializes the board and adds move listener to all 64 squares.
 * 
 * @author XJasonX
 * @since February 28, 2017
 */
@SuppressWarnings("serial")
public class Board extends JPanel {
    /**
     * number of squares in on row.
     */
    public static final int NUMBER_OF_SQUARES = 8;

    private transient MoveListener moveListener = new MoveListener();
    private static Square[][] squares = new Square[8][8];

    /**
     * Constructor for Board Adds two players.
     */
    public Board() {
        this.setSize(700, 700);
        this.createSquares();
        this.boardSetup();
    }

    /**
     * Setups the board and places piece in the default location.
     */
    public void boardSetup() {
        this.clearBoard();
        this.initializeBoard();
        System.out.println("Total Piece is created: " + Piece.getChessPieceCount());
        this.refreshBoard();
    }

    /**
     * Resumes the board.
     */
    public void resumeBoard() {
        this.clearBoard();
        for (Piece piece : Game.getPlayer1().getPieces().values()) {
            if (piece.getPosition() != null) {
                System.out.println("Player1's piece");
                Square coordinate = piece.getPosition();
                squares[coordinate.getRow()][coordinate.getCol()].setPiece(piece);
            }
        }

        for (Piece piece : Game.getPlayer2().getPieces().values()) {
            if (piece.getPosition() != null) {
                Square coordinate = piece.getPosition();
                squares[coordinate.getRow()][coordinate.getCol()].setPiece(piece);
            }
        }
        this.refreshBoard();
    }

    /**
     * refresh the board.
     */
    public void refreshBoard() {
        this.repaint();
        for (int row = 0; row < NUMBER_OF_SQUARES; row++) {
            for (int column = 0; column < NUMBER_OF_SQUARES; column++) {
                squares[row][column].repaint();
            }
        }
    }

    // creates 64 squares on the board
    private void createSquares() {
        setLayout(new GridLayout(NUMBER_OF_SQUARES, NUMBER_OF_SQUARES));
        for (int row = 0; row < NUMBER_OF_SQUARES; row++) {
            for (int column = 0; column < NUMBER_OF_SQUARES; column++) {
                if ((row + column + 1) % 2 == 0) {
                    squares[row][column] = new Square(80, row, column, Color.GRAY);
                } else {
                    squares[row][column] = new Square(80, row, column, Color.LIGHT_GRAY);
                }
                squares[row][column].addMouseListener(moveListener);
                this.add(squares[row][column]);
            }
        }
    }

    // Puts all the pieces in the Board.
    private void initializeBoard() {
        // initialize pieces for player1
        squares[7][0].setPiece(Game.getPlayer1().getPiece("r01")); // first white rook
        squares[7][7].setPiece(Game.getPlayer1().getPiece("r02")); // second white rook

        squares[7][1].setPiece(Game.getPlayer1().getPiece("n01")); // first white knight
        squares[7][6].setPiece(Game.getPlayer1().getPiece("n02")); // second white knight

        squares[7][2].setPiece(Game.getPlayer1().getPiece("b01")); // first white bishop
        squares[7][5].setPiece(Game.getPlayer1().getPiece("b02")); // second white bishop

        squares[7][3].setPiece(Game.getPlayer1().getPiece("k01")); // white king
        squares[7][4].setPiece(Game.getPlayer1().getPiece("q01")); // white queen

        for (int col = 0; col < NUMBER_OF_SQUARES; col++) {
            // places white pawn
            squares[6][col].setPiece(Game.getPlayer1().getPiece("p0" + (col + 1)));
        }

        // initialize pieces for player2
        squares[0][0].setPiece(Game.getPlayer2().getPiece("r01")); // first black rook
        squares[0][7].setPiece(Game.getPlayer2().getPiece("r02")); // second black rook

        squares[0][1].setPiece(Game.getPlayer2().getPiece("n01")); // first black knight
        squares[0][6].setPiece(Game.getPlayer2().getPiece("n02")); // second black knight

        squares[0][2].setPiece(Game.getPlayer2().getPiece("b01")); // first black bishop
        squares[0][5].setPiece(Game.getPlayer2().getPiece("b02")); // second black bishop

        squares[0][3].setPiece(Game.getPlayer2().getPiece("k01")); // black king
        squares[0][4].setPiece(Game.getPlayer2().getPiece("q01")); // black queen

        for (int col = 0; col < NUMBER_OF_SQUARES; col++) {
            // places black pawn
            squares[1][col].setPiece(Game.getPlayer2().getPiece("p0" + (col + 1)));
        }
    }

    // clear all pieces on the Board
    private void clearBoard() {
        if (moveListener.getPiece() != null) {
            Square square = moveListener.getPiece().getPosition();
            square.setBackground(square.getOriginalColor());
            moveListener.setPiece(null);
        }

        for (int row = 0; row < NUMBER_OF_SQUARES; row++) {
            for (int column = 0; column < NUMBER_OF_SQUARES; column++) {
                if (squares[row][column].getPiece() != null) {
                    squares[row][column].removePiece();
                }
            }
        }
    }

    /**
     * Gets the square at row and column.
     * 
     * @param row
     *            - row of the square
     * @param col
     *            - column of the square
     * @return Square - square position
     */
    public static Square getSquare(int row, int col) {
        return squares[row][col];
    }

    public static Square[][] getSquares() {
        return squares;
    }

    /**
     * Checks if it is check mated or not.
     * 
     * @return boolean - win condition
     */
    public boolean checkMate() {
        return false;
    }
}
