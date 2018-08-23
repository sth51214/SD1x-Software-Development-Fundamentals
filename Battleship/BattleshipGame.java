package battleship;

import java.util.Scanner;

public class BattleshipGame {
	
	public static void main(String[] args) {
		
		// Set up a new game
		Ocean ocean = new Ocean();
		ocean.placeAllShipsRandomly();
		
		ocean.print();
		
		while(ocean.isGameOver() == false) {
			// Ask user for input
			Scanner input = new Scanner(System.in);
			System.out.println("To shoot, enter the semi-colon-separated coordinates "
					+ "with a space after the comma. " +
					"Like 1, 1; 0, 3; 7, 3; 9, 11; 12, 17\n" +
					"Each coordinate must be a pair of Integers."
					+ " Only 5 shots are accepted.");
			
			String[] coordinates = input.nextLine().split("; ");
			for(String coordinate: coordinates) {
				String[] shot = coordinate.split(", ");
				int row = Integer.parseInt(shot[0]);
				int col = Integer.parseInt(shot[1]);
				ocean.shootAt(row, col);
			}
			

			ocean.print();
		}
	}

}
