package lab2;

public class GarageService {

    private Garage garage;

    public GarageService(Garage garage){
        this.garage = garage;
    }

    public int garageCapacity(){
        return garage.getBuses().stream().mapToInt(Bus :: getCapacity).sum();
    }

    public boolean companyOfPeople(int company){
        return garage.getBuses().stream().mapToInt(Bus :: getCapacity).sum() >= company;
    }

    public int findBiggestCapacity() {
        return garage.getBuses().stream().mapToInt(Bus :: getCapacity).max().getAsInt();
    }

    public int findSmallestCapacity() {
        return garage.getBuses().stream().mapToInt(Bus :: getCapacity).min().getAsInt();
    }

}
