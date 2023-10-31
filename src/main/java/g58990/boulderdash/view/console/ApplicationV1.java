/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package g58990.boulderdash.view.console;

import g58990.boulderdash.controller.BoulderDash;
import g58990.boulderdash.model.Direction;
import g58990.boulderdash.model.GameState;
import g58990.boulderdash.oo.Observable;
import g58990.boulderdash.oo.Observer;
import java.io.IOException;
import java.util.Scanner;

/**
 * The console view of the project Boulder Dash.
 * @author jp
 */
public class ApplicationV1 implements Observer {
    private BoulderDash boulderDash;
    private Observable board;
    
    /**
     * Select the level to play, build the board and start the game.
     */
    public void start() {
        String nameLevel = "level1.txt";
        try {
            boulderDash = new BoulderDash(nameLevel, this);
        } catch (IOException | IllegalArgumentException | IllegalStateException e) {
            System.out.println(e.getMessage());
        }
        boulderDash.startGame();
    }
    
    @Override
    public void setObservable(Observable observable) {
        this.board = observable;
    }
    
    /**
     * Lauch the application.
     * @param args unused
     */
    public static void main(String[] args) {
        ApplicationV1 app = new ApplicationV1();
        app.start();
    }
    
    /**
     * Display the board.
     */
    public void displayBoard() {
        System.out.println(boulderDash.toString());
    }
    
    /**
     * Give up the game and display a giving up message.
     */
    public void displayGiveUp() {
        boulderDash.giveUp();
        System.out.println("Vous avez abandonné la partie...");
    }
    
    /**
     * Display how many diamonds the player collected and how many he has to collect.
     */
    public void displayDiamonds() {
        System.out.println("Diamants : " + boulderDash.getDiamonds());
    }

    @Override
    public void update() {
        displayDiamonds();
        displayBoard();
        if(boulderDash.getGameState() != GameState.UNPLAYABLE 
                && boulderDash.getGameState() != GameState.IMPOSSIBLE_MOVE
                && boulderDash.getGameState() != GameState.NEXT_MOVE) {
            if(boulderDash.getGameState() == GameState.LOST) {
                System.out.println("Perdu !");
            } else if(boulderDash.getGameState() == GameState.WON) {
                System.out.println("Gagné !");
            } else {
                Scanner c = new Scanner(System.in);
                boolean validatedCommand;
                do {
                    validatedCommand = true;
                    String command = c.next();
                    try {
                        switch(command) {
                            case "giveup" -> displayGiveUp();
                            case "z" -> boulderDash.move(Direction.UP);
                            case "q" -> boulderDash.move(Direction.LEFT);
                            case "s" -> boulderDash.move(Direction.DOWN);
                            case "d" -> boulderDash.move(Direction.RIGHT);
                            case "undo" -> {
                                try {
                                    boulderDash.undo();
                                } catch (IndexOutOfBoundsException e) {
                                    System.out.println("Il n'y a pas de commande à annuler.");
                                    validatedCommand = false;
                                }
                            }
                            case "redo" -> {
                                try {
                                    boulderDash.redo();
                                } catch (IndexOutOfBoundsException e) {
                                    System.out.println("Il n'y a pas de commande à refaire.");
                                    validatedCommand = false;
                                }
                            }
                            default -> {
                                System.out.println("Retentez une commande.");
                                validatedCommand = false;
                            }
                        }
                    } catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                    }
                } while(!validatedCommand);
            }
        }
    }
}
