package DAO;

import Model.Lijek;

import java.util.List;

public interface LijekDao {
    void insert(String name, String genericName, String note);

    List<Lijek> getAllRecords();

    List<Lijek> paginate(int records);

    Lijek getById(int id);

    void deleteById(int deleteRecord);

    void updateById(int id, String name, String genericName, String note);

    void deleteAllRecords();

    int countAllDrugs();
}
