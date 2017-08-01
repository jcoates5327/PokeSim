
public enum Type {
	//Normal   (0,  163, 163, 115),
	Fire     (1,  236, 122, 41),
	Water    (2,  148, 195, 155),
	//Electric (3,  246, 205, 39),
	Grass    (4,  112, 191, 72);
//	Ice      (5,  142, 212, 212),
//	Fighting (6,  184, 46,  38),
//	Poison   (7,  153, 61,  153),
//	Ground   (8,  222, 187, 91),
//	Flying   (9,  163, 141, 230),
//	Psychic  (10, 247, 75,  127),
//	Bug      (11, 160, 176, 30),
//	Rock     (12, 176, 153, 54),
//	Ghost    (13, 106, 83,  144),
//	Dragon   (14, 106, 48,  247),
//	Dark     (15, 105, 82,  68),
//	Steel    (16, 177, 177, 203),
//	Fairy    (17, 229, 140, 229);
	
	
	private final int id;
	private final int r, g, b;
	
	private Type(int id, int r, int g, int b) {
		this.id = id;
		this.r = r;
		this.g = g;
		this.b = b;
	}
	
	public int id() {
		return id;
	}
	
	public int[] getRGB() {
		int[] rgb = {r, g, b};
		
		return rgb;
	}
	
	
}
