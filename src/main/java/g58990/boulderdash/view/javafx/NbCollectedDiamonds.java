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
public class NbCollectedDiamonds extends Label implements Observer {
    private int collectedDiamond;
    private Board board;
    
    public NbCollectedDiamonds(Board board) {
        this.board = board;
        this.collectedDiamond = board.getNbCollectedDiamonds();
        this.setText("" + collectedDiamond);
        board.registerObserver(this);
    }

    @Override
    public void update() {
        this.collectedDiamond = board.getNbCollectedDiamonds();
        this.setText("" + collectedDiamond);
    }

    @Override
    public void setObservable(Observable observable) {
    }
}
