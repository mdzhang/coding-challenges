/**
 * Yodle Application
 * Juggler Program
 *
 * {@link Circuit} is a juggling circuit consisting of several tricks,
 * which emphasizes different skills (hand-to-eye coordination, endurance, 
 * pizzazz)
 *
 * @author: Michelle D. Zhang
 */
public class Circuit implements Comparable<Circuit> {
    
    private int handEyeCoordination;
    private int endurance;
    private int pizzazz;
    private String name;

    public Circuit(String n) {
        this.name = n;
    }

    public Circuit(String n, int h, int e, int p) {
        this.name = n;
        this.handEyeCoordination = h;
        this.endurance = e;
        this.pizzazz = p;
    }

    // getters
    public int getHandEyeCoordination() { return this.handEyeCoordination; }
    public int getEndurance() { return this.endurance; }
    public int getPizzazz() { return this.pizzazz; }
    public String getName() { return this.name; }

    // setters
    public void setHandEyeCoordination(int h) { this.handEyeCoordination = h; }
    public void setEndurance(int e) { this.endurance = e; }
    public void setPizzazz(int p) { this.pizzazz = p; }

    public String toString() {
        return "C " + this.name 
               + " H:" + this.handEyeCoordination
               + " E:" + this.endurance
               + " P:" + this.pizzazz
        ;
    }

    /**
     * Create a circuit object from a given string
     * eg "C C1 H:2 E:1 P:1"
     *
     * @param String
     *          string to convert to circuit
     * @return Circuit
     *          new circuit object
     */
    public static Circuit parseCircuit(String s) {
        String[] parts = s.split(" ");

        return new Circuit(parts[1]
                , Integer.parseInt(parts[2].substring(2))
                , Integer.parseInt(parts[3].substring(2))
                , Integer.parseInt(parts[4].substring(2))
        );
    }

    /**
     * Orders circuit based on alphabetic order of circuit name
     *
     * @param Circuit
     *          circuit to compare this circuit to
     * @return int
     */
    public int compareTo(Circuit other) {
        return this.name.compareTo(other.name);
    }

    public boolean equals(Circuit other) {
        if(this.name.equals(other.name))
            return true;
        return false;
    }
}
