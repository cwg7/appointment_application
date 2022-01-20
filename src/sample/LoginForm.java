package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class LoginForm implements Initializable {

    @FXML
    private Label lblZoneID;
    @FXML
    private Label lblUserName;
    @FXML
    private Label lblPassword;
    @FXML
    private TextField tfUserName;
    @FXML
    private TextField tfPassword;
    @FXML
    private Button loginButton;
    private Stage stage;
    private Scene scene;

    private String password = "x";
    private String username;

 /*   @FXML
    public void handleLogin(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("mainMenu.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();


    }*/

    @FXML
    public void handleLogin(javafx.event.ActionEvent event) throws IOException {
        grabLoginData(tfUserName.getText());

        if (authenticate(tfUserName.getText(), tfPassword.getText(), true)) {

            Parent root = FXMLLoader.load(getClass().getResource("mainMenu.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
        else {
           //System.out.println("Sorry, incorrect login credentials");
            Alerts.invalidLoginInfo();
        }
    }

    public boolean authenticate(String username, String password, Boolean isSuccesful) {
        isSuccesful = false;
        if (username.equalsIgnoreCase("Test") && password.equals("password")) {
            isSuccesful = true;

        }
        return isSuccesful;

    }

    public static void grabLoginData(String username) {
        LocalDateTime timestamp = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formatDateTime = timestamp.format(formatter);

        System.out.println(timestamp);
        int hour = timestamp.getHour();
        //System.out.println("Hour: " + hour);
        System.out.println("Translated: " + formatDateTime);

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        lblZoneID.setText("Zone ID: " +ZoneId.systemDefault().toString());
    }
}
