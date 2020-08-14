package BazaPodataka;

import DAO.*;
import Model.*;


public class OperacijeNadBazom {

    public static void main(String[] args) {
        KorisnikDaoImpl korisnikDao = new KorisnikDaoImpl();
        LijekDaoImpl lijekDao = new LijekDaoImpl();
        ObrazacLijekaDaoImpl obrazacLijekaDao = new ObrazacLijekaDaoImpl();
        PacijentDaoImpl pacijentDao = new PacijentDaoImpl();
        PrepisaniLijekoviDaoImpl prepisaniLijekoviDao = new PrepisaniLijekoviDaoImpl();
        ReceptDaoImpl receptDao = new ReceptDaoImpl();

//----------------------------------------------------------------------------------------------------------------------
        /*############################
          #                          #
          #         KORISNIK         #
          #                          #
          ############################*/

        // Create
//        korisnikDao.insert("Aleksandar21", "Aleksandar21", "mycoolpass", "myemail@gmail.com");
        // Read
//        korisnikDao.getAllRecords();

        // Read by id
//        korisnikDao.getById(1);

        // Paginate
//        korisnikDao.paginate(3);

        // Update by id
//        korisnikDao.updateById(1, "Aleksandar Mijadzevic", "Aleksandar21", "myepicpass", "aleksandarmijadzevic@gmail.com");
//        korisnikDao.getById(1);

        // Delete by id
//        korisnikDao.deleteById(2);


        // Count
//        korisnikDao.countAllUsers();
//----------------------------------------------------------------------------------------------------------------------

        /* ############################
           #                          #
           #         LIJEK            #
           #                          #
           ############################ */

        // Create
//       Lijek lijek = new Lijek();
//       lijek.setMedicineName("Simvastatin ");
//       lijek.setGenericName("Zocor");
//       lijek.setNote("Problemi sa holesterolom...");
//       lijekDao.insert(lijek);

        // Read
//        lijekDao.getAllRecords();

        // Read by id
//        lijekDao.getById(1);

        // Paginate
//        lijekDao.paginate(2);

        // Update by id
//        lijekDao.updateById(2,"Lisinopril","Prinivil","Problemi sa visokim krvnim pritiskom...");

        // Delete by id
//        lijekDao.deleteById(1);

        // Count
//        lijekDao.countAllDrugs();
//----------------------------------------------------------------------------------------------------------------------

        /* ############################
           #                          #
           #         PACIJENT         #
           #                          #
           ############################ */

        // Create
//        pacijentDao.insert("Peter Parker","18/10/1985","New York City","muski","5252/7521/77","spidey@gmail.com");

        // Read
//        pacijentDao.getAllRecords();

        // Read by id
//        pacijentDao.getById(2);

        // Paginate
//        pacijentDao.paginate(2);

        // Update by id
//        pacijentDao.updateById(1,"Walter White","9/3/65","Freaky St 104","male","9291/3242/32","walterwhite@gmail.com");

        // Delete by id
//        pacijentDao.deleteById(4);

        // Count
//        pacijentDao.countAllPatients();
//----------------------------------------------------------------------------------------------------------------------

       /* ############################
          #                          #
          #          RECEPT          #
          #                          #
          ############################ */

        // Create
//        Recept recept = new Recept();
//        recept.setPrescriptionId(1);
//        recept.setDate("4/4/20 12:35h");
//        recept.setDosage("3x");
//        recept.setAdvice("Nesto nesto...");
//        recept.setNextExamination("21/08/20 10:00h");
//        receptDao.insert(recept);

        // Read
//        receptDao.getAllRecords();

        // Read by id
//        receptDao.getById(1);

        // Paginate
//        receptDao.paginate(1);

        // Update by id
//        receptDao.updateById(1,"5/6/20 11:15h", "2x na dan","Nesto nesto ...","7/7/20 12:30h");

        // Delete by id
//        receptDao.deleteById(5);

        // Count
//        receptDao.countAllPrescriptions();
//----------------------------------------------------------------------------------------------------------------------

       /* ############################
          #         PREPISANI        #
          #         LIJEKOVI         #
          #                          #
          ############################ */

        // Create
//        PrepisaniLijekovi prepisaniLijekovi = new PrepisaniLijekovi();
//        prepisaniLijekovi.setPrescribedMedicineId(1);
//        prepisaniLijekovi.setPrescribedMedicineStrength("50 ml, i.v");
//        prepisaniLijekovi.setPrescribedMedicineDuration("4 dana");
//        prepisaniLijekovi.setPrescribedMedicineDosage("1x");
//        prepisaniLijekovi.setPrescribedMedicineType("Analgetik");
//        prepisaniLijekovi.setPrescribedMedicineAdvice("Izbjegavati napor ...");
//        prepisaniLijekoviDao.insert(prepisaniLijekovi);

        // Read
//        prepisaniLijekoviDao.getAllRecords();

        // Read by id
//        prepisaniLijekoviDao.getById(2);

        // Paginate
//        prepisaniLijekoviDao.paginate(2);

        // Update by id
//        prepisaniLijekoviDao.updateById(1,"55 ml","3 dana","3x","Analgetik","Odmor 4 dana...");

        // Delete by id
//        prepisaniLijekoviDao.deleteById(1);

        // Count
//        prepisaniLijekoviDao.countAllPrescribedMedicine();
//----------------------------------------------------------------------------------------------------------------------
    }
}
