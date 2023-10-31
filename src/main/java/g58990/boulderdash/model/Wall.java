/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package g58990.boulderdash.model;

/**
 * Defines a wall.
 * @author jp
 */
public class Wall extends Content {

    /**
     * Constructor of Wall.
     * @param pos the position of the wall
     */
    public Wall(Position pos) {
        super(pos);
    }

    @Override
    public String toString() {
        return "/";
    }

    @Override
    public boolean isWalkable() {
        return false; 
    }
    
    
}
