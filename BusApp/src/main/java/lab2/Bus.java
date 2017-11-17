package lab2;


import java.lang.reflect.Array;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.regex.*;

public class Bus {

    private int capacity;
    private String indentificationNumber;
    private LocalDate dataConstruction;
    private Model model;


    private final static String CAPACITY_PATTERN = "capacity=(\\d{1,})";
    private final static String DATA_CONSTRUCTION_PATTERN = "dataConstruction=(\\d{4}\\-\\d{2}\\-\\d{2})";
    private final static String MODEL_PATTERN = "model=([A-Z]{1,})";
    private final static String NUMBER_PATTERN = "indentificationNumber=([A-Z]{2}\\d{4}[A-Z]{2})";
    private final static String NUMBER_PATTERN1 = "[A-Z]{2}\\d{4}[A-Z]{2}";


    public enum Model{
        VOLKSWAGEN, FORD, RENAULT, TOYOTA, ICARUS, NISSAN, LADA, GEELY, DAEWOO, AUDI
    }

    public Bus(){
        this.capacity = 5;
        this.indentificationNumber = "AH2718AC";
        this.dataConstruction = LocalDate.of(2010, Month.OCTOBER, 11);
        this.model = Model.LADA;
    }

    public Bus(Bus bus){
        this.capacity = bus.capacity;
        this.indentificationNumber = bus.indentificationNumber;
        this.dataConstruction = bus.dataConstruction;
        this.model = bus.model;
    }

    public Bus(int capacity, String indentificationNumber, LocalDate dataConstruction, Model model){
        this.capacity = capacity;
        this.indentificationNumber = indentificationNumber;
        this.dataConstruction = dataConstruction;
        this.model = model;
    }

    /**
     * @param number - amount of passengers
     * This method checks the capacity of the bus.
     * If there is enough place for passengers it will return true,
     * and will return false if there are to many people for this type of the bus.
     * @return
     */

    public boolean checkOfTheSize(int number){
        return number <= this.capacity;
    }

    /**
     * @param indentificationNumber
     * This method checks whether the identification number is correct.
     * @return
     */
    public boolean regularExpIdentificationNumber(String indentificationNumber){

        Pattern pattern = Pattern.compile(NUMBER_PATTERN1);
        Matcher match = pattern.matcher(indentificationNumber);


        return match.matches();
    }

    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String getIndentificationNumber() {
        return indentificationNumber;
    }

    public void setIndentificationNumber(String indentificationNumber) {
        this.indentificationNumber = indentificationNumber;
    }

    public LocalDate getDataConstruction() {
        return dataConstruction;
    }

    public void setDataConstruction(LocalDate dataConstruction) {
        this.dataConstruction = dataConstruction;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((indentificationNumber == null) ? 0 : indentificationNumber.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;

        if (obj == null)
            return false;

        if (getClass() != obj.getClass())
            return false;

        Bus other = (Bus) obj;

        return ((other.dataConstruction.equals(this.dataConstruction))
                && (other.capacity == this.capacity) && (other.indentificationNumber.equals(this.indentificationNumber)));

    }
    public Bus fromString(String[] str){
        Pattern pattern1 = Pattern.compile(NUMBER_PATTERN);
        Pattern pattern2 = Pattern.compile(MODEL_PATTERN);
        Pattern pattern3 = Pattern.compile(CAPACITY_PATTERN);
        Pattern pattern4 = Pattern.compile(DATA_CONSTRUCTION_PATTERN);
        Matcher match;

        for(int i = 0; i < 4; i++) {

            if((match = pattern1.matcher(str[i])).matches() == true){
                this.indentificationNumber = match.group(1);
            }
            if((match = pattern2.matcher(str[i])).matches() == true){
                this.model = Model.valueOf(match.group(1));
            }
            if((match = pattern3.matcher(str[i])).matches() == true){
                this.capacity = Integer.parseInt(match.group(1));
            }
            if((match = pattern4.matcher(str[i])).matches()){
                this.dataConstruction = LocalDate.parse(match.group(1));
            }
        }
        return this;
    }

    @Override
    public String toString() {

        return "capacity=" + capacity + "\nindentificationNumber=" + indentificationNumber + "\ndataConstruction="
                + dataConstruction + "\nmodel=" + model;
    }


}