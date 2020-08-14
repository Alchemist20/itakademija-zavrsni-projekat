package Kontroleri.Pacijent;

import DAO.PacijentDaoImpl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class DodajNovogPacijentaKontroler {
    PacijentDaoImpl pacijentDao = new PacijentDaoImpl();

    @FXML
    private TextField patientName;

    @FXML
    private TextField phoneNumber;

    @FXML
    private TextField patientsEmail;

    @FXML
    private TextArea patientsHomeAddress;

    @FXML
    private TextField patientsGender;

    @FXML
    private TextField dateOfBirth;


    @FXML
    public void savePatient(ActionEvent actionEvent) {
        pacijentDao.insert(patientName.getText(), dateOfBirth.getText(), patientsHomeAddress.getText(), patientsGender.getText(), phoneNumber.getText(), patientsEmail.getText());
        resetForm();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Obavjestenje");
        alert.setHeaderText("Novi pacijent je uspjesno dodat.");
        alert.show();


    }

    @FXML
    public void cancelChanges() {
        patientName.setText("");
        patientsEmail.setText("");
        phoneNumber.setText("");
        patientsGender.setText("");
        patientsHomeAddress.setText("");
        dateOfBirth.setText("");
    }

    private void resetForm() {
        cancelChanges();
    }

}
