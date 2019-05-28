# find-valid-path
## Description:
Determines whether an electrical grid of a power station can deliver power to a new customer using the stack data structure to find a valid path.

## Notable files:
The main method is in "FindValidPath\src\FindConnection.java"
Sample maps of the power station electrical grid inputs are included in "FindValidPath\input\.." (maps 6-8 do not have a path from the power station to the new costumer house)

## Prequisite:
Eclipse IDE

## Prepare for Deployment:
*Specify which power station map to be used as input using "Run Configurations"*

 The arguments tab in "Run Configurations" should contain the location of the input file/image:

 Examples arguments include:
 C:\Users\Eliza\FindValidPath\input\map1.txt 
  
## Deployment:
Once the program is run, a window showing the electrical grid, the power station,omni switches,horizonal/vertical switches neighbouring houses and the new customer house should pop up. The program will use a blue electric symbol block to show the path that is being actively being tested.The program is finished when the word "Found" or "Not Found" is printed in the console. "Found" indicates a valid path was found from the power station to the new costumer house.




