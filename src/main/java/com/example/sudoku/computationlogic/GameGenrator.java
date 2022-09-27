package com.example.sudoku.computationlogic;




import com.example.sudoku.problemdomain.Coordinates;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.example.sudoku.problemdomain.SudokuGame.GRID_BOUNDARY;


public class GameGenrator {


    public static int[][] getNewGameGrid(){
        return unSolvedGame(getSolvedGame());
    }

    private static int[][] unSolvedGame(int[][] solvedGame) {
        Random random = new Random(System.currentTimeMillis());

        boolean solvable = false;
        int[][] solveableArray = new int[GRID_BOUNDARY][GRID_BOUNDARY];

        while(solvable == false){
            SudokuUtilities.copySudokuArrayValues(solvedGame, solveableArray);

            int index =0;

            while(index < 40){
                int xCoordinate = random.nextInt(GRID_BOUNDARY);
                int yCoordinate = random.nextInt(GRID_BOUNDARY);

                if(solveableArray[xCoordinate][yCoordinate] != 0){
                    solveableArray[xCoordinate][yCoordinate] = 0;
                    index++;
                }
            }
            int[][] toBeSolved = new int[GRID_BOUNDARY][GRID_BOUNDARY];

            SudokuUtilities.copySudokuArrayValues(solveableArray, toBeSolved);

            solvable = SudokuSolver.puzzleSolvable(toBeSolved);
        }
        return solveableArray;
    }

    private static int[][]getSolvedGame(){
        Random random = new Random(System.currentTimeMillis());
        int[][] newGrid = new int[GRID_BOUNDARY][GRID_BOUNDARY];

        for(int value = 1; value <= GRID_BOUNDARY; value++) {
            int allcocation = 0;
            int interrupt = 0;

            List<Coordinates> allocaTracker = new ArrayList<>();

            int attempt = 0;

            while (allcocation < GRID_BOUNDARY) {
                if (interrupt > 200) {
                    allocaTracker.forEach(coord -> {
                        newGrid[coord.getX()][coord.getY()] = 0;
                    });

                    interrupt = 0;
                    allcocation = 0;
                    allocaTracker.clear();
                    attempt++;

                    if (attempt > 500) {
                        clearArray(newGrid);
                        attempt = 0;
                        value = 1;
                    }
                }
                int xCoordinate = random.nextInt(GRID_BOUNDARY);
                int yCoordinate = random.nextInt(GRID_BOUNDARY);

                if (newGrid[xCoordinate][yCoordinate] == 0) {
                    newGrid[xCoordinate][yCoordinate] = value;

                    if (GameLogic.SudokuIsInvalid(newGrid)) {
                        newGrid[xCoordinate][yCoordinate] = 0;
                        interrupt++;
                    } else {
                        allocaTracker.add(new Coordinates(xCoordinate, yCoordinate));
                        allcocation++;
                    }
                }
            }
        }
            return newGrid;
    }

    private static void clearArray(int[][] newGrid) {

        for (int xIndex = 0; xIndex < GRID_BOUNDARY; xIndex++ ) {
            for (int yIndex = 0; yIndex < GRID_BOUNDARY; yIndex++) {

                newGrid[xIndex][yIndex]=0;

            }
        }
    }
}
