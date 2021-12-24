import java.awt.*;

public interface State {

    /**
     * method to update individual state behaviours
     * @param deltaTime time passed between update calls
     */
    public void update(float deltaTime);

    /**
     * method to render individual
     * @param g Graphics object
     */
    public void render(Graphics g);
}
