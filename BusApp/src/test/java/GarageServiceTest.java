import lab2.Bus;
import lab2.Garage;
import lab2.GarageApp;
import lab2.GarageService;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.testng.Assert.assertEquals;

public class GarageServiceTest {
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
    /*GarageServices*/
    GarageService garageService1 = new GarageService(garage1);
    GarageService garageService2 = new GarageService(garage2);
    GarageService garageService3 = new GarageService(garage3);
    @BeforeClass
    public void setBuses(){
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
    }
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @DataProvider
    public Object[][] garageCapacityProvider() {
        return new Object[][]{{garageService1, 57}, {garageService2, 31}, {garageService3, 28}};
    }

    @Test(dataProvider = "garageCapacityProvider")
    public void garageCapacityTest(GarageService garageService, int check) {
        assertEquals(garageService.garageCapacity(), check);
    }
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
@DataProvider
public Object[][] garageServiceCompanyProvider() {
    return new Object[][]{{garageService1, 55, true}, {garageService2, 32, false}, {garageService3, 30, false}};
}

    @Test(dataProvider = "garageServiceCompanyProvider")
    public void garageServiceCompanyTest(GarageService garageService, int company, boolean result) {
        assertEquals(garageService.companyOfPeople(company), result);
    }
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @DataProvider
    public Object[][] garageServiceMaxProvider() {
        return new Object[][]{{garageService1, 35}, {garageService2, 17}, {garageService3, 15}};
    }

    @Test(dataProvider = "garageServiceMaxProvider")
    public void garageServiceMaxTest(GarageService garageService, int max) {
        assertEquals(garageService.findBiggestCapacity(), max);
    }
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @DataProvider
    public Object[][] garageServiceMinProvider() {
        return new Object[][]{{garageService1, 5}, {garageService2, 3}, {garageService3, 5}};
    }

    @Test(dataProvider = "garageServiceMinProvider")
    public void garageServiceMinTest(GarageService garageService, int min) {
        assertEquals(garageService.findSmallestCapacity(), min);
    }
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
}
