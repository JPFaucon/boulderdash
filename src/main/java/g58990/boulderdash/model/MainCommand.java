/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package g58990.boulderdash.model;

import java.util.Map;

/**
 * Defines the main command.
 * @author jp
 */
public class MainCommand implements Command{
    private Map<Content, Position> fallenContents;
    private Map<Content, Position> movedContents;
    private final Direction dir;
    private final Board board;
    
    /**
     * Constructor of MainCommand.
     * @param dir the dir of the movement of the player
     * @param board the game board
     */
    public MainCommand(Direction dir, Board board) {
        this.dir = dir;
        this.board = board;
    }
    
    @Override
    public void execute() {
        movedContents = board.move(dir.getDRow(), dir.getDCol());
        fallenContents = board.fall();
        board.notifyObserver();
    }

    @Override
    public void undo() {
        board.isUnplayable();
        if(!movedContents.isEmpty()) {
            if(fallenContents != null) {
                for (Map.Entry<Content, Position> entry : fallenContents.entrySet()) {
                    board.setFallenContent(entry.getKey(), entry.getValue());
                }
            }
            for (Map.Entry<Content, Position> entry : movedContents.entrySet()) {
                board.setMovedContent(entry.getKey(), entry.getValue());
            }
            board.checkGameState();
        }
        board.notifyObserver();
    }
    
}
