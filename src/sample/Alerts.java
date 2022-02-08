package sample;

import javafx.scene.control.Alert;
import javafx.stage.Modality;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Alerts {

    public static void checkFields() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.initModality(Modality.NONE);
        alert.setTitle("Error");
        alert.setHeaderText("Alert ");
        alert.setContentText("Please fill out all fields to add a new customer");
        alert.showAndWait();
    }

    public static void modHandler(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.initModality(Modality.NONE);
        alert.setTitle("Error");
        alert.setHeaderText("Warning");
        alert.setContentText("Please be sure to select a customer to modify before pressing the modify button");
        alert.showAndWait();
    }


    public static void modHandler2(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.initModality(Modality.NONE);
        alert.setTitle("Error");
        alert.setHeaderText("Warning");
        alert.setContentText("Please be sure to select an appointment to modify before pressing the modify button");
        alert.showAndWait();
    }

    public static void delHandler(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.initModality(Modality.NONE);
        alert.setTitle("Error");
        alert.setHeaderText("Warning");
        alert.setContentText("Please be sure to select a customer to modify before pressing the delete button");
        alert.showAndWait();
    }

    public static void reportsAlert(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.initModality(Modality.NONE);
        alert.setTitle("Error");
        alert.setHeaderText("Warning");
        alert.setContentText("Please be sure to select a month before clicking the 'show' button");
        alert.showAndWait();

    }

    public static void delHandler2(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.initModality(Modality.NONE);
        alert.setTitle("Error");
        alert.setHeaderText("Warning");
        alert.setContentText("Please be sure to select an appointment to delete before pressing the delete button");
        alert.showAndWait();
    }

    public static void delHandler3(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.initModality(Modality.NONE);
        alert.setTitle("Error");
        alert.setHeaderText("Warning");
        alert.setContentText("Sorry, you cannot delete a customer who has appointments scheduled. You will need to delete this customer's appointments manually first before you are able to delete this customer.");
        alert.showAndWait();
    }

    public static void selectHandler(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.initModality(Modality.NONE);
        alert.setTitle("Error");
        alert.setHeaderText("Warning");
        alert.setContentText("Please be sure to select a customer before pressing the select button");
        alert.showAndWait();

    }

    public static void selectHandler2(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.initModality(Modality.NONE);
        alert.setTitle("Error");
        alert.setHeaderText("Warning");
        alert.setContentText("Please be sure to select an appointment before pressing the select button");
        alert.showAndWait();

    }
    public static void invalidEndTime(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.initModality(Modality.NONE);
        alert.setTitle("Error");
        alert.setHeaderText("Warning");
        alert.setContentText("Invalid time selection. End time for the appointment must be after scheduled start time.");
        alert.showAndWait();

    }

    public static void invalidFieldHandler(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.initModality(Modality.NONE);
        alert.setTitle("Error");
        alert.setHeaderText("Warning");
        alert.setContentText("Please be sure to fill out all information related to creating a new appointment. This includes making selections for all comboboxes.");
        alert.showAndWait();


    }

    public static void invalidContactID() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.initModality(Modality.NONE);
        alert.setTitle("Error");
        alert.setHeaderText("Warning");
        alert.setContentText("No such Contact ID. Please enter a valid Contact ID.");
        alert.showAndWait();
    }

    public static void invalidUserID() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.initModality(Modality.NONE);
        alert.setTitle("Error");
        alert.setHeaderText("Warning");
        alert.setContentText("No such User ID. Please enter a valid User ID.");
        alert.showAndWait();
    }

// need french translation here

    public static void invalidLoginInfo(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.initModality(Modality.NONE);
        alert.setTitle("Error");
        alert.setHeaderText("Warning");
        alert.setContentText("Invalid login credentials" + "\n\n" +
                        "Please enter a valid username and password.");
        alert.showAndWait();


    }

    public static void invalidTextFields() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.initModality(Modality.NONE);
        alert.setTitle("Error");
        alert.setHeaderText("Warning");
        alert.setContentText("One or more textfields invalid. Please try again.");
        alert.showAndWait();
    }




    public static void deleteSuccessful(){

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.initModality(Modality.NONE);
        alert.setTitle("Deletion Successful");
        alert.setHeaderText("Deletion Successful!");
        alert.setContentText("Customer has been successfully deleted from the database ");
        alert.showAndWait();
        }

    public static void deleteSuccessful2(){

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.initModality(Modality.NONE);
        alert.setTitle("Deletion Successful");
        alert.setHeaderText("Deletion Successful!");
        alert.setContentText("Appointment has been successfully deleted from the database ");
        alert.showAndWait();
    }

    // need french translation here?

    public static void loginSuccessful(){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.initModality(Modality.NONE);
        alert.setTitle("Login Successful");
        alert.setHeaderText("Login Successful!");
        alert.setContentText("Login Successful");
        alert.showAndWait();

    }

    // need french translation here
    public static void incorectUserName(){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.initModality(Modality.NONE);
        alert.setTitle("Incorrect username");
        alert.setHeaderText("Incorrect username");
        alert.setContentText("Username not in the database");
        alert.showAndWait();
    }

    // need french translation here

    public static void incorectPassword(){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.initModality(Modality.NONE);
        alert.setTitle("Incorrect password");
        alert.setHeaderText("Incorrect password");
        alert.setContentText("Sorry, incorrect password");
        alert.showAndWait();
    }

    public static void invalidCustomerID(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.initModality(Modality.NONE);
        alert.setTitle("Invalid Customer ID");
        alert.setHeaderText("Invalid Customer ID");
        alert.setContentText("Please enter a valid customer ID");
        alert.showAndWait();
    }
    public static void upcomingAppt(){
        LocalDateTime startTime = MainMenuController.getUpcomingApptDate();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formatDateTime = startTime.format(formatter);

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.initModality(Modality.NONE);
        alert.setTitle("Appointment soon!");
        alert.setHeaderText("Appointment soon");
        alert.setContentText("There is an appointment scheduled within the next 15 minutes.\n" +
                "Appointment ID: " + MainMenuController.getUpcomingApptID() + "\n" +
               // "Appointment time: " + MainMenuController.getUpcomingApptDate());
                "Start time: " + formatDateTime);


                //"\n Appointment start time: " + MainMenuController.getUpcomingApptDate();
                //"Appointment start time: "+ MainMenuController.getUpcomingApptDate();
        //MainMenuController.
        alert.showAndWait();
    }

    public static void noUpcomingAppts(){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.initModality(Modality.NONE);
        alert.setTitle("No upcoming appointments");
        alert.setHeaderText("No upcoming appointments");
        alert.setContentText("There are no appointments scheduled within the next 15 minutes.");
        alert.showAndWait();

    }


    }
