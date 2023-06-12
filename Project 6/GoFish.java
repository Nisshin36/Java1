// Anh-Tu Ngoc Ho CS1323 Project 6 

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/** Play a simple game like Go Fish!
 * 
 * @author Deborah A. Trytten
 * @author Anh-Tu N. Ho 
 * @version 1.0
 *
 */
public class GoFish 
{

	/** Each player gets 7 cards initially
	 * 
	 */
	public static int STARTING_HAND_SIZE = 7;
	public static int DEFAULTSIZE = 52; 
	public static int RANKS = 13;
	
	/** Play a game of Go Fish!  The rules are below.
	 * A regular deck of cards consists of 52 cards.  
	 * There are four suits and thirteen card ranks (Ace, 2, 3,…10, Jack, Queen, and King). 
	 * We’re going to simplify our cards.  The cards will have ranks from 1 to 13, 
	 * and each rank will have identical cards.  This removes suit from the game.
	 * 
	 * The computer deals seven cards to the human and the computer from a shuffled deck. The 
	 * remaining cards are shared in a pile.
	 * 
	 * The human player should play first. The human asks the computer for all its card(s) 
	 * of a particular rank that is already in his or her hand. 
	 * For example Mayra may ask, "Computer, do you have any threes?" Mayra must have at 
	 * least one card of the rank she requested in her hand. The computer must hand over 
	 * all cards of that rank. If the computer has no cards of that rank, 
	 * Mayra is told to "Go fish," and she draws a card from the pool and places 
	 * it in her own hand. When any player at any time has all four cards of one rank,
	 *  it forms a book, and the cards must be removed from the hand and placed face 
	 *  up in front of that player. 
	 *  
	 *  If the player has no cards in their hand, they may not request cards form the other 
	 *  player, they just draw a card.
	 *  When the pile is empty, no cards are drawn, but the player still gets to ask for cards 
	 *  following the same rules.
	 *  
	 *  The computer is not allowed to examine or deduce the human player’s cards while 
	 *  playing the game. The computer should randomly pick one card from their hand to request.  
	 *  This means that the computer is not being strategic at all and will 
	 *  probably lose most of the time (unless the player really stinks at Go Fish!). 
	 *  
	 *  When all sets of cards have been laid down, the game ends. The player with the 
	 *  most cards in piles wins.
	 *  
	 *  The game is easier to play if the cards are printed out in sorted order.  
	 *  This also uses a method in the Collections class, which meets a learning objective.

	 * @param args There are no command line arguments.
	 */
	public static void main(String[] args) 
	{
		Scanner input = new Scanner(System.in);
		
		// TODO: Create deck of cards
		ArrayList<Integer> pool= new ArrayList<Integer>();
		createDeck(pool);
		
		// TODO: Shuffle Cards
		Collections.shuffle(pool); // Done 
		Collections.reverse(pool); // I had to use at least 2 methods from this class so eh.
		

		playOneGame(pool, input);
	}
	
	
	public static void playOneGame(ArrayList<Integer> pool, Scanner input)
	{
		ArrayList<Integer> computer = new ArrayList<Integer>();
		ArrayList<Integer> person = new ArrayList<Integer>();
		ArrayList<Integer> computerPile = new ArrayList<Integer> ();
		ArrayList<Integer> personPile = new ArrayList<Integer>();

		// TODO: Deal cards
		dealHands(pool, person, computer);
		// Done
		
		
		// TODO: Show the person their starting hand
		System.out.println("Here is your hand: " + person);
		// Done
		
		
		while (computerPile.size() + personPile.size() < DEFAULTSIZE || !pool.isEmpty())         // Game setinal 
		{
			
			if (!person.isEmpty())
			{
				System.out.println("What card do you want?: ");
				int card = input.nextInt();
				
				while ( !person.contains(card))                           // Player must call a card in player's hand. 
				{
					System.out.println("That card is not in your hand!");  
					System.out.println("What card do you want?: ");
					card = input.nextInt();
				}
				
				//TODO: Play one turn with the person doing the choosing
				playOneTurn(card, person, computer, personPile, computerPile, pool); 
		
			}
			else
			{
				person.add(pool.get(0));                    // If player hand is empty, remove an element from pool and add it to the player hand. 
			}	
			
			showGameState(person, computerPile, personPile);
			
			// Now it is the computer's turn
			if (!computer.isEmpty())
			{
				int card = computer.get((int)(Math.random()*computer.size()));	
				System.out.println("Do you have any "  + card + "'s ?");
				
				//TODO: Play one turn with the computer doing the choosing
				playOneTurn(card, computer, person, computerPile, personPile, pool);
				
			}
			else if (!pool.isEmpty())
			{
				//TODO: Let the computer draw from the deck
				computer.add(pool.get(0)); 
				// Done
			}
				
			showGameState(person, computerPile, personPile);
		}
		
		// TODO: Determine the winner and tell the user--remember ties are possible
		if ( personPile.size() > computerPile.size())
		{
			System.out.println("You Win");
		}
		else if ( personPile.size() < computerPile.size())
		{
			System.out.println("I Win");
		}
		else 
		{
			System.out.println("It's a painting");
		}
			
			
	}
	

