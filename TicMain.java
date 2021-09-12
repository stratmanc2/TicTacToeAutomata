// Play a game of Tic Tac Toe.
// The game is played on an NxN grid, where N >= 3.
class TicMain {
    public static void main(String[] args) {
        // The size of the board.
        int size = TicGame.MINIMUM_SIZE;
        TicManager game = new TicManager(size);
        game.play();
    }
}
