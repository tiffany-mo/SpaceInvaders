package spaceinvaders;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class GameOver extends GraphicsObject {
	
	public GameOver(double x, double y) {
		super(x,y);
	}
	
	@Override
	public void draw(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, 600, 400);
		g.setColor(Color.RED);
		g.setFont(new Font("Impact", Font.BOLD, 70));
		g.drawString("Game Over", 120, 220);
	}
	
}
