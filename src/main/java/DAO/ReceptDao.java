package DAO;

import Model.Recept;

import java.util.List;

public interface ReceptDao {

    void insert(Recept recept);

    List<Recept> getAllRecords();

    List<Recept> paginate(int records);

    Recept getById(int id);

    void deleteById(int deleteRecord);

    void updateById(int id, String date, String dosage, String advice, String nextExaminationDate);

    void deleteAllRecords();

    int countAllPrescriptions();

}
