/**
 * CIS 120 Game HW
 * (c) University of Pennsylvania
 * @version 2.0, Mar 2013
 */

// imports necessary libraries for Java swing
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * Game Main class that specifies the frame and widgets of the GUI
 */
public class Game implements Runnable {
	public void run() {
		// NOTE : recall that the 'final' keyword notes inmutability
		// even for local variables.

		// Top-level frame in which game components live
		// Be sure to change "TOP LEVEL FRAME" to the name of your game
		final JFrame frame = new JFrame("Connect 4");
		frame.setLocation(100, 100);

		// Status panel
		final JPanel status_panel = new JPanel();
		frame.add(status_panel, BorderLayout.SOUTH);
		final JLabel status = new JLabel("It's Player 1's turn! \t\t"
											+ " Player 1: " + Integer.toString(Board.rwins)
											+ "\t\t Player 2: " + Integer.toString(Board.ywins));
		status_panel.add(status);

		final Board board = new Board(status, frame);
		frame.add(board, BorderLayout.CENTER);
		
		// ToolBar
		final JButton b_restart = new JButton("Restart");
		b_restart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				board.restart();
			}
		});
		
		// Panel to contain buttons
		final JPanel control_panel = new JPanel();
		control_panel.setLayout(new GridLayout(1,3));
		control_panel.add(b_restart);
		
		frame.add(control_panel, BorderLayout.NORTH);

		// Put the frame on the screen
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
		// Start game
		board.restart();
		JOptionPane.showMessageDialog(frame, "Player 1 is red.\nPlayer 2 "
				+ "is yellow.\n\nUse the left and right arrow keys to pick "
				+ "which column\nto drop your peg in. Use the enter key "
				+ "to drop your\npeg into the board.\n\nYou win when 4 "
				+ "consecutive pegs of your color line\nup either "
				+ "vertically, horizontally, or diagonally.\n\nThere "
				+ "is a status bar at the bottom that shows whose\nturn "
				+ "it is as well as the number of wins for each player\n"
				+ "in consecutive plays.",
				"Instructions", JOptionPane.PLAIN_MESSAGE);
	}

	/*
	 * Main method run to start and run the game Initializes the GUI elements
	 * specified in Game and runs it IMPORTANT: Do NOT delete! You MUST include
	 * this in the final submission of your game.
	 */
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Game());
	}
}
