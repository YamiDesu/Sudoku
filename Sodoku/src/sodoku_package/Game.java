package sodoku_package;

public class Game {

	public static void start() {
		
				Board b = new Board();
				b.create();
				b.print();

		
		
		//Board.print();
		
		/*	public static void print() {
		for(int x=0; x<board.length ; x++) {
			for(int y=0; y<board[x].length ; y++) {
				System.out.print (board[x][y] + " ");
			}
			System.out.println(" ");
		}
	}*/
		
	}
	
}
