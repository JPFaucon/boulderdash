/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package g58990.boulderdash.model;

/**
 * Command interface.
 * @author jp
 */
public interface Command {
    /**
     * Executes the specified command.
     */
    public void execute();
    
    /**
     * Undoes the specified command.
     */
    public void undo();
}
