import uwcse.io.Input; // use the Input class

import java.text.DecimalFormat; // to format the output

/**
 * Homework 3 <br>
 * Simulating a purchase in Canada paid in US dollars
 * 
 * @LanQing Cheng <your name here>
 */

public class CanadianGiftShop {

	// Constants
	/** Exchange rate 1 US dollar = RATE Canadian dollar */
	public final double RATE = 1.16;

	/** Price of a jar of maple syrup in Canadian dollars before taxes */
	public final double JAR_PRICE = 5.95;

	/**
	 * Price of photograph of the city of Victoria in Canadian dollars before
	 * taxes
	 */
	public final double PHOTO_PRICE = 7.65;

	/** Price of a beaver hat in Canadian dollars before taxes */
	public final double HAT_PRICE = 16.35;

	/** Maximum allowed number of purchased items for each item */
	public final int MAX_ITEM = 100;

	/** Tax rate */
	public final double TAX_RATE = 0.093;
	

	// instance variables
	// number of purchased jars of maple syrup
	private int jarNumber;

	// number of purchased photographs of the city of Victoria
	private int photoNumber;

	// number of purchased beaver hats
	private int hatNumber;
	
	// number of US,CA currency, totalMoney, Money before Tax, changes
	private double payUS,costCA,totalMoney,sumMoney;

	// 2 digits after the decimal point for doubles
	private DecimalFormat twoDigits = new DecimalFormat("0.00");
	
	// changes in CA currency
	private double changeinCAD;
	
	// two decimal in the changes
	private double twoDecimal;



	/**
	 * Takes and processes the order from the customer
	 */
	public void takeAndProcessOrder() {
		// Here is a possible series of steps: call some other (private)
		// methods to do each step.

		// Display the items and their prices
		itemList();
					
		// Get the Customer's order
		input();
		
		// print out the money customer paid and transfer to CA $
		System.out.println("You gave $"+twoDigits.format(this.payUS) +" US,which is $"+twoDigits.format(this.costCA)+" CA\n");
		
		// separate mark
		System.out.print("--------------------------------------------");
		
		// show receipt
		showReceipt();
		
		// get changes for customer
		changeinCAD(this.costCA,this.totalMoney);
		
		}
		


	/**
	 * Displays the items for sale and their prices in Canadian dollars
	 */
	private void itemList() {	
		// print out the name of the shop
		System.out.println("       Welcome to Canadian Wonders");
		
		// separate the shop's name and products
		System.out.println("       ***************************");
		
		// introduce the products
		System.out.println("\nHere is a price list of our most popular products (in Canadian dollars)");
		
		// print the stuff and prices
		System.out.println("\nJar of Maple Syrup: $"+JAR_PRICE);
		System.out.println("Photograph of Victoria: $"+PHOTO_PRICE);
		System.out.println("Beaver Hat: $"+HAT_PRICE);
		
		// indicates the tax
		System.out.println("\nThe above prices don't include taxes. The tax rate is 9.30%");
		// indicates the rate for exchanging 
		System.out.println("Our exchange rate is 1 US dollar =1.16 Canadian dollars\n");
		
	}

	/**
	 * Gets the customer's order Precondition: none Postcondition: jarNumber,
	 * photoNumber and hatNumber are initialized to a value between 0 and
	 * MAX_ITEM
	 */
	private void input() {
		
		Input input = new Input();
		
		// get the jarNumber from input
		this.jarNumber = input.readInt("How many jars of maple syrup would you like(no more than 100)?");
		
		// check if the number of item is available
		this.jarNumber = checkNumberOfItem(jarNumber);
		
		// get the photoNumber from input
		this.photoNumber = input.readInt("How many photographs of Victoria would you like(no more than 100)?");
		
		// check if the number of item is available
		this.photoNumber = checkNumberOfItem(photoNumber);
		
		// get the hatNumber from input
		this.hatNumber = input.readInt("How many beaver hats would you like(no more than 100)?");
		
		// check if the number of item is available
		this.hatNumber = checkNumberOfItem(hatNumber);
		
		// do calculation to get sub-total and total in CA$		
		doCalculate();
		
		// get the money that customer paid
		this.payUS = input.readDouble("\n"+"Please, enter the US dollar amount to pay for it:");
		
		// change money to CA currency
		this.costCA =this.payUS* RATE;
		
		// call method checkMoney() to check if money is enough
		checkMoney();
		
		
				

	}

	
	/**
	 * Calculate the sub-total and total for the purchase, and print out
	 */

	private void doCalculate() {
		
		// calculate the sumMoney and total Money of the purchase
		this.sumMoney = this.jarNumber*JAR_PRICE+this.photoNumber*PHOTO_PRICE+this.hatNumber*HAT_PRICE;
		
		// calculate the total money after tax	
		this.totalMoney = this.sumMoney*(1+TAX_RATE);
		
		
		//print out the total purchase in US  and CA currency 
		System.out.printf("\nYour purchase total is $ "+twoDigits.format(this.totalMoney)+" CA (tax included)");
		
		
	}
	
	/**
	 * Check if the money is enough for purchase
	 * if not, say so and exit the program
	 */
	private void checkMoney() {
		
		// Check if the money is enough
		if(this.totalMoney > this.costCA){
			
			// if not, print so and exit the program
			System.out.println("Sorry, this is not enough!");
			System.exit(0);
		}
		
	}
	
