package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {

    }

    @Override
    public void createUsersTable() {
        String sqlQuery = "CREATE TABLE `users` (" +
                "  `id` INT NOT NULL AUTO_INCREMENT," +
                "  `name` VARCHAR(45) NOT NULL," +
                "  `lastName` VARCHAR(45) NOT NULL," +
                "  `age` INT NOT NULL," +
                "  PRIMARY KEY (`id`));";

        try (SessionFactory sessionFactory = Util.getSessionFactory()) {
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            session.createSQLQuery(sqlQuery).addEntity(Util.class)
                    .executeUpdate();
            session.getTransaction().commit();
        }
    }

    @Override
    public void dropUsersTable() {
        try (SessionFactory sessionFactory = Util.getSessionFactory()) {
            Session session = sessionFactory.openSession();
            session.beginTransaction();

            session.createSQLQuery("DROP TABLE IF EXISTS 'users';").addEntity(Util.class)
                    .executeUpdate();
            session.getTransaction().commit();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try (SessionFactory sessionFactory = Util.getSessionFactory()) {
            Session session = sessionFactory.getCurrentSession();
            session.beginTransaction();
            User user = new User(name, lastName, age);
            session.save(user);
            session.getTransaction().commit();
            System.out.println("User с именем – " + name + " добавлен в базу данных");
        }
    }

    @Override
    public void removeUserById(long id) {
        try (SessionFactory sessionFactory = Util.getSessionFactory()) {
            Session session = sessionFactory.getCurrentSession();
            session.beginTransaction();
            User user = session.get(User.class, id);
            session.delete(user);
            session.remove(user);
            session.getTransaction().commit();
        }
    }

    @Override
    public List<User> getAllUsers() {
        try (SessionFactory sessionFactory = Util.getSessionFactory()) {
            Session session = sessionFactory.getCurrentSession();
            session.beginTransaction();
            List<User> users = session.createQuery("FROM User").getResultList();
            session.getTransaction().commit();
            return users;
        }
    }

    @Override
    public void cleanUsersTable() {
        try (SessionFactory sessionFactory = Util.getSessionFactory()) {
            Session session = sessionFactory.getCurrentSession();
            session.beginTransaction();
            session.createSQLQuery("TRUNCATE users;").executeUpdate();
            session.getTransaction().commit();
        }
    }
}
