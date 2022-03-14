package gameOfLife;

import javafx.scene.paint.Color;
import java.util.ArrayList;

public class LiveCell extends Cell implements Colorizer {
	private boolean dying;

	public LiveCell() {
		super();
		dying = false;
	}
	
	public LiveCell(Cell prev) {
		//Mostly for replacing live cells with dead cells
		super(prev);
		dying = false;
		if (prev instanceof DeadCell) { this.colors = randColors(); }
		else if ( prev instanceof LiveCell) {this.colors = prev.colors;}
	}
	
	public LiveCell(int x, int y) {
		super(x,y);
		this.colors = randColors();
		dying = false;
	}
	
	public LiveCell(int x, int y, Color c) {
		this.x = x;
		this.y = y;
		this.colors = c;
		dying = false;
	}
	
	@Override
	public String toString() {
		return "Live Cell at coordinates "+this.x+","+this.y;
	}
	@Override
	public void checkCell(Cell[][] Grid, RuleSet rules) {
		checkDeath(Grid, rules);
	}
	
	//CheckDeath sets dying based on the number of adjacent living cells.
	public void checkDeath(Cell[][] Grid, RuleSet rules) {
		ArrayList<Cell> neighbors = getNeighbors(Grid, rules);
		int count = 0;
		for (int i = 0; i < neighbors.size(); i++) {
			count += (neighbors.get(i) instanceof LiveCell) ? 1 : 0;//iterate through neighbors, increment for live neighbors
		}
		this.dying = ( count > rules.max() || count < rules.min() ); //if living neighbors are more than maximum or less than minimum, cell is dying
		if (this.dying) {
			System.out.println("Live Cell at "+x+","+y+" will Die!");
		}
	}
	
	public boolean isDying() { return this.dying; }
}