import java.awt.Color;
import java.awt.Container;
import java.awt.Point;
import java.util.*;

import uwcse.io.*;
import uwcse.graphics.*;

import javax.swing.*;

/**
 * A class to create and manipulate graphics elements stored in an ArrayList
 */

public class GraphicsElements {

	/** Maximum number of disks in a pile of disks */
	public static final int MAXIMUM_NUMBER_OF_DISKS = 100;

	/** Maximum number of rows (or columns) in a square checkered board */
	public static final int MAXIMUM_NUMBER_OF_ROWS = 50;

	/** Maximum number of points in a Sierpinski triangle */
	public static final int MAXIMUM_NUMBER_OF_POINTS = 10000;

	/** Width of the window (from ViewWindow) */
	public static final int WIDTH = ViewWindow.WINDOW_WIDTH;

	/** Height of the window (from ViewWindow) */
	public static final int HEIGHT = ViewWindow.WINDOW_HEIGHT;

	//instance fields
	public Input input;
	public int numOfDisk;
	public ArrayList<Oval> diskList;


	public Color myColor1, myColor2;
	public Random random;

	public int numOfColums;

	public ArrayList<Rectangle> boardList;

	public ArrayList<Oval> tirgList;

	public int numOfPoint;

	public ArrayList<Color>  boardColorList;

	/**
	 * Create a top view of a pile of disks of decreasing diameters (from bottom
	 * to top). Use filled circles. The color of each disk is random. The pile
	 * should fill the window. <br>
	 * Store the circles in an ArrayList and return that ArrayList (the disk at
	 * the bottom should be the first element of the ArrayList)<br>
	 * The number of disks is given by the user (use a dialog box). If that
	 * number is less than or equal to 0 or greater than
	 * MAXIMUM_NUMBER_OF_DISKS, display an error message (use
	 * JOptionPane.showMessageDialog)and ask for it again.
	 */
	public ArrayList<Oval> createAPileOfDisks() {
		// create input and diskList
		Input input = new Input();
		this.diskList = new ArrayList<Oval>();
				
		//Use input to get number of disks from the user
		numOfDisk = input.readIntDialog("Enter the number of disks:");
		
		//Use while to control the input
		while (numOfDisk < 1 || numOfDisk > MAXIMUM_NUMBER_OF_DISKS) {
			JOptionPane
					.showMessageDialog(null, "Wrong input, please try again");
			numOfDisk = input.readIntDialog("Enter the number of disks:");
		}
		//Use loop to create and put circles into diskList
		for (int i = 0; i < numOfDisk; i++) {
			random = new Random();
			
			//Use random to create color
			int rgb1 = random.nextInt(256);
			int rgb2 = random.nextInt(256);
			int rgb3 = random.nextInt(256);
			
			//Create color
			myColor1 = new Color(rgb1, rgb2, rgb3);
			
			//Create ovals
			Oval circle = new Oval((int) (15 / (numOfDisk * 0.1) * i) + 50,
					(int) (15 / (numOfDisk * 0.1) * i), (int) (HEIGHT - 30
							/ (numOfDisk * 0.1) * i), (int) (HEIGHT - 30
							/ (numOfDisk * 0.1) * i), myColor1, true);
			
			// add circle into list
			this.diskList.add(circle);

		}
		// return list
		return this.diskList;

	}

