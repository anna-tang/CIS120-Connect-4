=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=
CIS 120 Game Project README
PennKey: annatang
=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=

===================
=: Core Concepts :=
===================

- List the four core concepts, the features they implement, and why each feature
  is an appropriate use of the concept. You may copy and paste from your proposal
  document if you did not change the features you are implementing.

  1. 2D Array
  
  A 2D Array makes sense to represent the Connect 4 board as a grid. It will
  allow search to be implemented easily because I can search rows, columns,
  and diagonals with four in a row.
  
  The 2-D array is an appropriate data structure because the board cannot be 
  resized, and it is a regular grid with order and indices.
  

  2. File I/O

  I will keep track of a list of usernames and corresponding high scores, and 
  display them to the user. If a player makes the leaderboard, my game prompts 
  the player to enter their username and their name and score are added to the 
  high score list, which is displayed once the game ends.
  
  I want the high scores list to be a list of all-time high scores, which 
  means that the scores need to be saved to a file in order to persist 
  across sessions of the game. I will then read that file into our game when 
  it launches and write to that file if new high scores are added. To display
  the high scores, I will read the file into the display.


  3. Complex Search

  I will check for victory by searching for four in a row in rows, columns, and
  diagonals.
  
  Complex search is required to search for available moves as well as to detect 
  a win or tie state.
  

  4. Testable Component

  I will test the different victory states: 4 in a row, column, or diagonal.
  
  Checking for victory is the core of the game logic. I can test the different
  edge cases and make sure victories are not given for cases where a column,
  row, or diagonal has 4 of one color but not consecutively.


=========================
=: Your Implementation :=
=========================

- Provide an overview of each of the classes in your code, and what their
  function is in the overall game.
  
  The Game class specifies the frame and widgets of the GUI. It contains all the
  panels and buttons as well as their action listeners. It initializes all the
  GUI elements and runs the game.
  
  The Board class holds the primary game logic. It contains a constructor for 
  the board, which is represented by a 2D array, and methods for interacting 
  with the board. It enables keyboard focus on the board. There are methods for 
  checking for win or tie states, resetting the board, and drawing the board to 
  the GUI.
  
  The Cell enum provides convenient indication of the contents of a cell in the 
  board or the color of the current player.
  
  The Score class holds all the functionality of the high scores feature. It 
  reads in high scores from a text file and displays the high scores, and writes
  out high scores to the same text file if a new high score is reached. It also
  contains the algorithm for calculating scores based on the state of the game
  board at victory.
  
  The Marker class represents the marker in the GUI that shows which column a
  player is placing their peg into. It contains a constructor that reads in an
  image file and a method that draws the marker to the GUI.
  
  The MyTests class holds all the JUnit Tests that test the state of the board,
  aka whether there is a victory or a tie.


- Revisit your proposal document. What components of your plan did you end up
  keeping? What did you have to change? Why?
  
  I kept all my core concepts, but changed parts of my design. In my proposal I
  had a GamePiece class to represent the pegs that stored the color of each 
  piece and a method for the movement of each piece as it was dropped, but I
  switched to a Cell enum instead that represented the color of each piece 
  because I realized I could represent the color of each piece with integers and
  an empty cell as an integer as well. Therefore the representation of the
  contents of my board became a 2D array of integers rather than GamePiece
  objects as I had originally proposed. I did not implement movement of the peg 
  as it dropped down to its position but instead just had repaint draw each peg 
  in its final position.


- Were there any significant stumbling blocks while you were implementing your
  game (related to your design, or otherwise)?
  
  Checking for all the edge cases of different victory states and implementing
  the isVictory method required some trial and error. Moving the actual
  components of the game around and changing the state (player, status, victory
  or tie) of the game proved to be slightly challenging.
 

- Evaluate your design. Is there a good separation of functionality? How well is
  private state encapsulated? What would you refactor, if given the chance?
  
  There is decently good separation of functionality since the GUI is separate
  from the actual game logic and the File I/O is also its own separate class.
  Private state is encapsulated very well since only constants or variables
  needed between classes were public, like the boolean for whether the game was
  running or not and the number of wins for each player for consecutive plays.
  If given the chance, I would create more helper functions for my Board class
  because there is some repetition in the cases for different current players. 



========================
=: External Resources :=
========================

- Cite any external resources (libraries, images, tutorials, etc.) that you may
  have used while implementing your game.

Marker image: http://i.imgur.com/ludu8pD.png
Java Dialog Tutorial: https://docs.oracle.com/javase/tutorial/uiswing/components/dialog.html

