package Default;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable{ //panel is what is inside the border 
	final int originalTileSize = 16; // 16x16 tile
	final int scale = 3; // scaling the 16x16 character by 3x
	
	final int tileSize = originalTileSize * scale; // 48x48 tile
	final int maxScreenCol = 16;
	final int maxScreenRow = 12;
	final int screenWidth = tileSize * maxScreenCol; //760 pixels
	final int screenHeight = tileSize * maxScreenRow; //576 pixels
	
	public GamePanel() {
		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true);
	}

	Thread gameThread;
	
	
	public void startgameThread() {
		gameThread = new Thread(this);
		gameThread.start();		
	}
	
	@Override
	public void run() {
		// Thread implemented to keep the game "running" meaning implement existence of time 
		while(gameThread != null) {
			System.out.println("Game thread is running");
			
			//1 Update: update information such as character positions
			//2 Draw: draw the screen with the updated information
			
			update();
			repaint();
		}
	}
	
	public void update() {
		
	}
	
	//function that draws the characters etc on the screen, it uses java graphics glass to draw it
	public void paintComponent(Graphics g) { 
		super.paintComponent(g);
		
		//Changing graphics class to graphics 2D class to get more functionality
		Graphics2D g2 = (Graphics2D)g;
		
		g2.setColor(Color.white);
		g2.fillRect(200, 200, tileSize, tileSize);
		g2.dispose();
	}
}






















