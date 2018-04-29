package snakeandladder;

import square.LadderSquare;
import square.Square;

public class Board {
	public static final int SIZE = 64;
	private Square[] squares;

	public Board() {
		this.squares = new Square[SIZE];
		for (int i = 0; i < squares.length; i++)
			squares[i] = new Square(i);

		squares[3] = new LadderSquare(squares[3].getNumber(), 63);

		squares[squares.length - 1].setGoal(true);
	}

	public void addPiece(Piece piece, int pos) {
		squares[pos].addPiece(piece);
	}

	public void movePiece(Piece piece, int steps) {
		int pos = getPiecePos(piece);
		squares[pos].removePiece(piece);
		int newPos = pos + steps;
		if (newPos >= squares.length)
			newPos = 2 * (squares.length - 1) - newPos;
		addPiece(piece, newPos);
		
		if (squares[newPos] instanceof LadderSquare) {
			LadderSquare ladderSquare = (LadderSquare) squares[newPos];
			steps = ladderSquare.goTo() - newPos;
			System.out.println("Found a ladder !! GOTO -> " + newPos);
			movePiece(piece, steps);
		}
	}

	public int getPiecePos(Piece piece) {
		for (Square s : squares)
			if (s.hasPiece(piece))
				return s.getNumber();
		return -1;
	}

	public boolean pieceIsAtGoal(Piece piece) {
		return squares[getPiecePos(piece)].isGoal();
	}

}
