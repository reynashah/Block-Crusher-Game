// Start of Layoutmap.java class
package blockCrushergame;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

	public class Layoutmap {
		public int blockWidth;
		public int blockHeight;
		
		public int map[][]; // 2D array for the formation of blocks (4,7)
		
		public Layoutmap(int row, int column) {
			map = new int[row][column];
		if (row > 0 && column > 0 && column < 12) {
			for(int i = 0; i < map.length; i++) {
				for(int j = 0; j < map[0].length; j++) {
					map[i][j] = 1; //when a block is on the board it has a value of 1 (when balls touches it changes to 0)
				}
			}				
		}
		else {
			Gamefunctions.play = false;
		}
		// if the amount of blocks is higher, each block will be smaller
			blockWidth = 540/column;  // size of each column
			blockHeight = 150/row;    // size of each row
		}
		// procedure for the layout of the map
		public void draw(Graphics2D g) {
			//apply draw(Graphics2D g) to the class Gamefunctions
			// nested loop to iterate through map 2D array
			for(int i = 0; i < map.length; i++) {
				for(int j = 0; j < map[0].length; j++) {
					if (map[i][j] > 0) {   //only draws if the value in map is 1
						g.setColor(Color.white);
						g.fillRect(j * blockWidth + 80,  i * blockHeight + 50, blockWidth, blockHeight);
						// draw the blocks
						
						g.setStroke(new BasicStroke(3));
						g.setColor(Color.black);
						g.drawRect(j * blockWidth + 80,  i * blockHeight + 50, blockWidth, blockHeight);
					}
				}
			}
		}
		public void setBlockValue(int value, int row, int col) { //changes map values if the block and ball intersect
			map[row][col] = value; // value is zero
			//changes value to represent that the blocks shouldn't be drawn
		}
	}