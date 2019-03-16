# Project:      Homework2 (ChengTenHomework02Sec03.py)
# Name:         LanQing Cheng, Alina Ten
# Date:         10/22/15
# Description:  This program will allow the user to determine
#               the cost of 3 items shipped with tax


#Test data
#Book $10 1 each 2lbs
#Cup  $15 2 each 1lbs 
#Pen  $1  1 each 0.5lbs
#Subtotal is $41.0, Shpping & Handling is $ 6.12
#Tax is $4.01 and the Total is $ 51.13





def main():
    
    
    fltSubTotal = 0
    fltShip = 0
    list=[] 
    
    # Loop for 3 times
    for i in range(3):
        
        # Capture the information for items
        strItem = str(input("Enter the name of item"))
        fltPrice = float(input("Enter the price of item"))
        intQuantity = int(input("Enter the quantity of item"))
        fltWeight = float(input("Enter the weight of item"))

        # List the items
        list.append(strItem)
        
        # Calculate the cost
        fltSubTotal += fltPrice * intQuantity
        
        #Shipping and Handling cost
        fltShip += 0.25 * fltWeight * intQuantity
        fltHandling = 5
        
    #Calculate the Tax and Total    
    fltTax = (fltSubTotal +fltShip + fltHandling)* 0.085
        
    fltTotal = fltSubTotal + fltShip + fltHandling + fltTax
    
    # Display all parts
    print("You have purchased:",list)
    print("The subtotal is $",round(fltSubTotal,2))
    print("The Shipping and Handling costs are $",round(fltShip + fltHandling,2))
    print("The Tax is $",round(fltTax,2))
    print("Total is $",round(fltTotal,2))

main()


    
