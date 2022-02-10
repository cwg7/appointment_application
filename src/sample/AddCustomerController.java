package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import sample.Division;


import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

import static sample.DBConnection.getConnection;
import static sample.Division.*;


//public class AddCustomerController<appointmentsPerCustomerOL> implements Initializable {

/**
 * This class allows users to add new customers to the database.
 */
public class AddCustomerController implements Initializable {

    @FXML
    private TableView customersTable;
    @FXML
    private TableColumn<Customer, Integer> idCol;
    @FXML
    private TableColumn<Customer, String> nameCol;
    @FXML
    private TableColumn<Customer, String> addressCol;
    @FXML
    private TableColumn<Customer, String> postalCodeCol;
    @FXML
    private TableColumn<Customer, String> phoneCol;
    @FXML
    private TableColumn<Customer, Integer> divisionCol;
    @FXML
    private Label lblName;
    @FXML
    private Label lblAddress;
    @FXML
    private Label lblPostalCode;
    @FXML
    private Label lblPhone;
    @FXML
    private TextField tfName;
    @FXML
    private TextField tfAddress;
    @FXML
    private TextField tfPostalCode;
    @FXML
    private TextField tfPhone;
    @FXML
    private Label lblDivision;
    @FXML
    private TextField tfDivision;
    @FXML
    private Button mainMenuButton;
    @FXML
    private Label lblCountry;
    @FXML
    private Label lblDivision2;
    @FXML
    ComboBox<String> country_box;
    @FXML
    ComboBox<String> division2_box;

    @FXML
    private Button addCustomerButton;
    private Stage stage;
    private Scene scene;

    /**
     * @return Returns array of customer data (id, name, address, postal code, phone, division id)
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
     * This method populates customer data on the tableview
     */
    public void showCustomers() {
        ObservableList<Customer> list = getCustomerList();
        idCol.setCellValueFactory(new PropertyValueFactory<Customer, Integer>("id"));
        nameCol.setCellValueFactory(new PropertyValueFactory<Customer, String>("name"));
        addressCol.setCellValueFactory(new PropertyValueFactory<Customer, String>("address"));
        postalCodeCol.setCellValueFactory(new PropertyValueFactory<Customer, String>("postalCode"));
        phoneCol.setCellValueFactory(new PropertyValueFactory<Customer, String>("phoneNumber"));
        divisionCol.setCellValueFactory(new PropertyValueFactory<Customer, Integer>("division_id"));
        customersTable.setItems(list);
    }

    /**
     * This method checks textfields and combobox selections to make sure the user entered data correctly
     */
    public void verify() {

        if (tfName.getText().isEmpty() || tfAddress.getText().isEmpty() || tfPostalCode.getText().isEmpty() || tfPhone.getText().isEmpty() || tfDivision.getText().isEmpty() || country_box.getItems().isEmpty()
        || division2_box.getItems().isEmpty())
        {
            Alerts.checkFields();
        }
    }


    /**
     * This method allows users to add new customers to the database provided they fill out textfields correctly and
     * make proper combobox selections
     * @param event (mouse button click on add customer)
     * @throws IOException
     * @throws SQLException
     */
    @FXML
    public void addCustomer(ActionEvent event) throws IOException, SQLException {
        //addCustomer();
        // insertCustomer();
        //verify();
        // if (tfName.getText().isEmpty() || tfAddress.getText().isEmpty() || tfPostalCode.getText().isEmpty() || tfPhone.getText().isEmpty() || tfDivision.getText().isEmpty()) {
        if (tfName.getText().isEmpty() || tfAddress.getText().isEmpty() || tfPostalCode.getText().isEmpty() || tfPhone.getText().isEmpty() || country_box.getValue() == null || division2_box.getValue() == null) {
            Alerts.checkFields();
            tfName.clear();
            tfAddress.clear();
            tfPostalCode.clear();
            tfPhone.clear();

        } else {
            preparedInsert();
            showCustomers();
            tfName.clear();
            tfAddress.clear();
            tfPostalCode.clear();
            tfPhone.clear();
            country_box.equals(null);

        }
    }


