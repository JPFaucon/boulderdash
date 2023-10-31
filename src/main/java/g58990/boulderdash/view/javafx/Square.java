/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package g58990.boulderdash.view.javafx;

import g58990.boulderdash.model.Boulder;
import g58990.boulderdash.model.Box;
import g58990.boulderdash.model.Diamond;
import g58990.boulderdash.model.Empty;
import g58990.boulderdash.model.Exit;
import g58990.boulderdash.model.Ground;
import g58990.boulderdash.model.Player;
import g58990.boulderdash.model.Wall;
import g58990.boulderdash.oo.Observable;
import g58990.boulderdash.oo.Observer;
import java.net.URL;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author jp
 */
public class Square extends Rectangle implements Observer {
    private Box box;
    
    public Square(String s, int x, int y, int squareDim, Box box) {
        URL url = null;
        switch (s) {
            case "/" -> url = getClass().getResource("/wall.png");
            case "*" -> url = getClass().getResource("/ground.png");
            case "P" -> url = getClass().getResource("/player_down.png");
            case "D" -> url = getClass().getResource("/diamond.png");
            case "O" -> url = getClass().getResource("/rock.png");
            case " " -> url = getClass().getResource("/empty.png");
            case "E" -> url = getClass().getResource("/exit.png");
        }
        if(url == null) {
            throw new IllegalArgumentException("Caractère inexistant");
        }
        try {
            Image img = new Image(url.toString());
            this.setFill(new ImagePattern(img));
            this.box = box;
            box.registerObserver(this);
            this.setX(x*squareDim);
            this.setY(y*squareDim);
        } catch(IllegalArgumentException e) {
            System.out.println("Nom d'image inexistant...");
        }
        
        
        this.setWidth(squareDim);
        this.setHeight(squareDim);
    }

    @Override
    public void update() {
        URL url = null;
        if(box.getContent() instanceof Wall) url = getClass().getResource("/wall.png");
        if(box.getContent() instanceof Ground) url = getClass().getResource("/ground.png");
        if(box.getContent() instanceof Player) url = getClass().getResource("/player_down.png");
        if(box.getContent() instanceof Diamond) url = getClass().getResource("/diamond.png");
        if(box.getContent() instanceof Boulder) url = getClass().getResource("/rock.png");
        if(box.getContent() instanceof Empty) url = getClass().getResource("/empty.png");
        if(box.getContent() instanceof Exit) url = getClass().getResource("/exit.png");
        if(url == null) {
            throw new IllegalArgumentException("Caractère inexistant");
        }
        Image img = new Image(url.toString());
        this.setFill(new ImagePattern(img));
    }

    @Override
    public void setObservable(Observable observable) {
        if(observable instanceof Box) {
            box = (Box)observable;
        }
        
    }
}