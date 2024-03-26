package tic.tactoe;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;


public class TicTacToeController extends Application {

    private Button[][] buttons = new Button[3][3];
    private GridPane gridPane = new GridPane();
    private TextField title;
    private VBox root;
    private boolean turn = true;

    @Override
    public void start(Stage stage) {
        root = new VBox();
        root.setPadding(new Insets(20));
        root.setAlignment(Pos.CENTER);

        title = new TextField("X's turn");
        title.setAlignment(Pos.CENTER);
        title.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        title.getStyleClass().add("text-field");
        title.setDisable(true);
        root.getChildren().add(title);


        gridPane.setPadding(new Insets(10));
        gridPane.setHgap(5);
        gridPane.setVgap(5);

        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                Button button = new Button();
                button.setMinSize(100, 100);
                button.getStyleClass().add("game-button");
                button.setFont(Font.font("Arial", FontWeight.BOLD, 40));
                int rowIndex = row;
                int colIndex = col; 
                button.setOnAction(event -> {
                handleButtonClick(rowIndex, colIndex);
                isGameFinished();
                });
                gridPane.add(button, col, row);
                buttons[row][col] = button;
            }
        }

        root.getChildren().add(gridPane);

        Button resetButton = new Button("Rest Game");
        resetButton.setOnAction(event -> resetGame());
        resetButton.setPrefWidth(200);
        root.getChildren().addAll(resetButton);

        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("css.css").toExternalForm());
        stage.setScene(scene);
        stage.setTitle("Tic Tac Toe");
        stage.show();
    }

    private void handleButtonClick(int row, int col) {
        if (turn) {
            buttons[row][col].setText("X");
            title.setText("O's turn");
        } else {
            buttons[row][col].setText("O");
            title.setText("X's turn");
        }
        turn = !turn;
        buttons[row][col].setDisable(true);
    }

    // Check if the game is finished
    private void isGameFinished() {
        isWon();
        isDraw();
    }

    // Check for winning conditions
    private void isWon() {
        // Check rows
        for (int row = 0; row < 3; row++) {
            if (!buttons[row][0].getText().isEmpty() && buttons[row][0].getText().equals(buttons[row][1].getText()) && buttons[row][1].getText().equals(buttons[row][2].getText())) {
                cleanWin(buttons[row][0].getText());
                return; // Exit the method early if a win is found
            }
        }

        // Check columns
        for (int col = 0; col < 3; col++) {
            if (!buttons[0][col].getText().isEmpty() && buttons[0][col].getText().equals(buttons[1][col].getText()) && buttons[1][col].getText().equals(buttons[2][col].getText())) {
                cleanWin(buttons[0][col].getText());
                return; // Exit the method early if a win is found
            }
        }

        // Check diagonals
        if (!buttons[1][1].getText().isEmpty() && ((buttons[0][0].getText().equals(buttons[1][1].getText()) && buttons[1][1].getText().equals(buttons[2][2].getText())) 
            || (buttons[0][2].getText().equals(buttons[1][1].getText()) && buttons[1][1].getText().equals(buttons[2][0].getText())))) {
            cleanWin(buttons[1][1].getText());
            return; // Exit the method early if a win is found
        }
    }


    private void cleanWin(String winner) {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                buttons[row][col].setDisable(true);
            }
        }

        title.setText(winner + " won the game!!!!!!");
    }

    // Check for a draw
    private void isDraw() {
        int drawCount = 0;
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (buttons[row][col].getText().equals("")) {
                    drawCount++;
                }
            }
        }

        if (drawCount == 0) {cleanDraw();}
    }

    private void cleanDraw() {
        title.setText("The game ended in a draw");
    }

    private void resetGame() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                buttons[row][col].setText("");
                buttons[row][col].setDisable(false);
            }
        }
        turn = true;
        title.setText("X's turn");
    }
    
}
