import DB.MySQLBusApp;
import classes.Bus;
import classes.Garage;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        MySQLBusApp mySQLBusApp = new MySQLBusApp();
        int id = 3;
        Garage garage = new Garage();
        garage = mySQLBusApp.getNotEmptyGarage(33);
        System.out.println(garage);

        /*Bus bus1 = new Bus(5, "AH8790UN", LocalDate.of(1997, 11, 10), Bus.Model.DAEWOO);
        Bus bus2 = new Bus(35, "AC7809OP", LocalDate.of(1990, 9, 28), Bus.Model.ICARUS);
        Bus bus3 = new Bus(10, "KL1324LM", LocalDate.of(2003, 10, 30), Bus.Model.VOLKSWAGEN);
        Bus bus4 = new Bus(7, "QP0987CH", LocalDate.of(2009, 12, 10), Bus.Model.RENAULT);
        Bus bus5 = new Bus(11, "BJ3435FG", LocalDate.of(2017, 10, 1), Bus.Model.GEELY);
        Bus bus6 = new Bus(3, "BU3185QG", LocalDate.of(2013, 5, 7), Bus.Model.FORD);

        ArrayList<Bus> buses1 = new ArrayList<>();
        ArrayList<Bus> buses2 = new ArrayList<>();
        ArrayList<Bus> buses3 = new ArrayList<>();
        buses1.add(bus1);
        buses1.add(bus2);

        buses2.add(bus3);
        buses2.add(bus4);

        buses3.add(bus5);
        buses3.add(bus6);

        Garage garage1 = new Garage("Boulvar st. 4-A", "Rpzhko Olena Mikhailovna", buses1);
        Garage garage2 = new Garage("Golovna st. 279-B", "Serbynchuk Andriy Yevhenovich", buses2);
        Garage garage3 = new Garage("Prospect st. 29-C", "Zaburyannyy Andriy Gennadiyovich", buses3);
        mySQLBusApp.addBus(bus1,1);
        mySQLBusApp.addBus(bus2,1);
        mySQLBusApp.addBus(bus3,2);
        mySQLBusApp.addBus(bus4,2);
        mySQLBusApp.addBus(bus5,3);
        mySQLBusApp.addBus(bus6,3);

        mySQLBusApp.addGarage(garage1);
        mySQLBusApp.addGarage(garage2);
        mySQLBusApp.addGarage(garage3);

*/

    }
}
