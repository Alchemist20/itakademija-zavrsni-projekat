package DAO;

import Model.ObrazacLijeka;

import java.util.List;

public interface ObrazacLijekaDao {
    void insert(ObrazacLijeka obrazacLijeka);

    List<ObrazacLijeka> getAllRecords();

    List<ObrazacLijeka> paginate(int records);

    ObrazacLijeka getById(int id);

    void deleteById(int deleteRecord);

    void updateById(int id, String medicineStrength, String duration, String dosage, String type, String advice);

    void deleteAllRecords();

    int countAllMedicineTemplates();

    void delete(ObrazacLijeka obrazacLijeka);
}
