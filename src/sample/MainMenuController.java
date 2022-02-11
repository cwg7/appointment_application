package sample;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import sample.Appointment;

import javax.imageio.IIOException;
import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.time.*;
import java.time.chrono.ChronoLocalDateTime;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalField;
import java.time.temporal.WeekFields;
import java.util.*;
import java.util.stream.Collectors;


/**
 * This is the MainMenuController class
 */
public class MainMenuController implements Initializable {

    private Stage stage;
    private Scene scene;

    @FXML
    private Button addCustomerBtn;
    @FXML
    private Label lblMenu;
    @FXML
    private Label lblMain;
    @FXML
    private Button addApptButton;
    @FXML
    private Button modApptButton;
    @FXML
    private TableView apptTable;
    @FXML
    private TableColumn<Appointment, Integer> apptIDCol;
    @FXML
    private TableColumn<Appointment, String> titleCol;
    @FXML
    private TableColumn<Appointment, String> descriptionCol;
    @FXML
    private TableColumn<Appointment, String> locationCol;
    @FXML
    private TableColumn<Appointment, String> typeCol;
    @FXML
    private TableColumn<Appointment, LocalDateTime> startCol;
    @FXML
    private TableColumn<Appointment, LocalDateTime> endCol;
    @FXML
    private TableColumn<Appointment, Integer> customerIDCol;
    @FXML
    private TableColumn<Appointment, Integer> userIDCol;
    @FXML
    private TableColumn<Appointment, Integer> contactCol;
    @FXML
    private TableColumn<Appointment, String> contactNameCol;

    @FXML
    private Button logoutButton;
    @FXML
    private Button reportsButton;




    @FXML
    private RadioButton monthRadioButton;
    @FXML
    private RadioButton weekRadioButton;
    @FXML
    private RadioButton viewAllRadioButton;
    @FXML
    private RadioButton less15RadioBtn;
    @FXML
    private ToggleGroup apptViewToggle;


    @FXML
    private ObservableList<Appointment> allAppointments = FXCollections.observableArrayList();

    @FXML
    private ObservableList<Appointment> weeklyAppointments = FXCollections.observableArrayList();

    @FXML
    private ObservableList<Appointment> monthlyAppointments = FXCollections.observableArrayList();

    @FXML
    private ObservableList<Appointment> apptsToday = FXCollections.observableArrayList();

    @FXML
    private ObservableList<Appointment> apptsIn15Minutes = FXCollections.observableArrayList();

    public static int upcomingApptID;
    public static LocalDateTime upcomingApptDate;


    /** This method filters appointment by current date.
     * This Lambda Expression filters out and lists appointments for current day
     * @return Returns a list of appointments for selected day.*/
    public void viewApptsToday() throws IOException {

        LocalDateTime rightNow = LocalDateTime.now();
        System.out.println("Time Right now: " + rightNow);
        int currentDay = rightNow.getDayOfMonth();

        List<Appointment> apptsToday = MainMenuController.getAppointments()

                .stream()
                .filter(a -> Objects.equals(a.getStart_time().getDayOfMonth(), currentDay))
                .collect(Collectors.toList());

        apptTable.setItems(FXCollections.observableList(apptsToday));


    }


    /**
     * This method calls the showAppointments() method which displays a list of all appointments on record
     * @throws SQLException
     */
    public void viewAllAppointments() throws SQLException {
        showAppointments();
    }


    /** This method filters appointment by current month
     * This Lambda Expression filters out and lists appointments for current month
     * @return Returns a list of appointments for current monnth.*/

    public void viewApptByMonth(ActionEvent event) throws IOException {
        //apptTable.setItems(null);
        //Month currentMonth = LocalDateTime.now().getMonth();
        //int monthInt = currentMonth.getValue();
        int currentMonth = LocalDateTime.now().getMonthValue();
        int nextMonth = currentMonth + 1;


        //Calendar now = Calendar.getInstance();


        //System.out.println(currentMonth);
        //System.out.println(monthInt);

        List<Appointment> apptsThisMonth = MainMenuController.getAppointments()

                .stream()
                .filter(a -> Objects.equals(a.getStart_time().getMonthValue(), currentMonth))
                .collect(Collectors.toList());

        apptTable.setItems(FXCollections.observableList(apptsThisMonth));



    }


