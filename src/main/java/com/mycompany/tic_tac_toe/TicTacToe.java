package com.mycompany.tic_tac_toe;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 * JavaFX TicTacToe
 */
public class TicTacToe extends Application {

    private Button buttons[][] = new Button[3][3];
    
    private Label player_X_Score_Label , player_O_Score_Label ;
    
    private BorderPane contentCreation(){
        BorderPane root = new BorderPane();
        
        //title
        Label title_Label  = new Label("TIC TAC TOE");
        title_Label.setStyle("-fx-font-size : 24pt ; -fx-font-weight : bold ;");
        root.setTop(title_Label);
        
        //Game board
        GridPane gridPane = new GridPane();
        for(int row = 0 ; row < 3 ; row++){
            for(int col = 0 ; col < 3 ; col++){
                Button button = new Button("-");
                button.setPrefSize(100,100);
                buttons[row][col] = button;
                gridPane.add(button, row , col);
            }
        }
        root.setCenter(gridPane);
        
        //score
        HBox score_Board = new HBox(50);
        player_X_Score_Label = new Label("Player X : 0");
        player_X_Score_Label.setStyle("-fx-font-size : 16pt ; -fx-font-weight : bold ;");
        
        player_O_Score_Label = new Label("Player O : 0");
        player_O_Score_Label.setStyle("-fx-font-size : 16pt ; -fx-font-weight : bold ;");
        
        score_Board.getChildren().addAll(player_X_Score_Label , player_O_Score_Label);
        
        root.setBottom(score_Board);
        
        return root;
    }
    
    @Override
    public void start(Stage stage) {
        Scene scene = new Scene(contentCreation());
        stage.setTitle("Tic-Tac-Toe");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}