import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.*;

@SuppressWarnings("serial")
public class Board extends JPanel{
	private Marker marker;
	
	// 2-D array representing the contents of each cell
	private Cell[][] board_contents;
	
	public boolean playing = false;
	private Cell curr_player;
	private int curr_row;
	private int curr_col;
	private JLabel status;
	private JFrame frame;
	private Score score;
	
	public static int rwins;
	public static int ywins;
	
	// Board constant dimensions
	public static final int NUM_COLS = 7;
	public static final int NUM_ROWS = 6;
	public static final int BOARD_SIZE = 700;
	public static final int CELL_SIZE = 100;
	public static final int GRID_WIDTH = 8;
	
	public Board(JLabel status, JFrame frame) {
		setBorder(BorderFactory.createLineBorder(Color.BLUE));
		
		setFocusable(true);
		
		try { score = new Score(); }
		catch (Exception ex) { }
		
		addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if (playing) {
					if (e.getKeyCode() == KeyEvent.VK_LEFT) {
						if (marker.index == 0) { }
						else {
							marker.index -= 1;
							marker.pos_x -= 100;
							repaint();
						}
					}
					else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
						if (marker.index == 6) { }
						else {
							marker.index += 1;
							marker.pos_x += 100;
							repaint();
						}
					}
					else if (e.getKeyCode() == KeyEvent.VK_ENTER) {
						// move game piece into position
						curr_col = marker.index;
						for (int i = NUM_ROWS - 1; i >= 0; i--) {
							if (board_contents[i][curr_col] == Cell.EMPTY) {
								curr_row = i;
								if (curr_player == Cell.RED) {
									board_contents[curr_row][curr_col] = Cell.RED;
									repaint();
									if (isVictory(board_contents, curr_player, curr_row, curr_col)) {
										playing = false;
										rwins++;
										status.setText("Player 1 wins! \t\t"
												+ " Player 1: " + Integer.toString(rwins)
												+ "\t\t Player 2: " + Integer.toString(ywins));
										
										try {
											score.addScore(frame, board_contents, curr_player);
											score.writeScores();
										}
										catch (Exception ex) { }
										
										break;
									}
									// Checks for tie, high score
									else if (isTie(board_contents)) {
										playing = false;
										status.setText("It's a tie! \t\t"
												+ " Player 1: " + Integer.toString(rwins)
												+ "\t\t Player 2: " + Integer.toString(ywins));
										try {
											score.displayScores(frame);
										}
										catch (Exception ex) { }
										break;
									}
									
									//update(curr_player, board_contents, curr_row, curr_col);
									curr_player = Cell.YELLOW;
									status.setText("It's Player 2's turn! \t\t"
												+ " Player 1: " + Integer.toString(rwins)
												+ "\t Player 2: " + Integer.toString(ywins));
									break;
								}
								else {
									board_contents[curr_row][curr_col] = Cell.YELLOW;
									repaint();
									if (isVictory(board_contents, curr_player, curr_row, curr_col)) {
										playing = false;
										ywins++;
										status.setText("Player 2 wins! \t\t"
												+ " Player 1: " + Integer.toString(rwins)
												+ "\t\t Player 2: " + Integer.toString(ywins));
										try {
											score.addScore(frame, board_contents, curr_player);
											score.writeScores();
										}
										catch (Exception ex) { }
										break;
									}
									// Checks for tie, high score
									else if (isTie(board_contents)) {
										playing = false;
										status.setText("It's a tie! \t\t"
												+ " Player 1: " + Integer.toString(rwins)
												+ "\t\t Player 2: " + Integer.toString(ywins));
										try {
											score.displayScores(frame);
										}
										catch (Exception ex) { }
										break;
									}
									//update(curr_player, board_contents, curr_row, curr_col);
									curr_player = Cell.RED;
									status.setText("It's Player 1's turn! \t\t"
												+ " Player 1: " + Integer.toString(rwins)
												+ "\t\t Player 2: " + Integer.toString(ywins));
									
									break;
								}
								
							}
						}
						
					}
					
				}
			}
		});
		
		this.status = status;
	}
	
	/**
	 * (Re-)set the game to its initial state.
	 */
	public void restart() {

		marker = new Marker();
		board_contents = new Cell[NUM_ROWS][NUM_COLS];
		for (int row = 0; row < NUM_ROWS; row++) {
			for (int col = 0; col < NUM_COLS; col++) {
				board_contents[row][col] = Cell.EMPTY;
			}
		}
		
		repaint();

		playing = true;
		curr_player = Cell.RED;
		status.setText("It's Player 1's turn! \t\t Player 1: " + 
						Integer.toString(rwins) + "\t\t Player 2: " + Integer.toString(ywins));

		// Make sure that this component has the keyboard focus
		requestFocusInWindow();
	}
	
	/**
	 * Checks for victory from current peg's position
	 * In 8 directions: 2 vertical, 2 horizontal, and 4 diagonal
	 */
	public static boolean isVictory(Cell[][] board_contents, Cell curr_player, int curr_row, int curr_col) {		
		// vertical down
		if (curr_row + 3 < 6) {
			if (board_contents[curr_row + 1][curr_col] == curr_player &&
					board_contents[curr_row + 2][curr_col] == curr_player &&
					board_contents[curr_row + 3][curr_col] == curr_player) { 
				return true; 
			}
		}
		
		// check entire row
		int count = 0;
		for (int col = 0; col < NUM_COLS; col++) {
			if (board_contents[curr_row][col] == curr_player) {
				count++;
				if (count == 4) { return true; }
			}
			else {
				count = 0;
			}
		}
		
		// check up-left to down-right diagonals
		// first diagonal
		if (curr_row - 3 >= 0 && curr_col - 3 >= 0) {
			if (board_contents[curr_row - 1][curr_col - 1] == curr_player &&
					board_contents[curr_row - 2][curr_col - 2] == curr_player &&
					board_contents[curr_row - 3][curr_col - 3] == curr_player) { 
				return true; 
			}
		}
		// second diagonal
		if (curr_row - 2 >= 0 && curr_col - 2 >= 0 && curr_row + 1 < 6 && curr_col + 1 < 7) {
			if (board_contents[curr_row + 1][curr_col + 1] == curr_player &&
					board_contents[curr_row - 1][curr_col - 1] == curr_player &&
					board_contents[curr_row - 2][curr_col - 2] == curr_player) { 
				return true; 
			}
		}
		// third diagonal
		if (curr_row - 1 >= 0 && curr_col - 1 >= 0 && curr_row + 2 < 6 && curr_col + 2 < 7) {
			if (board_contents[curr_row + 2][curr_col + 2] == curr_player &&
					board_contents[curr_row + 1][curr_col + 1] == curr_player &&
					board_contents[curr_row - 1][curr_col - 1] == curr_player) { 
				return true; 
					}
				}
		// fourth diagonal
		if (curr_row + 3 < 6 && curr_col + 3 < 7) {
			if (board_contents[curr_row + 1][curr_col + 1] == curr_player &&
					board_contents[curr_row + 2][curr_col + 2] == curr_player &&
					board_contents[curr_row + 3][curr_col + 3] == curr_player) { 
				return true; 
			}
		}
		
		
		// check down-left to up-right diagonals
		// first diagonal
		if (curr_row + 3 < 6 && curr_col - 3 >= 0) {
			if (board_contents[curr_row + 1][curr_col - 1] == curr_player &&
					board_contents[curr_row + 2][curr_col - 2] == curr_player &&
					board_contents[curr_row + 3][curr_col - 3] == curr_player) { 
				return true; 
			}
		}	
		// second diagonal
		if (curr_row - 1 >= 0 && curr_col - 2 >= 0 && curr_row + 2 < 6 && curr_col + 1 < 7) {
			if (board_contents[curr_row - 1][curr_col + 1] == curr_player &&
					board_contents[curr_row + 1][curr_col - 1] == curr_player &&
					board_contents[curr_row + 2][curr_col - 2] == curr_player) { 
				return true; 
			}
		}		
		// third diagonal
		if (curr_row - 2 >= 0 && curr_col - 1 >= 0 && curr_row + 1 < 6 && curr_col + 2 < 7) {
			if (board_contents[curr_row - 2][curr_col + 2] == curr_player &&
					board_contents[curr_row - 1][curr_col + 1] == curr_player &&
					board_contents[curr_row + 1][curr_col - 1] == curr_player) { 
				return true; 
			}
		}		
		// fourth diagonal
		if (curr_row - 3 >= 0 && curr_col + 3 < 7) {
			if (board_contents[curr_row - 1][curr_col + 1] == curr_player &&
					board_contents[curr_row - 2][curr_col + 2] == curr_player &&
					board_contents[curr_row - 3][curr_col + 3] == curr_player) { 
				return true; 
			}
		}	
		
		// no victories
		return false;
	}
	
	/**
	 * Checks for a tie
	 * (If there are no empty cells left)
	 */
	public static boolean isTie(Cell[][] board_contents) {
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 7; j++) {
				if (board_contents[i][j] == Cell.EMPTY) { return false; }
			}
		}
		return true;
	}
	
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		setBackground(Color.LIGHT_GRAY);
		
		// draw marker
		marker.draw(g);
		
		// draw gridlines
		g.setColor(Color.BLUE);
		for (int row = 0; row < NUM_ROWS; row++) {
			g.fillRoundRect(0, CELL_SIZE * row - GRID_WIDTH/2 + 100, 
					BOARD_SIZE-1, GRID_WIDTH, GRID_WIDTH, GRID_WIDTH);
		}
		for (int col = 0; col < NUM_COLS; col++) {
			g.fillRoundRect(CELL_SIZE * col - GRID_WIDTH/2, 100, GRID_WIDTH, 
					BOARD_SIZE - 101, GRID_WIDTH, GRID_WIDTH);
		}
		
		// iterate through board and draw red/yellow pieces as needed
		for (int row = 0; row < NUM_ROWS; row++) {
			for (int col = 0; col < NUM_COLS; col++) {
				int x = col * 100 + 13;
				int y = row * 100 + 110;
				int circle_size = 75;
				if (board_contents[row][col] == Cell.RED) {
					g.setColor(Color.RED);
					g.fillOval(x, y, circle_size, circle_size);
				}
				else if (board_contents[row][col] == Cell.YELLOW) {
					g.setColor(Color.YELLOW);
					g.fillOval(x, y, circle_size, circle_size);
				}
			}
		}
	}
	
	@Override
	public Dimension getPreferredSize() {
		return new Dimension(BOARD_SIZE, BOARD_SIZE);
	}
}
