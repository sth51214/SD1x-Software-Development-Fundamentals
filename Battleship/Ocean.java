package battleship;

import java.util.*;

public class Ocean {
	
	private Ship[][] ships = new Ship[20][20]; // Used to quickly determine which ship is in any 
								 	   // given location.
	private int shotsFired; // The total number of shots fired by the user.
	private int hitCount; // The number of times a shot hit a ship.
	private int shipsSunk; // The number of ships sunk.
	
	
	public Ocean() {
		for (int row = 0; row < 20; row ++) {
			for(int col = 0; col < 20; col ++) {
				this.ships[row][col] = new EmptySea();
			}
		}
		shotsFired = 0;
		hitCount = 0;
		shipsSunk = 0;
	}
	
	public void placeAllShipsRandomly() {
		
		Random random = new Random();
		
		Ship[] ship = new Ship[13];
		
		for(int index = 0; index < 13; index ++) {
			if(index == 0) {
				ship[index] = new BattleShip();
			}
			else if(index == 1) {
				ship[index] = new BattleCruiser();
			}
			else if (index < 4) {
				ship[index] = new Cruiser();
			} 
			else if (index < 6) {
				ship[index] = new LightCruiser();
			} 
			else if (index < 9) {
				ship[index] = new Destroyer();
			} 
			else if (index < 13) {
				ship[index] = new Submarine();
			}
		}
		
		for(Ship shipToPlace: ship) {
			while(true) {
				int randRow = random.nextInt(20);
				int randColumn = random.nextInt(20);
				
				boolean horizontal = random.nextBoolean();
				if(shipToPlace.okToPlaceShipAt(randRow, randColumn, horizontal, this)) {
					shipToPlace.placeShipAt(randRow, randColumn, horizontal, this);
					break;
				}
			}
		}
		
	}
	
	public boolean isOccupied(int row, int column) {
		if(this.getShipArray()[row][column].getShipType().equals("empty") == false)
			return true;
		return false;
	}
	
	public boolean shootAt(int row, int column) {
		this.shotsFired ++;
		if(this.isOccupied(row, column)	&& this.getShipArray()[row][column].isSunk() == false){
			this.getShipArray()[row][column].shootAt(row, column);
			if(this.getShipArray()[row][column].isSunk()) this.shipsSunk ++;
			this.hitCount ++;
			return true;
		}
		return false;
	}
	
	public int getShotsFired() {
		return shotsFired;
	}
	
	public int getHitCount() {
		return hitCount;
	}
	
	public int getShipsSunk() {
		return shipsSunk;
	}
	
	public boolean isGameOver() {
		return this.getShipsSunk() == 13;
	}
	
	public Ship[][] getShipArray(){
		return ships;
	}
	
	public void print() {
		for(int colIndex = 0; colIndex < 20; colIndex ++) {
			System.out.print("	" + colIndex);
		}
		System.out.println();
		int index;
		for (int row = 0; row < 20; row ++) {
			System.out.print(row + "	");
			for(int col = 0; col < 20; col ++) {
				if (this.getShipArray()[row][col].isHorizontal()) {
		            index = col - this.getShipArray()[row][col].getBowColumn();
				}
				else {
					index = row - this.getShipArray()[row][col].getBowRow();
				}
				if (this.getShipArray()[row][col].getShipType().equals("empty")) {
					System.out.print(this.getShipArray()[row][col] + "	");
				}
				else {
					if(this.getShipArray()[row][col].getHit()[index]) {
						System.out.print(this.getShipArray()[row][col] + "	");
					}
					else System.out.print("~" + "	");
				}
			}
			System.out.println();
		}

		
	}
}
