package gameOfLife;

// run as -> run configurations -> arguments
// --module-path "PATH\TO\JAVAFX\LIBS" --add-modules javafx.controls,javafx.fxml
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.*;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import java.io.FileInputStream;
import java.io.IOException;

public class Main extends Application {
	
	@Override
	public void start(Stage stage) throws Exception {
		//Create FXML loader
		FXMLLoader loader = new FXMLLoader();
		// path to FXML file
		String fxmlPath = "layout.fxml";
		FileInputStream fxmlStream = new FileInputStream(fxmlPath);
		
		//Create the pane and all details
		VBox root = (VBox) loader.load(fxmlStream);
		
		// create scene
		Scene scene = new Scene(root);
		// Set scene to stage
		stage.setScene(scene);
		// Set title
		stage.setTitle("Game of Life");
		// display
		stage.show();
	}
	
	public static void main(String[] args) {
		
		launch(args);
	}
}

//public class Main {
//	public static void main(String[] args) {
//		Cell[][] Grid = new Cell[6][6];
//		
//		TestCreateGrid(Grid);//Populate the grid
//		TestUpdateGrid(Grid, new RuleSet());//Update three times
//		TestUpdateGrid(Grid, new RuleSet());
//		TestUpdateGrid(Grid, new RuleSet());
//		
//		RuleSet hvwrap = new RuleSet(3,2,3,true,true);//The same test but with looping edges
//		
//		TestCreateGrid(Grid);//Populate the grid
//		TestUpdateGrid(Grid, hvwrap);//Update three times
//		TestUpdateGrid(Grid, hvwrap);
//		TestUpdateGrid(Grid, hvwrap);
//	}
//	
//	public static void TestCreateGrid(Cell[][] Grid) {
//		boolean live = false;
//		for (int i = 0; i < Grid.length; i++) {//iterate column
//			for (int j = 0; j < Grid[0].length; j++) { //iterate row
//				live = ((int)(Math.random()*10) >= 5);
//				Grid[i][j] = (live) ? new LiveCell(i,j) : new DeadCell(i,j);
//				System.out.print("["+((Grid[i][j] instanceof LiveCell)?"L":"D")+"]");
//			}
//			System.out.print("\n");
//		}
//		System.out.print("\n");
//	}
//	
//	public static void TestUpdateGrid(Cell[][] Grid, RuleSet rules) {
//		Grid = Updater.UpdateGrid(Grid, rules);
//
//		for (int i = 0; i < Grid.length; i++) {//iterate column
//			for (int j = 0; j < Grid[0].length; j++) { //iterate row
//				System.out.print("["+((Grid[i][j] instanceof LiveCell)?"L":"D")+"]");
//			}
//			System.out.print("\n");
//		}
//		System.out.print("\n");
//	}
//
//}
