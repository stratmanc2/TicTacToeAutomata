    // This class implements the grid structure used for the board.
    // All board operations and enquiries are made via this class.
public class TicGame {
    // The minimum grid size.
    public static final int MINIMUM_SIZE = 3;

    // The characters used to mark the board.
    public static final char Free = '.', Nought = 'O', Cross = 'X';

    // Game state indicators.
    public static final int NoughtWon = 0, CrossWon = 1, Drawn = 2,
                            Unfinished = 3;

    // The board. Initialized by the constructor according to the
    // required size.
    private final char[][] grid;

    // If no grid size is specified, use the normal size of 3x3.
    public TicGame(){
        this(MINIMUM_SIZE);
    }

        // Create a size-by-size grid, as long as size fits the minimum.
    public TicGame(int size){
        if(size < MINIMUM_SIZE){
            System.out.println(size+" must be at least "+MINIMUM_SIZE);
            // Use the MINIMUM_SIZE.
            size = MINIMUM_SIZE;
        }
        // Create the grid.
        grid = new char[size][size];
        // Fill the grid with Free indicators.
        clearBoard();
    }

    public void clearBoard(){
        char[][] board = getGrid();
        final int size = getBoardSize();

        for(int row = 0; row < size; row++){
            for(int col = 0; col < size; col++){
                board[row][col] = Free;
            }
        }
    }

    // check to see if board has free space
    private boolean hasFreeSpace(){
        int size = grid.length;
        for (int i = 0; i < size; i ++){
            for (int j = 0; j <size; j++){
                if (grid[i][j] == Free)
                    return true;
            }
        }
        return false;
    }

    // Is the given square free and valid?
    public boolean isSquareFree(int row, int col){
        if(isSquareValid(row,col)){
            char[][] board = getGrid();
            return board[row][col] == Free;
        }
        else{
            return false;
        }
    }

        // player wishes to play at row,col.
    public void play(int row,int col,char player){
        if(isSquareValid(row,col)){
            if(isSquareFree(row,col)){
                char[][] board = getGrid();
                board[row][col] = player;
            }
            else{
                System.out.println(row+" "+col+" is already occupied.");
            }
        }
    }

        // What is the state of the game?
        // Return one of the game state indicators:
        // NoughtWon, CrossWon, Drawn, Unfinished
        // Use the hasWon method as needed
    public int getGameState(){
        if (hasWon(Nought)){
            return NoughtWon;
        } else if (hasWon(Cross)){
            return CrossWon;
        } else if (hasFreeSpace()){
            return Unfinished;
        } else {
            return Drawn;
        }
    }

        // Has player won?
    public boolean hasWon(char player){
        final int size = getBoardSize();
        final char[][] board = getGrid();
        // Counts for the two diagonals.
        int leftDiagonalCount = 0, rightDiagonalCount = 0;
        for(int x = 0; x < size; x++){
            // How many for player on this row.
            int rowCount = 0;
            // How many for player on this col.
            int colCount = 0;
            for(int y = 0; y < size; y++){
                // Check the row.
                if(board[x][y] == player){
                    rowCount++;
                }
                // Check the column.
                if(board[y][x] == player){
                    colCount++;
                }
            }
            if((rowCount == size) || (colCount == size)){
                return true;
            }
            // Check the two diagonals.
            if(board[x][x] == player){
                leftDiagonalCount++;
            }
            if(board[x][size-x-1] == player){
                rightDiagonalCount++;
            }
        }
        if((leftDiagonalCount == size) || (rightDiagonalCount == size)){
            return true;
        }
        else{
            return false;
        }
    }

    // Switch players.
    public char nextPlayer(char previousPlayer){
        return (previousPlayer == Nought)? Cross : Nought;
    }

    // Return true if row,col is within the grid, false otherwise.
    // Print an error message if the square is not valid.
    private boolean isSquareValid(int row,int col){
        final int size = getBoardSize();
        if((row >= 0) && (col >= 0) && 
                (row < size) && (col < size)){
            return true;
        }
        else{
            System.out.println("Illegal square requested: "+row+" "+col);
            return false;
        }
    }

    public int getBoardSize(){
        return grid.length;
    }

    public char[][] getGrid(){
        return grid;
    }
}
