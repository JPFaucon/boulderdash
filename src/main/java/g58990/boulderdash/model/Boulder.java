/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package g58990.boulderdash.model;

/**
 * Defines a boulder.
 * @author jp
 */
public class Boulder extends Content {

    /**
     * Constructor of boulder.
     * @param pos the position of the boulder
     */
    public Boulder(Position pos) {
        super(pos);
    }

    @Override
    public String toString() {
        return "O";
    }

    @Override
    public boolean isMovable() {
        return true;
    }

    @Override
    public boolean isWalkable() {
        return false;
    }

    @Override
    public boolean isFallable() {
        return true;
    }
}
