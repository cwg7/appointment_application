package sample;

import javafx.scene.control.Alert;
import javafx.stage.Modality;

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

    public static void delHandler2(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.initModality(Modality.NONE);
        alert.setTitle("Error");
        alert.setHeaderText("Warning");
        alert.setContentText("Please be sure to select an appointment to delete before pressing the delete button");
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

    public static void invalidFieldHandler(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.initModality(Modality.NONE);
        alert.setTitle("Error");
        alert.setHeaderText("Warning");
        alert.setContentText("Please be sure to fill out all information related to creating a new appointment. This includes making selections for all comboboxes.");
        alert.showAndWait();


    }

    public static void invalidLoginInfo(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.initModality(Modality.NONE);
        alert.setTitle("Error");
        alert.setHeaderText("Warning");
        alert.setContentText("Invalid login credentials" + "\n\n" +
                        "Please enter a valid username and password.");
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
    }
