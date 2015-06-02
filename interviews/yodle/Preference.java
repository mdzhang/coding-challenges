/**
 * Yodle Application
 * Juggler Program
 *
 * {@link Preference} represents a circuit and a specific
 * Juggler's score for that circuit
 *
 * @author: Michelle D. Zhang
 */
public class Preference {
    private String circuitName;
    private Circuit circuit;
    private int score;

    public Preference(String circuitName) {
        this.circuitName = circuitName;
    }

    // getters
    public String getCircuitName() { return this.circuitName; }
    public Circuit getCircuit() { return this.circuit; }
    public int getScore() { return this.score; }

    // setters
    public void setCircuitName(String c) { this.circuitName = c; }
    public void setCircuit(Circuit c) { this.circuit = c; }
    public void setScore(int s) { this.score = s; }
}
