package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Main {
    public static void main(String[] args) {
        UserDao userDao = new UserDaoHibernateImpl();

        userDao.createUsersTable();

        userDao.saveUser("Oleg", "Petrov", (byte) 22);
        userDao.saveUser("Oliver", "Cars", (byte) 29);
        userDao.saveUser("Sam", "Goch", (byte) 23);
        userDao.saveUser("Mary", "Fox", (byte) 21);

        userDao.getAllUsers().forEach(System.out::println);

        userDao.cleanUsersTable();
        userDao.dropUsersTable();
    }
}
