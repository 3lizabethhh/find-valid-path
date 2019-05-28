/**Dongzheng (Elizabeth)Xu
 * This class is the exception for an empty stack.
 * 
 * Last edited: February 28 2019
 **/
public class EmptyStackException  extends RuntimeException{
	
	  public EmptyStackException (String collection)
	  {
	    super ("The " + collection + " is empty.");
	  }
	
}