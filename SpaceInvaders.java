
//JANICE WONG AND TIFFANY MO

// utility
import java.util.ArrayList;
import java.util.Random;
import java.util.stream.IntStream;
// graphics
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;

// events
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JComponent;
// swing
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;
//timer
//import java.util.Timer;
import java.util.TimerTask;

public class SpaceInvaders extends JPanel implements ActionListener, KeyListener, Runnable {

    private static final Graphics Graphics = null;
	private final int canvasWidth;
    private final int canvasHeight;
    private final Color backgroundColor;

    private final int framesPerSecond = 25;
    private final int msPerFrame = 1000 / framesPerSecond;
    private Timer timer;
    private int frame = 0;
    
    double x;
    double y;
    Spaceship spaceship;   
    
    SpaceshipBullet spaceship_bullet;
    ArrayList spaceship__bullets;   
    
    Alien bobby;
    ArrayList aliens = new ArrayList();
    
    AlienProjectile alien_projectile;
    ArrayList alienBullets = new ArrayList<>(1);
    
    GameWon win;
    GameLost loss;
    
    Boolean bottom_of_screen;
    Boolean isTouching;
    
    Random random = new Random();   

    /* Constructor for a Space Invaders game
     */
    public SpaceInvaders(double x, double y) {
        // fix the window size and background color
        this.canvasWidth = 600;
        this.canvasHeight = 400;
        this.backgroundColor = Color.BLACK;
        setPreferredSize(new Dimension(this.canvasWidth, this.canvasHeight));

        // set the drawing timer
        this.timer = new Timer(msPerFrame, this);
               
        this.x = x;
        this.y = y;
        this.spaceship = new Spaceship(x, y);
        
        this.spaceship_bullet = new SpaceshipBullet(23, 320);        
        this.spaceship__bullets = new ArrayList<SpaceshipBullet>(1);
            
        this.alien_projectile = new AlienProjectile(x, y);
        
        this.win = new GameWon(0,0);
        this.loss = new GameLost(0,0);
                
        //adding 15 aliens 
        for (int column = 60; column < 560; column += 100) {
			for(int row = 30; row < 200; row += 60) {				
				Alien alien = new Alien(column, row);	
				alien.speed_x = 2.0;				
				aliens.add(alien);				
			}
    	}    
        
        //shooting a projectile from a random living alien
    	int random_alien = random.nextInt(aliens.size());
    	Alien an_alien = (Alien) aliens.get(random_alien);
    	this.alien_projectile.x = an_alien.x;
    	this.alien_projectile.y = an_alien.y;
    	for (int sum = 0; sum < this.canvasHeight; sum++) {        		
    		this.alien_projectile.speed_y = 8.0;       		
    	}
    	
    	//default values for alien touching bottom of frame or spaceship
    	this.bottom_of_screen = false;
    	this.isTouching = false;
    }

    /* Start the game
     */
    @Override
    public void run() {
        // show this window
        display();

        // set a timer to redraw the screen regularly
        this.timer.start();
    }

    /* Create the window and display it
     */
    private void display() {
        JFrame jframe = new JFrame();
        jframe.addKeyListener(this);
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jframe.setContentPane(this);
        jframe.pack();
        jframe.setVisible(true);
    }

    /* Run all timer-based events
     *
     * @param e  An object describing the timer
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        // update the game objects
        update();
        // draw every object (this calls paintComponent)
        repaint(0, 0, this.canvasWidth, this.canvasHeight);
        // increment the frame counter
        this.frame++;
    }
    
   

    /* Paint/Draw the canvas.
     *
     * This function overrides the paint function in JPanel. This function is
     * automatically called when the panel is made visible.
     *
     * @param g The Graphics for the JPanel
     */
    @Override
    protected void paintComponent(Graphics g) {
        // clear the canvas before painting
        clearCanvas(g);
        if (hasWonGame()) {
            paintWinScreen(g);
        } else if (hasLostGame()) {
            paintLoseScreen(g);
        } else {
            paintGameScreen(g);
        }
    }

    /* Clear the canvas
     *
     * @param g The Graphics representing the canvas
     */
    private void clearCanvas(Graphics g) {
        Color oldColor = g.getColor();
        g.setColor(this.backgroundColor);
        g.fillRect(0, 0, this.canvasWidth, this.canvasHeight);
        g.setColor(oldColor);
    }                                                

    /* Respond to key release events
     *
     * A key release is when you let go of a key
     * 
     * @param e  An object describing what key was released
     */
    public void keyReleased(KeyEvent e) {
        // you can leave this function empty
    }

    /* Respond to key type events
     *
     * A key type is when you press then let go of a key
     * 
     * @param e  An object describing what key was typed
     */
    public void keyTyped(KeyEvent e) {
        // you can leave this function empty
    }

