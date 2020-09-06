package by.barzov.test.dao;


import by.barzov.util.Connector;

public class TestInitializator {
    public static void init() {
        try {
            Connector.init("com.mysql.jdbc.Driver",
                    "jdbc:mysql://localhost:3306/rooms_with_bulbs?useUnicode=true&amp;characterEncoding=utf8", "root", "");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
