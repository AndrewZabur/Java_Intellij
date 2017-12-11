package DB;

import classes.Bus;
import classes.Garage;

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
        Class.forName("com.mysql.jdbc.Driver");
        Connection connection =  DriverManager.getConnection(URL, USER_NAME, PASSWORD);
        return connection;
    }

    public void reloadIncrement()throws SQLException, ClassNotFoundException{
        Connection connection = getConnection();
        Statement statement = connection.createStatement();
        int update = statement.executeUpdate("ALTER TABLE bus AUTO_INCREMENT = 0;");
        int update1 = statement.executeUpdate("ALTER  TABLE garage AUTO_INCREMENT = 0");
        connection.close();
    }

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

    /*public ArrayList<Bus> getAllBuses()throws SQLException, ClassNotFoundException{
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
*/
    public ArrayList<Bus> getBusesOfGarage(int garageId)throws SQLException, ClassNotFoundException{
        ArrayList<Bus> buses = new ArrayList<>();
        Connection connection = getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM bus WHERE bus.garageId=" +garageId+";");
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

    public int getBusIdByIndentificationNumber(String busIndentificationNumber)throws SQLException, ClassNotFoundException{
        Connection connection = getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT bus.busId FROM bus WHERE bus.indentificationNumber LIKE " +
                "'" + busIndentificationNumber + "%'" +";");
        resultSet.next();
        return resultSet.getInt("busId");
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

    public void deleteBusesOfGarage(int garageId)throws SQLException, ClassNotFoundException{
        Connection connection = getConnection();
        Statement statement = connection.createStatement();
        int deleteInfo = statement.executeUpdate("DELETE FROM bus WHERE bus.garageId="+garageId+";");
        connection.close();
    }

    public int getGarageIdByAdress(String garageAdress)throws SQLException, ClassNotFoundException{
        Connection connection = getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT garage.garageId FROM garage WHERE garage.adress LIKE " +
                "'" +garageAdress + "%'" +";");
        resultSet.next();
        return resultSet.getInt("garageId");
    }


    public ArrayList<Garage> getAllGarages()throws SQLException, ClassNotFoundException{
        ArrayList<Garage> garages = new ArrayList<>();
        Connection connection = getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM garage;");
        while (resultSet.next()){
            Garage garage = new Garage();
            garage.setId(resultSet.getInt("garageId"));
            garage.setAdress(resultSet.getString("adress"));
            garage.setOwner(resultSet.getString("owner"));

            garages.add(garage);
        }
        connection.close();
        return garages;
    }

    public Garage getGarage(int id)throws SQLException, ClassNotFoundException{
        Connection connection = getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM garage" +
                " WHERE garage.garageId=" + id + ";");

        Garage garage = new Garage();
        ArrayList<Bus> buses = new ArrayList<>();
        while (resultSet.next()) {
            garage.setId(resultSet.getInt("garageId"));
            garage.setAdress(resultSet.getString("adress"));
            garage.setOwner(resultSet.getString("owner"));
        }
        garage.setBuses(buses);
        connection.close();
        return garage;
    }

    public boolean checkUniq(String IndentificationNumber)throws SQLException, ClassNotFoundException{
        Connection connection = getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT bus.indentificationNumber FROM bus WHERE bus.indentificationNumber LIKE " +
                "'" + IndentificationNumber + "%'" +";");
        return resultSet.next();


    }


    public Garage getNotEmptyGarage(int id)throws SQLException, ClassNotFoundException{
        Connection connection = getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM (garage LEFT JOIN bus ON bus.garageId=garage.garageId)" +
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

            garage.setId(resultSet.getInt("garageId"));
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

        ResultSet resultSet = statement.executeQuery("SELECT garage.garageId FROM garage WHERE adress=" +
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

    public void deleteGarageWithBuses(int id)throws SQLException, ClassNotFoundException{
        Connection connection = getConnection();
        Statement statement = connection.createStatement();
        int deleteInfo = statement.executeUpdate("DELETE FROM garage WHERE garageId =" + id + ";");
        deleteBusesOfGarage(id);
        connection.close();
    }
    public boolean garageTransportPeople(int garageId, int company)throws SQLException, ClassNotFoundException{
        Connection connection = getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT SUM(bus.capacity) FROM bus WHERE bus.garageId=" +
                "'"+ garageId +"'" + ";");
        resultSet.next();
        int result = resultSet.getInt(1);

        return result >= company;
    }


}
