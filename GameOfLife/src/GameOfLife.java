public class GameOfLife {
    private final int size;
    private final int[][] grid;
    private final int[][] newGrid;
    private final int[] livingRules;
    private final int[] birthRules;

    public GameOfLife(int size, String liveRules, String birthRules) {
        this.size = size;
        this.grid = new int[size][size];
        this.newGrid = new int[size][size];
        this.livingRules = new int[10];
        this.birthRules = new int[10];

        for (char c : liveRules.toCharArray()) {
            int i = Character.getNumericValue(c);
            this.livingRules[i] = 1;
        }
        for (char c : birthRules.toCharArray()) {
            int i = Character.getNumericValue(c);
            this.birthRules[i] = 1;
        }
    }

    public void initializeRandom() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                grid[i][j] = Math.random() < 0.5 ? 0 : 1;
            }
        }
    }

    public void nextGeneration() {
        for (int x = 0; x < size; x++) {
            for (int y = 0; y < size; y++) {
                int neighbours = countNeighbours(x, y);
                if (grid[x][y] == 1) {
                    newGrid[x][y] = livingRules[neighbours];
                } else {
                    newGrid[x][y] = birthRules[neighbours];
                }
            }
        }
        for (int i = 0; i < size; i++) {
            System.arraycopy(newGrid[i], 0, grid[i], 0, size);
        }
    }

    private int countNeighbours(int x, int y) {
        int count = 0;
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (i == 0 && j == 0) continue;
                int ni = (x + i + size) % size;
                int nj = (y + j + size) % size;
                count += grid[ni][nj];
            }
        }
        return count;
    }

    public int[][] getGrid() {
        return grid;
    }
}