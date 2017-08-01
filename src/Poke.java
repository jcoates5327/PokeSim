
public class Poke {
	private int initPixel;
	private boolean fought;
	
	private Type type;
	
	public Poke(int initPixel, Type type) {
		this.initPixel = initPixel;
		this.type = type;
	}
	
	public boolean fight(Poke opponent) {
		int attId = type.id();
		int defId = opponent.getType().id();
		
		int result = TypeMatrix.lookupValue(attId, defId);
		
		// for now we're only looking for super-effective result
		if (result == 2) {
			return true;
		}
		
		return false;
	}
	
	public Type getType() {
		return type;
	}
	
	public boolean hasFought() {
		return fought;
	}
	
	public void setFought(boolean fought) {
		this.fought = fought;
	}
	
	public int getInitPixel() {
		return initPixel;
	}
	
}
