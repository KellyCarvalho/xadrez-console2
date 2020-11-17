package chess;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import BoardGame.Board;
import BoardGame.Piece;
import BoardGame.Position;
import chess.piece.King;
import chess.piece.Rook;

public class ChessMatch {
	
	private int turn;
	private Color currentPlayer;
	
	private Board board;
	private boolean check;
	
	private List<Piece> piecesOnTheBoard = new ArrayList<>();
	private List<Piece> capturedPieces = new ArrayList<>();
	
	public ChessMatch() {
		board = new Board(8,8);
		turn = 1;
		currentPlayer = Color.WHITE;
		
		initialSetup();
	}
	
	public int getTurn() {
		return turn;
	}
	
	public Color getCurrentPlayer() {
		return currentPlayer;
	}
	
	public boolean getCheck() {
		return check;
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
		
		if(testCheck(currentPlayer)) {
			undoMove(source, target, capturedPiece);
			throw new ChessException("Você não pode se colocar em xeque");
		}
		
		check =(testCheck(opponente(currentPlayer)))?true:false;
		
		
		nextTurn();
		
		return (ChessPiece) capturedPiece;
	}
	
	private Piece makeMove(Position source, Position target) {
		//primeiro remove as peças da origem e destino
		Piece p = board.removePiece(source);
		//remove a peça que está na posição de destino
		Piece capturedPiece=board.removePiece(target);
		
		if(capturedPiece!=null) {
			piecesOnTheBoard.remove(capturedPiece);
			capturedPieces.add(capturedPiece);
		}
		//adiciona a peça da posição de origem para posição de destino
		board.placePiece(p, target);
		return capturedPiece;
		
	}
	
	private void undoMove(Position source, Position target, Piece capturedPiece) {
		Piece p = board.removePiece(target);
		board.placePiece(p, source);
		
		if(capturedPiece!=null) {
			board.placePiece(capturedPiece, target);
			capturedPieces.remove(capturedPiece);
			piecesOnTheBoard.add(capturedPiece);
			
		}
		
		
		
	}
	
	
	//validando a posição de origem
	public void validateSourcePosition(Position position) {
		
	if(!board.thereIsAPiece(position)) {
		throw new ChessException("Não existe peça na posição de origem: "+position);
		
	}	
	
	if(currentPlayer !=  ((ChessPiece)board.piece(position)).getColor()) {
		
		throw new ChessException("Você não pode mover uma peça adversaria");
		
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
	
	
	private void nextTurn() {
		turn++;
		
		currentPlayer = (currentPlayer==Color.WHITE)? Color.BLACK:Color.WHITE;
		
	}
	
   private Color opponente(Color color) {
	   
	   return (color==Color.WHITE)? Color.BLACK:Color.WHITE;
	   
   }
   //método para retornar o rei na cor
   private ChessPiece king(Color color) {
	   
	  List<Piece> list = piecesOnTheBoard.stream().filter(x -> ((ChessPiece)x).getColor()==color).collect(Collectors.toList());
	  
	  for(Piece p:list) {
		  if(p instanceof King) {
			  return (ChessPiece)p;
		  }
	  }
	   throw new IllegalStateException("Não existe o rei da cor "+color+" no tabuleiro");
   }
   
   
   
   //Lógica do cheque
   
   public boolean testCheck(Color color) {
	   Position kingPosition = king(color).getChessPosition().toPosition();
	   
	   List<Piece> opponentePieces = piecesOnTheBoard.stream().filter(x -> ((ChessPiece)x).getColor()==opponente(color)).collect(Collectors.toList());
	   
	   for(Piece p: opponentePieces) {
		   boolean[][] mat = p.possibleMoves();
		   if(mat[kingPosition.getRow()][kingPosition.getColumn()]) {
			   
			   
			   return true;
		   }
	   }
	   
	 return false;
	   
   }
   
   
	
	//atribuindo lugar a nova peça usando o sistema de localização do xadrez
	private void placeNewPiece(char column, int row,ChessPiece piece) {
		//Irá receber uma peça numa posição no formato matriz xadrez e irá converter para matriz regular
		board.placePiece(piece, new ChessPosition(column, row).toPosition());
		piecesOnTheBoard.add(piece);
	}
	
	
	//A forma com que as peças estarão dispostas no tabuleiro
	
	private void initialSetup() {
		placeNewPiece('a',1, new King(board, Color.WHITE));
		placeNewPiece('b',1, new Rook(board, Color.WHITE));
		placeNewPiece('c',1, new King(board, Color.BLACK));
		placeNewPiece('d',1, new Rook(board, Color.BLACK));
		placeNewPiece('e',1, new King(board, Color.WHITE));
	}


}
