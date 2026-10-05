// @edu:student-assignment

package uq.comp3506.a2;

import uq.comp3506.a2.structures.Edge;
import uq.comp3506.a2.structures.TopologyType;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

// This is part of COMP3506 Assignment 2. Students must implement their own solutions.

/**
 * Contains the entry points for all assignment problems.
 *
 * <p>Refer to the assignment specification for formal definitions and explanations.
 */
public class Problems {

    /**
     * Finds the largest element in numbers that repeats an odd number of times.
     * Values in "numbers" are guaranteed to be in [0, 2^32 -1]
     * <p>
     * Let {@code N} be the length of "numbers". The expected input sizes are:
     * <ul>
     *   <li>Basic tests: {@code N <= 100}</li>
     *   <li>Exhaustive tests: {@code N <= 10,000}</li>
     *   <li>Welcome to COMP3506: {@code N <= 1,000,000}</li>
     * </ul>
     *
     * @param numbers the sequence of numbers
     * @return the value of the largest number occurding an odd number of times
     * or {@code -1} if no number exists
     */
    public static long cantEven(Long[] numbers) {
        HashSet<Long> oddOccuringNumbers = new HashSet<>();
        for (Long number : numbers) {
            boolean added = oddOccuringNumbers.add(number);
            if (!added) {
                oddOccuringNumbers.remove(number); // count is even
            }
        }

        long largest = -1;
        for (long number : oddOccuringNumbers) {
            if (number > largest) {
                largest = number;
            }
        }
        return largest;
    }

    private record Point(int x, int y) {}


