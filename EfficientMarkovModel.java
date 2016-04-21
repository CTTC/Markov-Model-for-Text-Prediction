import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class EfficientMarkovModel extends AbstractMarkovModel 
{
	private int numOfChar;  // The number of characters to be used as reference each time so as to 
	                        // find  what the next character should be 
	private HashMap<String, ArrayList<String>> myMap;  // each key stores a short sequence of numOfChar characters
	                                                   // they are obtained from the input text(myText)
													   // Each value means the corresponding following character after 
													   // the key in the given text(myText).
	
	public EfficientMarkovModel(int n) {
		myRandom = new Random();
		numOfChar = n;
		myMap = new HashMap<String, ArrayList<String>>();
	}
	
	public void setRandom(int seed){
		myRandom = new Random(seed);
	}
	
	public void setTraining(String s){
		myText = s.trim();
	}
	public String toString()
	{
		return String.format("Efficient MarkovModel of order %d", numOfChar);
	}
	
	public void buildMap()
	{
		int begin = 0;
		do 
		{
			String key = myText.substring(begin, begin + numOfChar);
			if (!myMap.containsKey(key))
			{
				ArrayList<String> follows = new ArrayList<String>();
				int index = 0;
				do 
				{
					index = myText.indexOf(key, index);
					if (index != -1)
					{
						if(key.length() + index < myText.length())
						{
							follows.add(myText.substring(index + key.length(), index + key.length() + 1));
							index = index + 1;
						}
						else
						{
							break;
						}
					}
					else
					{
						break;
					}			
				} while (index != -1);
				myMap.put(key, follows);
			}
			begin += 1;
		} while ((begin + numOfChar) <= myText.length());
	}
	
		public void printHashMapInfo()
	{
		// Print the hashmap only if the hashmap is small
		if (myMap.size() < 50)
		{
			for (String word : myMap.keySet())
			{
				System.out.println("Key: " + word + "   Value: " + myMap.get(word));
			}
		}
		System.out.println("The number of keys: " + myMap.size());
		// Find the key with largest value
		int maxSize = 0;
		ArrayList<String> maxKey = new ArrayList<String>();
		for (String word : myMap.keySet())
		{
			if (myMap.get(word).size() > maxSize)
			{
				maxKey.clear();
				maxSize = myMap.get(word).size();
				maxKey.add(word);
			}
			else if (myMap.get(word).size() == maxSize)
			{
				maxKey.add(word);
			}
		}
		System.out.println("The size of the largest value in the HashMap is : " + maxSize);
		System.out.println("The keys that have the maximum size value: ");
		for (int i = 0; i < maxKey.size(); i++)
		{
			System.out.println(maxKey.get(i));
		}
	}	
	
	// For the given key, find out the possible following character
	public ArrayList<String> getFollows(String key)
	{		
		return myMap.get(key);		
	}
	

	// Get the text predicted by Markov Model with order = numOfChar
	// The length of the text is numChars
	public String getRandomText(int numChars){
		if (myText == null){
			return "";
		}
		buildMap();
		//printHashMapInfo();
		int index = myRandom.nextInt(myText.length() - numOfChar);
		String key = myText.substring(index, index + numOfChar);		
		StringBuilder sb = new StringBuilder();
		sb.append(key);
		for(int k=0; k < numChars; k++)
		{
			ArrayList<String> follows = getFollows(key);
			if (follows.size() == 0) {
		        break;
		    }
			index = myRandom.nextInt(follows.size());
			key = key.substring(1) + follows.get(index);
			sb.append(follows.get(index));
		}			
		return sb.toString();
	}
}
