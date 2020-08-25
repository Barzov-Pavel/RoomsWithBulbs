package by.barzov.test.dao;

import by.barzov.dao.DaoException;
import by.barzov.dao.RoomDao;
import by.barzov.dao.sql.RoomDaoImpl;
import by.barzov.domain.Room;
import by.barzov.util.Connector;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class RoomDaoTest {
    public static void main(String[] args) {
        TestInitializator.init();
        RoomDaoImpl roomDao = new RoomDaoImpl();
        Connection connection = null;

        try {
            connection = Connector.getConnection();
            roomDao.setConnection(connection);
            System.out.println("UserDao.read(1L):");
            readTest(roomDao);
            System.out.println("UserDao.readAll():");
            readAllTest(roomDao);
            System.out.println("UserDao.create():");
            Long id = createTest(roomDao);
            System.out.println("UserDao.readAll():");
            readAllTest(roomDao);
            System.out.println("UserDao.update():");
            updateTest(roomDao, id);
            System.out.println("UserDao.readAll():");
            readAllTest(roomDao);
            System.out.println("UserDao.readAll():");
            readAllTest(roomDao);
        } catch(SQLException | DaoException e) {
            e.printStackTrace();
        } finally {
            try{ connection.close(); } catch(Exception e) {}
        }
    }
    public static void readTest(RoomDao roomDao) throws DaoException {
        Room room = roomDao.read(1L);
        output(room);
    }

    public static void readAllTest(RoomDao roomDao) throws DaoException {
        List<Room> users = roomDao.readAll();
        for(Room user : users) {
            output(user);
        }
    }

    public static Long createTest(RoomDao userDao) throws DaoException {
        Room room = new Room();
        room.setName("first room");
        room.setCountry("Belarus");
        room.setLightIsOn(true);
        Long id = userDao.create(room);
        System.out.printf("\tUser successfully added with id=%d\n", id);
        return id;
    }

    public static void updateTest(RoomDao userDao, Long id) throws DaoException {
        Room room = new Room();
        room.setId(id);
        room.setLightIsOn(false);
        userDao.update(room);
        System.out.println("\tUser was successfully updated");
    }

    private static void output(Room room) {
        System.out.printf("\tid=%d, name=%s, country=%s, light is on=%s\n", room.getId(), room.getName(),
                room.getCountry(), room.getLightIsOn());
    }
}
