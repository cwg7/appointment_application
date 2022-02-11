package sample;

/**
 * This is the countries class
 */
public class Countries {

    private int country_id;
    private String country_name;


    public Countries(int country_id, String country_name) {
        this.country_id = country_id;
        this.country_name = country_name;
    }

    /**
     *
     * @return Returns country id
     */
    public int getCountry_id() {
        return country_id;
    }

    /**
     *
     * @param country_id sets country id
     */
    public void setCountry_id(int country_id) {
        this.country_id = country_id;
    }

    /**
     *
     * @return returns country name
     */
    public String getCountry_name() {
        return country_name;
    }

    /**
     *
     * @param country_name sets country name
     */
    public void setCountry_name(String country_name) {
        this.country_name = country_name;
    }
}
