import lab2.Bus;
import lab2.serialize.SerializeBusJson;
import lab2.serialize.SerializeBusTxt;
import lab2.serialize.SerializeBusXml;
import lab2.serialize.Serializing;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;

import static org.testng.Assert.assertEquals;

public class SerializingBusTest {

    Bus bus1 = new Bus(5, "AH8790UN", LocalDate.of(1997, 11, 10), Bus.Model.DAEWOO);
    Bus bus2 = new Bus(35, "AC7809OP", LocalDate.of(1990, 9, 28), Bus.Model.ICARUS);
    Bus bus3 = new Bus(10, "KL1324LM", LocalDate.of(2003, 10, 30), Bus.Model.VOLKSWAGEN);
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @DataProvider
    public Object[][] xmlBusProvider() throws IOException {
        return new Object[][]{{new SerializeBusXml(), "Bus.xml"}};
    }

    @Test(dataProvider = "xmlBusProvider")
    public void xmlBusTestDeserialize(Serializing<Bus> busXml, String file) throws IOException {
        assertEquals(busXml.deserializingObj(new FileReader(new File(file))), bus1);
    }

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @DataProvider
    public Object[][] jsonBusProvider() throws IOException {
        return new Object[][]{{new SerializeBusJson(), "Bus.json"}};
    }

    @Test(dataProvider = "jsonBusProvider")
    public void jsonBusTestDeserialize(Serializing<Bus> busJson, String file) throws IOException {
        assertEquals(busJson.deserializingObj(new FileReader(new File(file))), bus2);
    }

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @DataProvider
    public Object[][] txtBusProvider() throws IOException {
        return new Object[][]{{new SerializeBusTxt(), "Bus.txt"}};
    }

    @Test(dataProvider = "txtBusProvider")
    public void txtBusTestDeserialize(Serializing<Bus> busTxt, String file) throws IOException {
        assertEquals(busTxt.deserializingObj(new FileReader(new File(file))), bus3);
    }
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
}
