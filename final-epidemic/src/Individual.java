import java.awt.*;
import java.util.Random;

/**
 * Class that represents each individual, single square in gui
 */
public class Individual {
    //state objects
    private State HEALTHY;
    private State INFECTED;
    private State HOSPITAL;
    private State DEAD;
    private State COLLISION;
    private State SICK_COLLISION;
    public State state;
    //mediator individual behaviour related variables
    public Mediator mediator;
    private int posX, posY;
    private int velX, velY;
    public float socializationTime;
    public float socialTime;
    public float hospitalTime;
    public float infectionTime;
    public float moveRate;
    public float moveTime;
    public float deathTime;
    private double mask;
    private int speed;
    public int socialDistance;

    /**
     * Constructor for the Individual class initializes variables
     * @param mediator the mediator object
     * @param socializationTime time to wait while socialization
     * @param mask mask value
     * @param speed initial speed
     * @param mortalityRate mortality rate
     * @param socialDistance social distance
     */
    public Individual(Mediator mediator, int socializationTime, double mask, int speed, double mortalityRate, int socialDistance){
        this.speed = speed;
        this.socialDistance = socialDistance;
        this.moveRate = 1000/speed;
        this.moveTime = 0;
        this.mediator = mediator;
        this.socializationTime = socializationTime;
        this.mask = mask;
        this.deathTime = (float)(100000*(1.0-mortalityRate));
        randomPosition();
        randomDirection();

        //states are created
        HEALTHY = new HealthyState(this);
        INFECTED = new InfectedState(this);
        HOSPITAL = new HospitalState(this);
        DEAD = new DeadState(this);
        COLLISION = new CollisionState(this);
        SICK_COLLISION = new SickCollisionState(this);
        //starts with healthy state
        state = HEALTHY;

    }

    /**
     * method to paint individual in desired location
     * @param g graphics object
     */
    public void render(Graphics g) {
        state.render(g);
    }

    /**
     * method to update individual behaviour
     * @param deltaTime time passed between update calls
     */
    public void update(float deltaTime){
        state.update(deltaTime);
    }

    /**
     * method to set state of the individual
     * @param state state object
     */
    public void setState(State state){
        this.state = state;
    }

    /**
     * method to update location of the individual
     */
    public void move(){
        int nextX, nextY;
        nextX = posX + velX;
        nextY = posY + velY;

        if ((nextX > 995) || (nextX < 3)){
            velX *= -1;
        }
        if ((nextY > 595) || (nextY < 3)){
            velY *= -1;
        }
        posX += velX;
        posY += velY;
    }

    /**
     * method to update the location of the individual by desired amount
     * @param socialDistance amount of relocation
     */
    public void moveDesired(int socialDistance){
        int nextX, nextY;
        nextX = posX + (velX*socialDistance) + 5*velX;
        nextY = posY + (velY*socialDistance) + 5*velY;

        if ((nextX > 995) || (nextX < 3)){
            velX *= -1;
        }
        if ((nextY > 595) || (nextY < 3)){
            velY *= -1;
        }
        posX += (velX*socialDistance) + 5*velX;
        posY += (velY*socialDistance) + 5*velY;
    }

    /**
     * method to get x-axis position value of individual
     * @return x-axis position value
     */
    public int getPosX(){
        return posX;
    }

    /**
     * method to get y-axis position value of individual
     * @return y-axis position value
     */
    public int getPosY(){
        return posY;
    }

    /**
     * method to get mask coefficient of the individual
     * @return mask coefficient
     */
    public double getMask() {
        return mask;
    }

    /**
     * method to get state of the individual
     * @return state object
     */
    public State getState() {
        return this.state;
    }

    /**
     * method to assign random position to individual
     */
    public void randomPosition(){
        Random rand = new Random();
        posX = rand.nextInt(995);
        posY = rand.nextInt(595);
    }

    /**
     * method to assign random direction to individual
     */
    public void randomDirection(){
        Random rand = new Random();

        velX = (rand.nextInt(3) - 1);
        velY = (rand.nextInt(3) - 1);

        if (velX == 0 && velY == 0){
            if (rand.nextInt(2) == 0 ){
                velX = 1;
            }else {
                velY = 1;
            }
        }
    }

    /**
     * method to bounce the object
     */
    public void bounce(){
        velX *= -1;
        velY *= -1;
    }

    /**
     * method to get healthy state of the individual
     * @return healthy state object
     */
    public State getHEALTHY() {
        return HEALTHY;
    }

    /**
     * method to get infected state of the individual
     * @return infected state object
     */
    public State getINFECTED() {
        return INFECTED;
    }

    /**
     * method to get hospital state of the individual
     * @return hospital state object
     */
    public State getHOSPITAL() {
        return HOSPITAL;
    }

    /**
     * method to get dead state of the individual
     * @return dead state object
     */
    public State getDEAD() {
        return DEAD;
    }

    /**
     * method to get collision state of the individual
     * @return collision state object
     */
    public State getCOLLISION() {
        return COLLISION;
    }

    /**
     * method to get sick collision state of the individual
     * @return sick collision state object
     */
    public State getSICK_COLLISION() {
        return SICK_COLLISION;
    }
}

