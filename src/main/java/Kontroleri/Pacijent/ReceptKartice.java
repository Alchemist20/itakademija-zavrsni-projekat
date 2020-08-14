package Kontroleri.Pacijent;

import Model.Pacijent;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public abstract class ReceptKartice extends HBox {
    protected final VBox vBox;
    protected final Label label;
    protected final Label label0;
    protected final Label label1;


    public ReceptKartice(Pacijent pacijent) {
        vBox = new VBox();
        label = new Label();
        label0 = new Label();
        label1 = new Label();

        setAlignment(javafx.geometry.Pos.CENTER);
        setSpacing(5.0);


        vBox.setAlignment(javafx.geometry.Pos.CENTER_LEFT);

        label.setText(pacijent.getFullName());

        label0.setText("Datum rodjenja : " + age(pacijent));

        String sex = pacijent.getGender();

        label1.setText("Spol : " + sex);
        vBox.setPadding(new Insets(0.0, 10.0, 0.0, 5.0));

        vBox.getChildren().add(label);
        vBox.getChildren().add(label0);
        vBox.getChildren().add(label1);
        getChildren().add(vBox);

    }

    private String age(Pacijent pacijent) {
        return pacijent.getDob();
    }

}
