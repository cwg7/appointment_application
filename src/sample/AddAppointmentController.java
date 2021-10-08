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
    private TextField tfStart;
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



    private Stage stage;
    private Scene scene;

    /*ObservableList<Contacts> contactsObservableList = DBQuery.getAllContacts();
    ObservableList<String> allContactsNames = FXCollections.observableArrayList();
    contactsObservableList.forEach(Contacts -> allContactsNames.add(Contacts.getContactName()));*/

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
   /* public int populateContacts() throws SQLException {
        ObservableList<Contacts> contactsList = FXCollections.observableArrayList();
        Connection conn = DBConnection.getConnection();
        String query = "SELECT Contact_ID FROM contacts";
        Statement st = null;
        ResultSet rs;

        //PreparedStatement stm = connection.prepareStatement(query);

        rs = st.executeQuery(query);

        while (rs.next()) {
            int idList = rs.getInt("Contact_ID");
            // add group names to the array list

        }
        contactsList.add(idList)
        return contactsList;

        rs.close();
    }*/

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
        Customer selectedCustomer = (Customer) customerTable.getSelectionModel().getSelectedItem();
        int selectedCustomerID = selectedCustomer.getId();
        String selectedCustomerName = selectedCustomer.getName();
        tfCustomerID.setText(String.valueOf(selectedCustomerID));
        tfCustomerName.setText(selectedCustomerName);
        customerTable.setDisable(true);

    }
    public void preparedInsert() {
        //verify();
        PreparedStatement pstatement;
        String sql = "INSERT into appointments(Appointment_ID, Title, Description, Location, Type, Start, End, Customer_ID, User_ID, Contact_ID) Values(?,?,?,?,?,?,?,?,?,?)";
        try {
            pstatement = DBConnection.getConnection().prepareStatement(sql);
            pstatement.setInt(1, Integer.parseInt(tfCustomerID.getText()));
            pstatement.setString(2, tfTitle.getText());
            pstatement.setString(3, tfDescription.getText());
            pstatement.setString(4, tfLocation.getText());
            pstatement.setString(5, tfType.getText());
            pstatement.setTimestamp(6, valueOf(tfStart.getText()));
            pstatement.setTimestamp(7, valueOf(tfEnd.getText()));
            pstatement.setInt(8, Integer.parseInt(tfCustomerID.getText()));
            //pstatement.setInt(9, Integer.parseInt(tfUserID.getText()));
            pstatement.setInt(9, Integer.parseInt(String.valueOf(userID_box.getSelectionModel().getSelectedItem())));
            pstatement.setInt(10, Integer.parseInt(String.valueOf(contact_box.getSelectionModel().getSelectedItem())));

            // HERE IS WHERE I NEED TO GET CONTACT NAME SELECTION, AND THEN GET THE CONTACT_ID OF SELECTED CONTACT NAME,
            // THEN FEED CONTACT ID TO THE APPOINTMENT CONSTRUCTOR

            //THIS IS A NEW COMMENT




           /* Contacts contactName = contactName_box.getSelectionModel().getSelectedItem();
            int contactID = contactName.ge*/


           // pstatement.setInt(10, Integer.parseInt(String.valueOf(contactName_box.getSelectionModel().getSelectedItem().)) )
            //pstatement.setInt(10, Integer.parseInt(tfContactID.getText()));

            //int contactID = contact_box.getSelectionModel().getSelectedItem().getContact_id();
            //String contactName = contact_box.getSelectionModel().getSelectedItem().getContact_name();
            //pstatement.setString(10, contactName);

            //pstatement.setString(5, tfDivision.getText());
            pstatement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    public void addAppointmentButtonClick(ActionEvent event) throws IOException {
        preparedInsert();
        Parent root = FXMLLoader.load(getClass().getResource("mainMenu.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


    /*ArrayList<Integer> contactIDs = new ArrayList<Integer>();
    Connection conn = DBConnection.getConnection();
    //String query = "SELECT * FROM customers";
    Statement st;
    //ResultSet rs;
    String query = "SELECT Contact_ID FROM contacts";
    PreparedStatement stm = DBConnection.prepareStatement(query);

    ResultSet rs = stm.get().executeQuery(query);

while (rs.next()) {
        String groupName = rs.getString("group_name");
        // add group names to the array list
        groupNames.add(groupName)
    }

rs.close();

*/

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        DBQuery.userIDList.clear();
        DBQuery.getContactsList().clear();
        DBQuery.contactsNameList.clear();

        showCustomers();
        showContacts();
        contactsTable.setDisable(true);
        tfCustomerID.setDisable(true);
        tfCustomerName.setDisable(true);


        DBQuery.getContactsList();
        contact_box.setItems(DBQuery.getContactsIDList());

        userID_box.setItems(DBQuery.getUserIDList());
        //DBQuery.getContactsNameList();
        contactName_box.setItems(DBQuery.getContactsNameList());


    }

}
