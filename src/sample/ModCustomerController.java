package sample;

import com.mysql.cj.protocol.Resultset;
import javafx.beans.Observable;
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
import sample.Countries;
import sample.DBQuery;
import org.w3c.dom.Text;

import static java.sql.Timestamp.valueOf;
import static sample.DBConnection.getConnection;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

/**
 * This is the ModCustomerController class
 */
public class ModCustomerController implements Initializable {

    @FXML
    private TableView modCustomersTable;
    @FXML
    private TableColumn<Customer, Integer> idCol;
    @FXML
    private TableColumn<Customer, String> nameCol;
    @FXML
    private TableColumn<Customer, String> addressCol;
    @FXML
    private TableColumn<Customer, String> postalCol;
    @FXML
    private TableColumn<Customer, String> phoneCol;
    @FXML
    private TableColumn<Customer, Integer> divisionCol;
    @FXML
    private Label lblName;
    @FXML
    private Label lblAddress;
    @FXML
    private Label lblPostal;
    @FXML
    private Label lblPhone;
    @FXML
    private Label lblDivision;
    @FXML
    private TextField tfName;
    @FXML
    private TextField tfAddress;
    @FXML
    private TextField tfPostal;
    @FXML
    private TextField tfPhone;
    @FXML
    private TextField tfDivision;
    @FXML
    private Button saveButton;
    @FXML
    private Button modButton;
    @FXML
    private Button mainMenuButton;
    @FXML
    private Button deleteButton;
    @FXML
    ComboBox<String> country_box;
    @FXML
    ComboBox<String> division2_box;
    @FXML
    private Label lblCountry;
    @FXML
    private Label lblDivision2;

    private Stage stage;
    private Scene scene;

    @FXML
    private TextField tfCountry2;

    public ObservableList<String> allCountries = DBQuery.getAllCountries();

    /**
     * This method returns a list of all customer data
     *
     * @return returns all customer data from customers
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

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return customerList;
    }

    /**
     * This method captures the user-selected customer via the tableview and throws an exception if no such
     * selection is made
     *
     * @param event mouse click on select customer
     * @throws IOException
     * @throws SQLException
     */
    @FXML
    public void handleModClick(ActionEvent event) throws IOException, SQLException {
        //String countrySelection = country_box.getValue().toString();
        //String divisionSelection = division2_box.getValue().toString();
        Customer selectedCustomer;
        selectedCustomer = (Customer) modCustomersTable.getSelectionModel().getSelectedItem();
        //selectedCustomer.get

        if (selectedCustomer == null) {
            Alerts.modHandler();
        } else {
            int selectedCustomerID = selectedCustomer.getId();
            int divisionID = selectedCustomer.getDivision_id();

            //String countryName = selectedCustomer.getCountryName();
            Countries countryName;


            tfName.setDisable(false);
            tfName.setText(selectedCustomer.getName());
            tfAddress.setDisable(false);
            tfAddress.setText(selectedCustomer.getAddress());
            tfPostal.setDisable(false);
            tfPostal.setText(selectedCustomer.getPostalCode());
            tfPhone.setDisable(false);
            tfPhone.setText(selectedCustomer.getPhoneNumber());
            tfDivision.setDisable(true);
            tfDivision.setText(String.valueOf(divisionID));
            //tfCountry2.setText(countryName);

            country_box.setDisable(false);

            division2_box.setDisable(false);
            division2_box.setValue(DBQuery.getDivisionName(divisionID).getDivision_name());

            int countryID = DBQuery.getCountryIdByDivisionId(divisionID);


            country_box.setValue(DBQuery.getCountryNameByCountryID(countryID));


            modCustomersTable.setDisable(true);
            saveButton.setDisable(false);
            modButton.setDisable(true);
        }

    }

    /**
     * This method deletes a user-selected customer from the database
     *
     * @param event mouse click on delete customer button
     * @throws IOException
     */
    @FXML
    public void handleDelete(ActionEvent event) throws IOException {
        Customer selectedCustomer = (Customer) modCustomersTable.getSelectionModel().getSelectedItem();
        int customerID = selectedCustomer.getId();

        if (DBQuery.getAppointmentsPerCustomer(customerID).isEmpty()) {


            if (selectedCustomer == null) {
                Alerts.delHandler();

            } else {
                preparedDelete();
                showCustomers();
                Alerts.deleteSuccessful();
            }
        } else {
            Alerts.delHandler3();
        }

    }

