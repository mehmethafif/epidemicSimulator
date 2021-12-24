import java.awt.*;

/**
 * Class that implements sick collision state
 */
public class SickCollisionState implements State {
    private Individual individual;

    /**
     * constructor for SickCollisionState class
     * initialize individual
     * @param individual
     */
    public SickCollisionState(Individual individual) {
        this.individual = individual;
    }

    /**
     * method to update individual state behaviours
     * @param deltaTime time passed between update calls
     */
    @Override
    public void update(float deltaTime) {
        individual.socialTime += deltaTime;
        individual.infectionTime += deltaTime;
        if (individual.socialTime >= individual.socializationTime*1000){
            individual.setState(individual.getINFECTED());
            individual.socialTime = 0;
            //individual.bounce();
            individual.randomDirection();
            individual.moveDesired(individual.socialDistance);
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
        return ((String)obj).equals("SICK_COLLISION");
    }
}
