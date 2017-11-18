package lab2;
import lab2.DB.MySQLBusApp;

import java.io.*;
import java.sql.SQLException;
import java.time.LocalDate;


public class BusApp {

    public static void main(String[] args) throws IOException, SQLException, ClassNotFoundException, NullPointerException{

        MySQLBusApp obj = new MySQLBusApp();
        obj.reloadIncrement();
        Bus obj2 = new Bus(40, "AC7809OP", LocalDate.of(1990, 9, 28), Bus.Model.ICARUS);
        obj.addBus(obj2);

    }

}
