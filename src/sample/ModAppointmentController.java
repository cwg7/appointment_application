package sample;

import com.mysql.cj.xdevapi.Table;
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
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ResourceBundle;
import sample.Appointment;

import javax.swing.*;

import static java.sql.Timestamp.valueOf;

public class ModAppointmentController implements Initializable {

    @FXML
    private ScrollBar scrollBar;

    @FXML
    private TableView <Appointment> appointmentsTable;

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
    @FXML
    private Label lblWelcome;
    @FXML
    private Button selectButton;
    @FXML
    private Label lblStart;
    @FXML
    private Label lblEnd;
    @FXML
    private TextField tfApptID;
    @FXML
    private TextField tfTitle;
    @FXML
    private TextField tfDescription;
    @FXML
    private TextField tfLocation;
    @FXML
    private TextField tfContactID;
    @FXML
    private TextField tfType;
    @FXML
    private TextField tfStart;
    @FXML
    private TextField tfEnd;
    @FXML
    private Label lblUserID;
    @FXML
    private TextField tfUserID;
    ComboBox<Integer> userID_box;
    @FXML
    ComboBox<String> contactName_box;
    @FXML
    private Button saveChangesButton;
    @FXML
    private Label lblCustomerID;
    @FXML
    private TextField tfCustomerID;
    @FXML
    private Button deleteButton;

    @FXML
    private Button mainMenuButton;
    private Stage stage;
    private Scene scene;

    private LocalDate selectedDate;
    private LocalTime startTime;
    private LocalTime endTime;
    private Label lblNewStartTime;
    private Label lblNewEndTime;


    @FXML
    private Label lblDate;
    @FXML
    private Label lblContact;

    @FXML
    private DatePicker datePicker;

    @FXML
    private ComboBox<LocalTime> cbStartTime;
    @FXML
    private ComboBox<LocalTime> cbEndTime;

    @FXML
    private ComboBox<String> contactsComboBox;

    //private LocalDate selectedDate;
    private LocalDateTime startDateAndTime;
    private LocalDateTime endDateAndTime;


    public void showAppointments() {
        ObservableList<Appointment> list = MainMenuController.getAppointments();
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
        appointmentsTable.setItems(list);


    }

    //LocalDateTime startTimeObject;

    public void selectButtonClick(ActionEvent event) throws SQLException {

        saveChangesButton.setDisable(false);

        if (appointmentsTable.getSelectionModel().getSelectedItem() == null){
            Alerts.selectHandler2();
        }
        else if (appointmentsTable.getItems() != null) {
           // ObservableList<Appointment> appointment = appointmentsTable.getSelectionModel().getSelectedItems();
            //tfApptID =
            Appointment selectedAppointment;
            selectedAppointment = (Appointment) appointmentsTable.getSelectionModel().getSelectedItem();

            LocalDateTime startDateAndTime = selectedAppointment.getStart_time();
            LocalDate selectedDate = startDateAndTime.toLocalDate();

            LocalTime startClock = startDateAndTime.toLocalTime();

            /*startDateAndTime = LocalDateTime.of(selectedDate,cbStartTime.getValue());
            endDateAndTime = LocalDateTime.of(selectedDate,cbEndTime.getValue());*/

            LocalDateTime endTime = selectedAppointment.getEnd_time();
            LocalTime endClock =  endTime.toLocalTime();

            int selectedContactID = selectedAppointment.getContact_id();
            String selectedContactName = DBQuery.getContactNameByContactID(selectedContactID);


            //Customer selectedCustomer;
            //selectedCustomer = (Customer) modCustomersTable.getSelectionModel().getSelectedItem();

            if (selectedAppointment == null){
                Alerts.modHandler2();
            }
            else {
                int appointmentID = selectedAppointment.getAppointment_id();
                //int divisionID = selectedCustomer.getDivision_id();

                // = (Customer) modCustomersTable.getSelectionModel().getSelectedItems();


                //tfApptID.setDisable(false);
               // tfApptID.setText(selectedAppointment.getAppointment_id());
               // tfApptID.setText(Integer.parseInt(selectedAppointment.getAppointment_id()));
               // tfApptID.setText(selectedAppointment.getAppointment_id());
                tfTitle.setDisable(false);
                tfDescription.setDisable(false);
                tfLocation.setDisable(false);
                tfContactID.setDisable(false);
                tfUserID.setDisable(false);
                //tfStart.setDisable(false);
                //tfEnd.setDisable(false);
                tfType.setDisable(false);
                tfCustomerID.setDisable(false);
                tfApptID.setText(String.valueOf(selectedAppointment.getAppointment_id()));
                tfTitle.setText(selectedAppointment.getTitle());
                tfDescription.setText(selectedAppointment.getDescription());
                tfLocation.setText(selectedAppointment.getLocation());
                tfContactID.setText(String.valueOf(selectedAppointment.getContact_id()));
                tfType.setText(selectedAppointment.getType());
                tfStart.setText(String.valueOf(Timestamp.valueOf(selectedAppointment.getStart_time())));
                tfEnd.setText(String.valueOf(Timestamp.valueOf(selectedAppointment.getEnd_time())));
                tfCustomerID.setText(String.valueOf(selectedAppointment.getCustomer_id()));
                tfUserID.setText(String.valueOf(selectedAppointment.getUser_id()));

                
                //int contactID = tfContactID.get
               // contactsComboBox.setItems(DBQuery.getContactNameByContactID(contactID));

                datePicker.setValue(selectedDate);
                cbStartTime.setValue(startClock);
                cbEndTime.setValue(endClock);

                //tfContactID.setDisable(true);
                //contactsComboBox.setValue(selectedContactName);

                setComboBoxStart();
                setComboBoxEnd();

                //selectedContactName = contactName_box.getValue();


                //tfContactID.setText(DBQuery.getContactIDByContactName(selectedContactName));

            }

        }

    }

