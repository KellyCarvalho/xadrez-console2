package application;

import chess.ChessPiece;

public class UI {

	//imprimindo tabuleiro
	public static void printBoard(ChessPiece[][] pieces) {
		
		for(int i=0;i<pieces.length;i++) {
			//Ir� imprimir o n�mero das linhas do tabuleiro
			System.out.print((8-i)+" ");
			for(int j=0;j<pieces.length;j++) {
				
				//chama o m�todo que vai imprimir no console todo o tabuleiro
				printPiece(pieces[i][j]);
				
				
			}
			
			
			System.out.println();
		}
		//ir� imprimir a parte de baixo do tabuleiro
		System.out.println("  a b c d e f g h");
		
	}
	//Imprimindo pe�as no console
	private static void printPiece(ChessPiece piece) {
		
		if(piece==null) {
			System.out.print("-");
		}else {
			System.out.print(piece);
		}
			
		
		
		System.out.print(" ");
	}
	
}
