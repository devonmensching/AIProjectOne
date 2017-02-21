import java.util.ArrayList;

/*
 * Node.java 
 * The node class is designed to encapsulate all the information about 
 * a node in the search graph for the problem. It is intended to be 
 * completely problem independent – any domain specific information 
 * should be in the state class.
 * 
 * by Devon Mensching & Nick Polanco 
 * 
 */

public class Node {
	
	private Node parent;
	private ArrayList<Node> children;
	private int[][] puzzle;
	private int heuristic;
	
	public Node( int[][] puzzle )
	{
		this.parent = null;
		this.children = null;
		this.puzzle = puzzle;
		this.heuristic = 0;
	}
	
	public Node( Node parent, int[][] puzzle )
	{
		this.parent = parent;
		this.children = null;
		this.puzzle = puzzle;
		this.heuristic = 0;
	}
	
	public Node( Node parent, int[][] puzzle, int heuristic )
	{
		this.parent = parent;
		this.children = null;
		this.puzzle = puzzle;
		this.heuristic = 0;
	}
	
	// setParent( Node parent ) - sets the parent Node
	public void setParent( Node parent )
	{
		this.parent = parent;
	}
	
	// getParent( ) returns the parent Node
	public Node getParent( )
	{
		return parent;
	}
	
	// ( ArrayList<Node> children ) - sets the children Nodes
	public void setChildren( ArrayList<Node> children )
	{
		this.children = children;
	}
	
	// getChildren( ) - returns the children Nodes
	public ArrayList<Node> getChildren( )
	{
		return children;
	}
	
	// setPuzzle( int[][] puzzle ) - sets the eight-puzzle
	public void setPuzzle( int[][] puzzle )
	{
		this.puzzle = puzzle;
	}
	
	// getPuzzle( ) - returns the eight-puzzle
	public int[][] getPuzzle( )
	{
		return puzzle;
	}
	
	public void setHeuristic( int heuristic)
	{
		this.heuristic = heuristic;
	}
	
	public int getHeuristic( )
	{
		return heuristic;
	}
	
	// findEmpty( ) - returns the empty position in the eight-puzzle
	public int[] findEmpty( )
	{
		int[] position = new int[2];
		for( int i = 0; i < 3; i++ )
		{
			for( int j = 0; j < 3; j++ )
			{
				if( puzzle[i][j] == 0 )
				{
					position[0] = i;
					position[1] = j;
					return position;
				}
			}
		}
		return null;
	}
	
	// printNode( ) - prints out the eight-puzzle
	public void printNode( )
	{
		for( int i = 0; i < 3; i++ )
		{
			for(int j = 0; j < 3; j++ )
			{
				System.out.print(puzzle[i][j]);
			}
			System.out.println();
		}
	}
	
	// moveRight( int x, int y ) - create a child node where a piece is moved right
	public Node moveRight( int x, int y )
	{
		if( y < 2 )
		{   
			int[][] newPuzzle = new int[3][3];
			for( int i = 0; i < 3; i++ )
			{
				for(int j = 0; j < 3; j++ )
				{
					newPuzzle[i][j] = puzzle[i][j];
				}
			}
			int temp = newPuzzle[x][y];
			newPuzzle[x][y] = newPuzzle[x][y+1];
			newPuzzle[x][y+1] = temp;
			Node newNode = new Node(this, newPuzzle, calcuateHeuristic(newPuzzle));

			return newNode;
		}
		return null;
	}

	// moveLeft( int x, int y ) - create a child node where a piece is moved left
	public Node moveLeft( int x, int y )
	{
		if( y > 0 )
		{
			int[][] newPuzzle = new int[3][3];
			for( int i = 0; i < 3; i++ )
			{
				for(int j = 0; j < 3; j++ )
				{
					newPuzzle[i][j] = puzzle[i][j];
				}
			}
			int temp = newPuzzle[x][y];
			newPuzzle[x][y] = newPuzzle[x][y-1];
			newPuzzle[x][y-1] = temp;
			Node newNode = new Node(this, newPuzzle, calcuateHeuristic(newPuzzle));
			return newNode;
		}
		return null;
	}
	
	// movUp( int x, int y ) - create a child node where a piece is moved up
	public Node moveUp( int x, int y )
	{
		if( x > 0 )
		{
			int[][] newPuzzle = new int[3][3];
			for( int i = 0; i < 3; i++ )
			{
				for(int j = 0; j < 3; j++ )
				{
					newPuzzle[i][j] = puzzle[i][j];
				}
			}
			int temp = newPuzzle[x][y];
			newPuzzle[x][y] = newPuzzle[x-1][y];
			newPuzzle[x-1][y] = temp;
			Node newNode = new Node(this, newPuzzle, calcuateHeuristic(newPuzzle));
			return newNode;
		}
		return null;
	}
	
	// MovDown( int x, int y ) -Create a child node where a piece is moved down
	public Node moveDown( int x, int y )
	{
		if( x < 2 )
		{
			int[][] newPuzzle = new int[3][3];
			for( int i = 0; i < 3; i++ )
			{
				for(int j = 0; j < 3; j++ )
				{
					newPuzzle[i][j] = puzzle[i][j];
				}
			}
			int temp = newPuzzle[x][y];
			newPuzzle[x][y] = newPuzzle[x+1][y];
			newPuzzle[x+1][y] = temp;
			Node newNode = new Node(this, newPuzzle, calcuateHeuristic(newPuzzle));
			return newNode;
		}
		return null;
	}
		
	// findChildren( ) - returns an ArrayList of the children of the node 
	public ArrayList<Node> findChildren( )
	{
		ArrayList<Node> newChildren = new ArrayList<Node>();
		int[] empty = findEmpty();
		int x = empty[0];
		int y = empty[1];
		Node right = moveRight( x, y );
		Node left = moveLeft( empty[0], empty[1] );
		Node up = moveUp( empty[0], empty[1]);
		Node down = moveDown( empty[0], empty[1]);
		if( right != null )
		{
			newChildren.add( right );
		}
		if( left != null )
		{
			newChildren.add( left );
		}
		if( up != null )
		{
			newChildren.add( up );
		}
		if( down != null )
		{
			newChildren.add( down );
		}
		return newChildren;
	}
	
	public int calcuateHeuristic( int[][] puzzle )
	{
		int[][] end = new int[][]{{1,2,3},{8,0,4},{7,6,5}};
		
		int count = 0;
		
		for(int i = 0; i < 3; i++)
		{
			for(int j = 0; j < 3; j++)
			{
				if( end[i][j] != puzzle[i][j] )
				{
					count++;
				}
			}
		}
		
		return count;
	}
}
