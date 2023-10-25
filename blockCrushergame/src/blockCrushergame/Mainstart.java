// Start of Mainstart.java class
package blockCrushergame;

import javax.swing.JFrame;

public class Mainstart {

	public static void main(String[] args) {
		JFrame frame = new JFrame();
		Gamefunctions gamefunctions = new Gamefunctions();
		frame.setBounds(10, 10, 700, 600);
		frame.setTitle("Block Crusher");
		frame.setResizable(false);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(gamefunctions);
        frame.setLocationByPlatform(true);
		frame.add(new timecount());
	}
}