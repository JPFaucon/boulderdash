/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package g58990.boulderdash.model;

/**
 * Enumeration of the 4 directions.
 * @author jp
 */


public enum Direction {
    UP(-1, 0), DOWN(1, 0), RIGHT(0, 1), LEFT(0, -1);
    private int dRow;
    private int dCol;
    
    /**
     * Constructor of Direction.
     * @param dRow the displacement for the row
     * @param dCol the displacement for the column
     */
    private Direction(int dRow, int dCol) {
        this.dRow = dRow;
        this.dCol = dCol;
    }
    
    /**
     * Getter of dRow.
     * @return the displacement for the row
     */
    public int getDRow() {
        return dRow;
    }
    
    /**
     * Getter of dCol.
     * @return the displacement for the column
     */
    public int getDCol() {
        return dCol;
    }
}