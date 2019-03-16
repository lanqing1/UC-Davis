
/**
 * Filter that flips the image Gaussian4x4. This class is COMPLETE. Don't
 * change it.
 */
public class GaussianFilter5x5 implements Filter {
	public void filter(PixelImage pi) {
		
		// get data from pi
		Pixel[][] data = pi.getData();
		
		// set weighted average
		int scale=249, a=1,b=7,c=16,d=26,e=41;
		
		// take each pixel from 5x5
		for (int rowTemp = 2; rowTemp < data.length-2; rowTemp+=1) {
			for (int colTemp = 2; colTemp < data[0].length-2; colTemp+=1) {
				// pixel in the mid
				Pixel tempMid =data[rowTemp][colTemp];
				
				/* Location of each pixel
				 *   temp1   temp2   temp3   temp4   temp5
				 *   temp6   temp7   temp8   temp9   temp10
				 *   temp11  temp12 tempMid  temp14  temp15
				 *   temp16  temp17  temp18  temp19  temp20
				 *   temp21  temp22  temp12  temp24  temp25
				 * 
				 */
				Pixel temp1 = data[rowTemp-2][colTemp-2];
				Pixel temp2 = data[rowTemp-2][colTemp-1];
				Pixel temp3 = data[rowTemp-2][colTemp];
				Pixel temp4 = data[rowTemp-2][colTemp+1];
				Pixel temp5 = data[rowTemp-2][colTemp+2];
				
				Pixel temp6 = data[rowTemp-1][colTemp-2];
				Pixel temp7 = data[rowTemp-1][colTemp-1];
				Pixel temp8 = data[rowTemp-1][colTemp];
				Pixel temp9 = data[rowTemp-1][colTemp+1];
				Pixel temp10= data[rowTemp-1][colTemp+2];
				
				
				Pixel temp11 = data[rowTemp][colTemp-2];
				Pixel temp12 = data[rowTemp][colTemp-1];				
							
				Pixel temp14 = data[rowTemp][colTemp+1];
				Pixel temp15 = data[rowTemp][colTemp+2];
				
				Pixel temp16 = data[rowTemp-1][colTemp-2];
				Pixel temp17 = data[rowTemp-1][colTemp-1];
				Pixel temp18 = data[rowTemp-1][colTemp];
				Pixel temp19 = data[rowTemp-1][colTemp+1];
				Pixel temp20 = data[rowTemp-1][colTemp+2];
				
				Pixel temp21 = data[rowTemp-2][colTemp-2];
				Pixel temp22 = data[rowTemp-2][colTemp-1];
				Pixel temp23 = data[rowTemp-2][colTemp];
				Pixel temp24 = data[rowTemp-2][colTemp+1];
				Pixel temp25 = data[rowTemp-2][colTemp+2];
				
			
				// use neighbor pixel to calculate the central pixel
				// change red color
				tempMid.red= (temp1.red*a+temp2.red*a+temp3.red*c+temp4.red*a+temp5.red*a+temp6.red*a+temp7.red*b
						
						+temp8.red*d+temp9.red*b+temp10.red*a+temp11.red*c+temp12.red*d+tempMid.red*e+temp14.red*d
						+temp15.red*c+temp16.red*a+temp17.red*b+temp18.red*d+temp19.red*b+temp20.red*a+temp21.red*a+
						temp22.red*a+temp23.red*c+temp24.red*a+temp25.red*a)/scale;
				//change blue color
				tempMid.blue= (temp1.blue*a+temp2.blue*a+temp3.blue*c+temp4.blue*a+temp5.blue*a+temp6.blue*a+temp7.blue*b						
						+temp8.blue*d+temp9.blue*b+temp10.blue*a+temp11.blue*c+temp12.blue*d+tempMid.blue*e+temp14.blue*d
						+temp15.blue*c+temp16.blue*a+temp17.blue*b+temp18.blue*d+temp19.blue*b+temp20.blue*a+temp21.blue*a+
						temp22.blue*a+temp23.blue*c+temp24.blue*a+temp25.blue*a)/scale;
				//change green color
				tempMid.green= (temp1.green*a+temp2.green*a+temp3.green*c+temp4.green*a+temp5.green*a+temp6.green*a+temp7.green*b						
						+temp8.green*d+temp9.green*b+temp10.green*a+temp11.green*c+temp12.green*d+tempMid.green*e+temp14.green*d
						+temp15.green*c+temp16.green*a+temp17.green*b+temp18.green*d+temp19.green*b+temp20.green*a+temp21.green*a+
						temp22.green*a+temp23.green*c+temp24.green*a+temp25.green*a)/scale;
				
				// set tempMid back to data[][]
				data[rowTemp][colTemp]=tempMid;
				
			}
			
		}
					
		
		// set data to pi
		pi.setData(data);
	}
}
