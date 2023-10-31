/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package g58990.boulderdash.model;

/**
 * Defines an empty box.
 * @author jp
 */
public class Empty extends Content{

    /**
     * Constructor of Empty.
     * @param pos the position of the empty content
     */
    public Empty(Position pos) {
        super(pos);
    }

    @Override
    public String toString() {
        return " ";
    }

    @Override
    public boolean isEmpty() {
        return true;
    }
    
    
}
