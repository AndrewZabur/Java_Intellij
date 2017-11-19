package lab2.DB;

import lab2.Bus;
import lab2.BusBuilder;
import lab2.Garage;

import java.sql.*;
import java.util.ArrayList;

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


    public ArrayList<Bus> getBusForGarage(int id)throws SQLException, ClassNotFoundException{
        Connection connection = getNewConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM bus WHERE garageNum = " + id + ";");
        ArrayList<Bus> buses = new ArrayList<>();
        Bus bus;
        while(resultSet.next()) {
            bus = new BusBuilder().setModel(Bus.Model.valueOf(resultSet.getString("model")))
                    .setIdentificationNumder(resultSet.getString("indentificationNumber"))
                    .setCapacity(resultSet.getInt("capacity"))
                    .setDataConstruction(resultSet.getDate("dataConstruction").toLocalDate()).build();
            buses.add(bus);
        }
        connection.close();
        return buses;
    }

    public void addBus(Bus bus, int garageId)throws SQLException, ClassNotFoundException{
        Connection connection = getNewConnection();

        Statement statement = connection.createStatement();
        int newInfo = statement.executeUpdate("INSERT INTO bus(model, indentificationNumber, capacity, dataConstruction, garageNum) " +
                "VALUES('"+bus.getModel().toString()+"', '"+bus.getIndentificationNumber() +"', '"+bus.getCapacity()+"', " +
                "'"+Date.valueOf(bus.getDataConstruction())+"', '"+ garageId +"') ;");
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

    public Garage getGarage(int id)throws SQLException, ClassNotFoundException{
        Connection connection = getNewConnection();
        Statement statement = connection.createStatement();
        Statement statement1 = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM garage WHERE garageId=" + id + ";");
        ResultSet resultSet1 = statement1.executeQuery("SELECT * FROM bus WHERE garageNum="+ id +";");
        Garage garage = new Garage();
        while (resultSet1.next()) {
            garage.setBuses(getBusForGarage(id));
        }
        while (resultSet.next()) {
            garage.setAdress(resultSet.getString("adress"));
            garage.setOwner(resultSet.getString("owner"));
        }

        return garage;

    }

    public void addGarage(Garage garage)throws SQLException, ClassNotFoundException{
        Connection connection = getNewConnection();

        Statement statement = connection.createStatement();
        int newInfo = statement.executeUpdate("INSERT INTO garage(adress, owner) " +
                "VALUES ('"+garage.getAdress()+"', '"+garage.getOwner()+"')");
        connection.close();
    }

    public void updateGarage(Garage garage, int id)throws SQLException, ClassNotFoundException{
        Connection connection = getNewConnection();
        Statement statement = connection.createStatement();
        int updateInfo = statement.executeUpdate("UPDATE garage SET adress='"+garage.getAdress()+"', " +
                "owner='"+garage.getOwner()+"'" + "WHERE garageId=" + id + ";");
        connection.close();
    }

    public void deleteGarage(int id)throws SQLException, ClassNotFoundException{
        Connection connection = getNewConnection();
        Statement statement = connection.createStatement();
        int deleteInfo = statement.executeUpdate("DELETE FROM garage WHERE garageId =" + id + ";");
        connection.close();
    }

}