    /**
     * This method handles the delete customer function via a prepared statement
     */
    public void preparedDelete() {
        Customer selectedCustomer = (Customer) modCustomersTable.getSelectionModel().getSelectedItem();
        int selectedCustomerID = selectedCustomer.getId();

        PreparedStatement pstatement;
        String sql = "DELETE FROM customers WHERE Customer_ID = ?";
        try {
            pstatement = DBConnection.getConnection().prepareStatement(sql);
            pstatement.setInt(1, selectedCustomerID);
            pstatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    /**
     * This method saves the modified customer data and passes it to the prepared update statement
     *
     * @param event mouse click on save button
     * @throws IOException
     */
    @FXML
    public void saveButtonClick(ActionEvent event) throws IOException {

        preparedUpdate();
        modCustomersTable.setDisable(false);
        showCustomers();
        saveButton.setDisable(true);
        tfName.clear();
        tfName.setDisable(true);
        tfAddress.clear();
        tfAddress.setDisable(true);
        tfPostal.clear();
        tfPostal.setDisable(true);
        tfPhone.clear();
        tfPhone.setDisable(true);
        tfDivision.clear();
        tfDivision.setDisable(true);
        modButton.setDisable(false);

    }


    /**
     * This method updates modified customer data via a prepared statement
     */
    public void preparedUpdate() {
        Customer selectedCustomer = (Customer) modCustomersTable.getSelectionModel().getSelectedItem();
        int selectedCustomerID = selectedCustomer.getId();

        PreparedStatement pstatement;
        String sql = "UPDATE customers SET Customer_Name = ?, Address = ?, Postal_Code = ?, Phone = ?, Division_ID = ? WHERE Customer_ID = ?";
        try {
            // assert pstatement != null;
            pstatement = DBConnection.getConnection().prepareStatement(sql);
            pstatement.setString(1, tfName.getText());
            pstatement.setString(2, tfAddress.getText());
            pstatement.setString(3, tfPostal.getText());
            pstatement.setString(4, tfPhone.getText());
            //pstatement.setInt(5, Integer.parseInt(tfDivision.getText()));
            //pstatement.setInt(6, selectedCustomer.getId());
            ObservableList<Division> divisionsOL = AddCustomerController.getDivisionIDList();
            String tempVal = division2_box.getSelectionModel().getSelectedItem();
            int divisionID = 0;
            for (Division division : divisionsOL) {
                if (tempVal.equals(division.getDivision_name())) {
                    divisionID = division.getId();
                }


            }
            pstatement.setInt(5, Integer.parseInt(String.valueOf(divisionID)));
            pstatement.setInt(6, selectedCustomer.getId());


            pstatement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


    }

    /**
     * This method sets the division combo box in accordance with country selection
     *
     * @param event
     * @throws IOException
     */
    @FXML
    public void onCountrySelect(ActionEvent event) throws IOException {
        division2_box.setDisable(false);
        //division2_box.getItems().clear();

        String tempVal = country_box.getValue();

        if (tempVal != null)

            switch (tempVal) {
                case "U.S":
                    division2_box.setItems(DBQuery.getUsDivisionList());

                    break;
                case "UK":
                    division2_box.setItems(DBQuery.getUKDivisionList());
                    break;
                case "Canada":
                    division2_box.setItems(DBQuery.getCanadaDivisionList());
                    break;

                default:
                    division2_box.setItems(DBQuery.getAllDivisionList());


            }
    }

    /**
     * This method gets a division list based on division selection via DBquery
     *
     * @param event
     * @throws IOException
     */
    @FXML
    public void onDivisionSelect(ActionEvent event) throws IOException {

        ObservableList<Division> divisionsOL = AddCustomerController.getDivisionIDList();
        //String tempVal = division2_box.getSelectionModel().getSelectedItem();

        //Thread.sleep(1000);

        String tempVal = country_box.getValue();

        int divisionID = 0;


        for (Division division : divisionsOL) {

            if (tempVal != null && tempVal.equals(division.getDivision_name())) {
                tempVal = division.getDivision_name();
                divisionID = division.getId();
                tfDivision.setText(String.valueOf(divisionID));
            }
        }
        if (tempVal == null) {
            //Alerts.checkFields();

        } else {
            switch (tempVal) {
                case "U.S":
                    division2_box.setItems(DBQuery.getUsDivisionList());

                    break;
                case "UK":
                    division2_box.setItems(DBQuery.getUKDivisionList());
                    break;
                case "Canada":
                    division2_box.setItems(DBQuery.getCanadaDivisionList());
                    break;

                default:
                    division2_box.setItems(DBQuery.getAllDivisionList());

                    String divisionName = division2_box.getValue();
            }


        }


    }


    /**
     * This method displays all customers in the customer tableview
     */
    public void showCustomers() {
        ObservableList<Customer> list = getCustomerList();
        idCol.setCellValueFactory(new PropertyValueFactory<Customer, Integer>("id"));
        nameCol.setCellValueFactory(new PropertyValueFactory<Customer, String>("name"));
        addressCol.setCellValueFactory(new PropertyValueFactory<Customer, String>("address"));
        postalCol.setCellValueFactory(new PropertyValueFactory<Customer, String>("postalCode"));
        phoneCol.setCellValueFactory(new PropertyValueFactory<Customer, String>("phoneNumber"));
        divisionCol.setCellValueFactory(new PropertyValueFactory<Customer, Integer>("division_id"));
        modCustomersTable.setItems(list);
    }

    /**
     * This method redirects the user to the Main menu
     *
     * @param event back to main menu button
     * @throws IOException
     */
    @FXML
    public void backToMain(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("mainMenu.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }


    /**
     * Initializes the ModCustomerController screen
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        showCustomers();
        //saveButton.setDisable(true);
        tfName.setDisable(true);
        tfAddress.setDisable(true);
        tfPostal.setDisable(true);
        tfPhone.setDisable(true);
        tfDivision.setDisable(true);
        country_box.setDisable(true);
        division2_box.setDisable(true);
        country_box.setItems(DBQuery.getCountryList());
        division2_box.setItems(DBQuery.getAllDivisionList());

    }
}
