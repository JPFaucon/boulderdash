/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package g58990.boulderdash.util;

import g58990.boulderdash.model.Board;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

/**
 *
 * @author jp
 */
public class fileReader {
    /**
    * Reads given resource file as a string.
    *
    * @param fileName path to the resource file
    * @return the file's contents
    * @throws IOException if read fails for any reason
    */
    public static String getResourceFileAsString(String fileName) throws IOException {
        ClassLoader classLoader = ClassLoader.getSystemClassLoader();
        try (InputStream is = classLoader.getResourceAsStream("levels/" + fileName)) {
            if (is == null) throw new IOException("Le fichier n'existe pas.");
            try (InputStreamReader isr = new InputStreamReader(is);
                BufferedReader reader = new BufferedReader(isr)) {
                return reader.lines().collect(Collectors.joining(System.lineSeparator()));
            }
        }
    }
    
    /**
     * Creates a new board with the information of a file.
     * @param fileName the content of the file
     * @return the new board
     * @throws IOException if read fails for any reason
     * @throws IllegalStateException if the content of the file can't work to 
     * create the board
     * @throws IllegalArgumentException if there is an unknown character or if 
     * the number of diamond to collect isn't a integer
     */
    public static Board newBoard(String fileName) throws IOException,
            IllegalStateException, IllegalArgumentException {
        String level = getResourceFileAsString(fileName);
        String[] lines = level.split("\r\n|\r|\n");
        int nRows = lines.length - 1, nColumns = lines[1].length();
        String[][] letters = new String[nRows][nColumns];
        String[] diamonds = lines[0].split(" ");
        if(!isInteger(diamonds[0]) || !isInteger(diamonds[1])) {
            throw new IllegalArgumentException("Le nombre de diamants à "
                    + "collecter ou le nombre total de diamants n'est pas un entier.");
        }
        int nbDiamondToCollect = Integer.parseInt(diamonds[0]);
        int nbDiamondTotal = Integer.parseInt(diamonds[1]);
        for(int row = 0; row < nRows; row++) {
            letters[row] = lines[row+1].split("");
        }
        Board board = new Board(nbDiamondToCollect, nbDiamondTotal, nRows, nColumns);
        for(int row = 0; row < nRows; row++) {
            for(int col = 0; col < nColumns; col++) {
                switch(letters[row][col]) {
                    case "w" -> board.newWall(row, col);
                    case "W" -> board.newWall(row, col);
                    case "." -> board.newGround(row, col);
                    case " " -> board.newEmpty(row, col);
                    case "r" -> board.newBoulder(row, col);
                    case "d" -> board.newDiamond(row, col);
                    case "X" -> board.newPlayer(row, col);
                    case "P" -> board.newExit(row, col);
                    default -> throw new IllegalArgumentException("Un des caractère est inconnu.");
                }
            }
        }
        if(!board.isExit()) {
            throw new IllegalStateException("Il doit y avoir une sortie.");
        }
        if(!board.isPlayer()) {
            throw new IllegalStateException("Il doit y avoir un joueur.");
        }
        return board;
    }
    
    /**
     * Checks if a string is an integer.
     * @param str the string to check
     * @return true if the string is an integer, false otherwise
     */
    public static boolean isInteger(String str) { 
        try {  
            Integer.valueOf(str); 
            return true;
        } catch(NumberFormatException e){  
            return false;  
        }  
    }
}
