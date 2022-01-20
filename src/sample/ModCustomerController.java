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
import sample.Countries;
import sample.DBQuery;
import org.w3c.dom.Text;

import static java.sql.Timestamp.valueOf;
import static sample.DBConnection.getConnection;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

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

    @FXML
    public void handleModClick(ActionEvent event) throws IOException, SQLException {
        //String countrySelection = country_box.getValue().toString();
        //String divisionSelection = division2_box.getValue().toString();
        Customer selectedCustomer;
        selectedCustomer = (Customer) modCustomersTable.getSelectionModel().getSelectedItem();
        //selectedCustomer.get

        if (selectedCustomer == null){
            Alerts.modHandler();
        }
        else {
        int selectedCustomerID = selectedCustomer.getId();
        int divisionID = selectedCustomer.getDivision_id();

            //String countryName = selectedCustomer.getCountryName();
        Countries countryName;



            //String countryName = selectedCustomer.getCountryName();






        // = (Customer) modCustomersTable.getSelectionModel().getSelectedItems();


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
            //country_box.setValue(String.valueOf(DBQuery.getCountryIdByDivisionId(divisionID)));
           // tfCountry2.setText(String.valueOf(DBQuery.getCountryIdByDivisionId(divisionID)));

           // country_box.setValue(DBQuery.getCountryNameByCountryID(divisionID));
            

            //country_box.setItems(DBQuery.getDivisionName(divisionID));

            //country_box.setItems(countryName);
            //country_box.setValue(countryName);
            //country_box.setValue(DBQuery.getDivisionName());

            division2_box.setDisable(false);
            division2_box.setValue(DBQuery.getDivisionName(divisionID).getDivision_name());

            int countryID = DBQuery.getCountryIdByDivisionId(divisionID);

            //tfCountry2.setText(String.valueOf(DBQuery.getCountryIdByDivisionId(divisionID)));

            //country_box.setValue(DBQuery.getCountryNameByCountryID(divisionID));
            country_box.setValue(DBQuery.getCountryNameByCountryID(countryID));

            //tfCountry2.setText("YO");

           /* if (tfCountry2.getText() == "1") {
                country_box.setValue("U.S");
            }
            if (tfCountry2.getText() == "2") {
                country_box.setValue("UK");
            }
                if (tfCountry2.getText() == "3") {
                    country_box.setValue("Canada");
                }
            */




            //country_box.setValue(DBQuery.getCountryNameByCountryID(divisionID));

            //division2_box.setValue(String.valueOf(divisionID));
            //division2_box.setValue(String.valueOf(divisionID));

            //country_box.setItems(selectedCustomer.getCountryName());
            //country_box.setItems(divisionID.get);

            // tfDivision.setInt(Integer.parseInt(tfDivision.getText()));
            

                    //setText(Integer.parseInt(String.valueOf(selectedCustomer.getDivision_id())));
            modCustomersTable.setDisable(true);
            saveButton.setDisable(false);
            modButton.setDisable(true);
        }

    }

    @FXML
    public void handleDelete(ActionEvent event) throws IOException {
        Customer selectedCustomer = (Customer) modCustomersTable.getSelectionModel().getSelectedItem();
        int customerID = selectedCustomer.getId();






        if (selectedCustomer == null) {
            Alerts.delHandler();

        }
        else {
            preparedDelete();
            showCustomers();
            Alerts.deleteSuccessful();
        }

    }
    public void preparedDelete() {
        Customer selectedCustomer = (Customer) modCustomersTable.getSelectionModel().getSelectedItem();
        int selectedCustomerID = selectedCustomer.getId();

        PreparedStatement pstatement;
        String sql = "DELETE FROM customers WHERE Customer_ID = ?";
        try {
            pstatement = DBConnection.getConnection().prepareStatement(sql);
            pstatement.setInt(1,selectedCustomerID);
            pstatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


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
            for (Division division : divisionsOL)

            {
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




      /*  if (country_box.getValue().equals("U.S")) {
            // division2_box.setItems(null);
            division2_box.setItems(DBQuery.getUsDivisionList());

        }

        else if (country_box.getValue().equals("UK")) {
            division2_box.setItems(DBQuery.getUKDivisionList());
            //break;
        } else if (country_box.getValue().equals("Canada")) {
            division2_box.setItems(DBQuery.getCanadaDivisionList());

        }
*/

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

            if (tempVal != null && tempVal.equals(division.getDivision_name())) {
                tempVal = division.getDivision_name();
                divisionID = division.getId();
                tfDivision.setText(String.valueOf(divisionID));
            }
        }
        if (tempVal == null) {
            //Alerts.checkFields();

        }
        else{
        switch(tempVal) {
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

 /*   public void preparedCountrySelect() {
        Customer selectedCustomer = (Customer) modCustomersTable.getSelectionModel().getSelectedItem();
        //int selectedA = selectedCustomer.getAppointment_id();
        int divisionID = selectedCustomer.getDivision_id();

        PreparedStatement pstatement;
        String sql = "SELECT Division FROM first_level_divisions WHERE Division_ID = ?";
        try {
            // assert pstatement != null;
            pstatement = DBConnection.getConnection().prepareStatement(sql);
           // pstatement.setString(1, tfTitle.getText());
            pstatement.setString(1, country_box.setItems(sql));




            pstatement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


    }
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
    @FXML
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
        




       /* idCol.setCellValueFactory(new PropertyValueFactory<Customer, Integer>("id"));*/
       /* nameCol.setCellValueFactory(new PropertyValueFactory<Customer, String>("name"));*/
       /* addressCol.setCellValueFactory(new PropertyValueFactory<Customer, String>("address"));*/
       /* postalCol.setCellValueFactory(new PropertyValueFactory<Customer, String>("postalCode"));*/
       /* phoneCol.setCellValueFactory(new PropertyValueFactory<Customer, String>("phoneNumber"));*/
       /* divisionCol.setCellValueFactory(new PropertyValueFactory<Customer, Integer>("division_id"));*/
       /* modCustomersTable.setItems(AddCustomerController.getCustomerList());*/
    }
}
