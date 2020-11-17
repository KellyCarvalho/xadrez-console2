package application;

import java.util.InputMismatchException;
import java.util.Scanner;

import BoardGame.Board;
import BoardGame.Position;
import chess.ChessException;
import chess.ChessMatch;
import chess.ChessPiece;
import chess.ChessPosition;
import chess.Color;
import chess.piece.Rook;

public class Program {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		ChessMatch chessMatch = new ChessMatch();
		
		while(true) {
			try {
			
					UI.clearScreen();
					UI.printBoard(chessMatch.getPieces());
					System.out.println();
					System.out.print("Origem: ");
					ChessPosition source = UI.readChessPosition(sc);
					System.out.println();
					System.out.print("Destino: ");
					ChessPosition target = UI.readChessPosition(sc);
					
					ChessPiece capturedPiece = chessMatch.performChessMove(source, target);
			
					
			}catch(ChessException e) {
				System.out.println(e.getMessage());
				sc.nextLine();
				
			}catch(InputMismatchException e) {
				System.out.println(e.getMessage());
				sc.nextLine();
			}
	
		
		}
	
	
		
	


	}

}
