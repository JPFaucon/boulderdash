/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package g58990.boulderdash.controller;

import g58990.boulderdash.model.Board;
import g58990.boulderdash.model.Command;
import g58990.boulderdash.model.Direction;
import g58990.boulderdash.model.GameState;
import g58990.boulderdash.model.MainCommand;
import g58990.boulderdash.oo.Observer;
import static g58990.boulderdash.util.fileReader.newBoard;
import g58990.boulderdash.view.console.ApplicationV1;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Controller of BoulderDash.
 * @author jp
 */
public class BoulderDash {
    private final Board board;
    private List<Command> history;
    private int currentCommand;
    
    /**
     * Constructor of BoulderDash.
     * @param nameLevel the name of the level
     * @param app the application
     * @throws IOException if read fails for any reason
     * @throws IllegalStateException if the content of the file can't work to 
     * create the board
     * @throws IllegalArgumentException if there is an unknown character or if 
     * the number of diamond to collect isn't a integer
     */
    public BoulderDash(String nameLevel, Observer app) throws IOException, 
            IllegalArgumentException, IllegalStateException {
        board = newBoard(nameLevel);
        board.registerObserver(app);
        app.setObservable(board);
        history = new ArrayList();
        currentCommand = -1;
    }
    
    public BoulderDash(String nameLevel) throws IOException, 
            IllegalArgumentException, IllegalStateException {
        board = newBoard(nameLevel);
        history = new ArrayList();
        currentCommand = -1;
    }
    
    public void startGame() {
        board.fall();
        board.notifyObserver();
    }
    
    /**
     * Gets the state of the game.
     * @return the state of the game
     */
    public GameState getGameState() {
        return board.getGameState();
    }
    
    /**
     * Move the player.
     * @param dir the direction of the move
     */
    public void move(Direction dir) {
        Command move = new MainCommand(dir, board);
        if(currentCommand != (history.size()-1)) {
            history.subList(currentCommand + 1, history.size()).clear();
        }
        history.add(move);
        currentCommand++;
        move.execute();
    }
    
    /**
     * Undoes the last command.
     */
    public void undo() {
        currentCommand--;
        try {
            history.get(currentCommand+1).undo();
        } catch (IndexOutOfBoundsException e) {
            currentCommand++;
            throw new IndexOutOfBoundsException();
        }
    }
    
    /**
     * Redoes the last undo command.
     */
    public void redo() {
        currentCommand++;
        try{
            history.get(currentCommand).execute();
        } catch (IndexOutOfBoundsException e) {
            currentCommand--;
            throw new IndexOutOfBoundsException();
        }
        
    }
    
    /**
     * Gives up the game.
     */
    public void giveUp() {
        board.giveUp();
    }

    @Override
    public String toString() {
        return board.toString();
    }
    
    /**
     * Gets the number of collected diamond and the number of diamond to collect.
     * @return how many diamonds the player collected and how many he has to collect
     */
    public String getDiamonds() {
        return board.getDiamonds();
    }
    
    public void registerObserver(Observer view) {
        board.registerObserver(view);
    }
}
