package scr;
/**
 * Classe utilizzata per settare i dati degli attuatori da inviare al server in input
 */
public class Classe {
    private double accelerate;
    private double brake;
    private int gear;
    private double steering;

    // Costruttore
    public Classe(double accelerate, double brake, int gear, double steering) {
        this.accelerate = accelerate;
        this.brake = brake;
        this.gear = gear;
        this.steering = steering;
    }

    public Classe () {
        
    }

    // Getter and Setter for accelerate
    public double getAccelerate() {
        return accelerate;
    }

    public void setAccelerate(double accelerate) {
        this.accelerate = accelerate;
    }

    // Getter and Setter for brake
    public double getBrake() {
        return brake;
    }

    public void setBrake(double brake) {
        this.brake = brake;
    }

    // Getter and Setter for gear
    public int getGear() {
        return gear;
    }

    public void setGear(int gear) {
        this.gear = gear;
    }

    // Getter and Setter for steering
    public double getSteering() {
        return steering;
    }

    public void setSteering(double steering) {
        this.steering = steering;
    }
}