
        /* The basis for a Noughts and Crosses (TicTacToe) game
         * using a GridLayout of Buttons.
         * This class simply creates a grid of buttons
         * as a View of a TicTacToe game.
         * All of the underlying game functionality is provided
         * by an TTTGame object.
         */
class TicDisplay implements BoardListener {

    // The board of strings.
    private final String[][] board;

    public TicDisplay(int boardSize){

        final int rows = boardSize, cols = boardSize;

        // Add each button to the frame and the board.
        board = new String[rows][cols];
        for(int r = 0; r < rows; r++){
            for(int c = 0; c < cols; c++){
                board[r][c] = "-";
            }
        }
    }

        // Select the given squares and set the appropriate symbol.
        // Return true if the square was free.
    public void showSquare(int row,int col,char symbol){
        // Pause before showing the move.
        delay();
        //final JButton[][] board = getBoard();
        final String[][] board = getBoard();
        board[row][col] = symbol+"";
        //showBoard();
    }

    public void clear(){
        // Pause before clearing.
        delay();
        final String[][] board = getBoard();
        for(int r = 0; r < board.length; r++)
            for(int c = 0; c < board[0].length; c++)
                board[r][c] = "-";
       // showBoard();
    }

    protected void delay(){
        try{
            final int delayTime = 3000;
            Thread.sleep(delayTime);
        }
        catch(Exception e){}
    }

    protected void showBoard(){
        for(int r = 0; r < board.length; r++){
            for(int c = 0; c < board[0].length; c++)
                System.out.print(board[r][c] + " ");
	    System.out.print("\n");
        }
	System.out.print("\n");
    }

    @Override
    public void boardChanged(char[][] board){
        showBoard();
//        int size = board.length;
//        for(int row = 0; row < size; row++){
//            for(int col = 0; col < size; col++){
//                System.out.print(board[row][col]);
//            }
//            System.out.println();
//        }
    }

    protected String[][] getBoard(){
        return board;
    }


}
