package lab2.DB;

import lab2.Bus;
import lab2.Garage;

import java.sql.*;
import java.util.ArrayList;

public class MySQLBusApp {

    private final static String URL = "jdbc:mysql://localhost:3306/bus_app";
    private final static String USER_NAME = "andrew";
    private final static String PASSWORD = "andrew1997";

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
    }*/

    public void addBus(Bus bus, int garageId)throws SQLException, ClassNotFoundException{
        Connection connection = getConnection();
        Statement statement = connection.createStatement();
        int newInfo = statement.executeUpdate("INSERT INTO bus(model, indentificationNumber, capacity, dataConstruction, garageId) " +
                "VALUES('"+bus.getModel().toString()+"', '"+bus.getIndentificationNumber() +"', '"+bus.getCapacity()+"', " +
                "'"+Date.valueOf(bus.getDataConstruction())+"', '"+ garageId +"') ;");

        ResultSet resultSet = statement.executeQuery("SELECT busId FROM bus WHERE indentificationNumber=" +
                "'"+bus.getIndentificationNumber()+"';");
        resultSet.next();
        bus.setId(resultSet.getInt("busId"));

        connection.close();
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


    public ArrayList<Bus> getBusForGarage(int id)throws SQLException, ClassNotFoundException{
        Connection connection = getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM bus WHERE garageNum = " + id + ";");
        ArrayList<Bus> buses = new ArrayList<>();
        Bus bus = new Bus();
        while(resultSet.next()) {
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

    public void updateBus(Bus bus)throws SQLException, ClassNotFoundException{
        Connection connection = getConnection();
        Statement statement = connection.createStatement();
        int updateInfo = statement.executeUpdate("UPDATE bus SET model=  '"+bus.getModel().toString()+"', " +
                "indentificationNumber= '"+bus.getIndentificationNumber()+"', capacity= '"+bus.getCapacity()+"'," +
                "dataConstruction= '"+Date.valueOf(bus.getDataConstruction())+"' WHERE busId=" + bus.getId() +";" );

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
        Statement statement1 = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM garage WHERE garageId=" + id + ";");
        ResultSet resultSet1 = statement1.executeQuery("SELECT * FROM bus WHERE garageId="+ id +";");
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
        Connection connection = getConnection();
        Statement statement = connection.createStatement();
        int newInfo = statement.executeUpdate("INSERT INTO garage(adress, owner) " +
                "VALUES ('"+garage.getAdress()+"', '"+garage.getOwner()+"')");

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
