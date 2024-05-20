public class GameOfLife {
    private final int rows;
    private final int cols;
    private boolean[][] board;

    public GameOfLife(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.board = new boolean[rows][cols];
    }

    public void setInitialState(boolean[][] initialState) {
        this.board = initialState;
    }

    public void nextGeneration() {
        boolean[][] newBoard = new boolean[rows][cols];

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                int liveNeighbors = countLiveNeighbors(row, col);
                if (board[row][col]) {
                    newBoard[row][col] = liveNeighbors == 2 || liveNeighbors == 3;
                } else {
                    newBoard[row][col] = liveNeighbors == 3;
                }
            }
        }

        board = newBoard;
    }

    private int countLiveNeighbors(int row, int col) {
        int count = 0;
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (i == 0 && j == 0) continue;
                int newRow = (row + i + rows) % rows;
                int newCol = (col + j + cols) % cols;
                if (board[newRow][newCol]) count++;
            }
        }
        return count;
    }

    public boolean[][] getBoard() {
        return board;
    }
}