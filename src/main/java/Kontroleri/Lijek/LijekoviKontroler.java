package Kontroleri.Lijek;

import DAO.LijekDaoImpl;
import Model.Lijek;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class LijekoviKontroler implements Initializable {
    LijekDaoImpl lijekDao = new LijekDaoImpl();
    List<Lijek> drugsList = new ArrayList<>();
    int ukupno = 0;

    @FXML
    private ScrollPane scrollPan;
    @FXML
    private FlowPane flowPane;
    @FXML
    private TableView<Lijek> drugTable2;
    @FXML
    private TableColumn<Lijek, Integer> drugId;
    @FXML
    private TableColumn<Lijek, String> drugName;
    @FXML
    private TableColumn<Lijek, String> genericDrugName;
    @FXML
    private TableColumn<Lijek, String> drugNotes;
    @FXML
    private Button addNewDrug;
    @FXML
    private TextField pretraziLijekove;
    @FXML
    private Button nextDrugs;
    @FXML
    private Label ukupnoLijekova;
    @FXML
    private Label prikazLijekova;

    @FXML
    void addNewDrug(ActionEvent event) {
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
    void nextDrugs(ActionEvent event) {

    }

    @FXML
    void previousDrugs(ActionEvent event) {

    }

    @FXML
    void refreshDrugs(ActionEvent event) {
        ucitajLijekove();
    }

    @FXML
    void searchDrugs(ActionEvent event) {

    }

    private void ucitajLijekove() {
        drugTable2.getItems().clear();
        drugsList = lijekDao.paginate(10);
        drugId.setCellValueFactory(new PropertyValueFactory<>("MedicineId"));
        drugName.setCellValueFactory(new PropertyValueFactory<>("MedicineName"));
        genericDrugName.setCellValueFactory(new PropertyValueFactory<>("GenericName"));
        drugNotes.setCellValueFactory(new PropertyValueFactory<>("Note"));
        drugTable2.getItems().addAll(drugsList);


    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ukupno = lijekDao.countAllDrugs();
        ukupnoLijekova.setText("Ukuono " + ukupno);
        ucitajLijekove();
    }
}
