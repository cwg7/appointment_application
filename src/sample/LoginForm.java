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

/**
 * This is the LoginForm class
 */

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

    private String username;

    public static boolean notLoggedIn;

    String enteredPassword;
    String correctPassword;

    /**
     * This method looks up usernames from the database and checks them against a user-input username via textfield.
     * It then checks the password of the given username (if valid) to see if it matches as per db data.
     *
     * @param event
     * @throws IOException
     */
    @FXML
    public void handleUserLogin(ActionEvent event) throws IOException {
        DBQuery.getUserNames();
        grabLoginData(tfUserName.getText());


        if (DBQuery.searchUserNames(tfUserName.getText())) {
            enteredPassword = tfPassword.getText();
            correctPassword = DBQuery.getUserPassword(tfUserName.getText());

            if (enteredPassword.equals(correctPassword)) {

                Parent root = FXMLLoader.load(getClass().getResource("mainMenu.fxml"));
                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();

            } else {
                System.out.println("Sorry, incorrect password");
                incorectPassword();
                clearFields();
            }

        } else {
            incorrectUsername();
            clearFields();

        }

        recordLoginActivity();


    }

    /**
     * This method clears textfields
     */
    public void clearFields() {
        tfUserName.clear();
        tfPassword.clear();
    }


    /**
     * This method captures login data, attempted username via username textfield
     *
     * @param username
     * @return returns username
     */
    public static Object grabLoginData(String username) {
        LocalDateTime timestamp = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formatDateTime = timestamp.format(formatter);

        int hour = timestamp.getHour();

        return username;
    }


    /**
     * This method grabs a timestamp of an attempted login time
     *
     * @param timestamp
     * @return returns formatted date time
     */
    public String grabTimestamp(LocalDateTime timestamp) {
        ObservableList<LocalDateTime> loginTime = FXCollections.observableArrayList();
        LocalDateTime time = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formatDateTime = timestamp.format(formatter);


        return formatDateTime;

    }

    ZoneId zoneID = ZonedDateTime.now().getZone();
    ZonedDateTime zonedDateTime = LocalDateTime.now().atZone(zoneID);
    public ZonedDateTime userTime = zonedDateTime;


    /**
     * This method records login activity (attempts) and logs them to login_activity.txt
     */
    public void recordLoginActivity() {
        String username = tfUserName.getText();
        try (FileWriter fw = new FileWriter("login_activity.txt", true);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {
            String text = (String) grabLoginData(tfUserName.getText());
            out.print(grabTimestamp(LocalDateTime.now()));
            out.print(" Username:" + grabLoginData(" " + tfUserName.getText()));


            if (DBQuery.getUserNames().contains(username)) {

                if (DBQuery.getUserPassword(username).equals(tfPassword.getText())) {
                    out.println(" " + "Login Attempt: Successful");
                } else {
                    out.println(" " + "Login Attempt: Unsuccessful");
                }
            } else {
                out.println(" " + "Login Attempt: Unsuccessful");
            }


        } catch (IOException e) {

        }
    }

    private static String errorTitle;
    private static String errorHeaderIncorrect;
    private static String errorContentIncorrect;

    Alert alert1;

    /**
     * This method triggers an alert if password is incorrect
     */
    public static void incorectPassword() {
        Alert alert1 = new Alert(Alert.AlertType.ERROR);
        alert1.initModality(Modality.NONE);
        alert1.setTitle("alert1Title");
        alert1.setTitle(errorTitle);
        alert1.setHeaderText(errorHeaderIncorrect);
        alert1.setContentText(errorContentIncorrect);
        alert1.showAndWait();
    }

    private static String errorHeaderIncorrect2;
    public static String errorContentIncorrect2;
    Alert alert2;

    /**
     * This method triggers an alert if username is incorrect
     */
    public static void incorrectUsername() {
        Alert alert2 = new Alert(Alert.AlertType.ERROR);
        alert2.initModality(Modality.NONE);
        alert2.setTitle(errorTitle);
        //alert2.setTitle(alert2.getTitle());
        alert2.setHeaderText(errorHeaderIncorrect2);
        alert2.setContentText(errorContentIncorrect2);
        alert2.showAndWait();
    }

    /**
     * Initializes the loginForm screen. Prompts user for username and password and translates languages based on
     * language settings of user's machine
     *
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        lblZoneID.setText("Zone ID: " + ZoneId.systemDefault().toString());

        ResourceBundle rb = ResourceBundle.getBundle("sample/languages", Locale.getDefault());
        if (Locale.getDefault().getLanguage().equals("fr") || Locale.getDefault().getLanguage().equals("en")) {
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


    }
}
