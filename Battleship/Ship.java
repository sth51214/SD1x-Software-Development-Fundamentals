package battleship;


// We never really want to create a Ship.
// We always want to create a specific type of Ship.
// So this means the declaration will be abstract.
public abstract class Ship {
	
	// Class instances
	private int bowRow; // The row (0 to 19) which contains the bow (front) of the ship.
	private int bowColumn; // The column which contains the bow (front) of the ship.    
	private int length; // The number of squares occupied by the ship. Empty sea has length 1.
	private boolean horizontal; // True if the ship occupies a single row, false otherwise.
	private boolean[] hit; // This is a boolean array of size 8 that record hits.
	
	
	public int getBowRow() {
		return bowRow;
	}
	
	public void setBowRow(int bowRow) {
		this.bowRow = bowRow;
	}
	
	public int getBowColumn() {
		return bowColumn;
	}
	
	public void setBowColumn(int bowColumn) {
		this.bowColumn = bowColumn;
	}
	
	public int getLength() {
		return length;
	}
	
	public void setLength(int length) {
		this.length = length;
	}
	
	public boolean isHorizontal() {
		return horizontal;
	}
	
	public void setHorizontal(boolean horizontal) {
		this.horizontal = horizontal;
	}
	
	public boolean[] getHit() {
		return hit;
	}
	
	public void setHit(boolean[] hit) {
		this.hit = hit;
	}
	
	abstract String getShipType();
	
	public boolean okToPlaceShipAt(int row, int column, boolean horizontal, Ocean ocean) {
		
		if(ocean.isOccupied(row, column)) return false;
		if(horizontal) {
			if(column + this.getLength() > 20) return false;
			for (int shipRow = row - 1; shipRow <= row + 1; shipRow ++) {
				for( int index = column - 1; index < column + this.getLength() + 1; index ++) {
					try {
						if(ocean.isOccupied(shipRow, index))
							return false;
					}catch(Exception e) {
						continue;
					}
					
				}
			}
		}
		else {
			if(row + this.getLength() > 20) return false;
			for (int shipCol = column - 1; shipCol <= column + 1; shipCol ++) {
				for( int index = row - 1; index < row + this.getLength() + 1; index ++) {
					try {
						if(ocean.isOccupied(index, shipCol))
							return false;
					}catch (Exception e) {
						continue;
					}
				}
			}
		}
		return true;
	}
	
	public void placeShipAt(int row, int column, boolean horizontal, Ocean ocean) {
		
		// Set values of bowRow, bowColumn, and
        // horizontal for the ship.
		this.setBowRow(row);
		this.setBowColumn(column);
		this.setHorizontal(horizontal);
		
		// Set references to the ship in 
		// the ships array in the Ocean object
		if(horizontal) {
			for( int index = column; index < column + this.getLength(); index ++) {
				ocean.getShipArray()[row][index] = this;
			}
		}
		else {
			for( int index = row; index < row + this.getLength(); index ++) {
				ocean.getShipArray()[index][column] = this;
			}
		}
	}

	public boolean shootAt(int row, int column) {
		if(!this.isSunk()) {
			if(this.horizontal) {
				if(this.getBowRow() == row && column < this.getBowColumn() 
						+ this.getLength() && column >= this.getBowColumn()) {
					this.hit[column - this.getBowColumn()] = true;
					return true;
				}
			}
			else {
				if(this.getBowColumn() == column && row < this.getBowRow()
						+ this.getLength() && row >= this.getBowRow()) {
					this.hit[row - this.getBowRow()] = true;
					return true;
				}			
			}
		}
		return false;
	}
	
	public boolean isSunk() {
		for(boolean hit: hit) {
			if(hit == false) return false;
		}
		return true;
	}
	
	@Override

	public String toString() {
		if(this.isSunk()) return "x";
		return "S";		
	}
}
