# Project:      Homework3 (ChengLanQingHomework03Sec03.py)
# Name:         LanQing Cheng
# Date:         10/29/15
# Description:  This program will allow the user to  compute a
#               checking account balance. And display the total deposit,
#               total withdrawal, total service charge, and the times
#               that they do the transaction

def main():
    
    #Input a zero value
    fltWithdraw = 0.0
    fltService = 0.0 
    fltBalance = 0.0
    fltDeposit = 0.0
    
    fltTotalWithdraw = 0.0
    fltTotalService = 0.0
    fltTotalDeposit = 0.0
    fltTotalBalance = 0.0

    intTimeOfDeposit = 0
    intTimeOfWithdraw = 0
    intTimeOfService = 0

    #To start the loop
    strTransaction ="start"
    

    #Start the while loop until user type "end"    
    while strTransaction !="end":
        
        #Capture user input of transaction
        strTransaction = str(input("Enter the type of transaction(deposit,withdrawal,service charge,end):"))
        
        #Calculate the deposit part
        if strTransaction == "deposit":
            fltDeposit = float(input("Enter the amount of the deposit:"))

            #Make sure the number is valid 
            if fltDeposit < 0:
                print("Please enter a valid number")

            else:                
                fltTotalDeposit += fltDeposit
                fltTotalBalance += fltDeposit
                intTimeOfDeposit +=1
                
                print("Your account balance is :$", round(fltTotalBalance,2))
       
            
            

        #Calculate the withdrawal part 
        elif strTransaction =="withdrawal":
            fltWithdraw =float(input("Enter the amount of the withdrawal:"))

            #Make sure the number is valid 
            if fltWithdraw < 0:
                print("Please enter a valid number")
                
            elif (fltTotalBalance - fltWithdraw) >= 0:
                    
                    fltTotalWithdraw += fltWithdraw
                    fltTotalBalance = fltTotalBalance - fltWithdraw
                    intTimeOfWithdraw +=1
                    print("Your account balance is :$", round(fltTotalBalance,2))
                                        
            else:
                print("Here is not enough money to cover a Withdrawal")            
    
    


    
        #Calculate the Service Charge part
        elif strTransaction =="service charge":
            fltService = float(input("Enter the amount of the service charge:"))
            
            #Make sure the number is valid              
            if fltService < 0:
                print("Please enter a valid number")
                    
            else:
                fltTotalService += fltService
                fltTotalBalance = fltTotalBalance - fltService
                intTimeOfService += 1
                print("Your account balance is :$", round(fltTotalBalance,2))
                    
           
    #Stop the loop       
    strTransaction =="end"


    #Print the summary
    print()
    print("Here is your account information")
    print()
    
    print("Your account balance is: $",round(fltTotalBalance,2))
    print()
    
    print("The total amount of Deposit is: $",round(fltTotalDeposit,2))
    print("The number of deposits:",intTimeOfDeposit)
    
    print("The total amount of withdrawal is: $", round(fltTotalWithdraw,2))
    print("The number of the withdrawal:",intTimeOfWithdraw)
    
    print("The total amount of Service Charge is: $",round(fltTotalService,2))
    print("The number of Service Charges:",intTimeOfService)
    



main()
               





                
