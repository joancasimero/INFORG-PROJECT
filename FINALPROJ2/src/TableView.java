import controllers.Database;
import controllers.CreateTable;
import models.User;

import java.sql.Connection;
import java.util.List;

public class TableView {
    public static void main(String[] args) {
        // Establish a connection to the database
        Connection connection = Database.connect();

        // Load users from the database
        List<User> users = CreateTable.loadUsers(connection);
        System.out.println("Users:");
        for (User user : users) {
            System.out.println("Username: " + user.getUsername() + ", Password: " + user.getPassword());
        }

        // Close the connection
        try {
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
