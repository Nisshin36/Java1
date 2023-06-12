import java.util.ArrayList;
import java.util.Collections;

public class frequencyTest 
{

	public static void main(String[] args) 
	{
		ArrayList<Integer> a = new ArrayList<Integer>(); 
		ArrayList<Integer> b = new ArrayList<Integer>();
		a.add(4);
		a.add(4);
		a.add(4);
		a.add(4);
		a.add(3);
		a.add(5);
		a.add(8);
		a.add(5);
		a.add(8);
		a.add(9);
		
		int markedValue = -1; 
		for ( int i = 0; i < a.size(); ++ i)
		{
			if ( (Collections.frequency(a, a.get(i))) >= 4 ) 
			{
				markedValue = a.get(i); 
				while ( a.contains(markedValue)) 
				{
					a.remove(new Integer(markedValue));
				}
				
				for ( int n = 0; n < 4; ++ n)
				{
					b.add(markedValue); 
				}
			}
		}

		System.out.println(a);
		System.out.println(b);
		
		
	}

}
