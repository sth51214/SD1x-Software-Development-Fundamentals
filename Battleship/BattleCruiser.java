package battleship;

// Describes a battlecruiser - a ship that occupies 7 squares.
public class BattleCruiser extends Ship{
	
	BattleCruiser(){
		this.setLength(7);
		this.setHit(new boolean [this.getLength()]);
		for (int i = 0; i < this.getLength(); i++) {
            this.getHit()[i] = false;
        }
	}

	@Override
	String getShipType() {
		return "battleCruiser";
	}
	

}
