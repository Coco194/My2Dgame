package tile;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import javax.imageio.ImageIO;
import Default.GamePanel;


public class TileManager {

	GamePanel gp;
	Tile[] tile;
	int mapTileNum[][];
	
	public TileManager(GamePanel gp) {
		this.gp = gp;
		
		tile = new Tile[10];
		mapTileNum = new int[gp.maxScreenCol][gp.maxScreenRow];
		
		getTileImage();
		loadMap("/maps/gameMap");
		
		
	}
	
	public void getTileImage() {
		try {
			
			tile[0] = new Tile();
			tile[0].image = ImageIO.read(getClass().getResourceAsStream("/tiles/grass.png"));
			
			tile[1] = new Tile();
			tile[1].image = ImageIO.read(getClass().getResourceAsStream("/tiles/wall.png"));
			
			tile[2] = new Tile();
			tile[2].image = ImageIO.read(getClass().getResourceAsStream("/tiles/water.png"));
			
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public void loadMap(String filePath) {
	    try {
	    	
	    	// reads the raw bytes from the gameMap file
	    	// converts the raw bytes to characters using InputStreamReader 		
	        InputStream is = getClass().getResourceAsStream(filePath);
	        BufferedReader br = new BufferedReader(new InputStreamReader(is));

	        int row = 0;

	        String line;
	        while (row < gp.maxScreenRow && (line = br.readLine()) != null) {  
	            System.out.println("Reading line: " + line); 

	            String numbers[] = line.split(" "); 

	            for (int col = 0; col < gp.maxScreenCol; col++) {  
	                mapTileNum[col][row] = Integer.parseInt(numbers[col]);
	            }
	            
	            // Move to the next row
	            row++; 
	        }
	        br.close();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}

	
	public void draw(Graphics2D g2) {
		
		int col = 0;
		int row = 0;
		int x = 0;
		int y = 0;
		
		while(col < gp.maxScreenCol && row < gp.maxScreenRow) {
			
			int tileNum = mapTileNum[col][row];
			
			g2.drawImage(tile[tileNum].image, x, y, gp.tileSize, gp.tileSize, null);
			col++;
			x+= gp.tileSize;
			
			if(col == gp.maxScreenCol) {
				col = 0;
				x = 0;
				row++;
				y += gp.tileSize;
			}
		}
		
		//g2.drawImage(tile[0].image, 0, 0, gp.tileSize, gp.tileSize, null);
		//g2.drawImage(tile[1].image, 48, 0, gp.tileSize, gp.tileSize, null);
		//g2.drawImage(tile[2].image, 96, 0, gp.tileSize, gp.tileSize, null);
	}
}
