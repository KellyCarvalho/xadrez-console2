package chess.piece;

import BoardGame.Board;
import BoardGame.Position;
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

	@Override
	public boolean[][] possibleMoves() {
		boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];
		
		Position p = new Position(0,0);
		
		//verificar acima
		
		p.setValues(position.getRow()-1, position.getColumn());
		while(getBoard().positionExists(p)&& !getBoard().thereIsAPiece(p)) {
			mat[p.getRow()][p.getColumn()]=true;
			p.setRow(p.getRow()-1);
		}
		
		if(getBoard().positionExists(p)&& isThereOpponentePiece(p)) {
			mat[p.getRow()][p.getColumn()]=true;
		}
		
		//esquerda
		p.setValues(position.getRow(), position.getColumn()-1);
		while(getBoard().positionExists(p)&& !getBoard().thereIsAPiece(p)) {
			mat[p.getRow()][p.getColumn()]=true;
			p.setColumn(p.getColumn()-1);
		}
		
		if(getBoard().positionExists(p)&& isThereOpponentePiece(p)) {
			mat[p.getRow()][p.getColumn()]=true;
		}
		//direita
		p.setValues(position.getRow(), position.getColumn()+1);
		while(getBoard().positionExists(p)&& !getBoard().thereIsAPiece(p)) {
			mat[p.getRow()][p.getColumn()]=true;
			p.setColumn(p.getColumn()+1);
		}
		
		if(getBoard().positionExists(p)&& isThereOpponentePiece(p)) {
			mat[p.getRow()][p.getColumn()]=true;
		}
		
		//abaixo
		p.setValues(position.getRow()+1, position.getColumn());
		while(getBoard().positionExists(p)&& !getBoard().thereIsAPiece(p)) {
			mat[p.getRow()][p.getColumn()]=true;
			p.setRow(p.getRow()+1);
		}
		
		if(getBoard().positionExists(p)&& isThereOpponentePiece(p)) {
			mat[p.getRow()][p.getColumn()]=true;
		}
		
		
		
		return mat;
	}

}