	public static void showGameState(ArrayList<Integer> person, ArrayList<Integer> computerPile,
			ArrayList<Integer> personPile)
	{
		System.out.println("Here are your cards");
		showCards(person);
		System.out.println("Here is your pile");
		showCards(personPile);
		System.out.println("Here is my pile");
		showCards(computerPile);
	}
	

	public static void playOneTurn(int card, ArrayList<Integer> chooser, ArrayList<Integer> chosen,
			ArrayList<Integer> chooserPile, ArrayList<Integer> chosenPile, ArrayList<Integer> pool)
	{	
		
		if (chosen.contains(card))
		{
			transferCards(card, chooser, chosen);    // This method takes the card from the other player's hand and adds it to this player's hand. 
		}
		else
		{
			System.out.println("Go fish!");
			//TODO: Draw a card by removing it from the pool and putting it in the chooser's hand
			if (!pool.isEmpty())
			{
				chooser.add(pool.get(0));
				pool.remove(0);
			}		
			
		}
		//TODO: If there is a set of four matching cards, put them on the table
		int markedValue = -1;                                    // This is to record any values that shows up 4 times
		                                                         // I had to set it to a -1 in order for it to work
		for ( int i = 0; i < chooser.size(); ++ i)
		{
			if ( (Collections.frequency(chooser, chooser.get(i))) >= 4 ) // If this element at this specific index shows up more than 3 times,
			{
				markedValue = chooser.get(i);                    // Mark that element
				while ( chooser.contains(markedValue)) 
				{
					chooser.remove(new Integer(markedValue));    // Remove that element from Arraylist
				}
				
				for ( int n = 0; n < 4; ++ n)
				{
					chooserPile.add(markedValue);                // Since there are 4 elements of the same rank, add the elemrnt 4 times in the pile. 
				}
			}
		}
		
	}
	
	public static void transferCards(int card, ArrayList<Integer> destination, ArrayList<Integer> source)
	{
		while ( source.contains(card))                             // I used a while loop because computer..
		{                                                          // might contains multiple elements that match card. 
			 
			destination.add(source.get(source.indexOf(card)));     // From inside to out: 
			                                                       // Get the number containing card, 
			                                                       // Get the element of that number, 
			                                                       // Send it to person ArrayList.
			source.remove(source.indexOf(card));              
		}
	}
	
	
	public static void dealHands(ArrayList<Integer> pool, ArrayList<Integer> person, ArrayList<Integer> computer)
	{
		//TODO: Deal the cards
		int index;
		for ( index = 0; index < 7 ; ++index)                   // Should've used subList but I'm already too deep. 
		{
			person.add(pool.get(index));                        // Each the person and computer recieves 7 cards
			pool.remove(index);                                 // Take from pool, and add it to person or computer
		}
		for ( index = 0; index < 7; ++index)                    // This could be a method of its own but Im running out of time. 
		{
			computer.add(pool.get(index));                    
			pool.remove(index);                                 
		}
	}
	
	
	public static ArrayList<Integer> createDeck(ArrayList<Integer> pool)
	{
		//TODO: Create a deck of cards
		for ( int cards = 1; cards <= RANKS; ++cards)
		{
			for ( int suit = 0; suit < 4; ++suit)
			{
				pool.add(cards);
			}
		} 
		return pool; 
	}
	

	
	public static void showCards(ArrayList<Integer> cards)
	{
		// TODO: Sort the cards to make it easier for the user to know what they have
		Collections.sort(cards);
		
		for (Integer i: cards)
		{
			System.out.print(i + " ");
		}
		System.out.println();
	}

}
