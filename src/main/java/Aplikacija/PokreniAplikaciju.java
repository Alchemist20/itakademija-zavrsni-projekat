package Aplikacija;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.io.IOException;

public class PokreniAplikaciju extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/Izgled/Prijava.fxml"));
            primaryStage.setTitle("Prijava");
            primaryStage.setScene(new Scene(root, 1209, 470));
            primaryStage.show();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("Fajl ne postoji: " + e);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}