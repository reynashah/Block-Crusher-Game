// Start of Gamefunctions.java class
package blockCrushergame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.Timer;

import javax.swing.JPanel;

	public class Gamefunctions extends JPanel implements KeyListener, ActionListener {
		public static boolean play = false;
		private int score = 0;
		private int blocks = 28;
		private Timer balltimer;
		private int delay = 1;
		private int column = 7;
	
		private int player = 310;

		private int ballMove1 = 120;
		private int ballMove2 = 350;
		private int dirBall1 = -1;
		private int dirBall2 = -2;
		private static final Color LIGHT_RED = new Color(255,120,112);
		private static final Color LIGHT_GREEN = new Color(152,251,152);
		private static final Color LIGHT_YELLOW = new Color(255,255,153);
		private static final Color LIGHT_PURPLE = new Color(221,160,221);

		private Layoutmap gameboard;	
		// initialize all variables
		
	public Gamefunctions() {
		gameboard = new Layoutmap(4, column); 
		// call to Layoutmap
		addKeyListener(this);
		// for moving the paddle
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		balltimer = new Timer(delay, this);
		balltimer.start();
		timecount.time();
		// one timer for the game and one for the ball speed
	}

	public void paint (Graphics g) {
		//background
		g.setColor(Color.black);
		g.fillRect(1, 1, 692, 592);
	
		//map drawing
		gameboard.draw((Graphics2D)g);
		
		//timer drawing
		timecount.drawtime((Graphics2D)g);
	
		//score
		g.setColor(Color.LIGHT_GRAY);
		g.setFont(new Font("sans_serif", Font.BOLD, 25));
		g.drawString(""+score, 590, 40);
		
		//title
		g.setColor(Color.BLUE);
		g.setFont(new Font("roman_baseline", Font.BOLD, 40));
		g.drawString("BLOCK CRUSHER", 175, 40);
		
		//title2
		g.setColor(LIGHT_RED);
		g.setFont(new Font("roman_baseline", Font.BOLD, 40));
		g.drawString("BLOCK CRUSHER", 175, 43);
		
		//title3
		g.setColor(LIGHT_YELLOW);
		g.setFont(new Font("roman_baseline", Font.BOLD, 40));
		g.drawString("BLOCK CRUSHER", 175, 46);
	
		//border
		g.setColor(LIGHT_GREEN);
		g.fillRect(1, 30, 5, 700);
		g.fillRect(0, 0, 692, 4);
		g.fillRect(681, 0, 7, 592);
		g.fillRect(0, 559, 692, 7);

		// the paddle
		g.setColor(LIGHT_PURPLE);
		g.fillRect(player,  550,  100,  8);
	
		// the ball
		g.setColor(Color.PINK);
		g.fillOval(ballMove1,  ballMove2,  20,  20);
		
		//when there are no more blocks, the game must end (the player won)
		if(blocks <= 0) {
			play = false;
			dirBall1 = 0;
			dirBall2 = 0;
			g.setColor(Color.GREEN);
			g.setFont(new Font("times new roman", Font.BOLD, 30));
			g.drawString("YOU WON!!", 190, 300);
			g.setFont(new Font("times new roman", Font.BOLD, 25));
			g.drawString("Score: "+score, 275, 335);
			g.setColor(Color.LIGHT_GRAY);
			g.setFont(new Font("serif", Font.BOLD, 20));
			g.drawString("Hit Enter to play again", 235, 360);
			timecount.secondscount = 0;
			// the stopwatch must reset to 0 for the new game
		}

		// when the ball goes out of the screen, the game must end (the player lost)
		if(ballMove2 > 570) {
			play = false;
			dirBall1 = 0;
			dirBall2 = 0;
			g.setColor(Color.RED);
			g.setFont(new Font("times new roman", Font.BOLD, 30));
			g.drawString("Game Over! You lost :(", 190, 300);
			g.setFont(new Font("times new roman", Font.BOLD, 25));
			g.drawString("Score: "+score, 275, 335);
			g.setColor(Color.LIGHT_GRAY);
			g.setFont(new Font("serif", Font.BOLD, 20));
			g.drawString("Hit Enter to try again", 235, 360);
			timecount.secondscount = 0;
			// the stopwatch must reset to 0 for the new game
		}
		if(column > 11) { //the program must terminate after 5 levels are over
			dirBall1 = 0;
			dirBall2 = 0;
			g.setColor(Color.RED);
			g.setFont(new Font("roman_baseline", Font.BOLD, 30));
			g.drawString("MAX number of blocks reached", 120, 250);
			g.drawString("Rerun program to play again", 170, 285);
			timecount.secondscount = 0;
		}
		if(column == 7) { // level 1
			g.setColor(Color.orange);
			g.setFont(new Font("roman_baseline", Font.BOLD, 15));
			g.drawString("LEVEL 1", 8, 20);
		}
		if(column == 8) { // level 2
			g.setColor(Color.orange);
			g.setFont(new Font("roman_baseline", Font.BOLD, 15));
			g.drawString("LEVEL 2", 8, 20);
		}
		if(column == 9) { // level 3
			g.setColor(Color.orange);
			g.setFont(new Font("roman_baseline", Font.BOLD, 15));
			g.drawString("LEVEL 3", 8, 20);
		}
		if(column == 10) { // level 4
			g.setColor(Color.orange);
			g.setFont(new Font("roman_baseline", Font.BOLD, 15));
			g.drawString("LEVEL 4", 8, 20);
		}
		if(column == 11) { // level 5
			g.setColor(Color.orange);
			g.setFont(new Font("roman_baseline", Font.BOLD, 15));
			g.drawString("LEVEL 5", 8, 20);
		}
		//from grpahics library to dispose all resources being used
		g.dispose();
	}
	
	// procedure for detecting when the ball intersects a block and removing the block
	//event handler interface from java
	// imported library
	@Override
	public void actionPerformed(ActionEvent e) {
		balltimer.start();
		// start the timer for the ball speed delay
		if(play) {
			if(new Rectangle(ballMove1, ballMove2, 20, 20).intersects(new Rectangle(player, 550, 100, 8))) {
				dirBall2 = -dirBall2;
			}
			//detects the ball intersection with block and the ball bounces off
			
			A: for(int i = 0; i < gameboard.map.length; i++) { 
				for(int j = 0; j < gameboard.map[0].length; j++) {
					if(gameboard.map[i][j] > 0) {   // performs all the code in the if statement once for each block
						int block1 = j * gameboard.blockWidth + 80; // four corners of each block
						int block2 = i * gameboard.blockHeight + 50;
						int blockWidth = gameboard.blockWidth;
						int blockHeight = gameboard.blockHeight;

						Rectangle rectangle = new Rectangle(block1, block2, blockWidth, blockHeight); // one rectangle for each of the blocks
						Rectangle ballRectangle = new Rectangle(ballMove1, ballMove2, 20, 20); // creates a rectangle for the ball(easier to detect intersection)
						Rectangle blockRectangle = rectangle;

						if(ballRectangle.intersects(blockRectangle)) {
							gameboard.setBlockValue(0, i, j); //calls a procedure from Layoutmap class
							blocks--; // take out block
							score+= 5; // add to score

							if(ballMove1 + 19 <= blockRectangle.x || ballMove1 + 1 >= blockRectangle.x + blockRectangle.width) {
								dirBall1 = -dirBall1; //bounce
							}
							else {
								dirBall2 = -dirBall2; //bounce
							}
							break A;
						}
					}
				}
			}
			
			ballMove1+= dirBall1;
			ballMove2+= dirBall2;
			// ball bounces off the walls
			if(ballMove1 < 0) {
				dirBall1 = -dirBall1; //bounce left wall
			}
			if(ballMove2 < 0) {
				dirBall2 = -dirBall2; //bounce top wall
			}
			if(ballMove1 > 670) {
				dirBall1 = -dirBall1; //bounce right wall
			}
		}
		repaint();
		// redo gameboard after a block is crushed to make the new arrangement of blocks
	}
		
	
	@Override 
	public void keyTyped(KeyEvent e) { }
	//necessary for the keylistener interface
	
	@Override 
	public void keyReleased(KeyEvent e) { }
	// necessary for the keylistener interface

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
			if(player >= 600) {
				player = 600; // paddle should only move if its not hitting a wall
			}
			else {
				//this will move the paddle right when the right arrow key is pressed
				moveRight();
				//calls the moveRight procedure 
			}
		}
		if(e.getKeyCode() == KeyEvent.VK_LEFT) {
			if(player < 10) {
				player = 10; // paddle should only move if its not hitting a wall
			}
			else {
				//this will move the paddle right when the left arrow key is pressed
				moveLeft();
				//calls the moveLeft procedure
			}
		}
			 
	if(e.getKeyCode() == KeyEvent.VK_ENTER) {
		if (!play) {
			// resets the game if the player wants to play again
			timecount.secondscount = 0;
			ballMove1 = 120; // puts ball in correct spot
			ballMove2 = 350;
			dirBall1 = -1;
			dirBall2 = -2;
			player = 310; //paddle
			score = 0;
			column++;
			blocks = 4*column;
			
			
			
			gameboard = new Layoutmap(4,column); 
			// call Layoutmap

			repaint();
			// from the graphics library for redrawing the gameboard
			}
		}
	}
	
	public void moveRight() {
		play = true;
		player+=20;
		// the paddle will move to the right 20 increments
	}

	public void moveLeft() {
		play = true;
		player-=20;		// the paddle will move to the left 20 increments
	}
}