import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

import javax.swing.JFrame;
import javax.swing.JOptionPane;


public class Score {
	private int r; // red pegs used
	private int y; // yellow pegs used
	private int e; // empty cells
	private int e_per_row; // empty cells per row
	private int rows_unused;
	private int score;
	
	public static final int BASE_SCORE = 1000;
	public static final int ROW_MULTIPLE = 500;
	public static final int EXTRA_PEGS_MULTIPLE = 200;
	public static final int UNUSED_SPACES_MULTIPLE = 100;
	
	private BufferedReader in;
	private Writer out;
	private String line;
	private String fdoc = "highscores.txt";
	private String[] players;
	private int[] scores;
	public static final int NUM_SCORES = 5;
	
	public int getScore(Cell[][] board_contents, Cell winner) {
		final int p;
		for (int row = 0; row < Board.NUM_ROWS; row++) {
			for (int col = 0; col < Board.NUM_COLS; col++) {
				if (board_contents[row][col] == Cell.RED) {
					r++;
				}
				if (board_contents[row][col] == Cell.YELLOW) {
					y++;
				}
				if (board_contents[row][col] == Cell.EMPTY) {
					e++;
					e_per_row++;
				}
			}
			if (e_per_row == 7) { 
				rows_unused++;
				e_per_row = 0;
			}
		}
		if (winner == Cell.RED) { p = r; }
		else { p = y; }
		int row_bonus = ROW_MULTIPLE * rows_unused;
		int extra_pegs_bonus = EXTRA_PEGS_MULTIPLE * (21 - p);
		int unused_spaces_bonus = UNUSED_SPACES_MULTIPLE * e;
		
		score = BASE_SCORE + row_bonus + extra_pegs_bonus + unused_spaces_bonus;
		return score;
	}
	
	public Score() throws IOException {
		int index = 0;
		
		in = new BufferedReader(new FileReader(fdoc));
		players = new String[NUM_SCORES];
		scores = new int[NUM_SCORES];
		
		try {
			line = in.readLine();
			
			while (line != null && index < NUM_SCORES) {
   	  	    	line = line.toLowerCase().trim();
   	  	    	int tabPos = line.indexOf("\t");
				String player = line.substring(0, tabPos).trim();
				Integer hscore = Integer.parseInt(line.substring(tabPos+1, line.length()).trim());
				players[index] = player;
				scores[index] = hscore;
				index++;
				
				line = in.readLine();
			  }
		 }
		 catch (IOException e) {
			 throw new IOException("Error while reading");
		 }
		 finally {
			 in.close();
		 }
	}
	
	public void displayScores(JFrame frame) {
		String hsString = "";
		
		for (int i = 0; i < players.length; i++) {
			hsString += Integer.toString(i+1) + ". " + 
					players[i] + "_________________________" + scores[i] + "\n";
		}
		
		JOptionPane.showMessageDialog(frame, hsString, "HIGH SCORES", 
				JOptionPane.PLAIN_MESSAGE);
	}
	
	public void addScore(JFrame frame, Cell[][] board_contents, Cell winner) {
		String username = JOptionPane.showInputDialog(frame, 
				"New High Score! Enter your name:", "CONGRATULATIONS", 
				JOptionPane.QUESTION_MESSAGE);
		int score = getScore(board_contents, winner);
		if (score > scores[scores.length - 1]) {
			scores[scores.length - 1] = score;
			players[scores.length - 1] = username;
			sort();
			displayScores(frame);
		}
	}
	
	public void sort() {
		String tempName;
		int tempScore;
		for (int i = scores.length - 1; i > 0; i--) {
			if (scores[i] > scores[i-1]) {
				tempName = players[i];
				tempScore = scores[i];
				scores[i] = scores[i-1];
				players[i] = players[i-1];
				scores[i-1] = tempScore;
				players[i-1] = tempName;
			}
		}
	}
	
	public void writeScores() throws IOException {
		out = new BufferedWriter(new FileWriter(fdoc));
		try {
			for (int i = 0; i < NUM_SCORES; i++) {
				out.write(players[i] + "\t" + scores[i] + "\n");
			}
		}
		catch (IOException e) { }
		out.close();
	}
}
