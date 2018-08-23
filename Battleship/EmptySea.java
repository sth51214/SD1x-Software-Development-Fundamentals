package battleship;

// Describes a part of the ocean that does not have a ship in it.
public class EmptySea extends Ship{
	
	EmptySea() {
		this.setLength(1);
		this.setHit(new boolean [1]);
		this.getHit()[0] = false;
	}

	@Override
	public String getShipType() {
		return "empty";
	}
	
	@Override
	public boolean shootAt(int row, int column) {
		this.getHit()[0] = true;
		return false;
	}
	
	@Override

	public boolean isSunk() {
		return false;
	}
	
	@Override

	public String toString() {
		if(this.getHit()[0] == true ) {
			return "Ø";
		}
		return "~";
	}

}
