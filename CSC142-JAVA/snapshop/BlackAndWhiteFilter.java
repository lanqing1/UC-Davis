/**
 * Filter that flips the image into black and white. This class is COMPLETE. Don't
 * change it.
 */
public class BlackAndWhiteFilter implements Filter {
	public void filter(PixelImage pi) {
		
		// get data from pi
		Pixel[][] data = pi.getData();

		// take each pixel from data[][]
		for (int rowTemp =0; rowTemp < data.length; rowTemp+=1) {
			for (int colTemp =0; colTemp < data[0].length; colTemp+=1) {
				
				// calculate each pixel by adding rgb number together and divided b y3
				Pixel temp =data[rowTemp][colTemp];
				temp.red = (temp.red+temp.blue+temp.green)/3;
				temp.green = (temp.red+temp.blue+temp.green)/3;
				temp.blue = (temp.red+temp.blue+temp.green)/3;
				
				// set pixel back to data
				data[rowTemp][colTemp]=temp;
				
			}
		}
		
		
		

		

		pi.setData(data);
	}
}
