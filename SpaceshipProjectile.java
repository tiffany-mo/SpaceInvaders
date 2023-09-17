package spaceinvaders;
import java.awt.Color;
import java.awt.Graphics;

public class SpaceshipProjectile extends GraphicsObject{
	
	public SpaceshipProjectile(double x, double y) {
		super(x,y);
	}
	
	@Override
	public void draw(Graphics g) {
		g.setColor(Color.MAGENTA);        
        g.drawRoundRect((int) this.x, (int) this.y, 7, 20, 180, 40);
        g.fillRoundRect((int) this.x, (int) this.y, 7, 20, 180, 40);
	}
	
//	public void shoot(int pic_width, int pic_height, int frame) {
//		this.speed_y = -18.0;
//		super.update(pic_width, pic_height, frame);
//		
//	}
	
//	public void update(int pic_width, int pic_height, int frame) {
//        this.x += this.speed_x;
//        this.y += this.speed_y;
//    }
}
