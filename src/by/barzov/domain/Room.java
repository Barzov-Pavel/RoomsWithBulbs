package by.barzov.domain;

import java.util.ArrayList;
import java.util.List;

public class Room {
    private Long id;
    private String name;
    private String country;
    private Boolean lightIsOn;

    public Long getId() {
        return id;
    }

    public List<String> getCountries() {
        List<String> countries = new ArrayList<>();
        countries.add("Belarus");
        countries.add("USA");
        countries.add("Germany");
        return countries;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Boolean getLightIsOn() {
        return lightIsOn;
    }

    public void setLightIsOn(Boolean lightIsOn) {
        this.lightIsOn = lightIsOn;
    }
}
