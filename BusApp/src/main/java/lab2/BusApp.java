package lab2;

import lab2.DB.MySQLBusApp;

import java.io.*;
import java.sql.SQLException;

public class BusApp {

    public static void main(String[] args) throws IOException, SQLException, ClassNotFoundException, NullPointerException{
        MySQLBusApp mySQLBusApp = new MySQLBusApp();
        mySQLBusApp.reloadIncrement();
    }
}
