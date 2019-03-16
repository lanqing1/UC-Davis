
public class EdgyFilter implements Filter {
	public void filter(PixelImage pi) {
	
		// get data from pi
		Pixel[][] data= pi.getData();
		
		//set scale to 1 so that it doesn't affect result
		int scale=1;
	
		
		// call calculateWeightAverage to calculate the pixel and return data			
		data =pi.calculateWeightedAverage("Edgy",-1,-1,9,scale);
				
				
	
		// set data to pi
		pi.setData(data);
	}
}

