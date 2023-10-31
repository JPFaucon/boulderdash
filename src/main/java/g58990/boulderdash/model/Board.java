/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package g58990.boulderdash.model;

import g58990.boulderdash.oo.Observable;
import g58990.boulderdash.oo.Observer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Defines a board.
 * @author jp
 */
public class Board implements Observable {
    private final Box[][] boxess;
    private Player player;
    private GameState gameState;
    private int nbCollectedDiamonds;
    private final int nbDiamondToCollect;
    private final int nbDiamondTotal;
    private Position exitPosition;
    private final List<Observer> observers;
    
    /**
     * Constructor of Board.
     * @param nbDiamondToCollect the number of diamonds to collect
     * @param nbDiamondTotal the total number of diamond on the board
     * @param nRows the number of rows
     * @param nColumns the number of columns
     */
    public Board(int nbDiamondToCollect, int nbDiamondTotal, int nRows, int nColumns) {
        this.observers = new ArrayList<>();
        boxess = new Box[nRows][nColumns];
        gameState = GameState.INGAME;
        nbCollectedDiamonds = 0;
        this.nbDiamondTotal = nbDiamondTotal;
        this.nbDiamondToCollect = nbDiamondToCollect;
    }
    
    /**
     * Gets the box on the given position.
     * @param row the row of the position
     * @param col the col of the position
     * @return the box on the given position
     */
    public Box getBox(int row, int col) {
        if(row < 0 || col < 0 || row > boxess.length || col > boxess[0].length) {
            throw new IllegalArgumentException();
        }
        return boxess[row][col];
    }
    
    /**
     * Set a content on a position.
     * @param content the content to set
     * @param pos the position where to set the content
     */
    private void setContent(Content content, Position pos) {
        int row = pos.getRow(), col = pos.getColumn();
        content.setPosition(row, col);
        boxess[row][col].setContent(content);
    }
    
    /**
     * Creates a new wall on the given position.
     * @param row the row of the position
     * @param col the column of the position
     */
    public void newWall(int row, int col) {
        Position pos = new Position(row, col);
        boxess[row][col] = new Box(new Wall(pos));
    }
    
    /**
     * Creates a new ground on the given position.
     * @param row the row of the position
     * @param col the column of the position
     */
    public void newGround(int row, int col) {
        Position pos = new Position(row, col);
        boxess[row][col] = new Box(new Ground(pos));
    }
    
    /**
     * Creates a new empty on the given position.
     * @param row the row of the position
     * @param col the column of the position
     */
    public void newEmpty(int row, int col) {
        Position pos = new Position(row, col);
        boxess[row][col] = new Box(new Empty(pos));
    }
    
    /**
     * Creates a new empty on the given position.
     * @param pos the position of the empty
     */
    public void newEmpty(Position pos) {
        setContent(new Empty(pos), pos);
    }
    
    /**
     * Creates a new boulder on the given position.
     * @param row the row of the position
     * @param col the column of the position
     */
    public void newBoulder(int row, int col) {
        Position pos = new Position(row, col);
        boxess[row][col] = new Box(new Boulder(pos));
    }
    
    /**
     * Creates a new diamond on the given position.
     * @param row the row of the position
     * @param col the column of the position
     */
    public void newDiamond(int row, int col) {
        Position pos = new Position(row, col);
        boxess[row][col] = new Box(new Diamond(pos));
    }
    
    /**
     * Creates a new player on the given position.
     * @param row the row of the position
     * @param col the column of the position
     * @throws IllegalStateException if a player already exists
     */
    public void newPlayer(int row, int col) {
        if(!(player == null)) {
            throw new IllegalStateException("Il ne peut y avoir qu'un seul joueur.");
        }
        player = new Player(new Position(row, col));
        boxess[row][col] = new Box(player);
    }
    
    /**
     * Creates a new wall on the given position, save the position of the exit.
     * @param row the row of the position
     * @param col the column of the position
     * @throws IllegalStateException if an exit already exists
     */
    public void newExit(int row, int col) {
        if(!(exitPosition == null)) {
            throw new IllegalStateException("Il ne peut y avoir qu'une seule sortie.");
        }
        exitPosition = new Position(row, col);
        boxess[row][col] = new Box(new Wall(new Position(exitPosition)));
    }
    
    /**
     * Checks if there is a player.
     * @return true if there is a player, false otherwise
     */
    public boolean isPlayer() {
        return player != null;
    }
    
    /**
     * Checks if there is an exit.
     * @return true if there is an exit, false otherwise
     */
    public boolean isExit() {
        return exitPosition != null;
    }
    