    /**
     * This method directs the user to menu where they can modify customer data.
     * @param event (mouse click on modify customer button)
     * @throws IOException
     * @throws SQLException
     */
    @FXML
    public void modCustomerButton(ActionEvent event) throws IOException, SQLException {
        Parent root = FXMLLoader.load(getClass().getResource("modCustomer.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * This method adds new customer information to the database and effectively creates a new customer.
     */
    @FXML
    public void preparedInsert() {
        //verify();
        PreparedStatement pstatement;
        String sql = "INSERT into customers(Customer_Name, Address, Postal_Code, Phone, Division_ID) Values(?,?,?,?,?)";
        try {
            pstatement = getConnection().prepareStatement(sql);
            pstatement.setString(1, tfName.getText());
            pstatement.setString(2, tfAddress.getText());
            pstatement.setString(3, tfPostalCode.getText());
            pstatement.setString(4, tfPhone.getText());
            //pstatement.setInt(5, Integer.parseInt(tfDivision.getText()));

            ObservableList<Division> divisionsOL = getDivisionIDList();
            String tempVal = division2_box.getSelectionModel().getSelectedItem();
            int divisionID = 0;
            for (Division division : divisionsOL)

            {
                if (tempVal == null) {
                    Alerts.checkFields();
                }
                if (tempVal.equals(division.getDivision_name())) {
                    divisionID = division.getId();
                }

            }
            pstatement.setInt(5, Integer.parseInt(String.valueOf(divisionID)));
            pstatement.execute();
        } catch (SQLException throwables) {
            //Alerts.checkFields();
            throwables.printStackTrace();
        }
    }

    /**
     *
     * @return Returns an array of division data (division id, division (name), country id)
     */
    public static ObservableList<Division> getDivisionIDList() {
        ObservableList<Division> newDivisionIDList = FXCollections.observableArrayList();
        Connection conn = DBConnection.getConnection();
        String query = "SELECT * FROM first_level_divisions";
        Statement st;
        ResultSet rs;


        try {
            st = conn.createStatement();
            rs = st.executeQuery(query);
            Division division;
            while (rs.next()) {
                division = new Division(rs.getInt("Division_ID"), rs.getString("Division"),
                        rs.getInt("Country_ID"));
                newDivisionIDList.add(division);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return newDivisionIDList;
    }



    /*public void preparedUpdate() {
        PreparedStatement pstatement = null;
        String sql = "UPDATE customers SET Customer_Name = ?, Address = ?, Postal_Code = ?, Phone = ?, Division_ID WHERE id = ?";
        try {
            pstatement.setString(1, tfName.getText());
            pstatement.setString(2, tfAddress.getText());
            pstatement.setString(3, tfPostalCode.getText());
            pstatement.setString(4, tfPhone.getText());
            pstatement.setInt(5, Integer.parseInt(tfDivision.getText()));
            pstatement.execute();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
*/
   /* private void executeQuery(String query) {
        Connection conn = getConnection();
        Statement st;
        try {
            st = conn.createStatement();
            st.executeUpdate(query);

        } catch (Exception e) {

        }
    }*/

    /**
     * This method redirects the user back to the main menu
     * @param event (mouse click on back to main menu button)
     * @throws IOException
     */
    public void backToMain(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("mainMenu.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * This method sets corresponding division data based on country selection
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
     * This method sets country data based on division selection (the inverse of the method above)
     * @param event
     * @throws IOException
     */
    @FXML
    public void onDivisionSelect(ActionEvent event) throws IOException {

        ObservableList<Division> divisionsOL = AddCustomerController.getDivisionIDList();
        String tempVal = country_box.getValue();
        int divisionID = 0;

        for (Division division : divisionsOL) {

            if (tempVal.equals(division.getDivision_name())) {
                tempVal = division.getDivision_name();
                divisionID = division.getId();
                tfDivision.setText(String.valueOf(divisionID));
            }
        }

        switch(tempVal){
            case "U.S": division2_box.setItems(DBQuery.getUsDivisionList());

            break;
            case "UK": division2_box.setItems(DBQuery.getUKDivisionList());
            break;
            case "Canada": division2_box.setItems(DBQuery.getCanadaDivisionList());
            break;

            default: division2_box.setItems(DBQuery.getAllDivisionList());
            String divisionName = division2_box.getValue();
        }






    }

    /**
     *
     * @return Returns selected country based on combo box selection
     */
    @FXML
    public String getCountry(){
        String selectedCountry = country_box.getValue();
        return selectedCountry;
    }

    /**
     * @return returns division based on division combobox selection
     */
    @FXML
    public String getDivision(){
        String selectedDivision = division2_box.getValue();
        return selectedDivision;
    }

    /**
     * This method initializes the Add Customer menu. It sets the comboboxes (country & division) based on
     * a specified DB query.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        showCustomers();
        country_box.setItems(DBQuery.getCountryList());
        division2_box.setItems(DBQuery.getAllDivisionList());
        division2_box.setDisable(true);
    }
}

