/*
 * Created on Dec 1, 2004
 * Last update: June 24, 2010
 */

import java.awt.Color;
import java.awt.Graphics;

public class Cell {
	private int myX, myY; // x,y position on grid
	private boolean myAlive; // alive (true) or dead (false)
	private int myNeighbors; // count of neighbors with respect to x,y
	private boolean myAliveNextTurn; // Used for state in next iteration
	private Color myColor; // Based on alive/dead rules
	private final Color DEFAULT_ALIVE = Color.MAGENTA;
	private final Color DEFAULT_DEAD = Color.GRAY;

	public Cell(int x, int y) {
		this(x, y, false, Color.GRAY);
	}

	public Cell(int row, int col, boolean alive, Color color) {
		myAlive = alive;
		myColor = color;
		myX = col;
		myY = row;
	}

	public boolean getAlive() {
		return myAlive;
	}

	public int getX() {
		return myX;
	}

	public int getY() {
		return myY;
	}

	public Color getColor() {
		return myColor;
	}

	public void setAlive(boolean alive) {
		if (alive) {
			setAlive(true, DEFAULT_ALIVE);
		} else {
			setAlive(false, DEFAULT_DEAD);
		}
	}

	public void setAlive(boolean alive, Color color) {
		myColor = color;
		myAlive = alive;
	}

	public void setAliveNextTurn(boolean alive) {
		myAliveNextTurn = alive;
	}

	public boolean getAliveNextTurn() {
		return myAliveNextTurn;
	}


	public void setColor(Color color) {
		myColor = color;
	}

	public int getNeighbors() {
		return myNeighbors;
	}

public void calcNeighbors(Cell[][] cell) {
	
		//wraparound for all sides of cell
		
		int rightcol = ((myY + 100) +1)%100;
		int leftcol = ((myY + 100) - 1)%100;
		int bottomrow =  ((myX + 80) - 1)%80;
		int toprow = ((myX + 80) +1)%80;
		 //checks top row for alive
		for(int i = 0;i< 3;i++){
			//things with % tries to do wraparound	
			if(cell[toprow][((myY + 100 + i) - 1)%100].myAlive == true){
				myNeighbors ++;
			}
		}
		//checks bottom row for alive
		for(int i = 0;i< 3;i++){
			if(cell[bottomrow][((myY + 100 + i) - 1)%100].myAlive == true){
				myNeighbors ++;
			}
		}
		//checks remaining side squares for alive
		
		if(cell[myY][leftcol].myAlive == true){	//not sure if this will overcount the diagonals (neighbors 1, 3, 6, 8)
			/* |1 2 3|
			 * |4 x 5|
			 * |6 7 8|
			 */
			myNeighbors ++;
		}
		
		
		if(cell[myY][rightcol].myAlive == true){
			myNeighbors ++;
		}

		//prints neighbors to check
		//System.out.println("neighbors" + myNeighbors);
		

	}

	
	public void willIBeAliveNextTurn(){
		//enter this sequence if cell is dead (not alive)
		if(myAlive == false){
			if (myNeighbors == 2 || myNeighbors == 3){
				setAliveNextTurn(true);
			}
	
		}
		if(myAlive == true){
			if (myNeighbors == 2 || myNeighbors == 3){
				setAliveNextTurn(true);
			}

			else {
				setAliveNextTurn(false);
			}
		
		}
				//repaint();
	}
	
	public void draw(int x_offset, int y_offset, int width, int height,
			Graphics g) {
		// I leave this understanding to the reader
		int xleft = x_offset + 1 + (myX * (width + 1));
		int xright = x_offset + width + (myX * (width + 1));
		int ytop = y_offset + 1 + (myY * (height + 1));
		int ybottom = y_offset + height + (myY * (height + 1));
		Color temp = g.getColor();

		g.setColor(myColor);
		g.fillRect(xleft, ytop, width, height);
	}
}

