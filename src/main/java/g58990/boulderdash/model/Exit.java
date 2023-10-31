/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package g58990.boulderdash.model;

/**
 * Defines an exit.
 * @author jp
 */
public class Exit extends Content {

    /**
     * Constructor of Exit.
     * @param pos the position of the exit
     */
    public Exit(Position pos) {
        super(pos);
    }

    @Override
    public boolean isExit() {
        return true;
    }

    @Override
    public String toString() {
        return "E";
    }
    
    
}