    public void preparedUpdate() {
        Appointment selectedAppointment = (Appointment) appointmentsTable.getSelectionModel().getSelectedItem();
       /*
        int selectedAppointmentId = selectedAppointment.getAppointment_id();
        String selectedUserID = tfUserID.getText();

        ObservableList users = FXCollections.observableArrayList();
        users.add(DBQuery.getUserNames());
        int usersCount = users.size();*/

        //String selectedName = contactName_box.getSelectionModel().getSelectedItem();

        PreparedStatement pstatement;
        String sql = "UPDATE appointments SET Title = ?, Description = ?, Location = ?, Type = ?, Start = ?, End = ?, Customer_ID = ?, User_ID = ?, Contact_ID = ? WHERE Appointment_ID = ?";
        try {
            // assert pstatement != null;
            pstatement = DBConnection.getConnection().prepareStatement(sql);
            pstatement.setString(1, tfTitle.getText());
            pstatement.setString(2, tfDescription.getText());
            pstatement.setString(3, tfLocation.getText());
            pstatement.setString(4, tfType.getText());

            selectedDate = datePicker.getValue();
            LocalTime startTime = cbStartTime.getValue();
            LocalTime endTime = cbEndTime.getValue();

            /*selectedDate = datePicker.getValue();
            startTime = LocalDateTime.of(selectedDate,cbStartTime.getValue());
            endDateAndTime = LocalDateTime.of(selectedDate,cbEndTime.getValue());*/

            LocalDateTime startTimeAndDate = LocalDateTime.of(selectedDate,startTime);
            LocalDateTime endTimeAndDate = LocalDateTime.of(selectedDate,endTime);



            pstatement.setTimestamp(5, Timestamp.valueOf(startTimeAndDate));
            pstatement.setTimestamp(6, Timestamp.valueOf(endTimeAndDate));


            int specifiedCustomerID = Integer.parseInt(tfCustomerID.getText());
            if (DBQuery.getAllCustomerIDs().contains(specifiedCustomerID)) {
                pstatement.setInt(7, specifiedCustomerID);
            }
            else{
                Alerts.invalidCustomerID();
                return;
            }

            int specifiedUserID = Integer.parseInt(tfUserID.getText());
            ObservableList <Integer> allUsers = DBQuery.getAllUserIDs();
            boolean isThere = false;

            for (Integer userID: allUsers){
                if (userID == specifiedUserID){
                    isThere = true;
                }
            }
            if (isThere) {
                pstatement.setInt(8, Integer.parseInt(tfUserID.getText()));
            }
            else{
                Alerts.invalidUserID();
                return;
            }

       if (String.valueOf(DBQuery.getAllContactIDs()).contains(tfContactID.getText())){
                pstatement.setInt(9, Integer.parseInt(tfContactID.getText()));
                pstatement.setInt(10, Integer.parseInt(tfApptID.getText()));
                pstatement.execute();

            }
            else{
                Alerts.invalidContactID();
                pstatement.cancel();
                return;
            }
            //pstatement.setInt(10, Integer.parseInt(tfApptID.getText()));



           // pstatement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            Alerts.invalidTextFields();
            //return;

            //Alerts.invalidContactID();
        }


    }

    public void preparedDelete() {
        Appointment selectedAppointment = (Appointment) appointmentsTable.getSelectionModel().getSelectedItem();
        int selectedAppointmentID = selectedAppointment.getAppointment_id();

        PreparedStatement pstatement;
        String sql = "DELETE FROM appointments WHERE Appointment_ID = ?";
        try {
            pstatement = DBConnection.getConnection().prepareStatement(sql);
            pstatement.setInt(1,selectedAppointmentID);
            pstatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @FXML
    public void deleteAppointment(ActionEvent event) throws IOException {
        Appointment selectedAppointment = (Appointment) appointmentsTable.getSelectionModel().getSelectedItem();
        if (selectedAppointment != null) {
            preparedDelete();
            showAppointments();
            Alerts.deleteSuccessful2();
            //appointmentsTable.refresh();
        }
        else {
            Alerts.delHandler2();
        }

    }




    @FXML
    public void saveChanges(ActionEvent event) throws IOException {
        try {
            preparedUpdate();
            //contactsComboBox.getItems().clear();
            Parent root = FXMLLoader.load(getClass().getResource("mainMenu.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
        catch(Exception e){
            Alerts.invalidTextFields();
        }

    }

    public void setComboBoxStart(){

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

    }

    public void setComboBoxEnd(){

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


    }



    @FXML
    public void goToMain(ActionEvent event) throws IOException {
        //contactsComboBox.getItems().clear();
        Parent root = FXMLLoader.load(getClass().getResource("mainMenu.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        saveChangesButton.setDisable(true);
        MainMenuController.getAppointments();
        showAppointments();
        //contactsComboBox.getItems().clear();
        //contactsComboBox.setItems(DBQuery.getContactsNameList());

        tfApptID.setDisable(true);
        tfTitle.setDisable(true);
        tfDescription.setDisable(true);
        tfLocation.setDisable(true);
        tfContactID.setDisable(true);
        tfType.setDisable(true);
        tfStart.setDisable(true);
        tfEnd.setDisable(true);
        tfCustomerID.setDisable(true);
        tfUserID.setDisable(true);


        //tfContactID.setText(DBQuery.getContactIDByContactName(contactsComboBox.getValue()));



    }




}
