// Start of timecount.java class
package blockCrushergame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JPanel;

public class timecount extends JPanel {
		private static final Color LIGHT_BLUE = new Color(135,190,255);
		static int secondscount; // keeps track of how long the player has been plays that level
		public static void time() {
			Timer timer = new Timer(); //new timer
			timer.schedule(new TimerTask() {  //new timertask, which is adding a to secondscount every second
			  @Override
			  public void run() {
				  // +1 to secondscount for every second the timer runs
				  secondscount++;
			  }
			  // one second
			}, 0, 1000);
		}
		
		public static void drawtime (Graphics g) {
			//timer	drawing to put into Gamefunctions class	
			g.setColor(LIGHT_BLUE);
			g.setFont(new Font("roman_baseline", Font.BOLD, 20));
			g.drawString("" + timecount.secondscount, 75, 40);
		}
	}