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
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.EventObject;
import java.util.ResourceBundle;
import sample.Contacts;

import static java.sql.Timestamp.valueOf;

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

   /* @FXML
    private Button testButton;*/

    @FXML
    private TextArea taTestArea;




/*
    LocalTime startTimeObject;
    LocalTime endTimeObject;
    LocalDateTime startDateAndTimeObject;
    LocalDateTime getEndDateAndTimeObject;*/




    private Stage stage;
    private Scene scene;


    public AddAppointmentController() throws SQLException {
    }

   

    public static ObservableList<Customer> getCustomerList() {
        ObservableList<Customer> customerList = FXCollections.observableArrayList();
        Connection conn = DBConnection.getConnection();
        String query = "SELECT * FROM customers";
        Statement st;
        ResultSet rs;

        // rs.getInt("Customer_ID"),

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

    public void showCustomers() {
        ObservableList<Customer> list = getCustomerList();
        customerIDCol.setCellValueFactory(new PropertyValueFactory<Customer, Integer>("id"));
        customerNameCol.setCellValueFactory(new PropertyValueFactory<Customer, String>("name"));
        customerTable.setItems(list);


    }

    public static ObservableList<Contacts> getContactsList() {
        ObservableList<Contacts> newContactsList = FXCollections.observableArrayList();
        Connection conn = DBConnection.getConnection();
        String query = "SELECT * FROM contacts";
        Statement st;
        ResultSet rs;

        // rs.getInt("Customer_ID"),

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

    public void showContacts() {
        ObservableList<Contacts> list = getContactsList();
        contactIDCol.setCellValueFactory(new PropertyValueFactory<Customer, Integer>("contact_id"));
        contactNameCol.setCellValueFactory(new PropertyValueFactory<Customer, String>("contact_name"));
        contactsTable.setItems(list);


    }


    @FXML
    public void backToMainButtonClick(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("mainMenu.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }
    @FXML
    public void selectCustomerButtonClick(ActionEvent event) throws IOException {
        if (customerTable.getSelectionModel().getSelectedItem() == null){
            Alerts.selectHandler();
        }
        else {
            addAppointmentButton.setDisable(false);
            Customer selectedCustomer = (Customer) customerTable.getSelectionModel().getSelectedItem();
            int selectedCustomerID = selectedCustomer.getId();
            String selectedCustomerName = selectedCustomer.getName();
            tfCustomerID.setText(String.valueOf(selectedCustomerID));
            tfCustomerName.setText(selectedCustomerName);
            customerTable.setDisable(true);
        }

    }




    public void validateFields(){
        if (tfTitle.getText() == null || tfDescription.getText() == null || tfLocation.getText() == null
                || tfType.getText() == null || datePicker.getValue() == null || cbStartTime.getValue() == null
                || cbEndTime.getValue() == null || userID_box.getValue() == null || contactName_box.getValue() == null) {
  //removed this: 'datePicker.getValue() == null' from the above ^, still got exception
  //
            Alerts.invalidFieldHandler();
        }
        else{
            tfTitle.clear();
            tfDescription.clear();
            tfLocation.clear();
            tfType.clear();
            datePicker.setValue(null);
            cbStartTime.setValue(null);
            cbEndTime.setValue(null);
            userID_box.setValue(null);
            contactName_box.setValue(null);
        }
    }


    public void preparedInsert(){
        //verify();
        //getInfo();

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
            startDateAndTime = LocalDateTime.of(selectedDate,cbStartTime.getValue());
            pstatement.setTimestamp(5, Timestamp.valueOf(startDateAndTime));

            endDateAndTime = LocalDateTime.of(selectedDate,cbEndTime.getValue());
            pstatement.setTimestamp(6, Timestamp.valueOf(endDateAndTime));



            pstatement.setInt(7, Integer.parseInt(tfCustomerID.getText()));

            pstatement.setInt(8, Integer.parseInt(String.valueOf(userID_box.getSelectionModel().getSelectedItem())));


            ObservableList<Contacts> contactsOL = AddAppointmentController.getContactsList();
            String tempContactName = contactName_box.getSelectionModel().getSelectedItem();
            int contactID = 0;
            for (Contacts contact : contactsOL)

            {
                if (tempContactName.equals(contact.getContact_name())) {
                    contactID = contact.getContact_id();
                }


            }
            pstatement.setInt(9, Integer.parseInt(String.valueOf(contactID)));


            pstatement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        //LocalDateTime timeInfo = tfStart.get
    }


    public void addAppointmentButtonClick(ActionEvent event) throws IOException {
        //getInfo();
        //validateFields();
        //^validation check is not working
        preparedInsert();
        Parent root = FXMLLoader.load(getClass().getResource("mainMenu.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void goToTimePage(ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("time.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }





    @Override
    public void initialize(URL url, ResourceBundle rb) {

        //tfStart.se
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
//        contact_box.setItems(DBQuery.getContactsIDList());

        userID_box.setItems(DBQuery.getUserIDList());
        //DBQuery.getContactsNameList();
        contactName_box.setItems(DBQuery.getContactsNameList());

        //cbContacts.setItems(DBQuery.getContactNames());

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





       /* cbEndTime.getItems().add(LocalTime.parse("00:00"));
        cbEndTime.getItems().add(LocalTime.parse("01:00"));
        cbEndTime.getItems().add(LocalTime.parse("02:00"));
        cbEndTime.getItems().add(LocalTime.parse("03:00"));
        cbEndTime.getItems().add(LocalTime.parse("04:00"));
        cbEndTime.getItems().add(LocalTime.parse("05:00"));
        cbEndTime.getItems().add(LocalTime.parse("06:00"));
        cbEndTime.getItems().add(LocalTime.parse("07:00"));
        cbEndTime.getItems().add(LocalTime.parse("08:00"));
        cbEndTime.getItems().add(LocalTime.parse("09:00"));
        cbEndTime.getItems().add(LocalTime.parse("10:00"));
        cbEndTime.getItems().add(LocalTime.parse("11:00"));
        cbEndTime.getItems().add(LocalTime.parse("12:00"));
        cbEndTime.getItems().add(LocalTime.parse("13:00"));
        cbEndTime.getItems().add(LocalTime.parse("14:00"));
        cbEndTime.getItems().add(LocalTime.parse("15:00"));
        cbEndTime.getItems().add(LocalTime.parse("16:00"));
        cbEndTime.getItems().add(LocalTime.parse("17:00"));
        cbEndTime.getItems().add(LocalTime.parse("18:00"));
        cbEndTime.getItems().add(LocalTime.parse("19:00"));
        cbEndTime.getItems().add(LocalTime.parse("20:00"));
        cbEndTime.getItems().add(LocalTime.parse("21:00"));
        cbEndTime.getItems().add(LocalTime.parse("22:00"));
        cbEndTime.getItems().add(LocalTime.parse("23:00"));*/

    }

}
