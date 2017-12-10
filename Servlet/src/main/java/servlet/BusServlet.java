package servlet;

import DAO.Dao;
import DB.MySQLBusApp;
import classes.Bus;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@WebServlet("/BusServlet/*")
public class BusServlet extends HttpServlet {
    private Dao dao;
    private final String GET_BUSES_OF_GARAGE = "/WEB-INF/views/bus/bus.jsp";
    private final String ADD_BUS = "/WEB-INF/views/bus/addBus.jsp";
    private final String UPDATE_BUS = "/WEB-INF/views/bus/updateBus.jsp";
    private final Pattern ADD_BUS_PATTERN = Pattern.compile("/BusServlet/insert/(\\d+)");
    private final Pattern BUSES_LIST_PATTERN = Pattern.compile("/BusServlet/(\\d+)");
    private final Pattern UPDATE_BUS_PATTERN = Pattern.compile("/BusServlet/update/(\\d+)/(\\d+)");
    private final Pattern DELETE_BUS_PATTERN = Pattern.compile("/BusServlet/delete/(\\d+)/(\\d+)");

    public BusServlet(){
        dao = new Dao();
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        RequestDispatcher dispatcher;
        String url = request.getRequestURI();
        Matcher match;


        if ((match = ADD_BUS_PATTERN.matcher(url)).matches() == true) {
            int id = Integer.parseInt(match.group(1));
            dispatcher = request.getRequestDispatcher(ADD_BUS);
            request.setAttribute("garage", dao.getGarage(id));
            dispatcher.forward(request, response);
        }
        else if ((match = UPDATE_BUS_PATTERN.matcher(url)).matches() == true) {
            int id = Integer.parseInt(match.group(1));
            int garageId = Integer.parseInt(match.group(2));
            Bus bus = dao.getBus(id);
            request.setAttribute("bus", bus);
            request.setAttribute("garage", dao.getGarage(garageId));
            dispatcher = request.getRequestDispatcher(UPDATE_BUS);
            dispatcher.forward(request, response);

        }
        else if ((match = DELETE_BUS_PATTERN.matcher(url)).matches() == true) {
            int id = Integer.parseInt(match.group(1));
            int garageId = Integer.parseInt(match.group(2));
            dao.deleteBus(id);
            request.setAttribute("buses", dao.getBusesOfGarage(garageId));
            request.setAttribute("garage", dao.getGarage(garageId));
            dispatcher = request.getRequestDispatcher(GET_BUSES_OF_GARAGE);
            dispatcher.forward(request, response);
        }
        else if((match = BUSES_LIST_PATTERN.matcher(url)).matches() == true){
            int id = Integer.parseInt(match.group(1));
            request.setAttribute("garage", dao.getGarage(id));
            request.setAttribute("buses", dao.getBusesOfGarage(id));
            dispatcher = request.getRequestDispatcher(GET_BUSES_OF_GARAGE);
            dispatcher.forward(request, response);

        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        MySQLBusApp mySQLBusApp = new MySQLBusApp();
        String url = request.getRequestURI();
        Bus bus = new Bus();
        bus.setModel( Bus.Model.valueOf(request.getParameter( "model" )) );
        bus.setIndentificationNumber( request.getParameter( "indentificationNumber" ) );
        bus.setDataConstruction( LocalDate.parse(request.getParameter( "dataConstruction" )) );
        bus.setCapacity( Integer.parseInt( request.getParameter( "capacity" ) ) );

        Matcher match;
        if((match = UPDATE_BUS_PATTERN.matcher(url)).matches() == true) {
            int id = Integer.parseInt(match.group(1));
            int garageId = Integer.parseInt(match.group(2));
            bus.setId(id);
                dao.updateBus(bus);
                RequestDispatcher dispatcher = request.getRequestDispatcher( GET_BUSES_OF_GARAGE );
                request.setAttribute("buses", dao.getBusesOfGarage(garageId));
                request.setAttribute("garage", dao.getGarage(garageId));
                dispatcher.forward(request, response);

        }

        if((match = ADD_BUS_PATTERN.matcher(url)).matches() == true){
            int garageId = Integer.parseInt(match.group(1));

                dao.addBus(bus, garageId);
                RequestDispatcher dispatcher = request.getRequestDispatcher( GET_BUSES_OF_GARAGE );
                request.setAttribute("buses", dao.getBusesOfGarage(garageId));
                request.setAttribute("garage", dao.getGarage(garageId));
                dispatcher.forward(request, response);
            }
        }

    }

