package Kontroleri.ObrazacLijeka;

import DAO.ObrazacLijekaDaoImpl;
import DAO.PrepisaniLijekoviDaoImpl;
import DAO.ReceptDaoImpl;
import Model.ObrazacLijeka;
import Model.PrepisaniLijekovi;
import Model.Recept;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ObrazacLijekaKontroler implements Initializable {
    List<ObrazacLijeka> templates = new ArrayList<>();
    ObrazacLijekaDaoImpl obrazacLijekaDao = new ObrazacLijekaDaoImpl();
    ObrazacLijeka obrazacLijeka = new ObrazacLijeka();
    ReceptDaoImpl receptDao = new ReceptDaoImpl();
    Recept recept = new Recept();
    PrepisaniLijekoviDaoImpl prepisaniLijekoviDao = new PrepisaniLijekoviDaoImpl();
    PrepisaniLijekovi prepisaniLijekovi = new PrepisaniLijekovi();
    @FXML
    private TableView<ObrazacLijeka> drugTable;
    @FXML
    private TableColumn<ObrazacLijeka, Integer> templateId;
    @FXML
    private TableColumn<ObrazacLijeka, String> medicineType2;
    @FXML
    private TableColumn<ObrazacLijeka, String> medicineStrength2;
    @FXML
    private TableColumn<?, ?> medicineAdvice2;
    @FXML
    private TableColumn<ObrazacLijeka, String> dose2;
    @FXML
    private TableColumn<ObrazacLijeka, String> medicineDuration2;
    @FXML
    private TextField medicineAdvice;
    @FXML
    private TextField medicineType;
    @FXML
    private TextField medicineStrength;
    @FXML
    private TextField medicineDose;
    @FXML
    private TextField medicineDuration;
    @FXML
    private TextField nextVisitDate;
    @FXML
    private TextField currentDate;

    @FXML
    void cancelChanges(ActionEvent event) {
        resetForm();
    }

    @FXML
    void saveMedicineTemplate(ActionEvent event) {
        recept.setAdvice(medicineAdvice.getText());
        recept.setNextExamination(nextVisitDate.getText());
        recept.setDosage(medicineDose.getText());
        recept.setDate(currentDate.getText());
        receptDao.insert(recept);

        obrazacLijeka.setMedicineAdvice(medicineAdvice.getText());
        obrazacLijeka.setMedicineType(medicineType.getText());
        obrazacLijeka.setMedicineDosage(medicineDose.getText());
        obrazacLijeka.setMedicineDuration(medicineDuration.getText());
        obrazacLijeka.setMedicineStrength(medicineStrength.getText());
        obrazacLijekaDao.insert(obrazacLijeka);

        prepisaniLijekovi.setPrescribedMedicineAdvice(medicineAdvice.getText());
        prepisaniLijekovi.setPrescribedMedicineType(medicineType.getText());
        prepisaniLijekovi.setPrescribedMedicineDosage(medicineDose.getText());
        prepisaniLijekovi.setPrescribedMedicineDuration(medicineDuration.getText());
        prepisaniLijekovi.setPrescribedMedicineStrength(medicineStrength.getText());
        prepisaniLijekoviDao.insert(prepisaniLijekovi);


        resetForm();
        loadMedicineTable();

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Uspjeh");
        alert.setHeaderText("Dodali ste novi obrazac lijeka.");
        alert.show();
    }

    private void resetForm() {
        medicineAdvice.setText("");
        medicineStrength.setText("");
        medicineDose.setText("");
        medicineDuration.setText("");
        medicineType.setText("");
        nextVisitDate.setText("");
        currentDate.setText("");
    }

    private void loadMedicineTable() {
        drugTable.getItems().clear();
        templates = obrazacLijekaDao.paginate(10);

        templateId.setCellValueFactory(new PropertyValueFactory<>("MedicineTemplateId"));
        medicineType2.setCellValueFactory(new PropertyValueFactory<>("MedicineType"));
        medicineStrength2.setCellValueFactory(new PropertyValueFactory<>("MedicineStrength"));
        dose2.setCellValueFactory(new PropertyValueFactory<>("MedicineDosage"));
        medicineDuration2.setCellValueFactory(new PropertyValueFactory<>("MedicineDuration"));
        medicineAdvice2.setCellValueFactory(new PropertyValueFactory<>("MedicineAdvice"));
        drugTable.getItems().addAll(templates);
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadMedicineTable();
    }
}
