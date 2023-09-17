package spaceinvaders;

import java.awt.Color;
import java.awt.Graphics;

public class Spaceship extends GraphicsObject {

	public Spaceship(double x, double y) {
		super(x, y);
		
	}
	
	@Override
    public void draw(Graphics g) {  
        g.setColor(new Color (102, 102, 102));
        g.fillRect((int) this.x, (int) this.y, 37, 8);
        
        g.setColor(new Color (204, 204, 204));
        g.drawArc((int) this.x + 8, (int) this.y - 17, 18, 34, 0, 180);
        g.fillArc((int) this.x + 8, (int) this.y - 17, 18, 34, 0, 180);
        
        g.setColor(new Color(153, 153, 153));
        g.fillRect((int) this.x + 3, (int) this.y + 8, 8, 5);
        g.setColor(new Color(255, 102, 0));
        g.fillRect((int) this.x + 6, (int) this.y + 13, 3, 5);
        
        g.setColor(new Color(153, 153, 153));
        g.fillRect((int) this.x + 14, (int) this.y + 8, 8, 8);
        g.setColor(new Color(255, 51, 51));
        g.fillRect((int) this.x + 17, (int) this.y + 16, 3, 8);
        
        g.setColor(new Color(153, 153, 153));
        g.fillRect((int) this.x + 25, (int) this.y + 8, 8, 5);
        g.setColor(new Color(255, 102, 0));
        g.fillRect((int) this.x + 28, (int) this.y + 13, 3, 5);
        
        g.setColor(new Color(0, 0, 0));
        g.fillRect((int) this.x + 5, (int) this.y + 3, 3, 3);
        g.setColor(new Color(0, 0, 0));
        g.fillRect((int) this.x + 13, (int) this.y + 3, 3, 3);
        g.setColor(new Color(0, 0, 0));
        g.fillRect((int) this.x + 21, (int) this.y + 3, 3, 3);
        g.setColor(new Color(0, 0, 0));
        g.fillRect((int) this.x + 29, (int) this.y + 3, 3, 3);
    }
	

}

