package Startup;

import Backend.ThemeGenerator;
import Backend.Updater;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application {

    private static int _time;
    private Stage _primaryStage;

    @Override
    public void start(Stage primaryStage) throws Exception{
        _primaryStage = primaryStage;
        Parent root = FXMLLoader.load(getClass().getResource("/UI/MainUI.fxml"));
        _primaryStage.setTitle("Daily Art Challenge");
        _primaryStage.setScene(new Scene(root, 1024, 768));
        _primaryStage.show();
    }


    public static void main(String[] args) throws Exception
    {
        Updater.checkFirstRun();
        Updater.checkForUpdate();
        launch(args);
    }
}
