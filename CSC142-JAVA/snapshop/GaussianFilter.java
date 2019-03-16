/**
 * Filter that flips the image Gaussian 5x5. This class is COMPLETE. Don't
 * change it.
 */
public class GaussianFilter implements Filter {
	public void filter(PixelImage pi) {
		
		// get data from pi
		Pixel[][] data = pi.getData();
		int scale=16;
	
		// call calculateWeightAverage to calculate the pixel and return data
		data =pi.calculateWeightedAverage("Gaussian",1,2,4,scale);
				
				
					
		
		// set data to pi
		pi.setData(data);
	}
}