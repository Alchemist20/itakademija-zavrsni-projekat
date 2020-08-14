package DAO;

import BazaPodataka.DBKonekcija;
import BazaPodataka.HibernateUtil;
import Model.Korisnik;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class KorisnikDaoImpl implements KorisnikDao {
    DBKonekcija connection = new DBKonekcija();
    Connection con = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    @Override
    public void insert(String fullName, String username, String password, String email) {
        SessionFactory sessionFactory = HibernateUtil.createSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            Korisnik korisnik = new Korisnik();
            korisnik.setFullName(fullName);
            korisnik.setUsername(username);
            korisnik.setPassword(password);
            korisnik.setEmail(email);

            session.save(korisnik);
            transaction.commit();
            if (session.isOpen()) {
                session.close();
            }
            System.out.println("Zapis uspjesno dodat u tabelu.");

        } catch (HibernateException e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        }

    }

    @Override
    public List<Korisnik> getAllRecords() throws HibernateException {
        SessionFactory sessionFactory = HibernateUtil.createSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        Query<Korisnik> query = session.createQuery("FROM Korisnik");
        List<Korisnik> result = query.getResultList();
        for (Korisnik k : result) {
            System.out.println(k);
        }

        if (transaction != null) {
            transaction.rollback();
        }

        if (session.isOpen()) {
            session.close();
        }
        return result;
    }

    @Override
    public List<Korisnik> paginate(int records) throws HibernateException {
        SessionFactory sessionFactory = HibernateUtil.createSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        Query query = session.createQuery("FROM Korisnik");
        query.setFirstResult(0);
        query.setMaxResults(records);

        List<Korisnik> korisnikList = query.getResultList();
        for (Korisnik k : korisnikList) {
            System.out.println(k);
        }

        if (transaction != null) {
            transaction.rollback();
        }

        if (session.isOpen()) {
            session.close();
        }
        return korisnikList;
    }

    @Override
    public Korisnik getById(int id) throws HibernateException {
        SessionFactory sessionFactory = HibernateUtil.createSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        Query<Korisnik> query = session.createQuery("FROM Korisnik WHERE korisnicki_id= :korisnicki_id");
        query.setLong("korisnicki_id", id);
        Korisnik korisnik = (Korisnik) query.uniqueResult();
        System.out.println(korisnik);

        if (transaction != null) {
            transaction.rollback();
        }

        if (session.isOpen()) {
            session.close();
        }
        return korisnik;
    }

    @Override
    public void deleteById(int deleteRecord) {
        SessionFactory sessionFactory = HibernateUtil.createSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            Korisnik korisnik = new Korisnik();
            korisnik.setUserId(deleteRecord);
            session.delete(korisnik);
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
    public void updateById(int id, String fullName, String username, String password, String email) {
        con = connection.geConnection();
        try {
            pst = con.prepareStatement("update korisnik set ime_i_prezime=? ,korisnicko_ime=?, sifra=?, email=? where korisnicki_id=?");
            pst.setString(1, fullName);
            pst.setString(2, username);
            pst.setString(3, password);
            pst.setString(4, email);
            pst.setInt(5, id);

            pst.executeUpdate();
            pst.close();
            con.close();
            connection.con.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }


    @Override
    public void deleteAllRecords() {
        SessionFactory sessionFactory = HibernateUtil.createSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            Query<Korisnik> query = session.createQuery("FROM Korisnik");
            List<Korisnik> result = query.getResultList();
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
            if (session.isOpen()) {
                session.close();
            }
        }
    }

    @Override
    public int countAllUsers() {
        int totalUsers = 0;
        SessionFactory sessionFactory = HibernateUtil.createSessionFactory();
        Session session = sessionFactory.openSession();
        try {
            Query query = session.createQuery("select count (*) from Korisnik");
            List users = query.list();
            Number numberOfUsers = (Number) users.get(0);
            System.out.println("Broj korisnika u bazi: " + "[" + numberOfUsers.intValue() + "]");
            Long convertedNumber = (Long) numberOfUsers;
            int convertedInt = Math.toIntExact(convertedNumber);

            if (convertedInt != 0) {
                totalUsers = convertedInt;
            } else {
                totalUsers = 0;
            }
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return totalUsers;
    }

}




