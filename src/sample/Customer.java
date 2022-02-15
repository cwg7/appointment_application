package sample;

import java.sql.SQLException;

/**
 * This is the customer class
 */
public class Customer {
    private int id;
    private String name;
    private String address;
    private String postalCode;
    private String phoneNumber;
    private int division_id;
    private String countryName;

    public Customer(int id, String name, String address, String postalCode, String phoneNumber, int division_id) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.postalCode = postalCode;
        this.phoneNumber = phoneNumber;
        this.division_id = division_id;
        try {
            this.countryName = DBQuery.getCountryNameByCountryID(DBQuery.getCountryIdByDivisionId(division_id));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public Customer(int id, String name, String address, String postalCode, String phoneNumber, int division_id, String countryName) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.postalCode = postalCode;
        this.phoneNumber = phoneNumber;
        this.division_id = division_id;
        this.countryName = countryName;
    }

    /**
     * @return returns country name
     */
    public String getCountryName() {
        return countryName;
    }

    /**
     * @param countryName sets country name
     */
    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    /**
     * @return returns customer id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id sets customer id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return returns customer name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name sets customer name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return returns address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address sets address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @return returns postal code
     */
    public String getPostalCode() {
        return postalCode;
    }

    /**
     * @param postalCode sets postal code
     */
    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    /**
     * @return returns phone number
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * @param phoneNumber sets phone number
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * @return returns division id
     */
    public int getDivision_id() {
        return division_id;
    }

    /**
     * @param division_id sets division id
     */
    public void setDivision_id(int division_id) {
        this.division_id = division_id;
    }

}
