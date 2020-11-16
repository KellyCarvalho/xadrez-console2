package application;

import BoardGame.Board;
import BoardGame.Position;
import chess.ChessMatch;
import chess.ChessPiece;
import chess.Color;
import chess.piece.Rook;

public class Program {

	public static void main(String[] args) {
		
		ChessMatch chessMatch = new ChessMatch();
	
		UI.printBoard(chessMatch.getPieces());
		
	


	}

}
