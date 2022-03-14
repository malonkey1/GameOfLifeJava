package gameOfLife;

import javafx.scene.paint.Color;
import java.util.ArrayList;

public class DeadCell extends Cell implements Colorizer {
	private boolean born;
	
	public DeadCell() {
		// TODO Auto-generated constructor stub
		super();
		born = false;
	}
	
	public DeadCell(int x, int y){
		super(x,y);
		born = false;
		setColors(Color.BLACK);
	}
	
	public DeadCell(Cell prev) {
		super(prev);
		born = false;
		setColors(Color.BLACK);
	}
	
	@Override
	public String toString() {
		return "Dead Cell at coordinates "+this.x+","+this.y;
	}
	//Sets born based on adjacent living cells.
	@Override
	public void checkCell(Cell[][] Grid, RuleSet rules) {
		checkBirth(Grid, rules);
	}
	public void checkBirth(Cell[][] Grid, RuleSet Rules) {
		ArrayList<Cell> neighbors = getNeighbors(Grid, Rules);
		int count = 0;
		for (int i = 0; i < neighbors.size(); i++) {
			count += (neighbors.get(i) instanceof LiveCell) ? 1 : 0;//iterate through neighbors, increment for live neighbors
		}
		this.born = (count >= Rules.birth() && count <= Rules.max() );
		if (this.born) {
			System.out.println("Dead Cell at "+x+","+y+" will come to life!");
		}
	}
	
	public boolean isBorn() { return this.born; }

}
