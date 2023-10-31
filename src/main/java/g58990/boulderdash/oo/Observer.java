/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package g58990.boulderdash.oo;

/**
 *
 * @author jp
 */
public interface Observer {
    /**
     * This method is called whenever the observed object has changed.
     */
    public void update();

    /**
     * Sets an observable.
     * @param observable the observable to set
     */
    public void setObservable(Observable observable);
    
}
