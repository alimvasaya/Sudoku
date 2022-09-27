package com.example.sudoku;

import com.example.sudoku.buildLogic.SudokuBuildLogic;
import com.example.sudoku.userinterface.IUserInterfaceContract;
import com.example.sudoku.userinterface.UserInterfaceImpl;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

public class SudokuApplication extends Application {
    private  IUserInterfaceContract.View uiImpl;

    @Override
    public void start(Stage stage) throws IOException {
        Stage primaryStage = new Stage();
        uiImpl = new UserInterfaceImpl(primaryStage);
        try{
            SudokuBuildLogic.build(uiImpl);

        }catch (IOException e) {
            e.printStackTrace();
            throw e;

        }
    }

    public static void main(String[] args) {
        launch();
    }
}