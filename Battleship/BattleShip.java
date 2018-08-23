package battleship;


// Describes a battleship - a ship that occupies 8 squares.
public class BattleShip extends Ship{
		
	BattleShip(){
		this.setLength(8);
		this.setHit(new boolean [this.getLength()]);
		for (int i = 0; i < this.getLength(); i++) {
            this.getHit()[i] = false;
        }
	}

	@Override
	String getShipType() {
		return "battleship";
	}

}
