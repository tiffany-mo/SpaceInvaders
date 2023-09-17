package spaceinvaders;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.TimerTask;

import javax.swing.Timer;

public class Alien extends GraphicsObject {
	
	int row;
	int column;
	
	public Alien(double x, double y) {
		super(x,y);
		this.column = (int) x;
		this.row = (int) y;
	}
	
	@Override
	public void draw(Graphics g) {
		
		g.setColor(new Color(0, 255, 51));
		g.drawRect((int) this.x, (int) this.y, 30, 30);
		g.fillRect((int) this.x, (int) this.y, 30, 30);
		
		g.setColor(Color.BLACK);
		g.drawRect((int) this.x + 6, (int) this.y + 7, 5, 5);
		g.fillRect((int) this.x + 6, (int) this.y + 7, 5, 5);
		
		g.setColor(Color.BLACK);
		g.drawRect((int) this.x + 19, (int) this.y + 7, 5, 5);
		g.fillRect((int) this.x + 19, (int) this.y + 7, 5, 5);
		
		g.setColor(Color.RED);
		g.drawRect((int) this.x + 6, (int) this.y + 18, 3, 3);
		g.fillRect((int) this.x + 6, (int) this.y + 18, 3, 3);
		g.setColor(Color.RED);
		g.drawRect((int) this.x + 21, (int) this.y + 18, 3, 3);
		g.fillRect((int) this.x + 21, (int) this.y + 18, 3, 3);
		g.setColor(Color.RED);
		g.drawRect((int) this.x + 9, (int) this.y + 22, 12, 3);
		g.fillRect((int) this.x + 9, (int) this.y + 22, 12, 3);
		
		g.setColor(new Color(0, 255, 51));
		g.drawRect((int) this.x + 5, (int) this.y - 9, 3, 8);
		g.fillRect((int) this.x + 5, (int) this.y - 9, 3, 8);
		g.setColor(new Color(0, 255, 51));
		g.drawRect((int) this.x, (int) this.y - 9, 5, 3);
		g.fillRect((int) this.x, (int) this.y - 9, 5, 3);
		g.setColor(new Color(0, 255, 51));
		g.drawRect((int) this.x + 22, (int) this.y - 9, 3, 8);
		g.fillRect((int) this.x + 22, (int) this.y - 9, 3, 8);
		g.setColor(new Color(0, 255, 51));
		g.drawRect((int) this.x + 25, (int) this.y - 9, 5, 3);
		g.fillRect((int) this.x + 25, (int) this.y - 9, 5, 3);
		
		g.setColor(new Color(0, 255, 51));
		g.drawRect((int) this.x - 8, (int) this.y + 13, 8, 3);
		g.fillRect((int) this.x - 8, (int) this.y + 13, 8, 3);
		g.setColor(new Color(0, 255, 51));
		g.drawRect((int) this.x - 8, (int) this.y + 16, 3, 3);
		g.fillRect((int) this.x - 8, (int) this.y + 16, 3, 3);
		g.setColor(new Color(0, 255, 51));
		g.drawRect((int) this.x + 30, (int) this.y + 13, 8, 3);
		g.fillRect((int) this.x + 30, (int) this.y + 13, 8, 3);
		g.drawRect((int) this.x + 35, (int) this.y + 16, 3, 3);
		g.fillRect((int) this.x + 35, (int) this.y + 16, 3, 3);
		
	}
	
	public double getX() {
		return this.x;
	}
	
	public double getY() {
		return this.y;
	}
	
	
	@Override
	public void update(int pic_width, int pic_height, int frame) {
		
		if (this.x > this.column + 100) {
			this.speed_x = -this.speed_x;
			this.y += 3.5;
		}
		
		else if (this.x < this.column - 50) {
			this.speed_x = -this.speed_x;
			this.y += 3.5;
		}
			
		
		super.update(pic_width, pic_height, frame);
	
	}
}
