import lab2.Bus;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.time.LocalDate;

import static org.testng.Assert.assertEquals;

public class BusTest {

    /*Buses*/
    Bus bus1 = new Bus(5, "AH8790UN", LocalDate.of(1997, 11, 10), Bus.Model.DAEWOO);
    Bus bus2 = new Bus(35, "AC7809OP", LocalDate.of(1990, 9, 28), Bus.Model.ICARUS);
    Bus bus3 = new Bus(10, "KL1324LM", LocalDate.of(2003, 10, 30), Bus.Model.VOLKSWAGEN);
    Bus bus4 = new Bus(7, "QP0987CH", LocalDate.of(2009, 12, 10), Bus.Model.RENAULT);
    Bus bus5 = new Bus(11, "BJ3435FG", LocalDate.of(2017, 10, 1), Bus.Model.GEELY);
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * This test will check the capacity of the bus.
     *
     * @return
     */
    @DataProvider
    public Object[][] capacityProvider() {
        return new Object[][]{{bus1, 1, true}, {bus2, 33, true}, {bus3, 519, false}, {bus4, 100, false}, {bus5, 10, true}};
    }

    @Test(dataProvider = "capacityProvider")
    public void testCapacity(Bus bus, int company, boolean result) {
        assertEquals(bus.checkOfTheSize(company), result);
    }
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * This test will check whether the identification number of the bus is correct.
     *
     * @return
     */

    @DataProvider
    public Object[][] regularExpProvider() {
        return new Object[][]{{"AH8790UN", true}, {"ZA5334BUR", false}, {"BU3185QG", true}, {"QWE1243UT", false},
                {"IO9075PO", true} , {"ER09871PO", false}};
    }

    @Test(dataProvider = "regularExpProvider")
    public void reagularExpTest(String input, boolean result) {
        assertEquals(new Bus().regularExpIdentificationNumber(input), result);
    }
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
}
