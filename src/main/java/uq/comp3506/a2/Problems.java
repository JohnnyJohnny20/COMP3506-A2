// @edu:student-assignment

package uq.comp3506.a2;

import uq.comp3506.a2.structures.Edge;
import uq.comp3506.a2.structures.TopologyType;


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
        HashSet<Long> oddNumbers = new HashSet<>();
        for (Long number : numbers) {
            boolean added = oddNumbers.add(number);
            if (!added) {
                oddNumbers.remove(number); // count is even
            }
        }

        long largest = -1;
        for (long number : oddNumbers) {
            if (number > largest) {
                largest = number;
            }
        }
        return largest;
    }


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
        return dummy;
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

    }


}
