package chess.piece;

import BoardGame.Board;
import BoardGame.Position;
import chess.ChessPiece;
import chess.Color;

public class Bishop extends ChessPiece {

	
	public Bishop(Board board, Color color) {
		super(board, color);
		
	}
	
	@Override
	public String toString() {
		//A torre irá retornar no tabuleiro a letra R de rook
		return "B";
	}

	@Override
	public boolean[][] possibleMoves() {
		boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];
		
		Position p = new Position(0,0);
		
		//verificar noroeste
		
		p.setValues(position.getRow()-1, position.getColumn()-1);
		while(getBoard().positionExists(p)&& !getBoard().thereIsAPiece(p)) {
			mat[p.getRow()][p.getColumn()]=true;
			p.setValues(p.getRow()-1, p.getColumn()-1);
		}
		
		if(getBoard().positionExists(p)&& isThereOpponentePiece(p)) {
			mat[p.getRow()][p.getColumn()]=true;
		}
		
		//nordeste
		p.setValues(position.getRow()-1, position.getColumn()+1);
		while(getBoard().positionExists(p)&& !getBoard().thereIsAPiece(p)) {
			mat[p.getRow()][p.getColumn()]=true;
			p.setValues(p.getRow()-1, p.getColumn()+1);
		}
		
		if(getBoard().positionExists(p)&& isThereOpponentePiece(p)) {
			mat[p.getRow()][p.getColumn()]=true;
		}
		//sudeste
		p.setValues(position.getRow()+1, position.getColumn()+1);
		while(getBoard().positionExists(p)&& !getBoard().thereIsAPiece(p)) {
			mat[p.getRow()][p.getColumn()]=true;
			p.setValues(p.getRow()+1, p.getColumn()+1);
		}
		
		if(getBoard().positionExists(p)&& isThereOpponentePiece(p)) {
			mat[p.getRow()][p.getColumn()]=true;
		}
		
		//sudoeste
		p.setValues(position.getRow()+1, position.getColumn()-1);
		while(getBoard().positionExists(p)&& !getBoard().thereIsAPiece(p)) {
			mat[p.getRow()][p.getColumn()]=true;
			p.setValues(p.getRow()+1, p.getColumn()-1);
		}
		
		if(getBoard().positionExists(p)&& isThereOpponentePiece(p)) {
			mat[p.getRow()][p.getColumn()]=true;
		}
		
		
		
		return mat;
	}

}
