package by.barzov.test.dao;


import by.barzov.util.Connector;

public class TestInitializator {
    public static void init() {
        try {
            Connector.init("com.mysql.jdbc.Driver",
                    "jdbc:mysql://localhost:3306/rooms_with_bulbs", "root", "");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
