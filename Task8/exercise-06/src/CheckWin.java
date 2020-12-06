public class CheckWin {

    private char winner = '-';
    private StringBuffer board;

    public CheckWin(StringBuffer board) {
        this.board = board;
    }

    public char getWinner() {
        getHorizontalWinner();
        getVerticalWinner();
        getDiagonalWinner();
        return winner;
    }


    private void getHorizontalWinner() {
        // check for horizontal winner
        for (int i = 0; i < 9; i += 3) {
            if (board.charAt(i) != '-'
                    && board.charAt(i + 1) == board.charAt(i)
                    && board.charAt(i + 2) == board.charAt(i)){
                winner = board.charAt(i);
                return;
            }
        }
    }

    private void getVerticalWinner() {
        // check for vertical winner
        for (int i = 0; i < 3; ++i) {
            if (board.charAt(i) != '-'
                    && board.charAt(i + 3) == board.charAt(i)
                    && board.charAt(i + 6) == board.charAt(i)){
                winner = board.charAt(i);
                return;
            }
        }
    }

    private void getDiagonalWinner() {
        // check for diagonal winner
        if (board.charAt(0) != '-' && board.charAt(4) == board.charAt(0) && board.charAt(8) == board.charAt(0)){
            winner = board.charAt(0);
            return;
        }
        if (board.charAt(2) != '-' && board.charAt(4) == board.charAt(2) && board.charAt(6) == board.charAt(2))
            winner = board.charAt(2);
    }
}