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
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.chrono.ChronoLocalDateTime;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalField;
import java.time.temporal.WeekFields;
import java.util.*;
import java.util.stream.Collectors;

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
    private TableColumn <Appointment, Integer> apptIDCol;
    @FXML
    private TableColumn <Appointment, String> titleCol;
    @FXML
    private TableColumn <Appointment, String> descriptionCol;
    @FXML
    private TableColumn <Appointment, String> locationCol;
    @FXML
    private TableColumn <Appointment, String> typeCol;
    @FXML
    private TableColumn <Appointment, LocalDateTime> startCol;
    @FXML
    private TableColumn <Appointment, LocalDateTime> endCol;
    @FXML
    private TableColumn <Appointment, Integer> customerIDCol;
    @FXML
    private TableColumn <Appointment, Integer> userIDCol;
    @FXML
   private TableColumn <Appointment, Integer> contactCol;
    @FXML
    private TableColumn <Appointment, String> contactNameCol;
    //@FXML
    //private TableColumn <Appointment, String> contactCol;

    //hidden comment
    @FXML
    private Button goToPractice;
    @FXML
    private Button morePractice;
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
    private ToggleGroup apptViewToggle;

    @FXML
    private ObservableList<Appointment> allAppointments = FXCollections.observableArrayList();

    @FXML
    private  ObservableList<Appointment> weeklyAppointments = FXCollections.observableArrayList();

    @FXML
    private ObservableList<Appointment> monthlyAppointments = FXCollections.observableArrayList();

    public void viewAllAppointments() throws SQLException {
        showAppointments();
    }

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


        /*List<Appointment> apptsThisMonth = MainMenuController.getAppointments()

                .stream()
                .filter(a -> a.getStart_time().getMonth().equals(monthInt))
                .collect(Collectors.toList());

        apptTable.setItems(FXCollections.observableList(apptsThisMonth));*/




      /*  List<Appointment> apptsThisMonth = MainMenuController.getAppointments()

                .stream()
                .filter(a -> a.getStart_time().isAfter(LocalDateTime.now()) && a.getStart_time().isBefore(LocalDateTime.now().plusMonths(1)))
                .collect(Collectors.toList());



        apptTable.setItems(FXCollections.observableList(apptsThisMonth));*/




    }



    public void viewApptByWeek(ActionEvent event) throws IOException {
        TemporalField currentWeekBasedYear = WeekFields.of(Locale.getDefault()).weekOfWeekBasedYear();
        LocalDate currentDate = LocalDate.now();
        int currentWeekNumber = currentDate.get(currentWeekBasedYear);
        int currentYear = LocalDate.now().getYear();
        Calendar now = Calendar.getInstance();

        /*List<Customer> customersWithMoreThan100Points = customers
                .stream()
                .filter(c -> c.getPoints() > 100)
                .collect(Collectors.toList());*/

        // list of all appts goes below

        List<Appointment> apptsThisWeek = MainMenuController.getAppointments()

                .stream()
                .filter(a -> a.getStart_time().isAfter(LocalDateTime.now()) && a.getStart_time().isBefore(LocalDateTime.now().plusDays(7)))
                .collect(Collectors.toList());

        apptTable.setItems(FXCollections.observableList(apptsThisWeek));


        //int currentWeekNumber2 = now.get(Calendar.WEEK_OF_YEAR);

        //System.out.println(currentDate);
        //System.out.println(currentWeekNumber);
        //System.out.println(currentYear);

        //System.out.println(currentWeekNumber2);
    }

    /*private ToggleGroup calendarRadio;
    private boolean isWeekly;
    private boolean isMonthly;

    */

/*    @FXML private void handleDatePicked (ActionEvent event) throws IOException {
        if (isWeekly) {
            viewByWeek(); //View by Week is previously selected
        }
        else if (isMonthly) {
            viewByMonth(); //View by Month is previously selected
        }
        else {
            viewAll(); //View All is previously selected
        }
    }*/


    public void goPractice(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("practice.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    public void morePractice(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("morePractice.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }



    public static ObservableList<Appointment> getAppointments() {
        ObservableList<Appointment> appointmentsList = FXCollections.observableArrayList();
        Connection conn = DBConnection.getConnection();
        String query = "SELECT * FROM appointments";
        Statement st;
        ResultSet rs;

        // rs.getInt("Customer_ID"),

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

    public void showAppointmentsByMonth(){
        ObservableList<Appointment> list = getAppointments();
    }

    public void showAppointmentsByWeek(){
        ObservableList<Appointment> list = getAppointments();
    }

    public void modAppointmentButtonClick(ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("modAppointment.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
       // stage.setResizable(true);
        stage.show();
        //stage.setMaximized(true);
    }



    public void goToReports(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("reports.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        // stage.setResizable(true);
        stage.show();

    }




    @FXML
    public void addCustomerButtonClick(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("AddCustomer.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    public void addAppointmentButtonClick(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("addAppointment.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }
    @FXML
    public void logout(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("loginForm.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        showAppointments();


    }
}
