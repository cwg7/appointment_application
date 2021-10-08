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
import java.util.ResourceBundle;

import static sample.DBConnection.getConnection;


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
    //@FXML
    //private Button addButton;

    //Connection conn;
    @FXML
    private Button addCustomerButton;
    private Stage stage;
    private Scene scene;

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
    /*public void addAnotherCustomer(Customer customer){
        getCustomerList().add(customer);

    }*/

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

    public void verify() {

        if (tfName.getText().isEmpty() || tfAddress.getText().isEmpty() || tfPostalCode.getText().isEmpty() || tfPhone.getText().isEmpty() || tfDivision.getText().isEmpty()) {
            Alerts.checkFields();
        }


    }

   /* @FXML
    public void insertCustomer() throws IOException, SQLException {

        String query =

                "INSERT INTO customers " +
                "VALUES(" +
                "'" + tfName.getText() + "'," +
                "'" + tfAddress.getText() + "'," +
                "'" + tfPostalCode.getText() + "'," +
                "'" + tfPhone.getText() + "'," +
                "'" + tfDivision.getText()  +
                ")" + "INNER JOIN first_level_divisions ON customers.Division_ID = first_level_divisions.Division_ID";
        executeQuery(query);
        showCustomers();}
        */

    @FXML
    public void addCustomer(ActionEvent event) throws IOException, SQLException {
        //addCustomer();
        // insertCustomer();
        //verify();
        if (tfName.getText().isEmpty() || tfAddress.getText().isEmpty() || tfPostalCode.getText().isEmpty() || tfPhone.getText().isEmpty() || tfDivision.getText().isEmpty()) {
            Alerts.checkFields();
            tfName.clear();
            tfAddress.clear();
            tfPostalCode.clear();
            tfPhone.clear();
            tfDivision.clear();
        } else {
            preparedInsert();
            showCustomers();
            tfName.clear();
            tfAddress.clear();
            tfPostalCode.clear();
            tfPhone.clear();
            tfDivision.clear();
        }


    }

 /*   @FXML
    public void updateCustomer(ActionEvent event) throws IOException, SQLException {
        if (tfName.getText().isEmpty() || tfAddress.getText().isEmpty() || tfPostalCode.getText().isEmpty() || tfPhone.getText().isEmpty() || tfDivision.getText().isEmpty()) {
            Alerts.checkFields();
        } else {
            preparedUpdate();
            showCustomers();
        }

    }*/

    @FXML
    public void modCustomerButton(ActionEvent event) throws IOException, SQLException {
        Parent root = FXMLLoader.load(getClass().getResource("modCustomer.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    public void preparedInsert() {
        //verify();
        PreparedStatement pstatement;
        String sql = "INSERT into customers(Customer_Name, Address, Postal_Code, Phone, Division_ID) Values(?,?,?,?,?)";
        try {
            pstatement = DBConnection.getConnection().prepareStatement(sql);
            pstatement.setString(1, tfName.getText());
            pstatement.setString(2, tfAddress.getText());
            pstatement.setString(3, tfPostalCode.getText());
            pstatement.setString(4, tfPhone.getText());
            pstatement.setInt(5, Integer.parseInt(tfDivision.getText()));
            //pstatement.setString(5, tfDivision.getText());
            pstatement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void preparedUpdate() {
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

    private void executeQuery(String query) {
        Connection conn = getConnection();
        Statement st;
        try {
            st = conn.createStatement();
            st.executeUpdate(query);

        } catch (Exception e) {

        }
    }

    public void backToMain(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("mainMenu.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();


    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        showCustomers();
    }
}



    /*public void addCustomer(ActionEvent event) {

}
*/