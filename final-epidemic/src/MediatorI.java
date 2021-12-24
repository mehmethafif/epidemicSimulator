import java.util.ArrayList;

public interface MediatorI {

    /**
     * method to subscribe people to the mediator
     * @param people list of individuals
     */
    public void subscribePeople(ArrayList<Individual> people);

    /**
     * method to check whether an individual collides with another individual
     * also checks the states of the individuals and by considering the infection probabilities
     * states of the individuals changed
     * @param individual individual to check collision
     */
    public void checkCollision(Individual individual);

    /**
     * method to register an individual to the hospital if there is enough capacity
     * @param individual individual that requesting to enter hospital
     * @return whether individual entered the hospital or not
     */
    public boolean askEnterHospital(Individual individual);

    /**
     * method to remove an individual from the hospital when individual healed.
     * @param individual individual to remove from hospital
     */
    public void askExitHospital(Individual individual);

    /**
     * method to keep count of dead individuals
     */
    public void recordDeath();

}
