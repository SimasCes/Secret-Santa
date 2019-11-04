//To be able to convert array components into strings in the console
//using Arrays.toString() or for 2D arrays Arrays.deepToString()
import java.util.Arrays;


//Allows easier manipulation of Java arrays with commands such as array.add() to add items,
//array.get() to get an item in an array, array.set() to set a new variable for an index,
//array.remove() to remove items from an array, array.size() for the size of an array
import java.util.ArrayList;

//Allows for the generation of random numbers in java
import java.util.Random;





public class ChristmasDraw 
{

	public static void main(String[] args) 
	{

		//Create the amount of couples that there are (there needs to be 2 arrays with the same
		//values in them as this is used later)
		String[] pairs1 = {"x1", "y1", "x2", "y2", "x3", "y3", "x4", "y4", "x5", "y5"};
		String[] pairs2 = {"x1", "y1", "x2", "y2", "x3", "y3", "x4", "y4", "x5", "y5"};
		
		//Make an array of elements that cannot show up in the final array
		String[][] notAllowed = {{"x1", "y1"}, {"x2", "y2"}, {"x3", "y3"}, {"x4", "y4"}, {"x5", "y5"}, 
								 {"y1", "x1"}, {"y2", "x2"}, {"y3", "x3"}, {"y4", "x4"}, {"y5", "x5"}, 
								 {"x1", "x1"}, {"x2", "x2"}, {"x3", "x3"}, {"x4", "x4"}, {"x5", "x5"}, 
								 {"y1", "y1"}, {"y2", "y2"}, {"y3", "y3"}, {"y4", "y4"}, {"y5", "y5"}};
		
		
		//Calls the function to generate the first year matches
		String[][] firstMatches = getFirstMatches(pairs1, pairs2, notAllowed);
		
		//Calls the function to generate the second year matches
		String[][] secondMatches = getSecondMatches(notAllowed, firstMatches, pairs1, pairs2);
		
		//Will print the matches to the console (and who will be buying for who)
		printToConsole(firstMatches, secondMatches);
						
	}
	
	
	
