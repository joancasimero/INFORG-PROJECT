package controllers;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.stage.Stage;
import models.ReservationData;

import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;

public class bookaseat extends Application {

    @FXML
    Parent root;

    @FXML
    FXMLLoader loader;

    @FXML
    Button bookbackbutton, cancelbutton, reservebutton;

    @FXML
    private ChoiceBox<String> dateBox, fromBox, toBox, seatBox, timeBox;

    private Stage stage;

    public void bookback(ActionEvent event) throws IOException {
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/homepage.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
    @FXML
private void reserveSeat(ActionEvent event) {
    String selectedSeat = seatBox.getValue();
    String selectedDate = dateBox.getValue();
    String selectedTime = timeBox.getValue();
    String selectedFromPlace = fromBox.getValue();
    String selectedToPlace = toBox.getValue();

    if (selectedSeat != null && selectedDate != null && selectedTime != null) {
        if (selectedFromPlace.equals(selectedToPlace)) {
            // Display an alert indicating that "From" and "To" places are the same
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Invalid Selection");
            alert.setHeaderText("Invalid selection of departure and arrival place.");
            alert.setContentText("Please check and try again.");
            alert.showAndWait();
        } else {
            String reservationKey = selectedDate + " " + selectedTime + " " + selectedSeat;

            if (reservedSeats.contains(reservationKey)) {
                // Display an alert indicating that the seat is already reserved
                Alert alert = new Alert(AlertType.WARNING);
                alert.setTitle("Seat Already Reserved");
                alert.setHeaderText("The selected seat is already reserved for the specified date and time.");
                alert.setContentText("Please choose another seat, date, or departure time.");
                alert.showAndWait();
            } else {
                // Add the reservation to the list of reserved seats
                reservedSeats.add(reservationKey);
                seatBox.setItems(FXCollections.observableArrayList(availableSeats));

                // Display a confirmation message with reservation details
                Alert confirmation = new Alert(AlertType.INFORMATION);
                confirmation.setTitle("Reservation Successful");
                confirmation.setHeaderText("Seat Reserved Successfully");
                confirmation.setContentText("Date: " + selectedDate + "\n" +
                        "Time: " + selectedTime + "\n" +
                        "From: " + selectedFromPlace + "\n" +
                        "To: " + selectedToPlace + "\n" +
                        "Seat: " + selectedSeat + " has been reserved.");
                confirmation.showAndWait();
            }
        }
    } else {
        // Display an error message if any of the required details are missing
        Alert error = new Alert(AlertType.ERROR);
        error.setTitle("Reservation Error");
        error.setHeaderText("Incomplete Reservation Details");
        error.setContentText("Please select a valid date, time, and seat.");
        error.showAndWait();
    }
}

@FXML
private void cancelBooking(ActionEvent event) {
    String selectedSeat = seatBox.getValue();
    String selectedDate = dateBox.getValue();
    String selectedTime = timeBox.getValue();

    if (selectedSeat != null && selectedDate != null && selectedTime != null) {
        String reservationKey = selectedDate + " " + selectedTime + " " + selectedSeat;

        if (reservedSeats.contains(reservationKey)) {
            // Remove the reservation from the list of reserved seats
            reservedSeats.remove(reservationKey);
            // Add the seat back to the list of available seats
            availableSeats.add(selectedSeat);
            seatBox.setItems(FXCollections.observableArrayList(availableSeats));

            // Display a confirmation message for seat cancellation
            Alert confirmation = new Alert(AlertType.INFORMATION);
            confirmation.setTitle("Cancellation Successful");
            confirmation.setHeaderText("Seat Canceled Successfully");
            confirmation.setContentText("Seat: " + selectedSeat + " for " + selectedDate + " at " + selectedTime + " has been canceled.");
            confirmation.showAndWait();
        } else {
            // Display an alert indicating that the seat is not reserved
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Seat Not Reserved");
            alert.setHeaderText("The selected seat is not currently reserved for the specified date and time.");
            alert.setContentText("Please select a reserved seat for cancellation.");
            alert.showAndWait();
        }
    } else {
        // Display an error message if any of the required details are missing
        Alert error = new Alert(AlertType.ERROR);
        error.setTitle("Cancellation Error");
        error.setHeaderText("Incomplete Cancellation Details");
        error.setContentText("Please select a valid seat, date, and time for cancellation.");
        error.showAndWait();
    }
}

    private LinkedList<String> availableSeats;
    private Queue<String> reservedSeats;

    @FXML
    private void initialize() {
        // Initialize the reservedSeats queue
    reservedSeats = new LinkedList<>();

        ObservableList<String> dates = FXCollections.observableArrayList("2023-11-18", "2023-11-25", "2023-12-02");
        ObservableList<String> locations = FXCollections.observableArrayList("Cubao", "Baguio");
        ObservableList<String> departureTimes = FXCollections.observableArrayList("05:00 AM", "01:00 PM", "06:00 PM");

        dateBox.setItems(dates);
        fromBox.setItems(locations);
        toBox.setItems(locations);

        availableSeats = generateAvailableSeats();
        seatBox.setItems(FXCollections.observableArrayList(availableSeats));

        timeBox.setItems(departureTimes);

          ReservationData reservationData = ReservationData.getInstance();

    seatBox.setItems(FXCollections.observableArrayList(reservationData.getAvailableSeats()));
    }

    private LinkedList<String> generateAvailableSeats() {
        LinkedList<String> seats = new LinkedList<>();
        for (int seatNumber = 1; seatNumber <= 53; seatNumber++) {
            seats.add("Seat " + seatNumber);
        }
        return seats;
    }

  @FXML
  Button viewReservationsButton;
  
  @FXML
  private void viewReservations(ActionEvent event) {
      // Create a StringBuilder to build the list of reservations.
      StringBuilder reservationsList = new StringBuilder("Your Reservations:\n\n");
  
      for (String reservation : reservedSeats) {
          reservationsList.append(reservation).append("\n");
      }
  
      // a dialog or new window to display the reservations.
      Alert reservationsDialog = new Alert(AlertType.INFORMATION);
      reservationsDialog.setTitle("Your Reservations");
      reservationsDialog.setHeaderText(null);
      reservationsDialog.setContentText(reservationsList.toString());
      reservationsDialog.showAndWait();
  }
  
  public void initializeData(ReservationData reservationData) {
    this.reservedSeats = reservationData.getReservedSeats();
    this.availableSeats = reservationData.getAvailableSeats();
}

    @Override
    public void start(Stage arg0) throws Exception {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'start'");
    }
}