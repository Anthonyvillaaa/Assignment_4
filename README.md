Tic-Tac-Toe JavaFX Project
Design Overview:

This is a basic Tic-Tac-Toe game made with JavaFX. I used a 3x3 grid of buttons to represent the board. When a player clicks a button, it puts either an X or O depending on whose turn it is. The game checks for a winner or a draw after each move.

It is styled using inline CSS. A gridplane was used since it was the most simple and worked well with the tic-tac-toe game.

Class Relationships

Everything is in one class called `Main`, which extends `Application` from JavaFX. That’s the class that runs the GUI. Inside that class, I created a 2D array of buttons to hold the game board and a few helper methods:

 `processMove(row, col)` — handles what happens when a player clicks a button.
 `hasWinner()` — checks if someone has won.
 `isFilled()` — checks if the board is full (for a draw).
 `clearBoard()` — resets the board for a new game.
 `displayAlert(msg)` — shows the result in a pop-up.

How the Game is Solved:

The game ends when either player gets 3 in a row or when the board is full (draw). The program checks all rows, columns, and both diagonals to figure out if someone won.

Once the game ends, a message is shown and the board resets so the players can start over.

