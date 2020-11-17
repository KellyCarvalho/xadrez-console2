package chess.piece;

import BoardGame.Board;
import BoardGame.Position;
import chess.ChessPiece;
import chess.Color;

public class Pawn extends ChessPiece {

	public Pawn(Board board, Color color) {
		super(board, color);
		
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
			
			
		}
		
		return mat;
	}
	
	@Override
	public String toString() {
		return "P";
	}
	
	
	

}
