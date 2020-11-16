package BoardGame;

public class Board {
	private int rows;
	private int columns;
	private Piece[][] pieces;
	
	public Board(int rows, int columns) {
		
		if(rows < 1 || columns < 1) {
			
			throw new BoardException("Erro criando o tabuleiro: � necess�rio que a linha e a coluna tenha um valor maior que zero");
			
		}
		
		this.rows = rows;
		this.columns = columns;
		pieces = new Piece[rows][columns];
	}
	public int getRows() {
		return rows;
	}

	public int getColumns() {
		return columns;
	}
	public void setColumns(int columns) {
		this.columns = columns;
	}
	
	
//Retorna as pe�as do tabuleiro em rela��o a linha e coluna informada
	
	
public Piece piece(int row,int column) {
	//verificando se a posi��o existe antes de atrubuir o lugar a pe�a
	if(!positionExists(row, column)) {
		
	 throw new 	BoardException("Posi��o que est� tentando acessar n�o existe");
		
	}
	return pieces[row][column];
	
}

//Retorna as pe�as do tabuleiro em rela��o a posi��o j� completa


public Piece piece(Position position) {
	
	if(!positionExists(position)) {
		
		 throw new 	BoardException("Posi��o que est� tentando acessar n�o existe");
			
		}
	return pieces[position.getRow()][position.getColumn()];
}



    //atribuindo lugar a pe�a selecionada usando a posi��o escolhida


	public void placePiece(Piece piece, Position position) {
		//para mover de lugar, primeiro ser� testado se existe uma pe�a no lugar escolhido
		if(thereIsAPiece(position)) {
			throw new BoardException("j� existe uma pe�a no lugar escolhido: "+position+" escolha outro lugar");
		}

		//na posi��o informada a pe�a ser� inserida
		
		pieces[position.getRow()][position.getColumn()]=piece;
		
		//Para a pe�a n�o receber uma posi��o nula � necess�rio setar a nova posi��o da mesma
		
		piece.position=position;
	}
	
	//verificando se a posi��o existe
	private boolean positionExists(int row,int column) {
		
	//se a linha em quest�o � maior que as linhas do tabuleiro e a coluna em quest�o � maior que as colunas do tabuleiro	
	return row >=0 && row <rows && column >=0 && column <columns;
	
	
		
		
	}
	
	public boolean positionExists(Position position) {
		//l�gica da positionExists aplicada na posi��o
		return positionExists(position.getRow(),position.getColumn());
		
	}
	
	public boolean thereIsAPiece(Position position) {
		if(!positionExists(position)) {
			
			 throw new 	BoardException("Posi��o que est� tentando acessar n�o existe: "+position);
				
			}
		//verificando se a pe�a � diferente de nulo
		return piece(position) !=null;
		
	}
	
	

}
