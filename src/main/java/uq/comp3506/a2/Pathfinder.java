
// @edu:student-assignment

package uq.comp3506.a2;


// This is part of COMP3506 Assignment 2. Students must implement their own solutions.

/**
 * Tracks accessible cells in a square grid and detects a path from its left edge to its
 * right edge.
 */
public class Pathfinder {

    private final int dimension;
    private final int[] parent;
    private final boolean[] isAccessible;

    private final int vRight;
    private final int vLeft;

    /**
     * Creates a pathfinder whose cells are initially inaccessible.
     *
     * @param dimension the width and height of the grid
     */
    public Pathfinder(int dimension) {
        this.dimension = dimension;

        // Implement the initialisation logic
        int totalCells = dimension * dimension;
        this.parent = new int[totalCells + 2];
        this.isAccessible = new boolean[totalCells + 2];

        this.vLeft = totalCells;
        this.vRight = totalCells + 1;

        for (int i = 0; i < totalCells + 2; i++) {
            parent[i] = i;
        }

        this.isAccessible[vLeft] = true;
        this.isAccessible[vRight] = true;

    }

    private int getIndex(int x, int y) {
        return y * dimension + x;
    }

    private int find(int i) {
        while (i != parent[i]) {
            parent[i] = parent[parent[i]];
            i = parent[i];
        }
        return i;
    }

    private void union(int idxA, int idxB) {
        int rootA = find(idxA);
        int rootB = find(idxB);

        parent[rootA] = rootB;
    }

    private boolean inBounds(int x, int y) {
        return x >= 0 && x < dimension && y >= 0 && y < dimension;
    }

    private void checkAndUnion(int x, int y, int originIdx) {

        if (inBounds(x, y)) {
            int flatIdx = getIndex(x, y);
            if(isAccessible[flatIdx]) {
                union(flatIdx, originIdx);
            }
        }

    }

    /**
     * Marks a cell as accessible.
     *
     * @param x the cell's horizontal coordinate, in the range {@code [0, dimension)}
     * @param y the cell's vertical coordinate, in the range {@code [0, dimension)}
     */
    public void makeCellAccessible(int x, int y) {
        int idx = getIndex(x, y);
        isAccessible[idx] = true;

        checkAndUnion(x - 1, y, idx);
        checkAndUnion(x + 1, y, idx);
        checkAndUnion(x, y - 1, idx);
        checkAndUnion(x, y + 1, idx);

        if (x == 0) {
            union(idx, vLeft);
        }
        if (x == dimension - 1) {
            union(idx, vRight);
        }
    }

    /**
     * Reports whether adjacent accessible cells form a path from the left edge of the grid
     * to the right edge.
     *
     * @return {@code true} if such a path exists; otherwise {@code false}
     */
    public boolean doesPathExist() {
        return find(vLeft) == find(vRight);
    }
}
