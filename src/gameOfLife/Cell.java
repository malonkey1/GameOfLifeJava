package gameOfLife;

import javafx.scene.paint.Color;
import java.util.ArrayList;
import java.util.Random;

public abstract class Cell implements Colorizer {
	protected int x;
	protected int y;
	protected Color colors;
	
	Cell(){
		this.x = -1;
		this.y = -1;
		this.setColors(Color.BLACK);
	}
	
	Cell(Cell prev){
		this.x = prev.x;
		this.y = prev.y;
		randColors();
	}
	
	Cell(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	@Override
	public String toString() {
		return "Cell at coordinates "+this.x+","+this.y;
	}
	
	public void setColors(int r, int g, int b) {		
		this.colors = Color.rgb(//RGB values clamped between 0 and 255 inclusive
				Math.max(0, Math.min(255,r)),
				Math.max(0, Math.min(255,g)),
				Math.max(0, Math.min(255,b)));
	}
	
	public void setColors(Color color) {
		this.colors = color;
	}
	
	public Color randColors() {
		Random rand = new Random();
		int r = rand.nextInt(255);
		int g = rand.nextInt(255);
		int b = rand.nextInt(255);
		return Color.rgb(r,g,b);
	}
	
	public Color getColors() {
		return this.colors;
	}
	
	public void checkCell(Cell[][] Grid, RuleSet rules) {
		
	}
	
	//Getters
	public int getX() { return this.x; }
	public int getY() { return this.y; }
	
	public ArrayList<Cell> getNeighbors(Cell[][] Grid, RuleSet rules) {
		ArrayList<Cell> neighbors = new ArrayList<Cell>();
		int dx = Grid[0].length - 1;//Get number of columns;
		int dy = Grid.length - 1;//Get number of rows;
		// 0,0 is upper left
		// dx,0 is upper right
		// dx,dy is lower right
		// 0,dy is lower left
		//first check if it's not bordering an edge
		if (x == dx && y == dy) { //bottom right corner
			neighbors.add(Grid[x-1][y-1]);//upper left
			neighbors.add(Grid[x][y-1]);//upper middle
			neighbors.add(Grid[x-1][y]);//middle left
			if (rules.loopV()) {
				neighbors.add(Grid[x][0]);//lower middle
				neighbors.add(Grid[x-1][0]);//lower left
				}//wrap back to first row
			if (rules.loopH()) {
				neighbors.add(Grid[0][y]);//upper middle
				neighbors.add(Grid[0][y-1]);//upper right
				}//wrap back to first column
			if (rules.loopV() && rules.loopH()) { neighbors.add(Grid[0][0]); }//wrap row and column
		}
		else if (x == dx && y == 0) { //top right corner
			neighbors.add(Grid[x][y+1]);//lower middle
			neighbors.add(Grid[x-1][y+1]);//lower left
			neighbors.add(Grid[x-1][y]);//middle left
			if (rules.loopV()) {
				neighbors.add(Grid[x-1][dy]);//upper left
				neighbors.add(Grid[x][dy]);//upper middle
			}
			if (rules.loopH()) {
				neighbors.add(Grid[0][y]);//middle right
				neighbors.add(Grid[0][y+1]);//lower right
			}
			if (rules.loopV() && rules.loopH()) { neighbors.add(Grid[0][dy]); }
		}
		else if (x == 0 && y == dy) { //bottom left corner
			neighbors.add(Grid[x][y-1]);//upper middle
			neighbors.add(Grid[x+1][y-1]);//upper right
			neighbors.add(Grid[x+1][y]);//middle right
			if (rules.loopV()) {
				neighbors.add(Grid[x+1][0]);//lower right
				neighbors.add(Grid[x][0]);//lower middle
			}
			if (rules.loopH()) {
				neighbors.add(Grid[dx][y]);//middle left
				neighbors.add(Grid[dx][y-1]);//upper left
			}
			if (rules.loopV() && rules.loopH()) { neighbors.add(Grid[dx][0]); }
		}
		else if (x == 0 && y == 0) { //top left corner
			neighbors.add(Grid[x+1][y]);//middle right
			neighbors.add(Grid[x+1][y+1]);//lower right
			neighbors.add(Grid[x][y+1]);//lower middle
			if (rules.loopV()) {
				neighbors.add(Grid[x][dy]);//upper middle <vloop
				neighbors.add(Grid[x+1][dy]);//upper right <vloop
			}
			if (rules.loopH()) {
				neighbors.add(Grid[dx][y+1]);//lower left <hloop
				neighbors.add(Grid[dx][y]);//middle left <hloop
			}
			if (rules.loopV() && rules.loopH()) { neighbors.add(Grid[dx][dy]); }
		}
		else if (x == 0) {//left edge
			neighbors.add(Grid[x][y-1]);//upper middle
			neighbors.add(Grid[x+1][y-1]);//upper right
			neighbors.add(Grid[x+1][y]);//middle right
			neighbors.add(Grid[x+1][y+1]);//lower right
			neighbors.add(Grid[x][y+1]);//lower middle
			if (rules.loopH()) {
				neighbors.add(Grid[dx][y-1]);//upper left
				neighbors.add(Grid[dx][y]);//middle left
				neighbors.add(Grid[dx][y+1]);//lower left
			}
		}
		else if (x == dx) {//right edge
			neighbors.add(Grid[x-1][y-1]);//upper left
			neighbors.add(Grid[x][y-1]);//upper middle
			neighbors.add(Grid[x][y+1]);//lower middle
			neighbors.add(Grid[x-1][y+1]);//lower left
			neighbors.add(Grid[x-1][y]);//middle left
			if (rules.loopH()) {
				neighbors.add(Grid[0][y-1]);//upper right
				neighbors.add(Grid[0][y]);//middle right
				neighbors.add(Grid[0][y+1]);//lower right
			}
		}
		else if (y == 0) {//top edge
			neighbors.add(Grid[x+1][y]);//middle right
			neighbors.add(Grid[x+1][y+1]);//lower right
			neighbors.add(Grid[x][y+1]);//lower middle
			neighbors.add(Grid[x-1][y+1]);//lower left
			neighbors.add(Grid[x-1][y]);//middle left
			if (rules.loopV()) {
				neighbors.add(Grid[x-1][dy]);//upper left
				neighbors.add(Grid[x][dy]);//upper middle
				neighbors.add(Grid[x+1][dy]);//upper right
			}
		}
		else if (y == dy) {//bottom edge
			neighbors.add(Grid[x-1][y-1]);//upper left
			neighbors.add(Grid[x][y-1]);//upper middle
			neighbors.add(Grid[x+1][y-1]);//upper right
			neighbors.add(Grid[x+1][y]);//middle right
			neighbors.add(Grid[x-1][y]);//middle left
			if (rules.loopV()) {
				neighbors.add(Grid[x+1][0]);//lower right
				neighbors.add(Grid[x][0]);//lower middle
				neighbors.add(Grid[x-1][0]);//lower left
			}
		}
		else {//not on edge
			neighbors.add(Grid[x-1][y-1]);//upper left
			neighbors.add(Grid[x][y-1]);//upper middle
			neighbors.add(Grid[x+1][y-1]);//upper right
			neighbors.add(Grid[x+1][y]);//middle right
			neighbors.add(Grid[x+1][y+1]);//lower right
			neighbors.add(Grid[x][y+1]);//lower middle
			neighbors.add(Grid[x-1][y+1]);//lower left
			neighbors.add(Grid[x-1][y]);//middle left
		}
		
//		neighbors.forEach((cell) -> System.out.println("Neighbor"+cell));
		return neighbors;
	}
}
