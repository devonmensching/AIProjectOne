import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

/*
 *State.java
 * The state class is designed to encapsulate all the information 
 * about a particular state in the search problem. This means that 
 * all the specific information about the domain should be in this 
 * class.
 * 
 * by Devon Mensching & Nick Polanco 
 * 
 */

public class State {
	
	ArrayList<Node> open = new ArrayList<Node>();
	ArrayList<Node> closed = new ArrayList<Node>();
	ArrayList<Node> pathBFS = new ArrayList<Node>();
	int expandedNodesBFS = 0;
	
	// calculateBFS( Node startNode, Node endNode ) - calculates BFS
	public void calculateBFS( Node startNode, Node endNode )
	{
		// Generate a set of possible start states called open
		toOpen( startNode );
		Node currentNode = open.get( 0 );
		
		// Select and remove a state n from open 
		// If open is empty the search has failed
		// If node is goal state, the search has succeeded 
		while( !isEndNode( currentNode ) && open.size() != 0 )
		{
			// Generate all possible successors to n and place them into open
			generateChildren( currentNode );
			expandedNodesBFS++;
			
			// Check if current node is a member of closed  
			if( !isInClosed( currentNode ))
			{
				moveToClosed( currentNode );
			}
			
			open.remove( 0 );
			currentNode = open.get( 0 );
		}
		
		moveToClosed(currentNode);
		
		findPath( startNode );
		for(int i = pathBFS.size()-1; i > -1; i--)
		{
			pathBFS.get(i).printNode();
			System.out.println();
		}
		
	}
	
	// generateChildren( Node node ) - adds children of node to open
	public void generateChildren( Node node )
	{
		ArrayList<Node> children = node.findChildren();
		while( children.size() != 0 )
		{
			Node addChild = children.get( 0 );
			open.add( addChild );
			children.remove( 0 );
		}
	}

	// findPath( Node start ) - finds the path from start node to end node
	public void findPath( Node startNode )
	{
		Node currentNode = closed.get( closed.size() - 1 );
		pathBFS.add(currentNode);
		
		while( !isSameNode(startNode, currentNode ) )
		{
			Node parent = currentNode.getParent();
			pathBFS.add(parent);
			currentNode = parent;
		}
	}
	
	// toOpen( Node n ) - adds n to open
	public void toOpen( Node n )
	{
		open.add( n );
	}
	
	// moveToClosed( Node n ) - adds n to closed
	public void moveToClosed( Node n )
	{

		closed.add( n );
	}
	
	// isOpenEmpty( ) - returns true of open is empty
	public boolean isOpenEmpty( )
	{
		if( open.size() == 0 )
		{
			return true;
		}
		
		else
		{
			return false;
		}
	}
	
	// isInClosed( Node node ) - returns true if node is in closed  
	public boolean isInClosed( Node node )
	{
		for( int i=0; i < closed.size(); i++ )
		{
			if( Arrays.equals( closed.get( i ).getPuzzle() , node.getPuzzle() ))
			{
				return true;
			}
		}
			return false;
	}
	
	public boolean isEndNode( Node node)
	{
		int[][] end = new int[][]{{1,2,3},{8,0,4},{7,6,5}};
		int[][] n = node.getPuzzle();
		for(int i = 0; i < 3; i++)
		{
			for(int j = 0; j < 3; j++)
			{
				if(n[i][j] != end[i][j])
				{
					return false;
				}
			}
		}
		return true;
	}
	
	public boolean isSameNode( Node nodeOne, Node nodeTwo)
	{
		int[][] n1 = nodeOne.getPuzzle();
		int[][] n2 = nodeTwo.getPuzzle();
		for(int i = 0; i < 3; i++)
		{
			for(int j = 0; j < 3; j++)
			{
				if(n1[i][j] != n2[i][j])
				{
					return false;
				}
			}
		}
		return true;
	}
	
	// getPathNumber() -  returns the number of steps needed to take along the path 
	public int getPathNumber( )
	{
		return pathBFS.size();
	}
	
	// getExpandedNodesBFS() -  returns the number of nodes expanded for BFS
	public int getExpandedNodesBFS()
	{
		return expandedNodesBFS;
	}
	
}
