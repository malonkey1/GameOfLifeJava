package gameOfLife;


public class RuleSet {
	private int maxNeighbors = 3;
	private int minNeighbors = 2;
	private int birthThreshold = 3;
	private boolean loopH = false;
	private boolean loopV = false;
	
	RuleSet(){}
	
	RuleSet(int max, int min, int birth, boolean loopH, boolean loopV){
		this.maxNeighbors = max;
		this.minNeighbors = min;
		this.birthThreshold = birth;
		this.loopH = loopH;
		this.loopV = loopV;
	}
	
	//called by the UI to update rules
	public void UpdateRules(int max, int min, int birth, boolean loopH, boolean loopV) {
		setMax(max);
		setMin(min);
		setBirth(birth);
		setLH(loopH);
		setLV(loopV);
	}
	private void setMax(int max) { this.maxNeighbors = max; }
	private void setMin(int min) { this.minNeighbors = min; }
	private void setBirth(int birth) { this.birthThreshold = birth; }
	private void setLH(boolean loopH) { this.loopH = loopH; }
	private void setLV(boolean loopV) { this.loopV = loopV; }
	
	public int max() { return this.maxNeighbors; }
	public int min() { return this.minNeighbors; }
	public int birth() { return this.birthThreshold; }
	public boolean loopH() { return this.loopH; }
	public boolean loopV() { return this.loopV; }
	@Override
	public String toString() {
		return "Maximum Neighbors: "+this.max()+", Minimum Neighbors: "+this.min()+", Birth Threshold: "+this.birth()+(this.loopH()?", Loops horizontally":"")+(this.loopV()?", Loops vertically":"");
	}
}
