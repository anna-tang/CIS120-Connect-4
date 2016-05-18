import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


import org.junit.Test;

public class MyTests {
	// vertical victories
	@Test
	public void testVictoryVerticalLeftEnd() {
		Cell[][] board_contents = new Cell[Board.NUM_ROWS][Board.NUM_COLS];
		for (int row = 0; row < 4; row++) {
			board_contents[row][0] = Cell.RED;
		}
		assertTrue("Vertical left end", Board.isVictory(board_contents, Cell.RED, 0, 0));
	}
	
	@Test
	public void testVictoryVerticalRightEnd() {
		Cell[][] board_contents = new Cell[Board.NUM_ROWS][Board.NUM_COLS];
		for (int row = 2; row < 6; row++) {
			board_contents[row][6] = Cell.YELLOW;
		}
		assertTrue("Vertical right end", Board.isVictory(board_contents, Cell.YELLOW, 2, 6));
	}
	
	@Test
	public void testVictoryVerticalMiddle() {
		Cell[][] board_contents = new Cell[Board.NUM_ROWS][Board.NUM_COLS];
		for (int row = 1; row < 5; row++) {
			board_contents[row][3] = Cell.RED;
		}
		assertTrue("Vertical middle", Board.isVictory(board_contents, Cell.RED, 1, 3));
	}
	// horizontal victories
	@Test
	public void testVictoryHorizontalBottomRow() {
		Cell[][] board_contents = new Cell[Board.NUM_ROWS][Board.NUM_COLS];
		for (int col = 0; col < 4; col++) {
			board_contents[5][col] = Cell.RED;
		}
		assertTrue("Horizontal bottom", Board.isVictory(board_contents, Cell.RED, 5, 3));
	}
	
	@Test
	public void testVictoryHorizontalTopRow() {
		Cell[][] board_contents = new Cell[Board.NUM_ROWS][Board.NUM_COLS];
		for (int col = 2; col < 6; col++) {
			board_contents[0][col] = Cell.RED;
		}
		assertTrue("Horizontal top", Board.isVictory(board_contents, Cell.RED, 0, 4));
	}
	
	@Test
	public void testVictoryHorizontalMiddle() {
		Cell[][] board_contents = new Cell[Board.NUM_ROWS][Board.NUM_COLS];
		for (int col = 1; col < 5; col++) {
			board_contents[3][col] = Cell.RED;
		}
		assertTrue("Horizontal middle", Board.isVictory(board_contents, Cell.RED, 3, 4));
	}
	// diagonal victories
	@Test
	public void testVictoryDiagonalUpLeftDownRightEndPiece() {
		Cell[][] board_contents = new Cell[Board.NUM_ROWS][Board.NUM_COLS];
		board_contents[1][1] = Cell.RED;
		board_contents[2][2] = Cell.RED;
		board_contents[3][3] = Cell.RED;
		board_contents[4][4] = Cell.RED;
		assertTrue("Diagonal ULDR End", Board.isVictory(board_contents, Cell.RED, 1, 1));
	}
	
	@Test
	public void testVictoryDiagonalUpLeftDownRightMiddlePiece() {
		Cell[][] board_contents = new Cell[Board.NUM_ROWS][Board.NUM_COLS];
		board_contents[1][2] = Cell.RED;
		board_contents[2][3] = Cell.RED;
		board_contents[3][4] = Cell.RED;
		board_contents[4][5] = Cell.RED;
		assertTrue("Diagonal ULDR Middle", Board.isVictory(board_contents, Cell.RED, 3, 4));
	}
	
	@Test
	public void testVictoryDiagonalDownLeftUpRightEndPiece() {
		Cell[][] board_contents = new Cell[Board.NUM_ROWS][Board.NUM_COLS];
		board_contents[5][0] = Cell.RED;
		board_contents[4][1] = Cell.RED;
		board_contents[3][2] = Cell.RED;
		board_contents[2][3] = Cell.RED;
		assertTrue("Diagonal ULDR End", Board.isVictory(board_contents, Cell.RED, 2, 3));
	}
	
	@Test
	public void testVictoryDiagonalDownLeftUpRightMiddlePiece() {
		Cell[][] board_contents = new Cell[Board.NUM_ROWS][Board.NUM_COLS];
		board_contents[3][3] = Cell.RED;
		board_contents[2][4] = Cell.RED;
		board_contents[1][5] = Cell.RED;
		board_contents[0][6] = Cell.RED;
		assertTrue("Diagonal ULDR Middle", Board.isVictory(board_contents, Cell.RED, 1, 5));
	}
	
