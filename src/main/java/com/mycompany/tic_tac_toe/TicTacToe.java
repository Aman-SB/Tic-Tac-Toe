package com.mycompany.tic_tac_toe;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.paint.Color;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

/**
 * JavaFX TicTacToe
 */
public class TicTacToe extends Application {

    //adding button matrix to traversing for the X and O
    private Button buttons[][] = new Button[3][3];
    
    private Label player_X_Score_Label , player_O_Score_Label ;
    
    private boolean player_X_Turn = true;
    
    private int player_X_Score = 0 , player_O_Score = 0;
    
    private BorderPane contentCreation(){
        BorderPane root = new BorderPane();
        
        //title
        Label title_Label  = new Label("TIC TAC TOE");
        title_Label.setStyle("-fx-font-size : 24pt ; -fx-font-weight : bold ;"); //style label
        root.setTop(title_Label);
        
        //Game board
        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);  //shifting horizontal gap between cells
        gridPane.setVgap(10);   // shifting vertical gap between cells
        for(int row = 0 ; row < 3 ; row++){
            for(int col = 0 ; col < 3 ; col++){
                Button button = new Button("");
                button.setPrefSize(100,100);
                button.setOnAction(event-> buttonAction(button));
                buttons[row][col] = button;
                gridPane.add(button, col, row);
            }
        }
        root.setCenter(gridPane);   //setting the position of the gridpane to center
        
        //score
        HBox score_Board = new HBox(50);
        //player x labeling
        player_X_Score_Label = new Label("Player X : 0");
        player_X_Score_Label.setStyle("-fx-font-size : 16pt ; -fx-font-weight : bold ;");
        //player y labeling
        player_O_Score_Label = new Label("Player O : 0");
        player_O_Score_Label.setStyle("-fx-font-size : 16pt ; -fx-font-weight : bold ;");
        
        score_Board.getChildren().addAll(player_X_Score_Label , player_O_Score_Label);
        
        root.setBottom(score_Board);    //setting the score board to bottom in the screen
        
        return root;
    }
    
    //button action on clciking the button what we will be happen
    private void buttonAction(Button button){
        if(button.getText().equals("")){
            if(player_X_Turn){
                button.setText("X");
                button.setStyle("-fx-font-size : 24pt ; -fx-font-weight : bold ;-fx-border-color: grey;-fx-background-color: coral;");
            }
            else{
                button.setText("O");
                button.setStyle("-fx-font-size : 24pt ; -fx-font-weight : bold ;-fx-border-color: grey;-fx-background-color: yellow;");
            }
            player_X_Turn = !player_X_Turn;
            
            checkWinner();
        }
        return;
    }
    
    //winnning codintion logic
    private void checkWinner(){
        //row
        for(int row=0;row<3;row++){
            if(buttons[row][0].getText().equals(buttons[row][1].getText()) &&
                    buttons[row][1].getText().equals(buttons[row][2].getText()) &&
                    !buttons[row][0].getText().isEmpty())
            {
                //we will have a winner
                String winner = buttons[row][0].getText();
                showWinnerDialougeBox(winner);
                updateScore(winner);
                resetBoard();
                return;
            }
        }
        
        //col
        for(int col=0;col<3;col++){
            if(buttons[0][col].getText().equals(buttons[1][col].getText()) &&
                    buttons[1][col].getText().equals(buttons[2][col].getText()) &&
                    !buttons[0][col].getText().isEmpty())
            {
                //we will have a winner
                String winner = buttons[0][col].getText();
                showWinnerDialougeBox(winner);
                updateScore(winner);
                resetBoard();
                return;
            }
        }
                
        //first diagonal
       if(buttons[0][0].getText().equals(buttons[1][1].getText()) &&
                    buttons[1][1].getText().equals(buttons[2][2].getText()) &&
                    !buttons[0][0].getText().isEmpty())
            {
                //we will have a winner
                String winner = buttons[0][0].getText();
                showWinnerDialougeBox(winner);
                updateScore(winner);
                resetBoard();
                return;
            }
       
       //second diagonal
       if(buttons[0][2].getText().equals(buttons[1][1].getText()) &&
                    buttons[1][1].getText().equals(buttons[2][0].getText()) &&
                    !buttons[0][2].getText().isEmpty())
            {
                //we will have a winner
                String winner = buttons[0][2].getText();
                showWinnerDialougeBox(winner);
                updateScore(winner);
                resetBoard();
                return;
            }
       
       //tie condition
       boolean tie_checker = true;
       for(Button row[] : buttons){
            for(Button button : row){
                if(button.getText().isEmpty()){
                    tie_checker=false;
                    break;
                }
            }
        }
       
       if(tie_checker){
           resetBoard();
       }
    }
    
    //on winning dialouge box appear
    private void showWinnerDialougeBox(String winner){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Winner");
        alert.setContentText("Congratulation " + winner + " ! You won the game.");
        alert.setHeaderText("");
        alert.showAndWait();
    }
    
    //tie dialogue box
    private void showTieDialougeBox(String winner){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Tie");
        alert.setContentText("GameOver ! It's a tie.");
        alert.setHeaderText("");
        alert.showAndWait();
    }
    
    //updating score
    private void updateScore(String winner){
        if(winner.equals("X")){
            player_X_Score++;
            player_X_Score_Label.setText("Player X : " + player_X_Score);
        }
        else{
            player_O_Score++;
            player_O_Score_Label.setText("Player O : " + player_O_Score);
        }
    }
    
    //reset board
    private void resetBoard(){
        for(Button row[] : buttons){
            for(Button button : row){
                button.setText("");
                button.setStyle("-fx-font-size : 16pt ; -fx-font-weight : bold ;");
            }
        }
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