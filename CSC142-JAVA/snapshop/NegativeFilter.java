public class NegativeFilter implements Filter {
	public void filter(PixelImage pi) {
		Pixel[][] data = pi.getData();
		// get each pixel
		for (int row = 0; row < pi.getHeight(); row++) {
			for (int col = 0; col < pi.getWidth(); col++) {
				
				// calculate pixel
				Pixel temp = data[row][col];
				temp.red = 255-temp.red;
				temp.blue = 255-temp.blue;
				temp.green = 255-temp.green;
				
				
			}
		}

		pi.setData(data);
	}
}