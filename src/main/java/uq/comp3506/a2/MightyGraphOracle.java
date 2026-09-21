

// @edu:student-assignment

package uq.comp3506.a2;


// This is part of COMP3506 Assignment 2. Students must implement their own solutions.

/**
 * Provides limited access to a hidden directed acyclic graph and validates a proposed
 * longest path.
 */
public interface MightyGraphOracle {

    /**
     * Returns the number of vertices in the hidden graph.
     *
     * <p>Calling this method does not count toward the query limit.
     *
     * @return the number of vertices in the graph
     */
    public int numberOfNodes();

    /**
     * Returns the length of the longest path that starts at a specified vertex and contains
     * only vertices from a supplied set.
     *
     * <p>The path need not contain every supplied vertex. If no other supplied vertex is
     * reachable from {@code start}, the result is {@code 1}. If {@code start} is not present
     * in {@code nodes}, the result is {@code -1}. Calls to this method count toward the query
     * limit.
     *
     * @param start the vertex at which the path must start
     * @param nodes the vertices that the path may contain, including {@code start}
     * @return the longest permitted path length, or {@code -1} if {@code start} is absent
     */
    public int query(int start, int[] nodes);

    /**
     * Tests whether a sequence of vertices is a longest path in the hidden graph.
     *
     * <p>The vertices must be listed in traversal order: {@code nodes[0]} is the start,
     * {@code nodes[1]} is the next vertex, and so on. If several longest paths exist, any
     * one is accepted. Calling this method does not count toward the query limit.
     * However, you only have 10 attempts to test your solution per graph.
     *
     * @param nodes the vertices of the proposed path in traversal order
     * @return {@code true} if the proposed path is accepted; otherwise {@code false}
     */
    public boolean test(int[] nodes);
    
}
