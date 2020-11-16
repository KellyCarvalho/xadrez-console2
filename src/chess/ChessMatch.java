package chess;

import BoardGame.Board;
import chess.piece.King;

public class ChessMatch {
	
	private Board board;
	
	public ChessMatch() {
		board = new Board(8,8);
		initialSetup();
	}
	
	/*Converte a classe peças em Peças em xadrez, para não ser possível uma atuação 
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
	
	//atribuindo lugar a nova peça usando o sistema de localização do xadrez
	private void placeNewPiece(char column, int row,ChessPiece piece) {
		//Irá receber uma peça numa posição no formato matriz xadrez e irá converter para matriz regular
		board.placePiece(piece, new ChessPosition(column, row).toPosition());
	}
	
	
	//A forma com que as peças estarão dispostas no tabuleiro
	
	private void initialSetup() {
		placeNewPiece('a',1, new King(board, Color.WHITE));

	}


}
