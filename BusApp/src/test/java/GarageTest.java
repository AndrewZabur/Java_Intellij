import lab2.Bus;
import lab2.Garage;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.testng.Assert.assertEquals;

public class GarageTest {

    /*Buses*/
    Bus bus1 = new Bus(35, "BU3185QG", LocalDate.of(2013, 5, 7), Bus.Model.FORD);
    Bus bus2 = new Bus(17, "PL7616DY", LocalDate.of(2012, 6, 15), Bus.Model.LADA);
    Bus bus3 = new Bus(8, "PM0912UI", LocalDate.of(2010, 12, 11), Bus.Model.NISSAN);
    Bus bus4 = new Bus(15, "ZA1234UR", LocalDate.of(2000, 1, 2), Bus.Model.TOYOTA);
    Bus bus5 = new Bus(10, "BL7777AT", LocalDate.of(2015, 12, 11), Bus.Model.AUDI);
    /*Garage*/
    Garage garage = new Garage();
    /*ArrayList<Bus>*/
    ArrayList<Bus> buses = new ArrayList<>();
     @BeforeClass
     public void setBuses() {
         buses.add(bus1);
         buses.add(bus2);
         buses.add(bus3);
         buses.add(bus4);
         buses.add(bus5);

         garage.setBuses(buses);
     }
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * This test will check, whether the bus from the garage can transport this people.
     *
     * @return
     */

    @DataProvider
    public Object[][] garageInfoProvider() {

        ArrayList<Bus> result1 = new ArrayList<>();
        ArrayList<Bus> result2 = new ArrayList<>();
        ArrayList<Bus> result3 = new ArrayList<>();

        result1.add(bus1);

        result2.add(bus1);
        result2.add(bus2);
        result2.add(bus4);
        result2.add(bus5);

        result3.add(bus1);
        result3.add(bus2);

        return new Object[][]{{garage, 33, result1}, {garage, 10, result2}, {garage, 16, result3}};

    }

    @Test(dataProvider = "garageInfoProvider")
    public void testGarageInfo(Garage garage, int passengers, ArrayList<Bus> result) {
        assertEquals(garage.garageInfo(passengers), result);
    }
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * This test will check, whether the company of people will fit
     * to the all cars which are in this garage.
     *
     * @return
     */

    @DataProvider
    public Object[][] companyInfoProvider() {
        return new Object[][]{{garage, 59, true}, {garage, 100, false}, {garage, 85, true}, {garage, 86, false}};
    }

    @Test(dataProvider = "companyInfoProvider")
    public void testInfoCompany(Garage garage, int company, boolean result) {
        assertEquals(garage.companyOfPeople(company), result);
    }
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
}
