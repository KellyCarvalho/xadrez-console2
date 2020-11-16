package BoardGame;

public class Board {
	private int rows;
	private int columns;
	private Piece[][] pieces;
	
	public Board(int rows, int columns) {
		
		if(rows < 1 || columns < 1) {
			
			throw new BoardException("Erro criando o tabuleiro: é necessário que a linha e a coluna tenha um valor maior que zero");
			
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
	
	
//Retorna as peças do tabuleiro em relação a linha e coluna informada
	
	
public Piece piece(int row,int column) {
	//verificando se a posição existe antes de atrubuir o lugar a peça
	if(!positionExists(row, column)) {
		
	 throw new 	BoardException("Posição que está tentando acessar não existe");
		
	}
	return pieces[row][column];
	
}

//Retorna as peças do tabuleiro em relação a posição já completa


public Piece piece(Position position) {
	
	if(!positionExists(position)) {
		
		 throw new 	BoardException("Posição que está tentando acessar não existe");
			
		}
	return pieces[position.getRow()][position.getColumn()];
}



    //atribuindo lugar a peça selecionada usando a posição escolhida


	public void placePiece(Piece piece, Position position) {
		//para mover de lugar, primeiro será testado se existe uma peça no lugar escolhido
		if(thereIsAPiece(position)) {
			throw new BoardException("já existe uma peça no lugar escolhido: "+position+" escolha outro lugar");
		}

		//na posição informada a peça será inserida
		
		pieces[position.getRow()][position.getColumn()]=piece;
		
		//Para a peça não receber uma posição nula é necessário setar a nova posição da mesma
		
		piece.position=position;
	}
	
	//verificando se a posição existe
	private boolean positionExists(int row,int column) {
		
	//se a linha em questão é maior que as linhas do tabuleiro e a coluna em questão é maior que as colunas do tabuleiro	
	return row >=0 && row <rows && column >=0 && column <columns;
	
	
		
		
	}
	
	public boolean positionExists(Position position) {
		//lógica da positionExists aplicada na posição
		return positionExists(position.getRow(),position.getColumn());
		
	}
	
	public boolean thereIsAPiece(Position position) {
		if(!positionExists(position)) {
			
			 throw new 	BoardException("Posição que está tentando acessar não existe: "+position);
				
			}
		//verificando se a peça é diferente de nulo
		return piece(position) !=null;
		
	}
	
	

}
