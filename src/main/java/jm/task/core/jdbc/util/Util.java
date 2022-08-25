package jm.task.core.jdbc.util;

import java.sql.*;

public class Util {
    // реализуйте настройку соеденения с БД
    private static final String url = "jdbc:mysql://localhost:3306/sca_db";
    private static final String user = "root";
    private static final String password = "Malkavian1610";

    private Connection dbConnection;

    public Connection getDbConnection() throws SQLException {
        //Class.forName("com.mysql.cj.jdbc.Driver");
        dbConnection = DriverManager.getConnection(url, user, password);
        return dbConnection;
    }
}
