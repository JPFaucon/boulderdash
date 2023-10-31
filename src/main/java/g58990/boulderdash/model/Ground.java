/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package g58990.boulderdash.model;

/**
 * Defines a ground.
 * @author jp
 */
public class Ground extends Content {

    /**
     * Constructor of Ground.
     * @param pos the position of the ground
     */
    public Ground(Position pos) {
        super(pos);
    }

    @Override
    public String toString() {
        return "*";
    }

    @Override
    public boolean isGround() {
        return true;
    }

    @Override
    public boolean canCarryFallable() {
        return true;
    }
    
    
}
