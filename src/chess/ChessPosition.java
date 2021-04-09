package chess;

import BoardGame.Position;

public class ChessPosition {

	private  char column;
	private int row;
	
	public ChessPosition(char column, int row) {
		
		//o tipo char aceita compara��es de <> usando os pr�prios caracteres
		if(column<'a' ||column > 'h' || row < 1 || row > 8) {
			
			throw new ChessException("N�o foi possivel instanciar um ChessPosition ara coluna s� s�o aceitos caracteres entre a-h e para linha 1-8");

			
		}
		this.column = column;
		this.row = row;
	}

	public char getColumn() {
		return column;
	}



	public int getRow() {
		return row;
	}

	//convertendo a posi��o caractere,inteiro em posi��es verdadeiras da matriz
	protected Position toPosition() {
		return new Position(8-row, column-'a');
	}
	
	//convertendo a posi��o da matriz-xadrez para matriz de leitura
	protected static ChessPosition fromPosition(Position position) {
		
		return new ChessPosition((char)('a'+position.getColumn()), 8-position.getRow());
		
	}
	
	@Override
	public String toString() {
		return ""+column+row;
	}
	


	
	
}
