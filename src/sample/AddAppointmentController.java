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
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.time.*;
import java.util.ResourceBundle;


/**
 * This class allows the user to schedule new appointments. Users can select an existing customer from the database
 * to make appointments for. Data for new appointments is collected via textfields and comboboxes.
 */
public class AddAppointmentController implements Initializable {

    @FXML
    private TableView customerTable;
    @FXML
    private TableColumn customerIDCol;
    @FXML
    private TableColumn customerNameCol;
    @FXML
    private Button backToMainButton;
    @FXML
    private Button selectCustomerButton;
    @FXML
    private Label lblCustomerID;
    @FXML
    private Label lblCustomerName;
    @FXML
    private TextField tfCustomerID;
    @FXML
    private TextField tfCustomerName;
    @FXML
    private Label lblTitle;
    @FXML
    private Label lblDescription;
    @FXML
    private Label lblLocation;
    @FXML
    private Label lblType;
    @FXML
    private Label lblContactID;
    @FXML
    private TextField tfTitle;
    @FXML
    private TextField tfDescription;
    @FXML
    private TextField tfLocation;
    @FXML
    private TextField tfType;
    @FXML
    private TextField tfContactID;
    @FXML
    private Label lblStart;
    @FXML
    private Label lblEnd;
    @FXML
    private TextField tfEnd;
    @FXML
    private Label lblUserID;
    @FXML
    private TextField tfUserID;

    @FXML
    private Button addAppointmentButton;
    @FXML
    ComboBox<Integer> contact_box;
    @FXML
    private Label lblContact;
    @FXML
    private TableView contactsTable;
    @FXML
    private TableColumn contactIDCol;
    @FXML
    private TableColumn contactNameCol;
    @FXML
    ComboBox<Integer> userID_box;
    @FXML
    ComboBox<String> contactName_box;
    @FXML
    private Label lblContactName;

    @FXML
    private TextArea taHours;

    @FXML
    private Button practiceTimeButton;

    @FXML
    private ComboBox<String> cbContacts;

    @FXML
    private Label lblDate;
    @FXML
    private DatePicker datePicker;

    private LocalDate selectedDate;
    private LocalDateTime startDateAndTime;
    private LocalDateTime endDateAndTime;

    @FXML
    private ComboBox<LocalTime> cbStartTime;
    @FXML
    private ComboBox<LocalTime> cbEndTime;

    @FXML
    private TextField tfStartDateAndTime;


    @FXML
    private TextArea taTestArea;


    private Stage stage;
    private Scene scene;


