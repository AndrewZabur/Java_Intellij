package lab2;


import java.time.LocalDate;
import java.time.Month;
import java.util.regex.*;

public class Bus {

    private int capacity;
    private String indentificationNumber;
    private LocalDate dataConstruction;
    private Model model;
    private final static String NUMBER_PATTERN = "[A-Z]{2}\\d{4}[A-Z]{2}";

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

        Pattern pattern = Pattern.compile(NUMBER_PATTERN);
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

    @Override
    public String toString() {

        return "capacity=" + capacity + "\nindentificationNumber=" + indentificationNumber + "\ndataConstruction="
                + dataConstruction + "\nmodel=" + model ;
    }

}