import java.util.*;
/**
 * Yodle Application
 * Juggler Program
 *
 * {@link Assignment} represents a circuit and all the
 * juggler's assigned to this circuit
 *
 * @author: Michelle D. Zhang
 */
public class Assignment implements Comparable<Assignment> {
    private Circuit circuit;
    private ArrayList<ScoredJuggler> jugglers;
    private int min;
    private int minIndex;
    private int slots;
    private int jugglersAssigned;

    public Assignment(Circuit c) {
        this.circuit = c;
        this.min = 0;
        this.minIndex = 0;
        this.jugglersAssigned = 0;
        this.jugglers = new ArrayList<ScoredJuggler>();
    }

    public Circuit getCircuit() { return this.circuit; }
    public void setSlots (int s) { this.slots = s; }

    /**
     * Given a juggler, will assign that juggler to this circuit or not.
     *
     * @param Juggler to consider assigning
     * @return Juggler that needs to be reassigned to different circuit
     */
    public Juggler assignJuggler(Juggler j) {
        ScoredJuggler temp = new ScoredJuggler(j, this.circuit);
        int score = temp.getScore();

        // if circuit not yet full, automatically accept next juggler assigned
        if(!this.isFull()) {
            this.jugglers.add(temp);
            this.jugglersAssigned++;
            j.setAssigned(true);
            return null;
        } 

        Collections.sort(this.jugglers);

        // if the new juggler's score is not higher than the scores of
        // jugglers already assigned, do not assign
        if(score <= this.jugglers.get(0).getScore())
            return j;

        // if the new juggler's score IS higher, the old juggler will
        // be unassigned and replaced by the new juggler
        Juggler old = this.jugglers.get(0).getJuggler();
        old.setAssigned(false);
        this.jugglers.set(0, temp);
        j.setAssigned(true);
        return old; 
    }

    /**
     * Each circuit has a limited number of slots for jugglers,
     * so when the number of jugglers assigned to this assignment
     * is the same as the number of slots, the assignment is full
     *
     * @param None
     * @return boolean
     */
    public boolean isFull() {
        return this.jugglersAssigned == this.slots ? true : false;
    }

    /**
     * Convert this assignment to a string representation
     * eg C0 J11 C0:154 C1:27 C2:108, J4 C0:122 C2:106 C1:23, etc
     *
     * @param None.
     * @return String.
     */
    public String toString() {
        String jugglerString = "";
        for(ScoredJuggler j : this.jugglers) {
            jugglerString += j.getJuggler().toStringWithScores() + ", ";
        }
        // get rid of trailing comma and space
        jugglerString = jugglerString.substring(0, jugglerString.length() - 2);

        return this.circuit.getName() + " " + jugglerString;
    }

    public int compareTo(Assignment other) {
        return this.circuit.compareTo(other.getCircuit());
    }

    // this class keeps track of a juggler, and that juggler's score for
    // a specific circuit. It is only used in Assignment and so is a 
    // nested class. Instead of constantly recalculating score (by getting a
    // juggler and browsing through their preferences or redoing the 
    // dot product), I thought it better just to pair these two together
    // in a small class
    private class ScoredJuggler implements Comparable<ScoredJuggler> {
        private Juggler juggler;
        private int score;

        public ScoredJuggler(Juggler j, Circuit c) {
            this.juggler = j;
            this.score = j.getCircuitScore(c);
        }

        public int getScore() { return this.score; }
        public Juggler getJuggler() { return this.juggler; }

        public int compareTo(ScoredJuggler s) {
            if(this.score == s.score)
                return 0;
            return this.score > s.score ? 1 : -1;
        }
    }
}
