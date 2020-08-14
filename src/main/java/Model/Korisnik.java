package Model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "korisnik")
public class Korisnik {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "korisnicki_id", unique = true, nullable = false)
    private int userId;
    @Column(name = "ime_i_prezime", nullable = false)
    private String fullName;
    @Column(name = "korisnicko_ime", nullable = false)
    private String username;
    @Column(name = "sifra", nullable = false)
    private String password;
    @Column(name = "email", nullable = false)
    private String email;

    @OneToOne(mappedBy = "korisnik")
    private Recept recept;

    @OneToMany(mappedBy = "korisnik")
    private List<PrepisaniLijekovi> prepisaniLijekoviList = new ArrayList<>();

    @OneToMany(mappedBy = "korisnik")
    private List<ObrazacLijeka> obrazacLijekaArrayList = new ArrayList<>();

    public Korisnik() {
    }

    public Korisnik(String fullName, String username, String password, String email) {
        this.fullName = fullName;
        this.username = username;
        this.password = password;
        this.email = email;

    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Korisnik --> [" +
                "Korisnicki Id = " + userId + ", " +
                "Ime i Prezime = " + fullName + ", " +
                "Korisnicko Ime = " + username +
                ", Email = " + email +
                ']';
    }
}
