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


    @FXML
    public void addCustomer(ActionEvent event) throws IOException, SQLException {
        //addCustomer();
        // insertCustomer();
        //verify();
        // if (tfName.getText().isEmpty() || tfAddress.getText().isEmpty() || tfPostalCode.getText().isEmpty() || tfPhone.getText().isEmpty() || tfDivision.getText().isEmpty()) {
        if (tfName.getText().isEmpty() || tfAddress.getText().isEmpty() || tfPostalCode.getText().isEmpty() || tfPhone.getText().isEmpty()) {
            Alerts.checkFields();
            tfName.clear();
            tfAddress.clear();
            tfPostalCode.clear();
            tfPhone.clear();
            //tfDivision.clear();
        } else {
            preparedInsert();
            showCustomers();
            tfName.clear();
            tfAddress.clear();
            tfPostalCode.clear();
            tfPhone.clear();
            country_box.equals(null);
            //tfDivision.clear();
        }


    }



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
                if (tempVal.equals(division.getDivision_name())) {
                    divisionID = division.getId();
                }


            }
            pstatement.setInt(5, Integer.parseInt(String.valueOf(divisionID)));


            pstatement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static ObservableList<Division> getDivisionIDList() {
        ObservableList<Division> newDivisionIDList = FXCollections.observableArrayList();
        Connection conn = DBConnection.getConnection();
        String query = "SELECT * FROM first_level_divisions";
        Statement st;
        ResultSet rs;

        // rs.getInt("Customer_ID"),

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
    @FXML
    public void onCountrySelect(ActionEvent event) throws IOException {
        division2_box.setDisable(false);
        //division2_box.getItems().clear();

        String tempVal = country_box.getValue();

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

                //String divisionName = division2_box.getValue();




 /*           if (country_box.getValue().equals("U.S")) {
                // division2_box.setItems(null);
                division2_box.setItems(DBQuery.getUsDivisionList());

            }

            else if (country_box.getValue().equals("UK")) {
                division2_box.setItems(DBQuery.getUKDivisionList());
               //break;
            } else if (country_box.getValue().equals("Canada")) {
                division2_box.setItems(DBQuery.getCanadaDivisionList());

            }*/


                //}

        }
    }

    @FXML
    public void onDivisionSelect(ActionEvent event) throws IOException {

        ObservableList<Division> divisionsOL = AddCustomerController.getDivisionIDList();
        //String tempVal = division2_box.getSelectionModel().getSelectedItem();

        //Thread.sleep(1000);
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












            /*tempVal = division.getDivision_name();
            divisionID = division.getId();
            tfDivision.setText(String.valueOf(divisionID));*/



                //tfDivision.setText(String.valueOf(divisionID));

           /* if (division2_box.getValue() != null) {
                //divisionID = division.getId();
                tfDivision.setText(String.valueOf(divisionID));
            }*/



        }






    }
    @FXML
    public String getCountry(){
        String selectedCountry = country_box.getValue();
        return selectedCountry;
    }
    @FXML
    public String getDivision(){
        String selectedDivision = division2_box.getValue();
        return selectedDivision;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        showCustomers();


        //DBQuery.getCountryList();
        country_box.setItems(DBQuery.getCountryList());
        division2_box.setItems(DBQuery.getAllDivisionList());
        division2_box.setDisable(true);

        //tfDivision.setDisable(true);
        //division2_box.setItems(null);

    }
}



    /*public void addCustomer(ActionEvent event) {

}
*/