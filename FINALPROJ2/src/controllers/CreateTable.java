package controllers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import models.User;

public class CreateTable {

    public static void createTable(Connection connection) {
        try {
            String createTableSQL = "CREATE TABLE IF NOT EXISTS users (id INT AUTO_INCREMENT PRIMARY KEY, username VARCHAR(255), password VARCHAR(255), firstname VARCHAR(255), middlename VARCHAR(255), lastname VARCHAR(255))";
            Statement statement = connection.createStatement();
            statement.executeUpdate(createTableSQL);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static boolean addUser(Connection connection, String username, String password, String firstName, String middleName, String lastName) {
        try {
            String insertUserSQL = "INSERT INTO users (username, password, firstname, middlename, lastname) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(insertUserSQL);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            preparedStatement.setString(3, firstName);
            preparedStatement.setString(4, middleName);
            preparedStatement.setString(5, lastName);

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0; // Check if the user was added successfully
        } catch (SQLException e) {
            e.printStackTrace();
            return false; // User addition failed
        }
    }

    public static boolean usernameExists(Connection connection, String username) {
        try {
            String selectUserSQL = "SELECT * FROM users WHERE username = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(selectUserSQL);
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next(); // Returns true if username exists, false otherwise
        } catch (SQLException e) {
            e.printStackTrace();
            return false; // Error occurred, return false
        }
    }

    public static List<User> loadUsers(Connection connection) {
        List<User> userList = new ArrayList<>();

        try {
            String selectUsersSQL = "SELECT * FROM users";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(selectUsersSQL);

            while (resultSet.next()) {
                String username = resultSet.getString("username");
                String password = resultSet.getString("password");
                String firstName = resultSet.getString("firstname");
                String middleName = resultSet.getString("middlename");
                String lastName = resultSet.getString("lastname");
                User user = new User(username, password, firstName, middleName, lastName); // Create a User object
                userList.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return userList;
    }
}
