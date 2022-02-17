package sample;

import com.mysql.cj.protocol.Resultset;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;

import java.sql.*;

import static java.sql.Timestamp.valueOf;


/**
 * This is the DBQuery class
 */
public class DBQuery {
    private static PreparedStatement statement;

    Connection conn = DBConnection.getConnection();

    /**
     * This method sets the Prepared Statement object
     *
     * @param connection   Database Connection
     * @param sqlStatement SQL Statement string
     * @throws SQLException Catches SQLException and prints stacktrace.
     */
    public static void setPreparedStatement(Connection connection, String sqlStatement) throws SQLException {
        statement = connection.prepareStatement(sqlStatement);
    }

    /**
     * This method returns the Prepared Statement object
     *
     * @return Prepared Statement
     */
    public static PreparedStatement getPreparedStatement() {
        return statement;
    }

    /**
     * This method returns list of contact IDs
     *
     * @return returns contactIDList
     */
    public static ObservableList<Integer> getContactsIDList() {
        return contactsIDList;
    }


    public static ObservableList<Integer> contactsIDList = FXCollections.observableArrayList();

    /**
     * This method returns a list of contact Ids
     *
     * @return returns contactIDList
     */
    public static ObservableList<Integer> getContactsList() {

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

    /**
     * This method returns a list of contact names
     *
     * @return returns contactNameList
     */
    public static ObservableList<String> getContactsNameList() {

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

    /**
     * This method returns a list of user ids
     *
     * @return returns userIDList
     */
    public static ObservableList<Integer> getUserIDList() {

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

    public static ObservableList<String> typesList = FXCollections.observableArrayList();

    /**
     * This method returns a list of distinct appointment types
     *
     * @return returns distinct appointment types from appointments in the database
     */
    public static ObservableList<String> getAppointmentTypesList() {

        countryList.clear();

        Connection conn = DBConnection.getConnection();
        String query = "SELECT distinct type FROM appointments";
        Statement st;
        ResultSet rs;
        try {
            st = conn.createStatement();
            rs = st.executeQuery(query);

            while (rs.next()) {
                typesList.add(rs.getString("Type"));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return typesList;
    }


    public static ObservableList<String> countryList = FXCollections.observableArrayList();

    /**
     * This method returns a list of country names
     *
     * @return returns list of country names
     */
    public static ObservableList<String> getCountryList() {

        countryList.clear();

        Connection conn = DBConnection.getConnection();
        String query = "SELECT * FROM countries";
        Statement st;
        ResultSet rs;
        try {
            st = conn.createStatement();
            rs = st.executeQuery(query);

            while (rs.next()) {
                countryList.add(rs.getString("Country"));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return countryList;
    }

    public static ObservableList<String> usDivisionList = FXCollections.observableArrayList();

    /**
     * This method returns a list of division names where country id = 1
     *
     * @return returns division name where country id = 1
     */
    public static ObservableList<String> getUsDivisionList() {

        Connection conn = DBConnection.getConnection();
        String query = "SELECT Division FROM first_level_divisions WHERE Country_ID = 1";
        Statement st;
        ResultSet rs;
        try {
            st = conn.createStatement();
            rs = st.executeQuery(query);

            while (rs.next()) {
                usDivisionList.add(rs.getString("Division"));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return usDivisionList;
    }

    public static ObservableList<String> uKDivisionList = FXCollections.observableArrayList();

    /**
     * This method returns a list of uk divisions from the database where country id = 2
     *
     * @return returns ukDivisionList where country id = 2
     */
    public static ObservableList<String> getUKDivisionList() {

        Connection conn = DBConnection.getConnection();
        String query = "SELECT Division FROM first_level_divisions WHERE Country_ID = 2";
        Statement st;
        ResultSet rs;
        try {
            st = conn.createStatement();
            rs = st.executeQuery(query);

            while (rs.next()) {
                uKDivisionList.add(rs.getString("Division"));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return uKDivisionList;
    }

    public static ObservableList<String> canadaDivisionList = FXCollections.observableArrayList();

    /**
     * This method returns a list of canada divisions where country id = 3
     *
     * @return returns canadadivisionList where country id = 3
     */
    public static ObservableList<String> getCanadaDivisionList() {

        Connection conn = DBConnection.getConnection();
        String query = "SELECT Division FROM first_level_divisions WHERE Country_ID = 3";
        Statement st;
        ResultSet rs;
        try {
            st = conn.createStatement();
            rs = st.executeQuery(query);

            while (rs.next()) {
                canadaDivisionList.add(rs.getString("Division"));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return canadaDivisionList;
    }

    public static ObservableList<String> allDivisionList = FXCollections.observableArrayList();

    /**
     * This method returns a list of all divisions
     *
     * @return returns a list of all divsions from the databaes
     */
    public static ObservableList<String> getAllDivisionList() {

        Connection conn = DBConnection.getConnection();
        String query = "SELECT * FROM first_level_divisions";
        Statement st;
        ResultSet rs;
        try {
            st = conn.createStatement();
            rs = st.executeQuery(query);

            while (rs.next()) {
                allDivisionList.add(rs.getString("Division"));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return allDivisionList;
    }

    public static ObservableList<Integer> divisionIDList = FXCollections.observableArrayList();

    /**
     * This method returns a list of division ids from first_level_divisions
     *
     * @return returns list of division ids
     */
    public static ObservableList<Integer> getDivisionIDList() {

        Connection conn = DBConnection.getConnection();
        String query = "SELECT Division_ID FROM first_level_divisions";
        Statement st;
        ResultSet rs;
        try {
            st = conn.createStatement();
            rs = st.executeQuery(query);

            while (rs.next()) {
                divisionIDList.add(rs.getInt("Division_ID"));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return divisionIDList;
    }

    public static ObservableList<Integer> allContactIDs = FXCollections.observableArrayList();

    /**
     * This method returns a list of all contact Ids from the database
     *
     * @return returns a list of all contact ids from contacts
     */
    public static ObservableList<Integer> getAllContactIDs() {
        Connection conn = DBConnection.getConnection();
        String query = "SELECT Contact_ID FROM contacts";
        Statement st;
        ResultSet rs;
        try {
            st = conn.createStatement();
            rs = st.executeQuery(query);

            while (rs.next()) {
                allContactIDs.add(rs.getInt("Contact_ID"));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return allContactIDs;


    }

    public static ObservableList<Integer> allCustomerIDs = FXCollections.observableArrayList();

    /**
     * This method returns a list of all customer Ids from the database
     *
     * @return returns list of all customer Ids from customers
     */
    public static ObservableList<Integer> getAllCustomerIDs() {

        Connection conn = DBConnection.getConnection();
        String query = "SELECT Customer_ID FROM customers";
        Statement st;
        ResultSet rs;
        try {
            st = conn.createStatement();
            rs = st.executeQuery(query);

            while (rs.next()) {
                allCustomerIDs.add(rs.getInt("Customer_ID"));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return allCustomerIDs;


    }


    public static ObservableList<Integer> allUserIDs = FXCollections.observableArrayList();

    /**
     * returns a list of all user ids from the database
     *
     * @return returns all user ids from users
     */
    public static ObservableList<Integer> getAllUserIDs() {
        Connection conn = DBConnection.getConnection();
        String query = "SELECT User_ID FROM users";
        Statement st;
        ResultSet rs;
        try {
            st = conn.createStatement();
            rs = st.executeQuery(query);

            while (rs.next()) {
                allUserIDs.add(rs.getInt("User_ID"));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return allUserIDs;


    }

    public static ObservableList<String> userNames = FXCollections.observableArrayList();

    /**
     * This method returns a list of all user names on record
     *
     * @return returns all user names from users
     */
    public static ObservableList<String> getUserNames() {


        Connection conn = DBConnection.getConnection();
        String query = "SELECT User_Name FROM users";
        Statement st;
        ResultSet rs;
        try {
            st = conn.createStatement();
            rs = st.executeQuery(query);

            while (rs.next()) {
                userNames.add(rs.getString("User_Name"));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return userNames;
    }

    /**
     * This method searches to see if user input (for username) matches existing usernames on the database
     *
     * @param username
     * @return returns true or false based on search results. True if found.
     */
    @FXML
    public static boolean searchUserNames(String username) {
        if (userNames.contains(username)) {
            return true;

        } else {
            return false;
        }

    }


    public static ObservableList<String> allCountries = FXCollections.observableArrayList();

    /**
     * This method returns a list of all countries in the database
     *
     * @return returns list of country names from countries
     */
    public static ObservableList<String> getAllCountries() {

        Connection conn = DBConnection.getConnection();
        String query = "SELECT Country FROM countries";
        Statement st;
        ResultSet rs;
        try {
            st = conn.createStatement();
            rs = st.executeQuery(query);

            while (rs.next()) {
                allCountries.add(rs.getString("Country"));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return allCountries;
    }


    /**
     * This method returns division name based on division id
     *
     * @param id division id
     * @return returns division name based on division id (user input)
     */
    public static Division getDivisionName(int id) {
        ObservableList<Division> newDivisionName = FXCollections.observableArrayList();
        Connection conn = DBConnection.getConnection();
        String query = "SELECT * FROM first_level_divisions WHERE Division_ID = ?";
        PreparedStatement st;
        ResultSet rs;

        // rs.getInt("Customer_ID"),
        Division division = null;
        try {
            st = conn.prepareStatement(query);
            st.setInt(1, id);


            rs = st.executeQuery();
            while (rs.next()) {
                division = new Division(rs.getInt("Division_ID"), rs.getString("Division"),
                        rs.getInt("Country_ID"));
                newDivisionName.add(division);
            }

        } catch (Exception ex) {
            ex.printStackTrace();

        }
        return division;
    }

    /**
     * This method returns a list of appointments for a specific customer
     *
     * @param customer_id customer id
     * @return returns a list of appointments based on customer id
     */
    public static ObservableList<Appointment> getAppointmentsPerCustomer(int customer_id) {
        ObservableList<Appointment> appointmentsList = FXCollections.observableArrayList();
        Connection conn = DBConnection.getConnection();
        String query = "SELECT * FROM appointments WHERE Customer_ID = ?";
        PreparedStatement st;
        ResultSet rs;


        Appointment appointment = null;
        try {
            st = conn.prepareStatement(query);
            st.setInt(1, customer_id);
            rs = st.executeQuery();

            while (rs.next()) {
                appointment = new Appointment(rs.getInt("Appointment_ID"), rs.getString("Title"),
                        rs.getString("Description"), rs.getString("Location"), rs.getString("Type"), rs.getTimestamp("Start").toLocalDateTime(), rs.getTimestamp("End").toLocalDateTime(), rs.getInt("Customer_ID"), rs.getInt("User_ID"), rs.getInt("Contact_ID"));
                appointmentsList.add(appointment);
            }

        } catch (Exception ex) {
            ex.printStackTrace();

        }
        return appointmentsList;
    }

    /**
     * This method returns a list of appointments for a specific contact
     *
     * @param id contact id
     * @return returns a list of appointments for specified contact id (user selected)
     */
    public static ObservableList<Appointment> getAppointmentsPerContact(int id) {
        ObservableList<Appointment> appointmentsList = FXCollections.observableArrayList();
        Connection conn = DBConnection.getConnection();
        String query = "SELECT * FROM appointments WHERE Contact_ID = ?";
        PreparedStatement st;
        ResultSet rs;


        Appointment appointment = null;
        try {
            st = conn.prepareStatement(query);
            st.setInt(1, id);
            rs = st.executeQuery();

            while (rs.next()) {
                appointment = new Appointment(rs.getInt("Appointment_ID"), rs.getString("Title"),
                        rs.getString("Description"), rs.getString("Location"), rs.getString("Type"), rs.getTimestamp("Start").toLocalDateTime(), rs.getTimestamp("End").toLocalDateTime(), rs.getInt("Customer_ID"), rs.getInt("User_ID"), rs.getInt("Contact_ID"));
                appointmentsList.add(appointment);
            }

        } catch (Exception ex) {
            ex.printStackTrace();

        }
        return appointmentsList;
    }


    public static ObservableList<String> countryName = FXCollections.observableArrayList();


    /**
     * This method returns country id based on selected division id
     *
     * @param id
     * @return returns country id given a specified division id
     */
    public static int getCountryIdByDivisionId(int id) {
        try {
            String query = "select Country_ID from first_level_divisions where Division_ID = ?";
            Connection conn = DBConnection.getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            return resultSet.getInt("Country_ID");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return -1;
        }
    }


    /**
     * This method looks up password based on username
     *
     * @param username
     * @return returns password for specified username
     */
    public static String getUserPassword(String username) {
        try {
            String query = "select Password from users where User_Name = ?";
            Connection conn = DBConnection.getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            return resultSet.getString("Password");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }

    }

    /**
     * This method returns country name given a specified country id
     *
     * @param id
     * @return returns country name based on country id
     * @throws SQLException
     */
    public static String getCountryNameByCountryID(int id) throws SQLException {
        String query = "select Country from Countries where Country_ID = ?";
        Connection conn = DBConnection.getConnection();
        PreparedStatement preparedStatement = conn.prepareStatement(query);
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        return resultSet.getString("Country");

    }

    /**
     * This method returns contact name given specified contact id
     *
     * @param id
     * @return returns contact name
     * @throws SQLException
     */
    public static String getContactNameByContactID(int id) throws SQLException {
        String query = "select Contact_Name from Contacts where Contact_ID = ?";
        Connection conn = DBConnection.getConnection();
        PreparedStatement preparedStatement = conn.prepareStatement(query);
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        return resultSet.getString("Contact_Name");

    }

    /**
     * This method gets contact id given specfied contact name
     *
     * @param name
     * @return returns contact ID
     * @throws SQLException
     */
    public static int getContactIDByContactName(String name) throws SQLException {
        String query = "select Contact_ID from Contacts where Contact_Name = ?";
        Connection conn = DBConnection.getConnection();
        PreparedStatement preparedStatement = conn.prepareStatement(query);
        preparedStatement.setString(1, name);
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        return resultSet.getInt("Contact_ID");

    }


    /**
     * This method returns all contact names from contacts in the database
     *
     * @return returns contact names
     */
    public static ObservableList<String> getContactNames() {
        ObservableList<String> contactsNameList = FXCollections.observableArrayList();

        Connection conn = DBConnection.getConnection();
        String query = "SELECT Contact_Name FROM contacts";
        Statement st;
        ResultSet rs;
        try {
            st = conn.createStatement();
            rs = st.executeQuery(query);

            while (rs.next()) {
                contactsNameList.add(rs.getString("Division"));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return contactsNameList;
    }



}
