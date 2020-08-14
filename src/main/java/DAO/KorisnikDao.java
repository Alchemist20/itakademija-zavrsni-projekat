package DAO;

import Model.Korisnik;

import java.util.List;

public interface KorisnikDao {
    void insert(String fullName, String username, String password, String email);

    List<Korisnik> getAllRecords();

    List<Korisnik> paginate(int records);

    Korisnik getById(int id);

    void deleteById(int deleteRecord);

    void updateById(int id, String fullName, String username, String password, String email);

    void deleteAllRecords();

    int countAllUsers();

}
