/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package g58990.boulderdash.oo;

/**
 * Defines observable.
 * @author jp
 */
public interface Observable {
    /**
     * Register a new observer.
     * @param observer the new observer
     */
    public void registerObserver(Observer observer);
    /**
     * Remove an observer.
     * @param observer the observer to remove
     */
    public void removeObserver(Observer observer);
    /**
     * Notify the observer.
     */
    public void notifyObserver();
}
