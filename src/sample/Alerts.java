package sample;

import javafx.scene.control.Alert;
import javafx.stage.Modality;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


/**
 * This class is comprised of alerts which handle various exceptions.
 */
public class Alerts {


    /**
     * This method verifies that the user fills out all textfields of customer data before attempting to
     * add a new customer.
     */
    public static void checkFields() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.initModality(Modality.NONE);
        alert.setTitle("Error");
        alert.setHeaderText("Alert ");
        alert.setContentText("Please fill out all fields to add a new customer");
        alert.showAndWait();
    }


    /**
     * This method verifies that all fields are filled out correctly before a user can attempt to modify customer
     * records
     */
    public static void checkFields2() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.initModality(Modality.NONE);
        alert.setTitle("Error");
        alert.setHeaderText("Alert ");
        alert.setContentText("Please fill out all fields to modify the selected customer's records");
        alert.showAndWait();
    }

    /**
     * This method verifies that the user has selected a customer in the tableview before attempting to modify
     * a selected customer's info.
     */
    public static void modHandler() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.initModality(Modality.NONE);
        alert.setTitle("Error");
        alert.setHeaderText("Warning");
        alert.setContentText("Please be sure to select a customer to modify before pressing the modify button");
        alert.showAndWait();
    }

    /**
     * This method verifies that a user has selected an appointment to modify before attempting to modify a
     * selected appointment.
     */
    public static void modHandler2() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.initModality(Modality.NONE);
        alert.setTitle("Error");
        alert.setHeaderText("Warning");
        alert.setContentText("Please be sure to select an appointment to modify before pressing the modify button");
        alert.showAndWait();
    }

    /**
     * This method ensures that a user has selected a customer from the tableview to delete before attempting to delete
     * a customer from the database.
     */
    public static void delHandler() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.initModality(Modality.NONE);
        alert.setTitle("Error");
        alert.setHeaderText("Warning");
        alert.setContentText("Please be sure to select a customer to modify before pressing the delete button");
        alert.showAndWait();
    }

    /**
     * This method ensures that a user chooses a month via selection before clicking the 'show' button on the reports
     * screen.
     */
    public static void reportsAlert() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.initModality(Modality.NONE);
        alert.setTitle("Error");
        alert.setHeaderText("Warning");
        alert.setContentText("Please be sure to select a month before clicking the 'show' button");
        alert.showAndWait();

    }

    /**
     * This method ensures that a user selects an appointment to delete before pressing the delete button.
     */
    public static void delHandler2() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.initModality(Modality.NONE);
        alert.setTitle("Error");
        alert.setHeaderText("Warning");
        alert.setContentText("Please be sure to select an appointment to delete before pressing the delete button");
        alert.showAndWait();
    }

    /**
     * This method prevents errors which could be caused by a user attempting to delete a customer who has appointments
     * scheduled.
     */
    public static void delHandler3() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.initModality(Modality.NONE);
        alert.setTitle("Error");
        alert.setHeaderText("Warning");
        alert.setContentText("Sorry, you cannot delete a customer who has appointments scheduled. You will need to delete this customer's appointments manually first before you are able to delete this customer.");
        alert.showAndWait();
    }

    /**
     * This method ensures that a user selects a customer before pressing the select button.
     */
    public static void selectHandler() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.initModality(Modality.NONE);
        alert.setTitle("Error");
        alert.setHeaderText("Warning");
        alert.setContentText("Please be sure to select a customer before pressing the select button");
        alert.showAndWait();

    }

    /**
     * This method ensures that a user selects an appointment before pressing the select button.
     */
    public static void selectHandler2() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.initModality(Modality.NONE);
        alert.setTitle("Error");
        alert.setHeaderText("Warning");
        alert.setContentText("Please be sure to select an appointment before pressing the select button");
        alert.showAndWait();

    }

    /**
     * This method ensures that users fill out all textfields and comboboxes before attempting to save the new
     * appointment.
     */
    public static void invalidFieldHandler() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.initModality(Modality.NONE);
        alert.setTitle("Error");
        alert.setHeaderText("Warning");
        alert.setContentText("Please be sure to fill out all information related to creating a new appointment. This includes making selections for all comboboxes.");
        alert.showAndWait();


    }

    /**
     * This method alerts the user if there is no such contact ID upon reading user-entered data in the customer id
     * textfield.
     */
    public static void invalidContactID() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.initModality(Modality.NONE);
        alert.setTitle("Error");
        alert.setHeaderText("Warning");
        alert.setContentText("No such Contact ID. Please enter a valid Contact ID.");
        alert.showAndWait();
    }

    /**
     * This alert notifies the user that there are no such user IDs on record upon reading invalid user input.
     */
    public static void invalidUserID() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.initModality(Modality.NONE);
        alert.setTitle("Error");
        alert.setHeaderText("Warning");
        alert.setContentText("No such User ID. Please enter a valid User ID.");
        alert.showAndWait();
    }

    /**
     * This method alerts the user if there are one or more invalid textfields.
     */
    public static void invalidTextFields() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.initModality(Modality.NONE);
        alert.setTitle("Error");
        alert.setHeaderText("Warning");
        alert.setContentText("One or more textfields invalid. Please try again.");
        alert.showAndWait();
    }

    /**
     * This alert notifies the user if the desired timeslots for a new appointment collide/overlap with another
     * existing appointment scheduled in the database.
     */
    public static void appointmentOverlaps() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Scheduling Error.");
        alert.setHeaderText("The desired timeslots will overlap with another existing appointment for this customer.");
        alert.setContentText("Please select another appointment time.");
        alert.showAndWait();

    }

    /**
     * This method notifies the user if a customer has been successfully deleted from the database.
     */
    public static void deleteSuccessful() {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.initModality(Modality.NONE);
        alert.setTitle("Deletion Successful");
        alert.setHeaderText("Deletion Successful!");
        alert.setContentText("Customer has been successfully deleted from the database ");
        alert.showAndWait();
    }

    /**
     * This method notifies a user if an appointment has been successfully deleted from the database.
     */
    public static void deleteSuccessful2() {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.initModality(Modality.NONE);
        alert.setTitle("Deletion Successful");
        alert.setHeaderText("Deletion Successful!");
        alert.setContentText("Appointment has been successfully deleted from the database ");
        alert.showAndWait();
    }

    /**
     * This method notifies the user upon an attempted login if there are no such usernames in the database.
     */
    public static void incorectUserName() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.initModality(Modality.NONE);
        alert.setTitle("Incorrect username");
        alert.setHeaderText("Incorrect username");
        alert.setContentText("Username not in the database");
        alert.showAndWait();
    }

    /**
     * This method notifies the user if the customer id is invalid.
     */
    public static void invalidCustomerID() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.initModality(Modality.NONE);
        alert.setTitle("Invalid Customer ID");
        alert.setHeaderText("Invalid Customer ID");
        alert.setContentText("Please enter a valid customer ID");
        alert.showAndWait();
    }

    /**
     * This method captures the login time of the user and then notifies the user if there is an appointment within
     * the next 15 minutes.
     */
    public static void upcomingAppt() {
        LocalDateTime startTime = MainMenuController.getUpcomingApptDate();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formatDateTime = startTime.format(formatter);

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.initModality(Modality.NONE);
        alert.setTitle("Appointment soon!");
        alert.setHeaderText("Appointment soon");
        alert.setContentText("There is an appointment scheduled within the next 15 minutes.\n" +
                "Appointment ID: " + MainMenuController.getUpcomingApptID() + "\n" +
                "Start time: " + formatDateTime);


        alert.showAndWait();
    }


    /**
     * This method notifies the user there are no upcoming appointments should that be the case.
     */
    public static void noUpcomingAppts() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.initModality(Modality.NONE);
        alert.setTitle("No upcoming appointments");
        alert.setHeaderText("No upcoming appointments");
        alert.setContentText("There are no appointments scheduled within the next 15 minutes.");
        alert.showAndWait();

    }

}
