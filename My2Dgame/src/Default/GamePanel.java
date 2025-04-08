package Default;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;

public class GamePanel extends JPanel{ //panel is what is inside the border 
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
}
