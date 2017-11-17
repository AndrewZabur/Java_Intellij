package test2;

import lab2.GarageService;
import lab2.serialize.*;
import org.testng.annotations.Test;

import lab2.Bus;
import lab2.Garage;
import lab2.GarageApp;
import lab2.Bus.Model;
import static org.testng.Assert.assertEquals;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;

import org.testng.annotations.DataProvider;

public class BusAppTest {
    /*Buses*/
    Bus obj1 = new Bus(5, "AH8790UN", LocalDate.of(1997, 11, 10), Model.DAEWOO);
    Bus obj2 = new Bus(35, "AC7809OP", LocalDate.of(1990, 9, 28), Model.ICARUS);
    Bus obj3 = new Bus(10, "KL1324LM", LocalDate.of(2003, 10, 30), Model.VOLKSWAGEN);
    Bus obj4 = new Bus(7, "QP0987CH", LocalDate.of(2009, 12, 10), Model.RENAULT);
    Bus obj5 = new Bus(11, "BJ3435FG", LocalDate.of(2017, 10, 1), Model.GEELY);
    Bus obj6 = new Bus(3, "BU3185QG", LocalDate.of(2013, 5, 7), Model.FORD);
    Bus obj7 = new Bus(17, "PL7616DY", LocalDate.of(2012, 6, 15), Model.LADA);
    Bus obj8 = new Bus(8, "PM0912UI", LocalDate.of(2010, 12, 11), Model.NISSAN);
    Bus obj9 = new Bus(15, "ZA1234UR", LocalDate.of(2000, 1, 2), Model.TOYOTA);
    Bus obj10 = new Bus(5, "BL7777AT", LocalDate.of(2015, 12, 11), Model.AUDI);
    /*Garage*/
    Garage garage = new Garage();
    /*GrageApp*/
    GarageApp garages1 = new GarageApp();

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * This test will check the capacity of the bus.
     * @return
     */
    @DataProvider
    public Object[][] capacityProvider(){
        return new Object[][]{ {1, true}, {3, true}, {519, false}, {100, false}, {5, true}};
    }

    @Test(dataProvider = "capacityProvider")
    public void testCapacity(int p1, boolean p2){

        assertEquals(new Bus().checkOfTheSize(p1), p2);
    }

//////////////////////////////////////////////////////////////////////////

    /**
     *This test will check, whether the bus from the garage can transport this people.
     * @return
     */

    @DataProvider
    public Object[][] garageInfoProvider() {
        ArrayList<Bus> buses = new ArrayList<Bus>();
        buses.add(obj1);
        buses.add(obj2);
        buses.add(obj3);
        buses.add(obj4);
        buses.add(obj5);

        ArrayList<Bus> result1 = new ArrayList<Bus>();
        ArrayList<Bus> result2 = new ArrayList<Bus>();
        ArrayList<Bus> result3 = new ArrayList<Bus>();

        result1.add(obj2);

        result2.add(obj2);
        result2.add(obj3);
        result2.add(obj5);

        result3.add(obj2);
        result3.add(obj3);
        result3.add(obj4);
        result3.add(obj5);

        garage.setBuses(buses);

        return new Object[][] { {garage,33, result1}, {garage, 10, result2}, {garage,6, result3} };

    }

    @Test(dataProvider = "garageInfoProvider")
    public void testGarageInfo(Garage garage, int passengers, ArrayList<Bus> result) {

        result.equals(garage.garageInfo(passengers));

    }
//////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     *	This test will check, whether the company of people will fit
     *to the all cars which are in this garage.
     * @return
     */

    @DataProvider
    public Object[][] companyInfoProvider() {
        ArrayList<Bus> buses = new ArrayList<Bus>();
        buses.add(obj1);
        buses.add(obj2);
        buses.add(obj3);
        buses.add(obj4);
        buses.add(obj5);

        garage.setBuses(buses);

        return new Object[][] { {garage, 59, true}, {garage, 100, false}, {garage, 68, true} };
    }

    @Test(dataProvider = "companyInfoProvider")
    public void testInfoCompany(Garage garage, int company, boolean result) {
        assertEquals(garage.companyOfPeople(company),result);
    }

/////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * This test will check, which garages can transport given amount of people.
     * @return
     */

