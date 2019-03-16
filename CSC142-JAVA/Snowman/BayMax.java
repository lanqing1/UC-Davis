import java.awt.Color;// import color
import uwcse.graphics.*;//import uw library 

public class BayMax {
	
	//instance fields 
	private int x,y;
	private double scale;
	private GWindow window;
	private boolean True;

	/**
	   * Create a BayMax in the GWindow window.
	   * @param x the x coordinate of the center of the head of BayMax
	   * @param y the y coordinate of the center of the head of BayMax
	 * @param i 
	 * @param j 
	   * @scale the factor that multiplies all default dimensions for this BayMax
	   * (e.g. if the default size is 80, the size of this cable car is
	   * scale * 80)
	   * @window the graphics window this BayMax belongs to
	   */	
public BayMax(int x,int y, double scale, GWindow window){
	
	 // initialize the instance fields
	this.x = x;
	this.y = y;
	this.scale = scale;
	this.window = window;
	
	// The details of the drawing are in a private method
	this.draw();
}
	
private void draw(){
	
	// create BayMax	
	Oval head = new Oval(this.x,this.y,(int)(100*this.scale),
			(int)(80*this.scale),Color.white,true);
	Oval body = new Oval(this.x-(int)(10*this.scale),this.y+(int)(65*this.scale),
			(int)(120*this.scale),(int)(150*this.scale),Color.white,true);
	Oval eye = new Oval(this.x+(int)(20*this.scale),this.y+(int)(30*this.scale),
			(int)(20*this.scale),(int)(20*this.scale),Color.black,true);
	
	Oval eye1 = new Oval(this.x+(int)(60*this.scale),this.y+(int)(30*this.scale),
			(int)(20*this.scale),(int)(20*this.scale),Color.black,true);
	
	Line eye2 = new Line(this.x+(int)(20*this.scale),this.y+(int)(40*this.scale),
			this.x+(int)(65*this.scale),this.y+(int)(40*this.scale));
	
	Oval hand =new Oval(this.x-(int)(40*this.scale),this.y+(int)(100*this.scale),
			(int)(80*this.scale),(int)(30*this.scale),Color.white,true);
	
	Oval hand1 =new Oval(this.x+(int)(60*this.scale),this.y+(int)(100*this.scale),
			(int)(80*this.scale),(int)(30*this.scale),Color.white,true);
	

	
	Rectangle foot1 =new Rectangle(this.x+(int)(10*this.scale),this.y+(int)(190*this.scale),
			(int)(30*this.scale),(int)(30*this.scale),Color.white,true);
	Rectangle foot2 = new Rectangle(this.x+(int)(60*this.scale),this.y+(int)(190*this.scale),
			(int)(30*this.scale),(int)(30*this.scale),Color.white,true);
	
	Oval foot3 = new Oval(this.x+(int)(8*this.scale),this.y+(int)(210*this.scale),
			(int)(30*this.scale),(int)(18*this.scale),Color.white,true);
	
	Oval foot4 = new Oval(this.x+(int)(61*this.scale),this.y+(int)(210*this.scale),
			(int)(30*this.scale),(int)(18*this.scale),Color.white,true);
	
	// create the lantern in BayMax's hand
	Line lanternline = new Line(this.x+(int)(130*this.scale),this.y+(int)(110*this.scale),
			this.x+(int)(175*this.scale),this.y+(int)(110*this.scale));
	
	Line lanternline1 = new Line(this.x+(int)(175*this.scale),this.y+(int)(110*this.scale),
			this.x+(int)(175*this.scale),this.y+(int)(140*this.scale));

	Oval lanterncircle1 = new Oval(this.x+(int)(155*this.scale),this.y+(int)(135*this.scale),
			(int)(40*this.scale),(int)(10*this.scale),Color.black,true);	
	
	Oval lanterncircle2 = new Oval(this.x+(int)(155*this.scale),this.y+(int)(138*this.scale),
			(int)(40*this.scale),(int)(50*this.scale),Color.red,true);

	Oval lanterncircle3 = new Oval(this.x+(int)(155*this.scale),this.y+(int)(183*this.scale),
			(int)(40*this.scale),(int)(10*this.scale),Color.black,true);
	
	Rectangle lanternLine= new Rectangle(this.x+(int)(162*this.scale),this.y+(int)(140*this.scale),
			(int)(8*this.scale),(int)(45*this.scale),Color.black,True);
	
	Rectangle lanternLine1= new Rectangle(this.x+(int)(179*this.scale),this.y+(int)(140*this.scale),
			(int)(8*this.scale),(int)(45*this.scale),Color.black,True);
	
	// add all parts of BayMax to the window
	this.window.add(head);
	this.window.add(body);
	this.window.add(eye);
	this.window.add(eye1);
	this.window.add(eye2);
	this.window.add(hand);
	this.window.add(hand1);
	this.window.add(foot1);
	this.window.add(foot2);
	this.window.add(foot3);
	this.window.add(foot4);
	
	this.window.add(lanternline);
	this.window.add(lanternline1);
	
	this.window.add(lanterncircle2);
	this.window.add(lanterncircle1);
	this.window.add(lanterncircle3);
	
	this.window.add(lanternLine);
	this.window.add(lanternLine1);

}
}
