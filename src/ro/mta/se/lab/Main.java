package ro.mta.se.lab;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    public static void main(String[] args)
    {
        launch(args);
    }

    public void start(Stage primaryStage) throws IOException {
        FXMLLoader loader = new FXMLLoader();

        loader.setLocation(this.getClass().getResource("/gui.fxml"));
        loader.setController(new WeatherController());
        primaryStage.setScene(new Scene(loader.load()));
        primaryStage.setTitle("MeteoRed");
        primaryStage.show();


    }
    public void stop(){
        HistoryLogger historyLogger = HistoryLogger.getInstance();
        historyLogger.close();
    }
}
