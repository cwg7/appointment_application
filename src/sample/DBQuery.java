package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.AddAppointmentController;

import java.sql.*;

public class DBQuery {
    private static PreparedStatement statement;

    Connection conn = DBConnection.getConnection();

    /** This method sets the Prepared Statement object
     * @param connection Database Connection
     * @param sqlStatement SQL Statement string
     * @throws SQLException Catches SQLException and prints stacktrace.
     */
    public static void setPreparedStatement(Connection connection, String sqlStatement) throws SQLException {
        statement = connection.prepareStatement(sqlStatement);
    }

    /** This method returns the Prepared Statement object
     * @return Prepared Statement
     */
    public static PreparedStatement getPreparedStatement() {
        return statement;
    }

    public static ObservableList<Integer> getContactsIDList() {
        return contactsIDList;
    }

    /* public static ObservableList<Contacts> GetContactList() throws SQLException {
            List<List<String>> contact_data = GetResultsFromQuery("SELECT * FROM contacts");
            List<Contacts> contact_list = new ArrayList<>();

            for (List<String> val : contact_data) {
                contact_list.add(new Contact(val.get(0), val.get(1), val.get(2)));
            }

            return FXCollections.observableList(contact_list);
        }*/
   /*public static ObservableList<Contacts> getContactsList() {
       ObservableList<Contacts> contactsList = FXCollections.observableArrayList();
       Connection conn = DBConnection.getConnection();
       String query = "SELECT * FROM contacts";
       Statement st;
       ResultSet rs;
       try {
           st = conn.createStatement();
           rs = st.executeQuery(query);
           Contacts contact;
           while (rs.next()) {
            contact = new Contacts(rs.getInt("Contact_ID"),
                    rs.getString("Contact_Name"),
                    rs.getString("Email"));
                    contactsList.add(contact);
           }
       } catch (Exception ex) {
           ex.printStackTrace();
       }
       return contactsList;
   }
*/
   //ObservableList<String> contactsNameList = FXCollections.observableArrayList();
   public static ObservableList<Integer> contactsIDList = FXCollections.observableArrayList();

    public static ObservableList<Integer> getContactsList() {
        //ObservableList<Integer> contactsNameList = FXCollections.observableArrayList();

        Connection conn = DBConnection.getConnection();
        String query = "SELECT * FROM contacts";
        Statement st;
        ResultSet rs;
        try {
            st = conn.createStatement();
            rs = st.executeQuery(query);

            while (rs.next()) {
                contactsIDList.add(rs.getInt("Contact_ID"));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return contactsIDList;
    }

    public static ObservableList<String> contactsNameList = FXCollections.observableArrayList();

    public static ObservableList<String> getContactsNameList() {
        //ObservableList<Integer> contactsNameList = FXCollections.observableArrayList();

        Connection conn = DBConnection.getConnection();
        String query = "SELECT * FROM contacts";
        Statement st;
        ResultSet rs;
        try {
            st = conn.createStatement();
            rs = st.executeQuery(query);

            while (rs.next()) {
                contactsNameList.add(rs.getString("Contact_Name"));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return contactsNameList;
    }

    public static ObservableList<Integer> userIDList = FXCollections.observableArrayList();

    public static ObservableList<Integer> getUserIDList() {
        //ObservableList<Integer> contactsNameList = FXCollections.observableArrayList();

        Connection conn = DBConnection.getConnection();
        String query = "SELECT * FROM users";
        Statement st;
        ResultSet rs;
        try {
            st = conn.createStatement();
            rs = st.executeQuery(query);

            while (rs.next()) {
                userIDList.add(rs.getInt("User_ID"));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return userIDList;
    }





}
