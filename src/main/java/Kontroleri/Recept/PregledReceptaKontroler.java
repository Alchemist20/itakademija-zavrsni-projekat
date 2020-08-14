package Kontroleri.Recept;

import DAO.PacijentDaoImpl;
import DAO.PrepisaniLijekoviDaoImpl;
import Kontroleri.Pacijent.ReceptKartice;
import Model.Pacijent;
import Model.PrepisaniLijekovi;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class PregledReceptaKontroler implements Initializable {
    List<PrepisaniLijekovi> prepisaniLijekoviList = new ArrayList<>();
    PrepisaniLijekoviDaoImpl prepisaniLijekoviDao = new PrepisaniLijekoviDaoImpl();
    PacijentDaoImpl pacijentDao = new PacijentDaoImpl();
    Callback<TableColumn<PrepisaniLijekovi, String>, TableCell<PrepisaniLijekovi, String>> patientCard = (TableColumn<PrepisaniLijekovi, String> param) -> {
        final TableCell<PrepisaniLijekovi, String> cell = new TableCell<PrepisaniLijekovi, String>() {
            @Override
            public void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                    setText(null);
                } else {
                    Pacijent pacijent = pacijentDao.getById(getTableView().getItems().get(getIndex()).getPrescribedMedicineId());
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
    private TableView<PrepisaniLijekovi> prescribedMedicationTable;
    @FXML
    private TableColumn<PrepisaniLijekovi, Integer> prescribedDrugId;
    @FXML
    private TableColumn<PrepisaniLijekovi, String> prescribedDrugAdvice;
    @FXML
    private TableColumn<PrepisaniLijekovi, String> prescribedDrugDose;
    @FXML
    private TableColumn<PrepisaniLijekovi, String> presrribedDrugDuration;
    @FXML
    private TableColumn<PrepisaniLijekovi, String> prescribedDrugStrength;
    @FXML
    private TableColumn<PrepisaniLijekovi, String> prescribedDrugType;
    @FXML
    private TableColumn<PrepisaniLijekovi, String> action2;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ucitajPrepisaneLijekove();

    }

    private void ucitajPrepisaneLijekove() {
        prescribedMedicationTable.getItems().clear();
        prepisaniLijekoviList = prepisaniLijekoviDao.paginate(10);

        prescribedDrugId.setCellValueFactory(new PropertyValueFactory<>("PrescribedMedicineId"));
        prescribedDrugAdvice.setCellValueFactory(new PropertyValueFactory<>("PrescribedMedicineAdvice"));
        presrribedDrugDuration.setCellValueFactory(new PropertyValueFactory<>("PrescribedMedicineDuration"));
        prescribedDrugStrength.setCellValueFactory(new PropertyValueFactory<>("PrescribedMedicineStrength"));
        prescribedDrugType.setCellValueFactory(new PropertyValueFactory<>("PrescribedMedicineType"));
        prescribedDrugDose.setCellValueFactory(new PropertyValueFactory<>("PrescribedMedicineDosage"));
        action2.setCellFactory(patientCard);
        prescribedMedicationTable.getItems().addAll(prepisaniLijekoviList);

    }

    @FXML
    void refreshTable(ActionEvent event) {
        ucitajPrepisaneLijekove();

    }
}
