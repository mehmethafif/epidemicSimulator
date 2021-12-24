import java.awt.*;
import java.util.ArrayList;

/**
 * Society class that includes all members of society, hospital and mediator
 * individuals created in this class
 */
public class Society {
    private Mediator mediator;
    private ArrayList<Individual> people;
    private Hospital hospital;
    private int deathCount;
    private int infectionCount;
    private float time;
    private int population = 40;
    private int speed;
    private double spreadFactor;
    private double mortalityRate;
    private int maskPercentage;
    private int socialDistance;
    private int socialTime;
    int maskCount;

    /**
     * constructor for Society class creates individuals
     * @param population number of individual
     * @param speed speed of individuals
     * @param spreadFactor spread factor
     * @param mortalityRate mortality rate
     * @param maskPercentage percentage of people wearing mask
     * @param socialDistance social distance
     * @param socialTime time of individuals socializing
     */
    public Society(int population, int speed, double spreadFactor, double mortalityRate,
                   int maskPercentage, int socialDistance, int socialTime){
        this.population = population;
        this.speed = speed;
        this.spreadFactor = spreadFactor;
        this.mortalityRate = mortalityRate;
        this.maskPercentage = maskPercentage;
        this.socialDistance = socialDistance;
        this.socialTime = socialTime;
        this.hospital = new Hospital(population);
        this.maskCount = population*(maskPercentage/100);
        this.mediator = new Mediator(population, spreadFactor, socialTime, socialDistance);

        people = new ArrayList<Individual>();
        for (int i=0; i<population;i++){
            if (i<=maskCount){
                people.add(new Individual(mediator, socialTime, 0.2, speed, mortalityRate, socialDistance));
            } else {
                people.add(new Individual(mediator, socialTime, 1.0, speed, mortalityRate, socialDistance));
            }

        }
        State infected = people.get(0).getINFECTED();
        people.get(0).setState(infected);

        mediator.subscribePeople(people);
        mediator.addHospital(hospital);
    }

    /**
     * method to render every individual
     * @param g Graphics object
     */
    public void render(Graphics g){
        for(Individual individual : people){
            individual.render(g);
        }
        g.setColor(Color.BLACK);
        g.drawString("Healthy Count: " + mediator.getHealthyCount(), 5, 565);
        g.drawString("Infected Count: " + mediator.getInfectedCount(), 5, 575);
        g.drawString("Hospital Count: " + mediator.getPatientCount(), 5, 585);
        g.drawString("Death Count: " + mediator.getDeadCount(), 5, 595);
    }

    /**
     * method to update  every individuals behaviour and collision checks made by mediator
     * @param deltaTime passed between update calls
     */
    public void update(float deltaTime) {
        for (Individual individual : people){
            individual.update(deltaTime);
            if (individual.getState().equals("HEALTHY") || individual.getState().equals("INFECTED") ){
                mediator.checkCollision(individual);
            }
        }
    }
}