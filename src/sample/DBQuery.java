package sample;

import com.mysql.cj.protocol.Resultset;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;

import java.sql.*;

import static java.sql.Timestamp.valueOf;

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

    public static ObservableList<Integer> getContactsIDList() {
        return contactsIDList;
    }



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

    public static ObservableList<String> typesList = FXCollections.observableArrayList();

    public static ObservableList<String> getAppointmentTypesList() {
        //ObservableList<Integer> contactsNameList = FXCollections.observableArrayList();
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

    public static ObservableList<String> getCountryList() {
        //ObservableList<Integer> contactsNameList = FXCollections.observableArrayList();
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

    public static ObservableList<String> getUsDivisionList() {
        //ObservableList<Integer> contactsNameList = FXCollections.observableArrayList();

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

    public static ObservableList<String> getUKDivisionList() {
        //ObservableList<Integer> contactsNameList = FXCollections.observableArrayList();

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

    public static ObservableList<String> getCanadaDivisionList() {
        //ObservableList<Integer> contactsNameList = FXCollections.observableArrayList();

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

    public static ObservableList<String> getAllDivisionList() {
        //ObservableList<Integer> contactsNameList = FXCollections.observableArrayList();

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

    public static ObservableList<Integer> getDivisionIDList() {
        //ObservableList<Integer> contactsNameList = FXCollections.observableArrayList();

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
    public static ObservableList<Integer> getAllContactIDs(){
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
    public static ObservableList<Integer> getAllCustomerIDs(){
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
    public static ObservableList<Integer> getAllUserIDs(){
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

    public static ObservableList<String> getUserNames() {
        //ObservableList<Integer> contactsNameList = FXCollections.observableArrayList();

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

    @FXML
    public static boolean searchUserNames(String username) {
        if (userNames.contains(username)) {
            //System.out.println("USERNAME EXISTS IN THE DATABASE");
            //userNameExists();
            return true;

        }
        else {
            //System.out.println("USERNAME NOT IN DB");
            return false;
        }

    }

    @FXML
    public static boolean userNameExists(){
        boolean userNameExists = true;
        return true;

    }

    @FXML
    public static void checkPassword(String password) {

    }

    public static ObservableList<String> totNumCustomers = FXCollections.observableArrayList();
/*

    public static String getTotNumCustomers() {


        Connection conn = DBConnection.getConnection();
        String query = "SELECT count(*) FROM customers";
        Statement st;
        ResultSet rs;
        try {
            st = conn.createStatement();
            rs = st.executeQuery(query);
            while (rs.next()) {
                rs.getString("Customer_Name");
            }


    } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return String.valueOf(totNumCustomers.size());
}

*/






    public static ObservableList<String> allCountries = FXCollections.observableArrayList();
    public static ObservableList<String> getAllCountries() {
        //ObservableList<Integer> contactsNameList = FXCollections.observableArrayList();

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



   /*public static Appointment getAppointmentsForContact(String contactName) {
       ObservableList<Contacts> appts = FXCollections.observableArrayList();
       Connection conn = DBConnection.getConnection();
       String query = "select * from appointments from  Contact_ID = ?";
       PreparedStatement st;
       ResultSet rs;

       // rs.getInt("Customer_ID"),
       Appointment appointments = null;
       try {
           st = conn.prepareStatement(query);
           st.setString(1, contactName);


           rs = st.executeQuery();
           while (rs.next()) {
               appointments = new Appointment(rs.getInt("Appointment_ID"), rs.getString("Title"),
                       rs.getString("Description"), rs.getString("Location"), rs.getString("Type"), rs.getTimestamp("Start").toLocalDateTime(), rs.getTimestamp("End").toLocalDateTime(), rs.getInt("Customer_ID"), rs.getInt("User_ID"), rs.getInt("Contact_ID"));
               appts.add(appointments);
           }

       } catch (Exception ex) {
           ex.printStackTrace();

       }
       return appts;
       // was newDivisionName
   }*/

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
        // was newDivisionName
    }

    public static ObservableList<Appointment> getAppointmentsPerCustomer(int id) {
        ObservableList<Appointment> appointmentsList = FXCollections.observableArrayList();
        Connection conn = DBConnection.getConnection();
        String query = "SELECT * FROM appointments WHERE Customer_ID = ?";
        PreparedStatement st;
        ResultSet rs;

        // rs.getInt("Customer_ID"),
        Appointment appointment = null;
        try {
            st = conn.prepareStatement(query);
            st.setInt(1, id);
            rs = st.executeQuery();
           // Appointment appointment;
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

    public static ObservableList<Appointment> getAppointmentsPerContact(int id) {
        ObservableList<Appointment> appointmentsList = FXCollections.observableArrayList();
        Connection conn = DBConnection.getConnection();
        String query = "SELECT * FROM appointments WHERE Contact_ID = ?";
        PreparedStatement st;
        ResultSet rs;

        // rs.getInt("Customer_ID"),
        Appointment appointment = null;
        try {
            st = conn.prepareStatement(query);
            st.setInt(1, id);
            rs = st.executeQuery();
            // Appointment appointment;
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




/*
    public static ObservableList<String> countryListOL = FXCollections.observableArrayList();

    public static ObservableList<String> getCountryListOL() {
        //ObservableList<Integer> contactsNameList = FXCollections.observableArrayList();

        Connection conn = DBConnection.getConnection();
        String query = "SELECT Division FROM first_level_divisions";
        Statement st;
        ResultSet rs;
        try {
            st = conn.createStatement();
            rs = st.executeQuery(query);

            while (rs.next()) {
                countryListOL.add(rs.getString("Division_ID"));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return countryListOL;
    }
*/

    public static ObservableList<String> countryName = FXCollections.observableArrayList();

/*    public static ObservableList<String> getCountryName(division_id) {
        //ObservableList<Integer> contactsNameList = FXCollections.observableArrayList();

        Connection conn = DBConnection.getConnection();
        String query = "SELECT * FROM first_level_divisions WHERE Division_ID = ?";
        PreparedStatement st;
        ResultSet rs;
        try {
            st = conn.prepareStatement(query);
            rs = st.executeQuery(query);

            while (rs.next()) {
                countryName.add(rs.getString("Division_ID"));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return countryName;
    }*/
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

    public static String getCountryNameByCountryID(int id) throws SQLException {
        String query = "select Country from Countries where Country_ID = ?";
        Connection conn = DBConnection.getConnection();
        PreparedStatement preparedStatement = conn.prepareStatement(query);
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        return resultSet.getString("Country");

    }

    public static String getContactNameByContactID(int id) throws SQLException {
        String query = "select Contact_Name from Contacts where Contact_ID = ?";
        Connection conn = DBConnection.getConnection();
        PreparedStatement preparedStatement = conn.prepareStatement(query);
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        return resultSet.getString("Contact_Name");

    }

    public static int getContactIDByContactName(String name) throws SQLException {
        String query = "select Contact_ID from Contacts where Contact_Name = ?";
        Connection conn = DBConnection.getConnection();
        PreparedStatement preparedStatement = conn.prepareStatement(query);
        preparedStatement.setString(1, name);
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        return resultSet.getInt("Contact_ID");

    }



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

    public static ObservableList<String> getUserNameLogin() {
        ObservableList<String> userNames = FXCollections.observableArrayList();

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









}
