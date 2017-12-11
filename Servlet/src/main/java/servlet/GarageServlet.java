package servlet;

import DAO.Dao;
import classes.Bus;
import classes.Garage;
import org.testng.mustache.Model;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@WebServlet("/GarageServlet/*")
public class GarageServlet extends HttpServlet {

    private Dao dao;
    private final static String GARAGE_LIST = "/WEB-INF/views/garage/garage.jsp";
    private final String ADD_GARAGE = "/WEB-INF/views/garage/addGarage.jsp";
    private final String UPDATE_GARAGE = "/WEB-INF/views/garage/updateGarage.jsp";
    private final Pattern ADD_PATTERN = Pattern.compile("/GarageServlet/insert");
    private final Pattern GARAGES_PATTERN = Pattern.compile("/GarageServlet");
    private final Pattern UPDATE_PATTERN = Pattern.compile("/GarageServlet/update/(\\d+)");
    private final Pattern DELETE_PATTERN = Pattern.compile("/GarageServlet/delete/(\\d+)");

    public GarageServlet(){
        dao = new Dao();
    }
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
            Garage garage = dao.getGarage(id);
            request.setAttribute("garage", garage);
            dispatcher = request.getRequestDispatcher(UPDATE_GARAGE);
            dispatcher.forward(request, response);
        }
        else if ((match = DELETE_PATTERN.matcher(url)).matches() == true) {
            int id = Integer.parseInt(match.group(1));
            dao.deleteGarageWithBuses(id);
            request.setAttribute("garages", dao.getGarages());
            dispatcher = request.getRequestDispatcher(GARAGE_LIST);
            dispatcher.forward(request, response);
        }
        else if((match = GARAGES_PATTERN.matcher(url)).matches() == true){
            request.setAttribute("garages", dao.getGarages());
            dispatcher = request.getRequestDispatcher(GARAGE_LIST);
            dispatcher.forward(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Garage garage = new Garage();
        String url = request.getRequestURI();

        garage.setOwner(request.getParameter("owner"));
        garage.setAdress(request.getParameter("adress"));
        garage.setBuses(null);

        Matcher match;
        if((match = UPDATE_PATTERN.matcher(url)).matches() == true){
            int id = Integer.parseInt(match.group(1));
            garage.setId(id);
                dao.updateGarage(garage);
                RequestDispatcher view = request.getRequestDispatcher(GARAGE_LIST);
                request.setAttribute("garages", dao.getGarages());
                view.forward(request, response);

        }

        if ((match = ADD_PATTERN.matcher(url)).matches() == true) {
                dao.addGarage(garage);
                RequestDispatcher view = request.getRequestDispatcher(GARAGE_LIST);
                request.setAttribute("garages", dao.getGarages());
                view.forward(request, response);

        }

    }
}


