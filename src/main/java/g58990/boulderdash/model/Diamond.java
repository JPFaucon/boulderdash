/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package g58990.boulderdash.model;

/**
 * Defines a diamond.
 * @author jp
 */
public class Diamond extends Content {

    /**
     * Constructor of Diamond.
     * @param pos the position of the diamond
     */
    public Diamond(Position pos) {
        super(pos);
    }

    @Override
    public String toString() {
        return "D";
    }

    @Override
    public boolean isDiamond() {
        return true;
    }

    @Override
    public boolean isFallable() {
        return true;
    }

    @Override
    public boolean isMovable() {
        return true;
    }
    
    
}
