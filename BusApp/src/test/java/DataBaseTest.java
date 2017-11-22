import lab2.Bus;
import lab2.BusBuilder;
import lab2.DB.MySQLBusApp;
import lab2.Garage;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import static org.testng.Assert.assertEquals;

public class DataBaseTest {
    /*Buses*/
    Bus bus1;
    Bus bus2;
    Bus bus3;
    Bus bus4;
    Bus bus5;
    Bus bus6;
    /*ArrayList*/
    ArrayList<Bus> buses1 = new ArrayList<>();
    ArrayList<Bus> buses2 = new ArrayList<>();
    ArrayList<Bus> buses3 = new ArrayList<>();
    /*Garages*/
    Garage garage1 = new Garage("Boulvar st. 4-A", "Ivanov Ivan Ivanovich", buses1);
    Garage garage2 = new Garage("Golovna st. 279-B", "Serbynchuk Andriy Yevhenovich", buses2);
    Garage garage3 = new Garage("Prospect st. 29-C", "Zaburyannyy Andriy Gennadiyovich", buses3);

    @BeforeClass
    public void setInfo(){
        bus1 = new Bus(5, "AH8790UN", LocalDate.of(1997, 11, 10), Bus.Model.DAEWOO);
        bus2 = new Bus(35, "AC7809OP", LocalDate.of(1990, 9, 28), Bus.Model.ICARUS);
        bus3 = new Bus(10, "KL1324LM", LocalDate.of(2003, 10, 30), Bus.Model.VOLKSWAGEN);
        bus4 = new Bus(7, "QP0987CH", LocalDate.of(2009, 12, 10), Bus.Model.RENAULT);
        bus5 = new Bus(11, "BJ3435FG", LocalDate.of(2017, 10, 1), Bus.Model.GEELY);
        bus6 = new Bus(3, "BU3185QG", LocalDate.of(2013, 5, 7), Bus.Model.FORD);

        buses1.add(bus1);
        buses1.add(bus2);

        buses2.add(bus3);
        buses2.add(bus4);

        buses3.add(bus5);
        buses3.add(bus6);
    }

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @DataProvider
    public Object[][] BusDBProvider() throws SQLException, ClassNotFoundException {
        return new Object[][]{{bus1, 1}, {bus2, 1}, {bus3, 2}, {bus4, 2}, {bus5, 3}, {bus6, 3}};
    }

    @Test(dataProvider = "BusDBProvider")
    public void BusDBTest(Bus bus, int garageNum) throws SQLException, ClassNotFoundException {
        new MySQLBusApp().addBus(bus, garageNum);
        assertEquals(new MySQLBusApp().getBus(bus.getId()), bus);
        bus.setIndentificationNumber("IU9086OP");
        new MySQLBusApp().updateBus(bus);
        assertEquals(new MySQLBusApp().getBus(bus.getId()).getIndentificationNumber(),"IU9086OP");
        new MySQLBusApp().deleteBus(bus.getId());
    }
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @DataProvider
    public Object [][] addGarageDBProvider()throws SQLException, ClassNotFoundException{

    return new Object[][]{{garage1},{garage2},{garage3}};
}

    @Test(dataProvider = "addGarageDBProvider")
    public void addGarageDBTest(Garage garage)throws SQLException, ClassNotFoundException{
        new MySQLBusApp().addGarage(garage);
        assertEquals(new MySQLBusApp().getGarage(garage.getId()), garage);
        garage.setOwner("Biliychuk Andriy Serhiyovich");
        new MySQLBusApp().updateGarage(garage);
        assertEquals(new MySQLBusApp().getGarage(garage.getId()).getOwner(), "Biliychuk Andriy Serhiyovich");
        new MySQLBusApp().deleteGarage(garage.getId());
    }
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
}