	/**
	 * Create a square checkered board. Create a Rectangle for each square on
	 * the board. Store the Rectangles in an ArrayList and return that
	 * ArrayList. Use two colors only to paint the squares.<br>
	 * The board should cover most of the window. The number of rows (=number of
	 * columns) is given by the user (use a dialog box). If that number is less
	 * than or equal to 0 or greater than MAXIMUM_NUMBER_OF_ROWS, display an
	 * error message (use JOptionPane.showMessageDialog)and ask for it again.
	 */
	public ArrayList<Rectangle> createACheckeredBoard() {
		Rectangle b;
		Input input = new Input();
		
		// get number of columns from user
		numOfColums = input.readIntDialog("Enter the number of columns:");
		// control the number of input
		while (numOfColums < 1 || numOfColums > MAXIMUM_NUMBER_OF_ROWS) {
			JOptionPane
					.showMessageDialog(null, "Wrong input, please try again");
			numOfColums = input.readIntDialog("Enter the number of columns:");
		}
		// create lists
		this.boardList = new ArrayList<Rectangle>();
		this.boardColorList = new ArrayList<Color>();
		random = new Random();
		
		// use random to set color
		int rgb1 = random.nextInt(255);
		int rgb2 = random.nextInt(255);
		int rgb3 = random.nextInt(255);
		
		Color myColor2_1 = new Color(rgb1, rgb2, rgb3);
		Color myColor2_2 = new Color(rgb2, rgb1, rgb3);
		
		// add color to lists
		boardColorList.add(myColor2_1);
		boardColorList.add(myColor2_2);

		//use for loop to set color for each Rectangle
		for (int i = 0; i < numOfColums; i++) {

			for (int k = 0; k < numOfColums; k++) {
				if (i % 2 == 0 && k % 2 == 0 || i % 2 != 0 && k % 2 != 0) {

					myColor2 = myColor2_1;
				} else
					myColor2 = myColor2_2;
				
				// create the rectangles
				b = new Rectangle((WIDTH - HEIGHT) / 2 + HEIGHT / numOfColums
						* k, (HEIGHT % numOfColums + 10) / 2 + HEIGHT
						/ numOfColums * i, (int) HEIGHT / numOfColums,
						(int) HEIGHT / numOfColums, myColor2, true);
				// add rectangles into list
				boardList.add(b);
				
			}
		}
		//return the list
		return boardList;
		

	}

	/**
	 * Create a Sierpinski triangle. Create a filled Oval (circle of radius 1)
	 * for each point of the triangle. Store the Ovals in an ArrayList and
	 * return that ArrayList. Use one color only to paint the Ovals.<br>
	 * The triangle should cover most of the window.<br>
	 * The number of points is given by the user (use a dialog box). If that
	 * number is less than or equal to 0 or greater than
	 * MAXIMUM_NUMBER_OF_POINTS, display an error message (use
	 * JOptionPane.showMessageDialog)and ask for it again.
	 */
	public ArrayList<Oval> createASierpinskiTriangle() {
		
		Input input = new Input();
		random = new Random();
		this.tirgList = new ArrayList<Oval>();
		
		// get the number of points
		numOfPoint = input.readIntDialog("Enter the points:");
		
		//use while to control input
		while (numOfPoint < 1 || numOfPoint > MAXIMUM_NUMBER_OF_POINTS) {
			JOptionPane
					.showMessageDialog(null, "Wrong input, please try again");
			numOfPoint = input.readIntDialog("Enter the number of points:");
		}
		// set the location for each points
		Point P1 = new Point(WIDTH / 2, 0);
		Point P2 = new Point(0, HEIGHT);
		Point P3 = new Point(WIDTH, HEIGHT);
		Point P = new Point(WIDTH / 2, 0);
		Point Q = new Point();
		Point[] corners = { P1, P2, P3 };

		for (int i = 0; i < numOfPoint; i++) {
			int num = random.nextInt(3);
			
			//calculate the point ofQ
			Q.x = (corners[num].x + P.x) / 2;
			Q.y = (corners[num].y + P.y) / 2;
			
			// add oval into list
			this.tirgList.add(new Oval(Q.x, Q.y, 2, 2, Color.blue, true));
			P = Q;
		}
		
		return this.tirgList;

	}

	/**
	 * Rotate the colors in the pile of disks. Set the color of each disk to the
	 * color of the disk just above it. For the top disk, set its color to the
	 * color of the bottom disk (e.g. with 3 disks, if the colors are from
	 * bottom to top, red, blue, yellow, the new colors of the disks are from
	 * bottom to top, blue, yellow, red).<br>
	 * Precondition: graphicsList describes a pile of disks
	 */
	public ArrayList<Oval> rotateColorsInPileOfDisks(ArrayList<Oval> graphicsList) {
		// create local variables 
		ArrayList<Oval> tempList = new ArrayList<Oval>();
		ArrayList<Color> tempColorList = new ArrayList<Color>();
		Oval tempOval;

		//precondition to check if graphicsList describes a pile of disks
		if (graphicsList == diskList){
			// get color from getColorInPileOfDisks
			for (int i = 0; i < this.numOfDisk; i++) {
				tempOval = graphicsList.get(i);
				//get color
				tempColorList.add(tempOval.getColor());
			}
			
			// set color to oval 
			for (int i = 0; i < this.numOfDisk; i++) {
				// get oval and set color
				tempOval = (Oval) graphicsList.get(i);
				if (i-1>=0)
					tempOval.setColor((Color) tempColorList.get(i-1));
				else
					tempOval.setColor((Color) tempColorList.get(this.numOfDisk-1));

				tempList.add(tempOval);
			}
			
		}
			
		// return a new oval list
		this.diskList = tempList;
		return tempList;

	}

