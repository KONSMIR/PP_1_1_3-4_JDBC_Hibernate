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
//        userDao.createUsersTable();
//        userDao.saveUser("Tim", "Kort", (byte) 16);
//        userDao.saveUser("Tom", "Kort", (byte) 18);
//        userDao.saveUser("Time", "Kort", (byte) 20);
//        userDao.saveUser("T", "Kort", (byte) 22);
//        userDao.getAllUsers();
        userDao.cleanUsersTable();

//        UserService userService = new UserServiceImpl();
//
//        userService.createUsersTable();
//
//        userService.saveUser("Oleg", "Petrov", (byte) 22);
//        userService.saveUser("Oliver", "Cars", (byte) 29);
//        userService.saveUser("Sam", "Goch", (byte) 23);
//        userService.saveUser("Mary", "Fox", (byte) 21);
//
//        userService.getAllUsers().forEach(System.out::println);
//
//        userService.cleanUsersTable();
//        userService.dropUsersTable();
    }
}
