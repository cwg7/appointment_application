package sample;

/**
 * This is the division class
 */
public class Division {
    private int id;
    private String division_name;
    private int country_id;


    public Division(int id, String division_name, int country_id) {
        this.id = id;
        this.division_name = division_name;
        this.country_id = country_id;

    }

    /**
     * @return returns division id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id sets division id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * this method returns division name
     *
     * @return returns division name
     */
    public String getDivision_name() {
        return division_name;
    }

    /**
     * this method sets division name
     *
     * @param division_name
     */
    public void setDivision_name(String division_name) {
        this.division_name = division_name;
    }

    /**
     * This method returns country id
     *
     * @return returns country id
     */
    public int getCountry_id() {
        return country_id;
    }

    /**
     * This method sets country id
     *
     * @param country_id
     */
    public void setCountry_id(int country_id) {
        this.country_id = country_id;
    }
}

