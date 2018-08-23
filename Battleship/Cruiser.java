package battleship;

public class Submarine extends Ship{
	
	Submarine(){
		this.setLength(3);
		this.setHit(new boolean [this.getLength()]);
		for (int i = 0; i < this.getLength(); i++) {
            this.getHit()[i] = false;
        }
	}

	@Override
	String getShipType() {
		return "submarine";
	}

}
