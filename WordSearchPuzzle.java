public class WordSearchPuzzle implements WordSearchInterface {

    @Override
    public boolean isIncreasingSequencePossible(char[][] grid, int startRow, int startCol, int endRow, int endCol) {
        // Validate indices
        if (startRow < 0 || startCol < 0 || endRow < 0 || endCol < 0 ||
            startRow >= grid.length || startCol >= grid[0].length ||
            endRow >= grid.length || endCol >= grid[0].length) {
            throw new IndexOutOfBoundsException("One or more indices are out of bounds.");
        }

        // Perform a depth-first search (DFS) or breadth-first search (BFS)
        // to find a path with strictly increasing alphabetical order.
        return dfs(grid, startRow, startCol, endRow, endCol, Character.MIN_VALUE);
    }

    private boolean dfs(char[][] grid, int row, int col, int targetRow, int targetCol, char prevChar) {
        if (row == targetRow && col == targetCol) {
            return true;
        }

        // Temporarily mark the cell as visited
        char current = grid[row][col];
        if (current <= prevChar) {
            return false;
        }
        
        grid[row][col] = '*';  // Mark as visited
        
        int[] rowOffsets = {-1, -1, -1, 0, 0, 1, 1, 1};
        int[] colOffsets = {-1, 0, 1, -1, 1, -1, 0, 1};

        for (int i = 0; i < 8; i++) {
            int newRow = row + rowOffsets[i];
            int newCol = col + colOffsets[i];
            if (newRow >= 0 && newRow < grid.length && newCol >= 0 && newCol < grid[0].length &&
                grid[newRow][newCol] != '*' && dfs(grid, newRow, newCol, targetRow, targetCol, current)) {
                return true;
            }
        }

        grid[row][col] = current;  // Unmark as visited
        return true;
    }
}
