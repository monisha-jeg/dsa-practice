package sample_problems;

/**
 * Find and print unique shortest paths from (a, b) to (x, y) in a grid along with path
 * lengths and return count of unique paths.
 */
class GridWalk {
    static int findPathsRecursive(int a, int b, int x, int y, int numRows, int numCols) {
        return findPathsRecursive(a, b, x, y, 0, "", numRows, numCols);
    }

    static int findPathsRecursive(int a, int b, int x, int y, int pathLength, String path, int numRows, int numCols) {
        if (a == x && b == y) {
            System.out.println(path + " (length: " + pathLength + ")");
            return 1;
        }

        int count = 0;
        if (a < x && a < numRows - 1) {
            count += findPathsRecursive(a + 1, b, x, y, pathLength + 1, path + "R", numRows, numCols);
        }
        if (b < y && b < numCols - 1) {
            count += findPathsRecursive(a, b + 1, x, y, pathLength + 1, path + "L", numRows, numCols);
        }
        return count;
    }

    static int countPathsIterativeDynamic(int a, int b, int x, int y, int numRows, int numCols) {
        int[][] numPathsDp = new int[numRows][numCols];
        numPathsDp[a][b] = 1;

        for (int i = a; i <= x; i++) {
            for (int j = b; j <= y; j++) {
                if (i == a && j == b)
                    continue; // Skip the start position
                int fromLeft = (i > a) ? numPathsDp[i - 1][j] : 0;
                int fromBelow = (j > b) ? numPathsDp[i][j - 1] : 0;
                numPathsDp[i][j] = fromLeft + fromBelow;
            }
        }

        return numPathsDp[x][y];
    }

    public static void main(String[] args) {
        System.out.println("Count Paths Recursive: " + findPathsRecursive(1, 1, 3, 5, 6, 6));
        System.out.println("Count Paths Iterative Dynamic: " + countPathsIterativeDynamic(1, 1, 3, 5, 6, 6));
    }
}
