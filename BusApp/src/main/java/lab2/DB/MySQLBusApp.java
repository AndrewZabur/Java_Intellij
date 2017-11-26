package lab2.DB;

import lab2.Bus;
import lab2.Garage;

import java.sql.*;
import java.util.ArrayList;

public class MySQLBusApp {

    private final static String URL = "jdbc:mysql://localhost:3306/bus_app";
    private final static String USER_NAME = "andrew";
    private final static String PASSWORD = "andrew1997";
    private final static String INSERT_INTO_BUS = "INSERT INTO bus(model, indentificationNumber, capacity, dataConstruction, garageId) " +
            "VALUES(?,?,?,?,?);";
    private final static String INSERT_INTO_GARAGE = "INSERT INTO garage(adress, owner) " +
            "VALUES(?,?)";

    public Connection getConnection()throws SQLException, ClassNotFoundException{
        Connection connection =  DriverManager.getConnection(URL, USER_NAME, PASSWORD);
        return connection;
    }

    /*public void reloadIncrement()throws SQLException, ClassNotFoundException{
        Connection connection = getConnection();
        Statement statement = connection.createStatement();
        int update = statement.executeUpdate("ALTER TABLE bus AUTO_INCREMENT = 0;");
        int update1 = statement.executeUpdate("ALTER  TABLE garage AUTO_INCREMENT = 0");
        connection.close();
    }
*/
    public void addBus(Bus bus, int garageId)throws SQLException, ClassNotFoundException{
        Connection connection = getConnection();
        Statement statement = connection.createStatement();

        PreparedStatement preparedStatement = connection.prepareStatement(INSERT_INTO_BUS);
        preparedStatement.setString(1, bus.getModel().toString());
        preparedStatement.setString(2, bus.getIndentificationNumber());
        preparedStatement.setInt(3, bus.getCapacity());
        preparedStatement.setDate(4, Date.valueOf(bus.getDataConstruction()));
        preparedStatement.setInt(5, garageId);
        preparedStatement.execute();

        ResultSet resultSet = statement.executeQuery("SELECT busId FROM bus WHERE indentificationNumber=" +
                "'"+bus.getIndentificationNumber()+"';");
        resultSet.next();
        bus.setId(resultSet.getInt("busId"));

        connection.close();
    }

    public ArrayList<Bus> getAllBuses()throws SQLException, ClassNotFoundException{
        ArrayList<Bus> buses = new ArrayList<>();
        Connection connection = getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM bus;");
        while (resultSet.next()){
            Bus bus = new Bus();
            bus.setId(resultSet.getInt("busId"));
            bus.setModel(Bus.Model.valueOf(resultSet.getString("model")));
            bus.setIndentificationNumber(resultSet.getString("indentificationNumber"));
            bus.setCapacity(resultSet.getInt("capacity"));
            bus.setDataConstruction(resultSet.getDate("dataConstruction").toLocalDate());
            buses.add(bus);
        }
        connection.close();
        return buses;
    }

    public Bus getBus(int id)throws SQLException, ClassNotFoundException{
        Connection connection = getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM bus WHERE busId = " + id + ";");
        Bus bus =  new Bus();
        while(resultSet.next()) {
            bus.setId(resultSet.getInt("busId"));
            bus.setModel(Bus.Model.valueOf(resultSet.getString("model")));
            bus.setIndentificationNumber(resultSet.getString("indentificationNumber"));
            bus.setCapacity(resultSet.getInt("capacity"));
            bus.setDataConstruction(resultSet.getDate("dataConstruction").toLocalDate());
        }
        connection.close();
        return bus;
    }

    public void updateBus(Bus bus)throws SQLException, ClassNotFoundException{
        Connection connection = getConnection();
        Statement statement = connection.createStatement();
        int updateInfo = statement.executeUpdate("UPDATE bus SET model=  '"+bus.getModel().toString()+"', " +
                "indentificationNumber= '"+bus.getIndentificationNumber()+"', capacity= '"+bus.getCapacity()+"'," +
                "dataConstruction= '"+Date.valueOf(bus.getDataConstruction())+"'" +
                " WHERE busId=" + bus.getId() +";" );

        connection.close();
    }

    public void deleteBus(int id)throws SQLException, ClassNotFoundException{
        Connection connection = getConnection();
        Statement statement = connection.createStatement();
        int deleteInfo =  statement.executeUpdate("DELETE FROM bus WHERE busId=" + id + ";");
        connection.close();
    }

    public Garage getGarage(int id)throws SQLException, ClassNotFoundException{
        Connection connection = getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM (garage INNER JOIN bus ON garage.garageId = bus.garageId)" +
        " WHERE garage.garageId=" + id + ";");
        Garage garage = new Garage();
        ArrayList<Bus> buses = new ArrayList<>();
        while (resultSet.next()) {
            Bus bus = new Bus();

            bus.setId(resultSet.getInt("busId"));
            bus.setModel(Bus.Model.valueOf(resultSet.getString("model")));
            bus.setIndentificationNumber(resultSet.getString("indentificationNumber"));
            bus.setCapacity(resultSet.getInt("capacity"));
            bus.setDataConstruction(resultSet.getDate("dataConstruction").toLocalDate());
            buses.add(bus);
            garage.setAdress(resultSet.getString("adress"));
            garage.setOwner(resultSet.getString("owner"));
        }
        garage.setBuses(buses);

        return garage;
    }

    public void addGarage(Garage garage)throws SQLException, ClassNotFoundException{
        Connection connection = getConnection();
        Statement statement = connection.createStatement();
        PreparedStatement preparedStatement = connection.prepareStatement(INSERT_INTO_GARAGE);
        preparedStatement.setString(1,garage.getAdress());
        preparedStatement.setString(2,garage.getOwner());
        preparedStatement.execute();

        ResultSet resultSet = statement.executeQuery("SELECT garageId FROM garage WHERE adress=" +
                "'"+garage.getAdress()+"';");
        resultSet.next();
        garage.setId(resultSet.getInt("garageId"));
        connection.close();
    }

    public void updateGarage(Garage garage)throws SQLException, ClassNotFoundException{
        Connection connection = getConnection();
        Statement statement = connection.createStatement();
        int updateInfo = statement.executeUpdate("UPDATE garage SET adress='"+garage.getAdress()+"', " +
                "owner='"+garage.getOwner()+"'" + "WHERE garageId=" + garage.getId() + ";");
        connection.close();
    }

    public void deleteGarage(int id)throws SQLException, ClassNotFoundException{
        Connection connection = getConnection();
        Statement statement = connection.createStatement();
        int deleteInfo = statement.executeUpdate("DELETE FROM garage WHERE garageId =" + id + ";");
        connection.close();
    }

}
