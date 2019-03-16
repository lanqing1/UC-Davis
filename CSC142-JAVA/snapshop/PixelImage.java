import java.awt.image.*;

/**
 * Provides an interface to a picture as an array of Pixels
 */
public class PixelImage {
	private BufferedImage myImage;
	private int width;
	private int height;

	/**
	 * Map this PixelImage to a real image
	 * 
	 * @param bi
	 *            The image
	 */
	public PixelImage(BufferedImage bi) {
		// initialise instance variables
		this.myImage = bi;
		this.width = bi.getWidth();
		this.height = bi.getHeight();
	}

	/**
	 * Return the width of the image
	 */
	public int getWidth() {
		return this.width;
	}

	/**
	 * Return the height of the image
	 */
	public int getHeight() {
		return this.height;
	}

	/**
	 * Return the BufferedImage of this PixelImage
	 */
	public BufferedImage getImage() {
		return this.myImage;
	}

	/**
	 * Return the image's pixel data as an array of Pixels. The first coordinate
	 * is the x-coordinate, so the size of the array is [width][height], where
	 * width and height are the dimensions of the array
	 * 
	 * @return The array of pixels
	 */
	public Pixel[][] getData() {
		Raster r = this.myImage.getRaster();
		Pixel[][] data = new Pixel[r.getHeight()][r.getWidth()];
		int[] samples = new int[3];

		for (int row = 0; row < r.getHeight(); row++) {
			for (int col = 0; col < r.getWidth(); col++) {
				samples = r.getPixel(col, row, samples);
				Pixel newPixel = new Pixel(samples[0], samples[1], samples[2]);
				data[row][col] = newPixel;
				
			}
		}
		
		return data;
	}

	/**
	 * Set the image's pixel data from an array. This array matches that
	 * returned by getData(). It is an error to pass in an array that does not
	 * match the image's dimensions or that has pixels with invalid values (not
	 * 0-255)
	 * 
	 * @param data
	 *            The array to pull from
	 */
	public void setData(Pixel[][] data) {
		int[] pixelValues = new int[3]; // a temporary array to hold r,g,b
										// values
		WritableRaster wr = this.myImage.getRaster();

		if (data.length != wr.getHeight()) {
			throw new IllegalArgumentException("Array size does not match");
		} else if (data[0].length != wr.getWidth()) {
			throw new IllegalArgumentException("Array size does not match");
		}

		for (int row = 0; row < wr.getHeight(); row++) {
			for (int col = 0; col < wr.getWidth(); col++) {
				pixelValues[0] = data[row][col].red;
				pixelValues[1] = data[row][col].green;
				pixelValues[2] = data[row][col].blue;
				wr.setPixel(col, row, pixelValues);
			}
		}
	}

	// add a method to compute a new image given weighted averages
	public Pixel[][] calculateWeightedAverage(String nameOfMethod,int a,int b,int c,int scale){
		Pixel[][] data=this.getData();
		Pixel[][] finaldata=this.getData();
		
		// take each pixelb from data
		for (int rowTemp = 1; rowTemp < data.length-1; rowTemp+=1) {
			for (int colTemp = 1; colTemp < data[0].length-1; colTemp+=1) {
				// get pixel from 3x3
				Pixel tempMid =data[rowTemp][colTemp];
				
				Pixel temp2 = data[rowTemp-1][colTemp];
				Pixel temp1 = data[rowTemp-1][colTemp-1];
				Pixel temp3 = data[rowTemp-1][colTemp+1];
				
				Pixel temp4 = data[rowTemp][colTemp-1];
				Pixel temp6 = data[rowTemp][colTemp+1];
				Pixel temp7 = data[rowTemp+1][colTemp-1];
				Pixel temp8 = data[rowTemp+1][colTemp];
				Pixel temp9 = data[rowTemp+1][colTemp+1];
				// calculate pixel for Gaussian
				if (nameOfMethod=="Gaussian"){
					
					tempMid = AverageWeighted(temp1,temp2,temp3,temp4,temp9,
							temp6,temp7,temp8,tempMid, a, b, c,scale);
				
				}
				// calculate pixel for Laplacian
				else if (nameOfMethod=="Laplacian"){

					tempMid = AverageWeighted(temp1,temp2,temp3,temp4,temp9,
							temp6,temp7,temp8,tempMid, a, b, c,scale);
					
					tempMid= checkPixels( colTemp, colTemp, tempMid);
				
				}
				
				// calculate pixel for UnsharpMasking
				else if (nameOfMethod=="UnsharpMasking"){
					
				
					tempMid = AverageWeighted(temp1,temp2,temp3,temp4,temp9,
							temp6,temp7,temp8,tempMid,a,b,c,scale);
				
					
					tempMid= checkPixels( colTemp, colTemp, tempMid);					
				}
				
				// calculate pixel for Edgy
				else if (nameOfMethod=="Edgy"){
					tempMid = AverageWeighted(temp1,temp2,temp3,temp4,temp9,
							temp6,temp7,temp8,tempMid,a,b,c,scale);
					
					tempMid= checkPixels( colTemp, colTemp, tempMid);				
				}
		
				// use finaldata[][] to carry tempMid
				finaldata[rowTemp][colTemp] = tempMid;		
			}
		}
		
		return (Pixel[][]) finaldata;
		
	}
	
	// actual calculate method to get average  weighted number
	private Pixel AverageWeighted(Pixel temp1, Pixel temp2, Pixel temp3, 
			Pixel temp4, Pixel temp9, Pixel temp6, Pixel temp7,	Pixel temp8,
			Pixel tempMid, int a, int b, int c, int scale) {	
		
		// get red color
		tempMid.red= ((temp1.red*a+temp3.red*a+temp2.red*b+temp4.red*b+
				temp6.red*b+temp7.red*a+temp8.red*b+temp9.red*a+tempMid.red*c)/scale);
		// get blue color
		tempMid.blue= ((temp1.blue*a+temp3.blue*a+temp2.blue*b+temp4.blue*b+
				temp6.blue*b+temp7.blue*a+temp8.blue*b+temp9.blue*a+tempMid.blue*c)/scale);
		// get green color
		tempMid.green= ((temp1.green*a+temp3.green*a+temp2.green*b+temp4.green*b+
				temp6.green*b+temp7.green*a+temp8.green*b+temp9.green*a+tempMid.green*c)/scale);
		
		return tempMid;
	}
	
	// check pixel that if its >255 or <0
	private Pixel checkPixels(int row, int col,Pixel tempMid) {
		
		if (tempMid.red <0 ){
			tempMid.red=0;
			
		}
		if (tempMid.blue <0 ){
			tempMid.blue=0;
			
		}
		if (tempMid.green <0 ){
			tempMid.green=0;
			
		}
		if (tempMid.red>255 ){
			tempMid.red=255;
			
		}
		if (tempMid.blue >255 ){
			tempMid.blue=255;
			
		}
		if (tempMid.green >255){
			tempMid.green=255;
			
		}
		
		// return tempMid
		return tempMid;
		
		
	}
	
	}
	

