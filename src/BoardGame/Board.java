package BoardGame;

public class Board {
	private int rows;
	private int columns;
	private Piece[][] pieces;
	public Board(int rows, int columns) {
		
		this.rows = rows;
		this.columns = columns;
		pieces = new Piece[rows][columns];
	}
	public int getRows() {
		return rows;
	}
	public void setRows(int rows) {
		this.rows = rows;
	}
	public int getColumns() {
		return columns;
	}
	public void setColumns(int columns) {
		this.columns = columns;
	}
	
	
//Retorna as pe�as do tabuleiro em rela��o a linha e coluna informada
public Piece piece(int row,int column) {
	return pieces[row][column];
}

//Retorna as pe�as do tabuleiro em rela��o a posi��o j� completa
public Piece piece(Position position) {
	return pieces[position.getRow()][position.getColumn()];
}

	

}
