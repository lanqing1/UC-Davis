import uwcse.graphics.*; // access the graphics utilities in the uw library
import java.awt.Color; // access the Color class

/**
 * <p>
 * A MountainScene displays snow men, trees (with ornaments), a cable car and a
 * fourth element of your choice in a graphics window
 * </p>
 * 
 * @author Your name here
 */

public class MountainScene {

	/** The graphics window that displays the picture */
	private GWindow window;

	/**
	 * Create an image of a mountain scene
	 */
	/**
	 * 
	 */
	public MountainScene() {

		// The graphics window
		// The window is by default 500 wide and 400 high
		this.window = new GWindow("Mountain scene");
		this.window.setExitOnClose(); // so that a click on the close box of the
		// window terminates the application

		// Background (cyan here)
		Rectangle bgnd = new Rectangle(0, 0, window.getWindowWidth(), window
				.getWindowHeight(), Color.cyan, true);
		this.window.add(bgnd);

		// Create the scene elements
		// e.g. a tree in the lower left area 1.5 times the normal size
		new Tree(50, 325, 1.2, this.window);
		new Tree(250,220,0.9,this.window);
		new Tree(450,70,0.7,this.window);
		new Tree(300,50,0.7,this.window);
		new Tree(30,70,0.7,this.window);
		
		new SnowMan(370,40,0.6,this.window);
		new SnowMan(100,20,0.6,this.window);
		new SnowMan(350,200,1.3,this.window);
		
		new CableCar(100,120,1,this.window);
		
		//call BayMax
		new BayMax(150,270,0.5,this.window);
		new BayMax(200,10,0.4,this.window);	
		
	}

	/**
	 * Entry point of the program
	 */
	public static void main(String[] args) {
		new MountainScene();
	}

}
