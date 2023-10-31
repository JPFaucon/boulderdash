/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package g58990.boulderdash.view.javafx;

import g58990.boulderdash.controller.BoulderDash;
import g58990.boulderdash.model.Board;
import g58990.boulderdash.oo.Observable;
import g58990.boulderdash.oo.Observer;
import javafx.scene.layout.GridPane;

/**
 *
 * @author jp
 */
public class GameBoard extends GridPane implements Observer {
    private Board board;
    
    public GameBoard(String[][] boardString, Board board) {
        int squareDim = 16;
        this.board = board;
        board.registerObserver(this);
        for(int row = 0; row < boardString.length; row++) {
            for(int col = 0; col < boardString[0].length; col++) {
                Square square = new Square(boardString[row][col], col, row, squareDim, board.getBox(row, col));
                this.add(square, col*squareDim, row*squareDim);
            }
        }
    }

    @Override
    public void update() {
    }

    @Override
    public void setObservable(Observable observable) {
        if(observable instanceof Board) {
            this.board = (Board)observable;
        }
        
    }
}
