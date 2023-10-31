/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package g58990.boulderdash.model;

/**
 * Defines a player.
 * @author jp
 */
public class Player extends Content {
    
    /**
     * Constructor of Player.
     * @param pos the position of the player
     */
    public Player(Position pos) {
        super(pos);
    }

    @Override
    public boolean isPlayer() {
        return true;
    }

    @Override
    public boolean canCarryFallable() {
        return true;
    }

    @Override
    public String toString() {
        return "P";
    }

    @Override
    public boolean isMovable() {
        return true;
    }
}
