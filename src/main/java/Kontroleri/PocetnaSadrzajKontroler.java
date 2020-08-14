package Kontroleri;

import DAO.LijekDaoImpl;
import DAO.ObrazacLijekaDaoImpl;
import DAO.PacijentDaoImpl;
import DAO.ReceptDaoImpl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class PocetnaSadrzajKontroler implements Initializable {
    PacijentDaoImpl pacijentDao = new PacijentDaoImpl();
    LijekDaoImpl lijekDao = new LijekDaoImpl();
    ReceptDaoImpl receptDao = new ReceptDaoImpl();
    ObrazacLijekaDaoImpl obrazacLijekaDao = new ObrazacLijekaDaoImpl();
    @FXML
    private Text totalPatients;
    @FXML
    private Button btnPatient;
    @FXML
    private Text totalMedicine;
    @FXML
    private Button btnDrug;
    @FXML
    private Text totalPrescriptions;
    @FXML
    private Button btnPrescription;
    @FXML
    private Text totalPrescriptionTemplates;
    @FXML
    private Button btnTemplate;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        totalPatients.setText(String.valueOf(pacijentDao.countAllPatients()));
        totalMedicine.setText(String.valueOf(lijekDao.countAllDrugs()));
        totalPrescriptions.setText(String.valueOf(receptDao.countAllPrescriptions()));
        totalPrescriptionTemplates.setText(String.valueOf(obrazacLijekaDao.countAllMedicineTemplates()));
    }


    @FXML
    public void dodajPacijenta(ActionEvent actionEvent) {
        Stage addPatient = new Stage();
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/Izgled/Pacijent/NoviPacijent.fxml"));
            Scene scene = new Scene(root);
            addPatient.setTitle("Dodaj Novog Pacijenta");
            addPatient.setScene(scene);
            addPatient.show();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("Nepravilna putanja ili fajl ne postoji!");
        } catch (IOException e) {
            e.printStackTrace();

        }
    }

    @FXML
    public void dodajLijek(ActionEvent actionEvent) {
        Stage addDrug = new Stage();
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/Izgled/Lijek/NoviLijek.fxml"));
            Scene scene = new Scene(root);
            addDrug.setTitle("Dodaj Novi Lijek");
            addDrug.setScene(scene);
            addDrug.show();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("Nepravilna putanja ili fajl ne postoji!");
        } catch (IOException e) {
            e.printStackTrace();

        }
    }

    @FXML
    public void napraviNoviObrazac(ActionEvent actionEvent) {
        Stage addPrescriptionTemplate = new Stage();
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/Izgled/ObrazacLijeka/NoviObrazacLijeka.fxml"));
            Scene scene = new Scene(root);
            addPrescriptionTemplate.setTitle("Novi Obrazac Lijeka");
            addPrescriptionTemplate.setScene(scene);
            addPrescriptionTemplate.show();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("Nepravilna putanja ili fajl ne postoji!");
        } catch (IOException e) {
            e.printStackTrace();

        }
    }

    @FXML
    public void napraviRecept(ActionEvent actionEvent) {
        Stage addPrescription = new Stage();
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/Izgled/Pacijent/Pacijenti.fxml"));
            Scene scene = new Scene(root);
            addPrescription.setTitle("Izaberite pacijenta kojem zelite napisati recept");
            addPrescription.setScene(scene);
            addPrescription.show();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("Nepravilna putanja ili fajl ne postoji!");
        } catch (IOException e) {
            e.printStackTrace();

        }
    }


}