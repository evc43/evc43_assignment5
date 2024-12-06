public class WordSearchPuzzle implements WordSearchInterface {

    @Override
    public boolean isIncreasingSequencePossible(char[][] grid, int startRow, int startCol, int endRow, int endCol) {
        // Validate indices
        if (startRow < 0 || startCol < 0 || endRow < 0 || endCol < 0 ||
            startRow >= grid.length || startCol >= grid[0].length ||
            endRow >= grid.length || endCol >= grid[0].length) {
            throw new IndexOutOfBoundsException("One or more indices are out of bounds.");
        }

        // Use a visited array to track visited cells
        boolean[][] visited = new boolean[grid.length][grid[0].length];
        
        // Perform a depth-first search (DFS)
        return dfs(grid, startRow, startCol, endRow, endCol, Character.MIN_VALUE, visited);
    }

    private boolean dfs(char[][] grid, int row, int col, int targetRow, int targetCol, char prevChar, boolean[][] visited) {
        // Base case: if we reach the target cell
        if (row == targetRow && col == targetCol) {
            return true;
        }

        // Check bounds and if the current cell is valid
        if (row < 0 || row >= grid.length || col < 0 || col >= grid[0].length ||
            visited[row][col] || grid[row][col] <= prevChar) {
            return false;
        }

        // Mark the current cell as visited
        visited[row][col] = true;

        // Offsets for 8 possible directions
        int[] rowOffsets = {-1, -1, -1, 0, 0, 1, 1, 1};
        int[] colOffsets = {-1, 0, 1, -1, 1, -1, 0, 1};

        // Explore all 8 directions
        for (int i = 0; i < 8; i++) {
            int newRow = row + rowOffsets[i];
            int newCol = col + colOffsets[i];
            if (dfs(grid, newRow, newCol, targetRow, targetCol, grid[row][col], visited)) {
                return true;
            }
        }

        // Backtrack: unmark the current cell
        visited[row][col] = false;
        return false;
    }
}
