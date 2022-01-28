package sample;

public class Contacts {

    public int contact_id;
    public String contact_name;
    public String email;

    public Contacts(int contact_id, String contact_name, String email) {
        this.contact_id = contact_id;
        this.contact_name = contact_name;
        this.email = email;
    }

/*
    public Contacts(int contact_id) {
        this.contact_id = contact_id;
        this.contact_name = contact_name;
    }
*/

    public int getContactID(String contact_name){
        return contact_id;
    }

    public int Contacts(int contact_id) {
        return contact_id;
    }

    public int getContact_id() {
        return contact_id;
    }

    public void setContact_id(int contact_id) {
        this.contact_id = contact_id;
    }

    public String getContact_name() {
        return contact_name;
    }

    public void setContact_name(String contact_name) {
        this.contact_name = contact_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
