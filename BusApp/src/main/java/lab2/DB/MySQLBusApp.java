package lab2.DB;

import lab2.Bus;
import lab2.BusBuilder;

import java.sql.*;

public class MySQLBusApp {

    private final static String URL = "jdbc:mysql://localhost:3306/bus_app";
    private final static String USER_NAME = "andrew";
    private final static String PASSWORD = "andrew1997";

    public Connection getNewConnection()throws SQLException, ClassNotFoundException{

        Connection connection =  DriverManager.getConnection(URL, USER_NAME, PASSWORD);
        return connection;
    }

    public void reloadIncrement()throws SQLException, ClassNotFoundException{

        Connection connection = getNewConnection();
        Statement statement = connection.createStatement();
        int update = statement.executeUpdate("ALTER TABLE bus AUTO_INCREMENT = 0;");
        connection.close();
    }


    public Bus getBus(int id)throws SQLException, ClassNotFoundException{
        Connection connection = getNewConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM bus WHERE busId = " + id + ";");
        Bus bus =  new Bus();
        while(resultSet.next()) {
             bus = new BusBuilder().setModel(Bus.Model.valueOf(resultSet.getString("model")))
                    .setIdentificationNumder(resultSet.getString("indentificationNumber"))
                    .setCapacity(resultSet.getInt("capacity"))
                    .setDataConstruction(resultSet.getDate("dataConstruction").toLocalDate()).build();

        }
        connection.close();
        return bus;
    }

    public void addBus(Bus bus)throws SQLException, ClassNotFoundException{
        Connection connection = getNewConnection();

        Statement statement = connection.createStatement();
        int newInfo = statement.executeUpdate("INSERT INTO bus(model, indentificationNumber, capacity, dataConstruction) " +
                "VALUES('"+bus.getModel().toString()+"', '"+bus.getIndentificationNumber() +"', '"+bus.getCapacity()+"', " +
                "'"+Date.valueOf(bus.getDataConstruction())+"') ;");
        connection.close();
    }

    public void updateBus(Bus bus, int id)throws SQLException, ClassNotFoundException{
        Connection connection = getNewConnection();
        Statement statement = connection.createStatement();
        int updateInfo = statement.executeUpdate("UPDATE bus SET model=  '"+bus.getModel().toString()+"', " +
                "indentificationNumber= '"+bus.getIndentificationNumber()+"', capacity= '"+bus.getCapacity()+"'," +
                "dataConstruction= '"+Date.valueOf(bus.getDataConstruction())+"' WHERE busId=" + id +";" );
        connection.close();
    }

    public void deleteBus(int id)throws SQLException, ClassNotFoundException{
        Connection connection = getNewConnection();
        Statement statement = connection.createStatement();
        int deleteInfo =  statement.executeUpdate("DELETE FROM bus WHERE busId=" + id + ";");
        connection.close();
    }
}
