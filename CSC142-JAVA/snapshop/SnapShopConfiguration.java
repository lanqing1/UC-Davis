// Write your short report here (-2 if there is no report)

/**
 * A class to configure the SnapShop application
 * 
 * @author LanQing Cheng 
 * @version 03/13/2016
 *  
 * 1. I wrote Black&White filter,Median filter, Gaussian5x5filter, and Posterization filter filer
 * 	  besides the requirement.
 *
 * 2. Edgy and Laplacian doesn't really work. Maybe somethong wrong with my code. Since Gaussian 
 * 	  and Unsharp Masking share the same method with Edgy and Laplacian, and Gaussian and Unsharp
 * 	  Mashing run pretty good, I have no idea where I was wrong. Excepts Edgy and Laplacian, the rest 
 * 	  filters works.
 * 
 * 3. Surprises: This programming assignment surprise me that the we can implement any effect by 
 * 				 calculating each pixel in the graph. That show the relationship between original
 * 				 pixel and its neighboring pixels. I searched online for more effects that can be
 * 				 calculated to modify original graph and it's not easy to come up with a method that
 * 				 is just perfect for my situation. So I tried to do a filter by mysfle according to
 * 				 some basic principle of the effect, and I adjust the extra filters many time to get
 * 			     a satisfied one. It's really fun when I change the rgb color in each pixel and get 
 * 				 a extremely different graph.
 * 
 *     Problem:  Sometimes it's hard to change the graph to the way I expect because I am not familiar
 *     			 enough with the way rgb color works. And I only adjust the filter based on a certain 
 *     			 picture, so it's a problem that if the filter fits other pictures. 
 *      
 */


public class SnapShopConfiguration {
	/**
	 * Method to configure the SnapShop. Call methods like addFilter and
	 * setDefaultFilename here.
	 * 
	 * @param theShop
	 *            A pointer to the application
	 */
	public static void configure(SnapShop theShop) {

		theShop.setDefaultFilename("C:/Users/Toshiba/Desktop/seattle.jpg");
		theShop.addFilter(new FlipHorizontalFilter(), "Flip Horizontal");
		// add your other filters below
		theShop.addFilter(new FlipVerticalFilter(),"Flip Vertical");
		theShop.addFilter(new NegativeFilter(), "Negative");
		theShop.addFilter(new GaussianFilter(),"Gaussian");
		theShop.addFilter(new GaussianFilter(),"Gaussian 5x5");
		theShop.addFilter(new LaplacianFilter(),"Laplacian");
		theShop.addFilter(new UnsharpMaskingFilter(),"Unsharp masking");
		theShop.addFilter(new EdgyFilter(),"Edgy");
		theShop.addFilter(new BlackAndWhiteFilter(),"Black-White");
		theShop.addFilter(new MedianFilter(),"Median");
		theShop.addFilter(new PosterizationFilter(),"Yello-Posterization");
		
		
		
	}
}
