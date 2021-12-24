import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * GUI class that opens the frame, adds input panel and with
 * timer functionality renders the society
 */
public class GUI extends JPanel implements ActionListener {
    private Society society;
    public Timer timer;
    private InputPanel inputPanel;
    private float deltaTime;

    /**
     * constructor for GUI class that creates society and setups the frame
     * and starts the timer
     */
    public GUI(){
        society = new Society(800, 100, 0.8, 0.4, 30,1, 1);
        setSize(new Dimension(1000,600));
        setPreferredSize(new Dimension(1000,600));
        deltaTime = 20;
        timer = new Timer((int) deltaTime, this);
        setup();
        timer.start();
    }

    /**
     * method to prepare the gui frame, adds input panel and society canvas
     */
    private void setup(){
        this.setSize(new Dimension(1300, 600));
        this.setFocusable(true);
        JFrame frame = new JFrame("171044042");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(this, BorderLayout.EAST);
        this.inputPanel = new InputPanel(this);
        frame.add(inputPanel, BorderLayout.WEST);
        frame.pack();
        frame.setVisible(true);
    }

    /**
     * method to update whole society and its content
     * @param deltaTime passed between update calls
     */
    private void update(float deltaTime){
        society.update(deltaTime);
    }

    /**
     * method to render society
     * @param g Graphics object
     */
    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        society.render(g);
    }

    /**
     * method that called periodically by tiimer
     * updates society then renders it
     * @param e ActionEvent object
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == timer){
            update(deltaTime);
            repaint();
        }
    }

    /**
     * method that renews the society with new arguments
     * @param population number of individuals
     * @param speed speed of individuals
     * @param spreadFactor spread factor
     * @param mortalityRate mortality rate
     * @param maskPercentage percentage of individuals wearing mask
     * @param socialDistance social distance
     * @param socialTime time of socializing
     */
    public void newSociety(int population, int speed, double spreadFactor, double mortalityRate,
                           int maskPercentage, int socialDistance, int socialTime){

        this.society = new Society(population, speed, spreadFactor, mortalityRate, maskPercentage,
                socialDistance, socialTime);

    }

}
