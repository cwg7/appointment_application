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
import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

/**
 * This is the ReportsController class
 */
public class ReportsController implements Initializable {

    @FXML
    private Label lblPlease;
    @FXML
    private TextField tfNumAppts;
    @FXML
    private Label lblThere;
    @FXML
    private Label lblForThe;
    @FXML
    private Label lblType;
    @FXML
    private Label lblMonth;
    @FXML
    private TextField tfApptType;
    @FXML
    private Label lblForMonth;
    @FXML
    private TextField tfMonth2;
    @FXML
    private Label lblContact;
    @FXML
    private TextField tfCustNum;
    @FXML
    private TextField tfApptNum;
    @FXML
    private Label lblCustomers;
    @FXML
    private Label lblReport1;
    @FXML
    private Label lblReport2;
    @FXML
    private Label lblReport3;


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
    private Button showButton;

    @FXML
    private Button showAllAppts;

    @FXML
    private Button contactReportButton;
    @FXML
    private Button pressTotNumButton;

    @FXML
    private TextField tfTest;

    @FXML
    private Button mainMenuButton;
    private Stage stage;
    private Scene scene;

    @FXML
    private ComboBox<String> monthComboBox;

    @FXML
    private ComboBox<String> typeComboBox;

    @FXML
    private ComboBox<String> contactsComboBox;

    @FXML
    private Label lblByMonth;

    public ObservableList<String> months = FXCollections.observableArrayList();
    public ObservableList<String> types = FXCollections.observableArrayList();


    Month january = Month.JANUARY;
    int janMonthNum = january.getValue();

    Month february = Month.FEBRUARY;
    int febMonthNum = february.getValue();

    Month march = Month.MARCH;
    int marchMonthNum = march.getValue();

    Month april = Month.APRIL;
    int aprilMonthNum = april.getValue();

    Month june = Month.JUNE;
    int juneMonthNum = june.getValue();

    Month july = Month.JULY;
    int julyMonthNum = july.getValue();

    Month august = Month.AUGUST;
    int augustMonthNum = august.getValue();

    Month september = Month.SEPTEMBER;
    int septemberMonthNum = september.getValue();

    Month october = Month.OCTOBER;
    int octoberMonthNum = october.getValue();

    Month november = Month.NOVEMBER;
    int novemberMonthNum = november.getValue();

    Month december = Month.DECEMBER;
    int decemberMonthNum = december.getValue();

    /**
     * This method populates month selections in the combobox
     */
    public void addMonths() {
        months.add(0, "January");
        months.add(1, "February");
        months.add(2, "March");
        months.add(3, "April");
        months.add(4, "May");
        months.add(5, "June");
        months.add(6, "July");
        months.add(7, "August");
        months.add(8, "September");
        months.add(9, "October");
        months.add(10, "November");
        months.add(11, "December");
    }


    private Month currentMonth = LocalDateTime.now().getMonth();
    //private Month nextMonth = LocalDateTime



 /*   public void determineMonth(int monthNum) throws IOException {
        if (monthComboBox.getValue() == "January") {
            monthNum = 1;

        }
    }*/

    /**
     * This method displays the current total of appointments on record (report #3)
     *
     * @param event moust click on get #
     * @throws IOException
     */
    public void getApptNum(ActionEvent event) throws IOException {
        tfApptNum.setText(String.valueOf(MainMenuController.getAppointments().size()));
    }

    /**
     * This method displays all appointments in the tableview
     *
     * @return
     */
    public ObservableList showAppointments() {
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
        apptTable.setItems(list);

        return null;
    }

    /**
     * This method captures the selected month and appointment type to then pass to the lambda below
     *
     * @param event press show button
     * @throws IOException
     */
    public void pressShow(ActionEvent event) throws IOException {
        //showAppointments();
        //apptTable.setItems(showAppointments());

        if (monthComboBox.getSelectionModel().getSelectedItem() == null) {
            Alerts.reportsAlert();
        } else {
            showAppointments();


            //();

            viewApptByMonth2();
            tfApptType.setText(typeComboBox.getValue());
            tfApptType.setEditable(false);
            tfMonth2.setText(monthComboBox.getValue());
            tfMonth2.setEditable(false);
            tfNumAppts.setText(String.valueOf(apptTable.getItems().size()));
            tfNumAppts.setEditable(false);

        }


    }

    /**
     * this method calls the getAppointments method which displays all appointments in the database
     *
     * @param event
     * @throws IOException
     */
    public void showAllAppts(ActionEvent event) throws IOException {
        apptTable.setItems(MainMenuController.getAppointments());

        //tfNumAppts.clear();
    }

    public ObservableList<Appointment> getAppointments() {
        return null;
    }



