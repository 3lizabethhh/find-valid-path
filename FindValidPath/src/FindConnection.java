import java.io.FileNotFoundException;
import java.io.IOException;

/**Dongzheng (Elizabeth)Xu
 * This class will connect the power station to the customer by pushing and popping blocks in a stack.
 * This class will output "Found" if there is a valid electrical grid path to the new costumer.
 * and "Not Found" if there is no path.
 * 
 * Last edited: February 28 2019
 **/

public class FindConnection {

	private Map cityMap; 
	
	/**Constructor of an object that represents the city map where WPC and C are located
	 * @param passes in filename of file with city map that will be displayed
	 * 
	 * */
	public FindConnection(String filename) {
		try {
			cityMap = new Map (filename);
		}

		catch (InvalidMapException e) {
			System.out.println(e);
		}

		catch(FileNotFoundException e) {
			System.out.println(e);
		}
		catch (IOException e) {
			System.out.println(e);
		}
	}
	
	 
	/**
	 * Method that finds the next best cell for the path with priority in order of customer,omni switch,horizontal/vertical switch.
	 * @param map cell that is on top
	 * @return map cell that is the next best cell
	 */
	private MapCell bestCell (MapCell cell) {

		//keeps track of direction [0]north,[1]west etc.. that the current cell can access
		boolean[] direction = {true,true,true,true}; 

		//restricts accessible sides for vertical/horizontal switch
		if(cell.isVerticalSwitch()) {
			direction[1]=false;//can't access west
			direction[3]=false;//can't access east

		}
		if(cell.isHorizontalSwitch()) {
			direction[0]=false;//can't access north
			direction[2]=false;//can't access south

		}

		//check neighbouring cells: first customer, then omni then horizontal/vertical switches
		for(int i=0;i<4;i++) {//check all four neighbours to see if customer
			MapCell neighbour = cell.getNeighbour(i);
			if (neighbour!= null && !neighbour.isMarked() && direction[i]&& neighbour.isCustomer()) { //return cell if has not already marked,has content, and is accessible by current cell
				return neighbour;//return the neighbour cell
			}
		}

		for(int i=0;i<4;i++) {//check all four neighbours to see if omni
			MapCell neighbour = cell.getNeighbour(i);	
			if (neighbour!=null && !neighbour.isMarked() && direction[i]&&neighbour.isOmniSwitch()) { //return cell if has not already marked,has content, and is accessible by current cell
				return neighbour; }
		}

		for(int i=0;i<4;i++) {//check all four neighbours to see if vertical/horizonal with smallest index
			MapCell neighbour = cell.getNeighbour(i);	
			if (neighbour!=null && !neighbour.isMarked() && neighbour.isVerticalSwitch() && direction[i] && (i==0 || i==2)) { //if neighbour has not already been checked and is switch
				return neighbour; }

			if (neighbour!=null && !neighbour.isMarked() && neighbour.isHorizontalSwitch() && direction[i] && (i==1 || i==3)) { //if neighbour has not already been checked and is switch
				return neighbour; }

		}

		return null;//returns null if no unmarked adjacent cells
	}

	/**
	 * Accessor method to get cityMap
	 * @return city map
	 */
	public Map getCityMap() {
		return cityMap;
	}

	/**
	 * Main method that finds path from WPC to C.
	 * @param String[] args
	 *
	 */
	public static void main (String[] args) {
		FindConnection find = new FindConnection(args[0]);//create object of find connection
		ArrayStack<MapCell> stack = new ArrayStack<>(); //stack that saves the path

		boolean found=false; // true if customer reached

		MapCell start = find.getCityMap().getStart();//start on the power station 
		MapCell nextCell;

		stack.push(start); // add starting block to path
		start.markInStack(); //mark it as in path

		while(!stack.isEmpty( )&& !found) {//while stack isn't empty and we havent found house 
			try {
				start=stack.peek();//peek at current cell and 
				if(!start.isCustomer()) {// if curr cell is not destination keep looking for path
					nextCell=find.bestCell(start); // find the nextCell
					if(nextCell!=null) { //if next cell is a valid block add it to stack
						stack.push(nextCell);
						nextCell.markInStack();
					}
					else { //if next cell is empty take it out of stack
						stack.pop();
						start.markOutStack();
					}
				}
				else {
					found=true;//if destination found stop looping
				}
			}
			catch (EmptyStackException e) {
				System.out.println("Exception:The Stack is empty.");
				e.printStackTrace();
			}


		}
		if(found) {
			System.out.println("Found");
		}
		else {
			System.out.println("Not Found");
		}

	}


}