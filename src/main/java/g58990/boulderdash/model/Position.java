/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package g58990.boulderdash.model;

/**
 * Defines a position.
 * @author jp
 */
public class Position {
    private int row;
    private int column;
    
    /**
     * Constructor of Position.
     * @param row the row of the position
     * @param column the column of the position
     */
    public Position(int row, int column) {
        this.row = row;
        this.column = column;
    }
    
    /**
     * Constructor of Position.
     * @param pos the position to copy
     */
    public Position(Position pos) {
        row = pos.row;
        column = pos.column;
    }
    
    /**
     * Moves the position.
     * @param deltaRow the displacement of row
     * @param deltaColumn the displacement of column
     */
    public void move(int deltaRow, int deltaColumn) {
        row += deltaRow;
        column += deltaColumn;
    }
    
    /**
     * Set the position.
     * @param row the row of the position
     * @param col the column of the position
     */
    void setPosition(int row, int col) {
        this.row = row;
        this.column = col;
    }
    
    /**
     * Getter of row.
     * @return the row of the position
     */
    public int getRow() {
        return row;
    }
    
    /**
     * Getter of column.
     * @return the column of the position
     */
    public int getColumn() {
        return column;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Position other = (Position) obj;
        if (this.row != other.row) {
            return false;
        }
        return this.column == other.column;
    }

    @Override
    public String toString() {
        return "Position{" + "row=" + row + ", column=" + column + '}';
    }
    
    
}
