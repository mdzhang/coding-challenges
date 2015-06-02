import java.io.*;
import java.util.*;
/**
 * Yodle Application
 * Juggler Program
 *
 * {@link JuggleFest} is the tester class. The main method takes
 * in the input and output file names. Then the rest of the class
 * reads the input file, converts it to ArrayLists of Jugglers and
 * Assignments, scores the jugglers, matches them to circuits, and
 * writes the results to the output file.
 *
 * @author: Michelle D. Zhang
 */
public class JuggleFest {

    private ArrayList<Juggler> jugglers;
    private ArrayList<Juggler> remainingJugglers;
    private ArrayList<Assignment> assignments;
    private int jugglersPerCircuit;

    public JuggleFest(String inFile, String outFile) {
        this.jugglers = new ArrayList<Juggler>();
        this.assignments = new ArrayList<Assignment>();
        this.remainingJugglers = new ArrayList<Juggler>();

        readJugglersAndCircuits(inFile);
        scoreJugglerPreferences();
        matchJugglersToCircuits();
        writeAssignments(outFile);
    }

    /**
     * Read in jugglers and circuits from input file, create Juggler or
     * Assignment objects after parsing and add to arraylists.
     *
     * @param String
     *          name of input file
     * @return None.
     */
    private void readJugglersAndCircuits(String fileName) {
        String line;
        boolean readCircuits = true;
        BufferedReader in = null;
        int jugglerCount = 0, circuitCount = 0;

        try {
            in = new BufferedReader(new FileReader(fileName));

            while((line = in.readLine()) != null) {
                if(line.equals("")) {
                    readCircuits = !readCircuits;
                    continue;
                }
                if(readCircuits) {
                    this.assignments.add(new 
                        Assignment( Circuit.parseCircuit(line) ));
                    circuitCount++;
                } else {
                    this.jugglers.add(Juggler.parseJuggler(line));
                    jugglerCount++;
                }
            }

            this.jugglersPerCircuit = jugglerCount/circuitCount;
            for(Assignment a : this.assignments) 
                a.setSlots(this.jugglersPerCircuit);

        } catch(IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if(in != null) in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * After calculating assignments, write them to output file.
     *
     * @param String
     *          output file name
     * @return None.
     */
    private void writeAssignments(String fileName) {
        PrintWriter out = null;
        try {
            out = new PrintWriter(fileName);

            for(Assignment a : assignments) 
                out.write(a.toString() + "\n");

        } catch(FileNotFoundException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        } finally {
            if(out != null) out.close();
        }
    }

    /**
     * Associate the name of a circuit in Juggler preferences with actual
     * circuit object, then score each of juggler's preferences
     *
     * @param None.
     * @return None.
     */
    private void scoreJugglerPreferences() {
        Preference[] preferences;
        int preferenceCount;

        Collections.sort(this.assignments);

        for(Juggler j: this.jugglers) {
            preferenceCount = j.getPreferences().length;
            for(int i = 0; i < preferenceCount; i++) {
                j.getPreference(i).setCircuit(
                    findCircuitByName( j.getPreference(i).getCircuitName() )
                );
            }

            j.scorePreferences();
        }
    }

    /**
     * Given a circuit name string, find the circuit object in our arraylist
     * of Circuits that the name corresponds to. Assumes unique names. Called
     * in scoreJugglers to associate juggler preference string w/ object.
     *
     * @param circuitName
     *          String name of circuit
     * @return Circuit
     *          circuit object name corresponds to
     */
    private Circuit findCircuitByName(String circuitName) {
        return this.assignments.get( Collections.binarySearch(
               this.assignments, new Assignment( new Circuit(circuitName)) ) )
                .getCircuit();
    }

    /**
     * In the outer loop, we loop over every juggler in the arraylist, 
     * attempting to assign that juggler to its first choice circuit. If the 
     * juggler can't be assigned to that circuit (if circuit is full and 
     * current juggler's score is lower than all scores of juggler's already 
     * assigned to that circuit), it will be returned by the assignJuggler() 
     * call. if the current juggler replaces a juggler that had already been 
     * assigned to that circuit, the juggler that was replaced will be 
     * returned. So we enter a while loop that keeps trying to assign a 
     * juggler (current or replaced), looking at the next circuit in that
     * juggler's preference list each time.
     *
     * @param None.
     * @return None.
     */
    private void matchJugglersToCircuits() {
        Collections.sort(this.assignments);
        Assignment assignment = null;

        for(Juggler j : this.jugglers) {
            assignment = findNextAssignmentPreference(j);
            while((j = assignment.assignJuggler(j)) != null) {
                if((j.incPrefIndex()) == -1) {
                    this.remainingJugglers.add(j);
                    break;
                }
                assignment = findNextAssignmentPreference(j);
            }
        }


        if(this.remainingJugglers.size() > 0)
            matchRemainingJugglers();
    }

    /**
     * For jugglers that weren't good enough to enter any of the circuits on 
     * their preferences list, assign them to the next circuit that needs more 
     * jugglers.
     *
     * @param None.
     * @return None.
     */
    public void matchRemainingJugglers() {
        for(Assignment a : this.assignments) {
            if(a.isFull()) continue;
            while(!a.isFull())
                a.assignJuggler(this.remainingJugglers.remove(0));
        }
    }

    /**
     * Each juggler has an ordered list of preferred circuits they'd like
     * to participate in. An assignment contains a circuit inside of it. We
     * look for the next circuit in the juggler's preferences that we have 
     * not yet attempted to assign that juggler to (preferenceIndex), and find 
     * the assignment in JugglerFest's assignment array with a circuit that 
     * matches it. We then return that assignment.
     *
     * @param Juggler
     * @return Assignment
     */
    private Assignment findNextAssignmentPreference(Juggler j) {
        return this.assignments.get( 
            Collections.binarySearch(
                this.assignments, new Assignment(
                    j.getPreference(j.getPreferenceIndex()).getCircuit()
                )
            )
        );
    }

    public static void main(String[] args) {
        if(args.length != 2) {
            System.out.println(
                "Usage: java JuggleFest <input file> <output file>"
            );
            System.exit(1);
        }

        JuggleFest j = new JuggleFest(args[0], args[1]);

    }

}

