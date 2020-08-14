package Kontroleri.Pacijent;

import DAO.PacijentDaoImpl;
import DAO.ReceptDaoImpl;
import Model.Pacijent;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class PacijentiKontroler implements Initializable {

    PacijentDaoImpl pacijentDao = new PacijentDaoImpl();
    ReceptDaoImpl receptDao = new ReceptDaoImpl();
    List<Pacijent> patients = new ArrayList<>();

    Callback<TableColumn<Pacijent, String>, TableCell<Pacijent, String>> akcije = (TableColumn<Pacijent, String> param) -> {
        final TableCell<Pacijent, String> cell = new TableCell<Pacijent, String>() {
            @Override
            public void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                    setText(null);
                } else {

                    Button button = new Button("Napisi recept");
                    button.setDefaultButton(true);

                    button.setOnAction(e -> {
                        Stage writePrescription = new Stage();
                        try {
                            Parent root = FXMLLoader.load(getClass().getResource("/Izgled/ObrazacLijeka/NoviObrazacLijeka.fxml"));
                            Scene scene = new Scene(root);
                            writePrescription.setTitle("Novi Obrazac Lijeka");
                            writePrescription.setScene(scene);
                            writePrescription.show();
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
    private boolean patientDefault = true;
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private FlowPane flowPane;
    @FXML
    private TableView<Pacijent> patientsTable;
    @FXML
    private TableColumn<Pacijent, Integer> id;
    @FXML
    private TableColumn<Pacijent, String> fullName4;
    @FXML
    private TableColumn<Pacijent, String> dob;
    @FXML
    private TableColumn<Pacijent, String> patientActions;
    @FXML
    private TableColumn<Pacijent, String> gender;
    @FXML
    private TableColumn<Pacijent, String> phoneNum;
    @FXML
    private TableColumn<Pacijent, String> address;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ucitajPacijente();
    }

    @FXML
    public void dodajPacijenta(ActionEvent actionEvent) {
        Stage addNewPatient = new Stage();
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/Izgled/Pacijent/NoviPacijent.fxml"));
            Scene scene = new Scene(root);
            addNewPatient.setTitle("Izaberite pacijenta kojem zelite napisati recept");
            addNewPatient.setScene(scene);
            addNewPatient.show();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("Nepravilna putanja ili fajl ne postoji!");
        } catch (IOException e) {
            e.printStackTrace();

        }
    }

    private void ucitajPacijente() {
        patientDefault = true;

        patientsTable.getItems().clear();

        patients = pacijentDao.paginate(10);
        id.setCellValueFactory(new PropertyValueFactory<>("PatientId"));
        fullName4.setCellValueFactory(new PropertyValueFactory<>("FullName"));
        dob.setCellValueFactory(new PropertyValueFactory<>("Dob"));
        gender.setCellValueFactory(new PropertyValueFactory<>("Gender"));
        phoneNum.setCellValueFactory(new PropertyValueFactory<>("PhoneNumber"));
        address.setCellValueFactory(new PropertyValueFactory<>("Address"));
        patientActions.setCellFactory(akcije);


        patientsTable.getItems().addAll(patients);

    }

    @FXML
    void refreshPatientTable(ActionEvent event) {
        ucitajPacijente();
    }


}
