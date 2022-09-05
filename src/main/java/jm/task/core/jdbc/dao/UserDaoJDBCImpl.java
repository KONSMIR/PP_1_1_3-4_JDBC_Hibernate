package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    public UserDaoJDBCImpl() {
    }

    public void createUsersTable() {
        try (Connection connection = Util.getDbConnection()) {
            try (Statement st = connection.createStatement()) {
                st.executeUpdate("CREATE TABLE `users` (" +
                        "  `id` INT NOT NULL AUTO_INCREMENT," +
                        "  `name` VARCHAR(45) NOT NULL," +
                        "  `lastName` VARCHAR(45) NOT NULL," +
                        "  `age` INT NOT NULL," +
                        "  PRIMARY KEY (`id`));");
            }
        } catch (SQLException e) {
            System.out.println("ошибка SQLException при создании таблицы");
        }
    }

    public void dropUsersTable() {
        try (Connection connection = Util.getDbConnection()) {
            try (Statement st = connection.createStatement()) {
                st.executeUpdate("DROP TABLE IF EXISTS `users`;");
            }
        } catch (SQLException e) {
            System.out.println("ошибка SQLException при удалении таблицы");
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        int ageInt = age;
        try (Connection connection = Util.getDbConnection()) {
            PreparedStatement prSt = connection.prepareStatement(
                    "INSERT users(name, lastName, age) VALUES (?,?,?)");
            prSt.setString(1, name);
            prSt.setString(2, lastName);
            prSt.setInt(3, ageInt);
            prSt.executeUpdate();
            System.out.println("User с именем – " + name + " добавлен в базу данных");
        } catch (SQLException e) {
            System.out.println("ошибка SQLException при добавлении пользователя");
        }
    }

    public void removeUserById(long id) {
        try (Connection connection = Util.getDbConnection()) {
            try (Statement st = connection.createStatement()) {
                st.executeUpdate("DELETE FROM users WHERE Id = " + id);
            }
        } catch (SQLException e) {
            System.out.println("ошибка SQLException");
        }
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        try (Connection connection = Util.getDbConnection()) {
            try (Statement st = connection.createStatement()) {
                try (ResultSet rs = st.executeQuery("SELECT * FROM users")) {
                    while (rs.next()) {
                        User user = new User(rs.getString("name"),
                                rs.getString("lastName"), rs.getByte("age"));
                        user.setId(rs.getLong("id"));
                        users.add(user);
                    }
                }
            }
            //users.forEach(System.out::println);
        } catch (SQLException e) {
            System.out.println("ошибка SQLException при выгрузке списка");
        }
        return users;
    }

    public void cleanUsersTable() {
        try (Connection connection = Util.getDbConnection()) {
            try (Statement st = connection.createStatement()) {
                st.executeUpdate("TRUNCATE users");
            }
        } catch (SQLException e) {
            System.out.println("ошибка SQLException");
        }
    }
}
