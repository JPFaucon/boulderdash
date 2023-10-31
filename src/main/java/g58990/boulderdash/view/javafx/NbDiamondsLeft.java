/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package g58990.boulderdash.view.javafx;

import g58990.boulderdash.model.Board;
import g58990.boulderdash.oo.Observable;
import g58990.boulderdash.oo.Observer;
import javafx.scene.control.Label;

/**
 *
 * @author jp
 */
public class NbDiamondsLeft extends Label implements Observer {
    private Board board;
    private int nbDiamondsOnBoard;
    
    public NbDiamondsLeft(Board board) {
        this.board = board;
        board.registerObserver(this);
        nbDiamondsOnBoard = board.getNbDiamondTotal();
        this.setText("" + nbDiamondsOnBoard);
    }

    @Override
    public void update() {
        nbDiamondsOnBoard = board.getNbDiamondTotal() - board.getNbCollectedDiamonds();
        this.setText("" + nbDiamondsOnBoard);
    }

    @Override
    public void setObservable(Observable observable) {
    }
}
