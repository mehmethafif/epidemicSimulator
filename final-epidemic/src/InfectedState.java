import java.awt.*;

/**
 * Class that implements infected state
 */
public class InfectedState implements State {
    private Individual individual;

    /**
     * constructor for InfectedState class
     * initialize individual
     * @param individual
     */
    public InfectedState(Individual individual) {
        this.individual = individual;
    }

    /**
     * method to update individual state behaviours
     * @param deltaTime time passed between update calls
     */
    @Override
    public void update(float deltaTime) {
        individual.infectionTime += deltaTime;
        if (individual.infectionTime >= individual.deathTime){
            individual.setState(individual.getDEAD());
            individual.mediator.recordDeath();
        } else if (individual.infectionTime >= 25000){
            individual.mediator.askEnterHospital(individual);
        }
        individual.moveTime += deltaTime;
        while(individual.moveTime >= individual.moveRate){
            individual.move();
            individual.moveTime -= individual.moveRate;
        }

    }

    /**
     * method to render individual
     * @param g Graphics object
     */
    @Override
    public void render(Graphics g){
        Color color;
        color = Color.RED;
        g.setColor(color);
        g.fillRect(individual.getPosX(), individual.getPosY(), 5, 5);
    }

    /**
     * method to check whether given string represents the state
     * @param obj state name string
     * @return true or false
     */
    @Override
    public boolean equals(Object obj) {
        return ((String)obj).equals("INFECTED");
    }


}
