import java.awt.*;

/**
 * Class that implements hospital state
 */
public class HospitalState implements State {
    private Individual individual;

    /**
     * constructor for HospitalState class
     * initialize individual
     * @param individual
     */
    public HospitalState(Individual individual) {
        this.individual = individual;
    }

    /**
     * method to update individual state behaviours
     * @param deltaTime time passed between update calls
     */
    @Override
    public void update(float deltaTime) {
        individual.hospitalTime += deltaTime;
        if (individual.hospitalTime > 10000){
            individual.setState(individual.getHEALTHY());
            individual.mediator.askExitHospital(individual);
            individual.infectionTime = 0;
            individual.randomPosition();
            individual.randomDirection();
        }
    }

    /**
     * method to render individual
     * nothing rendered in hospital state
     * @param g Graphics object
     */
    @Override
    public void render(Graphics g){ }

    /**
     * method to check whether given string represents the state
     * @param obj state name string
     * @return true or false
     */
    @Override
    public boolean equals(Object obj) {
        return ((String)obj).equals("HOSPITAL");
    }
}
