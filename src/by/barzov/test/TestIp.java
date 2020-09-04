package by.barzov.test;

import com.maxmind.geoip2.DatabaseReader;
import com.maxmind.geoip2.exception.GeoIp2Exception;
import com.maxmind.geoip2.model.CountryResponse;
import com.maxmind.geoip2.record.RepresentedCountry;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;

public class TestIp {


    public static void main(String[] args) throws IOException, GeoIp2Exception {
        File database = new File("E:\\проекты\\RoomsWithBulbs\\src\\web\\WEB-INF\\lib\\GeoLite2-Country.mmdb");
        DatabaseReader dbReader = new DatabaseReader.Builder(database).build();
        InetAddress ip = InetAddress.getByName("0:0:0:0:0:0:0:1");
        CountryResponse response = dbReader.country(ip);

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
        countries.removeIf(x -> x.isBlank());
        countries.sort((x, y) -> x.compareTo(y));

        System.out.println(countries.get(0));

        String str = countries.get(0).replace("\"", "");
        System.out.println(str);

        for (int i = 0; i < countries.size(); i++) {
            String string = null;
            String old = countries.get(0);
            string = old.replace("\"", "");
            countries.remove(old);
            countries.add(string);
        }
        countries.remove("country_name");
        countries.sort((x, y) -> x.compareTo(y));


        //String countryName = response.getCountry().getName();


        System.out.println(countries);
    }


}