    /**
     * Finds the first command that makes a robot revisit a cell.
     *
     * <p>The robot starts at {@code (0, 0)} and executes the supplied commands in order.
     * Each command moves the robot up ({@code U}), down ({@code D}), left ({@code L}), or
     * right ({@code R}).
     *
     * <p>Let {@code N} be the number of commands. The expected input sizes are:
     * <ul>
     *   <li>Basic tests: {@code N <= 1,000}</li>
     *   <li>Exhaustive tests: {@code N <= 100,000}</li>
     *   <li>Welcome to COMP3506: {@code N <= 1,000,000}</li>
     * </ul>
     *
     * @param commands the sequence of movement commands
     * @return the zero-based index of the first command that revisits a cell, or {@code -1}
     *         if no cell is revisited
     */
    public static long firstVisited(String commands) {
        // This is just a placeholder.
        // Modify this method as you wish
        HashSet<Point> points = new HashSet<>();
        int X = 0;
        int Y = 0;
        Point start = new Point(X, Y);
        points.add(start);
        for (int i = 0; i < commands.length(); i++) {
            char dir = commands.charAt(i);
            switch (dir) {
                case 'U':
                    Y++;
                    break;
                case 'L':
                    X--;
                    break;
                case 'D':
                    Y--;
                    break;
                case 'R':
                    X++;
                    break;
            }
            Point point = new Point(X, Y);
            if (!points.add(point)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Creates a pathfinder for an initially inaccessible square grid.
     *
     * <p>Cells are made accessible one at a time with
     * {@link Pathfinder#makeCellAccessible(int, int)}. Two cells are adjacent when they share
     * an edge. The pathfinder reports whether adjacent accessible cells form a path between
     * the left and right edges of the grid.
     *
     * <p>Let {@code N} be the grid dimension, {@code M} the number of calls to
     * {@code makeCellAccessible}, and {@code Q} the number of calls to
     * {@link Pathfinder#doesPathExist()}. The expected input sizes are:
     * <ul>
     *   <li>Basic tests: {@code N <= 50}, {@code M <= 2,500}, and {@code Q <= 100}</li>
     *   <li>Exhaustive tests: {@code N <= 1,000}, {@code M <= 300,000}, and
     *       {@code Q <= 10,000}</li>
     *   <li>Welcome to COMP3506: {@code N <= 10,000}, {@code M <= 10,000,000}, and
     *       {@code Q <= 10,000,000}</li>
     * </ul>
     *
     * @param n the dimension of the grid
     * @return a pathfinder for an {@code n}-by-{@code n} grid
     */
    public static Pathfinder pathfinder(int n) {
        // This is just a placeholder.
        // Modify this method as you wish
        return new Pathfinder(n);
    }


    private static int find(int i, HashMap<Integer, Integer> parent) {
        while (i != parent.get(i)) {
            int currentParent = parent.get(i);
            int grandParent = parent.get(currentParent);
            parent.put(i, grandParent);
            i = grandParent;
        }
        return i;
    }

    private static void union(int idxA, int idxB, HashMap<Integer, Integer> parent) {
        int rootA = find(idxA, parent);
        int rootB = find(idxB, parent);

        parent.replace(rootA, rootB);
    }

    /**
     * Determines the topology of the burrow system represented by an edge list.
     *
     * <p>Vertex identifiers are not guaranteed to be contiguous or to fall within a given
     * range. Inputs contain no self-loops, duplicate edges, or isolated vertices.
     *
     * <p>Let {@code N} be the number of vertices. The expected input sizes are:
     * <ul>
     *   <li>Basic tests: {@code N <= 10}</li>
     *   <li>Exhaustive tests: {@code N <= 1,000}</li>
     *   <li>Welcome to COMP3506: {@code N <= 10,000}</li>
     * </ul>
     *
     * @param <S> the type of the vertex identifiers
     * @param <U> the type of the edge values
     * @param edgeList the edges that make up the graph
     * @return the graph's topology type
     */
    public static <S, U> TopologyType topologyDetection(List<Edge<S, U>> edgeList) {
        TopologyType dummy = TopologyType.UNKNOWN;
        int roots = 0;
        int cyclicRoots = 0;
        HashMap<Integer, Integer> parent = new HashMap<>(); // Key will be vertex, Value will be parent.
        HashSet<Integer> hasCycle = new HashSet<>();

        for (Edge<S, U> edge : edgeList) {
            int v1 = edge.getVertex1().getId();
            int v2 = edge.getVertex2().getId();

            if (!parent.containsKey(v1)) {
                parent.put(v1, v1); // set parent to itself
            }
            if (!parent.containsKey(v2)) {
                parent.put(v2, v2);
            }

            int root1 = find(v1, parent);
            int root2 = find(v2, parent);
            if (root1 == root2) {
                hasCycle.add(root1);
            } else {
                union(v1, v2, parent);
                if (hasCycle.contains(root1)) {
                    hasCycle.add(root2);
                }
            }
        }

        for (Integer i : parent.keySet()) {
            if (parent.get(i).equals(i)) {
                roots++;
                if (hasCycle.contains(i)) {
                    cyclicRoots++;
                }
            }
        }

        if (roots == 1 && cyclicRoots == 0) return TopologyType.CONNECTED_CONFIDENT;
        if (roots == 1 && cyclicRoots == 1) return TopologyType.CONNECTED_CONFUSING;
        if (roots > 1 && cyclicRoots == 0) return TopologyType.DISCONNECTED_CONFIDENT;
        if (roots > 1 && cyclicRoots == roots) return TopologyType.DISCONNECTED_CONFUSING;
        if (cyclicRoots > 0 && cyclicRoots < roots) return TopologyType.HYBRID;
        return dummy;
    }

    private static int determineNextNode(MightyGraphOracle oracle, ArrayList<Integer> candidates, int prev) {
        for (int candidate : candidates) {
            int[] test = new int[candidates.size()];
            for (int i = 0; i < candidates.size() - 1; i++) {
                if (candidates.get(i) != candidate) {
                    test[i] = candidates.get(i);
                }
            }
            test[candidates.size() - 1] = prev;
            if (oracle.query(prev, test) == 0) {
                return candidate;
            }
        }
        return -1;
    }

    /**
     * Finds and submits a longest path through a hidden directed acyclic graph.
     *
     * <p>The graph's vertices and edges can be inspected only through the supplied oracle.
     * Vertices are numbered from {@code 0} to {@code numberOfNodes() - 1}. The number of calls
     * to {@link MightyGraphOracle#query(int, int[])} is limited. This method must submit a
     * valid solution with {@link MightyGraphOracle#test(int[])} before returning.
     * You only have 10 attempts per graph to test your solution regardless of test tier.
     *
     * <p>Let {@code N} be the number of vertices and {@code Q} the maximum number of queries.
     * The expected input sizes are:
     * <ul>
     *   <li>Basic tests: {@code N <= 50} and {@code Q = N^2 = 2,500}</li>
     *   <li>Exhaustive tests: {@code N <= 500} and {@code Q = 10N = 5,000}</li>
     *   <li>Welcome to COMP3506: {@code N <= 1,000} and {@code Q = 2N = 2,000}</li>
     * </ul>
     *
     * @param graphOracle the oracle used to query the hidden graph and submit a solution
     */
    public static void graphExplorer(MightyGraphOracle graphOracle) {
        int n = graphOracle.numberOfNodes();
        int[] allNodes = new int[n];
        for (int i = 0; i < n; i++) {
            allNodes[i] = i;
        }
        ArrayList<ArrayList<Integer>> maxPaths = new ArrayList<>();
        for (int i = 0; i < n + 1 ; i++) {
            maxPaths.add(new ArrayList<>()); // create buckets
        }

        int longestPathLength = 0;
        // Fill buckets - nodes grouped by max path
        for (int i = 0; i < n; i++) {
            int pathLength = graphOracle.query(i,allNodes);
            maxPaths.get(pathLength).add(i);
            if (pathLength > longestPathLength) {
                longestPathLength = pathLength;
            }
        }

        for (int i = longestPathLength; i > 0; i--) {
            ArrayList<Integer> bucket = maxPaths.get(i);
            if (bucket.size() <= 1) {
                continue;
            }
            int candidate;
            if (i == longestPathLength) {
                candidate = bucket.getFirst();
            } else {
                candidate = determineNextNode(graphOracle, bucket, maxPaths.get(i+1).getFirst());
            }

            bucket.clear();
            bucket.add(candidate);
        }

        int[] longestPath = new int[n];
        for (int i = 0; i < n; i++) {
            if (!maxPaths.get(i).isEmpty()) {
                longestPath[i] = maxPaths.get(i).getFirst();
            }
        }
        graphOracle.test(longestPath);
    }
}
