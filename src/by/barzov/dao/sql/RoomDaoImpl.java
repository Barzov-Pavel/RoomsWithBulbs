package by.barzov.dao.sql;

import by.barzov.dao.DaoException;
import by.barzov.dao.RoomDao;
import by.barzov.domain.Room;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class RoomDaoImpl extends BaseDaoImpl implements RoomDao {
    @Override
    public List<Room> readAll() throws DaoException {
        String sql = "SELECT `id`, `name`, `country`, `light_is_on` FROM `room`";
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            statement = getConnection().createStatement();
            resultSet = statement.executeQuery(sql);
            List<Room> rooms = new ArrayList<>();
            while (resultSet.next()) {
                Room room = new Room();
                room.setId(resultSet.getLong("id"));
                room.setName(resultSet.getString("name"));
                room.setCountry(resultSet.getString("country"));
                if (resultSet.getInt("light_is_on") == 1) {
                    room.setLightIsOn(true);
                } else {
                    room.setLightIsOn(false);
                }
                rooms.add(room);
            }
            return rooms;
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            try {
                statement.close();
            } catch (Exception e) {
            }
            try {
                resultSet.close();
            } catch (Exception e) {
            }
        }
    }

    @Override
    public Room read(Long id) throws DaoException {
        String sql = "SELECT `name`, `country`, `light_is_on` FROM `room` WHERE `id` = ?";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = getConnection().prepareStatement(sql);
            statement.setLong(1, id);
            resultSet = statement.executeQuery();
            Room room = null;
            if(resultSet.next()) {
                room = new Room();
                room.setId(id);
                room.setName(resultSet.getString("name"));
                room.setCountry(resultSet.getString("country"));
                if (resultSet.getInt("light_is_on") == 1){
                    room.setLightIsOn(true);
                } else {
                    room.setLightIsOn(false);
                }
            }
            return room;
        } catch(SQLException e) {
            throw new DaoException(e);
        } finally {
            try{ statement.close(); } catch(Exception e) {}
            try{ resultSet.close(); } catch(Exception e) {}
        }
    }

    @Override
    public Long create(Room room) throws DaoException {
        String sql = "INSERT INTO `room` (`name`, `country`, `light_is_on`) VALUES (?, ?, ?)";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, room.getName());
            statement.setString(2, room.getCountry());
            if (room.getLightIsOn()){
                statement.setInt(3, 1);
            } else {
                statement.setInt(3, 0);
            }
            statement.executeUpdate();
            Long id = null;
            resultSet = statement.getGeneratedKeys();
            if(resultSet.next()) {
                id = resultSet.getLong(1);
            }
            return id;
        } catch(SQLException e) {
            throw new DaoException(e);
        } finally {
            try{ statement.close(); } catch(Exception e) {}
            try{ resultSet.close(); } catch(Exception e) {}
        }
    }

    @Override
    public void update(Room room) throws DaoException {
        String sql = "UPDATE `room` SET `light_is_on` = ? WHERE `id` = ?";
        PreparedStatement statement = null;
        try {
            statement = getConnection().prepareStatement(sql);
            if (room.getLightIsOn()) {
                statement.setInt(1, 1);
            } else {
                statement.setInt(1,0);
            }
            statement.setLong(2, room.getId());
            statement.executeUpdate();
        } catch(SQLException e) {
            throw new DaoException(e);
        } finally {
            try{ statement.close(); } catch(Exception e) {}
        }
    }
}
