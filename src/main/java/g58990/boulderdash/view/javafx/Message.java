/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package g58990.boulderdash.view.javafx;

import g58990.boulderdash.model.Board;
import g58990.boulderdash.model.GameState;
import g58990.boulderdash.oo.Observable;
import g58990.boulderdash.oo.Observer;
import javafx.geometry.Pos;
import javafx.scene.control.Label;

/**
 *
 * @author jp
 */
public class Message extends Label implements Observer {
    private final Board board;
    
    public Message(Board board) {
        this.board = board;
        board.registerObserver(this);
    }

    @Override
    public void update() {
        GameState gameState = board.getGameState();
        String message = "";
        String[] messages = this.getText().split("\n");
        message += messages[0];
        switch(gameState) {
            case LOST -> {
                this.setText("Vous avez perdu la partie.");
                return;
            }
            case WON -> {
                this.setText("Vous avez gagné !");
                return;
            }
            case IMPOSSIBLE_MOVE -> message = ("Le mouvement est impossible.");
            case NEXT_MOVE -> message = ("");
            case EXITAPPEARED -> message += "\nLa sortie est apparue, rejoignez-la pour gagner le niveau.";
        }
        this.setText(message);
        this.setAlignment(Pos.CENTER);
    }

    @Override
    public void setObservable(Observable observable) {
    }
    
}