    /**
     * Moves the player.
     * @param dRow the displacement of the row
     * @param dCol the displacement of the column
     * @return the moved content with their old positions
     */
    public Map<Content, Position> move(int dRow, int dCol) {
        isNextMove();
        notifyObserver();
        Map<Content, Position> movedContents = new HashMap();
        Position newPos = new Position(player.getPos());
        Position playerPos = new Position(player.getPos());
        int oldRow = player.getPos().getRow();
        int oldCol = player.getPos().getColumn();
        newPos.move(dRow, dCol);
        int row = newPos.getRow();
        int col = newPos.getColumn();
        Content content = boxess[row][col].getContent();
        boolean isDiamond = content.isDiamond();
        boolean isMovable = content.isMovable();
        boolean isWalkable = content.isWalkable();
        
        if(!inBoard(newPos)) {
            throw new IllegalArgumentException("Le déplacement est impossible.");
        } else if(isWalkable) {
            movedContents.put(content, content.getPos());
            moveContent(player, oldRow, oldCol, dRow, dCol);
            if(content.isExit()) {
                gameState = GameState.WON;
            }
            if(isDiamond) {
                incDiamond();
                if(nbCollectedDiamonds == nbDiamondToCollect) {
                    int eRow = exitPosition.getRow(), eCol = exitPosition.getColumn();
                    movedContents.put(boxess[eRow][eCol].getContent(), new Position(eRow, eCol));
                    boxess[eRow][eCol].setContent(new Exit(exitPosition));
                    gameState = GameState.EXITAPPEARED;
                }
            }
        } else if(isMovable) {
            Box emptyBox = boxess[row+dRow][col+dCol];
            boolean isNextEmpty = emptyBox.isEmpty();
            if(isNextEmpty && Math.abs(dRow) == 0) {
                movedContents.put(content, new Position(content.getPos()));
                movedContents.put(emptyBox.getContent(), emptyBox.getContent().getPos());
                moveContent(content, row, col, dRow, dCol);
                moveContent(player, oldRow, oldCol, dRow, dCol);
            } else {
                isImpossibleMove();
                notifyObserver();
            } 
        } else {
            isImpossibleMove();
            notifyObserver();
        }
        if(!movedContents.isEmpty()) movedContents.put(player, playerPos);
        isUnplayable();
        notifyObserver();
        return movedContents;
    }
    
    /**
     * Sets gameState to isnextmove.
     */
    private void isNextMove() {
        gameState = GameState.NEXT_MOVE;
    }
    
    /**
     * Sets gameState to impossiblemove.
     */
    private void isImpossibleMove() {
        gameState = GameState.IMPOSSIBLE_MOVE;
    }
    
    /**
     * Decrements the number of collected diamonds.
     */
    void decDiamond() {
        nbCollectedDiamonds--;
    }
    
    /**
     * Increments the number of collected diamonds.
     */
    void incDiamond() {
        nbCollectedDiamonds++;
    }
    
    /**
     * Falls fallable content.
     * @return a map of content with old position and current position of fallen contents
     */
    public Map<Content, Position> fall() {
        Map<Content, Position> fallenContents = new HashMap();
        isUnplayable();
forBreak:
        for(int row = boxess.length - 2; row > 0; row--) {
            for(int col = 1; col < boxess[0].length - 1; col++) {
                int tempRow = row;
                int tempCol = col;
                Content content = boxess[row][col].getContent();
                if(content.isFallable()) {
                    Position contentPos = new Position(row, col);
                    while(isEmpty(tempRow+1, tempCol)
                            || (!boxess[tempRow+1][tempCol].canCarryFallable()
                            && (isRightEmpty(tempRow, tempCol) || (isLeftEmpty(tempRow, tempCol))))) {
                        if(isEmpty(tempRow+1, tempCol)) {
                            moveContent(content, tempRow, tempCol, 1, 0);
                            tempRow++;
                        } else if(isRightEmpty(tempRow, tempCol)) {
                            moveContent(content, tempRow, tempCol, 0, 1);
                            tempCol++;
                        } else if(isLeftEmpty(tempRow, tempCol)) {
                            moveContent(content, tempRow, tempCol, 0, -1);
                            tempCol--;
                        }
                        if(boxess[tempRow+1][tempCol].isPlayer()) {
                            gameState = GameState.LOST;
                            break forBreak;
                        }
                        notifyObserver();
                    }
                    if(!contentPos.equals(content.getPos())) {
                        fallenContents.put(content, contentPos);
                    }
                }
            }
        }
        checkGameState();
        return fallenContents;
    }
    
    /**
     * Gives up the game.
     */
    public void giveUp() {
        gameState = GameState.LOST;
        notifyObserver();
    }
    
    /**
     * Move the content of a box (set this box empty) to another box.
     * @param content the content to move
     * @param row the row of the box
     * @param col the column of the box
     * @param dRow the displacement of the row
     * @param dCol the displacement of the column 
     */
    private void moveContent(Content content, int row, int col, int dRow, int dCol) {
        content.move(dRow, dCol);
        boxess[row+dRow][col+dCol].setContent(content);
        boxess[row][col].setContent(new Empty(new Position(row, col)));
    }
    
