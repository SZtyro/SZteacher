package pl.sztyro.szteacher.config;

import com.fasterxml.classmate.AnnotationConfiguration;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Configuration
@EnableTransactionManagement
@EnableAutoConfiguration
public class HibernateUtil {

    @PersistenceContext
    private EntityManager entityManager;


    /**
     * Zwraca otwartą sesję hibernate
     *
     * @return Session
     */
    public Session getSession() {
        Session session = null;
        if (entityManager == null || (session = entityManager.unwrap(Session.class)) == null) {

            throw new NullPointerException();
        }
        //session.beginTransaction();
        return session;
    }
}