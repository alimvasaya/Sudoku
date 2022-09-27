package com.example.sudoku.computationlogic;

import com.example.sudoku.constants.GameState;
import com.example.sudoku.constants.Rows;
import com.example.sudoku.problemdomain.SudokuGame;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.example.sudoku.problemdomain.SudokuGame.GRID_BOUNDARY;

public class GameLogic {
    public static SudokuGame getNewGame(){
        return new SudokuGame(
                GameState.NEW,
                GameGenrator.getNewGameGrid()
        );
    }
    public static GameState checkForCompletion(int[][] grid){
        if(SudokuIsInvalid(grid)) return GameState.ACTIVE;
        if(tilesAreNotfilled(grid)) return GameState.ACTIVE;

        return GameState.COMPLETE;
    }

    public static boolean tilesAreNotfilled(int[][] grid) {
        for (int xIndex = 0; xIndex < GRID_BOUNDARY; xIndex++) {
            for (int yIndex = 0; yIndex < GRID_BOUNDARY; yIndex++) {
                if (grid[xIndex][yIndex] == 0) return true;
            }
        }
        return false;
    }

    static boolean SudokuIsInvalid(int[][] grid) {
        if(rowsAreInvalid(grid))return true;
        if(columnsAreInvalid(grid))return true;
        if(squaresAreInvalid(grid))return true;
        else return false;
    }

    private static boolean squaresAreInvalid(int[][] grid) {
        if(rowsofSquaresIsInvalid(Rows.TOP, grid)) return true;

        if(rowsofSquaresIsInvalid(Rows.MIDDLE, grid)) return true;

        if(rowsofSquaresIsInvalid(Rows.BOTTOM, grid)) return true;

        return false;

    }

    private static boolean rowsofSquaresIsInvalid(Rows value, int[][] grid) {
        switch (value) {
            case TOP:
                //x FIRST = 0
                if (squareIsInvalid(0, 0, grid)) return true;
                //x SECOND = 3
                if (squareIsInvalid(0, 3, grid)) return true;
                //x THIRD = 6
                if (squareIsInvalid(0, 6, grid)) return true;

                //Otherwise squares appear to be valid
                return false;

            case MIDDLE:
                if (squareIsInvalid(3, 0, grid)) return true;
                if (squareIsInvalid(3, 3, grid)) return true;
                if (squareIsInvalid(3, 6, grid)) return true;
                return false;

            case BOTTOM:
                if (squareIsInvalid(6, 0, grid)) return true;
                if (squareIsInvalid(6, 3, grid)) return true;
                if (squareIsInvalid(6, 6, grid)) return true;
                return false;

            default:
                return false;
        }
    }
    private static boolean squareIsInvalid(int xIndex, int yIndex, int[][] grid) {

        int yIndexEnd = yIndex + 3;
        int xIndexEnd = xIndex + 3;

        List<Integer> square = new ArrayList<>();

        while (yIndex < yIndexEnd) {

            while (xIndex < xIndexEnd) {
                square.add(
                        grid[xIndex][yIndex]
                );
                xIndex++;
            }

            //reset x to original value by subtracting by 2
            xIndex -= 3;

            yIndex++;
        }

        //if square has repeats, return true
        if (collectionHasRepeats(square)) return true;
        return false;
    }
    public static boolean columnsAreInvalid(int[][] grid) {
        for (int xIndex = 0; xIndex < GRID_BOUNDARY; xIndex++) {
            List<Integer> row = new ArrayList<>();
            for (int yIndex = 0; yIndex < GRID_BOUNDARY; yIndex++) {
                row.add(grid[xIndex][yIndex]);
            }

            if (collectionHasRepeats(row)) return true;
        }

        return false;
    }

    public static boolean rowsAreInvalid(int[][] grid) {
        for (int yIndex = 0; yIndex < GRID_BOUNDARY; yIndex++) {
            List<Integer> row = new ArrayList<>();
            for (int xIndex = 0; xIndex < GRID_BOUNDARY; xIndex++) {
                row.add(grid[xIndex][yIndex]);
            }

            if (collectionHasRepeats(row)) return true;
        }

        return false;
    }
    public static boolean collectionHasRepeats(List<Integer> collection) {
        for (int index =1; index<= GRID_BOUNDARY; index++){
            if (Collections.frequency(collection, index) > 1) return true;
        }
        return false;
    }
}