    /**
     * Checks if a box is empty.
     * @param row the row of the box
     * @param col the column of the box
     * @return true if the box is empty, false otherwise
     */
    private boolean isEmpty(int row, int col) {
        return boxess[row][col].isEmpty();
    }
    
    /**
     * Checks if the box left and the box left down a given box are empty.
     * @param row the row of the given box
     * @param col the column of the given box
     * @return true if the two boxes are empty, false otherwise
     */
    private boolean isLeftEmpty(int row, int col) {
        return isEmpty(row, col-1) && isEmpty(row+1, col-1);
    }
    
    /**
     * Checks if the box right and the box right down a given box are empty.
     * @param row the row of the given box
     * @param col the column of the given box
     * @return true if the two boxes are empty, false otherwise
     */
    private boolean isRightEmpty(int row, int col) {
        return isEmpty(row, col+1) && isEmpty(row+1, col+1);
    }
    
    /**
     * Getter of gameState.
     * @return the state of the game.
     */
    public GameState getGameState() {
        return gameState;
    }
    
    /**
     * Checks if a position is on the board.
     * @param pos the position to check
     * @return true if the position is on the board, false otherwise
     */
    private boolean inBoard(Position pos) {
        int row = pos.getRow();
        int col = pos.getColumn();
        return !(row < 0 || col < 0 || row >= boxess.length || col >= boxess[0].length);
    }

    @Override
    public String toString() {
        String board = "";
        for(Box[] boxes : boxess) {
            for(Box box : boxes) {
                board += box.toString();
                //board += " ";
            }
            board += "\n";
        }
        return board;
    }
   
    /**
     * Check and set the state of the game.
     */
    public void checkGameState() {
        if(gameState != GameState.LOST && gameState != GameState.WON) {
            if(isExitAppeared()) gameState = GameState.EXITAPPEARED;
            else gameState = GameState.INGAME;
        }
    }
    
    /**
     * Checks if the exit has appeared.
     * @return true if the exit has appeared, false otherwise
     */
    public boolean isExitAppeared() {
        int exitRow = exitPosition.getRow(), exitCol = exitPosition.getColumn();
        return boxess[exitRow][exitCol].getContent().isExit();
    }

    
    /**
     * Undoes the fall of a content.
     * @param content the content that falls
     * @param pos the previous position of the content
     */
    void setFallenContent(Content content, Position pos) {
        int row = content.getPos().getRow(), col = content.getPos().getColumn();
        newEmpty(new Position(row, col));
        setContent(content, pos);
    }
    
    /**
     * Undoes the movement of a content.
     * @param content the content that moved
     * @param pos the previous position of the content
     */
    void setMovedContent(Content content, Position pos) {
        int row = content.getPos().getRow(), col = content.getPos().getColumn();
        if(content == boxess[row][col].getContent()) newEmpty(new Position(row, col));
        setContent(content, pos);
        if(content.isDiamond()) decDiamond();
    }

    @Override
    public void registerObserver(Observer observer) {
        if (!observers.contains(observer)) {
            observers.add(observer);
        }

    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObserver() {
        for (Observer observer : observers) {
            observer.update();
        }

    }
    
    /**
     * Sets the game state to unplayable.
     */
    void isUnplayable() {
        if(gameState != GameState.LOST && gameState != GameState.WON)
            gameState = GameState.UNPLAYABLE;
    }
    
    /**
     * Gets the number of collected diamonds and the number of diamonds to collect.
     * @return a string with the number of collected diamonds and the number of diamonds to collect
     */
    public String getDiamonds() {
        String s = "";
        s += nbCollectedDiamonds;
        s += "/";
        s += nbDiamondToCollect;
        return s;
    }
    
    /**
     * Gets the number of collected diamonds.
     * @return the number of collected diamonds
     */
    public int getNbCollectedDiamonds() {
        return nbCollectedDiamonds;
    }
    
    /**
     * Gets the total number of diamonds.
     * @return the total number of diamonds
     */
    public int getNbDiamondTotal() {
        return nbDiamondTotal;
    }
    
    /**
     * Gets the number of diamonds to collect.
     * @return the number of diamond to collect
     */
    public int getNbDiamondToCollect() {
        return nbDiamondToCollect;
    }
    
    /**
     * Getter of player.
     * @return the player
     */
    Player getPlayer() {
        return player;
    }
    
    /**
     * Gets the content of a box.
     * @param row the row of the box
     * @param col the col of the box
     * @return the content of the box
     */
    Content getContent(int row, int col) {
        return boxess[row][col].getContent();
    }
}
