package DAO;

import BazaPodataka.HibernateUtil;
import Model.PrepisaniLijekovi;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class PrepisaniLijekoviDaoImpl implements PrepisaniLijekoviDao {
    @Override
    public void insert(PrepisaniLijekovi prepisaniLijekovi) {
        SessionFactory sessionFactory = HibernateUtil.createSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.save(prepisaniLijekovi);
            transaction.commit();
            session.close();
            System.out.println("Zapis uspjesno dodat u tabelu.");

        } catch (HibernateException e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    @Override
    public List<PrepisaniLijekovi> getAllRecords() {
        SessionFactory sessionFactory = HibernateUtil.createSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        Query<PrepisaniLijekovi> query = session.createQuery("FROM PrepisaniLijekovi");
        List<PrepisaniLijekovi> result = query.getResultList();
        for (PrepisaniLijekovi pl : result) {
            System.out.println(pl);
        }

        if (transaction != null) {
            transaction.rollback();
        }

        session.close();
        return result;
    }

    @Override
    public List<PrepisaniLijekovi> paginate(int records) {
        SessionFactory sessionFactory = HibernateUtil.createSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        Query query = session.createQuery("FROM PrepisaniLijekovi");
        query.setFirstResult(0);
        query.setMaxResults(records);

        List<PrepisaniLijekovi> prepisaniLijekoviList = query.getResultList();
        for (PrepisaniLijekovi pl : prepisaniLijekoviList) {
            System.out.println(pl);
        }

        if (transaction != null) {
            transaction.rollback();
        }

        session.close();
        return prepisaniLijekoviList;
    }

    @Override
    public PrepisaniLijekovi getById(int id) {
        SessionFactory sessionFactory = HibernateUtil.createSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        Query<PrepisaniLijekovi> query = session.createQuery("FROM PrepisaniLijekovi WHERE prescribedMedicineId= :prescribedMedicineId");
        query.setLong("prescribedMedicineId", id);
        PrepisaniLijekovi prepisaniLijekovi = (PrepisaniLijekovi) query.uniqueResult();
        System.out.println(prepisaniLijekovi);

        if (transaction != null) {
            transaction.rollback();
        }

        session.close();
        return prepisaniLijekovi;
    }

    @Override
    public void deleteById(int deleteRecord) {
        SessionFactory sessionFactory = HibernateUtil.createSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            PrepisaniLijekovi prepisaniLijekovi = new PrepisaniLijekovi();
            prepisaniLijekovi.setPrescribedMedicineId(deleteRecord);
            session.delete(prepisaniLijekovi);
            System.out.println("Zapis sa id-om:" + deleteRecord + " je uspjesno obrisan.");
            transaction.commit();
            session.close();
        } catch (HibernateException e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            session.close();
        }
    }

    @Override
    public void updateById(int id, String medicineStrength, String medicineDuration, String dosage, String type, String advice) {
        SessionFactory sessionFactory = HibernateUtil.createSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            PrepisaniLijekovi prepisaniLijekovi = new PrepisaniLijekovi();
            prepisaniLijekovi.setPrescribedMedicineId(id);
            prepisaniLijekovi.setPrescribedMedicineStrength(medicineStrength);
            prepisaniLijekovi.setPrescribedMedicineDuration(medicineDuration);
            prepisaniLijekovi.setPrescribedMedicineDosage(dosage);
            prepisaniLijekovi.setPrescribedMedicineType(type);
            prepisaniLijekovi.setPrescribedMedicineAdvice(advice);
            session.update(prepisaniLijekovi);
            transaction.commit();
            session.close();
            System.out.println("Zapis sa id: " + id + " je azuriran.");

        } catch (HibernateException e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            session.close();
        }
    }

    @Override
    public void deleteAllRecords() {
        SessionFactory sessionFactory = HibernateUtil.createSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            Query<PrepisaniLijekovi> query = session.createQuery("FROM PrepisaniLijekovi");
            List<PrepisaniLijekovi> result = query.getResultList();
            session.delete(result);
            transaction.commit();
            session.close();
            System.out.println("Uspjesno obrisani svi zapisi iz tabele.");

        } catch (HibernateException e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            session.close();
        }

    }

    @Override
    public int countAllPrescribedMedicine() {
        int totalPrescribedMedicine = 0;
        SessionFactory sessionFactory = HibernateUtil.createSessionFactory();
        Session session = sessionFactory.openSession();
        try {
            Query query = session.createQuery("select count (*) from PrepisaniLijekovi");
            List users = query.list();
            Number numberOfPrescribedMedicine = (Number) users.get(0);
            System.out.println("Broj prepisanih lijekova u bazi: " + "[" + numberOfPrescribedMedicine.intValue() + "]");
            Long convertedNumber = (Long) numberOfPrescribedMedicine;
            int convertedInt = Math.toIntExact(convertedNumber);

            if (convertedInt != 0) {
                totalPrescribedMedicine = convertedInt;
            } else {
                totalPrescribedMedicine = 0;
            }
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return totalPrescribedMedicine;
    }

}
