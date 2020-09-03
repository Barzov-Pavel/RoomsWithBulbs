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

public class RoomSaveController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Room room = new Room();

        try {
            room.setName(req.getParameter("name"));
            room.setCountry(req.getParameter("country"));
            room.setLightIsOn(false);
        } catch (NumberFormatException e) {
        }
        Connection connection = null;
        try {
            connection = Connector.getConnection();
            RoomDaoImpl dao = new RoomDaoImpl();
            dao.setConnection(connection);
            RoomServiceImpl service = new RoomServiceImpl();
            service.setRoomDao(dao);
            service.create(room);
        } catch (SQLException | ServiceException ex) {
            throw new ServletException(ex);
        } finally {
            try {
                connection.close();
            } catch (Exception ex) {
            }
        }
        resp.sendRedirect(req.getContextPath() + "/room/list.html");
    }
}
