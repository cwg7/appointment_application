package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
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

    //public static ObservableList<String> userNames = FXCollections.observableArrayList();

 /*   public ObservableList getUserNames() {
        userNames.add(DBQuery.getUserNameLogin().toString());
        return userNames;
    }
*/

    @FXML
    public void handleUserLogin(ActionEvent event) throws IOException {
        DBQuery.getUserNames();
        grabLoginData(tfUserName.getText());
        //recordLoginActivity2();


        if (DBQuery.searchUserNames(tfUserName.getText())) {
            //System.out.println("Okay. Now checking the password. . . . ");
            String enteredPassword = tfPassword.getText();
            String correctPassword = DBQuery.getUserPassword(tfUserName.getText());
            //System.out.println("correct password: " + correctPassword);

            if (enteredPassword.equals(correctPassword)) {
                //System.out.println("Correct Password");
                //loginSuccessful(true);
                //recordLoginActivity();
                //recordLoginActivity2();

                Parent root = FXMLLoader.load(getClass().getResource("mainMenu.fxml"));
                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            } else {
                //System.out.println("Sorry, incorrect password");
                //loginSuccessful(false);
                //recordLoginActivity();
                //recordLoginActivity2();
                Alerts.incorectPassword();
            }

        } else {
            //System.out.println("Sorry, incorrect username");
            //loginSuccessful(false);
            Alerts.incorectUserName();
            //recordLoginActivity2();

            //recordLoginActivity();
        }

        //recordLoginActivity();
        recordLoginActivity();


    }









  /*  @FXML
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


/*


    @FXML
    public static void searchUserNames(String username) {
        if (userNames.contains(username)) {
            System.out.println("USERNAME EXISTS IN THE DATABASE");


        }
        else {
            System.out.println("USERNAME NOT IN DB");
        }
    }
*/

    @FXML
    public static void checkPassword(String password) {

    }

    @FXML
    public void handleLogin(javafx.event.ActionEvent event) throws IOException {
        String username = tfUserName.getText();
        grabLoginData(tfUserName.getText());
        DBQuery.getUserNames();
        DBQuery.searchUserNames(username);

        //recordLoginActivity();


        if (authenticate(tfUserName.getText(), tfPassword.getText(), true)) {

            Parent root = FXMLLoader.load(getClass().getResource("mainMenu.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } else {
            //System.out.println("Sorry, incorrect login credentials");
            Alerts.invalidLoginInfo();
        }
        //recordLoginActivity();
        //boolean authenticated = true;
    }

    // get all users && matching passwords


    public boolean loginSuccessful(boolean isSuccessful) {
        isSuccessful = true;
        return true;
    }

    public boolean loginFailed() {
        boolean isSuccessful = false;
        return false;
    }

    public boolean authenticate(String username, String password, Boolean isSuccesful) {
        isSuccesful = false;
        if (username.equalsIgnoreCase("test") && password.equals("test")) {
            isSuccesful = true;

        }
        if (username.equalsIgnoreCase("admin") && password.equals("admin")) {
            isSuccesful = true;
        }
        return isSuccesful;

    }

    /*public boolean authenticate2(String username, String password, Boolean isSuccessful) {
        isSuccessful = false;
        username = tfUserName.getText();
        password = tfPassword.getText();
        if (DBQuery.getUserNames().contains(username)) {
            if (password.equals(DBQuery.getUserPassword(password))) {
                isSuccessful = true;
            }

        }}*/
/*    public boolean authenticated() {
        boolean isSuccessful = true;
        return true;
    }*/


    public static Object grabLoginData(String username) {
        LocalDateTime timestamp = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formatDateTime = timestamp.format(formatter);

        int hour = timestamp.getHour();

        return username;
    }

    // NOT THIS
    /*
    public List loginActivity = FXCollections.observableArrayList();
    //loginActivity.add
    */


 /*   public ObservableList getLoginActivity() {
        ObservableList <String> loginData = FXCollections.observableArrayList();
        String username = tfUserName.getText();
        String password = tfPassword.getText();







        return loginData;
    }*/

    public String grabTimestamp(LocalDateTime timestamp) {
        ObservableList<LocalDateTime> loginTime = FXCollections.observableArrayList();
        LocalDateTime time = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formatDateTime = timestamp.format(formatter);


        return formatDateTime;

    }

   /* public void recordLoginActivity() {
        try(FileWriter fw = new FileWriter("login_activity.txt", true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter out = new PrintWriter(bw))

        {
            String text = (String) grabLoginData(tfUserName.getText());
            out.print(grabTimestamp(LocalDateTime.now()));
            out.print(" Username:" + grabLoginData(" " + tfUserName.getText()));

            if (authenticate(tfUserName.getText(), tfPassword.getText(), true)) {

                out.println(" " + "Login Attempt: Successful");
        }
            else {
                out.println(" " + "Login Attempt: Unsuccessful");
            }


        } catch (IOException e) {

        }
    }*/

    public void recordLoginActivity() {
        String username = tfUserName.getText();
        try(FileWriter fw = new FileWriter("login_activity.txt", true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter out = new PrintWriter(bw))


        {
            String text = (String) grabLoginData(tfUserName.getText());
            out.print(grabTimestamp(LocalDateTime.now()));
            out.print(" Username:" + grabLoginData(" " + tfUserName.getText()));


            if (DBQuery.getUserNames().contains(username)){

                if (DBQuery.getUserPassword(username).equals(tfPassword.getText())) {
                    out.println(" " + "Login Attempt: Successful");
                }
                else{
                    out.println(" " + "Login Attempt: Unsuccessful");
                }
            }
            else {
                out.println(" " + "Login Attempt: Unsuccessful");
            }


        } catch (IOException e) {

        }
    }





    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        lblZoneID.setText("Zone ID: " +ZoneId.systemDefault().toString());
        //DBQuery.getUserNames();
    }
}
