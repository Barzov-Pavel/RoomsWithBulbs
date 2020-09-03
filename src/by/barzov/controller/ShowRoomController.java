package by.barzov.controller;

import by.barzov.dao.sql.RoomDaoImpl;
import by.barzov.domain.Room;
import by.barzov.service.ServiceException;
import by.barzov.service.logic.RoomServiceImpl;
import by.barzov.util.Connector;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class ShowRoomController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Room room = new Room();
        room.setId(Long.parseLong(req.getParameter("id")));
        room.setName(req.getParameter("name"));
        room.setCountry(req.getParameter("country"));
        room.setLightIsOn(Boolean.parseBoolean(req.getParameter("light")));
        req.setAttribute("room", room);
        req.getRequestDispatcher("/WEB-INF/jsp/room/room.jsp").forward(req, resp);
    }
}
