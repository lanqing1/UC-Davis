import java.util.Arrays;

/**
 * Filter that change every pixel to its minimum number of each color. This class is COMPLETE. Don't
 * change it.
 */
public class MedianFilter implements Filter {
	public void filter(PixelImage pi) {
		
		
		// get data from pi
		Pixel[][] data = pi.getData();

		
		// use loop to edit every pixel
		for (int rowTemp = 1; rowTemp < data.length-1; rowTemp+=1) {
			for (int colTemp = 1; colTemp < data[0].length-1; colTemp+=1) {
				
				// get every pixel in 3x3
				Pixel tempMid =data[rowTemp][colTemp];				
				Pixel temp2 = data[rowTemp-1][colTemp];
				Pixel temp1 = data[rowTemp-1][colTemp-1];
				Pixel temp3 = data[rowTemp-1][colTemp+1];
				Pixel temp4 = data[rowTemp][colTemp-1];
				Pixel temp6 = data[rowTemp][colTemp+1];
				Pixel temp7 = data[rowTemp+1][colTemp-1];
				Pixel temp8 = data[rowTemp+1][colTemp];
				Pixel temp9 = data[rowTemp+1][colTemp+1];
		
				// put red color in redList
				int[] redList = {temp1.red,temp2.red,temp3.red,
						temp4.red,temp7.red,temp6.red,temp8.red,temp9.red,tempMid.red};
				
				// arrange list in the order that int is from small to big
				Arrays.sort(redList);
				
				// take the middle int			
				tempMid.red= redList[4];
				
				// put blue color in redList		
				int[] blueList = {temp1.blue,temp2.blue,temp3.blue,
						temp4.blue,	temp7.blue,temp6.blue,temp8.blue,temp9.blue,tempMid.blue};
				Arrays.sort(blueList);
				// get the middle int
				tempMid.blue= blueList[4];
			
				// put green color in redList
				int[] greenList = {temp1.green,temp2.green,temp3.green
						,temp4.green,temp7.green,temp6.green,temp8.green,temp9.green,tempMid.green};
				
			
				Arrays.sort(greenList);
				
				tempMid.green= greenList[4];
				 
				
				data[rowTemp][colTemp]=tempMid;

			}		
		}
	

		pi.setData(data);
	}
}
