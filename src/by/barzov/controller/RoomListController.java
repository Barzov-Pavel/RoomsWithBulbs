package by.barzov.controller;

import by.barzov.dao.sql.RoomDaoImpl;
import by.barzov.domain.Room;
import by.barzov.service.ServiceException;
import by.barzov.service.logic.RoomServiceImpl;
import by.barzov.util.Connector;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RoomListController extends HttpServlet {
    @Override
    public void init() throws ServletException {
        try {
            Connector.init("com.mysql.jdbc.Driver",
                    "jdbc:mysql://localhost:3306/rooms_with_bulbs?useUnicode=true&amp;characterEncoding=utf8", "root", "");
        } catch (ClassNotFoundException e) {
            throw new ServletException(e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        Connection connection = null;
        try {
            connection = Connector.getConnection();
            RoomDaoImpl dao = new RoomDaoImpl();
            dao.setConnection(connection);
            RoomServiceImpl service = new RoomServiceImpl();
            service.setRoomDao(dao);
            List<Room> rooms = service.findAll();
            req.setAttribute("rooms", rooms);
            req.getRequestDispatcher("/WEB-INF/jsp/room/list.jsp").forward(req, resp);
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
