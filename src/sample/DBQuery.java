package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

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

   /* public static Appointment getApptsForContact(int contactID) {
        ObservableList<Appointment> apptsForContact = FXCollections.observableArrayList();
        Connection conn = DBConnection.getConnection();
        String query = "SELECT * FROM appointments WHERE Contact_ID = ?";
        PreparedStatement st;
        ResultSet rs;

        // rs.getInt("Customer_ID"),
        Appointment appointments = null;
        try {
            st = conn.prepareStatement(query);
            st.setInt(1, contactID);


            rs = st.executeQuery();
            while (rs.next()) {
                appointments = new Division(rs.getInt("Division_ID"), rs.getString("Division"),
                        rs.getInt("Country_ID"));
                newDivisionName.add(division);
            }

        } catch (Exception ex) {
            ex.printStackTrace();

        }
*/

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

    public static String getCountryNameByCountryID(int id) throws SQLException {
        String query = "select Country from Countries where Country_ID = ?";
        Connection conn = DBConnection.getConnection();
        PreparedStatement preparedStatement = conn.prepareStatement(query);
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        return resultSet.getString("Country");

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
