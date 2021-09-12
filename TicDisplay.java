class TicDisplay implements BoardListener {

    private final String[][] board;

    public TicDisplay(int boardSize) {

        final int rows = boardSize, cols = boardSize;

        // Add each button to the frame and the board.
        board = new String[rows][cols];
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                board[r][c] = "-";
            }
        }
    }

    protected void delay() {
        try {
            final int delayTime = 3000;
            Thread.sleep(delayTime);
        } catch (Exception e) {
        }
    }

    protected void showBoard(char[][] board) {
        for (int r = 0; r < board.length; r++) {
            for (int c = 0; c < board[0].length; c++)
                System.out.print(board[r][c] + " ");
            System.out.print("\n");
        }
        System.out.print("\n");
    }

    @Override
    public void boardChanged(char[][] board) {
        showBoard(board);
    }
}
