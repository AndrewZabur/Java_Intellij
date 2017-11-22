import lab2.Bus;
import lab2.Garage;
import lab2.serialize.SerializeGarageJson;
import lab2.serialize.SerializeGarageTxt;
import lab2.serialize.SerializeGarageXml;
import lab2.serialize.Serializing;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

import static org.testng.Assert.assertEquals;

public class SerializingGarageTest {
    /*Buses*/
    /*Xml*/
    Bus bus1 = new Bus(5, "AH8790UN", LocalDate.of(1997, 11, 10), Bus.Model.DAEWOO);
    Bus bus2 = new Bus(35, "AC7809OP", LocalDate.of(1990, 9, 28), Bus.Model.ICARUS);
    Bus bus3 = new Bus(10, "KL1324LM", LocalDate.of(2003, 10, 30), Bus.Model.VOLKSWAGEN);
    Bus bus4 = new Bus(7, "QP0987CH", LocalDate.of(2009, 12, 10), Bus.Model.RENAULT);
    /*Json*/
    Bus bus5 = new Bus(11, "BJ3435FG", LocalDate.of(2017, 10, 1), Bus.Model.GEELY);
    Bus bus6 = new Bus(3, "BU3185QG", LocalDate.of(2013, 5, 7), Bus.Model.FORD);
    Bus bus7 = new Bus(17, "PL7616DY", LocalDate.of(2012, 6, 15), Bus.Model.LADA);
    /*Txt*/
    Bus bus8 = new Bus(8, "PM0912UI", LocalDate.of(2010, 12, 11), Bus.Model.NISSAN);
    Bus bus9 = new Bus(15, "ZA1234UR", LocalDate.of(2000, 1, 2), Bus.Model.TOYOTA);
    Bus bus10 = new Bus(5, "BL7777AT", LocalDate.of(2015, 12, 11), Bus.Model.AUDI);
    /*ArrayList<Bus>*/
    ArrayList<Bus> busesXml = new ArrayList<>();
    ArrayList<Bus> busesJson = new ArrayList<>();
    ArrayList<Bus> busesTxt = new ArrayList<>();
    /*Garages*/
    Garage xml = new Garage("Boulvar st. 4-A", "Ivanov Ivan Ivanovich", busesXml);
    Garage json = new Garage("Golovna st. 279-B", "Serbynchuk Andriy Yevhenovich", busesJson);
    Garage txt = new Garage("Prospect st. 29-C", "Zaburyannyy Andriy Gennadiyovich", busesTxt);
    @BeforeClass
    public void setBusesXml() {
        busesXml.add(bus1);
        busesXml.add(bus2);
        busesXml.add(bus3);
        busesXml.add(bus4);

        busesJson.add(bus5);
        busesJson.add(bus6);
        busesJson.add(bus7);

        busesTxt.add(bus8);
        busesTxt.add(bus9);
        busesTxt.add(bus10);
    }

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @DataProvider
    public Object[][] xmlGarageProvider() throws IOException {

        return new Object[][]{{new SerializeGarageXml(), "Garage.xml"}};
    }

    @Test(dataProvider = "xmlGarageProvider")
    public void xmlGarageTestDeserialize(Serializing<Garage> garageXml, String file) throws IOException {
        assertEquals(garageXml.deserializingObj(new FileReader(new File(file))), xml);
    }

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @DataProvider
    public Object[][] jsonGarageProvider() throws IOException {

        return new Object[][]{{new SerializeGarageJson(), "Garage.json"}};
    }

    @Test(dataProvider = "jsonGarageProvider")
    public void jsonGarageTestDeserialize(Serializing<Garage> garageJson, String file) throws IOException {
        assertEquals(garageJson.deserializingObj(new FileReader(new File(file))), json);
    }

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @DataProvider
    public Object[][] txtGarageProvider() throws IOException {
        return new Object[][]{{new SerializeGarageTxt(), "Garage.txt"}};
    }

    @Test(dataProvider = "txtGarageProvider")
    public void txtGarageTestDeserialize(Serializing<Garage> garageTxt, String file) throws IOException {
        assertEquals(garageTxt.deserializingObj(new FileReader(new File(file))),txt);
    }
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
}
