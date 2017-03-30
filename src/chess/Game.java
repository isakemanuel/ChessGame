package chess;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.filechooser.FileNameExtensionFilter;

import chess.board.Board;
import chess.piece.Player;

/**
 * class Game is a JFrame which adds board and two players.
 * 
 * @author XJasonX
 * @version 1.0
 * @since February 28, 2017
 */
@SuppressWarnings("serial")
public class Game extends JFrame {
    private static Player player1;
    private static Player player2;
    private static boolean turn = true;
    private static boolean saved = true;

    private final Board board;
    private final JMenuBar menuBar = new JMenuBar();
    private final JTextArea chatBox = new JTextArea();
    private final JTextField msgBox = new JTextField();
    private final JButton sendBtn = new JButton("Send");
    private static Messenger msger;

    /**
     * Constructor for objects of class Game.
     */
    public Game() {
        super("Chess Game");
        this.setSize(1000, 720);
        this.setResizable(false);
        player1 = new Player(false);
        player2 = new Player(true);
        board = new Board();
        this.setMenuBar();
        this.add(board);
        chatBox.setPreferredSize(new Dimension(320, 600));
        chatBox.setEditable(false);
        this.add(chatBox);
        msgBox.setPreferredSize(new Dimension(250, 30));
        this.add(msgBox);
        sendBtn.setPreferredSize(new Dimension(65, 30));
        this.add(sendBtn);
        msger = new Messenger();
        this.sendMsg();
        this.frameLayout();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
    
    // set frame layout
    private void frameLayout() {
        SpringLayout layout = new SpringLayout();
        this.setLayout(layout);
        layout.putConstraint(SpringLayout.WEST, board, 10, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, board, 10, SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.NORTH, chatBox, 10, SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.WEST, chatBox, 10, SpringLayout.EAST, board);
        layout.putConstraint(SpringLayout.WEST, msgBox, 10, SpringLayout.EAST, board);
        layout.putConstraint(SpringLayout.NORTH, msgBox, 5, SpringLayout.SOUTH, chatBox);
        layout.putConstraint(SpringLayout.WEST, sendBtn, 5, SpringLayout.EAST, msgBox);
        layout.putConstraint(SpringLayout.NORTH, sendBtn, 5, SpringLayout.SOUTH, chatBox);

    }
    
    // send the message of msgBox to chatBox
    private void sendMsg() {
        sendBtn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent event) {
                String msg = msgBox.getText();
                if (!msg.equals("")) {
                    displayMsg(msg);
                    msgBox.setText("");
                }
            }
            
        });
    }
    
    /**
     * display message to textArea.
     * @param msg message string
     */
    public static void displayMsg(String msg) {
        msger.displayMessage(msg);
    }

    // Creates Menu bar on the frame
    private void setMenuBar() {
        // File Menu, F - Mnemonic
        final JMenu gameMenu = new JMenu("Game");
        gameMenu.setMnemonic(KeyEvent.VK_F);
        menuBar.add(gameMenu);

        // File -> New, N - Mnemonic
        final JMenuItem newMenuItem = new JMenuItem("New Game", KeyEvent.VK_N);
        newMenuItem.addActionListener(new ActionListener() {
            // restart a new game
            @Override
            public void actionPerformed(ActionEvent event) {
                restart();
            }
        });

        // File -> Save, S - Mnemonic
        final JMenuItem saveMenuItem = new JMenuItem("Save", KeyEvent.VK_S);
        saveMenuItem.addActionListener(new ActionListener() {
            // saves the current board
            @Override
            public void actionPerformed(ActionEvent event) {
                save();
            }
        });

        // File -> Load, L - Mnemonic
        final JMenuItem loadMenuItem = new JMenuItem("Open", KeyEvent.VK_O);
        loadMenuItem.addActionListener(new ActionListener() {
            // loads the board
            @Override
            public void actionPerformed(ActionEvent event) {
                open();
            }
        });

        // File -> Exit
        final JMenuItem exitMenuItem = new JMenuItem("Exit");
        exitMenuItem.addActionListener(new ActionListener() {
            // exit the game
            @Override
            public void actionPerformed(ActionEvent event) {
                exit();
            }

        });

        gameMenu.add(newMenuItem);
        gameMenu.addSeparator();
        gameMenu.add(saveMenuItem);
        gameMenu.add(loadMenuItem);
        gameMenu.addSeparator();
        gameMenu.add(exitMenuItem);
        this.setJMenuBar(menuBar);
    }

    /**
     * Restarts the game.
     */
    public void restart() {
        player1.resetPieces();
        player2.resetPieces();
        turn = true;
        board.boardSetup();
        displayMsg("Board is reset");
    }

    /**
     * Saves the current game.
     */
    public void save() {
        System.out.println("Saving...");
        displayMsg("Saving...");
        String saveFilePath = getFilePath(false);
        System.out.println(saveFilePath);
        if (saveFilePath.equals("")) {
            displayMsg("Discard saving");
            return;
        }
        if (!saveFilePath.toLowerCase().endsWith(".gam")) {
            saveFilePath += ".gam";
        }
        try {
            FileOutputStream outputStream = new FileOutputStream(saveFilePath);
            ObjectOutputStream out = new ObjectOutputStream(outputStream);
            out.writeObject(player1);
            out.writeObject(player2);
            out.writeBoolean(turn);
            out.close();
            outputStream.close();
            saved = true;
            System.out.println("Game is saved");
            displayMsg("Game is saved");
        } catch (FileNotFoundException exception) {
            System.err.println(exception.getMessage());
        } catch (IOException exception) {
            System.err.println(exception.getMessage());
        }

    }

    /**
     * Loads and opens the game.
     */
    public void open() {
        System.out.println("Loading...");
        displayMsg("Loading...");
        String openFilePath = getFilePath(true);
        if (!openFilePath.toLowerCase().endsWith(".gam")) {
            displayMsg("Open failed");
            return;
        }
        System.out.println(openFilePath);

        try {
            FileInputStream inputStream = new FileInputStream(openFilePath);
            ObjectInputStream in = new ObjectInputStream(inputStream);
            player1 = (Player) in.readObject();
            player2 = (Player) in.readObject();
            turn = in.readBoolean();
            board.resumeBoard();
            this.repaint();
            in.close();
            inputStream.close();
            displayMsg("Game Loaded");
        } catch (FileNotFoundException exception) {
            System.err.println(exception.getMessage());
        } catch (IOException exception) {
            System.err.println(exception.getMessage());
        } catch (ClassNotFoundException exception) {
            System.err.println(exception.getMessage());
        }
    }

    // gets the file path from file chooser
    private String getFilePath(boolean open) {
        String filePath = "";
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.dir")));
        fileChooser.setFileFilter(new FileNameExtensionFilter("*.gam", "gam"));
        if (open) {
            fileChooser.setDialogTitle("Open File");
            int userSelection = fileChooser.showOpenDialog(this);
            if (userSelection == JFileChooser.APPROVE_OPTION) {
                filePath = fileChooser.getSelectedFile().getAbsolutePath();
            } else if (userSelection == JFileChooser.CANCEL_OPTION) {
                return filePath;
            }

        } else {
            fileChooser.setDialogTitle("Save File");
            int userSelection = fileChooser.showSaveDialog(this);
            if (userSelection == JFileChooser.APPROVE_OPTION) {
                filePath = fileChooser.getSelectedFile().getAbsolutePath();
            }
        }

        System.out.println(filePath);
        return filePath;
    }

    /**
     * exits the game.
     */
    public void exit() {
        if (!saved) {
            int option = JOptionPane.showOptionDialog(this, "Would you like to save the game?", 
                    "Saving Game", JOptionPane.YES_NO_CANCEL_OPTION, 
                    JOptionPane.QUESTION_MESSAGE, null, null, null);
            switch (option) {
              case 0:
                  // save the game
                  save();
                  System.exit(0);
                  break;
              case 1:
                  // close the program without saving
                  System.exit(0);
                  break;
              case 2:
                  // cancel the dialog
                  break;
              default:
                  // should not be here
            }
            System.out.println(option);
        } else  {
            System.exit(0);
        }
    }

    /**
     * Gets Player's turn.
     * 
     * @return Player - player1 or player2
     */
    public static Player getPlayerTurn() {
        return turn ? player1 : player2;
    }

    /**
     * true for player 1 turn or false for player 2 turn.
     * 
     * @param switchTurn
     *            - switch turns
     */
    public static void setTurn(boolean switchTurn) {
        turn = switchTurn;
    }

    /**
     * returns turn.
     * 
     * @return boolean - turn of players
     */
    public static boolean getTurn() {
        return turn;
    }

    /**
     * Gets Player 1 from the game.
     * 
     * @return Player the player1
     */
    public static Player getPlayer1() {
        return player1;
    }

    /**
     * Sets Player 1.
     * 
     * @param player1
     *            the player1 to set
     */
    public static void setPlayer1(Player player1) {
        Game.player1 = player1;
    }

    /**
     * Gets Player 2 from the game.
     * 
     * @return Player the player2
     */
    public static Player getPlayer2() {
        return player2;
    }

    /**
     * Sets Player 2.
     * 
     * @param player2
     *            the player2 to set
     */
    public static void setPlayer2(Player player2) {
        Game.player2 = player2;
    }

    /**
     * Gets the game is saved or not.
     * 
     * @return the saved
     */
    public static boolean isSaved() {
        return saved;
    }

    /**
     * Sets the game is saved of not.
     * 
     * @param saved
     *            the saved to set
     */
    public static void setSaved(boolean saved) {
        Game.saved = saved;
    }

    /**
     * Main method to run the program.
     * 
     * @param args
     *            - arguments that is passed in
     */
    public static void main(String[] args) {
        new Game();
    }
    
    // Messenger class is to display message at the chat box
    private class Messenger {
        /**
         * display message to textArea.
         * @param msg message string
         */
        public void displayMessage(String msg) {
            chatBox.append(msg + "\n");
        }
    }

}
