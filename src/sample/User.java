package sample;

/**
 * This is the User class
 */
public class User {

    private int user_id;
    private String user_name;
    private String password;

    public User(int user_id, String user_name, String password) {
        this.user_id = user_id;
        this.user_name = user_name;
        this.password = password;
    }

    /**
     * This method returns the user id
     *
     * @return returns user id
     */
    public int getUser_id() {
        return user_id;
    }

    /**
     * this method sets user id
     *
     * @param user_id
     */
    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    /**
     * This method returns user name
     *
     * @return returns user name
     */
    public String getUser_name() {
        return user_name;
    }

    /**
     * This metohd sets user name
     *
     * @param user_name
     */
    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    /**
     * This method returns user password
     *
     * @return returns user password
     */

    public String getPassword() {
        return password;
    }

    /**
     * This method sets user password
     *
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }
}
