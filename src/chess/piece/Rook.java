package chess.piece;

import BoardGame.Board;
import chess.ChessPiece;
import chess.Color;

public class Rook extends ChessPiece {

	
	public Rook(Board board, Color color) {
		super(board, color);
		
	}
	
	@Override
	public String toString() {
		//A torre irá retornar no tabuleiro a letra R de rook
		return "R";
	}

}
