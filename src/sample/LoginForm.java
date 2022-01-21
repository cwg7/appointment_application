package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
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

    //private String password = "x";
    private String username;

 /*   @FXML
    public void handleLogin(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("mainMenu.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();


    }*/

    // Just using this method to make logging in fast during build
    /*@FXML
    public void justGo(ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("mainMenu.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }*/

    @FXML
    public void handleLogin(javafx.event.ActionEvent event) throws IOException {
        grabLoginData(tfUserName.getText());
        //recordLoginActivity();



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
        recordLoginActivity();
        //boolean authenticated = true;
    }

    public boolean authenticate(String username, String password, Boolean isSuccesful) {
        isSuccesful = false;
        if (username.equalsIgnoreCase("Test") && password.equals("test")) {
            isSuccesful = true;

        }
        return isSuccesful;

    }

    public static Object grabLoginData(String username) {
        LocalDateTime timestamp = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formatDateTime = timestamp.format(formatter);




        System.out.println("Username: " + username);
        System.out.println("Timestamp:" +timestamp);
        int hour = timestamp.getHour();
        System.out.println("Translated: " + formatDateTime);







        //System.out.println("Login attempt: " + "(authenticate(usern));

        return username ;
    }

    // NOT THIS
    /*
    public List loginActivity = FXCollections.observableArrayList();
    //loginActivity.add
    */


    public ObservableList getLoginActivity() {
        ObservableList <String> loginData = FXCollections.observableArrayList();
        String username = tfUserName.getText();
        String password = tfPassword.getText();







        return loginData;
    }

    public String grabTimestamp(LocalDateTime timestamp) {
        ObservableList <LocalDateTime> loginTime = FXCollections.observableArrayList();
        LocalDateTime time = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formatDateTime = timestamp.format(formatter);


        return formatDateTime;

    }

    public void recordLoginActivity() {
        try(FileWriter fw = new FileWriter("login_activity.txt", true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter out = new PrintWriter(bw))

        {
            String text = (String) grabLoginData(tfUserName.getText());

            //out.println(getLoginActivity());
            out.print(grabTimestamp(LocalDateTime.now()));
            out.print(" Username:" + grabLoginData(" " + tfUserName.getText()));

            if (authenticate(tfUserName.getText(), tfPassword.getText(), true)) {

                out.println(" " + "Login Attempt: Successful");
        }
            else {
                out.println(" " + "Login Attempt: Unsuccessful");
            }
            //out.print(authenticate(tfUserName.getText(), tfPassword.getText(), true))

            out.println("\n");
            //out.println(grabTimestamp(LocalDateTime.now()));

            //out.println(grabLoginData(tfUserName.getText()));

            //out.println(text);

            //more code
            //out.println("more text");
            //more code
        } catch (IOException e) {
            //exception handling left as an exercise for the reader
        }
    }





    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        lblZoneID.setText("Zone ID: " +ZoneId.systemDefault().toString());
    }
}