	//Function to get first year matches
	public static String[][] getFirstMatches(String[] pairs1, String[]pairs2, String[][] notAllowed)
	{
		
		//Convert the 2 strings above (pairs1 and pairs2) into an array list of strings
		//(which means we can remove elements with the "import java.util.ArrayList" functions
		ArrayList<String> couples1 = new ArrayList<String>(Arrays.asList(pairs1));
		ArrayList<String> couples2 = new ArrayList<String>(Arrays.asList(pairs2));
				
		
		//Initialise a 2D array for people to be stored in (when we know who is buying for who)
		//e.g. x2 is buying for y3 it will be [x2, y3]
		String[][] matches = new String[10][2];
		
				
		int i = 0;
		//Create a random number from 0 to the length of the array
		int ran = pairs1.length;
		//Use the random function
		Random random = new Random();
		//For the 2D array, make sure all the 0th elements are filled with people who will be buying
		//for other people (e.g. [[x1, null], [y4, null], ...]
		while(couples1.size() != 0)
		{
			
			//Create a random number from 0 to the length of the array
			int r = random.nextInt(ran);
			
			//To loop through the 10 different spaces available in firstMatches[i][]
			i = i % 10;
			matches[i][0] = couples1.get(r);
			//Remove the element you stored in the array so you cannot store this element again
			couples1.remove(r);
			//To move to the next array in the double array
			i++;
			//So you do not generate a random number larger than available array elements
			ran--;
			
		}
		
		//To make sure the program does not have any empty prints (if the array, when it is populated, 
		//has an element that cannot occur the while loop will not stop)
		int tooMany = 0;
		//Set ran back to 10 as pairs1 is now empty
		ran = pairs2.length;
		//to now make sure all other elements are filled (the 1st elements so [][these ones])
		while(couples2.size() != 0)
		{
			
			//Create a random number from 0 to the length of the array
			int r = random.nextInt(ran);
			
			//To loop through the 10 different spaces available in firstMatches[i][]
			i = i % 10;
			
			//The random number is the index of the person, this sets that person
			//As the person getting the present pushing it into the firstMatches array
			matches[i][1] = couples2.get(r);
			
			//A variable is set to make sure that the correct elements are in the final array
			int count = 0;
			
			//Will loop through the notAllowed array to check if the final array elements meet
			//the criteria
			for(int j = 0; j < notAllowed.length; j++)
			{
				
				//This method is able to compare strings in java
				//If the number count does not reach 20 that means something from the array notAllowed
				//was present and that means that that instance does not work and needs to repeat
				if(Arrays.deepEquals(matches[i], notAllowed[j]) == false)	
				{
					count++;
				}
				
				//If the sub array (of the 2D array firstMatches) is good (not in the not Allowed array)
				//Then that element will be removed form the couples2 array so it is not repeated in the
				//final array. Otherwise a new element will be assigned and checked
				if(count == notAllowed.length)	
				{
					//Remove the element you stored in the array so you cannot store this element again
					couples2.remove(r);
					//To move to the next array in the double array
					i++;
					//So you do not generate a random number larger than available array elements
					ran--;
				}
				
			}
			
			//Increment too many
			tooMany++;
			
			//If too many attempts occur and you cannot leave the while loop, the 
			//you call the function again
			if(tooMany == 30)
			{
				//matches (the thing to be returned is not re-calculated)
				matches = getFirstMatches(pairs1, pairs2, notAllowed);
				//To exit out of the function
				return matches;
			}
			
			
		}
		
		//Returns the array of the matches for year 1
		return matches;
	}
	
	
	//Function to get second year matches
	public static String[][] getSecondMatches(String[][] notAllowed, String[][] firstMatches, String[] pairs1, String[]pairs2)
	{
				
		//Sets variables for lengths of the first year matches and the not allowed array 
		int n = notAllowed.length;
		int m = firstMatches.length;
		
		//Initialise a 2D array for people to be stored in (when we know who is buying for who)
		//e.g. x2 is buying for y3 it will be [x2, y3] for the 1st year
		String[][] notAllowedNew = new String[n + m][2];
		
		//Fills the array with the notAllowed elements
		for(int k = 0; k < n; k++)
		{
			notAllowedNew[k] = notAllowed[k];
		}
		
		//Initialised so the array for firstMatches can start at 0
		int o = 0;
		//Fills the array with the last years (firstMatches) matches so they cannot be repeated
		for(int l = n; l < m+n; l++)
		{
			notAllowedNew[l] = firstMatches[o];
			//integer o is incremented so firstMatches can be looped through
			o++;
		}
		
		//Returns the array of the matches for year 2
		return getFirstMatches(pairs1, pairs2, notAllowedNew);
		
	}
	
	
	//Function to print the 1st and 2nd year matches (and which person will be buying for who) 
	public static void printToConsole(String[][] firstMatches, String[][] secondMatches)
	{
		//Prints the title for 1st year's draw to the console
		System.out.println("First year's draw: ");
		System.out.println("--------------");
		//Loops through the 1st year matches and prints who will be buying for who
		for(var i = 0; i < firstMatches.length; i++)
		{
			System.out.println(firstMatches[i][0] + " buys for " + firstMatches[i][1]);
		}
		
		//Makes a space between the 2 years
		System.out.println("");
		System.out.println("");
		
		//Prints the title for 2nd year's draw to the console
		System.out.println("Second year's draw: ");
		System.out.println("--------------");
		//Loops through the 2nd year matches and prints who will be buying for who
		for(var j = 0; j < secondMatches.length; j++)
		{
			System.out.println(secondMatches[j][0] + " buys for " + secondMatches[j][1]);
		}
		
	}
	
}






