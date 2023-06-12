/**
 * Project 3
 * Anh-Tu N. Ho
 * 113339392
 * CS1323
 * Comments are on the right of the line. 
 */



import java.util.Scanner;
public class FloorsRUs
{

	public static int CalculuateFlooring(int width, int depth, int SquareFeetPerBox)
	{
		int boxesNeeded;                  // This is the value that we're tryna' obtain through this method. 
		int initialArea;
		final double unusedExtra = 0.05;      // As stated in the assigment
		double totalAreaNeeded;
		
		initialArea = width * depth; 
		totalAreaNeeded = initialArea + ( initialArea * unusedExtra );
		boxesNeeded = (int) Math.ceil(totalAreaNeeded/SquareFeetPerBox) ;    // Rounded up to a whole box. 

		return boxesNeeded; 
		 
	}
	
	public static void main(String[] args) 
	{
		
		int boxesAvailable = 100;                      // This variable will also control the program's continuation.  
		final int SQUARE_FEET_PER_BOX = 155;
		int width = 0;
		int depth = 0;
		int boxesNeeded = 0; 
		
		
		while ( boxesAvailable > 0)       // If there are boxes available, the program will continue running.
		{
			
			Scanner Input = new Scanner(System.in);
		
			System.out.println();
			System.out.println("We now have " + boxesAvailable + " boxes avialable.");                       
			System.out.println("What is the size of your room?"
					+ " Enter the width and depth in whole feet: ");
			
			width = Input.nextInt();	
			depth = Input.nextInt();			
			boxesNeeded = CalculuateFlooring( width, depth, SQUARE_FEET_PER_BOX);     // Pass the width, depth, and constant to the CalculateFlooring method that calculates the boxes that would be needed. 
			
			if ( boxesAvailable > boxesNeeded)                          // After the method returns the value of how many boxes are needed, it will see whether it has that amount available. 
			{						
				System.out.println( "Your " + boxesNeeded + " boxes will be shipped to you shortly."); 
				boxesAvailable = boxesAvailable - boxesNeeded; 
			}
			else                                                 // If the amount of boxes available are not enough....
			{
				System.out.println("We currentyl only have " + boxesAvailable + " boxes left.");
				System.out.println("Based on our calculations, a room of " 
						+ width + " feet by " + depth + " feet would need " + boxesNeeded 
						+ " boxes."); 
				System.out.println("Would you like to purcahse the remaining boxes anyways? Yes or No?");		// It would ask the user if he/she wants to purchase what's left. 	
				
				String answer = new String(); 
				answer = Input.next(); 
				if ( answer.equalsIgnoreCase("yes") )                                                        // If the user enters yes....
				{
					System.out.println( "Your " + boxesNeeded + " boxes will be shipped to you shortly.");
					System.out.println("Alright we all out, bye.");
					boxesAvailable = 0;				                            // Then all boxes will be sold, thus ending the program.  
				}
				else if ( answer.equalsIgnoreCase("no") )                                      // If the user enters no, 
				{
					// PRIMING READ
					System.out.println("How many boxes would you like to purchase then?");         // program will prompt user for how much they would like to buy, whether it's 0 or a negative number. 
					boxesNeeded = Input.nextInt();
					
					while ( boxesNeeded > boxesAvailable )                           // boxesNeeded is strictly dependent on the user, if the user needs more boxes than available...
					{
						// PRIMING READ
						System.out.println("Didnt I just tell you that I don't have enough boxes?");
						System.out.println("How many boxes would you like to purchase then?");        // It will ask the user to enter again. 
						boxesNeeded = Input.nextInt();
					}
					
					if ( (boxesNeeded > 0) && (boxesNeeded <= boxesAvailable) )                 // If the user enters a value that greater than 0 but no more than the available...
					{
						System.out.println( "Your " + boxesNeeded + " boxes will be shipped to you shortly.");  // Then the program will sell the specified amount to the user,
						boxesNeeded = boxesAvailable = boxesNeeded;                                             // and adjust the amount of boxes still available after the transaction. 
					}
					else 
					{
						System.out.println("K Bye Then.");          // If the user enters a negative number, the program ends. 
					}
				}
				else 
				{
					System.out.println("Aight K Bye");         // If the user answers something other than yes, or no, then the program ends. 
				}

				
			}
		
		}	

	}

}
