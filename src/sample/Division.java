package sample;

public class Division{
    private int id;
    private String division_name;
    private  int country_id;

    public Division(String id, String division_name, String country_id)  {
        this.id = Integer.parseInt(id);
        this.division_name = division_name;
        this.country_id = Integer.parseInt(country_id);
    }

    public int getId() {
        return id;
    }
    public String getDivision_name() {
        return division_name;
    }
    public int getCountryId() {
        return country_id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDivision_name(String division_name) {
        this.division_name = division_name;
    }

    public void setCountry_id(int country_id) {
        this.country_id = country_id;
    }
}
