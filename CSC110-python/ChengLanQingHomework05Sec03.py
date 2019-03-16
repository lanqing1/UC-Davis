# Project:      Homework5 (ChengLanQingHomework05Sec03.py)
# Name:         LanQing Cheng
# Date:         28/11/15
# Description:  This program will allow the user to click on Dice
#               outlines, and display random dice. Then display the
#               total dice in the bottom, and allow the user to exit.


from graphics import*
import random

# Create Congratulation function to congratulate the result
def Congratulation(sumDice,i,win):
    if i ==4:
        if sumDice >= 20:
            Text(Point(150,180),"Congratulations! You are super lucky!").draw(win)
        
        elif 20> sumDice >10:
            Text(Point(150,180),"You are lucky!").draw(win)

        elif sumDice<=10:
            Text(Point(150,180),"You are a little bit lucky!").draw(win)
            
# Create Total function to calculate the dice total
def  Total(lstsumDice,win,i):
    if i ==0:
        Text(Point(160,130),"Dice Total :").draw(win)

        
    sumDice = sum(lstsumDice)

    # Hide the Dice total appeared before
    RectHide = Rectangle(Point(140,150),Point(160,170))
    RectHide.setOutline("khaki")
    RectHide.setFill("khaki")
    RectHide.draw(win)

    # Display the Dice total
    TextDice = Text(Point(150,160),sumDice)
    TextDice.draw(win)

        
    # Call Congralation function
    Congratulation(sumDice,i,win)
            
# Create random function to random the dice
def Random(Dot1,Dot2,Dot3,Dot4,Dot5,Dot6,Dot7,win,lstsumDice,i):

    # Random the Dice number
    Dice = random.randint(1,6)

    # Draw Dice for each side
    if Dice == 1:
        Dot6.draw(win)
        
    elif Dice ==2:
        Dot1.draw(win)
        Dot7.draw(win)

    elif Dice ==3:
        Dot6.draw(win)
        Dot1.draw(win)
        Dot7.draw(win)

    elif Dice ==4:
        Dot1.draw(win)
        Dot3.draw(win)
        Dot4.draw(win)
        Dot7.draw(win)

    elif Dice ==5:
        Dot1.draw(win)
        Dot3.draw(win)
        Dot4.draw(win)
        Dot7.draw(win)
        Dot6.draw(win)

    elif Dice ==6:
        Dot1.draw(win)
        Dot3.draw(win)
        Dot4.draw(win)
        Dot7.draw(win)
        Dot2.draw(win)
        Dot5.draw(win)

    
    # Put Dice in list
    lstsumDice.append(Dice)
    
    # Call Total function
    Total(lstsumDice,win,i)

def Dot(ClickXY,win,i,lstsumDice):
    
    
    #center = Point(45+70*i,45+70*i)
    Dot1 = Circle(Point(30+70*i,30),5)
    Dot2 = Circle(Point(30+70*i,45),5)
    Dot3 = Circle(Point(30+70*i,60),5)
    Dot4 = Circle(Point(60+70*i,30),5)
    Dot5 = Circle(Point(60+70*i,45),5)
    Dot6 = Circle(Point(45+70*i,45),5)
    Dot7 = Circle(Point(60+70*i,60),5)
    
    Dot1.setFill("black")   
    Dot2.setFill("black")    
    Dot3.setFill("black")    
    Dot4.setFill("black")    
    Dot5.setFill("black")    
    Dot6.setFill("black")    
    Dot7.setFill("black")
    
    # Call Random function to get random dice
    Dice = Random(Dot1,Dot2,Dot3,Dot4,Dot5,Dot6,Dot7,win,lstsumDice,i)


def main():
    
    # Create a window
    win = GraphWin("Shapes",400,200)
    win.setBackground("khaki")

    lstsumDice = []
    sumDice = 0

    # Use loop to draw dice and text
    # List the text of dice
    listDice = ["Dice1","Dice2","Dcie3","Dice4","Dice5"]
    
    for i in range(5):
        
        # Set 5 dice
        Dice = Rectangle(Point(18+70*i,18),Point(73+70*i,73))
        Dice.setOutline("grey")
        Dice.setWidth(2)
        Dice.draw(win)

        # Set 5 text in DiceRectangle
        TextDice = Text(Point(45+70*i,45),listDice[i])
        TextDice.setTextColor("grey")
        TextDice.draw(win)

    # Draw exiRectangle
    ExitRect = Rectangle(Point(300,150),Point(340,173))
    ExitRect.draw(win)
    
    Text(Point(320,161),"Exit").draw(win)
    ExitRect.setFill("white")
    
    # Click 5 times to throw dice
    Click1 = win.getMouse()              
    if 70>Click1.getX()>20 and 70>Click1.getY() >20:

        # Build Dice
        Rect1 = Rectangle(Point(20,20),Point(70,70))
        Rect1.setFill("white")
        Rect1.draw(win)
        i =0
        
        # Call Dot function to draw Dot
        Dot(Click1,win,i,lstsumDice)

    Click2 = win.getMouse()  
    if 140>Click2.getX()>90 and 70>Click2.getY() >20:

        Rect2 = Rectangle(Point(90,20),Point(140,70))
        Rect2.setFill("white")
        Rect2.draw(win)
        i = 1
        Dot(Click2,win,i,lstsumDice)
      
        
    Click3 = win.getMouse()
    if 210>Click3.getX()>160 and 70>Click3.getY() >20:

        Rect3 = Rectangle(Point(160,20),Point(210,70))
           
        Rect3.setFill("white")
        Rect3.draw(win)
        i =2
        Dot(Click3,win,i,lstsumDice)
        
    Click4 = win.getMouse()
    if 280>Click4.getX()>230 and 70>Click4.getY() >20:

        Rect4 = Rectangle(Point(230,20),Point(280,70))
           
        Rect4.setFill("white")
        Rect4.draw(win)
        i = 3
        Dot(Click4,win,i,lstsumDice)

        
    Click5 = win.getMouse()
    if 350>Click5.getX()>300 and 70>Click5.getY() >20:
        
        Rect5 = Rectangle(Point(300,20),Point(350,70))               
        Rect5.setFill("white")
        Rect5.draw(win)
        i = 4
        Dot(Click5,win,i,lstsumDice)


    # Click on Exit bottom to get close
    Clickexit = win.getMouse()    
    
    if 340 > Clickexit.getX() >300 and 173 > Clickexit.getY() > 150:
        win.close()

 
   
main()