    /**
     * @return Returns array list of customer data (id, name, address, postal code, phone, division id)
     */
    public static ObservableList<Customer> getCustomerList() {
        ObservableList<Customer> customerList = FXCollections.observableArrayList();
        Connection conn = DBConnection.getConnection();
        String query = "SELECT * FROM customers";
        Statement st;
        ResultSet rs;

        try {
            st = conn.createStatement();
            rs = st.executeQuery(query);
            Customer customer;
            while (rs.next()) {
                customer = new Customer(rs.getInt("Customer_ID"), rs.getString("Customer_Name"),
                        rs.getString("Address"), rs.getString("Postal_Code"), rs.getString("Phone"), rs.getInt("Division_ID"));
                customerList.add(customer);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return customerList;
    }

    /**
     * This method displays customer data in the tableview
     */
    public void showCustomers() {
        ObservableList<Customer> list = getCustomerList();
        customerIDCol.setCellValueFactory(new PropertyValueFactory<Customer, Integer>("id"));
        customerNameCol.setCellValueFactory(new PropertyValueFactory<Customer, String>("name"));
        customerTable.setItems(list);
    }

    /**
     * @return Returns an array of contact data (contact id, contact name, contact email)
     */
    public static ObservableList<Contacts> getContactsList() {
        ObservableList<Contacts> newContactsList = FXCollections.observableArrayList();
        Connection conn = DBConnection.getConnection();
        String query = "SELECT * FROM contacts";
        Statement st;
        ResultSet rs;

        try {
            st = conn.createStatement();
            rs = st.executeQuery(query);
            Contacts contact;
            while (rs.next()) {
                contact = new Contacts(rs.getInt("Contact_ID"), rs.getString("Contact_Name"),
                        rs.getString("Email"));
                newContactsList.add(contact);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return newContactsList;
    }

    /**
     * This method displays contact info on a small tableview on the top right of the screen
     */
    public void showContacts() {
        ObservableList<Contacts> list = getContactsList();
        contactIDCol.setCellValueFactory(new PropertyValueFactory<Customer, Integer>("contact_id"));
        contactNameCol.setCellValueFactory(new PropertyValueFactory<Customer, String>("contact_name"));
        contactsTable.setItems(list);
    }


    /**
     * This method allows the user to leave the current screen and go back to the main menu.
     *
     * @param event (mouse click on Go Back button)
     * @throws IOException
     */
    @FXML
    public void backToMainButtonClick(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("mainMenu.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * This method allows the user to select an existing customer to make an appointment for. Upon clicking, customer
     * name and id are retrieved, so as to allow the user to make appointments for the selected customer.
     *
     * @param event (mouse click on select customer button)
     * @throws IOException
     */
    @FXML
    public void selectCustomerButtonClick(ActionEvent event) throws IOException {
        if (customerTable.getSelectionModel().getSelectedItem() == null) {
            Alerts.selectHandler();
        } else {
            addAppointmentButton.setDisable(false);
            Customer selectedCustomer = (Customer) customerTable.getSelectionModel().getSelectedItem();
            int selectedCustomerID = selectedCustomer.getId();
            String selectedCustomerName = selectedCustomer.getName();
            tfCustomerID.setText(String.valueOf(selectedCustomerID));
            tfCustomerName.setText(selectedCustomerName);
            customerTable.setDisable(true);
        }

    }

    /**
     * This method checks to make sure the user has filled out all textfields, and also checks to see if comboboxes
     * were selected correctly as well.
     */
    public boolean validateFields2(boolean isValid) {
        if (tfTitle.getText() == null || tfDescription.getText() == null || tfLocation.getText() == null
                || tfType.getText() == null || datePicker.getValue() == null || cbStartTime.getValue() == null
                || cbEndTime.getValue() == null || userID_box.getValue() == null || contactName_box.getValue() == null) {
            //removed this: 'datePicker.getValue() == null' from the above ^, still got exception
            //
            isValid = false;
            Alerts.invalidFieldHandler();


        } else {
            isValid = true;

        }
        return isValid;
    }


    private final LocalTime est8Am = LocalTime.of(8, 0);
    private final LocalTime est10Pm = LocalTime.of(22, 0);

    /**
     * This method creates a new appointment and adds it to the database via a prepared statement.
     */
    public void preparedInsert() {

        if (validateFields2(true)) {
            PreparedStatement pstatement;
            String sql = "INSERT into appointments(Title, Description, Location, Type, Start, End, Customer_ID, User_ID, Contact_ID) Values(?,?,?,?,?,?,?,?,?)";
            try {

                pstatement = DBConnection.getConnection().prepareStatement(sql);
                //pstatement.setInt(1, Integer.parseInt(tfCustomerID.getText()));
                pstatement.setString(1, tfTitle.getText());
                pstatement.setString(2, tfDescription.getText());
                pstatement.setString(3, tfLocation.getText());
                pstatement.setString(4, tfType.getText());

                selectedDate = datePicker.getValue();
                startDateAndTime = LocalDateTime.of(selectedDate, cbStartTime.getValue());
                endDateAndTime = LocalDateTime.of(selectedDate, cbEndTime.getValue());


                ZoneId userZoneId = ZoneId.systemDefault();


                ZonedDateTime zoneDateTimeStart = ZonedDateTime.of(startDateAndTime, userZoneId);
                ZonedDateTime zoneDateTimeEnd = ZonedDateTime.of(endDateAndTime, userZoneId);


                ZoneId estZoneId = ZoneId.of("US/Eastern");


                ZonedDateTime estZoneDateTimeStart = zoneDateTimeStart.withZoneSameInstant(estZoneId);
                ZonedDateTime estZoneDateTimeEnd = zoneDateTimeEnd.withZoneSameInstant(estZoneId);

                LocalTime userStartEST = estZoneDateTimeStart.toLocalDateTime().toLocalTime();
                LocalTime userEndEST = estZoneDateTimeEnd.toLocalDateTime().toLocalTime();


                if (userStartEST.isAfter(userEndEST) || userStartEST.equals(userEndEST)) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("ERROR");
                    alert.setHeaderText("Selected appointment start time is after or equal to end time.");
                    alert.setContentText("Please select different appointment start and/or end time slot.");

                    alert.showAndWait();
                    return;
                }

                //compare converted Eastern time zone appt times picked by user to set business hours in EST:
                if (userStartEST.isBefore(est8Am) || userEndEST.isAfter(est10Pm)) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("ERROR");
                    alert.setHeaderText("Selected appointment times are outside of business hours.");
                    alert.setContentText("Please select different appointment times. Business hours are between 8:00AM-10:00PM EST.");

                    alert.showAndWait();
                    return;
                }


                //Check if there is a previously scheduled appointment for customer that will overlap with new appt:
                if (checkApptOverlap() == true) {
                    Alerts.appointmentOverlaps();
                    return;
                }


                pstatement.setTimestamp(5, Timestamp.valueOf(startDateAndTime));
                System.out.println("user selected start time: " + startDateAndTime);

                pstatement.setTimestamp(6, Timestamp.valueOf(endDateAndTime));

                pstatement.setInt(7, Integer.parseInt(tfCustomerID.getText()));

                pstatement.setInt(8, Integer.parseInt(String.valueOf(userID_box.getSelectionModel().getSelectedItem())));

                ObservableList<Contacts> contactsOL = AddAppointmentController.getContactsList();
                String tempContactName = contactName_box.getSelectionModel().getSelectedItem();
                int contactID = 0;
                for (Contacts contact : contactsOL) {
                    if (tempContactName.equals(contact.getContact_name())) {
                        contactID = contact.getContact_id();
                    }
                }
                pstatement.setInt(9, Integer.parseInt(String.valueOf(contactID)));
                pstatement.execute();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }


    /**
     * This method allows the user to save new appointment information, so long as the data the user provided is valid
     *
     * @param event (mouse click Save Button)
     * @throws IOException
     */
    public void addAppointmentButtonClick(ActionEvent event) throws IOException {
        preparedInsert();
        Parent root = FXMLLoader.load(getClass().getResource("mainMenu.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        // }
    }


    /**
     * This method tests if the new appointment to be scheduled overlaps with an existing appointment on record.
     *
     * @return Returns match that contains a boolean value. True if there is an overlapping appointment.
     * False if there are no matches.
     */
    public boolean checkApptOverlap() {
        ObservableList<Appointment> apptMatches = DBQuery.getAppointmentsPerCustomer(Integer.parseInt(tfCustomerID.getText()));
        boolean match = false;
        for (int i = 0; i < apptMatches.size(); i++) {
            Appointment appt = apptMatches.get(i);
            LocalDateTime startAppt = appt.getStart_time();
            LocalDateTime endAppt = appt.getEnd_time();

            if (startDateAndTime.isAfter(startAppt.minusMinutes(0)) && startDateAndTime.isBefore(endAppt.plusMinutes(0))) {
                match = true;
                break;

            } else if (endDateAndTime.isAfter(startAppt.minusMinutes(1)) && endDateAndTime.isBefore(endAppt.plusMinutes(1))) {
                match = true;
                break;

            } else if (startDateAndTime.isBefore(startAppt.plusMinutes(1)) && endDateAndTime.isAfter(endAppt.minusMinutes(1))) {
                match = true;
                break;

            } else {
                match = false;
                continue;
            }
        }
        return match;
    }


    /**
     * This method initializes the add appointment screen. It populates both the customer and contact tables.
     * And sets the localdatetime options for the user to select appointment times in comboboxes.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        taHours.setDisable(true);
        addAppointmentButton.setDisable(true);
        DBQuery.userIDList.clear();
        DBQuery.getContactsList().clear();
        DBQuery.contactsNameList.clear();

        showCustomers();
        showContacts();
        contactsTable.setDisable(true);
        tfCustomerID.setDisable(true);
        tfCustomerName.setDisable(true);

        DBQuery.getContactsList();
        userID_box.setItems(DBQuery.getUserIDList());
        contactName_box.setItems(DBQuery.getContactsNameList());

        cbStartTime.getItems().add(LocalTime.parse("00:00"));
        cbStartTime.getItems().add(LocalTime.parse("00:15"));
        cbStartTime.getItems().add(LocalTime.parse("00:30"));
        cbStartTime.getItems().add(LocalTime.parse("00:45"));
        cbStartTime.getItems().add(LocalTime.parse("01:00"));
        cbStartTime.getItems().add(LocalTime.parse("01:15"));
        cbStartTime.getItems().add(LocalTime.parse("01:30"));
        cbStartTime.getItems().add(LocalTime.parse("01:45"));
        cbStartTime.getItems().add(LocalTime.parse("02:00"));
        cbStartTime.getItems().add(LocalTime.parse("02:15"));
        cbStartTime.getItems().add(LocalTime.parse("02:30"));
        cbStartTime.getItems().add(LocalTime.parse("02:45"));
        cbStartTime.getItems().add(LocalTime.parse("03:00"));
        cbStartTime.getItems().add(LocalTime.parse("03:15"));
        cbStartTime.getItems().add(LocalTime.parse("03:30"));
        cbStartTime.getItems().add(LocalTime.parse("03:45"));
        cbStartTime.getItems().add(LocalTime.parse("04:00"));
        cbStartTime.getItems().add(LocalTime.parse("04:15"));
        cbStartTime.getItems().add(LocalTime.parse("04:30"));
        cbStartTime.getItems().add(LocalTime.parse("04:45"));
        cbStartTime.getItems().add(LocalTime.parse("05:00"));
        cbStartTime.getItems().add(LocalTime.parse("05:15"));
        cbStartTime.getItems().add(LocalTime.parse("05:30"));
        cbStartTime.getItems().add(LocalTime.parse("05:45"));
        cbStartTime.getItems().add(LocalTime.parse("06:00"));
        cbStartTime.getItems().add(LocalTime.parse("06:15"));
        cbStartTime.getItems().add(LocalTime.parse("06:30"));
        cbStartTime.getItems().add(LocalTime.parse("06:45"));
        cbStartTime.getItems().add(LocalTime.parse("07:00"));
        cbStartTime.getItems().add(LocalTime.parse("07:15"));
        cbStartTime.getItems().add(LocalTime.parse("07:30"));
        cbStartTime.getItems().add(LocalTime.parse("07:45"));
        cbStartTime.getItems().add(LocalTime.parse("08:00"));
        cbStartTime.getItems().add(LocalTime.parse("08:15"));
        cbStartTime.getItems().add(LocalTime.parse("08:30"));
        cbStartTime.getItems().add(LocalTime.parse("08:45"));
        cbStartTime.getItems().add(LocalTime.parse("09:00"));
        cbStartTime.getItems().add(LocalTime.parse("09:15"));
        cbStartTime.getItems().add(LocalTime.parse("09:30"));
        cbStartTime.getItems().add(LocalTime.parse("09:45"));
        cbStartTime.getItems().add(LocalTime.parse("10:00"));
        cbStartTime.getItems().add(LocalTime.parse("10:15"));
        cbStartTime.getItems().add(LocalTime.parse("10:30"));
        cbStartTime.getItems().add(LocalTime.parse("10:45"));
        cbStartTime.getItems().add(LocalTime.parse("11:00"));
        cbStartTime.getItems().add(LocalTime.parse("11:15"));
        cbStartTime.getItems().add(LocalTime.parse("11:30"));
        cbStartTime.getItems().add(LocalTime.parse("11:45"));
        cbStartTime.getItems().add(LocalTime.parse("12:00"));
        cbStartTime.getItems().add(LocalTime.parse("12:15"));
        cbStartTime.getItems().add(LocalTime.parse("12:30"));
        cbStartTime.getItems().add(LocalTime.parse("12:45"));
        cbStartTime.getItems().add(LocalTime.parse("13:00"));
        cbStartTime.getItems().add(LocalTime.parse("13:15"));
        cbStartTime.getItems().add(LocalTime.parse("13:30"));
        cbStartTime.getItems().add(LocalTime.parse("13:45"));
        cbStartTime.getItems().add(LocalTime.parse("14:00"));
        cbStartTime.getItems().add(LocalTime.parse("14:15"));
        cbStartTime.getItems().add(LocalTime.parse("14:30"));
        cbStartTime.getItems().add(LocalTime.parse("14:45"));
        cbStartTime.getItems().add(LocalTime.parse("15:00"));
        cbStartTime.getItems().add(LocalTime.parse("15:15"));
        cbStartTime.getItems().add(LocalTime.parse("15:30"));
        cbStartTime.getItems().add(LocalTime.parse("15:45"));
        cbStartTime.getItems().add(LocalTime.parse("16:00"));
        cbStartTime.getItems().add(LocalTime.parse("16:15"));
        cbStartTime.getItems().add(LocalTime.parse("16:30"));
        cbStartTime.getItems().add(LocalTime.parse("16:45"));
        cbStartTime.getItems().add(LocalTime.parse("17:00"));
        cbStartTime.getItems().add(LocalTime.parse("17:15"));
        cbStartTime.getItems().add(LocalTime.parse("17:30"));
        cbStartTime.getItems().add(LocalTime.parse("17:45"));
        cbStartTime.getItems().add(LocalTime.parse("18:00"));
        cbStartTime.getItems().add(LocalTime.parse("18:15"));
        cbStartTime.getItems().add(LocalTime.parse("18:30"));
        cbStartTime.getItems().add(LocalTime.parse("18:45"));
        cbStartTime.getItems().add(LocalTime.parse("19:00"));
        cbStartTime.getItems().add(LocalTime.parse("19:15"));
        cbStartTime.getItems().add(LocalTime.parse("19:30"));
        cbStartTime.getItems().add(LocalTime.parse("19:45"));
        cbStartTime.getItems().add(LocalTime.parse("20:00"));
        cbStartTime.getItems().add(LocalTime.parse("20:15"));
        cbStartTime.getItems().add(LocalTime.parse("20:30"));
        cbStartTime.getItems().add(LocalTime.parse("20:45"));
        cbStartTime.getItems().add(LocalTime.parse("21:00"));
        cbStartTime.getItems().add(LocalTime.parse("21:15"));
        cbStartTime.getItems().add(LocalTime.parse("21:30"));
        cbStartTime.getItems().add(LocalTime.parse("21:45"));
        cbStartTime.getItems().add(LocalTime.parse("22:00"));
        cbStartTime.getItems().add(LocalTime.parse("22:15"));
        cbStartTime.getItems().add(LocalTime.parse("22:30"));
        cbStartTime.getItems().add(LocalTime.parse("22:45"));
        cbStartTime.getItems().add(LocalTime.parse("23:00"));
        cbStartTime.getItems().add(LocalTime.parse("23:15"));
        cbStartTime.getItems().add(LocalTime.parse("23:30"));
        cbStartTime.getItems().add(LocalTime.parse("23:45"));

        cbEndTime.getItems().add(LocalTime.parse("00:00"));
        cbEndTime.getItems().add(LocalTime.parse("00:15"));
        cbEndTime.getItems().add(LocalTime.parse("00:30"));
        cbEndTime.getItems().add(LocalTime.parse("00:45"));
        cbEndTime.getItems().add(LocalTime.parse("01:00"));
        cbEndTime.getItems().add(LocalTime.parse("01:15"));
        cbEndTime.getItems().add(LocalTime.parse("01:30"));
        cbEndTime.getItems().add(LocalTime.parse("01:45"));
        cbEndTime.getItems().add(LocalTime.parse("02:00"));
        cbEndTime.getItems().add(LocalTime.parse("02:15"));
        cbEndTime.getItems().add(LocalTime.parse("02:30"));
        cbEndTime.getItems().add(LocalTime.parse("02:45"));
        cbEndTime.getItems().add(LocalTime.parse("03:00"));
        cbEndTime.getItems().add(LocalTime.parse("03:15"));
        cbEndTime.getItems().add(LocalTime.parse("03:30"));
        cbEndTime.getItems().add(LocalTime.parse("03:45"));
        cbEndTime.getItems().add(LocalTime.parse("04:00"));
        cbEndTime.getItems().add(LocalTime.parse("04:15"));
        cbEndTime.getItems().add(LocalTime.parse("04:30"));
        cbEndTime.getItems().add(LocalTime.parse("04:45"));
        cbEndTime.getItems().add(LocalTime.parse("05:00"));
        cbEndTime.getItems().add(LocalTime.parse("05:15"));
        cbEndTime.getItems().add(LocalTime.parse("05:30"));
        cbEndTime.getItems().add(LocalTime.parse("05:45"));
        cbEndTime.getItems().add(LocalTime.parse("06:00"));
        cbEndTime.getItems().add(LocalTime.parse("06:15"));
        cbEndTime.getItems().add(LocalTime.parse("06:30"));
        cbEndTime.getItems().add(LocalTime.parse("06:45"));
        cbEndTime.getItems().add(LocalTime.parse("07:00"));
        cbEndTime.getItems().add(LocalTime.parse("07:15"));
        cbEndTime.getItems().add(LocalTime.parse("07:30"));
        cbEndTime.getItems().add(LocalTime.parse("07:45"));
        cbEndTime.getItems().add(LocalTime.parse("08:00"));
        cbEndTime.getItems().add(LocalTime.parse("08:15"));
        cbEndTime.getItems().add(LocalTime.parse("08:30"));
        cbEndTime.getItems().add(LocalTime.parse("08:45"));
        cbEndTime.getItems().add(LocalTime.parse("09:00"));
        cbEndTime.getItems().add(LocalTime.parse("09:15"));
        cbEndTime.getItems().add(LocalTime.parse("09:30"));
        cbEndTime.getItems().add(LocalTime.parse("09:45"));
        cbEndTime.getItems().add(LocalTime.parse("10:00"));
        cbEndTime.getItems().add(LocalTime.parse("10:15"));
        cbEndTime.getItems().add(LocalTime.parse("10:30"));
        cbEndTime.getItems().add(LocalTime.parse("10:45"));
        cbEndTime.getItems().add(LocalTime.parse("11:00"));
        cbEndTime.getItems().add(LocalTime.parse("11:15"));
        cbEndTime.getItems().add(LocalTime.parse("11:30"));
        cbEndTime.getItems().add(LocalTime.parse("11:45"));
        cbEndTime.getItems().add(LocalTime.parse("12:00"));
        cbEndTime.getItems().add(LocalTime.parse("12:15"));
        cbEndTime.getItems().add(LocalTime.parse("12:30"));
        cbEndTime.getItems().add(LocalTime.parse("12:45"));
        cbEndTime.getItems().add(LocalTime.parse("13:00"));
        cbEndTime.getItems().add(LocalTime.parse("13:15"));
        cbEndTime.getItems().add(LocalTime.parse("13:30"));
        cbEndTime.getItems().add(LocalTime.parse("13:45"));
        cbEndTime.getItems().add(LocalTime.parse("14:00"));
        cbEndTime.getItems().add(LocalTime.parse("14:15"));
        cbEndTime.getItems().add(LocalTime.parse("14:30"));
        cbEndTime.getItems().add(LocalTime.parse("14:45"));
        cbEndTime.getItems().add(LocalTime.parse("15:00"));
        cbEndTime.getItems().add(LocalTime.parse("15:15"));
        cbEndTime.getItems().add(LocalTime.parse("15:30"));
        cbEndTime.getItems().add(LocalTime.parse("15:45"));
        cbEndTime.getItems().add(LocalTime.parse("16:00"));
        cbEndTime.getItems().add(LocalTime.parse("16:15"));
        cbEndTime.getItems().add(LocalTime.parse("16:30"));
        cbEndTime.getItems().add(LocalTime.parse("16:45"));
        cbEndTime.getItems().add(LocalTime.parse("17:00"));
        cbEndTime.getItems().add(LocalTime.parse("17:15"));
        cbEndTime.getItems().add(LocalTime.parse("17:30"));
        cbEndTime.getItems().add(LocalTime.parse("17:45"));
        cbEndTime.getItems().add(LocalTime.parse("18:00"));
        cbEndTime.getItems().add(LocalTime.parse("18:15"));
        cbEndTime.getItems().add(LocalTime.parse("18:30"));
        cbEndTime.getItems().add(LocalTime.parse("18:45"));
        cbEndTime.getItems().add(LocalTime.parse("19:00"));
        cbEndTime.getItems().add(LocalTime.parse("19:15"));
        cbEndTime.getItems().add(LocalTime.parse("19:30"));
        cbEndTime.getItems().add(LocalTime.parse("19:45"));
        cbEndTime.getItems().add(LocalTime.parse("20:00"));
        cbEndTime.getItems().add(LocalTime.parse("20:15"));
        cbEndTime.getItems().add(LocalTime.parse("20:30"));
        cbEndTime.getItems().add(LocalTime.parse("20:45"));
        cbEndTime.getItems().add(LocalTime.parse("21:00"));
        cbEndTime.getItems().add(LocalTime.parse("21:15"));
        cbEndTime.getItems().add(LocalTime.parse("21:30"));
        cbEndTime.getItems().add(LocalTime.parse("21:45"));
        cbEndTime.getItems().add(LocalTime.parse("22:00"));
        cbEndTime.getItems().add(LocalTime.parse("22:15"));
        cbEndTime.getItems().add(LocalTime.parse("22:30"));
        cbEndTime.getItems().add(LocalTime.parse("22:45"));
        cbEndTime.getItems().add(LocalTime.parse("23:00"));
        cbEndTime.getItems().add(LocalTime.parse("23:15"));
        cbEndTime.getItems().add(LocalTime.parse("23:30"));
        cbEndTime.getItems().add(LocalTime.parse("23:45"));
    }

}
