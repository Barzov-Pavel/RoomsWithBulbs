package by.barzov.service.logic;

import by.barzov.dao.DaoException;
import by.barzov.dao.RoomDao;
import by.barzov.domain.Room;
import by.barzov.service.RoomService;
import by.barzov.service.ServiceException;

import java.util.List;

public class RoomServiceImpl implements RoomService {
    private RoomDao roomDao;

    public void setRoomDao(RoomDao roomDao) {
        this.roomDao = roomDao;
    }

    @Override
    public List<Room> findAll() throws ServiceException{
        try {
            return roomDao.readAll();
        }catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

}