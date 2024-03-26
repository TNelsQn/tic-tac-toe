package tic.tactoe;

import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

public class App extends Application {

    private TicTacToeController tictactoe;

    @Override
    public void start(Stage stage) throws IOException {
        tictactoe = new TicTacToeController();
        tictactoe.start(stage);
    }

    public static void main(String[] args) {
        launch();
    }

}