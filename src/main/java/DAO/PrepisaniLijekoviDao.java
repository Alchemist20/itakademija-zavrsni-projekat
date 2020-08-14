package DAO;

import Model.PrepisaniLijekovi;

import java.util.List;

public interface PrepisaniLijekoviDao {
    void insert(PrepisaniLijekovi prepisaniLijekovi);

    List<PrepisaniLijekovi> getAllRecords();

    List<PrepisaniLijekovi> paginate(int records);

    PrepisaniLijekovi getById(int id);

    void deleteById(int deleteRecord);

    void updateById(int id, String medicineStrength, String medicineDuration, String dosage, String type, String advice);

    void deleteAllRecords();

    int countAllPrescribedMedicine();
}
