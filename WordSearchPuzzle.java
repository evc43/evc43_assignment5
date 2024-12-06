public class WordSearchPuzzle implements WordSearchInterface {

    @Override
    public boolean isIncreasingSequencePossible(char[][] grid, int startRow, int startCol, int endRow, int endCol) {
        
        if (startRow < 0 || startCol < 0 || endRow < 0 || endCol < 0 ||
            startRow >= grid.length || startCol >= grid[0].length ||
            endRow >= grid.length || endCol >= grid[0].length) {
            throw new IndexOutOfBoundsException("One or more indices are out of bounds.");
        }

        
        boolean[][] visited = new boolean[grid.length][grid[0].length];
        
        
        return dfs(grid, startRow, startCol, endRow, endCol, Character.MIN_VALUE, visited);
    }

    private boolean dfs(char[][] grid, int row, int col, int targetRow, int targetCol, char prevChar, boolean[][] visited) {
        
        if (row == targetRow && col == targetCol) {
            return true;
        }

        
        if (row < 0 || row >= grid.length || col < 0 || col >= grid[0].length ||
            visited[row][col] || grid[row][col] <= prevChar) {
            return false;
        }

        
        visited[row][col] = true;

        
        int[] rowOffsets = {-1, -1, -1, 0, 0, 1, 1, 1};
        int[] colOffsets = {-1, 0, 1, -1, 1, -1, 0, 1};

        
        for (int i = 0; i < 8; i++) {
            int newRow = row + rowOffsets[i];
            int newCol = col + colOffsets[i];
            if (dfs(grid, newRow, newCol, targetRow, targetCol, grid[row][col], visited)) {
                return true;
            }
        }

       
        visited[row][col] = false;
        return false;
    }
}