    /* Respond to key press events
     *
     * A key type is when you press then let go of a key
     * 
     * @param e  An object describing what key was typed
     */
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
        	//spaceship moves ten spaces left when left arrow key pressed
			if (spaceship.x > 10) {
				spaceship.x -= 10;
			}
			//spaceship bullet position follows the spaceship
			this.spaceship_bullet.x -= 10;
			

        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
        	//spaceship moves ten spaces right when right arrow key pressed
        	if (spaceship.x < 560) {
        		spaceship.x += 10;
        	}
        	//spaceship bullet position follows the spaceship
        	this.spaceship_bullet.x += 10;

        } else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
        	//spaceship bullet is created and added to arraylist when spacebar is pressed
        	SpaceshipBullet another_one = new SpaceshipBullet(this.spaceship_bullet.x, this.spaceship_bullet.y);
        	this.spaceship__bullets.add(another_one);    
        	
        	for (int sum = 0; sum < this.canvasHeight; sum++) {      
        		another_one.speed_y = -18.0;
        	}
        	//if spaceship bullet goes past top of frame, resets position and clears arraylist
        	if (spaceship_bullet.y < 0) {
            	this.spaceship_bullet.y = this.spaceship.y - 45; 
        		this.spaceship__bullets.clear();

        	}

        }
    }

    /* Update the game objects
     */
    private void update() {
    	
    	//removes alien when its position is equal to position of spaceship bullet, clears arraylist afterward
    	for (int alive = 0; alive < aliens.size(); alive++) {
    		Alien goingToDie = (Alien) aliens.get(alive);
	    	for (int bul = 0; bul < this.spaceship__bullets.size(); bul++) {
	    		SpaceshipBullet buls = (SpaceshipBullet) this.spaceship__bullets.get(bul);
	    		if (buls.x >= goingToDie.x - 10 && buls.x <= goingToDie.x + 46 && buls.y >= goingToDie.y && buls.y <= goingToDie.y + 38) {
					aliens.remove(alive);
		    		this.spaceship__bullets.clear();
	    		}
	    	}	    	
		}    	  		
	}

    /* Check if the player has lost the game
     * 
     * @returns  true if the player has lost, false otherwise
     */
    private boolean hasLostGame() {
    	// if spaceship's position is equal to alien projectile's position, end game
    	if (alien_projectile.x >= spaceship.x - 46 && alien_projectile.x <= spaceship.x + 46 && alien_projectile.y >= spaceship.y && alien_projectile.y <= spaceship.y + 38) {
    		return true;
    	}
    	//if alien reaches bottom of frame, end game
    	if (this.bottom_of_screen == true) {
    		return true;
    	}
    	//if alien touches spaceship, end game
    	if (this.isTouching == true) {
    		return true;
    	}   	
        return false; 
    }

    /* Check if the player has won the game
     * 
     * @returns  true if the player has won, false otherwise
     */
    private boolean hasWonGame() {
    	//if arraylist of aliens is empty, player wins
    	if (aliens.size() == 0) {
    		return true; 
    	}
    	else {
    		return false;
    	}
    }

    /* Paint the screen during normal gameplay
     *
     * @param g The Graphics for the JPanel
     */
    private void paintGameScreen(Graphics g) {
    	
    	this.spaceship.draw(g);
    	
    	//draws aliens from arraylist
    	for (int num = 0; num < aliens.size(); num++) {
    		((Alien) aliens.get(num)).draw(g);
			((Alien) aliens.get(num)).update(canvasWidth, canvasHeight, frame);

    	}
    	
    	this.alien_projectile.draw(g);
	    this.alien_projectile.update(canvasWidth, canvasHeight, frame);
    	
    	//draws spaceship bullets from arraylist
    	for (int bullets = 0; bullets < spaceship__bullets.size(); bullets++) {
    		SpaceshipBullet bullet = (SpaceshipBullet) spaceship__bullets.get(bullets);
    		bullet.draw(g);
    		bullet.update(canvasWidth, canvasHeight, frame);
    	}
    	
    	//checks if aliens are touching bottom of screen or spaceship
    	for (int bot = 0; bot < aliens.size(); bot++) {
    		Alien bott = (Alien) aliens.get(bot);
    		if (bott.y >= 400) {
    			this.bottom_of_screen = true;
    		}
    		if (bott.x >= spaceship.x && bott.x <= spaceship.x + 37 && bott.y >= spaceship.y - 34) {
    			this.isTouching = true;
    		}
    	}
    	
    	//creates two random numbers and fires an alien projectile from random existing alien if the numbers are equal to each other
    	for (int b = 0; b < 1000; b++) { 
    		Random num1 = new Random();
    		Random num2 = new Random();
    		if (num1.nextInt(100000) == num2.nextInt(100000)) {
    	    	int random_alien1 = random.nextInt(aliens.size());
    	    	Alien an_alien1 = (Alien) aliens.get(random_alien1);
    	    	this.alien_projectile.x = an_alien1.x;
    	    	this.alien_projectile.y = an_alien1.y;
    		}
    	}
    }

    /* Paint the screen when the player has won
     *
     * @param g The Graphics for the JPanel
     */
    private void paintWinScreen(Graphics g) {
    	if (this.hasWonGame() == true) {
    		this.win.draw(g);
    	}
    }

    /* Paint the screen when the player has lost
     *
     * @param g The Graphics for the JPanel
     */
    private void paintLoseScreen(Graphics g) {
    	if (this.hasLostGame() == true) {
    		this.loss.draw(g);
    	}
    }

    public static void main(String[] args) {
        SpaceInvaders invaders = new SpaceInvaders(10.0, 365.0);
        EventQueue.invokeLater(invaders);
        
    }
}
