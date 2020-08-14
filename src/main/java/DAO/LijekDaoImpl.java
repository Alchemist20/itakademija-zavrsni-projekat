package DAO;

import BazaPodataka.HibernateUtil;
import Model.Lijek;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class LijekDaoImpl implements LijekDao {
    @Override
    public void insert(String name, String genericName, String note) {
        SessionFactory sessionFactory = HibernateUtil.createSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            Lijek lijek = new Lijek();
            lijek.setMedicineName(name);
            lijek.setGenericName(genericName);
            lijek.setNote(note);

            session.save(lijek);
            transaction.commit();
            session.close();
            System.out.println("Zapis uspjesno dodat u tabelu.");

        } catch (HibernateException e) {
            e.printStackTrace();
            System.out.println("Unos lijeka u bazu nije uspio!");
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    @Override
    public List<Lijek> getAllRecords() {
        SessionFactory sessionFactory = HibernateUtil.createSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        Query<Lijek> query = session.createQuery("FROM Lijek");
        List<Lijek> result = query.getResultList();
        for (Lijek l : result) {
            System.out.println(l);
        }

        if (transaction != null) {
            transaction.rollback();
        }

        session.close();
        return result;
    }


    @Override
    public List<Lijek> paginate(int records) {
        SessionFactory sessionFactory = HibernateUtil.createSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        Query query = session.createQuery("FROM Lijek");
        query.setFirstResult(0);
        query.setMaxResults(records);

        List<Lijek> lijekList = query.getResultList();
        for (Lijek l : lijekList) {
            System.out.println(l);
        }

        if (transaction != null) {
            transaction.rollback();
        }

        session.close();
        return lijekList;
    }

    @Override
    public Lijek getById(int id) {
        SessionFactory sessionFactory = HibernateUtil.createSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        Query<Lijek> query = session.createQuery("FROM Lijek WHERE id_lijeka= :id_lijeka");
        query.setLong("id_lijeka", id);
        Lijek lijek = (Lijek) query.uniqueResult();
        System.out.println(lijek);

        if (transaction != null) {
            transaction.rollback();
        }

        if (session.isOpen()) {
            session.close();
        }
        return lijek;
    }

    @Override
    public void deleteById(int deleteRecord) {
        SessionFactory sessionFactory = HibernateUtil.createSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            Lijek lijek = new Lijek();
            lijek.setMedicineId(deleteRecord);
            session.delete(lijek);
            System.out.println("Zapis sa id-om:" + deleteRecord + " je uspjesno obrisan.");
            transaction.commit();
            session.close();
        } catch (HibernateException e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            if (session.isOpen()) {
                session.close();
            }
        }

    }

    @Override
    public void updateById(int id, String name, String genericName, String note) {
        SessionFactory sessionFactory = HibernateUtil.createSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            Lijek lijek = new Lijek();
            lijek.setMedicineId(id);
            lijek.setMedicineName(name);
            lijek.setGenericName(genericName);
            lijek.setNote(note);

            session.update(lijek);
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
            Query<Lijek> query = session.createQuery("FROM Lijek");
            List<Lijek> result = query.getResultList();
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
    public int countAllDrugs() {
        int totalDrugs = 0;
        SessionFactory sessionFactory = HibernateUtil.createSessionFactory();
        Session session = sessionFactory.openSession();
        try {
            Query query = session.createQuery("select count (*) from Lijek");
            List users = query.list();
            Number numberOfDrugs = (Number) users.get(0);
            System.out.println("Broj lijekova u bazi: " + "[" + numberOfDrugs.intValue() + "]");
            Long convertedNumber = (Long) numberOfDrugs;
            int convertedInt = Math.toIntExact(convertedNumber);

            if (convertedInt != 0) {
                totalDrugs = convertedInt;
            } else {
                totalDrugs = 0;
            }
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return totalDrugs;
    }
}

