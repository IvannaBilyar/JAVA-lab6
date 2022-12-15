package database;

import lab6.Teacher;

import java.sql.*;
public class DBManager implements AutoCloseable {
    static final String url = "jdbc:mysql://127.0.0.1:330/foreign_language_school";
    static final String user = "root";
    static final  String pass = "bilyarivanka07";
    private Connection connection;

    public DBManager() {
        try {
            connection = DriverManager.getConnection(url, user, pass);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void close() {
        try {
            connection.close();
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
    }


    public boolean executeUpdate(String query) {
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);
            return true;
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
            return false;
        }
    }


    public ResultSet getData(String query) {
        try {
            Statement statement = connection.createStatement();
            return statement.executeQuery(query);
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
            return null;
        }
    }


    public PreparedStatement getPreparedStatement(String query) throws SQLException {
        return connection.prepareStatement(query);
    }
}

