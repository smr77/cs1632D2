//Steven M. Ross
//Smr77@pitt.edu


import java.util.*;
import java.io.*;

// Class that contains the various methods needed for the CitySim metrics
public class City
{
	private String[] cityLocations;		// inner city locations 
	private int driverC;				// Holds the current driver
	private Random ranGen;				//Holds the Random number generator
	private int driverTotal = 5;		//Sets the number of drivers allowed for the simulation 

	//Constructor for setting up the City Sim conditions
	public City(int seed)
	{
		//Set up the RNG
		ranGen = new Random(seed);
	
		//Set up the city locations
		String[] Locations = {"Mall", "Bookstore", "Coffee Shop", "University", "Outside City"};
		this.cityLocations = Locations;
		
		//Set up the driver index
		this.driverC = 0; 							
	}
	
	//Starts and runs the simulation
	public void StartSim()
	{
		int start; 					//The starting location
		int dest = -1 ;					//The destination location
		String road = "";					//**********
		boolean firstRun = true;	//Flag to signify that that 
		
		//Run through the City Sim with 5 drivers
		while(this.driverC < driverTotal)
		{
			start = GetStartLocation();			//Gets the starting location of the driver
			
			//Check to see if the starting location is "Outside City" and handles the case if it is
			if(firstRun == true & start == 4)
			{
				dest = getOutsideDest(start);		//Get the special case destination 			
				start = dest;
				firstRun = false;	
			}
			
			while( dest != 4)
			{
				dest = showRoute(start);
				start = dest;
			}
						
			printEnd(this.driverC);
			firstRun = true;
			this.driverC++;
			start = -1;
			dest = -1;
			
		}
		
		
	}
	
	
	
	//Deals with FUN-FIRST-LOC. Generates destinitation when a driver spawns outside of the city
	//Prints the route then returns the random destinitaion 
	public int getOutsideDest(int start)
	{
		int x;			//holds the rn to compare against the city list
		int result = -1;    //Holds the destinitaion to return 
		String road = "";
		
		x = ranGen.nextInt(2 - 0) + 0;
		
		if ( x == 0 )
		{
			result = 0;
		}
		
		else if( x == 1 )
		{
			result = 3;
		}
			
		if( result == 0)
		{
			road = "Fourth Ave.";
		}
		else if (result == 3)
		{
			road = "Fifth Ave.";
		}
		
		printRoute(this.driverC, 4, result ,road);
		
		return result; 
	}
	
	//Shows the route the driver can and will take. 
	public int showRoute(int start)
	{
		int destinitaion = -1;
		int typeRoad;
		String road = "";
		
		//Generates what type of road to use
		// i.e 0 = two ways, 1 = one way. 
		typeRoad = ranGen.nextInt(2 - 0) + 0; 
		
		switch (start)
        { 
			case 0: // Mall
				
				if( typeRoad == 0)
				{
					road = "Meow St.";
					destinitaion  = 2;
				}
				
				else if( typeRoad == 1)
				{
				
					road = "Fourth Ave.";
					destinitaion  = 1;
				}
				
				break;
            case 1: // BookStore
				if( typeRoad == 0)
				{
					road = "Chirp St.";
					destinitaion  = 3;
				}
				
				else if( typeRoad == 1)
				{
					road = "Fourth Ave.";
					destinitaion  = 4;
				}
				break;
				
			case 2: // Coffee Shop
				if( typeRoad == 0)
				{
					road = "Meow St.";
					destinitaion  = 0;
				}
				
				else if( typeRoad == 1)
				{
					road = "Fifth Ave.";
					destinitaion  = 4;
				}
                break;
				
            case 3: // University
				if( typeRoad == 0)
				{
					road = "Chirp St.";
					destinitaion  = 1;
				}
				
				else if( typeRoad == 1)
				{
				
					road = "Fifth Ave.";
					destinitaion = 2;
				}
				
				break;
					
			default:
				System.out.println("Unknown Error");
		}
			
		printRoute(this.driverC, start, destinitaion, road);
		
		return destinitaion;
	}
	
	
	//This method generates a random starting location for drivers based on the number of locations(5)
	//Returns an int representing the city placement in cityLocations 
	public int GetStartLocation()
	{
		return this.ranGen.nextInt(this.cityLocations.length - 0) + 0;
	}
	
	//Gets the city location names from an integer value corresponding to the cityLocations array
	public String GetLocationName(int index) 
	{
		return this.cityLocations[index];
	}
	
	//Prints the current route of the current driver
	public void printRoute(int driver, int startLoc, int destLoc, String road)
	{
		System.out.println("Driver " + driver + " heading from " + GetLocationName(startLoc) + " to " + GetLocationName(destLoc) + " via " + road);
	}
	
	//Prints text stating the current driver has left the city
	public void printEnd(int index)
	{
		System.out.println("Driver " + index + " has left the city!");
		System.out.println("------");
	}
	
	
	
	
}