    @DataProvider
    public Object[][] garageProvider() {

        ArrayList<Bus> bus1 = new ArrayList<Bus>();
        ArrayList<Bus> bus2 = new ArrayList<Bus>();
        ArrayList<Bus> bus3 = new ArrayList<Bus>();

        bus1.add(obj1);
        bus1.add(obj2);
        bus1.add(obj3);
        bus1.add(obj4);

        bus2.add(obj5);
        bus2.add(obj6);
        bus2.add(obj7);

        bus3.add(obj8);
        bus3.add(obj9);
        bus3.add(obj10);

        Garage gar1 = new Garage("Golovna st. 279(A)", "Serbynchuk Andriy Yevhenovich", bus1);
        Garage gar2 = new Garage("Olimpic st. 311(H)", "Tomyuk Mykola Yuriyovich", bus2);
        Garage gar3 = new Garage("Stasyuka st. 8(B)", "Gomenyuk Stanislav Vasilovich", bus3);


        ArrayList<Garage> garages = new ArrayList<Garage>();

        garages.add(gar1);
        garages.add(gar2);
        garages.add(gar3);

        ArrayList<Garage> tmpGarage1 = new ArrayList<Garage>();

        ArrayList<Garage> tmpGarage2 = new ArrayList<Garage>();

        ArrayList<Garage> tmpGarage3 = new ArrayList<Garage>();

        tmpGarage1.add(gar1);
        tmpGarage1.add(gar2);

        tmpGarage2.add(gar1);

        tmpGarage3.add(gar1);
        tmpGarage3.add(gar2);
        tmpGarage3.add(gar3);

        garages1.setGarages(garages);

        return new Object[][] { {garages1, 31, tmpGarage1}, {garages1, 55, tmpGarage2}, {garages1, 27, tmpGarage3 } };
    }

    @Test(dataProvider = "garageProvider")
    public void testGarages(GarageApp garages, int company, ArrayList<Garage> result) {

        result.equals(garages.ifCanTransportCompany(company));
    }
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * This test will check whether the identification number of the bus is correct.
     * @return
     */

    @DataProvider
    public Object[][] regularExpProvider() {
        return new Object[][] { { "AH8790UN",  true}, {"BU3185QG", true}, {"ZA5334BUR", false}, {"QWE1243UT", false}, {"ER09871PO", false}};
    }

    @Test(dataProvider = "regularExpProvider")
    public void reagularExpTest(String input, boolean check) {
        assertEquals(new Bus().regularExpIdentificationNumber(input), check);
    }
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @DataProvider
    public Object[][] garageCapacityProvider(){

        ArrayList<Bus> bus1 = new ArrayList<Bus>();
        ArrayList<Bus> bus2 = new ArrayList<Bus>();
        ArrayList<Bus> bus3 = new ArrayList<Bus>();

        bus1.add(obj1);
        bus1.add(obj2);
        bus1.add(obj3);
        bus1.add(obj4);

        bus2.add(obj5);
        bus2.add(obj6);
        bus2.add(obj7);

        bus3.add(obj8);
        bus3.add(obj9);
        bus3.add(obj10);

        Garage gar1 = new Garage("Golovna st. 279(A)", "Serbynchuk Andriy Yevhenovich", bus1);
        Garage gar2 = new Garage("Olimpic st. 311(H)", "Tomyuk Mykola Yuriyovich", bus2);
        Garage gar3 = new Garage("Stasyuka st. 8(B)", "Gomenyuk Stanislav Vasilovich", bus3);

        GarageService gars1 = new GarageService(gar1);
        GarageService gars2 = new GarageService(gar2);
        GarageService gars3 = new GarageService(gar3);

        return new Object[][]{{gars1, 57},{gars2, 31},{gars3, 28}};
    }

    @Test(dataProvider = "garageCapacityProvider")
    public void garageCapacityTest(GarageService garageService, int  check) {
        assertEquals(garageService.garageCapacity(), check);
    }
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @DataProvider
    public Object[][] garageServiceCompanyProvider(){
    ArrayList<Bus> bus1 = new ArrayList<Bus>();
    ArrayList<Bus> bus2 = new ArrayList<Bus>();
    ArrayList<Bus> bus3 = new ArrayList<Bus>();

    bus1.add(obj1);
    bus1.add(obj2);
    bus1.add(obj3);
    bus1.add(obj4);

    bus2.add(obj5);
    bus2.add(obj6);
    bus2.add(obj7);

    bus3.add(obj8);
    bus3.add(obj9);
    bus3.add(obj10);

    Garage gar1 = new Garage("Golovna st. 279(A)", "Serbynchuk Andriy Yevhenovich", bus1);
    Garage gar2 = new Garage("Olimpic st. 311(H)", "Tomyuk Mykola Yuriyovich", bus2);
    Garage gar3 = new Garage("Stasyuka st. 8(B)", "Gomenyuk Stanislav Vasilovich", bus3);

    GarageService gars1 = new GarageService(gar1);
    GarageService gars2 = new GarageService(gar2);
    GarageService gars3 = new GarageService(gar3);

    return new Object[][]{{gars1, 55, true},{gars2, 32, false},{gars3, 30, false}};
}

