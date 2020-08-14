package Model;

import org.hibernate.annotations.Cascade;

import javax.persistence.*;

@Entity
@Table(name = "obrazac_lijeka")
public class ObrazacLijeka {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JoinColumn(name = "id_obrasca_lijeka", unique = true, nullable = false)
    private int medicineTemplateId;
    @JoinColumn(name = "jacina_lijeka", nullable = false)
    private String medicineStrength;
    @JoinColumn(name = "trajanje_lijeka", nullable = false)
    private String medicineDuration;
    @JoinColumn(name = "doza", nullable = false)
    private String medicineDosage;
    @JoinColumn(name = "tip_lijeka", nullable = false)
    private String medicineType;
    @JoinColumn(name = "savjeti")
    private String medicineAdvice;

    @ManyToOne
    @JoinColumn(name="korisnicki_id")
    private Korisnik korisnik;

    public ObrazacLijeka() {
    }


    public ObrazacLijeka(String medicineStrength, String medicineDuration, String medicineDosage, String medicineType, String medicineAdvice) {
        this.medicineStrength = medicineStrength;
        this.medicineDuration = medicineDuration;
        this.medicineDosage = medicineDosage;
        this.medicineType = medicineType;
        this.medicineAdvice = medicineAdvice;
    }

    public int getMedicineTemplateId() {
        return medicineTemplateId;
    }

    public void setMedicineTemplateId(int medicineTemplateId) {
        this.medicineTemplateId = medicineTemplateId;
    }

    public String getMedicineStrength() {
        return medicineStrength;
    }

    public void setMedicineStrength(String medicineStrength) {
        this.medicineStrength = medicineStrength;
    }

    public String getMedicineDuration() {
        return medicineDuration;
    }

    public void setMedicineDuration(String medicineDuration) {
        this.medicineDuration = medicineDuration;
    }

    public String getMedicineDosage() {
        return medicineDosage;
    }

    public void setMedicineDosage(String medicineDosage) {
        this.medicineDosage = medicineDosage;
    }

    public String getMedicineType() {
        return medicineType;
    }

    public void setMedicineType(String medicineType) {
        this.medicineType = medicineType;
    }

    public String getMedicineAdvice() {
        return medicineAdvice;
    }

    public void setMedicineAdvice(String medicineAdvice) {
        this.medicineAdvice = medicineAdvice;
    }

    @Override
    public String toString() {
        return "ObrazacLijeka --> [" +
                "Id Obrasca Lijeka = " + medicineTemplateId +
                ", Jacina Lijeka = " + medicineStrength +
                ", Trajanje Lijeka = " + medicineDuration +
                ", Doza = " + medicineDosage +
                ", Tip Lijeka = " + medicineType +
                ", Savjet = " + medicineAdvice +
                ']';
    }
}
