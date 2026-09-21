
// @edu:student-assignment

package uq.comp3506.a2;


// This is part of COMP3506 Assignment 2. Students must implement their own solutions.

/**
 * Tracks accessible cells in a square grid and detects a path from its left edge to its
 * right edge.
 */
public class Pathfinder {

    private final int dimension;

    /**
     * Creates a pathfinder whose cells are initially inaccessible.
     *
     * @param dimension the width and height of the grid
     */
    public Pathfinder(int dimension) {
        this.dimension = dimension;

        // Implement the initialisation logic
    }
    
    /**
     * Marks a cell as accessible.
     *
     * @param x the cell's horizontal coordinate, in the range {@code [0, dimension)}
     * @param y the cell's vertical coordinate, in the range {@code [0, dimension)}
     */
    public void makeCellAccessible(int x, int y) {

    }

    /**
     * Reports whether adjacent accessible cells form a path from the left edge of the grid
     * to the right edge.
     *
     * @return {@code true} if such a path exists; otherwise {@code false}
     */
    public boolean doesPathExist() {
        return false;
    }
}
