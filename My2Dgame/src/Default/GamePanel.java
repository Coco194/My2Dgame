package Default;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import entity.Player;
import tile.TileManager;

public class GamePanel extends JPanel implements Runnable{ //panel is what is inside the border 
	final int originalTileSize = 16; // 16x16 tile
	final int scale = 3; // scaling the 16x16 character by 3x
	
	public final int tileSize = originalTileSize * scale; // 48x48 tile
	public final int maxScreenCol = 16;
	public final int maxScreenRow = 12;
	public final int screenWidth = tileSize * maxScreenCol; //760 pixels
	public final int screenHeight = tileSize * maxScreenRow; //576 pixels
	
	// FPS
	int FPS = 60;
	
	//instantiate KeyHandler object
	KeyHandler keyH = new KeyHandler();
	Thread gameThread;	
	Player player = new Player(this, keyH);
	TileManager tileM = new TileManager(this);
	
	
	public GamePanel() {
		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true);
		this.addKeyListener(keyH);
		this.setFocusable(true);
	}

	
	public void startgameThread() {
		gameThread = new Thread(this);
		gameThread.start();		
	}
	
	
	// Game loop: Process input -> update character -> render -> delay(sleep method called)
	// Thread implemented to keep the game "running" meaning implement existence of time 
	// 1 Update: update information such as character positions
	// 2 Draw: draw the screen with the updated information

	
	@Override
	public void run() {
		double drawInterval = 1000000000/FPS;
		double delta = 0;
		long lastTime = System.nanoTime();
		long currentTime;
		
		while(gameThread != null) {
			
			currentTime = System.nanoTime();
			
			delta += (currentTime - lastTime) / drawInterval;
			
			lastTime = currentTime;
			
			if(delta >= 1) {
				update();
				repaint();
				delta--;
			}
		}
	}
	
	
	public void update() {
		
		player.update();

	}
	
	//function that draws the characters etc on the screen, it uses java graphics glass to draw it
	public void paintComponent(Graphics g) { 
		super.paintComponent(g);
		
		//Changing graphics class to graphics 2D class to get more functionality
		Graphics2D g2 = (Graphics2D)g;
		
		tileM.draw(g2);
		player.draw(g2);
		
		g2.dispose();
	}
}






















