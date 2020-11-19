package chess;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import BoardGame.Board;
import BoardGame.Piece;
import BoardGame.Position;
import chess.piece.Bishop;
import chess.piece.King;
import chess.piece.Knight;
import chess.piece.Pawn;
import chess.piece.Queen;
import chess.piece.Rook;

public class ChessMatch {
	
	private int turn;
	private Color currentPlayer;
	
	private Board board;
	private boolean check;
	private boolean checkMate;
	private ChessPiece enPassantVulnerable;
	
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
	
	public boolean getCheckMate() {
		return checkMate;
	}
	
	public ChessPiece getEnPassantVunerable() {
		return enPassantVulnerable;
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
	
	
	public boolean[][] possibleMoves(ChessPosition sourcePosition){
		
		Position position = sourcePosition.toPosition();
		validateSourcePosition(position);
		return board.piece(position).possibleMoves();
		
	}
	
	//ir� usar o m�todo de convers�o com as posi��es recebidas
	public ChessPiece performChessMove(ChessPosition sourcePosition, ChessPosition targetPosition) {
		Position source = sourcePosition.toPosition();
		Position target = targetPosition.toPosition();
		validateSourcePosition(source);
		validadeTargetPosition(source, target);
		Piece capturedPiece = makeMove(source,target);
		
		if(testCheck(currentPlayer)) {
			undoMove(source, target, capturedPiece);
			throw new ChessException("Voc� n�o pode se colocar em xeque");
		}
		
		ChessPiece movedPiece = (ChessPiece)board.piece(target);
		check =(testCheck(opponente(currentPlayer)))?true:false;
		
		if(testCheckMate(opponente(currentPlayer))) {
			checkMate=true;
		}else {
			nextTurn();
		}
		
		//movimento especial enPassant
		
		if(movedPiece instanceof Pawn && (target.getRow()==source.getRow()-2 ||target.getRow()==source.getRow()+2)) {
			enPassantVulnerable=movedPiece;
			
		}else {
			enPassantVulnerable=null;
		}
		
		
		return (ChessPiece) capturedPiece;
	}
	
	private Piece makeMove(Position source, Position target) {
		//primeiro remove as pe�as da origem e destino
		ChessPiece p =(ChessPiece)board.removePiece(source);
		p.increaseMoveCount();
		//remove a pe�a que est� na posi��o de destino
		Piece capturedPiece=board.removePiece(target);
		board.placePiece(p, target);
		
		if(capturedPiece!=null) {
			piecesOnTheBoard.remove(capturedPiece);
			capturedPieces.add(capturedPiece);
		}
		
		//testando movimentos especiais roque pequeno
		
		if(p instanceof King && target.getColumn()==source.getColumn()+2) {
			Position sourceT = new Position(source.getRow(), source.getColumn()+3);
			Position targetT = new Position(source.getRow(), source.getColumn()+1);
		ChessPiece rook = (ChessPiece)board.removePiece(sourceT);
		board.placePiece(rook, targetT);
		rook.increaseMoveCount();
		
		}
		
		//testando movimentos especiais roque grande
		if(p instanceof King && target.getColumn()==source.getColumn()-2) {
			Position sourceT = new Position(source.getRow(), source.getColumn()-4);
			Position targetT = new Position(source.getRow(), source.getColumn()-1);
		ChessPiece rook = (ChessPiece)board.removePiece(sourceT);
		board.placePiece(rook, targetT);
		rook.increaseMoveCount();
		
		}
		
		
		//movimento especial en passant
		
		if(p instanceof Pawn) {
			if(source.getColumn() != target.getColumn()&& capturedPiece ==null) {
				Position pawnPosition;
				
				if(p.getColor()==Color.WHITE) {
					pawnPosition= new Position(target.getRow()+1, target.getColumn());
				}else {
					
					pawnPosition= new Position(target.getRow()-1, target.getColumn());
					
				}
				
				capturedPiece = board.removePiece(pawnPosition);
				capturedPieces.add(capturedPiece);
				piecesOnTheBoard.remove(capturedPiece);
			
			}
			
		}
		
		
		
		
		return capturedPiece;
		
	}
	
	private void undoMove(Position source, Position target, Piece capturedPiece) {
		ChessPiece p =(ChessPiece)board.removePiece(target);
		p.decreaseMoveCount();
		board.placePiece(p, source);
		
		if(capturedPiece!=null) {
			board.placePiece(capturedPiece, target);
			capturedPieces.remove(capturedPiece);
			piecesOnTheBoard.add(capturedPiece);
			
		}
		
		if(p instanceof King && target.getColumn()==source.getColumn()+2) {
			Position sourceT = new Position(source.getRow(), source.getColumn()+3);
			Position targetT = new Position(source.getRow(), source.getColumn()+1);
		ChessPiece rook = (ChessPiece)board.removePiece(targetT);
		board.placePiece(rook, sourceT);
		rook.decreaseMoveCount();
		
		}
		
		//testando movimentos especiais roque grande
		if(p instanceof King && target.getColumn()==source.getColumn()-2) {
			Position sourceT = new Position(source.getRow(), source.getColumn()-4);
			Position targetT = new Position(source.getRow(), source.getColumn()-1);
		ChessPiece rook = (ChessPiece)board.removePiece(targetT);
		board.placePiece(rook, sourceT);
		rook.decreaseMoveCount();
		
		}
		
		
		//movimento especial en passant
		
				if(p instanceof Pawn) {
					if(source.getColumn() != target.getColumn()&& capturedPiece ==enPassantVulnerable) {
						ChessPiece pawn = (ChessPiece)board.removePiece(target);
						Position pawnPosition;
						
						if(p.getColor()==Color.WHITE) {
							pawnPosition= new Position(3, target.getColumn());
						}else {
							
							pawnPosition= new Position(4, target.getColumn());
							
						}
						
						board.placePiece(pawn, pawnPosition);
						
						
				
					
					}
					
				}
		
		
	}
	
	
	//validando a posi��o de origem
	public void validateSourcePosition(Position position) {
		
	if(!board.thereIsAPiece(position)) {
		throw new ChessException("N�o existe pe�a na posi��o de origem: "+position);
		
	}	
	
	if(currentPlayer !=  ((ChessPiece)board.piece(position)).getColor()) {
		
		throw new ChessException("Voc� n�o pode mover uma pe�a adversaria");
		
	}
	
	if(!board.piece(position).isThereAnypossibleMove()) {
		throw new ChessException("N�o existe movimentos possiveis para a peca");
	}
	}
	
	//validando a posi��o de destino
	
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
   //m�todo para retornar o rei na cor
   private ChessPiece king(Color color) {
	   
	  List<Piece> list = piecesOnTheBoard.stream().filter(x -> ((ChessPiece)x).getColor()==color).collect(Collectors.toList());
	  
	  for(Piece p:list) {
		  if(p instanceof King) {
			  return (ChessPiece)p;
		  }
	  }
	   throw new IllegalStateException("N�o existe o rei da cor "+color+" no tabuleiro");
   }
   
   
   
   //L�gica do cheque
   
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
   
   private boolean testCheckMate(Color color) {
	   
	   if(!testCheck(color)) {
		   return false;
	   }
	   
	   List<Piece> list = piecesOnTheBoard.stream().filter(x -> ((ChessPiece)x).getColor()==color).collect(Collectors.toList());
	   
	   for(Piece p:list) {
		   
		   boolean[][] mat = p.possibleMoves();
		   for(int i=0; i<board.getRows();i++) {
			   for(int j=0;j<board.getColumns();j++) {
				   
				   if(mat[i][j]) {
					   
					   Position source = ((ChessPiece)p).getChessPosition().toPosition();
					   Position target = new Position(i,j);
					   Piece capturedPiece = makeMove(source, target);
					   boolean testCheck= testCheck(color);
					   undoMove(source, target, capturedPiece);
					   if(!testCheck) {
						   return false;
					   }
					   
					   
				   }
				   
			   }
		   }
	   }
	   
	   return true;
   }
   
   
   
	
	//atribuindo lugar a nova pe�a usando o sistema de localiza��o do xadrez
	private void placeNewPiece(char column, int row,ChessPiece piece) {
		//Ir� receber uma pe�a numa posi��o no formato matriz xadrez e ir� converter para matriz regular
		board.placePiece(piece, new ChessPosition(column, row).toPosition());
		piecesOnTheBoard.add(piece);
	}
	
	
	//A forma com que as pe�as estar�o dispostas no tabuleiro
	
	private void initialSetup() {
		
		placeNewPiece('a',1, new Rook(board, Color.WHITE));
		//bispo
		placeNewPiece('c',1, new Bishop(board, Color.WHITE));
		placeNewPiece('d',1, new Queen(board, Color.WHITE));
		placeNewPiece('e',1, new King(board, Color.WHITE,this));
		placeNewPiece('h',1, new Rook(board, Color.WHITE));
		placeNewPiece('f',1, new Bishop(board, Color.WHITE));
		placeNewPiece('b',1, new Knight(board, Color.WHITE));
		placeNewPiece('g',1, new Knight(board, Color.WHITE));
		
		placeNewPiece('a',2, new Pawn(board, Color.WHITE,this));
		placeNewPiece('b',2, new Pawn(board, Color.WHITE,this));
		placeNewPiece('c',2, new Pawn(board, Color.WHITE,this));
		placeNewPiece('d',2, new Pawn(board, Color.WHITE,this));
		placeNewPiece('e',2, new Pawn(board, Color.WHITE,this));
		placeNewPiece('f',2, new Pawn(board, Color.WHITE,this));
		placeNewPiece('g',2, new Pawn(board, Color.WHITE,this));
		placeNewPiece('h',2, new Pawn(board, Color.WHITE,this));
		
		placeNewPiece('a',8, new Rook(board, Color.BLACK));
		//bispo
		placeNewPiece('c',8, new Bishop(board, Color.BLACK));
		placeNewPiece('e',8, new King(board, Color.BLACK,this));
		placeNewPiece('f',8, new Bishop(board, Color.BLACK));
		placeNewPiece('h',8, new Rook(board, Color.BLACK));
		
		placeNewPiece('a',7, new Pawn(board, Color.BLACK,this));
		placeNewPiece('b',7, new Pawn(board, Color.BLACK,this));
		placeNewPiece('c',7, new Pawn(board, Color.BLACK,this));
		placeNewPiece('d',7, new Pawn(board, Color.BLACK,this));
		placeNewPiece('e',7, new Pawn(board, Color.BLACK,this));
		placeNewPiece('f',7, new Pawn(board, Color.BLACK,this));
		placeNewPiece('g',7, new Pawn(board, Color.BLACK,this));
		placeNewPiece('h',7, new Pawn(board, Color.BLACK,this));
		
		
			
		
	
	}


}
