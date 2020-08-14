package DAO;

import Model.Pacijent;

import java.util.List;

public interface PacijentDao {
    void insert(String fullName, String dob, String address, String gender, String telephone, String email);

    List<Pacijent> getAllRecords();

    List<Pacijent> paginate(int records);

    Pacijent getById(int id);

    void deleteById(int deleteRecord);

    void updateById(int id, String fullName, String dob, String address, String gender, String telephone, String email);

    void deleteAllRecords();

    int countAllPatients();

}