    @Test(dataProvider = "garageServiceCompanyProvider")
    public void garageServiceCompanyTest(GarageService garageService, int company, boolean  check) {
        assertEquals(garageService.companyOfPeople(company), check);
    }
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @DataProvider
    public Object[][] garageServiceMaxProvider(){
    ArrayList<Bus> bus1 = new ArrayList<Bus>();
    ArrayList<Bus> bus2 = new ArrayList<Bus>();
    ArrayList<Bus> bus3 = new ArrayList<Bus>();

    bus1.add(obj1);
    bus1.add(obj2);
    bus1.add(obj3);
    bus1.add(obj4);

    bus2.add(obj5);
    bus2.add(obj6);
    bus2.add(obj7);

    bus3.add(obj8);
    bus3.add(obj9);
    bus3.add(obj10);

    Garage gar1 = new Garage("Golovna st. 279(A)", "Serbynchuk Andriy Yevhenovich", bus1);
    Garage gar2 = new Garage("Olimpic st. 311(H)", "Tomyuk Mykola Yuriyovich", bus2);
    Garage gar3 = new Garage("Stasyuka st. 8(B)", "Gomenyuk Stanislav Vasilovich", bus3);

    GarageService gars1 = new GarageService(gar1);
    GarageService gars2 = new GarageService(gar2);
    GarageService gars3 = new GarageService(gar3);

    return new Object[][]{{gars1, 35},{gars2, 17},{gars3, 15}};
}

    @Test(dataProvider = "garageServiceMaxProvider")
    public void garageServiceMaxTest(GarageService garageService, int max) {
        assertEquals(garageService.findBiggestCapacity(), max);
    }
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @DataProvider
    public Object[][] garageServiceMinProvider(){
    ArrayList<Bus> bus1 = new ArrayList<Bus>();
    ArrayList<Bus> bus2 = new ArrayList<Bus>();
    ArrayList<Bus> bus3 = new ArrayList<Bus>();

    bus1.add(obj1);
    bus1.add(obj2);
    bus1.add(obj3);
    bus1.add(obj4);

    bus2.add(obj5);
    bus2.add(obj6);
    bus2.add(obj7);

    bus3.add(obj8);
    bus3.add(obj9);
    bus3.add(obj10);

    Garage gar1 = new Garage("Golovna st. 279(A)", "Serbynchuk Andriy Yevhenovich", bus1);
    Garage gar2 = new Garage("Olimpic st. 311(H)", "Tomyuk Mykola Yuriyovich", bus2);
    Garage gar3 = new Garage("Stasyuka st. 8(B)", "Gomenyuk Stanislav Vasilovich", bus3);

    GarageService gars1 = new GarageService(gar1);
    GarageService gars2 = new GarageService(gar2);
    GarageService gars3 = new GarageService(gar3);

    return new Object[][]{{gars1, 5},{gars2, 3},{gars3, 5}};
}

    @Test(dataProvider = "garageServiceMinProvider")
    public void garageServiceMinTest(GarageService garageService, int min) {
        assertEquals(garageService.findSmallestCapacity(), min);
    }
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @DataProvider
    public Object[][] xmlBusProvider()throws IOException{
        return new Object[][]{ {new SerializeBusXml(),"Bus.xml"} };
    }

    @Test( dataProvider = "xmlBusProvider")
    public void xmlBusTestSerialize(Serializing<Bus> busXml, String file)throws IOException {
        busXml.serializingObj(obj1, new PrintWriter(new File(file)));
    }

    @Test(dataProvider = "xmlBusProvider")
    public void xmlBusTestDeserialize(Serializing<Bus> busXml, String file)throws IOException{
        assertEquals(busXml.deserializingObj(new FileReader(new File(file))), obj1);
    }
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @DataProvider
    public Object[][] jsonBusProvider()throws IOException{
    return new Object[][]{ {new SerializeBusJson(),"Bus.json"} };
}

    @Test( dataProvider = "jsonBusProvider")
    public void jsonBusTestSerialize(Serializing<Bus> busJson, String file)throws IOException {
        busJson.serializingObj(obj2, new PrintWriter(new File(file)));
    }