    /** This method filters appointment that start within the next 15 minutes of login time.
     * This Lambda Expression filters out and only lists appointments which are to occur within the next 15 minutes
     * @return Returns a list of appointments within the next 15 minutes
     * */
    public void viewApptsIn15() {
        LocalDate currentDate = LocalDate.now();
        Calendar now = Calendar.getInstance();

        ZoneId zoneID = ZonedDateTime.now().getZone();

        ZonedDateTime loginTime = LocalDateTime.now().atZone(zoneID);


        //ZoneId zoneID = ZonedDateTime.now().getZone();
        ZonedDateTime zonedDateTime = LocalDateTime.now().atZone(zoneID);
        ZonedDateTime userTime = zonedDateTime;
        LocalTime currTime = userTime.toLocalTime();

        //now = userTime;

        List<Appointment> appointments = MainMenuController.getAppointments()

                .stream()

                //originally was this next line below vvvv
                //.filter(a -> a.getStart_time().isAfter(LocalDateTime.now()) && a.getStart_time().isBefore(LocalDateTime.now().plusMinutes(15)))
                /// this one here ^^^^^

                ///going to try this one though vvvv
                .filter(a -> a.getStart_time().isAfter(loginTime.toLocalDateTime()) && a.getStart_time().isBefore(loginTime.toLocalDateTime().plusMinutes(15)))
                // trying this ^^^^^^^


                //.filter(a -> a.getStart_time().isAfter(currTime)) && a.getStart_time().isBefore(ChronoLocalDateTime.from(currTime.plusMinutes(15))))
                //.filter(a -> a.getStart_time().isAfter(ChronoLocalDateTime.from(userTime))) && a.getStart_time().isBefore(userTime.plusMinutes(15))
                .collect(Collectors.toList());

        apptTable.setItems(FXCollections.observableList(appointments));
        //System.out.println(apptTable.getSelectionModel().getSelectedItems());
        //int apptID = apptIDCol.getCellData(0);


        //trying to figure out how to pull this info and provide it via an alert
        //
        //upcomingApptID = apptIDCol.getCellData(0);
        if (appointments.size() != 0) {
            upcomingApptID = appointments.get(0).getAppointment_id();
            upcomingApptDate = appointments.get(0).getStart_time();
        }
        else{
            upcomingApptID = -1;
            upcomingApptDate = null;
        }


       // upcomingApptDate = appointments.get(0).getStart_time();



        //System.out.println("Appointment ID: " + upcomingApptID);
        //LocalDateTime startTime = startCol.getCellData(0);

        // same with this one
        //
        // upcomingApptDate = startCol.getCellData(0);


    }


    // methods below are attempt at retrieving info for alerts relating to upcoming appt

    /**
     * This method returns upcoming appointment ID
     * @return returns the id of the appointment which starts within the next 15 minutes
     */
    public static int getUpcomingApptID() {
        return upcomingApptID;
    }

    /**
     * This method returns the date of the upcoming appointment
     * @return returns date of upcoming appointment
     */
    public static LocalDateTime getUpcomingApptDate() {
        return upcomingApptDate;
    }


    /** This method filters appointment by current week.
     * This Lambda Expression filters out and lists appointments for current week
     * @return Returns a list of appointments for current week
     * */

    public void viewApptByWeek(ActionEvent event) throws IOException {
        TemporalField currentWeekBasedYear = WeekFields.of(Locale.getDefault()).weekOfWeekBasedYear();
        LocalDate currentDate = LocalDate.now();
        int currentWeekNumber = currentDate.get(currentWeekBasedYear);
        int currentYear = LocalDate.now().getYear();
        Calendar now = Calendar.getInstance();

        List<Appointment> apptsThisWeek = MainMenuController.getAppointments()

                .stream()
                .filter(a -> a.getStart_time().isAfter(LocalDateTime.now()) && a.getStart_time().isBefore(LocalDateTime.now().plusDays(7)))
                .collect(Collectors.toList());

        apptTable.setItems(FXCollections.observableList(apptsThisWeek));

    }


