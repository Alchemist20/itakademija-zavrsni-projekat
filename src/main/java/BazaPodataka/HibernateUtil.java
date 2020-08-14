package BazaPodataka;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class HibernateUtil {
    private static SessionFactory sessionFactory;
    private static ServiceRegistry serviceRegistry;

    public static SessionFactory createSessionFactory() {
        try {
            Configuration configuration = new Configuration();
            configuration.addAnnotatedClass(Model.Korisnik.class);
            configuration.addAnnotatedClass(Model.Lijek.class);
            configuration.addAnnotatedClass(Model.ObrazacLijeka.class);
            configuration.addAnnotatedClass(Model.Pacijent.class);
            configuration.addAnnotatedClass(Model.PrepisaniLijekovi.class);
            configuration.addAnnotatedClass(Model.Recept.class);
            configuration.configure("/Hibernate/hibernate.cfg.xml");
            serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
            sessionFactory = configuration.buildSessionFactory(serviceRegistry);
            return sessionFactory;
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static void shutdown() {
        getSessionFactory().close();
    }

}