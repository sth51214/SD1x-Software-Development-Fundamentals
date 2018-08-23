package battleship;

public class LightCruiser extends Ship{
	
	LightCruiser(){
		this.setLength(5);
		this.setHit(new boolean [this.getLength()]);
		for (int i = 0; i < this.getLength(); i++) {
            this.getHit()[i] = false;
        }
	}

	@Override
	String getShipType() {
		return "lightCruiser";
	}

}
