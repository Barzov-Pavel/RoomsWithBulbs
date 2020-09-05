package by.barzov.controller;

import by.barzov.dao.sql.RoomDaoImpl;
import by.barzov.domain.Room;
import by.barzov.service.ServiceException;
import by.barzov.service.logic.RoomServiceImpl;
import by.barzov.util.Connector;
import com.maxmind.geoip2.DatabaseReader;
import com.maxmind.geoip2.exception.GeoIp2Exception;
import com.maxmind.geoip2.model.CountryResponse;
import com.maxmind.geoip2.record.Country;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
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
            room.setIp(getClientIp(req));
            req.setAttribute("room", room);
            if (checkCountryIp(room)) {
                req.getRequestDispatcher("/WEB-INF/jsp/room/room.jsp").forward(req, resp);
            } else if (room.getIp().equals("0:0:0:0:0:0:0:1") || room.getIp().equals("127.0.0.1")) {
                req.getRequestDispatcher("/WEB-INF/jsp/room/room.jsp").forward(req, resp);
            }
            else {
                //resp.sendRedirect(req.getContextPath() + "/room/exception.html)");
                req.getRequestDispatcher("/WEB-INF/jsp/room/exception.jsp").forward(req, resp);
            }

        } catch (SQLException | ServiceException e) {
            throw new ServletException(e);
        } finally {
            try {
                connection.close();
            } catch (Exception e) {
            }
        }
    }

    private static String getClientIp(HttpServletRequest request) {
        String remoteAddr = "";
        if (request != null) {
            remoteAddr = request.getHeader("X-Forwarded-For");
            if (remoteAddr == null || "".equals(remoteAddr)) {
                remoteAddr = request.getRemoteAddr();
            }
        }
        return remoteAddr;
    }

    public static boolean checkCountryIp(Room room) {
        File database = new File("E:\\проекты\\RoomsWithBulbs\\src\\web\\WEB-INF\\lib\\GeoLite2-Country.mmdb");
        DatabaseReader dbReader = null;
        try {
            dbReader = new DatabaseReader.Builder(database).build();
            InetAddress ip = InetAddress.getByName(room.getIp());
            CountryResponse response = dbReader.country(ip);
            String countryName = response.getCountry().getName();
            if (countryName.equals(room.getCountry())) {
                return true;
            } else return false;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (GeoIp2Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
