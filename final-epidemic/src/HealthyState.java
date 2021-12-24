import java.awt.*;

/**
 * Class that implements healthy state
 */
public class HealthyState implements State {
    private Individual individual;

    /**
     * constructor for HealthyState class
     * initialize individual
     * @param individual
     */
    public HealthyState(Individual individual) {
        this.individual = individual;
    }

    /**
     * method to update individual state behaviours
     * @param deltaTime time passed between update calls
     */
    @Override
    public void update(float deltaTime) {
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
        color = Color.GREEN;
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
        return ((String)obj).equals("HEALTHY");
    }

}
