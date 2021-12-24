import java.util.ArrayList;

/**
 * Hospital class that represents the hospital in society
 */
public class Hospital {
    private int capacity;
    private int patientCount;
    public ArrayList<Individual> patients;

    /**
     * constructor for hospital class
     * initializes patient list, capacity and count
     * @param population
     */
    public Hospital(int population) {
        this.capacity = population/100;
        this.patientCount = 0;
        this.patients = new ArrayList<Individual>();
    }

    /**
     * method to add individual to the hospital
     * @param individual individual to be added
     */
    public void addPatient(Individual individual){
        this.patientCount += 1;
        this.patients.add(individual);
    }

    /**
     * method to remove patient from hospital
     * @param individual individual to be removed
     */
    public void removePatient(Individual individual){
        this.patientCount -= 1;
        this.patients.remove(individual);
    }

    /**
     * method to check if hospital is full
     * @return true or false
     */
    public boolean isFull(){
        if (capacity <= patientCount){
            return true;
        }else{
            return false;
        }
    }

    /**
     * method to get patient count in hospital
     * @return number of individual in hospital
     */
    public int getPatientCount() {
        return patientCount;
    }
}

