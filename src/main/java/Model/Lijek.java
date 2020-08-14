package Model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "lijek")
public class Lijek {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_lijeka",unique = true, nullable = false)
    private int medicineId;
    @Column(name = "ime_lijeka", nullable = false)
    private String medicineName;
    @Column(name = "genericko_ime",nullable = false)
    private String genericName;
    @Column(name = "opazanja")
    private String note;


    public Lijek() {}

    public Lijek(String medicineName, String genericName, String note) {
        this.medicineName = medicineName;
        this.genericName = genericName;
        this.note = note;
    }

    public int getMedicineId() {
        return medicineId;
    }

    public void setMedicineId(int medicineId) {
        this.medicineId = medicineId;
    }

    public String getMedicineName() {
        return medicineName;
    }

    public void setMedicineName(String medicineName) {
        this.medicineName = medicineName;
    }

    public String getGenericName() {
        return genericName;
    }

    public void setGenericName(String genericName) {
        this.genericName = genericName;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public String toString() {
        return "Lijek -->[" +
                "Id Lijeka = " + medicineId +
                ", Ime Lijeka = " + medicineName +
                ", Genericko Ime = " + genericName +
                ", Opazanja = " + note +
                ']';
    }
}
