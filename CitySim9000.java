//Steven M Ross
//Smr77@pitt.edu

import java.io.*;
import java.util.*;

public class CitySim9000 
{
	public static void main(String[] args) throws IOException
	{
		int seed = 0;						//user inputed seed for rng
		
		//Get the random seed and error check the input
		seed = GetValidInput(args);
		
		//Set up the City simulation object
		City CitySim = new City(seed);
		
		//Startins the simulaion 
		CitySim.StartSim();
		
		//System.out.println(CitySim.GetStartLocation());
		
	}
	
	//Checks the command line input and returns the seed only if the input is valid
	//Throws errors based on FUN-ARGS
	public static int GetValidInput(String[] args)
	{
		int x = 0;
		
		//Check to make sure input is a single integer
		if(args.length > 1 || args.length < 1)
		{
			System.err.println("User input error. Please enter a single interger value");  
			System.exit(1);
		}
		//Check to make sure that the value is an integer
		else
		{
			try 
			{
				x = Integer.parseInt(args[0]);
			}
			catch (NumberFormatException e) 
			{
				System.err.println("Input (" + args[0] + ") must be an integer.");
				System.exit(1);
			}
		}
		
		return x;
	}
}