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
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
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

    public static boolean notLoggedIn;


    //public static ObservableList<String> userNames = FXCollections.observableArrayList();

 /*   public ObservableList getUserNames() {
        userNames.add(DBQuery.getUserNameLogin().toString());
        return userNames;
    }
*/

    String enteredPassword;
    String correctPassword;
    @FXML
    public void handleUserLogin(ActionEvent event) throws IOException {
        DBQuery.getUserNames();
        grabLoginData(tfUserName.getText());
        //recordLoginActivity2();


        if (DBQuery.searchUserNames(tfUserName.getText())) {
            //System.out.println("Okay. Now checking the password. . . . ");
            //String enteredPassword = tfPassword.getText();
            enteredPassword = tfPassword.getText();
            //String correctPassword = DBQuery.getUserPassword(tfUserName.getText());
            correctPassword = DBQuery.getUserPassword(tfUserName.getText());
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
                System.out.println("Sorry, incorrect password");
                //loginSuccessful(false);
                //recordLoginActivity();
                //recordLoginActivity2();

                //Alerts.incorectPassword();
                //loginFalse(false);


                incorectPassword();
                clearFields();
                // incorrectLogin();
            }

        } else {
            //System.out.println("Sorry, incorrect username");
            //loginSuccessful(false);

            //Alerts.incorectUserName();
            incorrectUsername();
            clearFields();

        }

        //recordLoginActivity();
        recordLoginActivity();


    }




    public void clearFields(){
        tfUserName.clear();
        tfPassword.clear();
    }












    @FXML
    public static void checkPassword(String password) {

    }

    /*@FXML
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

            //was using this one before  vvv
            // Alerts.invalidLoginInfo();

            incorectPassword();
        }
        //recordLoginActivity();
        //boolean authenticated = true;
    }*/


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

    ZoneId zoneID =ZonedDateTime.now().getZone();
    ZonedDateTime zonedDateTime = LocalDateTime.now().atZone(zoneID);
    public ZonedDateTime userTime = zonedDateTime;

/*
    public static <ZonedDateTime getZonedDateTime(){
        return userTime;
    }
*/

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

    public boolean loginSuccessful(boolean bool) {
        bool = true;
        return true;
    }

    public boolean loginFalse(boolean bool){
        bool = false;
        return false;
    }

    private static String errorTitle;
    //private static String errorHeaderMissing;
    private static String errorHeaderIncorrect;
    //private static String errorContentMissing;
    private static String errorContentIncorrect;

    Alert alert1;

    public static void incorectPassword(){
        Alert alert1 = new Alert(Alert.AlertType.ERROR);
        alert1.initModality(Modality.NONE);
        alert1.setTitle("alert1Title");
        alert1.setTitle(errorTitle);
        alert1.setHeaderText(errorHeaderIncorrect);
        alert1.setContentText(errorContentIncorrect);

        //alert1.setTitle(alert1.getTitle());

        //alert1.setHeaderText(alert1.getHeaderText());

       // alert1.setContentText(alert1.getContentText());
        alert1.showAndWait();
    }

    private static String errorHeaderIncorrect2;
    public static String errorContentIncorrect2;
    Alert alert2;
    public static void incorrectUsername(){
        Alert alert2 = new Alert(Alert.AlertType.ERROR);
        alert2.initModality(Modality.NONE);
        alert2.setTitle(errorTitle);
        //alert2.setTitle(alert2.getTitle());
        alert2.setHeaderText(errorHeaderIncorrect2);
        alert2.setContentText(errorContentIncorrect2);
        alert2.showAndWait();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        lblZoneID.setText("Zone ID: " +ZoneId.systemDefault().toString());

        ResourceBundle rb = ResourceBundle.getBundle("sample/languages", Locale.getDefault());
        if (Locale.getDefault().getLanguage().equals("fr")  || Locale.getDefault().getLanguage().equals("en")){
            lblZoneID.setText(rb.getString("lblZoneID") + ZoneId.systemDefault().toString());
            lblUserName.setText(rb.getString("lblUsername"));
            lblPassword.setText(rb.getString("lblPassword"));
            tfUserName.setPromptText(rb.getString("username"));
            tfPassword.setPromptText(rb.getString("password"));
            loginButton.setText(rb.getString("login"));

            errorTitle = rb.getString("errorTitle");
            errorHeaderIncorrect = rb.getString("errorHeaderIncorrect");
            errorContentIncorrect = rb.getString("errorContentIncorrect");

            errorHeaderIncorrect2 = rb.getString("errorHeaderIncorrect2");
            errorContentIncorrect2 = rb.getString("errorContentIncorrect2");
        }

        notLoggedIn = true;
        Locale currentLocale = Locale.getDefault();
        System.out.println("Current Locale: " + currentLocale);
        System.out.println(currentLocale.getDisplayLanguage());
        System.out.println(currentLocale.getDisplayCountry());


        // lblZoneID.setText("Zone ID: " +ZoneId.systemDefault().toString());
        //DBQuery.getUserNames();
    }
}