    /**
     * This method returns list of all appointments
     * @return returns all appointments
     */
    public static ObservableList<Appointment> getAppointments() {
        ObservableList<Appointment> appointmentsList = FXCollections.observableArrayList();
        Connection conn = DBConnection.getConnection();
        String query = "SELECT * FROM appointments";
        Statement st;
        ResultSet rs;

        try {
            st = conn.createStatement();
            rs = st.executeQuery(query);
            Appointment appointment;
            while (rs.next()) {
                appointment = new Appointment(rs.getInt("Appointment_ID"), rs.getString("Title"),
                        rs.getString("Description"), rs.getString("Location"), rs.getString("Type"), rs.getTimestamp("Start").toLocalDateTime(), rs.getTimestamp("End").toLocalDateTime(), rs.getInt("Customer_ID"), rs.getInt("User_ID"), rs.getInt("Contact_ID"));
                appointmentsList.add(appointment);
            }

        } catch (Exception ex) {
            ex.printStackTrace();

        }
        return appointmentsList;
    }

    /**
     * This method displays all appointments on the tableview
     */
    public void showAppointments() {
        ObservableList<Appointment> list = getAppointments();
        apptIDCol.setCellValueFactory(new PropertyValueFactory<Appointment, Integer>("appointment_id"));
        titleCol.setCellValueFactory(new PropertyValueFactory<Appointment, String>("title"));
        descriptionCol.setCellValueFactory(new PropertyValueFactory<Appointment, String>("description"));
        locationCol.setCellValueFactory(new PropertyValueFactory<Appointment, String>("location"));
        typeCol.setCellValueFactory(new PropertyValueFactory<Appointment, String>("type"));
        startCol.setCellValueFactory(new PropertyValueFactory<Appointment, LocalDateTime>("start_time"));
        endCol.setCellValueFactory(new PropertyValueFactory<Appointment, LocalDateTime>("end_time"));
        customerIDCol.setCellValueFactory(new PropertyValueFactory<Appointment, Integer>("customer_id"));
        userIDCol.setCellValueFactory(new PropertyValueFactory<Appointment, Integer>("user_id"));
        //contactCol.setCellValueFactory(new PropertyValueFactory<Appointment, String>("contact_name"));
        contactCol.setCellValueFactory(new PropertyValueFactory<Appointment, Integer>("contact_id"));
        //contactNameCol.setCellValueFactory(new PropertyValueFactory<Appointment, String>("contact_name"));
        apptTable.setItems(list);


    }

    /**
     * This method displays all appointments within the next 15 minutes on the tableview
     */
    public void showApptsIn15() {
        showAppointments();
        viewApptsIn15();
    }


    public void showAppointmentsByMonth() {
        ObservableList<Appointment> list = getAppointments();
    }

    /**
     * This method displays all appointments within the current week
     */
    public void showAppointmentsByWeek() {
        ObservableList<Appointment> list = getAppointments();
    }

    /**
     * This method directs the user to the modify appointment menu
     * @param event mouse click on mod appt button
     * @throws IOException
     */
    public void modAppointmentButtonClick(ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("modAppointment.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        // stage.setResizable(true);
        stage.show();
        //stage.setMaximized(true);
    }


    /**
     * This method directs the user to the reports menu
     * @param event moust click on reports button
     * @throws IOException
     */
    public void goToReports(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("reports.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        // stage.setResizable(true);
        stage.show();

    }


    /**
     * This method directs the user to the Add Customer menu
     * @param event mouse click on add customer button
     * @throws IOException
     */
    @FXML
    public void addCustomerButtonClick(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("AddCustomer.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    /**
     * This method directs the user to the add appointment menu
     * @param event mouse click on add appointment button
     * @throws IOException
     */
    @FXML
    public void addAppointmentButtonClick(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("addAppointment.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    /**
     * This button logs the user out and directs them to the login menu
     * @param event mouse click on logout button
     * @throws IOException
     */
    @FXML
    public void logout(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("loginForm.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Initializes the MainMenuController, displays all appointments
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        //showAppointments();
        viewApptsIn15();

        while (LoginForm.notLoggedIn)
        if (apptTable.getItems().isEmpty()) {

            showAppointments();
            Alerts.noUpcomingAppts();
            LoginForm.notLoggedIn = false;

        }
        else{
            // need to use this below
            //viewApptsIn15();
            //
            //going to try this
            showApptsIn15();
            viewApptsIn15();
            Alerts.upcomingAppt();
            LoginForm.notLoggedIn = false;
        }
        showAppointments();

    }
    }

