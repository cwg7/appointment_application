package sample;

import javafx.scene.control.Alert;
import javafx.stage.Modality;

public class Alerts {

    public static void checkFields() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.initModality(Modality.NONE);
        alert.setTitle("Error");
        alert.setHeaderText("Alert ");
        alert.setContentText("Please fill out all textfields to add a new customer");
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

    public static void delHandler(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.initModality(Modality.NONE);
        alert.setTitle("Error");
        alert.setHeaderText("Warning");
        alert.setContentText("Please be sure to select a customer to modify before pressing the delete button");
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
    }
