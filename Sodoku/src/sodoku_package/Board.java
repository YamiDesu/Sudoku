package sodoku_package;
import java.util.*;

public class Board {

	ArrayList<Integer> range = new ArrayList<Integer>();
	ArrayList<Integer> column = new ArrayList<Integer>();
	int[][] board = new int[9][9];
	int posRange = 0;
	int problemCounter = 0;
	
	// Grundgerüst, um das Spielbrett zu erstelen
	public void create() {
		for(int x=0 ; x<board.length ; x++) {
			// Notwendig für die Exception
			for(boolean checkRow = true ; checkRow ; ) {
				try {
					createColumn(x);
					checkRow = false;
				}
				catch (Exception e) {
					//e.printStackTrace();
					clearBoth();
					posRange=0;
					problemCounter++;
					if(problemCounter==1000) {
						System.out.println("Problem-Counter hit 1k");
						e.printStackTrace();
						System.exit(0);
					}
					continue;
				}
			}
			for(int i=0; i<9 ; i++)
				board[x][i] = column.get(i);
			clearBoth();
			System.out.println(" ");
			print();
			System.out.println(" ");
		}
	}
	
	// Erstellt eine ganze Reihe
	public void createColumn(int x) throws Exception{

		fillRange();

		for(int y=0; y<board.length ; y++) {
			while( column.size() < 9 ) {

				if(posRange == range.size())
					throw new Exception(" Out of Bounds in der Range ");

				Collections.shuffle(range);
				if(checkNumber(x,y,range.get(posRange))) {
					column.add(range.get(posRange));
					range.remove(posRange);
					posRange = 0;
					break;
				}
				else
					posRange++;
			}
		}
	}

	/////////////// M E T H O D E N ////////////////////////////
	
	// Druckt das Feld aus, wenn alles erfolgreich war
	public void print() {
		//System.out.println(Arrays.deepToString(board).replace("], ", "\n").replace("[", "").replace("]", "").replace(",",""));
		for(int x=0 ; x<board.length ; x++) {
			for(int y=0 ; y<board.length ; y++)
					System.out.print(" " + board[y][x]);
			System.out.println(" ");
		}
	}
	
	// Druckt die fertige Reihe aus, zum Testen
	public void printRow(int y) {
		for(int x=0 ; x<board.length ; x++)
			System.out.print(board[x][y] + " ");
		System.out.println("");
	}
	
	// Füllt den Zahlenspeicher auf
	public void fillRange() {
		for(int i=1 ; i <= 9 ; i++) { // zu 8 machen?
			range.add(i);
		}
	}
	
	// Prüft die eingegebene Zahl
	public boolean checkNumber(int x, int y, int zahl){
		// Check Column
		for(int c=0 ; c<column.size(); c++)
			if( column.size() > 0)
				if( column.get(c) == zahl)
					return false;
		// Check Row
		for(int r=0 ; r<9 ; r++)
			if( board[r][y] == zahl )
				return false;
			/*else if( board[r][y] == zahl )
				return false;*/
		// Check Box
		for(int xBox=(x/3)*3 ; xBox<=((x/3)*3)+2 ; xBox++)
			for(int yBox=(y/3)*3 ; yBox<=((y/3)*3)+2 ; yBox++)
				if( board[xBox][yBox] == zahl )
					return false;
		// Return, wenn die Zahl passt
		return true;
	}
	
	// Um Platz zu sparen
	public void clearBoth() {
		column.clear();
		range.clear();
	}
	
}
