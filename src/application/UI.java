package application;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import chess.ChessMatch;
import chess.ChessPiece;
import chess.ChessPosition;
import chess.Color;

public class UI {
	public static final String ANSI_RESET = "\u001B[0m";
	public static final String ANSI_BLACK = "\u001B[30m";
	public static final String ANSI_RED = "\u001B[31m";
	public static final String ANSI_GREEN = "\u001B[32m";
	public static final String ANSI_YELLOW = "\u001B[33m";
	public static final String ANSI_BLUE = "\u001B[34m";
	public static final String ANSI_PURPLE = "\u001B[35m";
	public static final String ANSI_CYAN = "\u001B[36m";
	public static final String ANSI_WHITE = "\u001B[37m";

	public static final String ANSI_BLACK_BACKGROUND = "\u001B[40m";
	public static final String ANSI_RED_BACKGROUND = "\u001B[41m";
	public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
	public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
	public static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";
	public static final String ANSI_PURPLE_BACKGROUND = "\u001B[45m";
	public static final String ANSI_CYAN_BACKGROUND = "\u001B[46m";
	
	
	//limpando a tela
	
	public static void clearScreen() {
		System.out.print("\033[H\033[2J");
		System.out.flush();
	}
	
	//lendo o a posição que o usuário informa
		public static ChessPosition readChessPosition(Scanner sc) {
			
			try {
				String s = sc.nextLine();
				char column= s.charAt(0);
				
				int row = Integer.parseInt(s.substring(1));
				
				return new ChessPosition(column, row);
				
			}catch(RuntimeException e){
				
				throw new InputMismatchException("Erro lendo posição de xadrez valores válidos são de a-h e 1-8");
				
			}
			
		}
		
		public static void printMatch(ChessMatch chessMatch, List<ChessPiece> captured) {
			
			printBoard(chessMatch.getPieces());
			System.out.println();
			printCapturedPieces(captured);
			System.out.println();
			System.out.println("Turno: "+chessMatch.getTurn());
			if(!chessMatch.getCheckMate()) {
				System.out.println("Aguardando jogador: "+chessMatch.getCurrentPlayer());
				
				if(chessMatch.getCheck()) {
					System.out.println("CHECK!");
				}
			}
			else {
				System.out.println("CHECKMATE!!!");
				System.out.println("Ganhador: "+chessMatch.getCurrentPlayer());
			}
			
		}
	
	//imprimindo tabuleiro
	public static void printBoard(ChessPiece[][] pieces) {
		
		for(int i=0;i<pieces.length;i++) {
			//Irá imprimir o número das linhas do tabuleiro
			System.out.print((8-i)+" ");
			for(int j=0;j<pieces.length;j++) {
				
				//chama o método que vai imprimir no console todo o tabuleiro
				printPiece(pieces[i][j],false);
				
				
			}
			
			
			System.out.println();
		}
		//irá imprimir a parte de baixo do tabuleiro
		System.out.println("  a b c d e f g h");
		
	}
	
	//imprimindo movimentos possiveis
	
	//imprimindo tabuleiro
	public static void printBoard(ChessPiece[][] pieces,boolean[][] possibleMoves) {
		
		for(int i=0;i<pieces.length;i++) {
			//Irá imprimir o número das linhas do tabuleiro
			System.out.print((8-i)+" ");
			for(int j=0;j<pieces.length;j++) {
				
				//chama o método que vai imprimir no console todo o tabuleiro
				printPiece(pieces[i][j],possibleMoves[i][j]);
				
				
			}
			
			
			System.out.println();
		}
		//irá imprimir a parte de baixo do tabuleiro
		System.out.println("  a b c d e f g h");
		
	}
	
	//Imprimindo peças no console
	
	
	private static void printPiece(ChessPiece piece,boolean background) {
		
		if(background==true) {
			System.out.print(ANSI_CYAN_BACKGROUND);
		}
		
		if (piece == null) {
            System.out.print("-" + ANSI_RESET);
        }
        else {
            if (piece.getColor() == Color.WHITE) {
                System.out.print(ANSI_WHITE + piece + ANSI_RESET);
            }
            else {
                System.out.print(ANSI_YELLOW + piece + ANSI_RESET);
            }
        }
        System.out.print(" ");
	}
	
	private static void printCapturedPieces(List<ChessPiece> captured) {
		List<ChessPiece> white = captured.stream().filter(x ->x.getColor()==Color.WHITE).collect(Collectors.toList());
		List<ChessPiece> black = captured.stream().filter(x ->x.getColor()==Color.BLACK).collect(Collectors.toList());
	    System.out.println("Peças Capturadas: ");
	    System.out.println("Brancas: ");
	    System.out.println(ANSI_WHITE);
	    System.out.println(Arrays.toString(white.toArray()));
	    System.out.println(ANSI_RESET);
	    
	    System.out.println("Pretas: ");
	    System.out.println(ANSI_YELLOW);
	    System.out.println(Arrays.toString(black.toArray()));
	    System.out.println(ANSI_RESET);
	    
	}
	
}
