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
        Connection connection = null;
        try {
            connection = Connector.getConnection();
            RoomDaoImpl dao = new RoomDaoImpl();
            dao.setConnection(connection);
            RoomServiceImpl service = new RoomServiceImpl();
            service.setRoomDao(dao);
            Room room = service.readRoomById(Long.parseLong(req.getParameter("id")));
            req.setAttribute("room", room);
            req.getRequestDispatcher("/WEB-INF/jsp/room/room.jsp").forward(req, resp);
        } catch (SQLException | ServiceException e) {
            throw new ServletException(e);
        } finally {
            try {
                connection.close();
            } catch (Exception e) {
            }
        }
    }
}
