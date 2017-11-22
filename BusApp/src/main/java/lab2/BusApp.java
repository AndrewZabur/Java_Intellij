package lab2;

import lab2.DB.MySQLBusApp;

import java.io.*;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;


public class BusApp {

    public static void main(String[] args) throws IOException, SQLException, ClassNotFoundException, NullPointerException{

        /*MySQLBusApp mySQLBusApp = new MySQLBusApp();
        Bus bus1 = new Bus(5, "AH8790UN", LocalDate.of(1997, 11, 10), Bus.Model.DAEWOO);
        Bus bus2 = new Bus(35, "AC7809OP", LocalDate.of(1990, 9, 28), Bus.Model.ICARUS);
        Bus bus3 = new Bus(10, "KL1324LM", LocalDate.of(2003, 10, 30), Bus.Model.VOLKSWAGEN);
        Bus bus4 = new Bus(7, "QP0987CH", LocalDate.of(2009, 12, 10), Bus.Model.RENAULT);
        Bus bus5 = new Bus(11, "BJ3435FG", LocalDate.of(2017, 10, 1), Bus.Model.GEELY);
        *//*ArrayList<Bus>*//*
        ArrayList<Bus> buses1 = new ArrayList<>();
        ArrayList<Bus> buses2 = new ArrayList<>();
        ArrayList<Bus> buses3 = new ArrayList<>();
        *//*Garage*//*
        buses1.add(bus1);
        buses1.add(bus2);
        buses2.add(bus3);
        buses2.add(bus4);
        buses3.add(bus5);
        Garage garage1 = new Garage("Golovna st. 279-A", "Serbynchuk Andriy Yevhenovich", buses1);
        Garage garage2 = new Garage("Olimpic st. 311-H", "Tomyuk Mykola Yuriyovich", buses2);
        Garage garage3 = new Garage("Stasyuka st. 8-B", "Gomenyuk Stanislav Vasilovich", buses3);
        mySQLBusApp.addGarage(garage1);
        mySQLBusApp.addGarage(garage2);
        mySQLBusApp.addGarage(garage3);
        mySQLBusApp.addBus(bus1, garage1.getId());
        mySQLBusApp.addBus(bus2, garage1.getId());
        mySQLBusApp.addBus(bus3, garage2.getId());
        mySQLBusApp.addBus(bus4, garage2.getId());
        mySQLBusApp.addBus(bus5, garage3.getId());
*/
    }
}