	// tie
	@Test
	public void testTie() {
		Cell[][] board_contents = new Cell[Board.NUM_ROWS][Board.NUM_COLS];
		board_contents[0][0] = Cell.YELLOW;
		board_contents[0][1] = Cell.YELLOW;
		board_contents[0][2] = Cell.RED;
		board_contents[0][3] = Cell.RED;
		board_contents[0][4] = Cell.YELLOW;
		board_contents[0][5] = Cell.RED;
		board_contents[0][6] = Cell.RED;
		board_contents[1][0] = Cell.YELLOW;
		board_contents[1][1] = Cell.RED;
		board_contents[1][2] = Cell.RED;
		board_contents[1][3] = Cell.YELLOW;
		board_contents[1][4] = Cell.YELLOW;
		board_contents[1][5] = Cell.RED;
		board_contents[1][6] = Cell.YELLOW;
		board_contents[2][0] = Cell.RED;
		board_contents[2][1] = Cell.RED;
		board_contents[2][2] = Cell.YELLOW;
		board_contents[2][3] = Cell.RED;
		board_contents[2][4] = Cell.RED;
		board_contents[2][5] = Cell.YELLOW;
		board_contents[2][6] = Cell.YELLOW;
		board_contents[3][0] = Cell.YELLOW;
		board_contents[3][1] = Cell.RED;
		board_contents[3][2] = Cell.YELLOW;
		board_contents[3][3] = Cell.YELLOW;
		board_contents[3][4] = Cell.RED;
		board_contents[3][5] = Cell.YELLOW;
		board_contents[3][6] = Cell.YELLOW;
		board_contents[4][0] = Cell.RED;
		board_contents[4][1] = Cell.YELLOW;
		board_contents[4][2] = Cell.YELLOW;
		board_contents[4][3] = Cell.RED;
		board_contents[4][4] = Cell.RED;
		board_contents[4][5] = Cell.YELLOW;
		board_contents[4][6] = Cell.RED;
		board_contents[5][0] = Cell.YELLOW;
		board_contents[5][1] = Cell.YELLOW;
		board_contents[5][2] = Cell.RED;
		board_contents[5][3] = Cell.RED;
		board_contents[5][4] = Cell.YELLOW;
		board_contents[5][5] = Cell.RED;
		board_contents[5][6] = Cell.RED;
		assertFalse("Tie is not victory", Board.isVictory(board_contents, Cell.RED, 3, 4));
		assertTrue("Tie", Board.isTie(board_contents));

	}
	
	// non-victories
	@Test
	public void testNonVictoryFourNonConsecutiveRow() {
		Cell[][] board_contents = new Cell[Board.NUM_ROWS][Board.NUM_COLS];
		board_contents[3][0] = Cell.RED;
		board_contents[3][1] = Cell.RED;
		board_contents[3][4] = Cell.RED;
		board_contents[3][5] = Cell.RED;
		assertFalse("Non Victory Row", Board.isVictory(board_contents, Cell.RED, 3, 4));
	}
	
	@Test
	public void testNonVictoryFourNonConsecutiveColumn() {
		Cell[][] board_contents = new Cell[Board.NUM_ROWS][Board.NUM_COLS];
		board_contents[5][5] = Cell.RED;
		board_contents[4][5] = Cell.RED;
		board_contents[3][5] = Cell.RED;
		board_contents[0][5] = Cell.RED;
		assertFalse("Non Victory Column", Board.isVictory(board_contents, Cell.RED, 0, 5));
	}
	
	@Test
	public void testNonVictoryFourNonConsecutiveULDRDiagonal() {
		Cell[][] board_contents = new Cell[Board.NUM_ROWS][Board.NUM_COLS];
		board_contents[0][0] = Cell.RED;
		board_contents[3][3] = Cell.RED;
		board_contents[4][4] = Cell.RED;
		board_contents[5][5] = Cell.RED;
		assertFalse("Non Victory Column Diagonal ULDR", Board.isVictory(board_contents, Cell.RED, 3, 3));
	}
	
	@Test
	public void testNonVictoryFourNonConsecutiveDLURDiagonal() {
		Cell[][] board_contents = new Cell[Board.NUM_ROWS][Board.NUM_COLS];
		board_contents[4][1] = Cell.RED;
		board_contents[3][2] = Cell.RED;
		board_contents[1][4] = Cell.RED;
		board_contents[0][5] = Cell.RED;
		assertFalse("Non Victory Column Diagonal DLUR", Board.isVictory(board_contents, Cell.RED, 1, 4));
	}
}
