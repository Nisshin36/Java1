import java.util.ArrayList;

public class Campaign {

	private String candidateName;
	private ArrayList<Donor> donors;
	
	public Campaign(String  name)
	{
		//TODO Initialize all of the instance data
	}
	
	public String getCandidateName()
	{
		//TODO Complete the accessor
		return null; // stub
	}

	public String getDonors()
	{
		String result = candidateName + "\n";
		result += donors.toString();
		
		return result;
	}
	public double getAllDonations()
	{
		double sum = 0.0;
		for (int i=0; i<donors.size(); ++i)
		{
			Donor d = donors.get(i);
			sum += d.getTotalDonations();
		}
		return sum;
	}
	
	public void addDonor(String name)
	{
		// TODO Check that there is not a donor by this name already
		
		
		// TODO If we get here this donor does not exist--add them in
		
	}
	
	public double getDonation(String donor)
	{
		//TODO Complete this method

		return 0.0; // stub
	}
	
	public String getDonationList(String donor)
	{
		for (int i=0; i<donors.size(); ++i)
		{
			Donor d = donors.get(i);
			if (d.getName().equals(donor))
			{
				return d.toString();
			}
		}

		return "No donor with that name was found";
	}
	
	public void addDonation(String donorName, double donation)
	{
		// TODO Complete this method
	}
}
