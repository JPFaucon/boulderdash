/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package g58990.boulderdash.model;

import g58990.boulderdash.oo.Observable;
import g58990.boulderdash.oo.Observer;
import java.util.ArrayList;
import java.util.List;

/**
 * Defines a box.
 * @author jp
 */
public class Box implements Observable {
    private Content content;
    private final List<Observer> observers;
    
    /**
     * Constructor of Box.
     * @param content the content of the box
     */
    public Box(Content content) {
        this.observers = new ArrayList();
        this.content = content;
    }
    
    /**
     * Constructor of box.
     */
    public Box() {
        this.observers = new ArrayList();
        this.content = null;
    }
    
    /**
     * Getter of content.
     * @return the content of the box
     */
    public Content getContent() {
        return this.content;
    }
    
    /**
     * Checks if the box is empty.
     * @return true if the box is empty, false otherwise
     */
    boolean isEmpty() {
        return content.isEmpty();
    }
    
    /**
     * Checks if the content is the player.
     * @return true if the content is the player, false otherwise
     */
    boolean isPlayer() {
        return content.isPlayer();
    }
    
    /**
     * Setter of content.
     * @param content the content to set
     */
    void setContent(Content content) {
        this.content = content;
        notifyObserver();
    }
    
    /**
     * Checks if the content is a ground.
     * @return true if the content is a ground, false otherwise
     */
    boolean isGround() {
        return content.isGround();
    }
    
    /**
     * Checks if the content can carry a fallable content.
     * @return true if the content can carry a fallable content, false otherwise
     */
    boolean canCarryFallable() {
        return content.canCarryFallable();
    }

    @Override
    public String toString() {
        return content.toString();
    }

    @Override
    public void registerObserver(Observer observer) {
        if (!observers.contains(observer)) {
            observers.add(observer);
        }    
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObserver() {
        for (Observer observer : observers) {
            observer.update();
        }

    }
    
    
}
