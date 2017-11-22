import lab2.Bus;
import lab2.Garage;
import lab2.GarageApp;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.testng.Assert.assertEquals;


public class GarageAppTest {

    /*Buses*/
    Bus bus1 = new Bus(5, "AH8790UN", LocalDate.of(1997, 11, 10), Bus.Model.DAEWOO);
    Bus bus2 = new Bus(35, "AC7809OP", LocalDate.of(1990, 9, 28), Bus.Model.ICARUS);
    Bus bus3 = new Bus(10, "KL1324LM", LocalDate.of(2003, 10, 30), Bus.Model.VOLKSWAGEN);
    Bus bus4 = new Bus(7, "QP0987CH", LocalDate.of(2009, 12, 10), Bus.Model.RENAULT);
    Bus bus5 = new Bus(11, "BJ3435FG", LocalDate.of(2017, 10, 1), Bus.Model.GEELY);
    Bus bus6 = new Bus(3, "BU3185QG", LocalDate.of(2013, 5, 7), Bus.Model.FORD);
    Bus bus7 = new Bus(17, "PL7616DY", LocalDate.of(2012, 6, 15), Bus.Model.LADA);
    Bus bus8 = new Bus(8, "PM0912UI", LocalDate.of(2010, 12, 11), Bus.Model.NISSAN);
    Bus bus9 = new Bus(15, "ZA1234UR", LocalDate.of(2000, 1, 2), Bus.Model.TOYOTA);
    Bus bus10 = new Bus(5, "BL7777AT", LocalDate.of(2015, 12, 11), Bus.Model.AUDI);
    /*GrageApp*/
    GarageApp garages1 = new GarageApp();
    /*ArrayList<Bus>*/
    ArrayList<Bus> buses1 = new ArrayList<>();
    ArrayList<Bus> buses2 = new ArrayList<>();
    ArrayList<Bus> buses3 = new ArrayList<>();
    /*Garage*/
    Garage garage1 = new Garage("Golovna st. 279-A", "Serbynchuk Andriy Yevhenovich", buses1);
    Garage garage2 = new Garage("Olimpic st. 311-H", "Tomyuk Mykola Yuriyovich", buses2);
    Garage garage3 = new Garage("Stasyuka st. 8-B", "Gomenyuk Stanislav Vasilovich", buses3);
    /*ArrayList<Garage>*/
    ArrayList<Garage> garages = new ArrayList<>();


    @BeforeClass
    public void setGarages(){
        buses1.add(bus1);
        buses1.add(bus2);
        buses1.add(bus3);
        buses1.add(bus4);

        buses2.add(bus5);
        buses2.add(bus6);
        buses2.add(bus7);

        buses3.add(bus8);
        buses3.add(bus9);
        buses3.add(bus10);

        garages.add(garage1);
        garages.add(garage2);
        garages.add(garage3);

        garages1.setGarages(garages);
    }
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * This test will check, which garages can transport given amount of people.
     *
     * @return
     */

    @DataProvider
    public Object[][] garageProvider() {

        ArrayList<Garage> tmpGarage1 = new ArrayList<>();
        ArrayList<Garage> tmpGarage2 = new ArrayList<>();
        ArrayList<Garage> tmpGarage3 = new ArrayList<>();

        tmpGarage1.add(garage1);
        tmpGarage1.add(garage2);

        tmpGarage2.add(garage1);

        tmpGarage3.add(garage1);
        tmpGarage3.add(garage2);
        tmpGarage3.add(garage3);

        return new Object[][]{{garages1, 31, tmpGarage1}, {garages1, 55, tmpGarage2}, {garages1, 27, tmpGarage3}};
    }

    @Test(dataProvider = "garageProvider")
    public void testGarages(GarageApp garages, int company, ArrayList<Garage> result) {
        assertEquals(garages.ifCanTransportCompany(company), result);
    }
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
}
