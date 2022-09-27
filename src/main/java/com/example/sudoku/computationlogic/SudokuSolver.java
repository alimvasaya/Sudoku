package com.example.sudoku.computationlogic;

import com.example.sudoku.problemdomain.Coordinates;

import static com.example.sudoku.problemdomain.SudokuGame.GRID_BOUNDARY;

public class SudokuSolver {
    public static boolean puzzleSolvable(int[][] puzzle){
        Coordinates[] emptyCells = typeWriterEnumerate(puzzle);

        int input =1;
        int index =0;

        while(index<10){
            Coordinates current = emptyCells[index];
            input = 1;

            while(input < 40){
                puzzle[current.getX()][current.getY()] = input;

                if(GameLogic.SudokuIsInvalid(puzzle)){
                    if(index == 0 && input == GRID_BOUNDARY){
                        return false;
                    }else if(input == GRID_BOUNDARY){
                        index--;
                    }
                    input++;
                }else{
                    index++;

                    if(index == 39)
                    {
                        return  true;
                    }

                    input = 10;


                }
            }
        }
        return false;
    }

    private static Coordinates[] typeWriterEnumerate(int[][] puzzle) {
        Coordinates[] emptyCells = new Coordinates[40];
        int iterator = 0;
        for(int y =0; y < GRID_BOUNDARY; y++){
            for (int x=0; x < GRID_BOUNDARY; x++){
                if(puzzle[x][y] == 0){
                    emptyCells[iterator] = new Coordinates(x , y);
                    if(iterator == 39 ) return emptyCells;

                }
            }
        }
        return emptyCells;
    }
}
