package DAO;

import DB.MySQLBusApp;
import classes.Bus;
import classes.Garage;

import java.sql.SQLException;
import java.util.ArrayList;

public class Dao {
    private MySQLBusApp mySQLBusApp;

    public Dao(){
        mySQLBusApp = new MySQLBusApp();
    }

    public ArrayList<Garage> getGarages() {
        ArrayList<Garage> garages = new ArrayList<>();
        try {
            garages = mySQLBusApp.getAllGarages();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return garages;
    }

    public void addGarage(Garage garage){
        try {
            mySQLBusApp.addGarage(garage);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void updateGarage(Garage garage){
        try {
            mySQLBusApp.updateGarage(garage);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Garage getGarage(int garageId){
        Garage garage = new Garage();
        try {
            garage = mySQLBusApp.getGarage(garageId);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return garage;
    }

    public void deleteGarageWithBuses(int id){
        try {
            mySQLBusApp.deleteGarageWithBuses(id);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Bus> getBusesOfGarage(int garageId){
        ArrayList<Bus> buses = new ArrayList<>();
        try {
            buses = mySQLBusApp.getBusesOfGarage(garageId);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return buses;
    }

    public void updateBus(Bus bus){
        try {
            mySQLBusApp.updateBus(bus);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void addBus(Bus bus, int garageId){
        try {
            mySQLBusApp.addBus(bus, garageId);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Bus getBus(int id){
        Bus bus = new Bus();
        try {
            bus = mySQLBusApp.getBus(id);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return bus;
    }

    public void deleteBus(int id){
        try {
            mySQLBusApp.deleteBus(id);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
