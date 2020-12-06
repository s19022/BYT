public class TicTacToe {
	public StringBuffer board;

	public TicTacToe(String s) {
		board = new StringBuffer(s);
	}

	public TicTacToe(String s, int position, char player) {
		board = new StringBuffer(s);
		board.setCharAt(position, player);
	}

	public int suggestMove(char player) {
		if (!hasEmptySpaces()) return -1;
		int move = findWinningMove(player);
		if (move != -1) return move;

		return findPossibleMove();
	}


	public TicTacToe makeMove(int i, char player) {
		return new TicTacToe(board.toString(), i, player);
	}

	public char winner() {
		return new CheckWin(board).getWinner();
	}

	private boolean hasEmptySpaces(){
		for (int i = 0; i < 9; i++) {
			if (board.charAt(i) == '-')
				return true;
		}
		return false;
	}

	private int findWinningMove(char player){
		for (int i = 0; i < 9; i++) {
			if (board.charAt(i) != '-') continue;

			TicTacToe game = makeMove(i, player);
			if (game.winner() == player)
				return i;

		}
		return -1;
	}

	private int findPossibleMove(){
		for (int i = 0; i < 9; i++) {
			if (board.charAt(i) == '-')
				return i;
		}
		return -1;
	}
}
