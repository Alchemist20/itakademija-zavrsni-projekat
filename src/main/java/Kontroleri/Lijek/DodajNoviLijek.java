package Kontroleri.Lijek;

import DAO.LijekDaoImpl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class DodajNoviLijek {
    LijekDaoImpl lijekDao = new LijekDaoImpl();

    @FXML
    private TextField medicineName;

    @FXML
    private TextField genericMedicineName;

    @FXML
    private TextArea medicineNotes;

    @FXML
    public void saveMedicine(ActionEvent actionEvent) {
        lijekDao.insert(medicineName.getText(), genericMedicineName.getText(), medicineNotes.getText());
        resetForm();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Obavjestenje");
        alert.setHeaderText("Uspjesno ste sacuvali novi lijek.");
        alert.show();
    }

    @FXML
    public void cancelChanges(ActionEvent actionEvent) {
        resetForm();
    }

    private void resetForm() {
        medicineName.setText("");
        medicineNotes.setText("");
        genericMedicineName.setText("");
    }
}
