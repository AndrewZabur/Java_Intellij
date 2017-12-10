package servlet;

import DB.MySQLBusApp;
import classes.Bus;
import classes.Garage;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@WebServlet("/GarageServlet/*")
public class GarageServlet extends HttpServlet {

    private final static String GARAGE_LIST = "/WEB-INF/views/garage/garage.jsp";
    private final String ADD_GARAGE = "/WEB-INF/views/garage/addGarage.jsp";
    private final String UPDATE_GARAGE = "/WEB-INF/views/garage/updateGarage.jsp";
    private final Pattern ADD_PATTERN = Pattern.compile("/GarageServlet/insert");
    private final Pattern GARAGES_PATTERN = Pattern.compile("/GarageServlet");
    private final Pattern UPDATE_PATTERN = Pattern.compile("/GarageServlet/update/(\\d+)");
    private final Pattern DELETE_PATTERN = Pattern.compile("/GarageServlet/delete/(\\d+)");


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher;
        String url = request.getRequestURI();
        Matcher match;

        if ((match = ADD_PATTERN.matcher(url)).matches() == true) {
            dispatcher = request.getRequestDispatcher(ADD_GARAGE);
            dispatcher.forward(request, response);
        }
        else if ((match = UPDATE_PATTERN.matcher(url)).matches() == true) {
            int id = Integer.parseInt(match.group(1));
            Garage garage = getGarage(id);
            request.setAttribute("garage", garage);
            dispatcher = request.getRequestDispatcher(UPDATE_GARAGE);
            dispatcher.forward(request, response);
        }
        else if ((match = DELETE_PATTERN.matcher(url)).matches() == true) {
            int id = Integer.parseInt(match.group(1));
            deleteGarage(id);
            request.setAttribute("garages", getGarages());
            dispatcher = request.getRequestDispatcher(GARAGE_LIST);
            dispatcher.forward(request, response);
        }
        else if((match = GARAGES_PATTERN.matcher(url)).matches() == true){
            request.setAttribute("garages", getGarages());
            dispatcher = request.getRequestDispatcher(GARAGE_LIST);
            dispatcher.forward(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Garage garage = new Garage();
        MySQLBusApp mySQLBusApp = new MySQLBusApp();
        String url = request.getRequestURI();

        garage.setOwner(request.getParameter("owner"));
        garage.setAdress(request.getParameter("adress"));
        garage.setBuses(null);

        Matcher match;
        if((match = UPDATE_PATTERN.matcher(url)).matches() == true){
            int id = Integer.parseInt(match.group(1));
            garage.setId(id);
            try {
                mySQLBusApp.updateGarage(garage);
                RequestDispatcher view = request.getRequestDispatcher(GARAGE_LIST);
                request.setAttribute("garages", getGarages());
                view.forward(request, response);
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

        if ((match = ADD_PATTERN.matcher(url)).matches() == true) {
            try {
                mySQLBusApp.addGarage(garage);
                RequestDispatcher view = request.getRequestDispatcher(GARAGE_LIST);
                request.setAttribute("garages", getGarages());
                view.forward(request, response);
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

    }

    public ArrayList<Garage> getGarages() {
        ArrayList<Garage> garages = new ArrayList<>();
        MySQLBusApp mySQLBusApp = new MySQLBusApp();

        try {
            garages = mySQLBusApp.getAllGarages();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return garages;
    }

    public void deleteGarage(int id) {
        MySQLBusApp mySQLBusApp = new MySQLBusApp();
        try {
            mySQLBusApp.deleteGarage(id);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public Garage getGarage(int id) {
        MySQLBusApp mySQLBusApp = new MySQLBusApp();
        Garage garage = null;
        try {
            garage = mySQLBusApp.getGarage(id);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return garage;
    }


}


