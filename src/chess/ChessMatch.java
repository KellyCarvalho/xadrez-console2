package chess;

import BoardGame.Board;
import BoardGame.Position;
import chess.piece.King;
import chess.piece.Rook;

public class ChessMatch {
	
	private Board board;
	
	public ChessMatch() {
		board = new Board(8,8);
		initialSetup();
	}
	
	/*Converte a classe pe�as em Pe�as em xadrez, para n�o ser poss�vel uma atua��o 
	direto na classe principal e facilitar o trabalho com camadas*/
	public ChessPiece[][] getPieces(){
		ChessPiece[][] mat = new ChessPiece[board.getRows()][board.getColumns()];
		for(int i=0;i<board.getRows();i++) {
			for(int j=0;j<board.getColumns();j++) {
				
				mat[i][j]= (ChessPiece) board.piece(i,j);
				
			}
		}
		
		return mat;
	}
	
	
	//A forma com que as pe�as estar�o dispostas no tabuleiro
	
	private void initialSetup() {
		board.placePiece(new King(board, Color.WHITE), new Position(2,2));
		board.placePiece(new King(board, Color.WHITE), new Position(2,1));
	}


}
