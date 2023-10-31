/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package g58990.boulderdash.model;

/**
 * Defines a content.
 * @author jp
 */
public abstract class Content {
    private Position pos;
    
    /**
     * Constructor of Content.
     * @param pos the position of the content
     */
    public Content(Position pos) {
        this.pos = pos;
    }
    
    /**
     * Getter of pos.
     * @return the position of the content
     */
    public Position getPos() {
        return pos;
    }
    
    /**
     * Moves the content.
     * @param dRow the displacement of the row
     * @param dCol the displacement of the col
     * @return true if the content is moving
     * @throws IllegalArgumentException if the content isn't movable or if the 
     * movement is impossible
     */
    public boolean move(int dRow, int dCol) {
        if(this.isMovable()) {
            if((Math.abs(dRow) == 1 && dCol == 0) || (Math.abs(dCol) == 1 && dRow == 0)) {
                pos.move(dRow, dCol);
                return true;
            }
        } 
        throw new IllegalArgumentException("Mouvement impossible !");
    }
    
    /**
     * Set the position.
     * @param row the row of the position
     * @param col the column of the position
     */
    void setPosition(int row, int col) {
        pos.setPosition(row, col);
    }
    
    /**
     * Checks if the content is walkable.
     * @return true if the content is walkable, false otherwise
     */
    public boolean isWalkable() {
        return true;
    }
    
    /**
     * Checks if the content is movable.
     * @return true if the content is movable, false otherwise
     */
    public boolean isMovable() {
        return false;
    }
    
    /**
     * Checks if the content can fall.
     * @return true if the content can fall, false otherwise
     */
    public boolean isFallable() {
        return false;
    }
    
    /**
     * Checks if the content is empty.
     * @return true if the content is empty, false otherwise
     */
    public boolean isEmpty() {
        return false;
    }
    
    /**
     * Checks if the content is a diamond.
     * @return true if the content is a diamond, false otherwise
     */
    public boolean isDiamond() {
        return false;
    }
    
    /**
     * Checks if the content is the player.
     * @return true if the content is the player, false otherwise
     */
    public boolean isPlayer() {
        return false;
    }
    
    /**
     * Checks if the content is a ground.
     * @return true if the content is a ground, false otherwise
     */
    public boolean isGround() {
        return false;
    }
    
    /**
     * Checks if the content is the exit.
     * @return true if the content is the exit, false otherwise
     */
    public boolean isExit() {
        return false;
    }
    
    /**
     * Checks if the content can carry a fallable content.
     * @return true if the content can carry a fallable content, false otherwise
     */
    public boolean canCarryFallable() {
        return false;
    }
}
