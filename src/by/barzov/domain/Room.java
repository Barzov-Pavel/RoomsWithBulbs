package by.barzov.domain;

public class Room {
    private Long id;
    private String name;
    private String country;
    private Boolean lightIsOn;

    public Long getId() {
        return id;
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
