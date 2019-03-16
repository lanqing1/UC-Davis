import uwcse.graphics.*; // access the graphics utilities in the uw library
import java.awt.Color; // access the Color class


/**
 * <p>Create a cable car in a graphics window</p>  
 * @author your name here
 */

public class CableCar {

  // Your instance fields go here
	private int x,y;
	private double scale;
	private GWindow window;
	private boolean True;
	

  /**
   * Create a cable car at location (x,y) in the GWindow window.
   * @param x the x coordinate of the center of the cable car
   * @param y the y coordinate of the center of the cable car
 * @param i 
 * @param j 
   * @scale the factor that multiplies all default dimensions for this cable car
   * (e.g. if the default size is 80, the size of this cable car is
   * scale * 80)
   * @window the graphics window this cable car belongs to
   */
  public CableCar(int x, int y, double scale, GWindow window)
  {
    // initialize the instance fields
	this.x = x;
	this.y = y;
	this.scale = scale;
	this.window = window;
	
	  

    // The details of the drawing are in a private method
    this.draw();
  }

  /** Draw a cable car at location (x,y) */
  private void draw()
  
  {
	  Line line = new Line (0,120,600,120);
	  Triangle car = new Triangle (this.x*(int)(this.scale),this.y*(int)(this.scale)
		  ,this.x-(int)(30*this.scale),this.y+(int)(50*this.scale),
		  this.x+(int)(30*this.scale),this.y+(int)(50*this.scale),Color.black,True);
	  Rectangle car1 = new Rectangle(this.x-(int)(90*this.scale),
			  this.y+(int)(50*this.scale),200,80,Color.blue,true);
	  
	  Rectangle car2 = new Rectangle(this.x-(int)(70*this.scale),
			  this.y+(int)(70*this.scale),30,30,Color.white,true);
	  Rectangle car3 = new Rectangle(this.x-(int)(10*this.scale),
			  this.y+(int)(70*this.scale),30,30,Color.white,true);
	  Rectangle car4 = new Rectangle(this.x+(int)(50*this.scale),
			  this.y+(int)(70*this.scale),30,30,Color.white,true);
   
  
	this.window.add(line);	
  	this.window.add(car);
  	this.window.add(car1);
  	this.window.add(car2);
  	this.window.add(car3);
  	this.window.add(car4);
	  
  }
}
