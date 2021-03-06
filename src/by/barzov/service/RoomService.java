package by.barzov.service;

import by.barzov.domain.Room;

import java.util.List;

public interface RoomService {
    List<Room> findAll() throws ServiceException;
    void create(Room room) throws ServiceException;
    void onLight(Room room) throws ServiceException;
    Room readRoomById(Long id) throws ServiceException;
}
