import java.util.ArrayList;

/**
 * Class that implement Mediator which controls interaction between the individual
 */
public class Mediator implements MediatorI {
    //list of individuals and some conditional variables
    private ArrayList<Individual> people;
    private double probInfection;
    private Hospital hospital;
    private double spreadFactor;
    private int socialTime;
    private int socialDistance;
    private int infectedCount;
    private int healthyCount;
    private int deadCount;

    /**
     * Constructor for mediator class initializes the variables
     * @param population number of individual
     * @param spreadFactor spread factor
     * @param socialTime time to wait while socializing
     * @param socialDistance social distance
     */
    public Mediator(int population, double spreadFactor, int socialTime, int socialDistance){
        this.spreadFactor = spreadFactor;
        this.socialTime = socialTime;
        this.socialDistance = socialDistance;
        this.infectedCount = 1;
        this.healthyCount = population-1;
        this.deadCount = 0;
    }

    /**
     * method to subscribe people to the mediator
     * @param people list of individuals
     */
    public void subscribePeople(ArrayList<Individual> people){
        this.people = people;
    }

    /**
     * method to check whether an individual collides with another individual
     * also checks the states of the individuals and by considering the infection probabilities
     * states of the individuals changed
     * @param individual individual to check collision
     */
    public void checkCollision(Individual individual) {
        for (Individual otherIndividual : people){
            if (!(individual == otherIndividual)){

                double dist = Math.sqrt(Math.pow(individual.getPosX() - otherIndividual.getPosX(), 2) +
                        Math.pow(individual.getPosY() - otherIndividual.getPosY(), 2));
                boolean collided = (dist <= socialDistance+5);
                if (collided && individual.getState().equals("HEALTHY") && otherIndividual.getState().equals("INFECTED")){
                    probInfection = Math.min(spreadFactor*(1+socialTime/10)*individual.getMask()* otherIndividual.getMask()*(1-socialDistance/10), 1);
                    if (Math.random() < probInfection){
                        individual.setState(individual.getSICK_COLLISION());
                        infectedCount += 1;
                        healthyCount -= 1;
                        otherIndividual.setState(otherIndividual.getSICK_COLLISION());

                    }else {
                        individual.setState(individual.getCOLLISION());
                        otherIndividual.setState(otherIndividual.getSICK_COLLISION());
                    }
                }else if(collided && individual.getState().equals("HEALTHY") && otherIndividual.getState().equals("HEALTHY")){
                    individual.setState(individual.getCOLLISION());
                    otherIndividual.setState(otherIndividual.getCOLLISION());
                }else if (collided && individual.getState().equals("INFECTED") && otherIndividual.getState().equals("INFECTED")){
                    individual.setState(individual.getSICK_COLLISION());
                    otherIndividual.setState(otherIndividual.getSICK_COLLISION());
                }
            }
        }
    }

    /**
     * method to register an individual to the hospital if there is enough capacity
     * @param individual individual that requesting to enter hospital
     * @return whether individual entered the hospital or not
     */
    @Override
    public boolean askEnterHospital(Individual individual) {
        if(!hospital.isFull()){
            hospital.addPatient(individual);
            individual.setState(individual.getHOSPITAL());
            infectedCount -= 1;
            return true;
        }else {
            return false;
        }
    }

    /**
     * method to remove an individual from the hospital when individual healed.
     * @param individual individual to remove from hospital
     */
    @Override
    public void askExitHospital(Individual individual) {
        hospital.removePatient(individual);
        healthyCount+=1;
    }

    /**
     * method to keep count of dead individuals
     */
    @Override
    public void recordDeath() {
        deadCount += 1;
        infectedCount -=1;
    }

    /**
     * method to register hospital to mediator
     */
    public void addHospital(Hospital hospital){
        this.hospital = hospital;
    }

    /**
     * method to get count of patients in hospital
     * @return number of individuals in hospital
     */
    public int getPatientCount(){
        return hospital.getPatientCount();
    }

    /**
     * method to get healthy individuals count
     * @return number of healthy individuals
     */
    public int getHealthyCount() {
        return healthyCount;
    }

    /**
     * method to get infected individuals count
     * @return number of infected individuals
     */
    public int getInfectedCount() {
        return infectedCount;
    }

    /**
     * method to get dead individuals count
     * @return number of dead individuals
     */
    public int getDeadCount() {
        return deadCount;
    }

}
