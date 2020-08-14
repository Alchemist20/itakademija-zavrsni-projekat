package Kontroleri;

import DAO.KorisnikDaoImpl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class RegistracijaKontroler  {
    KorisnikDaoImpl korisnikDao = new KorisnikDaoImpl();
    @FXML
    private TextField fullName;
    @FXML
    private TextField username2;
    @FXML
    private PasswordField password2;
    @FXML
    private TextField email;
    @FXML
    private Button loginButton;


    @FXML
    public void loginAction(ActionEvent actionEvent) {
        loginButton.getScene().getWindow().hide();
        Stage loginStage = new Stage();
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/Izgled/Prijava.fxml"));
            Scene scene = new Scene(root);
            loginStage.setTitle("Prijava");
            loginStage.setScene(scene);
            loginStage.show();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Nepravilna putanja ili fajl ne postoji!");
        }


    }


    @FXML
    public void registerUserOnAction(ActionEvent actionEvent) {
        if (allowUserInsert() && checkLength()) {
            korisnikDao.insert(fullName.getText(), username2.getText(), password2.getText(), email.getText());
            resetForm();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Obavjestenje");
            alert.setHeaderText("Novi korisnik je uspjesno registrovan.");
            alert.show();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Prazna polja!");
            alert.setHeaderText("Popunite sva polja kako biste se registrovali.");
            alert.show();
        }
    }

    private void resetForm() {
        fullName.setText("");
        username2.setText("");
        password2.setText("");
        email.setText("");
    }

    private boolean allowUserInsert() {
        boolean isFilledOut;
        if (fullName.getText().isEmpty() || username2.getText().isEmpty() || password2.getText().isEmpty() || email.getText().isEmpty()) {
            isFilledOut = false;

        } else {
            isFilledOut = true;

        }
        return isFilledOut;
    }

    private boolean checkLength() {
        boolean isTooLong;
        if (password2.getLength() > 30 || username2.getLength() > 20 && fullName.getLength() > 50 || email.getLength() > 50) {
            isTooLong = true;
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Previse karaktera");
            alert.setHeaderText("Molimo unesite sifru koja nije duza od 30 karaktera i korisnicko ime koje nije duze od 20 karaktera.");
            alert.show();
        } else {
            isTooLong = false;
        }
        return isTooLong;
    }
}
