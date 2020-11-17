package chess;

import BoardGame.Board;
import BoardGame.Piece;
import BoardGame.Position;
import chess.piece.King;
import chess.piece.Rook;

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
	
	
	public boolean[][] possibleMoves(ChessPosition sourcePosition){
		
		Position position = sourcePosition.toPosition();
		validateSourcePosition(position);
		return board.piece(position).possibleMoves();
		
	}
	
	//irá usar o método de conversão com as posições recebidas
	public ChessPiece performChessMove(ChessPosition sourcePosition, ChessPosition targetPosition) {
		Position source = sourcePosition.toPosition();
		Position target = targetPosition.toPosition();
		validateSourcePosition(source);
		validadeTargetPosition(source, target);
		Piece capturedPiece = makeMove(source,target);
		
		return (ChessPiece) capturedPiece;
	}
	
	private Piece makeMove(Position source, Position target) {
		//primeiro remove as peças da origem e destino
		Piece p = board.removePiece(source);
		//remove a peça que está na posição de destino
		Piece capturedPiece=board.removePiece(target);
		//adiciona a peça da posição de origem para posição de destino
		board.placePiece(p, target);
		return capturedPiece;
		
	}
	
	//validando a posição de origem
	public void validateSourcePosition(Position position) {
		
	if(!board.thereIsAPiece(position)) {
		throw new ChessException("Não existe peça na posição de origem: "+position);
		
	}	
	
	if(!board.piece(position).isThereAnypossibleMove()) {
		throw new ChessException("Não existe movimentos possiveis para a peca");
	}
	}
	
	//validando a posição de destino
	
	private void validadeTargetPosition(Position source, Position target) {
		
		if(!board.piece(source).possibleMove(target)) {
			throw new ChessException("Movimento Invalido");
			
			
		}
		
		
	}
	
   
	
	//atribuindo lugar a nova peça usando o sistema de localização do xadrez
	private void placeNewPiece(char column, int row,ChessPiece piece) {
		//Irá receber uma peça numa posição no formato matriz xadrez e irá converter para matriz regular
		board.placePiece(piece, new ChessPosition(column, row).toPosition());
	}
	
	
	//A forma com que as peças estarão dispostas no tabuleiro
	
	private void initialSetup() {
		placeNewPiece('a',1, new Rook(board, Color.WHITE));
		placeNewPiece('b',1, new Rook(board, Color.WHITE));
		placeNewPiece('c',1, new Rook(board, Color.BLACK));
		placeNewPiece('d',1, new Rook(board, Color.BLACK));
		placeNewPiece('e',1, new Rook(board, Color.WHITE));
	}


}
