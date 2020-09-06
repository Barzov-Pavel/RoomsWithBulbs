package by.barzov.controller;

import by.barzov.domain.Room;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class RoomCreateController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        Room room = new Room();
        List<String> countries = room.getCountries();
        req.setAttribute("countries", countries);
        req.getRequestDispatcher("/WEB-INF/jsp/room/create.jsp").forward(req, resp);
    }
}
