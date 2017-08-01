import processing.core.PApplet;
import java.util.ArrayList;

public class PokeSim extends PApplet {

	private final int INTERVAL = 1;
	private final int NUM_ITERATIONS = 10000;
	
	private int lastTime;
	private int numPixels;
	private int curIteration;
	
	private Poke[] pokemon;

	public static void main(String[] args) {
		PApplet.main("PokeSim");
		TypeMatrix.populateMatrix();
	}
	
	
	public void settings() {
		size(800, 800);
	}

	public void setup()	{
	  frameRate(1440);
	  lastTime = 0;
	  curIteration = -1;
	  
	  numPixels = width * height;
	  
	  pokemon = new Poke[height * width];
	  
	  generatePokemon();
	  
	  // test the distribution of Types
	  int[] test = new int[18];
	  for (Poke p : pokemon) {
		  test[p.getType().id()]++;
	  }
	  System.out.print("[");
	  for (int t : test) {
		  System.out.print(" " + t);
	  }
	  System.out.println(" ]");
	  
	}

	public void draw() {
		if (curIteration < 0) {
			loadPixels();
			drawPokemon();
			updatePixels();
			
			curIteration++;
		}
		
		if ((curIteration < NUM_ITERATIONS)) {
			// pick a random pixel from those remaining
			// get neighboring pixels
			// have Poke at center fight neighbors
			// if super-effective, assign Poke's color to specified neighbor
			
			
			// TODO: try accounting for weak + effective; use bigger blocks instead of ind. pixels
			
			loadPixels();
			
			for (int i = 0; i < numPixels; i++) {
				Poke poke = pokemon[i];
				int[] neighbors = getNeighbors(i);
				
				for (int pixel : neighbors) {
					if ((pixel >= 0) && poke.fight(pokemon[pixel])) {
						pokemon[pixel] = new Poke(pixel, poke.getType());
						int[] rgb = pokemon[pixel].getType().getRGB();
						
						pixels[pixel] = color(rgb[0], rgb[1], rgb[2]);
					}
				}
			}
			//System.out.println("Iteration: " + curIteration);
			updatePixels();
			
			curIteration++;
		}
		
		if (curIteration == (NUM_ITERATIONS - 1)) {
			int[] test = new int[18];
			  for (Poke p : pokemon) {
				  test[p.getType().id()]++;
			  }
			  System.out.print("[");
			  for (int t : test) {
				  System.out.print(" " + t);
			  }
			  System.out.println(" ]");
			  curIteration++;
		}
	}
	
	/*
	 * Generate a Pokemon of random Type for each pixel
	 */
	private void generatePokemon() {
		Type[] types = Type.values();
		
		for (int i = 0; i < numPixels; i++) {
			int typeId = (int) Math.floor(random(types.length));	// get a random integer between 0 and 17, inclusive
			
			pokemon[i] = new Poke(i, types[typeId]);	// populate array with Pokemon of randomly assigned type
		}
	}
	
	private void drawPokemon() {
		for (int i = 0; i < numPixels; i++) {
			int[] rgb = pokemon[i].getType().getRGB();
			
			pixels[i] = color(rgb[0], rgb[1], rgb[2]);
		}
	}
	
	/*
	 * Given a pixel, get the index of its 8 neighbors
	 * for now, not wrapping around borders; border pixels will have -1 for missing neighbors
	 */
	private int[] getNeighbors(int loc) {
		int[] neighbors = new int[8];	// indices of 8 neighbor pixels, in order from top left to bottom right
		
		/* handle edge cases */
		
		// top left corner
		if (loc == 0) {
			neighbors[0] = neighbors[1] = neighbors[2] = neighbors[3] = -1;
			neighbors[4] = 1;
			neighbors[5] = -1;
			neighbors[6] = width;
			neighbors[7] = width + 1;
		}
		
		// top right corner
		else if (loc == width - 1) {
			neighbors[0] = neighbors[1] = neighbors[2] = -1;
			neighbors[3] = width - 1;
			neighbors[4] = -1;
			neighbors[5] = (loc + width) - 1;
			neighbors[6] = loc + width;
			neighbors[7] = -1;
		}
		
		// bottom left corner
		else if (loc == numPixels - width) {
			neighbors[0] = -1;
			neighbors[1] = loc - width;
			neighbors[2] = (loc - width) + 1;
			neighbors[3] = -1;
			neighbors[4] = loc + 1;
			neighbors[5] = neighbors[6] = neighbors[7] = -1;
		}
		
		// bottom right corner
		else if (loc == numPixels - 1) {
			neighbors[0] = (loc - width) - 1;
			neighbors[1] = loc - width;
			neighbors[2] = -1;
			neighbors[3] = loc - 1;
			neighbors[4] = neighbors[5] = neighbors[6] = neighbors[7] = -1;
		}
		
		// top row
		else if (loc < width - 1) {
			neighbors[0] = neighbors[1] = neighbors[2] = -1;
			neighbors[3] = loc - 1;
			neighbors[4] = loc + 1;
			neighbors[5] = (loc + width) - 1;
			neighbors[6] = loc + width;
			neighbors[7] = (loc + width) + 1;
		}
		
		// bottom row
		else if ((loc > numPixels - width) && (loc < numPixels - 1)) {
			neighbors[0] = (loc - width) - 1;
			neighbors[1] = loc - width;
			neighbors[2] = (loc - width) + 1;
			neighbors[3] = loc - 1;
			neighbors[4] = loc + 1;
			neighbors[5] = neighbors[6] = neighbors[7] = -1;
		}
		
		// left-most column
		else if (loc % width == 0) {
			neighbors[0] = -1;
			neighbors[1] = loc - width;
			neighbors[2] = (loc - width) + 1;
			neighbors[3] = -1;
			neighbors[4] = loc + 1;
			neighbors[5] = -1;
			neighbors[6] = loc + width;
			neighbors[7] = (loc + width) + 1;
		}
		
		// right-most column
		else if ((loc + 1) % width == 0) {
			neighbors[0] = (loc - width) - 1;
			neighbors[1] = loc - width;
			neighbors[2] = -1;
			neighbors[3] = loc -1;
			neighbors[4] = -1;
			neighbors[5] = (loc + width) - 1;
			neighbors[6] = loc + width;
			neighbors[7] = -1;
		}
		
		return neighbors;
	}
	
	/*
	 * Keep track of amount of time passed
	 * Returns true if amount is greater than interval specified by interval property
	 */
	private boolean checkTime() {
		int curTime = millis();
		
		if ((curTime - lastTime) >= INTERVAL) {
			lastTime = curTime;
			return true;
		}
		
		return false;
	}

}
