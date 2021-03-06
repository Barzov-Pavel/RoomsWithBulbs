package by.barzov.domain;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Room {
    private String ip;
    private Long id;
    private String name;
    private String country;
    private Boolean lightIsOn;

    public Long getId() {
        return id;
    }

    public List<String> getCountries() {
        String csvFile = "E:\\проекты\\RoomsWithBulbs\\src\\web\\WEB-INF\\lib\\GeoLite2-Country-Locations-en.csv";
        String line = "";
        String cvsSplitBy = ",";

        List<String> countries = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            while ((line = br.readLine()) != null) {

                // use comma as separator
                String[] country = line.split(cvsSplitBy);

                countries.add(country[5]);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < countries.size(); i++) {
            String string = null;
            String old = countries.get(0);
            string = old.replace("\"", "");
            countries.remove(old);
            countries.add(string);
        }
        countries.remove("country_name");
        countries.sort((x, y) -> x.compareTo(y));
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

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }
}
