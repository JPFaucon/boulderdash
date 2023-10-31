/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package g58990.boulderdash.view.javafx;

import g58990.boulderdash.controller.BoulderDash;
import g58990.boulderdash.model.Board;
import g58990.boulderdash.model.Direction;
import g58990.boulderdash.model.GameState;
import g58990.boulderdash.oo.Observable;
import g58990.boulderdash.oo.Observer;
import java.io.IOException;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 *
 * @author jp
 */
public class ApplicationJavaFX extends Application implements Observer {
    private BoulderDash boulderDash;
    private Board board;

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Boulder Dash");
        VBox root = new VBox();
        Label welcome = new Label("Bienvenue dans le jeu Boulder Dash, veuillez sélectionner un niveau.");
        final double MAX_FONT_SIZE = 30.0;
        welcome.setFont(new Font(MAX_FONT_SIZE));
        root.getChildren().add(welcome);
        root.setAlignment(Pos.CENTER);
        HBox levels = buttonsLevels(2, stage);
        root.getChildren().add(levels);
        levels.setAlignment(Pos.CENTER);
        levels.setSpacing(10);
        Scene chooseLevel = new Scene(root);
        stage.setScene(chooseLevel);
        stage.setMaximized(true);
        stage.show();
    }
    
    public void startLevel(String lvlName, Stage stage) {
        try {
            boulderDash = new BoulderDash(lvlName, this);
        } catch (IOException | IllegalArgumentException | IllegalStateException e) {
            System.out.println(e.getMessage());
        }
        boulderDash.startGame();
        
        
        VBox root = new VBox(10);
        root.setAlignment(Pos.CENTER);
        Scene scene = new Scene(root, 560, 380);
        HBox diamonds = new HBox();
        diamonds.setAlignment(Pos.CENTER);
        diamonds.setSpacing(20);
        HBox hboxBoard = new HBox();
        HBox hboxMessage = new HBox();
        HBox buttons = new HBox();
        
        Label message = new Message(board);
        hboxMessage.getChildren().add(message);
        hboxMessage.setAlignment(Pos.CENTER);
        hboxMessage.setPadding(new Insets(20));
        
        Button undo = new Button("Undo");
        undo.setMaxWidth(100);
        undo.setFocusTraversable(false);
        Button redo = new Button("Redo");
        redo.setMaxWidth(100);
        redo.setFocusTraversable(false);
        
        Button giveup = new Button("Abandonner");
        giveup.setMaxWidth(100);
        giveup.setFocusTraversable(false);
        
        
        VBox collectedDiamondsVB = new VBox();
        collectedDiamondsVB.setAlignment(Pos.CENTER);
        diamonds.getChildren().add(collectedDiamondsVB);
        Label collectedDiamonds = new Label("Nombre de diamants collectés");
        Label nbCollectedDiamonds = new NbCollectedDiamonds(board);
        collectedDiamondsVB.getChildren().add(collectedDiamonds);
        collectedDiamondsVB.getChildren().add(nbCollectedDiamonds);
        
        VBox diamondsToCollectVB = new VBox();
        diamondsToCollectVB.setAlignment(Pos.CENTER);
        diamonds.getChildren().add(diamondsToCollectVB);
        Label diamondsToCollect = new Label("Nombre de diamants à collecter");
        Label nbDiamondsToCollect = new Label("" + board.getNbDiamondToCollect());
        diamondsToCollectVB.getChildren().add(diamondsToCollect);
        diamondsToCollectVB.getChildren().add(nbDiamondsToCollect);
        
        VBox diamondsLeftVB = new VBox();
        diamondsLeftVB.setAlignment(Pos.CENTER);
        diamonds.getChildren().add(diamondsLeftVB);
        Label diamondsLeft = new Label("Nombre de diamants encore en jeu");
        Label nbDiamondsLeft = new NbDiamondsLeft(board);
        diamondsLeftVB.getChildren().add(diamondsLeft);
        diamondsLeftVB.getChildren().add(nbDiamondsLeft);
        
        buttons.setAlignment(Pos.CENTER);
        buttons.setSpacing(50);
        buttons.getChildren().add(undo);
        buttons.getChildren().add(giveup);
        buttons.getChildren().add(redo);
        
        String boardString = boulderDash.toString();
        GameBoard gameBoard = new GameBoard(stringToArray(boardString), board);
        
        EventHandler<KeyEvent> keyListener = (KeyEvent event) -> {
            if(boulderDash.getGameState() == GameState.LOST 
                || boulderDash.getGameState() == GameState.WON){
                event.consume();
                return;
            }

            if(event.getCode() == KeyCode.UP || event.getCode() == KeyCode.DOWN ||
                    event.getCode() == KeyCode.RIGHT || event.getCode() == KeyCode.LEFT) {
                switch(event.getCode()) {
                    case UP -> boulderDash.move(Direction.UP);
                    case DOWN -> boulderDash.move(Direction.DOWN);
                    case RIGHT -> boulderDash.move(Direction.RIGHT);
                    case LEFT -> boulderDash.move(Direction.LEFT);
                }
            }
            event.consume();
        };
        scene.addEventHandler(KeyEvent.KEY_PRESSED, keyListener);
        
        undo.addEventHandler(ActionEvent.ACTION, (ActionEvent t) -> {
            if(boulderDash.getGameState() == GameState.LOST 
                || boulderDash.getGameState() == GameState.WON){
                t.consume();
                return;
            }
            try {
                boulderDash.undo();
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Il n'y a pas de commande à annuler.");
            }
        });
        
        redo.addEventHandler(ActionEvent.ACTION, (ActionEvent t) -> {
            if(boulderDash.getGameState() == GameState.LOST 
                || boulderDash.getGameState() == GameState.WON){
                t.consume();
                return;
            }
            try {
                boulderDash.redo();
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Il n'y a pas de commande à refaire.");
            }
        });
        
        giveup.addEventHandler(ActionEvent.ACTION, (ActionEvent t) -> {
            if(boulderDash.getGameState() == GameState.LOST 
                || boulderDash.getGameState() == GameState.WON){
                t.consume();
                return;
            }
            boulderDash.giveUp();
        });
        
        root.getChildren().add(diamonds);
        root.getChildren().add(hboxBoard);
        root.getChildren().add(hboxMessage);
        root.getChildren().add(buttons);
        root.getChildren().add(buttonsLevels(2, stage));
        hboxBoard.setAlignment(Pos.CENTER);
        hboxBoard.getChildren().add(gameBoard);
        
        stage.setScene(scene);
        stage.setMaximized(true);
    }
    
    public String[][] stringToArray(String s) {
        String[] lines = s.split("\r\n|\r|\n");
        int nRows = lines.length, nColumns = lines[1].length();
        String[][] letters = new String[nRows][nColumns];
        for(int row = 0; row < nRows; row++) {
            letters[row] = lines[row].split("");
        }
        return letters;
    }
    
    public HBox buttonsLevels(int numberLevels, Stage stage) {
        HBox levels = new HBox();
        levels.setAlignment(Pos.CENTER);
        levels.setSpacing(30);
        levels.setPadding(new Insets(20));
        for (int i = 0; i < numberLevels; i++) {
            Button button = new Button("Level " + (i+1));
            button.setFocusTraversable(false);
            levels.getChildren().add(button);
        }
        int i = 1;
        for(Node button : levels.getChildren()) {
            String lvlName = "level" + i + ".txt";
            button.addEventHandler(ActionEvent.ACTION, (ActionEvent t) -> {
                startLevel(lvlName, stage);
            });
            i++;
        }
        return levels;
    }

    @Override
    public void update() {
    }

    @Override
    public void setObservable(Observable observable) {
        if(observable instanceof Board board1)
        this.board = board1;
    }
}
