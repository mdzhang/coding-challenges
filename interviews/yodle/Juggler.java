/**
 * Yodle Application
 * Juggler Program
 *
 * {@link Juggler} represents a juggler with different skills eg
 * hand-to-eye coordination, endurance, pizzazz, and a number
 * of preferences ie circuits this juggler would like to be assigned to
 *
 * @author: Michelle D. Zhang
 */
public class Juggler {
    
    private String name;
    private int handEyeCoordination;
    private int endurance;
    private int pizzazz;
    private boolean assigned;
    private Preference[] preferences;
    private int preferenceIndex;

    public Juggler(String n, int h, int e, int p, String[] pref) {
        this.name = n;
        this.handEyeCoordination = h;
        this.endurance = e;
        this.pizzazz = p;
        this.assigned = false;
        this.preferences = new Preference[pref.length];
        this.preferenceIndex = 0;

        for(int i = 0; i < pref.length; i++)
            this.preferences[i] = new Preference(pref[i]);
    }

    // getters
    public int getHandEyeCoordination() { return this.handEyeCoordination; }
    public int getEndurance() { return this.endurance; }
    public int getPizzazz() { return this.pizzazz; }
    public boolean isAssigned() { return this.assigned; }
    public Preference[] getPreferences() { return this.preferences; }
    public Preference getPreference(int i) { return this.preferences[i]; }
    public int getPreferenceIndex() { return this.preferenceIndex; }

    // setters
    public void setHandEyeCoordination(int h) { this.handEyeCoordination = h; }
    public void setEndurance(int e) { this.endurance = e; }
    public void setPizzazz(int p) { this.pizzazz = p; }
    public void setAssigned(boolean a) { this.assigned = a; }
    public void setPreferences(Preference[] p) { this.preferences = p; }
    public void setPreferenceIndex(int i) { this.preferenceIndex = i; }

    public int incPrefIndex() { 
        this.preferenceIndex++; 
        if(this.preferenceIndex > this.preferences.length - 1) {
            this.preferenceIndex--;
            return -1;
        }
        return 0;
    }

    /**
     * For each circuit preference, find the dot product of the 
     * juggler's and circuit's skills and set that as the
     * preference's score
     *
     * @param None.
     * @return None.
     */
    public void scorePreferences() {
        Circuit c = null;
        for(Preference p : this.preferences) {
            c = p.getCircuit();

            p.setScore(
                this.getHandEyeCoordination() * c.getHandEyeCoordination()
              + this.getEndurance() * c.getEndurance()
              + this.getPizzazz() * c.getPizzazz()
            );
        }
    }

    /**
     * Find out what the score (dot product) of this juggler for the
     * specified circuit is. 
     *
     * @param Circuit c
     *          for which you would like to find the juggler's score
     * @return int
     *          score for this juggler for given circuit 
     */
    public int getCircuitScore(Circuit c) {
        return this.getHandEyeCoordination() * c.getHandEyeCoordination()
               + this.getEndurance() * c.getEndurance()
               + this.getPizzazz() * c.getPizzazz();

       /*
        for(Preference p : this.preferences)
            if(p.getCircuit().equals(c))
                return p.getScore();
        return -1;
        */
    }

    /**
     * Convert this object to a string representation 
     * eg J J0 H:3 E:9 P:2 C2,C0,C1
     *
     * @param None.
     * @return String
     *          String representation of this juggler
     */
    public String toString() {
        String prefString = this.preferences[0].getCircuitName();
        for(int i = 1; i < this.preferences.length; i++) {
            prefString += "," + this.preferences[i].getCircuitName();
        }

        return "J " + this.name 
               + " H:" + this.handEyeCoordination
               + " E:" + this.endurance
               + " P:" + this.pizzazz
               + " " + prefString
        ;
    }

    /**
     * Convert this object to a string representation with this juggler's
     * circuit preferences and this juggler's score for each circuit
     *
     * @param None
     * @return String
     */
    public String toStringWithScores() {
        String scoredPrefs = this.preferences[0].getCircuitName() +
            ":" + this.preferences[0].getScore();

        for(int i = 1; i < this.preferences.length; i++) {
            scoredPrefs += " " + this.preferences[i].getCircuitName();
            scoredPrefs += ":" + this.preferences[i].getScore();
        }

        return this.name + " " + scoredPrefs;
    }

    /**
     * Create a juggler object from a string representation
     *
     * @param String
     * @return Juggler
     */
    public static Juggler parseJuggler(String s) {
        String[] parts = s.split(" ");

        return new Juggler(parts[1]
                , Integer.parseInt(parts[2].substring(2))
                , Integer.parseInt(parts[3].substring(2))
                , Integer.parseInt(parts[4].substring(2))
                , parts[5].split(",")
        );
    }
}
