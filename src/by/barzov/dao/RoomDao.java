package by.barzov.dao;

import by.barzov.domain.Room;

import java.util.List;

public interface RoomDao extends Dao<Room> {
    List<Room> readAll() throws DaoException;
}
