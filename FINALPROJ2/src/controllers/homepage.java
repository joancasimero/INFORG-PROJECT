package controllers;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import models.ReservationData;
import models.SharedData;

public class homepage {
    @FXML
    Parent root;
    @FXML
    FXMLLoader loader;
    @FXML
    Button bookbutton, routesbutton, logoutbutton;
    @FXML
    Label usernameLabel;

    private Stage stage;

    @FXML
    public void initialize() {
        SharedData sharedData = SharedData.getInstance();
        String loggedInUsername = sharedData.getLoggedInUsername();
        usernameLabel.setText("Magandang Araw, " + loggedInUsername + "!");
    }

    public void bookaseat(ActionEvent event) throws IOException {
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/bookaseat.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);

        ReservationData reservationData = ReservationData.getInstance();

        bookaseat controller = loader.getController();
        controller.initializeData(reservationData);

        stage.setScene(scene);
        stage.show();
    }

    public void viewroutes(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/viewroutes.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void logout(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/login2.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void initializeData(String username) {
        usernameLabel.setText("Magandang Araw, " + username + "!");
    }
}