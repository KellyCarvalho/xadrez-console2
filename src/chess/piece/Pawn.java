package chess.piece;

import BoardGame.Board;
import BoardGame.Position;
import chess.ChessMatch;
import chess.ChessPiece;
import chess.Color;

public class Pawn extends ChessPiece {
	
	//dependencia para a partida
	
		private ChessMatch chessMatch;

	public Pawn(Board board, Color color,ChessMatch chessMatch) {
		super(board, color);
		this.chessMatch=chessMatch;
		
	}

	@Override
	public boolean[][] possibleMoves() {
		
		boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];
		Position p = new Position(0,0);
		
		if(getColor()==Color.WHITE) {
			p.setValues(position.getRow()-1, position.getColumn());
			if(getBoard().positionExists(p)&&!getBoard().thereIsAPiece(p)) {
				
				mat[p.getRow()][p.getColumn()]=true;
				
				
			}
			//testando a primeira vez que o peão pode andar 2 casas
			p.setValues(position.getRow()-2, position.getColumn());
			Position p2 = new Position(position.getRow()-1, position.getColumn());
			if(getBoard().positionExists(p)&&!getBoard().thereIsAPiece(p)&& getBoard().positionExists(p2)&&!getBoard().thereIsAPiece(p2) &&getMoveCount()==0) {
				
				mat[p.getRow()][p.getColumn()]=true;
				
				
			}
			//diagonal esquerda
			p.setValues(position.getRow()-1, position.getColumn()-1);
			//se a posição existe e tem uma peça adversária lá
			if(getBoard().positionExists(p)&& isThereOpponentePiece(p)) {
				
				mat[p.getRow()][p.getColumn()]=true;
				
				
			}
			//diagonal direita
			p.setValues(position.getRow()-1, position.getColumn()+1);
			//se a posição existe e tem uma peça adversária lá
			if(getBoard().positionExists(p)&& isThereOpponentePiece(p)) {
				
				mat[p.getRow()][p.getColumn()]=true;
				
				
			}
			
			//movimento especial en Passant brancas
			
			if(position.getRow()==3) {
				
				Position left = new Position(position.getRow(), position.getColumn()-1);
				
				if(getBoard().positionExists(left) && isThereOpponentePiece(left)&& getBoard().piece(left)==chessMatch.getEnPassantVunerable()) {
					
					mat[left.getRow()-1][left.getColumn()]=true;
}
				
			}
			
			
			
			
			//movimento especial en passant peças brancas
			
			if(position.getRow()==3) {
				
				Position left = new Position(position.getRow(), position.getColumn()-1);
				
				if(getBoard().positionExists(left) && isThereOpponentePiece(left)&&getBoard().piece(left)==chessMatch.getEnPassantVunerable()) {
					
					mat[left.getRow()-1][left.getColumn()]=true;

				}
				
            Position right = new Position(position.getRow(), position.getColumn()+1);
				
				if(getBoard().positionExists(right) && isThereOpponentePiece(right)&&getBoard().piece(right)==chessMatch.getEnPassantVunerable()) {
					
					mat[right.getRow()-1][right.getColumn()]=true;

				}
				
			}
			
			
			
		}else {
			
			
			p.setValues(position.getRow()+1, position.getColumn());
			if(getBoard().positionExists(p)&&!getBoard().thereIsAPiece(p)) {
				
				mat[p.getRow()][p.getColumn()]=true;
				
				
			}
			//testando a primeira vez que o peão pode andar 2 casas
			p.setValues(position.getRow()+2, position.getColumn());
			Position p2 = new Position(position.getRow()+1, position.getColumn());
			if(getBoard().positionExists(p)&&!getBoard().thereIsAPiece(p)&& getBoard().positionExists(p2)&&!getBoard().thereIsAPiece(p2) &&getMoveCount()==0) {
				
				mat[p.getRow()][p.getColumn()]=true;
				
				
			}
			//diagonal esquerda
			p.setValues(position.getRow()+1, position.getColumn()-1);
			//se a posição existe e tem uma peça adversária lá
			if(getBoard().positionExists(p)&& isThereOpponentePiece(p)) {
				
				mat[p.getRow()][p.getColumn()]=true;
				
				
			}
			//diagonal direita
			p.setValues(position.getRow()+1, position.getColumn()+1);
			//se a posição existe e tem uma peça adversária lá
			if(getBoard().positionExists(p)&& isThereOpponentePiece(p)) {
				
				mat[p.getRow()][p.getColumn()]=true;
				
				
			}
			
			
//movimento especial en passant peças pretas
			
			if(position.getRow()==4) {
				
				Position left = new Position(position.getRow(), position.getColumn()-1);
				
				if(getBoard().positionExists(left) && isThereOpponentePiece(left)&&getBoard().piece(left)==chessMatch.getEnPassantVunerable()) {
					
					mat[left.getRow()+1][left.getColumn()]=true;

				}
				
            Position right = new Position(position.getRow(), position.getColumn()+1);
				
				if(getBoard().positionExists(right) && isThereOpponentePiece(right)&&getBoard().piece(right)==chessMatch.getEnPassantVunerable()) {
					
					mat[right.getRow()+1][right.getColumn()]=true;

				}
				
			}
			
		}
		
		return mat;
	}
	
	@Override
	public String toString() {
		return "P";
	}
	
	
	

}
