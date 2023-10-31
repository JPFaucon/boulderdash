/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package g58990.boulderdash.model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;
import static g58990.boulderdash.util.fileReader.newBoard;
import java.io.IOException;

/**
 *
 * @author jp
 */
public class BoardTest {
    private Board board;
    private Player player;

    @Test
    public void testMoveInGround() {
        try {
            board = newBoard("level1.txt");
            player = board.getPlayer();
        } catch(IOException e) {
            System.out.println("Raté");
        }
        Position posPlayer = player.getPos();
        Position previousPosPlayer = new Position(posPlayer);
        board.move(-1, 0);
        previousPosPlayer.move(-1, 0);
        assertEquals(posPlayer, previousPosPlayer);
    }
    
    @Test
    public void testMoveInBoulderNotEmptyBehind() {
        try {
            board = newBoard("level1.txt");
            player = board.getPlayer();
        } catch(IOException e) {
            System.out.println("Raté");
        }
        Position posPlayer = player.getPos();
        Position previousPosPlayer = new Position(posPlayer);
        board.move(0, 1);
        assertEquals(posPlayer, previousPosPlayer);
    }
    
    @Test
    public void testMoveInWall() {
        try {
            board = newBoard("level1.txt");
            player = board.getPlayer();
        } catch(IOException e) {
            System.out.println("Raté");
        }
        Position posPlayer = player.getPos();
        posPlayer.setPosition(2, 1);
        Position previousPosPlayer = new Position(posPlayer);
        board.move(0, -1);
        assertEquals(posPlayer, previousPosPlayer);
    }
    
    @Test
    public void testMoveInBoulderEmptyBehindHorizontal() {
        try {
            board = newBoard("level1.txt");
            player = board.getPlayer();
        } catch(IOException e) {
            System.out.println("Raté");
        }
        Position posPlayer = player.getPos();
        posPlayer.setPosition(5, 5);
        Position previousPosPlayer = new Position(posPlayer);
        board.move(0, -1);
        previousPosPlayer.move(0, -1);
        assertEquals(posPlayer, previousPosPlayer);
    }
    
    @Test
    public void testMoveInBoulderEmptyBehindVertical() {
        try {
            board = newBoard("level1.txt");
            player = board.getPlayer();
        } catch(IOException e) {
            System.out.println("Raté");
        }
        Position posPlayer = player.getPos();
        posPlayer.setPosition(3, 3);
        Position previousPosPlayer = new Position(posPlayer);
        board.move(1, 0);
        previousPosPlayer.move(0, 0);
        assertEquals(posPlayer, previousPosPlayer);
    }
    
    @Test
    public void testMoveInDiamond() {
        try {
            board = newBoard("level1.txt");
            player = board.getPlayer();
        } catch(IOException e) {
            System.out.println("Raté");
        }
        Position posPlayer = player.getPos();
        posPlayer.setPosition(1, 9);
        Position previousPosPlayer = new Position(posPlayer);
        int diamonds = board.getNbCollectedDiamonds();
        board.move(0, 1);
        previousPosPlayer.move(0, 1);
        assertEquals(posPlayer, previousPosPlayer);
        assertEquals(board.getNbCollectedDiamonds(), diamonds+1);
    }
    
    @Test
    public void testMoveInEmpty() {
        try {
            board = newBoard("level1.txt");
            player = board.getPlayer();
        } catch(IOException e) {
            System.out.println("Raté");
        }
        Position posPlayer = player.getPos();
        posPlayer.setPosition(1, 7);
        Position previousPosPlayer = new Position(posPlayer);
        board.move(0, 1);
        previousPosPlayer.move(0, 1);
        assertEquals(posPlayer, previousPosPlayer);
    }
    
    public void testFallDontHaveToFall() {
        try {
            board = newBoard("level1.txt");
            player = board.getPlayer();
        } catch(IOException e) {
            System.out.println("Raté");
        }
        Position posBoulder = board.getContent(2, 2).getPos();
        Position previousPosBoulder = new Position(posBoulder);
        board.fall();
        assertEquals(posBoulder, previousPosBoulder);
    }
    
    public void testFallHaveToFall() {
        try {
            board = newBoard("level1.txt");
            player = board.getPlayer();
        } catch(IOException e) {
            System.out.println("Raté");
        }
        Position posBoulder = board.getContent(4, 3).getPos();
        board.fall();
        Position expectedPosBoulder = new Position(5,3);
        assertEquals(posBoulder, expectedPosBoulder);
    }
    
    public void testFallHaveToFallBoulderUnder() {
        try {
            board = newBoard("level1.txt");
            player = board.getPlayer();
        } catch(IOException e) {
            System.out.println("Raté");
        }
        Position posBoulder = board.getContent(4, 4).getPos();
        board.fall();
        Position expectedPosBoulder = new Position(5,4);
        assertEquals(posBoulder, expectedPosBoulder);
    }
    
    public void testFallHaveToFallOnSide() {
        try {
            board = newBoard("level1.txt");
            player = board.getPlayer();
        } catch(IOException e) {
            System.out.println("Raté");
        }
        board.newEmpty(5, 2);
        board.newEmpty(4, 2);
        Position posBoulder = board.getContent(4, 1).getPos();
        board.fall();
        Position expectedPosBoulder = new Position(5,2);
        assertEquals(posBoulder, expectedPosBoulder);
    }
    
    public void testFallHaveToFallMoreThanOneSquare() {
        try {
            board = newBoard("level1.txt");
            player = board.getPlayer();
        } catch(IOException e) {
            System.out.println("Raté");
        }
        board.newEmpty(3, 2);
        board.newEmpty(4, 2);
        Position posBoulder = board.getContent(2, 2).getPos();
        board.fall();
        Position expectedPosBoulder = new Position(4,2);
        assertEquals(posBoulder, expectedPosBoulder);
    }
    
    public void testFallDiamond() {
        try {
            board = newBoard("level1.txt");
            player = board.getPlayer();
        } catch(IOException e) {
            System.out.println("Raté");
        }
        Position posBoulder = board.getContent(8, 30).getPos();
        board.fall();
        Position expectedPosBoulder = new Position(9,30);
        assertEquals(posBoulder, expectedPosBoulder);
    }
}
