package Kontroleri;

import Autentikacija.Autentikuj;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PrijavaKontroler {
    Autentikuj autentikuj = new Autentikuj();
    @FXML
    private TextField username;
    @FXML
    private PasswordField password;
    @FXML
    private Button loginBtn;
    @FXML
    private Button registerBtn;
    @FXML
    private Button forgotPassword;


    @FXML
    private void handleLogin(ActionEvent actionEvent) {
        if (checkUserValidity()) {
            if (autentikuj.authenticate(username.getText(), password.getText())) {
                try {
                    Parent root = FXMLLoader.load(getClass().getResource("/Izgled/Pocetna.fxml"));
                    root.getStylesheets().add("/Stil/pocetnastil.css");
                    Scene scene = new Scene(root);
                    Stage homeStage = new Stage();
                    homeStage.setTitle("Pocetna");
                    homeStage.setScene(scene);
                    homeStage.show();

                    Stage stage = (Stage) loginBtn.getScene().getWindow();
                    stage.close();

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                    System.out.println("Nepravilna putanja ili fajl ne postoji!");

                } catch (IOException ex) {
                    Logger.getLogger(PrijavaKontroler.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {

                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Korisnicko ime i sifra nisu validni");
                alert.setHeaderText("Unesite ispravne podatke");
                alert.show();
            }

        }
    }

    @FXML
    private void handleRegistration(ActionEvent actionEvent) {
        loginBtn.getScene().getWindow().hide();
        Stage registrationStage = new Stage();
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/Izgled/Registracija.fxml"));
            Scene scene = new Scene(root);
            registrationStage.setTitle("Registracija");
            registrationStage.setScene(scene);
            registrationStage.show();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("Nepravilna putanja ili fajl ne postoji!");
        } catch (IOException e) {
            e.printStackTrace();

        }

    }

    public boolean checkUserValidity() {
        boolean isValid = false;
        if (username.getText().isEmpty() || password.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Polja su prazna!");
            alert.setHeaderText("Molimo unesite vase korisnicko ime i sifru.");
            alert.show();

        } else {
            isValid = true;
        }
        return isValid;
    }

}
