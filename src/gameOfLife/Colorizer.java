package gameOfLife;

import javafx.scene.paint.Color;

interface Colorizer {
	//Set colors based on integer color values
	void setColors(int r, int g, int b);
	//set colors based on passed-in color object
	void setColors(Color color);
	//Set to a random color;
	public Color randColors();
	//get colors, returns a Color object
	Color getColors();
}
