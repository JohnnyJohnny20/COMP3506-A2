/**
* Supplied by the COMP3506/7505 teaching team, Semester 2, 2026.
*/

import uq.comp3506.a2.Problems;
import uq.comp3506.a2.structures.Heap;
import uq.comp3506.a2.structures.UnorderedMap;
import uq.comp3506.a2.structures.Vertex;
import uq.comp3506.a2.structures.Edge;
import uq.comp3506.a2.structures.Entry;
import uq.comp3506.a2.structures.TopologyType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import uq.comp3506.a2.Problems;

public class TestProblems {

    // The series of tests that need to be implemented
    public static void testCantEven() {
        System.out.println("Testing 'I Literally Can't Even'");
    }

    public static void testDoominos() {
        System.out.println("Testing 'Doominos Pizza Delivery'");
    }

    public static void testCurst() {
        System.out.println("Testing 'Curst Pizza's Robot Blocker'");
    }

    // Here is an example of how you can make your own tests
    public static void testTunnelVision() {
        System.out.println("Testing 'Tunnel Vision'");

        List<Vertex<String>> vertices = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            vertices.add(new Vertex<>(i, "yeet"));
        }
        ArrayList<Edge<String, Character>> connectedGraph = new ArrayList<>(Arrays.asList(
            new Edge<>(vertices.get(0), vertices.get(1), 'G'),
            new Edge<>(vertices.get(0), vertices.get(2), 'U'),
            new Edge<>(vertices.get(1), vertices.get(4), 'R'),
            new Edge<>(vertices.get(1), vertices.get(2), 'K'),
            new Edge<>(vertices.get(1), vertices.get(3), 'E'),
            new Edge<>(vertices.get(2), vertices.get(5), 'Y'),
            new Edge<>(vertices.get(2), vertices.get(6), 'T'),
            new Edge<>(vertices.get(3), vertices.get(5), 'I'),
            new Edge<>(vertices.get(3), vertices.get(6), 'M'),
            new Edge<>(vertices.get(5), vertices.get(6), 'E'))
        );
        assert Problems.topologyDetection(connectedGraph) == TopologyType.CONNECTED_CONFUSING;


        ArrayList<Edge<String, Character>> disconnectedTree = new ArrayList<>(Arrays.asList(
            new Edge<>(vertices.get(0), vertices.get(1), 'B'),
            new Edge<>(vertices.get(0), vertices.get(2), 'A'),
            new Edge<>(vertices.get(1), vertices.get(4), 'R'),
            new Edge<>(vertices.get(3), vertices.get(6), 'R'),
            new Edge<>(vertices.get(5), vertices.get(6), 'Y'))
        );
        assert Problems.topologyDetection(disconnectedTree) == TopologyType.DISCONNECTED_CONFIDENT;

        // you can use these ones if you prefer jUnit testing instead
        // assertEquals(TopologyType.CONNECTED_GRAPH, Problems.topologyDetection(connectedGraph));
        // assertEquals(TopologyType.FOREST, Problems.topologyDetection(disconnectedTree));
    }

    // This one is hard to make your own tests for. You might like to just sit
    // down, come up with a strategy to solve the problem, and then hit gradescope
    // with your solution
    public static void testLabyrinth() {
        System.out.println("Testing 'The UQ Nephilim Labyrinth'");
    }

    // Try to call the given test based on the input
    public static void dispatch(String str) {
        switch (str.toLowerCase()) {
            case "canteven": 
                testCantEven();
                return;
            case "doominos":
                testDoominos();
                return;
            case "curst":
                testCurst();
                return;
            case "tunnelvision":
                testTunnelVision();
                return;
            case "labyrinth":
                testLabyrinth();
                return;
            default:
                throw new IllegalArgumentException("Unknown command: " + str);
        }
    }

    // Does what it says on the tin 
    private static void usage() {
        System.out.println("Usage: java TestProblems <commands>");
        System.out.println("Commands:");
        System.out.println("  canteven");
        System.out.println("  doominos");
        System.out.println("  curst");
        System.out.println("  tunnelvision");
        System.out.println("  labyrinth");
    }

    public static void main(String[] args) {
        
        // Basic checking - make sure a command is provided
        if (args.length == 0) {
            usage();
            return;
        }

        // Walk the commands and try to dispatch them
        for (int i = 0; i < args.length; ++i) {
            dispatch(args[i]);
        }

        // profit??
    }

}
