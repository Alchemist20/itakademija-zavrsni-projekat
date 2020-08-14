package Kontroleri;

import DAO.KorisnikDaoImpl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class PodesavanjaKontroler {
    KorisnikDaoImpl korisnikDao = new KorisnikDaoImpl();
    @FXML
    private TextField fullName3;

    @FXML
    private TextField username3;

    @FXML
    private TextField email3;

    @FXML
    private PasswordField oldPass3;

    @FXML
    private PasswordField newPass3;

    @FXML
    private PasswordField reNewPass3;


    private boolean allowProfileUpdate() {
        boolean isFilledOut;
        if (username3.getText().isEmpty() || email3.getText().isEmpty() || fullName3.getText().isEmpty() || oldPass3.getText().isEmpty() || newPass3.getText().isEmpty() || reNewPass3.getText().isEmpty()) {
            isFilledOut = false;
        } else {
            isFilledOut = true;
        }
        return isFilledOut;
    }

    private void resetForm() {
        username3.setText("");
        email3.setText("");
        fullName3.setText("");
        oldPass3.setText("");
        newPass3.setText("");
        reNewPass3.setText("");
    }


    @FXML
    public void handleProfileChange(ActionEvent actionEvent) {
        if (allowProfileUpdate() && arePasswordsMatch()) {
            korisnikDao.updateById(1, fullName3.getText(), username3.getText(), newPass3.getText(), email3.getText());
            resetForm();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Obavjestenje");
            alert.setHeaderText("Vas profil je uspjesno azuriran.");
            alert.show();

        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Greska");
            alert.setHeaderText("Molimo popunite sva polja.");
            alert.show();

        }
    }

    private boolean arePasswordsMatch() {
        boolean isValidMatch;
        if (newPass3.getText().matches(reNewPass3.getText())) {
            isValidMatch = true;
        } else {
            isValidMatch = false;
        }
        return isValidMatch;
    }
}
