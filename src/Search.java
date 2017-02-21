import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

/*
 * Search.java
 * The search file contains the driver program and the search algorithm code. 
 * As mentioned before, you could write both algorithms in one file, 
 * or write a separate file for each algorithm.
 * 
 * by Devon Mensching & Nick Polanco 
 * 
 */



public class Search {	
	
	public static void main( String args[] ) 
	{
		// Prompt the user to enter a file name
		System.out.println("Please enter the file name: ");

		// Open file with the name matching user input 
		java.util.Scanner input = new java.util.Scanner(System.in);	
		String fileName = input.nextLine();
		File file = null;
		Scanner scanner = null;
		try
		{
			file = new File(fileName);
			scanner = new Scanner(new BufferedReader(new FileReader(file)));
		}
		catch(Exception e)
		{
			System.out.println("File not Found. Please run the program again.");
		}		
		input.close();	
		
		// Read through file & insert file contents into ArrayList
		ArrayList<Integer> firstInts = new ArrayList<Integer>();	
		while( scanner.hasNext() )
		{
			if( scanner.hasNextInt() )
			{
				firstInts.add(scanner.nextInt());
			}
			else
			{
				scanner.next();
			}
		}
				
		// Create 2D array with file contents 
		int[][] startState = new int[3][3];
		for( int i = 0; i < 3; i++ )
		{
			for( int j = 0; j < 3; j++ )
			{
				startState[i][j] = firstInts.get( 0 );
				firstInts.remove( 0 );
			}
		}
		Node startNode = new Node( startState );
		
		// Create end node
		int[][] end = new int[][]{{1,2,3},{8,0,4},{7,6,5}};
		Node endNode = new Node( end );
		
		
		// Print the initial state of the eight-puzzle
		System.out.println("The intial state of the puzzle is: ");
		startNode.printNode();
		System.out.println("----------------------------------------------------------");
			
		// Print the path of the states from initial to goal 
		System.out.println("The path of the states from the intial to goal is:");
		startNode.findChildren();
		State s = new State();
		s.calculateBFS(startNode, endNode);
		System.out.println("----------------------------------------------------------");
		
		// Print the number of steps needed to take along the path 
		System.out.println("The number of steps needed to take along the path is: " + s.getPathNumber());
		System.out.println("----------------------------------------------------------");
		
		
		// Print the total number of nodes expanded while doing the search 
		System.out.println("The total number of nodes exapnded during the search is: " + s.getExpandedNodesBFS());
		System.out.println("----------------------------------------------------------");
		
		System.out.println("Best First Search: ");
		s.calculateIS(startNode, endNode);
		
	}
}
