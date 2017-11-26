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
    Bus bus7;
    Bus bus8;
    /*ArrayList*/
    ArrayList<Bus> buses1 = new ArrayList<>();
    ArrayList<Bus> buses2 = new ArrayList<>();
    ArrayList<Bus> buses3 = new ArrayList<>();
    ArrayList<Bus> buses4 = new ArrayList<>();
    /*Garages*/
    Garage garage1 = new Garage("Boulvar st. 4-A", "Ivanov Ivan Ivanovich", buses1);
    Garage garage2 = new Garage("Golovna st. 279-B", "Serbynchuk Andriy Yevhenovich", buses2);
    Garage garage3 = new Garage("Prospect st. 29-C", "Zaburyannyy Andriy Gennadiyovich", buses3);
    Garage garage4 = new Garage("Mizyuna st. 890-N", "Rozhik Mikhailo Ivanivich", buses4);

    @BeforeClass
    public void setInfo(){
        bus1 = new Bus(5, "AH8790UN", LocalDate.of(1997, 11, 10), Bus.Model.DAEWOO);
        bus2 = new Bus(35, "AC7809OP", LocalDate.of(1990, 9, 28), Bus.Model.ICARUS);
        bus3 = new Bus(10, "KL1324LM", LocalDate.of(2003, 10, 30), Bus.Model.VOLKSWAGEN);
        bus4 = new Bus(7, "QP0987CH", LocalDate.of(2009, 12, 10), Bus.Model.RENAULT);
        bus5 = new Bus(11, "BJ3435FG", LocalDate.of(2017, 10, 1), Bus.Model.GEELY);
        bus6 = new Bus(3, "BU3185QG", LocalDate.of(2013, 5, 7), Bus.Model.FORD);
        bus7 = new Bus(17, "PL7616DY", LocalDate.of(2012, 6, 15), Bus.Model.LADA);
        bus8 = new Bus(8, "PM0912UI", LocalDate.of(2010, 12, 11), Bus.Model.NISSAN);

        buses1.add(bus1);
        buses1.add(bus2);

        buses2.add(bus3);
        buses2.add(bus4);

        buses3.add(bus5);
        buses3.add(bus6);

        buses4.add(bus7);
        buses4.add(bus8);
    }

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @DataProvider
    public Object[][] addBusDBProvider() throws SQLException, ClassNotFoundException {
        return new Object[][]{{bus1, 1}, {bus2, 1}, {bus3, 2}, {bus4, 2}, {bus5, 3}, {bus6, 3}};
    }

    @Test(dataProvider = "addBusDBProvider")
    public void addBusDBTest(Bus bus, int garageNum) throws SQLException, ClassNotFoundException {
        new MySQLBusApp().addBus(bus, garageNum);
     }
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @DataProvider
    public Object[][] getBusDBProvider() throws SQLException, ClassNotFoundException {
        return new Object[][]{{bus4, 4}};
    }

    @Test(dataProvider = "getBusDBProvider")
    public void getBusDBTest(Bus bus, int id) throws SQLException, ClassNotFoundException {
        bus.setId(id);
        assertEquals(new MySQLBusApp().getBus(id), bus);
    }
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @DataProvider
    public Object[][] updateBusDBProvider() throws SQLException, ClassNotFoundException {
         return new Object[][]{{bus2, 2}};
    }

    @Test(dataProvider = "updateBusDBProvider")
    public void updateBusDBTest(Bus bus, int id) throws SQLException, ClassNotFoundException {
        bus.setId(id);
        bus.setIndentificationNumber("OP6578UU");
        new MySQLBusApp().updateBus(bus);
        assertEquals(new MySQLBusApp().getBus(bus.getId()).getIndentificationNumber(), "OP6578UU");
    }
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @DataProvider
    public Object[][] deleteBusDBProvider() throws SQLException, ClassNotFoundException{
        return new Object[][]{{bus1, 1}};
    }

    @Test(dataProvider = "deleteBusDBProvider")
    public void deleteBusDBTest(Bus bus, int id) throws SQLException, ClassNotFoundException {
        bus.setId(id);
        new MySQLBusApp().deleteBus(bus.getId());
    }

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @DataProvider
    public Object [][] addGarageDBProvider()throws SQLException, ClassNotFoundException{
        return new Object[][]{{garage1}, {garage2}, {garage3}, {garage4}};
    }

    @Test(dataProvider = "addGarageDBProvider")
    public void addGarageDBTest(Garage garage)throws SQLException, ClassNotFoundException{
        new MySQLBusApp().addGarage(garage);
    }
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @DataProvider
    public Object [][] getGarageDBProvider()throws SQLException, ClassNotFoundException{
        return new Object[][]{{garage2, 2}};
    }

    @Test(dataProvider = "getGarageDBProvider")
    public void getGarageDBTest(Garage garage, int id)throws SQLException, ClassNotFoundException{
        garage.setId(id);
        assertEquals(new MySQLBusApp().getGarage(id), garage);
    }
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @DataProvider
    public Object[][] updateGarageDBProvider() throws SQLException, ClassNotFoundException {
        return new Object[][]{{garage3, 3}};
    }

    @Test(dataProvider = "updateGarageDBProvider")
    public void updateGarageDBTest(Garage garage, int id) throws SQLException, ClassNotFoundException {
        garage.setId(id);
        garage.setAdress("Mizyuna st. 121-I");
        new MySQLBusApp().updateGarage(garage);
        assertEquals(new MySQLBusApp().getGarage(garage.getId()).getAdress(), "Mizyuna st. 121-I");
    }
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @DataProvider
    public Object[][] deleteGarageDBProvider() throws SQLException, ClassNotFoundException{
         return new Object[][]{{garage4, 4}};
    }

    @Test(dataProvider = "deleteGarageDBProvider")
    public void deleteGarageDBTest(Garage garage, int id) throws SQLException, ClassNotFoundException {
        garage.setId(id);
        new MySQLBusApp().deleteGarage(garage.getId());
    }
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
}