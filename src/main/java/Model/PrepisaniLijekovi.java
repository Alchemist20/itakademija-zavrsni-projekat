package Model;

import org.hibernate.annotations.Cascade;

import javax.persistence.*;

@Entity
@Table(name = "prepisani_lijekovi")
public class PrepisaniLijekovi {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JoinColumn(name = "id_prepisanih_lijekova", unique = true, nullable = false)
    private int prescribedMedicineId;
    @JoinColumn(name = "jacina_lijeka", nullable = false)
    private String prescribedMedicineStrength;
    @JoinColumn(name = "trajanje_lijeka", nullable = false)
    private String prescribedMedicineDuration;
    @JoinColumn(name = "doza", nullable = false)
    private String prescribedMedicineDosage;
    @JoinColumn(name = "tip_lijeka", nullable = false)
    private String prescribedMedicineType;
    @JoinColumn(name = "savjeti")
    private String prescribedMedicineAdvice;


    @ManyToOne
    @JoinColumn(name="korisnicki_id")
    private Korisnik korisnik;

    public PrepisaniLijekovi() {
    }

    public PrepisaniLijekovi(String prescribedMedicineStrength, String prescribedMedicineDuration, String prescribedMedicineDosage, String prescribedMedicineType, String prescribedMedicineAdvice) {
        this.prescribedMedicineStrength = prescribedMedicineStrength;
        this.prescribedMedicineAdvice = prescribedMedicineAdvice;
        this.prescribedMedicineDuration = prescribedMedicineDuration;
        this.prescribedMedicineDosage = prescribedMedicineDosage;
        this.prescribedMedicineType = prescribedMedicineType;
        this.prescribedMedicineAdvice = prescribedMedicineAdvice;
    }

    public int getPrescribedMedicineId() {
        return prescribedMedicineId;
    }

    public void setPrescribedMedicineId(int prescribedMedicineId) {
        this.prescribedMedicineId = prescribedMedicineId;
    }

    public String getPrescribedMedicineStrength() {
        return prescribedMedicineStrength;
    }

    public void setPrescribedMedicineStrength(String prescribedMedicineStrength) {
        this.prescribedMedicineStrength = prescribedMedicineStrength;
    }

    public String getPrescribedMedicineDuration() {
        return prescribedMedicineDuration;
    }

    public void setPrescribedMedicineDuration(String prescribedMedicineDuration) {
        this.prescribedMedicineDuration = prescribedMedicineDuration;
    }

    public String getPrescribedMedicineDosage() {
        return prescribedMedicineDosage;
    }

    public void setPrescribedMedicineDosage(String prescribedMedicineDosage) {
        this.prescribedMedicineDosage = prescribedMedicineDosage;
    }

    public String getPrescribedMedicineType() {
        return prescribedMedicineType;
    }

    public void setPrescribedMedicineType(String prescribedMedicineType) {
        this.prescribedMedicineType = prescribedMedicineType;
    }

    public String getPrescribedMedicineAdvice() {
        return prescribedMedicineAdvice;
    }

    public void setPrescribedMedicineAdvice(String prescribedMedicineAdvice) {
        this.prescribedMedicineAdvice = prescribedMedicineAdvice;
    }

    @Override
    public String toString() {
        return "PrepisaniLijekovi --> [" +
                "Id Prepisanih Lijekova = " + prescribedMedicineId +
                ", Jacina Lijeka = " + prescribedMedicineStrength +
                ", Trajanje Lijeka = " + prescribedMedicineDuration +
                ", Doza = " + prescribedMedicineDosage +
                ", Tip Lijeka = " + prescribedMedicineType +
                ", Savjeti = " + prescribedMedicineAdvice +
                ']';
    }
}
