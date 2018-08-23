package battleship;

public class Destroyer extends Ship{
	
	Destroyer(){
		this.setLength(4);
		this.setHit(new boolean [this.getLength()]);
		for (int i = 0; i < this.getLength(); i++) {
            this.getHit()[i] = false;
        }
	}

	@Override
	String getShipType() {
		return "destroyer";
	}

}
