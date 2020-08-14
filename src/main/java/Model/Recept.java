package Model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "recept")
public class Recept {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JoinColumn(name = "id_recepta", unique = true,nullable = false)
    private int prescriptionId;
    @JoinColumn(name = "datum_izdatog_recepta",nullable = false)
    private String date;
    @JoinColumn(name = "doza", nullable = false)
    private String dosage;
    @JoinColumn(name = "savjeti")
    private String advice;
    @JoinColumn(name = "sljedeci_pregled")
    private String nextExamination;

    @OneToOne(mappedBy="recept")
    private Pacijent pacijent;

    @OneToOne
    private Korisnik korisnik;

    public Recept() {}

    public Recept(String date, String dosage, String advice, String nextExamination) {
        this.date = date;
        this.dosage = dosage;
        this.advice = advice;
        this.nextExamination = nextExamination;
    }

    public int getPrescriptionId() {
        return prescriptionId;
    }

    public void setPrescriptionId(int prescriptionId) {
        this.prescriptionId = prescriptionId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDosage() {
        return dosage;
    }

    public void setDosage(String dosage) {
        this.dosage = dosage;
    }

    public String getAdvice() {
        return advice;
    }

    public void setAdvice(String advice) {
        this.advice = advice;
    }

    public String getNextExamination() {
        return nextExamination;
    }

    public void setNextExamination(String nextExamination) {
        this.nextExamination = nextExamination;
    }

    @Override
    public String toString() {
        return "Recept --> [" +
                "Id Recepta = " + prescriptionId +
                ", Datum Izdatog Recepta = " + date +
                ", Doza = " + dosage +
                ", Savjeti = " + advice +
                ", Sljedeci Pregled = " + nextExamination +
                ']';
    }
}
