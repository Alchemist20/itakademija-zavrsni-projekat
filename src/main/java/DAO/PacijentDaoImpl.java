package DAO;

import BazaPodataka.HibernateUtil;
import Model.Pacijent;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;


public class PacijentDaoImpl implements PacijentDao {

    @Override
    public void insert(String fullName, String dob, String address, String gender, String telephone, String email) {
        SessionFactory sessionFactory = HibernateUtil.createSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            Pacijent pacijent = new Pacijent();
            pacijent.setFullName(fullName);
            pacijent.setDob(dob);
            pacijent.setAddress(address);
            pacijent.setGender(gender);
            pacijent.setPhoneNumber(telephone);
            pacijent.setEmail(email);

            session.save(pacijent);
            transaction.commit();
            if (session.isOpen()) {
                session.close();
            }
            System.out.println("Zapis uspjesno dodat u tabelu.");

        } catch (HibernateException e) {
            e.printStackTrace();
            System.out.println("Unos pacijenta u bazu nije uspio!");
            if (transaction != null) {
                transaction.rollback();
            }
        }

    }

    @Override
    public List<Pacijent> getAllRecords() throws HibernateException {
        SessionFactory sessionFactory = HibernateUtil.createSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        Query<Pacijent> query = session.createQuery("FROM Pacijent");
        List<Pacijent> result = query.getResultList();
        for (Pacijent p : result) {
            System.out.println(p);
        }

        if (transaction != null) {
            transaction.rollback();
        }

        session.close();
        return result;

    }

    @Override
    public List<Pacijent> paginate(int records) throws HibernateException {
        SessionFactory sessionFactory = HibernateUtil.createSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        Query query = session.createQuery("FROM Pacijent");
        query.setFirstResult(0);
        query.setMaxResults(records);

        List<Pacijent> pacijentList = query.getResultList();
        for (Pacijent p : pacijentList) {
            System.out.println(p);
        }

        if (transaction != null) {
            transaction.rollback();
        }

        session.close();
        return pacijentList;
    }

    @Override
    public Pacijent getById(int id) {
        SessionFactory sessionFactory = HibernateUtil.createSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        Query<Pacijent> query = session.createQuery("FROM Pacijent WHERE patientId= :patientId");
        query.setLong("patientId", id);
        Pacijent pacijent = (Pacijent) query.uniqueResult();
        System.out.println(pacijent);

        if (transaction != null) {
            transaction.rollback();
        }

        session.close();
        return pacijent;
    }

    @Override
    public void deleteById(int deleteRecord) {
        SessionFactory sessionFactory = HibernateUtil.createSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            Pacijent pacijent = new Pacijent();
            pacijent.setPatientId(deleteRecord);
            session.delete(pacijent);
            System.out.println("Zapis sa id-om: " + deleteRecord + "je uspjesno obrisan.");
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
    public void updateById(int id, String fullName, String dob, String address, String gender, String telephone, String email) {
        SessionFactory sessionFactory = HibernateUtil.createSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            Pacijent pacijent = new Pacijent();
            pacijent.setPatientId(id);
            pacijent.setFullName(fullName);
            pacijent.setDob(dob);
            pacijent.setAddress(address);
            pacijent.setGender(gender);
            pacijent.setPhoneNumber(telephone);
            pacijent.setEmail(email);

            session.update(pacijent);
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
            Query<Pacijent> query = session.createQuery("FROM Pacijent");
            List<Pacijent> result = query.getResultList();
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
    public int countAllPatients() {
        int totalPatients = 0;
        SessionFactory sessionFactory = HibernateUtil.createSessionFactory();
        Session session = sessionFactory.openSession();
        try {
            Query query = session.createQuery("select count (*) from Pacijent");
            List patients = query.list();
            Number numberOfPatients = (Number) patients.get(0);
            System.out.println("Broj pacijenta u bazi: " + "[" + numberOfPatients.intValue() + "]");
            Long convertedNumber = (Long) numberOfPatients;
            int convertedInt = Math.toIntExact(convertedNumber);

            if (convertedInt != 0) {
                totalPatients = convertedInt;
            } else {
                totalPatients = 0;
            }
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return totalPatients;
    }

}
