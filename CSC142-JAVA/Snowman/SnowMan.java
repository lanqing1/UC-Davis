import uwcse.graphics.*; // access the graphics utilities in the uw library
import java.awt.Color; // access the Color class

/**
 * <p>Create a snow man in a graphics window</p>
 * @author your name here
 */  

public class SnowMan {

  // Your instance fields go here
	private GWindow window;
	private int x,y;
	private double scale;
	
	
	

  /**
   * Create a snow man in at location (x,y) in the GWindow window.
   * @param x the x coordinate of the center of the head of the snow man
   * @param y the y coordinate of the center of the head of the snow man
   * @scale the factor that multiplies all default dimensions for this snow man
   * (e.g. if the default head radius is 20, the head radius of this snow man is
   * scale * 20)
   * @window the graphics window this snow man belongs to
   */
  public SnowMan(int x, int y, double scale, GWindow window)
  {
    // initialize the instance fields
	  this.x = x;
	  this.y = y;
	  this.scale = scale;
	  this.window = window;
	  

    // Put the details of the drawing in a private method
    this.draw();
  }

  /** Draw in the graphics window a snow man at location (x,y) */
  private void draw()
  {
	  
	  Oval head = new Oval(this.x,this.y, (int)(50*this.scale),
			  (int)(50*this.scale),Color.white,true);
	  Oval body = new Oval(this.x-(int)(18*this.scale),this.y+(int)(40*this.scale),
			  (int)(90*this.scale), (int)(90*this.scale),Color.white,true);
	  Oval eye = new Oval(this.x+(int)(10*this.scale),this.y+(int)(10*this.scale),
			  (int)(10*this.scale), (int)(10*this.scale), Color.black,true);
	  Oval eye1 = new Oval(this.x+(int)(30*this.scale),this.y+(int)(10*this.scale),
			  (int)(10*this.scale), (int)(10*this.scale), Color.black,true);
	  Triangle nose = new Triangle (this.x+(int)(25*this.scale),this.y+(int)(16*this.scale),
			  this.x+(int)(20*this.scale),this.y+(int)(25*this.scale),
			  this.x+(int)(30*this.scale),this.y+(int)(25*this.scale),Color.ORANGE,true);
	  Rectangle mouth = new Rectangle (this.x+(int)(18*this.scale),
			  this.y+(int)(28*this.scale),(int)(15*this.scale),
			  (int)(5*this.scale),Color.black,true);
	  Oval button = new Oval(this.x+(int)(19*this.scale),this.y+(int)(45*this.scale),
			  (int)(12*this.scale), (int)(12*this.scale), Color.black,true);
	  Oval button1 = new Oval(this.x+(int)(19*this.scale),this.y+(int)(70*this.scale),
			  (int)(12*this.scale), (int)(12*this.scale), Color.black,true);
	  Oval button2 = new Oval(this.x+(int)(19*this.scale),this.y+(int)(95*this.scale),
			  (int)(12*this.scale), (int)(12*this.scale), Color.black,true);
	  Rectangle hat = new Rectangle (this.x+(int)(10*this.scale),
			  this.y-(int)(1*this.scale),(int)(30*this.scale),
			  (int)(5*this.scale),Color.yellow,true);
	  Rectangle hat1 = new Rectangle (this.x+(int)(15*this.scale),
			  this.y-(int)(20*this.scale),(int)(18*this.scale),
			  (int)(20*this.scale),Color.red,true);
	  Line hand =new Line(this.x-(int)(30*this.scale),this.y+(int)(60*this.scale)
			  ,this.x+(int)(10*this.scale),this.y+(int)(60*this.scale));
	  Line hand1 =new Line(this.x+(int)(40*this.scale),this.y+(int)(60*this.scale)
			  ,this.x+(int)(80*this.scale),this.y+(int)(60*this.scale));
	  
	  Line hand2 =new Line(this.x-(int)(30*this.scale),this.y+(int)(50*this.scale)
			  ,this.x-(int)(15*this.scale),this.y+(int)(60*this.scale));
	  Line hand3 =new Line(this.x+(int)(65*this.scale),this.y+(int)(60*this.scale)
			  ,this.x+(int)(80*this.scale),this.y+(int)(50*this.scale));

	  
	  this.window.add(head);
	  this.window.add(body);
	  this.window.add(eye);
	  this.window.add(eye1);
	  this.window.add(nose);
	  this.window.add(mouth);
	  this.window.add(button);
	  this.window.add(button1);
	  this.window.add(button2);
	  this.window.add(hat1);
	  this.window.add(hat);
	  this.window.add(hand);
	  this.window.add(hand1);
	  this.window.add(hand2);
	  this.window.add(hand3);
	    
	  
  }
}
