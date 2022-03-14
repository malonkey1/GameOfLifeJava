package gameOfLife;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;


public class FXMLController {
	@FXML
	private TextField tfMax;
	@FXML
	private TextField tfMin;
	@FXML
	private TextField tfBirth;
	@FXML
	private CheckBox cbLH;
	@FXML
	private CheckBox cbLV;
	@FXML
	private Button btnUpdate;
	@FXML
	private Button btnStopStart;
	@FXML
	private Button btnStartOver;
	private boolean running;
	@FXML
	private Canvas canvas;
	
	private RuleSet rules = new RuleSet();
	private Cell[][] Grid = new Cell[100][100];
	
	private Runnable updateGrid = new Runnable() {
		public void run() {
			Grid = Updater.UpdateGrid(Grid, rules);
			drawGrid(Grid, canvas);
		}
	};
	
	private ScheduledExecutorService es;
	
	
	
	public FXMLController() {
		
	}

	@FXML
	private void initialize() {
		//Set rules inputs to match the initial ruleset
		Grid = randomGrid(100);
		tfMax.setText(String.valueOf(rules.max()));
		tfMin.setText(String.valueOf(rules.min()));
		tfBirth.setText(String.valueOf(rules.birth()));
		cbLH.setSelected(rules.loopH());
		cbLV.setSelected(rules.loopV());
		running = false;
		btnStopStart.setText("Start");
		
		drawGrid(Grid, canvas);
		
	}

	@FXML
	private void UpdateRuleset() {
		if (running) {return;}//does nothing if the simulation is running. the button should already be disabled, but safety first!
		int newMax = (tfMax.getText().isEmpty())?0:Integer.parseInt(tfMax.getText());
		int newMin = (tfMin.getText().isEmpty())?0:Integer.parseInt(tfMin.getText());
		int newBirth = (tfBirth.getText().isEmpty())?0:Integer.parseInt(tfBirth.getText());
		boolean newLH = cbLH.isSelected();
		boolean newLV = cbLV.isSelected();
		rules.UpdateRules(newMax,newMin,newBirth,newLH,newLV);
	}
	
	private Cell[][] randomGrid(int d) {
		boolean live = false;
		Cell[][] g = new Cell[d][d];
		for (int i = 0; i < d; i++) {
			for (int j = 0; j < d; j++) {
				live = ((int)(Math.random()*100) >= 75);
				g[i][j] = live ? new LiveCell(i,j) : new DeadCell(i,j);
			}// Math.round(Math.random*255)
		}
		return g;
	}
	
	public static void drawCell (int x, int y, Canvas canvas, Color c, boolean live) {
		GraphicsContext g = canvas.getGraphicsContext2D();
		g.setFill(c);
		g.fillRect(x*4, y*4, 4, 4);
		System.out.println("Drew "+(live?"live":"dead")+" cell at "+x+", "+y);
	}
	
	private static void drawGrid (Cell[][] g, Canvas c) {
		c.getGraphicsContext2D().clearRect(0, 0, c.getWidth(), c.getHeight());
		for (int i = 0; i < g.length; i++) {
			for (int j = 0; j < g[0].length; j++) {
				drawCell(g[i][j].x, g[i][j].y, c, g[i][j].getColors(),g[i][j] instanceof LiveCell);
			}
		}
	}
	
	@FXML
	private void StartStop() {
		running = !running;
		if (running) {//Starting the scheduled service to update by generation
			es = Executors.newScheduledThreadPool(1);
			es.scheduleAtFixedRate(updateGrid, 1, 1, TimeUnit.SECONDS);
			btnStopStart.setText("Stop");
		} else {//Stop generations
			es.shutdown();
			btnStopStart.setText("Start");
		}
		btnUpdate.setDisable(running);
		btnStartOver.setDisable(running);
	}
	
}
