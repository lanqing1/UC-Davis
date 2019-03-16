from graphics import*

def main():
    
    # Create a window
    win = GraphWin("Shapes",400,150)
    win.setBackground("lavender")
    
    # Creat a rectangle    
    AddRect = Rectangle(Point(25,15),Point(80,40))
    
    # Draw the rectangle "ADD"
    AddRect.draw(win)
    
    # Fill the rectangle with white
    AddRect.setFill("white")
    AddRect.setOutline("White")

    # Add text"Add" inside the rectangle
    Text(Point(51,28),"Add").draw(win)

    # Draw symbol"+" and "="
    SymbolRect1 = Rectangle(Point(100,60),Point(120,63))
    SymbolRect1.setFill("black")
    SymbolRect1.draw(win)

    SymbolRect2 = Rectangle(Point(109,53),Point(112,70))
    SymbolRect2.setFill("black")
    SymbolRect2.draw(win)

    SymbolRect3 = Rectangle(Point(220,55),Point(240,58))
    SymbolRect3.setFill("black")
    SymbolRect3.draw(win)

    SymbolRect4 = Rectangle(Point(220,61),Point(240,64))
    SymbolRect4.setFill("black")
    SymbolRect4.draw(win)

    # Create two rectangle
    Rect1= Rectangle(Point(25,50),Point(80,75))
    Rect1.setFill("white")
    Rect1.setOutline("grey")
    Rect1.setWidth(4)
    Rect1.draw(win)

    Rect2= Rectangle(Point(145,50),Point(200,75))
    Rect2.setFill("white")
    Rect2.setOutline("grey")
    Rect2.setWidth(4)
    Rect2.draw(win)
    
    # Let the user input 2 number
    entInput1 = Entry(Point(52,63),5)
    entInput1.setFill("White")
    entInput1.draw(win)

    entInput2 = Entry(Point(172,63),5)
    entInput2.setFill("White")
    entInput2.draw(win)

    


    blnRun = True

    while blnRun == True:

        # Gets X and Y coords.        
        ClickXY = win.getMouse()
        ClickX = ClickXY.getX()
        ClickY = ClickXY.getY()

        if ClickX<80 and ClickX> 25 and ClickY<40 and ClickY>15:
            
            fltInput1 = float(entInput1.getText())
            fltInput2 = float(entInput2.getText())
            fltAnswer = fltInput1 + fltInput2
            strAnswer = str(fltAnswer)
            ansText = Text(Point(280,60),strAnswer)
            ansText.setSize(20)
            ansText.setTextColor("salmon")
            ansText.draw(win)
            blnRun = False

    win.getMouse()
    win.close()
    
    
     

main()
         
