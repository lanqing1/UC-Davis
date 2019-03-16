import java.util.Arrays;

/**
 * Filter that change every pixel to its minimum number of each color. This class is COMPLETE. Don't
 * change it.
 */
public class PosterizationFilter implements Filter {
	public void filter(PixelImage pi) {
		
		
		// get data from pi
		Pixel[][] data = pi.getData();

		
		// use loop to edit every pixel
		for (int rowTemp =0; rowTemp < data.length; rowTemp+=1) {
			for (int colTemp =0; colTemp < data[0].length; colTemp+=1) {
				
				// set whole color in photo into 2*3=6 colors
				Pixel temp =data[rowTemp][colTemp];
				if(temp.red>30 &&temp.red<200)
					temp.red=200;
				else
					temp.red=255;
				if(temp.blue>10 &&temp.blue<170)
					temp.blue=100;
				else
					temp.blue=205;
				
				if(temp.green>40 &&temp.green<160)
					temp.green=200;
				else
					temp.green=220;
				// set pixel back to data[][]
				data[rowTemp][colTemp]=temp;
				
			}
		}
		

		pi.setData(data);
	}

	
}
