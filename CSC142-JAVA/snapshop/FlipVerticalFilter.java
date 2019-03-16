/**
 * Filter that flips the image vertically. This class is COMPLETE. Don't
 * change it.
 */
public class FlipVerticalFilter implements Filter {
	public void filter(PixelImage pi) {
		Pixel[][] data = pi.getData();
		
		// take each pixel
		for (int row = 0; row < pi.getHeight()/2; row++) {
			for (int col = 0; col < pi.getWidth(); col++) {
				Pixel temp = data[row][col];
				
				// rearrange pixels
				data[row][col] = data[pi.getHeight() - row - 1][col];
				
				data[pi.getHeight() - row - 1][col] = temp;
			}
		}
		// set data back to graph
		pi.setData(data);
	}
}

