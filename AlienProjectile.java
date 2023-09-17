package spaceinvaders;


import java.awt.Color;
import java.awt.Graphics;
import java.util.TimerTask;

public class AlienProjectile extends GraphicsObject {
	
	
	public AlienProjectile(double x, double y) {
		super(x, y);
	}
	
	@Override
    public void draw(Graphics g) {  
        g.setColor(Color.ORANGE);        
        g.drawRoundRect((int) this.x, (int) this.y, 10, 10, 180, 40);
        g.fillRoundRect((int) this.x, (int) this.y, 10, 10, 180, 40);
    }
	
	
}
