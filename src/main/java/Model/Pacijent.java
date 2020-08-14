package Model;

import javax.persistence.*;

@Entity
@Table(name = "pacijent")
public class Pacijent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JoinColumn(name = "id_pacijenta", unique = true, nullable = false)
    private int patientId;
    @JoinColumn(name = "puno_ime", nullable = false)
    private String fullName;
    @JoinColumn(name = "datum_rodjenja", nullable = false)
    private String dob;
    @JoinColumn(name = "adresa")
    private String address;
    @JoinColumn(name = "spol")
    private String gender;
    @JoinColumn(name = "telefon")
    private String phoneNumber;
    @JoinColumn(name = "email")
    private String email;

    @OneToOne
    private Recept recept;

    public Pacijent() {
    }

    public Pacijent(String fullName, String dob, String address, String gender, String phoneNumber, String email) {
        this.fullName = fullName;
        this.dob = dob;
        this.address = address;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }


    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Pacijent -->[" +
                "Id Pacijenta = " + patientId +
                ", Puno Ime = " + fullName +
                ", Datum Rodjenja = " + dob +
                ", Adresa = " + address +
                ", Spol = " + gender +
                ", Telefon = " + phoneNumber +
                ",Email = " + email +
                ']';
    }
}
