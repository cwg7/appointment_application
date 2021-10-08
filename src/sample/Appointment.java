package sample;

import javafx.scene.control.DatePicker;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class Appointment {

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

    public int getAppointment_id() {
        return appointment_id;
    }

    public void setAppointment_id(int appointment_id) {
        this.appointment_id = appointment_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public LocalDateTime getStart_time() {
        return start_time;
    }

    public void setStart_time(LocalDateTime start_time) {
        this.start_time = start_time;
    }

    public LocalDateTime getEnd_time() {
        return end_time;
    }

    public void setEnd_time(LocalDateTime end_time) {
        this.end_time = end_time;
    }

    public int getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getContact_id() {
        return contact_id;
    }

    public void setContact_id(int contact_id) {
        this.contact_id = contact_id;
    }
}
