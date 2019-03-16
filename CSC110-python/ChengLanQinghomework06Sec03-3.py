# Project:      Homework06 (ChengLanQingHomework06Sec03.py)
# Name:         LanQing Cheng
# Date:         07/12/15
# Description:  This program will  import an input file that have
#               information on any number of families.
#               It allows the user to choose in 4 reports to generate.

    
    
# Create the function to find the families that above the poverty level 
def Report2(win,lstFamily,lstAbove):
    for i in range(14):
        
        if lstFamily[i][3] == "HI":
            intSize = int(lstFamily[i][1])
            intStandProverty = (intSize-1)* 3660 +10700
            
            if  int(lstFamily[i][2])> intStandProverty :
                lstAbove.append(lstFamily[i])
                          
        elif lstFamily[i][3] == "AK":

            intSize = int(lstFamily[i][1])
            intStandProverty = (intSize-1)* 3980 +11630
            
            if  int(lstFamily[i][2])> intStandProverty :
                lstAbove.append(lstFamily[i])

        else:
            intSize = int(lstFamily[i][1])
            intStandProverty = (intSize-1)* 3180 +9310

            if  int(lstFamily[i][2])> intStandProverty :
                lstAbove.append(lstFamily[i])
                   

# Create the function to find the families that below the poverty level 
def Report1(lstFamily,lstBelow):
    
    for i in range(14):
        
        if lstFamily[i][3] == "HI":
            intSize = int(lstFamily[i][1])
            intStandProverty = (intSize-1)* 3660 +10700
            
            if  int(lstFamily[i][2])< intStandProverty :
                lstBelow.append(lstFamily[i])
                          

        elif lstFamily[i][3] == "AK":

            intSize = int(lstFamily[i][1])
            intStandProverty = (intSize-1)* 3980 +11630
            
            if  int(lstFamily[i][2])< intStandProverty :
                lstBelow.append(lstFamily[i])

        else:
            intSize = int(lstFamily[i][1])
            intStandProverty = (intSize-1)* 3180 +9310

            if  int(lstFamily[i][2])< intStandProverty :
                lstBelow.append(lstFamily[i])
                
            
# Create the function to hide the text    
def RectHide(win):
    RectHide = Rectangle(Point(20,90),Point(600,200))
    RectHide.setOutline("khaki")
    RectHide.setFill("khaki")
    RectHide.draw(win)          


from graphics import*

def main():


    # Create win
    win = GraphWin("Poverty",600,200)
    win.setBackground("Khaki")

    Flag = True
    
    # Draw buttons
    for i in range(6):
        btn = Rectangle(Point(20+80*i,60),Point(80+80*i,80))    

        btn.draw(win)
    
    # Draw texts in buttons
    lstText = ["import","report1","report2","report3","report4","exit"]
    for i in range(6):
       Text(Point(50+i*80,70),lstText[i]).draw(win) 
  
    # Click on first button
    while Flag == True:
        Click1 = win.getMouse()
        
        if 20<Click1.getX()<80 and 60<Click1.getY()<80:
        
            # Open infile to get data
            inFile = open("input file.txt","r")
        
            # Create lists
            lstFamily = []
            lstA = []
            lstBelow = []
            lstAbove = []
            
            # Put data in list
            for line in inFile:
                lstA = line.split()
                lstFamily.append(lstA)
                
            # Call function to get data
            Report1(lstFamily,lstBelow)
            Report2(win,lstFamily,lstAbove)
                
            # Hide the previous text
            RectHide(win)
            
            Text(Point(270,170),"You have import the input file").draw(win)
            
            Flag = False

        else:
            RectHide(win)
            Text(Point(270,170),"Please click on import button first").draw(win)

           
        
    b = 0  
    while Flag == False :
        
        # Second click to write out report1 into the outfile
        Click = win.getMouse()
        X = Click.getX()
        Y = Click.getY()
        
        # Report 01
        # write out to a file all the families that fall below the poverty level
        if 100<X<160 and 60<Y<80:
            
            # Open the outFile to write in data
            outFile = open("Cheng_LanQing_report01.txt","w")
         
            for data1 in lstBelow:
                
                print(data1[0],file=outFile)
                
            # Close the outfile
            outFile.close()
            
            RectHide(win)
            Text(Point(270,170),"Report 1 has been written to <Cheng_LanQing_report01.txt>").draw(win)

        # Report 02
        # Display the families that above the poverty level
        elif 180<X<240 and 60<Y<80:
            a = 0
            
            RectHide(win)
            Text(Point(270,120),"These families are above the average income:").draw(win)
           
            for data2 in lstAbove:
                a += 1
                                     
                
                Text(Point(a*40 + 40,170),data2[0]).draw(win)

        # Report 03
        # Display the percentage of families that fall below the poverty level
        elif 260<X<320 and 60<Y<80:        
            
            # Get the data of families that below the poverty
            intBelow = len(lstBelow)

            # Calculate the percentage
            fltPctBelow = round(intBelow/14*100,2)

            RectHide(win)
            
            # Display the result
            Text(Point(270,170),"% families fall below the poverty level").draw(win)
            Text(Point(120,170),str(fltPctBelow)).draw(win)

        
        # Report 04           
        elif 340<X<400 and 60<Y<80:
             
            # Open the outfile
            outFile2 = open("Cheng_LanQing_report04.txt","w")
            
            b += 1
            # Control the following loop only go once
            if b == 1:
                
                # Add a field that indicates if families fall below the poverty level.
                for i in lstBelow:
                    
                    lstFamily[lstFamily.index(i)].insert(4,"Below poverty family")

            # Write out the file
            for data2 in lstFamily:           
                
                print(data2,file=outFile2)
                
            # Display the message shows write out file succeed    
            RectHide(win)
            Text(Point(270,170),"Report 4 has been written to <Cheng_LanQing_report04.txt>").draw(win)
            
            # Close the outfile
            outFile2.close()
                    
                    
        # Click to close       
        if 420<X<480 and 60<Y<80:
            Flag = True

        if Flag == True:
            win.close()

main()        
