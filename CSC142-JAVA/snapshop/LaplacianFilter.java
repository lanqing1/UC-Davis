/**
 * Filter that flips the image Laplacian. This class is COMPLETE. Don't
 * change it.
 */
public class LaplacianFilter implements Filter {
	public void filter(PixelImage pi) {
		
		// get data from pi
		Pixel[][] data = pi.getData();
		
		//set scale to 1 so that it doesn't affect result
		int scale=1;

		
		
		
		// call calculateWeightAverage to calculate the pixel and return data		
		data =pi.calculateWeightedAverage("Laplacian",-1,-1,8,scale);
		

		pi.setData(data);
	}
}


