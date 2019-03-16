
public class UnsharpMaskingFilter implements Filter {
	public void filter(PixelImage pi) {

		// get data from pi
		Pixel[][] data= pi.getData();
		int scale=16;
	
		// call calculateWeightAverage to calculate the pixel and return data		
		data =pi.calculateWeightedAverage("UnsharpMasking",-1,-2,28,scale);
				
	
		// set data to pi
		pi.setData(data);
	}
}

