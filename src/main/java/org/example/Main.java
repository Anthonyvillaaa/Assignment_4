package org.example;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Main extends Application {

    // Keeps track of whether it's X's turn or not
    private boolean isXTurn = true;

    // 2D array to store our 3x3 game board buttons
    private final Button[][] grid = new Button[3][3];

    public static void main(String[] args) {
        // This starts the JavaFX application
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        // Set up the main layout using GridPane (basically a grid layout)
        GridPane layout = new GridPane();
        layout.setHgap(5);
        layout.setVgap(5);
        layout.setStyle("-fx-background-color: white; -fx-border-color: black; -fx-border-width: 2px;");

        // Creating 3 rows and 3 columns of buttons for the Tic-Tac-Toe board
        for (int y = 0; y < 3; y++) {
            for (int x = 0; x < 3; x++) {
                Button tile = new Button();
                tile.setPrefSize(100, 100); // makes the button a square
                tile.setFont(Font.font(24)); // sets the font size
                tile.setStyle("-fx-background-color: white; -fx-border-color: black; -fx-border-width: 2px;");
                final int row = y;
                final int col = x;
                // When the user clicks a button, it will call processMove
                tile.setOnAction(event -> processMove(row, col));
                grid[y][x] = tile;
                layout.add(tile, x, y); // add button to the layout
            }
        }

        // Setting up the window (stage)
        Scene gameScene = new Scene(layout, 340, 360);
        primaryStage.setTitle("Tic Tac Toe");
        primaryStage.setScene(gameScene);
        primaryStage.show();
    }

    // This handles what happens when a player makes a move
    private void processMove(int row, int col) {
        Button current = grid[row][col];

        // Ignore if button already has text
        if (!current.getText().isEmpty()) {
            return;
        }

        // Set the text to "X" or "O" depending on the turn, and style it
        if (isXTurn) {
            current.setText("X");
            current.setStyle("-fx-background-color: #d1e7ff; -fx-border-color: black; -fx-border-width: 2px; -fx-text-fill: #004085;");
        } else {
            current.setText("O");
            current.setStyle("-fx-background-color: #ffdce0; -fx-border-color: black; -fx-border-width: 2px; -fx-text-fill: #721c24;");
        }

        // Check if this move won the game
        if (hasWinner()) {
            displayAlert((isXTurn ? "X" : "O") + " wins!");
        }
        // If not a win, check for a draw
        else if (isFilled()) {
            displayAlert("Draw!");
        }
        // Otherwise, keep playing
        else {
            isXTurn = !isXTurn; // switch turns
        }
    }

    // Checks if all cells are filled (for detecting a draw)
    private boolean isFilled() {
        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                if (grid[r][c].getText().isEmpty()) {
                    return false; // found an empty cell
                }
            }
        }
        return true;
    }

    // Shows a popup window with the result and resets the board
    private void displayAlert(String msg) {
        Alert gameAlert = new Alert(Alert.AlertType.INFORMATION);
        gameAlert.setTitle("Result");
        gameAlert.setHeaderText(null);
        gameAlert.setContentText(msg);
        gameAlert.showAndWait(); // waits until user clicks OK
        clearBoard(); // reset board after game ends
    }

    // Clears the board and resets turn back to X
    private void clearBoard() {
        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                grid[r][c].setText("");
                grid[r][c].setStyle("-fx-background-color: white; -fx-border-color: black; -fx-border-width: 2px;");
            }
        }
        isXTurn = true;
    }

    // Checks for all possible winning lines: rows, columns, diagonals
    private boolean hasWinner() {
        for (int i = 0; i < 3; i++) {
            if (lineMatch(grid[i][0], grid[i][1], grid[i][2]) || // check row
                    lineMatch(grid[0][i], grid[1][i], grid[2][i])) { // check column
                return true;
            }
        }
        // check both diagonals
        return lineMatch(grid[0][0], grid[1][1], grid[2][2]) ||
                lineMatch(grid[0][2], grid[1][1], grid[2][0]);
    }

    // Helper method to check if three buttons have the same non-empty text
    private boolean lineMatch(Button b1, Button b2, Button b3) {
        String t1 = b1.getText();
        return !t1.isEmpty() && t1.equals(b2.getText()) && t1.equals(b3.getText());
    }
}
