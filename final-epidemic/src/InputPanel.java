import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Input panel class that implements buttons and sliders
 * Starting values are defined
 */
public class InputPanel extends JPanel {
    private GUI gui;
    private JButton pauseButton, restartButton, playButton;
    private JSlider populationSlider, speedSlider, spreadFactorSlider, mortalityRateSlider, maskPercentageSlider, socialDistanceSlider, socialTimeSlider;
    private JLabel populationLabel, speedLabel, spreadFactorLabel, mortalityRateLabel, maskPercentageLabel, socialDistanceLabel, socialTimeLabel;
    private int population = 800, speed = 100, maskPercentage = 30, socialDistance = 1, socialTime = 1;
    private double spreadFactor = 0.8, mortalityRate = 0.4;
    private boolean paused = false;

    /**
     * constructor for InputPanel class
     * creates buttons and sliders and defines them
     * @param gui GUI object that creates frame and the canvas
     */
    public InputPanel(GUI gui){
        this.gui = gui;

        SliderListener sliderListener = new SliderListener();
        setSize(new Dimension(300, 600));
        setPreferredSize(new Dimension(300,600));
        BoxLayout vertical = new BoxLayout(this, BoxLayout.Y_AXIS);
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
        setLayout(vertical);

        //play button
        playButton = new JButton("Play");
        playButton.setContentAreaFilled(false);
        playButton.setOpaque(true);
        playButton.addActionListener(new ButtonListener());

        //pause button
        pauseButton = new JButton("Pause");
        pauseButton.setContentAreaFilled(false);
        pauseButton.setOpaque(true);
        pauseButton.addActionListener(new ButtonListener());

        //restart button
        restartButton = new JButton("Restart");
        restartButton.setContentAreaFilled(false);
        restartButton.setOpaque(true);
        restartButton.addActionListener(new ButtonListener());

        //population slider
        populationLabel = new JLabel("Population: 800");
        populationLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        populationSlider = new JSlider(JSlider.HORIZONTAL, 1, 1000, 800);
        populationSlider.setMajorTickSpacing(200);
        populationSlider.setMinorTickSpacing(20);
        populationSlider.setPaintTicks(true);
        populationSlider.setPaintLabels(true);
        populationSlider.setAlignmentX(Component.LEFT_ALIGNMENT);
        populationSlider.addChangeListener(sliderListener);
        populationSlider.setEnabled(false);

        //speed slider
        speedLabel = new JLabel("Speed: 60");
        speedLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        speedSlider = new JSlider(JSlider.HORIZONTAL, 1, 500, 100);
        speedSlider.setMajorTickSpacing(100);
        speedSlider.setMinorTickSpacing(10);
        speedSlider.setPaintTicks(true);
        speedSlider.setPaintLabels(true);
        speedSlider.setAlignmentX(Component.LEFT_ALIGNMENT);
        speedSlider.addChangeListener(sliderListener);
        speedSlider.setEnabled(false);

        //spread factor slider
        spreadFactorLabel = new JLabel("Spread Factor: 0.8");
        spreadFactorLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        spreadFactorSlider = new JSlider(JSlider.HORIZONTAL, 5, 10, 8);
        spreadFactorSlider.setMajorTickSpacing(1);
        spreadFactorSlider.setMinorTickSpacing(1);
        spreadFactorSlider.setPaintTicks(true);
        spreadFactorSlider.setPaintLabels(true);
        spreadFactorSlider.setAlignmentX(Component.LEFT_ALIGNMENT);
        spreadFactorSlider.addChangeListener(sliderListener);
        spreadFactorSlider.setEnabled(false);

        //mortality rate slider
        mortalityRateLabel = new JLabel("Mortality Rate: 0.4");
        mortalityRateLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        mortalityRateSlider = new JSlider(JSlider.HORIZONTAL, 1, 9, 4);
        mortalityRateSlider.setMajorTickSpacing(1);
        mortalityRateSlider.setMinorTickSpacing(1);
        mortalityRateSlider.setPaintTicks(true);
        mortalityRateSlider.setPaintLabels(true);
        mortalityRateSlider.setAlignmentX(Component.LEFT_ALIGNMENT);
        mortalityRateSlider.addChangeListener(sliderListener);
        mortalityRateSlider.setEnabled(false);

        //mask percentage slider
        maskPercentageLabel = new JLabel("Mask Percentage: %30");
        maskPercentageLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        maskPercentageSlider = new JSlider(JSlider.HORIZONTAL, 0, 100, 30);
        maskPercentageSlider.setMajorTickSpacing(20);
        maskPercentageSlider.setMinorTickSpacing(10);
        maskPercentageSlider.setPaintTicks(true);
        maskPercentageSlider.setPaintLabels(true);
        maskPercentageSlider.setAlignmentX(Component.LEFT_ALIGNMENT);
        maskPercentageSlider.addChangeListener(sliderListener);
        maskPercentageSlider.setEnabled(false);

        //social distance slider
        socialDistanceLabel = new JLabel("Social Distance: 1");
        socialDistanceLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        socialDistanceSlider = new JSlider(JSlider.HORIZONTAL, 0, 9, 1);
        socialDistanceSlider.setMajorTickSpacing(1);
        socialDistanceSlider.setMinorTickSpacing(1);
        socialDistanceSlider.setPaintTicks(true);
        socialDistanceSlider.setPaintLabels(true);
        socialDistanceSlider.setAlignmentX(Component.LEFT_ALIGNMENT);
        socialDistanceSlider.addChangeListener(sliderListener);
        socialDistanceSlider.setEnabled(false);

        //social time slider
        socialTimeLabel = new JLabel("Social Time: 1");
        socialTimeLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        socialTimeSlider = new JSlider(JSlider.HORIZONTAL, 0, 9, 1);
        socialTimeSlider.setMajorTickSpacing(1);
        socialTimeSlider.setMinorTickSpacing(1);
        socialTimeSlider.setPaintTicks(true);
        socialTimeSlider.setPaintLabels(true);
        socialTimeSlider.setAlignmentX(Component.LEFT_ALIGNMENT);
        socialTimeSlider.addChangeListener(sliderListener);
        socialTimeSlider.setEnabled(false);

        add(playButton);
        add(pauseButton);
        add(restartButton);
        add(populationLabel);
        add(populationSlider);
        add(speedLabel);
        add(speedSlider);
        add(spreadFactorLabel);
        add(spreadFactorSlider);
        add(mortalityRateLabel);
        add(mortalityRateSlider);
        add(maskPercentageLabel);
        add(maskPercentageSlider);
        add(socialDistanceLabel);
        add(socialDistanceSlider);
        add(socialTimeLabel);
        add(socialTimeSlider);
    }


