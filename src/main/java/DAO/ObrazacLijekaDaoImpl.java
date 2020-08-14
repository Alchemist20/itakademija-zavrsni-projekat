package DAO;

import BazaPodataka.HibernateUtil;
import Model.ObrazacLijeka;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class ObrazacLijekaDaoImpl implements ObrazacLijekaDao {
    @Override
    public void insert(ObrazacLijeka obrazacLijeka) {
        SessionFactory sessionFactory = HibernateUtil.createSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.save(obrazacLijeka);
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
    public List<ObrazacLijeka> getAllRecords() {
        SessionFactory sessionFactory = HibernateUtil.createSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        Query<ObrazacLijeka> query = session.createQuery("FROM ObrazacLijeka");
        List<ObrazacLijeka> result = query.getResultList();
        for (ObrazacLijeka ol : result) {
            System.out.println(ol);
        }

        if (transaction != null) {
            transaction.rollback();
        }

        session.close();
        return result;
    }

    @Override
    public List<ObrazacLijeka> paginate(int records) {
        SessionFactory sessionFactory = HibernateUtil.createSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        Query query = session.createQuery("FROM ObrazacLijeka");
        query.setFirstResult(0);
        query.setMaxResults(records);

        List<ObrazacLijeka> obrazacLijekaList = query.getResultList();
        for (ObrazacLijeka ol : obrazacLijekaList) {
            System.out.println(ol);
        }

        if (transaction != null) {
            transaction.rollback();
        }

        session.close();
        return obrazacLijekaList;
    }

    @Override
    public ObrazacLijeka getById(int id) {
        SessionFactory sessionFactory = HibernateUtil.createSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        Query<ObrazacLijeka> query = session.createQuery("FROM ObrazacLijeka WHERE medicineTemplateId= :medicineTemplateId");
        query.setLong("medicineTemplateId", id);
        ObrazacLijeka obrazacLijeka = (ObrazacLijeka) query.uniqueResult();
        System.out.println(obrazacLijeka);

        if (transaction != null) {
            transaction.rollback();
        }

        session.close();
        return obrazacLijeka;
    }

    @Override
    public void deleteById(int deleteRecord) {
        SessionFactory sessionFactory = HibernateUtil.createSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            ObrazacLijeka obrazacLijeka = new ObrazacLijeka();
            obrazacLijeka.setMedicineTemplateId(deleteRecord);
            session.delete(obrazacLijeka);
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
    public void updateById(int id, String medicineStrength, String duration, String dosage, String type, String advice) {
        SessionFactory sessionFactory = HibernateUtil.createSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            ObrazacLijeka obrazacLijeka = new ObrazacLijeka();
            obrazacLijeka.setMedicineTemplateId(id);
            obrazacLijeka.setMedicineStrength(medicineStrength);
            obrazacLijeka.setMedicineDuration(duration);
            obrazacLijeka.setMedicineDosage(dosage);
            obrazacLijeka.setMedicineType(type);
            obrazacLijeka.setMedicineAdvice(advice);
            session.update(obrazacLijeka);
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
            Query<ObrazacLijeka> query = session.createQuery("FROM ObrazacLijeka");
            List<ObrazacLijeka> result = query.getResultList();
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
    public int countAllMedicineTemplates() {
        int totalMedicineTemplates = 0;
        SessionFactory sessionFactory = HibernateUtil.createSessionFactory();
        Session session = sessionFactory.openSession();
        try {
            Query query = session.createQuery("select count (*) from ObrazacLijeka");
            List users = query.list();
            Number numberOfMedicineTemplates = (Number) users.get(0);
            System.out.println("Broj obrazaca lijeka u bazi: " + "[" + numberOfMedicineTemplates.intValue() + "]");
            Long convertedNumber = (Long) numberOfMedicineTemplates;
            int convertedInt = Math.toIntExact(convertedNumber);

            if (convertedInt != 0) {
                totalMedicineTemplates = convertedInt;
            } else {
                totalMedicineTemplates = 0;
            }
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return totalMedicineTemplates;
    }

    @Override
    public void delete(ObrazacLijeka obrazacLijeka) {
        SessionFactory sessionFactory = HibernateUtil.createSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.delete(obrazacLijeka);
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
}

