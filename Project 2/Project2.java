import java.util.Scanner;

public class Project2 
{

	public static void main(String[] args)
	{
		
		System.out.println("Welcome to the stress indicator survey");
		System.out.print("You will be asked to answer some of the following questions"
				+ " to determine your stress levels.");
		System.out.println("For these questions, just simply answer 'yes' or 'no.");
		System.out.println("Let's begin.");
		
		int stressLevel = 0;
		String userInput = new String();

		Scanner scanner = new Scanner(System.in);
		
		System.out.println("Do you usually try to get away from people as fast as you can? ");
		userInput = scanner.nextLine();		
		if (userInput.equalsIgnoreCase("yes"))		
		{
			stressLevel = stressLevel + 1; 
		}
		

		System.out.println("Do you feel like you no longer have fun anymore? ");
		userInput= scanner.nextLine();
		if (userInput.equalsIgnoreCase("yes"))
		{
			stressLevel = stressLevel + 1;
		}
		
		
		System.out.println("Do you feel trapped? ");
		userInput = scanner.nextLine();
		if (userInput.equalsIgnoreCase("yes"))
		{
			stressLevel = stressLevel + 1; 
		}
		
		
		
		if (stressLevel < 1)
		{
			System.out.println("You're not stressed, you're depressed. Seek help.");
		}
		else if (stressLevel < 2)
		{
			System.out.println("You're beginning to stress out.");
		}
		else if (stressLevel < 3)
		{
			System.out.println("You're possibly stressed out");
		}
		else
		{
			System.out.println("Yeah...you're probably stressed out");
		}
		
		
	}
	
	//fin. 

}
