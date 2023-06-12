
import java.util.Arrays;
import java.util.Scanner; 

public class FindEmail 
{
	public static final int ADD = 1;                                          // Public constants 
	public static final int SEARCH = 2;
	public static final int QUIT = 3;

	
	public static void main (String[] args)
	{
		
		Scanner keyboard = new Scanner(System.in);
		
		int arrayLength = 100;                                             // Creating an oversized array 
		String[] emailList = new String[arrayLength];
		emailList[0] = "null";                                             // Putting a random placeholder in the array 
		int size = 1;                                                      // to create an initial insertion point 
		
		
		// START
		// Goes straight to menu method, the method will return a user's choice. 
		int choice = 1;                                                    // This satisfy the condition below and launch the program. 
		while (choice != QUIT && size!= 100)                               // Program's iteration is dependent on the user's input and the array spaces available. 
		{
			choice = menuChoice(keyboard);	                               // choice is dependent on the user's input, attained from the menuChoice method. See line 66.
			
			// The menuChoice method will only pass a integer that falls within the range of ADD to QUIT. 
			if ( choice == ADD)                                            // If user chooses option 1. 
			{
				Scanner userString = new Scanner(System.in);               // Created a new scanner to avoid any collisions with the previous scanner. 
				                                                           // This scanner is used solely for string input. 
				System.out.println("");
				System.out.println("Enter the new email address.");
				String target = userString.nextLine().toLowerCase();       // the user's email input will be stored in target. 
				
				boolean IsEmailAddress = isEmailAddress(target);           // Test to see whether this is a valid email. See line 115. 
				
				if ( IsEmailAddress == true)                               // If isEmailAddress method returns true...
				{
					size = addNewEmail( emailList, size, target );         // Pass the array, array size, and email input to the add method. See line 131.
				}
				else                                                       // If the email is an invalid format, 
				{                                                          // Then the program will return back to line 26. 
					System.out.println("Invalid email format");
				}		
			}	

			if ( choice == SEARCH)                                         // If user chooses option 2. 
			{
				Scanner userString = new Scanner(System.in);
				
				System.out.println("");
				System.out.println("Enter a partial search");
				String target = userString.nextLine().toLowerCase();       
				size = search(emailList, size, target);                    // Passes the emailList array, its size, 
			}		                                                       // and the target the user is searching for. See line 88. 
		}	                                                               // This line is the end of the iteration,
		                                                                   // If the user havent entered a command to quit, and if 
		                                                                   // There are still spaces available in the array,  
		                                                                   // Then the program jumps back to line 26. 
	} 
	                                                                       // END 
	
	
	public static int menuChoice(Scanner keyboard)
	{
		int userNum = 0;                                                              // Just to bring up the menu. userNum will be the user's input. 
			
		while ((userNum < ADD) || (userNum > QUIT))                                   // Will continue prompting the user for a correct choice. 
		{                                                                             // Correct choices can only be from 1 to 3. 
			System.out.println("");
			System.out.println("Please choose from the following menu of choices:");
			System.out.println("1.) Enter a new email address");
			System.out.println("2.) Find an existing email address.");
			System.out.println("3.) Quit");
			System.out.println("What is your choice?");
			userNum = keyboard.nextInt();                                             // userName is attained from the "keyboard" scanner.
																					  // keyboard is passed from the main method and was assigned in the menuChoice method. 
		}
		
		return userNum;                                                               // menuChoice method returns the value of userNum, 
																				      // Which will be applied to the choice variable in the main method. Return to line 28.
	}
	
	
	
	public static int search(String[] data, int size, String target)                  // data is the email list, and target is what we're searching for. 
	{
		boolean found = false;                 
		
		for ( int index = 0; index < size; ++index)                                   // Cycle through each element in the array. 
		{
			if ( data[index].startsWith(target))                                      // Comparing it to the target. 
			{
				found = true;                                                         // If found, the boolean will change to true. 
				System.out.println("");
				System.out.println("Results found: " + data[index]);
			}
		}
		
		if ( found == false )                                                         // If the boolean is still false, it means the target was not found. 
		{
			System.out.println("Results Not found.");		              
		}
		
		// This is just to bring the program back to the main method ( line 58 ), size is unchanged. 
		return size;                                                                 
		
	
	}
	
	public static boolean isEmailAddress ( String input )
	{
		boolean isEmailAddress; 
		if ( (input.indexOf(" ") == -1) && (input.indexOf("\n") == -1) && (input.indexOf("\t") == -1)  // A valid email will contain none of the following:
				&& (input.indexOf("@") != -1) && (input.indexOf("@") == input.lastIndexOf("@")) )      // Spaces, tabs, newlines, and must contain 1 @. 
		{
			isEmailAddress = true; 
		}
		else 
		{
			isEmailAddress= false; 
		}
		
		return isEmailAddress;                                                       // returns the test result to the main method. Return to line 39. 
	}
	
	public static int addNewEmail(String[] data, int size, String insertMe)          // data is the emailList, size is the amount of elements in the array, and insertMe is the user's email. 
	{
		
		Arrays.sort(data, 0, size);                                                  // Sorts the array from the first occupied element 
																				     // to the last occupied element in alphabetical order. 
		
		int searchResult = Arrays.binarySearch(data, 0, size, insertMe);             // Search the array for the Email in a certain range. Returns an integer.
		int insertionPoint = -searchResult - 1;                                      // Derived from the equation searchResult = -insertionPoint - 1. 
		
		if ( searchResult < 0 )                                                      // If binarySearch returns a negative number, then the email wasn't found. 
		{                                                                            // Therefore, it can now be added to the list. 
			for ( int index = size; index > insertionPoint; --index)                 // Starting from the right and stopping when it reaches the insertion point. 
			{
				data[index] = data[index - 1];                                       // Shift all elements from the right of the insertion point down the list. 
			}
			data[insertionPoint] = insertMe;                                         // Insert the email into a newly available array index. 
			size = size + 1;                                                         // Since we just added another item to the array, 
		}                                                                            // We must not that the size has increased. 
		else                                                                         // If binarySearch returns a positive number, 
		{                                                                            // That means the user's email is already in the list. 
			Scanner keyboard = new Scanner(System.in);
			System.out.println("This address was already inserted.");
 
		}		
		return size;		                                                         // Report the size back to the main method. Return to line 43. 
	}
	
}