	/**
	 * Check if the number of item is from 0 to 100
	 * if not, initialize the item number to 0
	 */
	private int checkNumberOfItem(int numOfItem) {
		
		// use boolean to check the number of items
		if(numOfItem>MAX_ITEM){
			
			// if the number of item is lager than 100, print out error
			System.out.println("Sorry, we don't have that many in stock (count as 0)");
			
			// count the number as 0
			numOfItem = 0;
		}
			
		if(numOfItem <0){
			
			// if the number of item is less than 0, print out error
			System.out.println(numOfItem+" is not a valid number of items!");
			
			// count the number as 0
			numOfItem = 0;
		}	
		return numOfItem;		
	}
	
	/**
	 * show the receipt: print out the orders, price, sub-total,tax and total
	 */
	private void showReceipt() {
		// show receipt to customer
		System.out.println("\n                 Receipt");
		System.out.println("item                  quantity    price");
		
		// print out the items, quantities and price
		// display the stuff only if customer ordered
		if (this.jarNumber>0)
			System.out.println("Jar of Maple Syrup       "+this.jarNumber  +"     "+"CA $"+twoDigits.format(this.jarNumber*JAR_PRICE));
		
		// check the number of photo that customer ordered
		if (this.photoNumber>0)
			System.out.println("Photograph of Victoria   "+this.photoNumber+"     "+"CA $"+twoDigits.format(this.photoNumber*PHOTO_PRICE));
		
		// check the number of hat that customer ordered
		if (this.hatNumber>0)
			System.out.println("Beaver Hat               "+this.hatNumber  +"     "+"CA $"+twoDigits.format(this.hatNumber*HAT_PRICE));
		
		// separate the items and conclusion
		System.out.print("--------------------------------------------");
		
		// print out the sub-total
		System.out.println("\nsubtotal:  CA $"+twoDigits.format(this.sumMoney));
		
		//print out the tax
		System.out.println("Tax:       CA $"+twoDigits.format(this.sumMoney*TAX_RATE));
		
		//print out the tota money 
		System.out.println("Total:     CA¡¡$"+twoDigits.format(this.totalMoney));
		
		
	}

	/**
	 * Given a purchase and a payment in canadian dollars ,
	 * displays the change amount in canadian dollars and cents
	 * 
	 * @costCA
	 *            payment in CA dollars
	 * @totalMoney
	 *           purchase amount in Canadia dollars
	 */
	private void changeinCAD(double costCA, double totalMoney) {

		// calculate the change in CA$
		this.changeinCAD = costCA-totalMoney;
		
		//separate mark		
		System.out.print("--------------------------------------------\n");
		
		// display the changes
		System.out.print("\nHere is your change: $"+twoDigits.format(this.changeinCAD) +" CA\n");
		
		// calculate the number of 20$ bill		
		if((int)changeinCAD/20 >0)
			System.out.print((int)changeinCAD/20+" $20 bil");
		
		// check if the noun is proper
		checkNoun((int)changeinCAD/20 );
		
		// calculate the number of 10$ bill		
		if((int)changeinCAD%20/10 >0)
			System.out.print((int)changeinCAD%20/10+" $10 bill");
		
		// check if the noun is proper
		checkNoun((int)changeinCAD%20/10 );
		
		// calculate the number of 5$ bill		
		if((int)changeinCAD%10/5 >0)
			System.out.print((int)changeinCAD%10/5+" $5 bill");
		
		// check if the noun is proper
		checkNoun((int)changeinCAD%10/5 );
		
		// calculate the number of 1$ bill		
		if((int)changeinCAD%5 >0)
			System.out.print((int)changeinCAD%5+" $1 bill");
		
		// check if the noun is proper
		checkNoun((int)changeinCAD%5 );
		
		// transfer two decimal to int
		twoDecimal = (changeinCAD%1)*100%100;
		
		//round the two decimal
		twoDecimal = Math.round(twoDecimal);
		
		// calculate the number of 25 cent coins		
		if((int) twoDecimal/25 >0)
			System.out.print( ((int) twoDecimal)/25 +" 25c coin");
		
		// check if the noun is proper
		checkNoun(((int) twoDecimal)/25);
		
		// calculate the number of 10 cent coins		
		if((int) twoDecimal%25/10 >0)
			System.out.print( ((int) twoDecimal)%25/10 +" 10c coin");
		
		// check if the noun is proper
		checkNoun(((int) twoDecimal)%25/10 );
		
		// calculate the number of 1 cent coins	
		if(twoDecimal %25%10  >=1)
			System.out.print(Math.round(twoDecimal %25 %10)+" 1c coin"+"");
		
		// check if the noun is proper
		checkNoun(((int) twoDecimal)%25%10 );
		
		// display a message
		System.out.println("Thank you for shopping with us!");		
	}
	
/**
 * check if "s" should appear
 * @num the number of bill or coin
 */
	private void checkNoun(int num) {
		
		// if the number of bill or coin is greater than 1 , add 's' after bill or coin
		if (num>1)
			System.out.print("s\n");
		if(num==1)
			System.out.print("\n");
		
		// 
		
	}



	/**
	 * Entry point of the program
	 */
	public static void main(String[] args) {
		new CanadianGiftShop().takeAndProcessOrder();
	}

}
