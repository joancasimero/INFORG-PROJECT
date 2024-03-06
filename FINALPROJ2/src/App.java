import java.sql.Connection;
import java.util.List;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import controllers.CreateTable; 
import models.User; 
import controllers.Database; 
import controllers.login2; 

public class App extends Application {

    public static void main(String[] args) {
        launch(args);
    }

@Override
public void start(Stage stage) throws Exception {
    try {
        Image image = new Image("/images/logotab.png");

        // Initialize the database connection
        Connection connection = Database.connect();

        if (connection != null) {
            // Create the 'users' table if it doesn't exist
            CreateTable.createTable(connection);

            // Load existing user accounts from the database
            List<User> existingUsers = CreateTable.loadUsers(connection);

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/login2.fxml"));
            Parent root = loader.load();

            login2 loginController = loader.getController();

            // Set the existingUsers in the loginController
            loginController.setExistingUsers(existingUsers);

            Scene scene = new Scene(root);

            stage.setTitle("Kalinaw Express");
            stage.getIcons().add(image);
            stage.setScene(scene);
            stage.show();
        } else {
            // Handle database connection error
            System.out.println("Failed to connect to the database");
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
}
}