	/**
	 * Flip the 2 colors of the checkboard<br>
	 * Precondition: graphicsList describes a checkered board
	 */
	public ArrayList<Rectangle> flipColorsInCheckeredBoard(ArrayList<?> graphicsList) {
		
		Color tempColora;
		ArrayList<Rectangle> newRectList = new ArrayList<Rectangle>();
		Rectangle tempRect = null;
		
		// precondition to check the graphicsList
		if (graphicsList==  boardList){
			
			// use for loop to change color
			for (int i = 0; i < numOfColums * numOfColums; i++) {
				tempRect = (Rectangle) graphicsList.get(i);
				tempColora = getColorInCheckeredBoard(i, 0, graphicsList);

				if (tempColora == (Color) boardColorList.get(0))
					// set new color to rectangle
					tempRect.setColor(boardColorList.get(1));

				if (tempColora == (Color) boardColorList.get(1))
					tempRect.setColor(boardColorList.get(0));
				// add rectangles to new list
				newRectList.add(tempRect);// add to list
			
		}
		
		}
		// return new list
		this.boardList = newRectList;

		return newRectList;
	}

	/**
	 * Change the color of the Sierpinski triangle (all circles should change to
	 * the same color). Switch between 3 colors (e.g. blue->red->green, if the
	 * triangle was blue, make it red, if it was red, make it green, if it was
	 * green make it blue).<br>
	 * Precondition: graphicsList describes a Sierpinski triangle
	 */
	public ArrayList<Oval> changeColorsInSierpinskiTriangle(ArrayList<?> graphicsList) {
		
		//create variables
		ArrayList<Oval> newTriList = new ArrayList<Oval>();
		Oval ovalTemp;
		Color a=null;
		
		// get oval from tirgList
		ovalTemp=(Oval) graphicsList.get(1);
		
		// get color from oval
		a=(Color)ovalTemp.getColor();
	
		
		// precondition
		if (graphicsList==this.tirgList){
			
			
			// if color is blue change to red
			if (a == Color.blue) {
				for (int i = 0; i < graphicsList.size(); i++) {
					
					// get oval from list and set color 
					Oval temp = (Oval) graphicsList.get(i);
					temp.setColor(Color.red);
										
					//put oval into new list
					newTriList.add(temp);
					
				}
				
			}
			else if (tirgList.get(1).getColor()  == Color.red) {
				
				// change every Oval to green
				for (int i = 0; i < tirgList.size(); i++) {
					Oval temp = (Oval) tirgList.get(i);
					temp.setColor(Color.green);
					newTriList.add(temp);
				}

			} else if (tirgList.get(1).getColor() ==Color.green){
				
				// change to blue
				for (int i = 0; i < tirgList.size(); i++) {
					Oval temp = (Oval) tirgList.get(i);
					temp.setColor(Color.blue);
					
					newTriList.add(temp);
				}
			}
			
		}
		// update tirgList
		this.tirgList=newTriList;
		return newTriList;
		

	}

	/**
	 * Return the color at location (x,y) in the pile of disks. If (x,y) is not
	 * part of the pile of disks, return null.<br>
	 * Precondition: graphicsList describes a pile of disks
	 */
	public Color getColorInPileOfDisks(int x, int y, ArrayList<?> graphicsList) {
		
		// return null since we doesn't need this method
		return null;
	}

	/**
	 * Return the color at location (x,y) in the checkered board. If (x,y) is
	 * not part of the board, return null.<br>
	 * Precondition: graphicsList describes a checkered board
	 */
	public Color getColorInCheckeredBoard(int x, int y, ArrayList<?> graphicsList) {
		// initialize tempColor to null
		Color tempColor =null;
		
		// precondition
		if(graphicsList==this.boardList){ 
			
			//get color from graphicsList
			if (x >= 0 && x < graphicsList.size() && y == 0) {
				Rectangle temp = (Rectangle) graphicsList.get(x);
				tempColor = temp.getColor();
			}
		}
		
		//return color
		return tempColor;
	}

	/**
	 * Return the color at location (x,y) in the Sierpinski triangle. If (x,y)
	 * is not part of the pile of disks, return null.<br>
	 * Precondition: graphicsList describes a Sierpinski triangle
	 */
	public Color getColorInSierpinskiTriangle(int x, int y,	ArrayList<Oval> graphicsList) {
		
		return null;
	}
}