    /**
     * inner class that controls button functionality
     */
    private class ButtonListener implements ActionListener {

        /**
         * method starts stops or restarts the program according to button pressed
         * @param e ActionEvent object
         */
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == playButton) {
                gui.timer.restart();
                paused = false;
            } else if (e.getSource() == pauseButton) {
                gui.timer.stop();
                paused = true;
            } else if (e.getSource() == restartButton) {
                gui.timer.stop();
                restart();
                paused = false;
                playButton.setEnabled(true);
                pauseButton.setEnabled(true);
                restartButton.setEnabled(true);
                populationSlider.setEnabled(false);
                speedSlider.setEnabled(true);
                spreadFactorSlider.setEnabled(false);
                mortalityRateSlider.setEnabled(false);
                maskPercentageSlider.setEnabled(false);
                socialDistanceSlider.setEnabled(false);
                gui.timer.restart();
            }

            populationSlider.setEnabled(paused);
            speedSlider.setEnabled(paused);
            spreadFactorSlider.setEnabled(paused);
            mortalityRateSlider.setEnabled(paused);
            maskPercentageSlider.setEnabled(paused);
            socialDistanceSlider.setEnabled(paused);
            socialTimeSlider.setEnabled(paused);

        }

    }


    /**
     * inner class that controls slider functionality
     */
    private class SliderListener implements ChangeListener {

        /**
         * method changes label according to slider value
         * @param e ChangeEvent object
         */
        public void stateChanged(ChangeEvent e) {
            if (e.getSource() == populationSlider || e.getSource() == speedSlider || e.getSource() == spreadFactorSlider
                    || e.getSource() == mortalityRateSlider || e.getSource() == maskPercentageSlider
                    || e.getSource() == socialDistanceSlider) {
                playButton.setEnabled(false);
                pauseButton.setEnabled(false);
            }

            population = populationSlider.getValue();
            populationLabel.setText("Population: " + population);

            speed = speedSlider.getValue();
            speedLabel.setText("Speed: " + speed);

            spreadFactor = (double)spreadFactorSlider.getValue() / 10.0;
            spreadFactorLabel.setText("Spread Factor: " + spreadFactor);

            mortalityRate = (double)mortalityRateSlider.getValue() / 10.0;
            mortalityRateLabel.setText("Mortality Rate: " + mortalityRate);

            maskPercentage = maskPercentageSlider.getValue();
            maskPercentageLabel.setText("Mask Percentage: %" + maskPercentage);

            socialDistance = socialDistanceSlider.getValue();
            socialDistanceLabel.setText("Social Distance: " + socialDistance);

            socialTime = socialTimeSlider.getValue();
            socialTimeLabel.setText("Social Time: " + socialTime);

        }
    }

    /**
     * method to create new society with given arguments when restart button pressed
     */
    public void restart(){
        gui.newSociety(population, speed, spreadFactor, mortalityRate, maskPercentage, socialDistance, socialTime);
    }



}
