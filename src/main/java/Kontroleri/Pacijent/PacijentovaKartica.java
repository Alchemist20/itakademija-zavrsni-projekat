package Kontroleri.Pacijent;

import Model.Pacijent;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public abstract class PacijentovaKartica extends AnchorPane {
    protected final HBox hBox;
    protected final VBox vBox;
    protected final VBox vBox0;
    protected final Label label;
    protected final Label label0;
    protected final Label label1;
    protected final Label label2;
    protected final Label label3;
    protected final Button button;
    protected final ButtonBar buttonBar;

    protected final int patientId;
    Pacijent pacijent;

    private String patientSex = "Male";

    public PacijentovaKartica() {
        patientId = pacijent.getPatientId();
        hBox = new HBox();
        vBox = new VBox();

        vBox0 = new VBox();
        label = new Label();
        label0 = new Label();
        label1 = new Label();
        label2 = new Label();
        label3 = new Label();
        button = new Button();
        buttonBar = new ButtonBar();

        setPrefHeight(196.0);
        setPrefWidth(451.0);

        AnchorPane.setBottomAnchor(hBox, 0.0);
        AnchorPane.setLeftAnchor(hBox, 0.0);
        AnchorPane.setRightAnchor(hBox, 0.0);
        AnchorPane.setTopAnchor(hBox, 0.0);
        hBox.setPrefHeight(182.0);
        hBox.setPrefWidth(325.0);
        hBox.setSpacing(7.0);

        vBox.setAlignment(javafx.geometry.Pos.TOP_CENTER);
        vBox.setPrefHeight(182.0);
        vBox.setPrefWidth(172.0);

        vBox0.setPrefHeight(162.0);
        vBox0.setPrefWidth(275.0);

        label.setText(pacijent.getFullName());

        label0.setText("Datum rodjenja " + age(pacijent));

        patientSex = pacijent.getGender();

        label1.setText("Spol : " + patientSex);

        button.setMnemonicParsing(false);
        button.setOnAction(this::prescriptionOnAction);
        button.setText("Napisi novi recept");
        VBox.setMargin(button, new Insets(5.0, 0.0, 0.0, 0.0));

        buttonBar.setPrefHeight(40.0);
        buttonBar.setPrefWidth(200.0);

        hBox.getChildren().add(vBox);
        vBox0.getChildren().add(label);
        vBox0.getChildren().add(label0);
        vBox0.getChildren().add(label1);
        vBox0.getChildren().add(label2);
        vBox0.getChildren().add(label3);
        vBox0.getChildren().add(button);
        vBox0.getChildren().add(buttonBar);
        hBox.getChildren().add(vBox0);
        getChildren().add(hBox);

    }

    private String age(Pacijent pacijent) {
        String age = "";
        return pacijent.getDob();
    }

    protected abstract void prescriptionOnAction(javafx.event.ActionEvent actionEvent);

}