   /* @FXML
    public void viewApptByMonth() throws IOException {
        //apptTable.setItems(null);
        //Month currentMonth = LocalDateTime.now().getMonth();
        //int monthInt = currentMonth.getValue();
        int currentMonth = LocalDateTime.now().getMonthValue();
        int nextMonth = currentMonth + 1;

        List<Appointment> apptsInFeb = MainMenuController.getAppointments()

                .stream()
                .filter(a -> Objects.equals(a.getStart_time().getMonthValue(), nextMonth))
                .collect(Collectors.toList());

        apptTable.setItems(FXCollections.observableList(apptsInFeb));

    }*/


    /**
     * The Lambda filters all appointments by month based on combobox selection
     *
     * @throws IOException
     */
    @FXML
    public void viewApptByMonth2() throws IOException {
        //apptTable.setItems(null);
        //Month currentMonth = LocalDateTime.now().getMonth();
        //int monthInt = currentMonth.getValue();
        int currentMonth = LocalDateTime.now().getMonthValue();
        int nextMonth = currentMonth + 1;
        int monthNum = 0;
        int currentYear = LocalDateTime.now().getYear();

        String selectedMonth = monthComboBox.getValue();
        if (selectedMonth.equalsIgnoreCase("January")) {
            monthNum = 1;
        }
        if (selectedMonth.equalsIgnoreCase("February")) {
            monthNum = 2;
        }
        if (selectedMonth.equalsIgnoreCase("March")) {
            monthNum = 3;
        }
        if (selectedMonth.equalsIgnoreCase("April")) {
            monthNum = 4;
        }
        if (selectedMonth.equalsIgnoreCase("May")) {
            monthNum = 5;
        }
        if (selectedMonth.equalsIgnoreCase("June")) {
            monthNum = 6;
        }
        if (selectedMonth.equalsIgnoreCase("July")) {
            monthNum = 7;
        }
        if (selectedMonth.equalsIgnoreCase("August")) {
            monthNum = 8;
        }
        if (selectedMonth.equalsIgnoreCase("September")) {
            monthNum = 9;
        }
        if (selectedMonth.equalsIgnoreCase("October")) {
            monthNum = 10;
        }
        if (selectedMonth.equalsIgnoreCase("November")) {
            monthNum = 11;
        }
        if (selectedMonth.equalsIgnoreCase("December")) {
            monthNum = 12;
        }


        int finalMonthNum = monthNum;
        String selectedType = typeComboBox.getValue();
        List<Appointment> apptsInSelectedMonth = MainMenuController.getAppointments()

                .stream()
                .filter(a -> Objects.equals(a.getStart_time().getMonthValue(), finalMonthNum))
                .filter(a -> Objects.equals(a.getType(), selectedType))
                .collect(Collectors.toList());

        apptTable.setItems(FXCollections.observableList(apptsInSelectedMonth));


    }

    /**
     * This method allows the user to view all appointments scheduled per contact via combobox selection
     *
     * @throws IOException
     */
    @FXML
    public void viewApptByContactName() throws IOException {

        int selectedIndex = contactsComboBox.getSelectionModel().getSelectedIndex();
        int contact_id;

        if (selectedIndex == 0) {
            contact_id = 1;
            apptTable.setItems(FXCollections.observableList(DBQuery.getAppointmentsPerContact(contact_id)));
        } else if (selectedIndex == 1) {
            contact_id = 2;
            apptTable.setItems(FXCollections.observableList(DBQuery.getAppointmentsPerContact(contact_id)));
        } else if (selectedIndex == 2) {
            contact_id = 3;
            apptTable.setItems(FXCollections.observableList(DBQuery.getAppointmentsPerContact(contact_id)));
        }


    }

    /**
     * This method sorts appointments by contact name
     *
     * @param event
     * @throws IOException
     */
    @FXML
    public void sortByContactName(ActionEvent event) throws IOException {
        apptTable.setItems(getAppointments());
        viewApptByContactName();
        //contactsComboBox.setItems(null);
        //dcontactsComboBox.getItems().clear();
    }

    /**
     * This method redirects the user to the main menu
     *
     * @param event go back to main menu button
     * @throws IOException
     */
    public void goToMainMenu(ActionEvent event) throws IOException {
        //contactsComboBox.setItems(null);
        contactsComboBox.getItems().clear();
        Parent root = FXMLLoader.load(getClass().getResource("mainMenu.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        //contactsComboBox.setItems(null);
    }


    /**
     * Initializes the ReportsController screen
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //contactsComboBox.setItems(null);
        showAppointments();
        addMonths();
        monthComboBox.setItems(months);
        typeComboBox.setItems(DBQuery.getAppointmentTypesList());

        contactsComboBox.getItems().clear();
        contactsComboBox.setItems(DBQuery.getContactsNameList());


    }

}
