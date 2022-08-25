package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();

        userService.createUsersTable();

        userService.saveUser("Oleg", "Petrov", (byte) 22);
        userService.saveUser("Oliver", "Cars", (byte) 29);
        userService.saveUser("Sam", "Goch", (byte) 23);
        userService.saveUser("Mary", "Fox", (byte) 21);

        userService.getAllUsers().forEach(System.out::println);

        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}
