package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.AddAppointmentController;
import sample.Contacts;

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










}