    @Test( dataProvider = "jsonBusProvider")
    public void jsonBusTestDeserialize(Serializing<Bus> busJson, String file)throws IOException {
        assertEquals(busJson.deserializingObj(new FileReader(new File(file))), obj2);
    }
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @DataProvider
    public Object[][] txtBusProvider()throws IOException{
    return new Object[][]{ {new SerializeBusTxt(),"Bus.txt"} };
    }

    @Test( dataProvider = "txtBusProvider")
    public void txtBusTestSerialize(Serializing<Bus> busTxt, String file)throws IOException {
        busTxt.serializingObj(obj1, new PrintWriter(new File(file)));
    }

    @Test( dataProvider = "txtBusProvider")
    public void txtBusTestDeserialize(Serializing<Bus> busTxt, String file)throws IOException{
        assertEquals(busTxt.deserializingObj(new FileReader(new File(file))), obj1);
    }
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @DataProvider
    public Object [][] xmlGarageProvider()throws IOException{

        return new Object[][]{{new SerializeGarageXml(), "Garage.xml"}};
    }

    @Test(dataProvider = "xmlGarageProvider")
    public void xmlGarageTestSerialize(Serializing<Garage> garageXml, String file)throws IOException{
        ArrayList<Bus> bus2 = new ArrayList<Bus>();

        bus2.add(obj5);
        bus2.add(obj6);
        bus2.add(obj7);
        bus2.add(obj8);
        Garage gar2 = new Garage("Boulvar st. 4(A)", "Ivanov Ivan Ivanovich", bus2);

        garageXml.serializingObj(gar2, new PrintWriter(new File(file)));
    }

    @Test(dataProvider = "xmlGarageProvider")
    public void xmlGarageTestDeserialize(Serializing<Garage> garageXml, String file)throws IOException{
        ArrayList<Bus> bus2 = new ArrayList<Bus>();

        bus2.add(obj5);
        bus2.add(obj6);
        bus2.add(obj7);
        bus2.add(obj8);
        Garage gar2 = new Garage("Boulvar st. 4(A)", "Ivanov Ivan Ivanovich", bus2);

        assertEquals(garageXml.deserializingObj(new FileReader(new File(file))), gar2 );
    }
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @DataProvider
    public Object [][] jsonGarageProvider()throws IOException{

        return new Object[][]{{new SerializeGarageJson(), "Garage.json"}};
    }

    @Test(dataProvider = "jsonGarageProvider")
    public void jsonGarageTestSerialize(Serializing<Garage> garageJson, String file)throws IOException{
        ArrayList<Bus> bus1 = new ArrayList<Bus>();
        bus1.add(obj1);
        bus1.add(obj2);
        bus1.add(obj3);
        bus1.add(obj4);
        Garage gar1 = new Garage("Golovna st. 279(A)", "Serbynchuk Andriy Yevhenovich", bus1);

        garageJson.serializingObj(gar1, new PrintWriter(new File(file)));
    }

    @Test(dataProvider = "jsonGarageProvider")
    public void jsonGarageTestDeserialize(Serializing<Garage> garageJson, String file)throws IOException{
        ArrayList<Bus> bus1 = new ArrayList<Bus>();
        bus1.add(obj1);
        bus1.add(obj2);
        bus1.add(obj3);
        bus1.add(obj4);
        Garage gar1 = new Garage("Golovna st. 279(A)", "Serbynchuk Andriy Yevhenovich", bus1);

        assertEquals(garageJson.deserializingObj(new FileReader(new File(file))), gar1 );
    }
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @DataProvider
    public Object [][] txtGarageProvider()throws IOException{
        return new Object[][]{{new SerializeGarageTxt(), "Garage.txt"}};
    }

    @Test(dataProvider = "txtGarageProvider")
    public void txtGarageTestSerialize(Serializing<Garage> garageTxt, String file)throws IOException{
        ArrayList<Bus> bus = new ArrayList<Bus>();

        bus.add(obj1);
        bus.add(obj3);
        bus.add(obj10);
        Garage gar = new Garage("Golovna st. 29(A)", "Zabur Andriy Gen", bus);

        garageTxt.serializingObj(gar, new PrintWriter(new File(file)));
    }

    @Test(dataProvider = "txtGarageProvider")
    public void txtGarageTestDeserialize(Serializing<Garage> garageTxt, String file)throws IOException{
        ArrayList<Bus> bus = new ArrayList<Bus>();
        bus.add(obj1);
        bus.add(obj3);
        bus.add(obj10);
        Garage gar = new Garage("Golovna st. 29(A)", "Zabur Andriy Gen", bus);

        garageTxt.deserializingObj(new FileReader(new File(file))).equals(gar);
    }
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
}