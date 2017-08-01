import java.util.ArrayList;

public class TypeMatrix {
	private static int[][] typeMatrix;	// conveniently initialized to all zeros
	
	/*
	 * Fill out values for the Pokemon Type Chart, using Gen 6 values
	 * 
	 * 0  == Normal
	 * 1  == Not very effective
	 * 2  == Super-effective
	 * -1 == No effect
	 */
	public static void populateMatrix() {
		typeMatrix = new int[18][18];
		
		// [Attacker] [Defender]
		
		// NORMAL
		typeMatrix[0][12] =  1;
		typeMatrix[0][13] = -1;
		typeMatrix[0][16] =  1;
		
		// FIRE
		typeMatrix[1][1]  = 1;
		typeMatrix[1][2]  = 1;
		typeMatrix[1][4]  = 2;
		typeMatrix[1][5]  = 2;
		typeMatrix[1][11] = 2;
		typeMatrix[1][12] = 1;
		typeMatrix[1][14] = 1;
		typeMatrix[1][16] = 2;
		
		// WATER
		typeMatrix[2][1]  = 2;
		typeMatrix[2][2]  = 1;
		typeMatrix[2][4]  = 1;
		typeMatrix[2][8]  = 2;
		typeMatrix[2][12] = 2;
		typeMatrix[2][14] = 1;
		
		// ELECTRIC
		typeMatrix[3][2]  =  2;
		typeMatrix[3][3]  =  1;
		typeMatrix[3][4]  =  1;
		typeMatrix[3][8]  = -1;
		typeMatrix[3][9]  =  2;
		typeMatrix[3][14] =  1;
		
		// GRASS
		typeMatrix[4][1]  = 1;
		typeMatrix[4][2]  = 2;
		typeMatrix[4][4]  = 1;
		typeMatrix[4][7]  = 1;
		typeMatrix[4][8]  = 2;
		typeMatrix[4][9]  = 1;
		typeMatrix[4][11] = 1;
		typeMatrix[4][12] = 2;
		typeMatrix[4][14] = 1;
		typeMatrix[4][16] = 1;
		
		// ICE
		typeMatrix[5][1]  = 1;
		typeMatrix[5][2]  = 1;
		typeMatrix[5][4]  = 2;
		typeMatrix[5][5]  = 1;
		typeMatrix[5][8]  = 2;
		typeMatrix[5][9]  = 2;
		typeMatrix[5][14] = 2;
		typeMatrix[5][16] = 1;
		
		// FIGHTING
		typeMatrix[6][0]  =  2;
		typeMatrix[6][5]  =  2;
		typeMatrix[6][7]  =  1;
		typeMatrix[6][9]  =  1;
		typeMatrix[6][10] =  1;
		typeMatrix[6][11] =  1;
		typeMatrix[6][12] =  2;
		typeMatrix[6][13] = -1;
		typeMatrix[6][15] =  2;
		typeMatrix[6][16] =  2;
		typeMatrix[6][17] =  1;
		
		// POISON
		typeMatrix[7][4]  =  2;
		typeMatrix[7][7]  =  1;
		typeMatrix[7][8]  =  1;
		typeMatrix[7][12] =  1;
		typeMatrix[7][13] =  1;
		typeMatrix[7][16] = -1;
		typeMatrix[7][17] =  2;
		
		// GROUND
		typeMatrix[8][1]  =  2;
		typeMatrix[8][3]  =  2;
		typeMatrix[8][4]  =  1;
		typeMatrix[8][7]  =  2;
		typeMatrix[8][9]  = -1;
		typeMatrix[8][11] =  1;
		typeMatrix[8][12] =  2;
		typeMatrix[8][16] =  2;
		
		// FLYING
		typeMatrix[9][3]  = 1;
		typeMatrix[9][4]  = 2;
		typeMatrix[9][6]  = 2;
		typeMatrix[9][11] = 2;
		typeMatrix[9][12] = 1;
		typeMatrix[9][16] = 1;
		
		// PSYCHIC
		typeMatrix[10][6]  =  2;
		typeMatrix[10][7]  =  2;
		typeMatrix[10][10] =  1;
		typeMatrix[10][15] = -1;
		typeMatrix[10][16] =  1;
		
		// BUG
		typeMatrix[11][1]  = 1;
		typeMatrix[11][4]  = 2;
		typeMatrix[11][6]  = 1;
		typeMatrix[11][7]  = 1;
		typeMatrix[11][9]  = 1;
		typeMatrix[11][10] = 2;
		typeMatrix[11][13] = 1;
		typeMatrix[11][15] = 2;
		typeMatrix[11][16] = 1;
		typeMatrix[11][17] = 1;
		
		// ROCK
		typeMatrix[12][1]  = 2;
		typeMatrix[12][5]  = 2;
		typeMatrix[12][6]  = 1;
		typeMatrix[12][8]  = 1;
		typeMatrix[12][9]  = 2;
		typeMatrix[12][11] = 2;
		typeMatrix[12][16] = 1;
		
		// GHOST
		typeMatrix[13][0]  = -1;
		typeMatrix[13][10] =  2;
		typeMatrix[13][13] =  2;
		typeMatrix[13][15] =  1;
		
		// DRAGON
		typeMatrix[14][14] =  2;
		typeMatrix[14][16] =  1;
		typeMatrix[14][17] = -1;
		
		// DARK
		typeMatrix[15][6]  = 1;
		typeMatrix[15][10] = 2;
		typeMatrix[15][13] = 2;
		typeMatrix[15][15] = 1;
		typeMatrix[15][17] = 1;
		
		// STEEL
		typeMatrix[16][1]  = 1;
		typeMatrix[16][2]  = 1;
		typeMatrix[16][3]  = 1;
		typeMatrix[16][5]  = 2;
		typeMatrix[16][12] = 2;
		typeMatrix[16][16] = 1;
		typeMatrix[16][17] = 2;
		
		// FAIRY
		typeMatrix[17][1]  = 1;
		typeMatrix[17][6]  = 2;
		typeMatrix[17][7]  = 1;
		typeMatrix[17][14] = 2;
		typeMatrix[17][15] = 2;
		typeMatrix[17][16] = 1;
				
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		Type[] typeNames = Type.values();
		int numTypes = typeNames.length;
		
		for (int i = 0; i < numTypes; i++) {
			sb.append(typeNames[i].name().toUpperCase());
			sb.append("\n");
			
			ArrayList<String> weakAgainst = new ArrayList<>();
			ArrayList<String> strongAgainst = new ArrayList<>();
			ArrayList<String> noEffectOn = new ArrayList<>();
			
			for (int j = 0; j < typeNames.length; j++) {
				int result = typeMatrix[i][j];
				
				if (result == 1) {
					weakAgainst.add(typeNames[j].name().toUpperCase());
				} else if (result == 2) {
					strongAgainst.add(typeNames[j].name().toUpperCase());
				} else if (result == -1) {
					noEffectOn.add(typeNames[j].name().toUpperCase());
				}
			}
			
			if (weakAgainst.size() > 0) {
				sb.append("Weak against: ");
				
				for (int s = 0; s < weakAgainst.size(); s++) {
					if (s != 0)
						sb.append(", ");
					sb.append(weakAgainst.get(s));
				}
				sb.append("\n");
			}
			
			if (strongAgainst.size() > 0) {
				sb.append("Strong against: ");
				
				for (int s = 0; s < strongAgainst.size(); s++) {
					if (s != 0)
						sb.append(", ");
					sb.append(strongAgainst.get(s));
				}
				sb.append("\n");
			}
			
			if (noEffectOn.size() > 0) {
				sb.append("No effect on: ");
				
				for (int s = 0; s < noEffectOn.size(); s++) {
					if (s != 0)
						sb.append(", ");
					sb.append(noEffectOn.get(s));
				}
				sb.append("\n");
			}
			
			sb.append("\n");
		}
		
		
		return sb.toString();
	}


	public static int lookupValue(int attackerId, int defenderId) {
		return typeMatrix[attackerId][defenderId];
	}

}
