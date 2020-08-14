package Kontroleri.Recept;

import DAO.PacijentDaoImpl;
import DAO.ReceptDaoImpl;
import Kontroleri.Pacijent.ReceptKartice;
import Model.Pacijent;
import Model.Recept;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ReceptKontroler implements Initializable {

    ReceptDaoImpl receptDao = new ReceptDaoImpl();
    PacijentDaoImpl pacijentDao = new PacijentDaoImpl();
    List<Recept> recepti = new ArrayList<>();

    int ukupno = 0;
    boolean prescriptionDefault = true;

    Callback<TableColumn<Recept, String>, TableCell<Recept, String>> akcija = (TableColumn<Recept, String> param) -> {
        final TableCell<Recept, String> cell = new TableCell<Recept, String>() {
            @Override
            public void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                    setText(null);
                } else {

                    Button button = new Button("Pregled Recepta");
                    button.setDefaultButton(true);
                    button.setOnAction(e -> {
                        Stage viewPrescription = new Stage();
                        try {
                            Parent root = FXMLLoader.load(getClass().getResource("/Izgled/Recept/PregledRecepta.fxml"));
                            Scene scene = new Scene(root);
                            viewPrescription.setTitle("Pregled Recepta");
                            viewPrescription.setScene(scene);
                            viewPrescription.show();
                        } catch (FileNotFoundException ex) {
                            ex.printStackTrace();
                            System.out.println("Nepravilna putanja ili fajl ne postoji!");
                        } catch (IOException exception) {
                            exception.printStackTrace();

                        }

                    });
                    HBox hBox = new HBox(button);
                    setGraphic(hBox);
                    setText(null);
                }
            }
        };
        return cell;
    };

    Callback<TableColumn<Recept, String>, TableCell<Recept, String>> patientCard = (TableColumn<Recept, String> param) -> {
        final TableCell<Recept, String> cell = new TableCell<Recept, String>() {
            @Override
            public void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                    setText(null);
                } else {
                    Pacijent pacijent = pacijentDao.getById(getTableView().getItems().get(getIndex()).getPrescriptionId());
                    ReceptKartice receptKartice = new ReceptKartice(pacijent) {
                    };
                    setGraphic(receptKartice);
                    setText(null);
                }
            }
        };
        return cell;
    };
    @FXML
    private Button noviRecept;
    @FXML
    private TableView<Recept> prescriptionTable;
    @FXML
    private TableColumn<Pacijent, Integer> patientId;
    @FXML
    private TableColumn<Recept, String> patientDescription;
    @FXML
    private TableColumn<Recept, String> prescriptionDate;
    @FXML
    private TableColumn<Recept, String> mainComplain;
    @FXML
    private TableColumn<Recept, String> action;
    @FXML
    private Label total;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ukupno = receptDao.countAllPrescriptions();
        ucitajRecepte();
        total.setText("Ukupno recepta :" + ukupno);
    }

    private void ucitajRecepte() {
        prescriptionDefault = true;

        prescriptionTable.getItems().clear();

        recepti = receptDao.paginate(10);
        patientId.setCellValueFactory(new PropertyValueFactory<>("PrescriptionId"));
        prescriptionDate.setCellValueFactory(new PropertyValueFactory<>("Date"));
        mainComplain.setCellValueFactory(new PropertyValueFactory<>("Advice"));
        patientDescription.setCellFactory(patientCard);
        action.setCellFactory(akcija);

        prescriptionTable.getItems().addAll(recepti);
    }

    @FXML
    public void napisiNoviRecept(ActionEvent actionEvent) {
        Stage writePrescription = new Stage();
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/Izgled/Pacijent/Pacijenti.fxml"));
            Scene scene = new Scene(root);
            writePrescription.setTitle("Izaberite pacijenta kojem zelite napisati recept");
            writePrescription.setScene(scene);
            writePrescription.show();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("Nepravilna putanja ili fajl ne postoji!");
        } catch (IOException e) {
            e.printStackTrace();

        }
    }

    @FXML
    void refreshTable(ActionEvent event) {
        ucitajRecepte();
    }

}
