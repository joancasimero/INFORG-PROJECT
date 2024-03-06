package controllers;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import models.SharedData;
import models.User;

public class login2 {

    private Connection connection;

    public login2() {
        connection = Database.connect(); 
        if (connection == null) {
            System.out.println("Failed to connect to the database");
        }
    }

    @FXML
    private TextField usernamefield;

    @FXML
    private PasswordField passwordfield;

    @FXML
    private Label invalidlabel, invalidlabel2;

    private Stage stage;

    public void setExistingUsers(List<User> existingUsers) {
        // You can do something with existingUsers if needed
    }

    public void login(ActionEvent event) throws IOException {
        String loggedInUsername = "";

        String enteredUsername = usernamefield.getText();
        String enteredPassword = passwordfield.getText();

        boolean authenticated = false;

        String query = "SELECT username FROM users WHERE username = ? AND password = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, enteredUsername);
            preparedStatement.setString(2, enteredPassword);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                loggedInUsername = resultSet.getString("username");
                authenticated = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (authenticated) {
            SharedData sharedData = SharedData.getInstance();
            sharedData.setLoggedInUsername(loggedInUsername);

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/homepage.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);

            homepage controller = loader.getController();
            controller.initializeData(loggedInUsername);

            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } else {
            invalidlabel.setVisible(true);
            invalidlabel2.setVisible(true);
        }

      }
  
  public void meettheteam(ActionEvent event) throws IOException {
  
      stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
      FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/meettheteam.fxml"));
      Parent root = loader.load();
      Scene scene = new Scene(root);
      stage.setScene(scene);
      stage.show();
  
    }
  
    public void contactus(ActionEvent event) throws IOException {
  
      stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
      FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/contactus.fxml"));
      Parent root = loader.load();
      Scene scene = new Scene(root);
      stage.setScene(scene);
      stage.show();
  
    }
  
    public void aboutus(ActionEvent event) throws IOException {
  
      stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
      FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/aboutus.fxml"));
      Parent root = loader.load();
      Scene scene = new Scene(root);
      stage.setScene(scene);
      stage.show();
  
    }
  
    public void createpage(ActionEvent event) throws IOException {
  
      stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
      FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/signup.fxml"));
      Parent root = loader.load();
      Scene scene = new Scene(root);
      stage.setScene(scene);
      stage.show();
  
    }
  }