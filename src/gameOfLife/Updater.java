package gameOfLife;


public abstract class Updater {
	public static Cell[][] CheckGrid(Cell[][] Grid, RuleSet rules) {
		for (int i = 0; i < Grid.length; i++) {
			for (int j = 0; j < Grid[0].length; j++) {
				Grid[i][j].checkCell(Grid, rules);
			}
		}
		return Grid;
	}
	
	public static Cell[][] UpdateGrid(Cell[][] Grid, RuleSet rules) {
		Grid = CheckGrid(Grid, rules);
		for (int i = 0; i < Grid.length; i++) {
			for (int j = 0; j < Grid[0].length; j++) {
				Grid[i][j] = UpdateCell(Grid[i][j]);
			}
		}
		return Grid;
	}
	
	private static Cell UpdateCell(Cell c) { //Updates a cell, a dead cell for live or vice versa
		if (c instanceof LiveCell) {
			c = (((LiveCell) c).isDying()) ? new DeadCell(c) : c;
		}
		else if (c instanceof DeadCell) {
			c = (((DeadCell) c).isBorn()) ? new LiveCell(c) : c;
		}
		return c;
	}
}
