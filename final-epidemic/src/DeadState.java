import java.awt.*;

/**
 * Class that implements dead state
 */
public class DeadState implements State {
    private Individual individual;

    /**
     * constructor for DeadState class
     * initialize individual
     * @param individual
     */
    public DeadState(Individual individual) {
        this.individual = individual;
    }

    /**
     * method to update individual state behaviours
     * no update made in dead state
     * @param deltaTime time passed between update calls
     */
    @Override
    public void update(float deltaTime) { }

    /**
     * method to render individual
     * nothing rendered in dead state
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
        return ((String)obj).equals("DEAD");
    }
}
