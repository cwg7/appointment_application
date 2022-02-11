package sample;

import javafx.scene.control.DatePicker;

import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * This is the appointment class
 */
public class Appointment {

    private String contact_name;
    private int appointment_id;
    private String title;
    private String description;
    private String location;
    private String type;
    //private String start_time;
    //private String end_time;
    private LocalDateTime start_time;
    private LocalDateTime end_time;
    private int customer_id;
    private int user_id;
    private int contact_id;


    public Appointment(int appointment_id, String title, String description, String location, String type, LocalDateTime start_time, LocalDateTime end_time, int customer_id, int user_id, int contact_id) {
        this.appointment_id = appointment_id;
        this.title = title;
        this.description = description;
        this.location = location;
        this.type = type;
        this.start_time = start_time;
        this.end_time = end_time;
        this.customer_id = customer_id;
        this.user_id = user_id;
        this.contact_id = contact_id;
    }
    public Appointment(int appointment_id, String title, String description, String location, String type, LocalDateTime start_time, LocalDateTime end_time, int customer_id, int user_id, String contact_name) {
        this.appointment_id = appointment_id;
        this.title = title;
        this.description = description;
        this.location = location;
        this.type = type;
        this.start_time = start_time;
        this.end_time = end_time;
        this.customer_id = customer_id;
        this.user_id = user_id;
        this.contact_name = contact_name;
    }

    /**
     *
     * @return Returns the appointment id
     */
    public int getAppointment_id() {
        return appointment_id;
    }

    /**
     *
     * @param appointment_id sets appointment id
     */
    public void setAppointment_id(int appointment_id) {
        this.appointment_id = appointment_id;
    }

    /**
     * @return Returns appointment title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title sets appointment title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return Returns appointment description
     */
    public String getDescription() {
        return description;
    }

    /**
     *
     * @param description sets appointment description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     *
     * @return Returns appointment location
     */
    public String getLocation() {
        return location;
    }

    /**
     *
     * @param location sets appointment location
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     *
     * @return Returns appointment type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type sets appointment type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     *
     * @return Returns appointment start time
     */
    public LocalDateTime getStart_time() {
        return start_time;
    }

    /**
     *
     * @param start_time sets appointment start time
     */
    public void setStart_time(LocalDateTime start_time) {
        this.start_time = start_time;
    }

    /**
     *
     * @return Returns appointment end time
     */
    public LocalDateTime getEnd_time() {
        return end_time;
    }

    /**
     * @param end_time sets appointment end time
     */
    public void setEnd_time(LocalDateTime end_time) {
        this.end_time = end_time;
    }

    /**
     *
     * @return Returns customer id
     */
    public int getCustomer_id() {
        return customer_id;
    }

    /**
     *
     * @param customer_id sets customer id
     */
    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }

    /**
     *
     * @return Returns user id
     */
    public int getUser_id() {
        return user_id;
    }

    /**
     * @param user_id sets user id
     */
    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    /**
     *
     * @return Returns contact id
     */
    public int getContact_id() {
        return contact_id;
    }

    /**
     *
     * @param contact_id sets contact id
     */
    public void setContact_id(int contact_id) {
        this.contact_id = contact_id;
    }

    /**
     *
     * @return Returns contact name
     */
    public String getContact_name() {
        return contact_name;
    }

    /**
     *
     * @param contact_name sets contact name
     */
    public void setContact_name(String contact_name) {
        this.contact_name = contact_name;
    }
}
