package sample;

/**
 * This is the contact class
 */
public class Contacts {

    public int contact_id;
    public String contact_name;
    public String email;

    public Contacts(int contact_id, String contact_name, String email) {
        this.contact_id = contact_id;
        this.contact_name = contact_name;
        this.email = email;
    }

    /**
     * @param contact_name
     * @return Returns contact id
     */
    public int getContactID(String contact_name) {
        return contact_id;
    }

    /**
     * @param contact_id
     * @return Returns contact id
     */
    public int Contacts(int contact_id) {
        return contact_id;
    }

    /**
     * @return Returns contact id
     */
    public int getContact_id() {
        return contact_id;
    }

    /**
     * @param contact_id sets contact id
     */

    public void setContact_id(int contact_id) {
        this.contact_id = contact_id;
    }

    /**
     * @return Returns contact name
     */
    public String getContact_name() {
        return contact_name;
    }

    /**
     * @param contact_name sets contact name
     */
    public void setContact_name(String contact_name) {
        this.contact_name = contact_name;
    }

    /**
     * @return Returns email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email sets email
     */
    public void setEmail(String email) {
        this.email = email;
    }
}
