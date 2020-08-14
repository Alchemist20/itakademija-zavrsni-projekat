package DAO;

import BazaPodataka.HibernateUtil;
import Model.Recept;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class ReceptDaoImpl implements ReceptDao {

    @Override
    public void insert(Recept recept) {
        SessionFactory sessionFactory = HibernateUtil.createSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.save(recept);
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
    public List<Recept> getAllRecords() {
        SessionFactory sessionFactory = HibernateUtil.createSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        Query<Recept> query = session.createQuery("FROM Recept");
        List<Recept> result = query.getResultList();
        for (Recept r : result) {
            System.out.println(r);
        }

        if (transaction != null) {
            transaction.rollback();
        }

        session.close();
        return result;
    }

    @Override
    public List<Recept> paginate(int records) {
        SessionFactory sessionFactory = HibernateUtil.createSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        Query query = session.createQuery("FROM Recept");
        query.setFirstResult(0);
        query.setMaxResults(records);

        List<Recept> receptList = query.getResultList();
        for (Recept r : receptList) {
            System.out.println(r);
        }

        if (transaction != null) {
            transaction.rollback();
        }

        session.close();
        return receptList;
    }

    @Override
    public Recept getById(int id) {
        SessionFactory sessionFactory = HibernateUtil.createSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        Query<Recept> query = session.createQuery("FROM Recept WHERE prescriptionId= :prescriptionId");
        query.setLong("prescriptionId", id);
        Recept recept = (Recept) query.uniqueResult();
        System.out.println(recept);

        if (transaction != null) {
            transaction.rollback();
        }

        session.close();
        return recept;
    }

    @Override
    public void deleteById(int deleteRecord) {
        SessionFactory sessionFactory = HibernateUtil.createSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            Recept recept = new Recept();
            recept.setPrescriptionId(deleteRecord);
            session.delete(recept);
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
    public void updateById(int id, String date, String dosage, String advice, String nextExaminationDate) {
        SessionFactory sessionFactory = HibernateUtil.createSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            Recept recept = new Recept();
            recept.setPrescriptionId(id);
            recept.setDate(date);
            recept.setDosage(dosage);
            recept.setAdvice(advice);
            recept.setNextExamination(nextExaminationDate);
            session.update(recept);
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
            Query<Recept> query = session.createQuery("FROM Recept");
            List<Recept> result = query.getResultList();
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
    public int countAllPrescriptions() {
        int totalPrescriptions = 0;
        SessionFactory sessionFactory = HibernateUtil.createSessionFactory();
        Session session = sessionFactory.openSession();
        try {
            Query query = session.createQuery("select count (*) from Recept");
            List users = query.list();
            Number numberOfPrescriptions = (Number) users.get(0);
            System.out.println("Broj recepta u bazi: " + "[" + numberOfPrescriptions.intValue() + "]");
            Long convertedNumber = (Long) numberOfPrescriptions;
            int convertedInt = Math.toIntExact(convertedNumber);

            if (convertedInt != 0) {
                totalPrescriptions = convertedInt;
            } else {
                totalPrescriptions = 0;
            }
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return totalPrescriptions;
    }

}
