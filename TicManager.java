import java.util.ArrayList;
import java.util.List;

// Run a game of noughts and crosses.
        // Squares are chosen randomly.
class TicManager {

    private List<BoardListener> boardListeners = new ArrayList<>();
    // The board. Initialized in the constructor.
    private final TicGame game;
    // A GUI for the game.
    private final TicDisplay display;

    // How big the game's board should be.
    public TicManager(int size){
        game = new TicGame(size);
        // Create a display for the game.
        display = new TicDisplay(size);
        // connect display (ticDisplay) model (ticGame)
        addListener(display);
    }

    private void alertListeners(){
        for (BoardListener boardListener : boardListeners){
            boardListener.boardChanged(game.getGrid());
        }
    }

    // Play a single game.
    public void play(){
        final TicGame game = getGame();
        game.clearBoard();

        // Who plays next.
        char player = TicGame.Nought;
        while(game.getGameState() == TicGame.Unfinished){
            makeARandomMove(player);
            if(!game.hasWon(player)){
                player = game.nextPlayer(player);
            }
            alertListeners();
        }
        reportTheResult();
    }

        // Make a random move for player.
    private void makeARandomMove(char player){
        final TicGame game = getGame();
        // We shall generate a random row and column for each move.
        int row, col;
        // Generate a valid random move.
        do{
            row = getRandomPosition();
            col = getRandomPosition();
        } while(!game.isSquareFree(row,col));
        // Play in the chosen square.
        game.play(row,col,player);
        getDisplay().showSquare(row,col,player);
    }

    private void reportTheResult(){
        final TicGame game = getGame();
        int result = game.getGameState();

        if(result == TicGame.Drawn){
            System.out.println("The game was drawn.");
        }
        else if(result == TicGame.NoughtWon){
            System.out.println(TicGame.Nought+" won.");
        }
        else if(result == TicGame.CrossWon){
            System.out.println(TicGame.Cross+" won.");
        }
        else{
            System.out.println("Unknown result: "+result);
        }
    }

    // Return a valid random number as part of a move.
    private int getRandomPosition(){
        return (int) (Math.random() * getGame().getBoardSize());
    }

    private TicGame getGame(){
        return game;
    }

    private TicDisplay getDisplay(){
        return display;
    }

    public void addListener(BoardListener boardListener){
        boardListeners.add(boardListener);
    }
}
