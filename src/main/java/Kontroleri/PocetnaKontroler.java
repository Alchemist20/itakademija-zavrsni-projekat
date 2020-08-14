package Kontroleri;

import Aplikacija.ButtonStil;
import DAO.KorisnikDaoImpl;
import Model.Korisnik;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PocetnaKontroler implements Initializable {
    Korisnik korisnik = new Korisnik();
    KorisnikDaoImpl korisnikDao = new KorisnikDaoImpl();
    ButtonStil buttonStil = new ButtonStil();

    @FXML
    private AnchorPane mainContent;
    @FXML
    private Button btnHome;
    @FXML
    private Button btnPrescription;
    @FXML
    private Button btnPatient;
    @FXML
    private Button btnDrug;
    @FXML
    private Button btnSettings;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            mainContent.getChildren().clear();
            mainContent.getChildren().add(FXMLLoader.load(getClass().getResource("/Izgled/PocetnaSadrzaj.fxml")));
            homeActive();
        } catch (IOException ex) {
            Logger.getLogger(PocetnaKontroler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    public void homeAction(ActionEvent actionEvent) {
        Stage stage = (Stage) btnSettings.getScene().getWindow();
        stage.setTitle("Pocetna");
        try {
            mainContent.getChildren().clear();
            mainContent.getChildren().add(FXMLLoader.load(getClass().getResource("/Izgled/PocetnaSadrzaj.fxml")));
            homeActive();
        } catch (IOException ex) {
            Logger.getLogger(PocetnaKontroler.class.getName()).log(Level.SEVERE, null, ex);
        }

    }


    @FXML
    public void receptiOnAction(ActionEvent actionEvent) {
        Stage stage = (Stage) btnSettings.getScene().getWindow();
        stage.setTitle("Recepti");
        try {
            mainContent.getChildren().clear();
            mainContent.getChildren().add(FXMLLoader.load(getClass().getResource("/Izgled/Recept/Recept.fxml")));
            prescriptionActive();
        } catch (IOException ex) {
            Logger.getLogger(PocetnaKontroler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    @FXML
    public void pacijentiOnAction(ActionEvent actionEvent) {
        Stage stage = (Stage) btnSettings.getScene().getWindow();
        stage.setTitle("Pacijenti");
        try {
            mainContent.getChildren().clear();
            mainContent.getChildren().add(FXMLLoader.load(getClass().getResource("/Izgled/Pacijent/Pacijenti.fxml")));
            patientsActive();
        } catch (IOException ex) {
            Logger.getLogger(PocetnaKontroler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    public void lijekoviOnAction(ActionEvent actionEvent) {
        Stage stage = (Stage) btnSettings.getScene().getWindow();
        stage.setTitle("Lijekovi");
        try {
            mainContent.getChildren().clear();
            mainContent.getChildren().add(FXMLLoader.load(getClass().getResource("/Izgled/Lijek/Lijekovi.fxml")));
            drugsActive();
        } catch (IOException ex) {
            Logger.getLogger(PocetnaKontroler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    public void podesavanjaOnAction(ActionEvent actionEvent) {
        Stage stage = (Stage) btnSettings.getScene().getWindow();
        stage.setTitle("Podesavanja");
        try {
            mainContent.getChildren().clear();
            mainContent.getChildren().add(FXMLLoader.load(getClass().getResource("/Izgled/Podesavanja/Podesavanja.fxml")));
            settingsActive();
        } catch (IOException ex) {
            Logger.getLogger(PocetnaKontroler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    private void homeActive() {
        btnHome.setStyle(buttonStil.pocetnaAktivna);
        btnPrescription.setStyle("");
        btnSettings.setStyle("");
        btnDrug.setStyle("");
        btnPatient.setStyle("");
    }

    private void prescriptionActive() {
        btnPrescription.setStyle(buttonStil.receptAktivan);
        btnHome.setStyle("");
        btnSettings.setStyle("");
        btnDrug.setStyle("");
        btnPatient.setStyle("");

    }


    private void patientsActive() {
        btnPatient.setStyle(buttonStil.pacijentiAktivni);
        btnHome.setStyle("");
        btnPrescription.setStyle("");
        btnSettings.setStyle("");
        btnDrug.setStyle("");

    }

    private void drugsActive() {
        btnDrug.setStyle(buttonStil.lijekoviAktivni);
        btnHome.setStyle("");
        btnPatient.setStyle("");
        btnPrescription.setStyle("");
        btnSettings.setStyle("");
    }

    private void settingsActive() {
        btnSettings.setStyle(buttonStil.podesavanjaAktivna);
        btnHome.setStyle("");
        btnPrescription.setStyle("");
        btnDrug.setStyle("");
        btnPatient.setStyle("");

    }

    @FXML
    public void handleOdjava(ActionEvent actionEvent) throws IOException {
        FXMLLoader fXMLLoader = new FXMLLoader();
        fXMLLoader.setLocation(getClass().getResource("/Izgled/Prijava.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(fXMLLoader.load());
        stage.setScene(scene);
        stage.setTitle("Prijava");
        stage.show();

        Stage nStage = (Stage) mainContent.getScene().getWindow();
        nStage.close();

    }


}


