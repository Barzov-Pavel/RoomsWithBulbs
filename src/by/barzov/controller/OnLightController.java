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

public class OnLightController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Room room = new Room();
        room.setId(Long.parseLong(req.getParameter("id")));
        room.setName(req.getParameter("name"));
        room.setCountry(req.getParameter("country"));
        if (Boolean.parseBoolean(req.getParameter("light")) == true) {
            room.setLightIsOn(false);
        } else {
            room.setLightIsOn(true);
        }
        Connection connection = null;
        try {
            connection = Connector.getConnection();
            RoomDaoImpl dao = new RoomDaoImpl();
            dao.setConnection(connection);
            RoomServiceImpl service = new RoomServiceImpl();
            service.setRoomDao(dao);
            service.onLight(room);
            resp.sendRedirect(req.getContextPath() + "/room/room.html?id="+room.getId());
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
