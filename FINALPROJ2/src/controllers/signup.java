package controllers;

import java.io.IOException;
import java.sql.Connection;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;

public class signup {

    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;

    @FXML
private TextField firstnameField;
@FXML
private TextField middlenameField;
@FXML
private TextField lastnameField;

     @FXML
    Button createaccountbutton, signupbackbutton;

    @FXML
    Parent root;
  
    @FXML
    FXMLLoader loader;
  

    // Define a connection field
    private Connection connection;

    public signup() {
        // Constructor: Create a database connection
        connection = Database.connect();
        if (connection == null) {
            // Handle database connection error and show an alert
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Database Connection Error");
        alert.setHeaderText("Failed to connect to the database");
        alert.setContentText("Please check your database connection settings.");

        alert.showAndWait();
    }
}

public void makeaccount(ActionEvent event) throws IOException {
    String newUsername = usernameField.getText();
    String newPassword = passwordField.getText();
    String firstName = firstnameField.getText();
    String middleName = middlenameField.getText();
    String lastName = lastnameField.getText();
    
    if (newUsername.isEmpty() || newPassword.isEmpty() || firstName.isEmpty() || lastName.isEmpty()) {
        showAlert(AlertType.ERROR, "Error", "Empty Fields", "Please fill in all fields.");
        return; 
    }

    if (connection != null) {
        // Check if the username already exists
        boolean usernameExists = CreateTable.usernameExists(connection, newUsername);

        if (usernameExists) {
            showAlert(AlertType.ERROR, "Error", "Username Already Exists", "The username you entered already exists. Please choose a different one.");
            return;
        }

        boolean userAdded = CreateTable.addUser(connection, newUsername, newPassword, firstName, middleName, lastName);

        if (userAdded) {
            showAlert(AlertType.INFORMATION, "Success", "Account Created", "You have successfully created an account.");
        } else {
            showAlert(AlertType.ERROR, "Error", "Account Creation Failed", "Failed to create the account. Please try again.");
        }
    } else {
        showAlert(AlertType.ERROR, "Error", "Database Connection Failed", "Failed to connect to the database. Please check your database settings.");
    }
    
    // Clear the input fields or perform other necessary actions
    usernameField.clear();
    passwordField.clear();
    firstnameField.clear();
    middlenameField.clear();
    lastnameField.clear();
}
    
    private void showAlert(AlertType type, String title, String headerText, String contentText) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.setContentText(contentText);
        alert.showAndWait();
    }

     public void signupback(ActionEvent event) throws IOException {

    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/login2.fxml"));
    Parent root = loader.load();
    Scene scene = new Scene(root);
    stage.setScene(scene);
    stage.show();
  }
}