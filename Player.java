import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.ImageIcon;

public class Player extends Entity{
	int velX= 0, velY = 0;
	// speed of the player
	int speed = 5;
	// hp of the player
	int hp = 5;
	//check is restart or not
	boolean restart = false;
	int first = 1;
	Image i = getPlayerImg1();
	int choose = 1;
	int choose2 = 1;
	boolean die = false;
	public Player(int x, int y) {
		super(x,y);
		
	}
	//update movement
	public void update() {
		// don't allow player going outside the frame
		if(y <= 0) {
			velY = 0;
			y+= speed;
			
		}else if(y >= 550) {
			velY = 0;
			y-= speed;
		}
		if(x <= 0) {
			velX = 0;
			x += speed;
		}
		else if(x >= 750) {
			velX = 0;
			x -= speed;
		}
		x += velX;
		y += velY;
		checkco();
		
		
	}
	
	public void draw(Graphics2D g2d) {
		
		g2d.drawImage(i, x, y, null);
	}
	//a position when player going right
	public Image getPlayerImg1() {
		ImageIcon ic = new ImageIcon("qiangsen1.png");
		Image i = ic.getImage();
		return i;
	}
	//a position when player going right
	public Image getPlayerImg2() {
		ImageIcon ic = new ImageIcon("qiangsen2.png");
		Image i = ic.getImage();
		return i;
	}
	//a position when player going left
	public Image getPlayerImg3() {
		ImageIcon ic = new ImageIcon("qiangsen3.png");
		Image i = ic.getImage();
		return i;
	}
	//a position when player going left
	public Image getPlayerImg4() {
		ImageIcon ic = new ImageIcon("qiangsen4.png");
		Image i = ic.getImage();
		return i;
	}
	// react to key event
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		if(key == KeyEvent.VK_W) {
			velY = -speed;
			if(i == getPlayerImg2()|| i ==getPlayerImg1()) {
				if(choose == 1) {
					i = getPlayerImg1();
					choose++;
				}
				else {
					i = getPlayerImg2();
					choose = 1;
				}
			}
			else {
				if(choose2 == 1) {
					i = getPlayerImg3();
					choose2++;
				}
				else {
					i = getPlayerImg4();
					choose2 = 1;
				}
			}
		}
		else if(key == KeyEvent.VK_S) {
			velY = speed;
			if(i == getPlayerImg3()|| i ==getPlayerImg4()) {
				//changing image
				if(choose2 == 1) {
					i = getPlayerImg3();
					choose2++;
				}
				else {
					i = getPlayerImg4();
					choose2 = 1;
				}
			}
			else {
				if(choose == 1) {
					i = getPlayerImg1();
					choose++;
				}
				else {
					i = getPlayerImg2();
					choose = 1;
				}
			}
		}
		else if(key == KeyEvent.VK_A) {
			velX = -speed;
			if(choose2 == 1) {
				i = getPlayerImg3();
				choose2++;
			}
			else {
				i = getPlayerImg4();
				choose2 = 1;
			}
		}
		else if(key == KeyEvent.VK_D) {
			velX = speed; 
			if(choose == 1) {
				i = getPlayerImg1();
				choose++;
			}
			else {
				i = getPlayerImg2();
				choose = 1;
			}
		}
		if( e.getKeyCode() == KeyEvent.VK_R) {
			restart =true;
		}
	}
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		if(key == KeyEvent.VK_W) {
			velY = 0;
		}
		else if(key == KeyEvent.VK_S) {
			velY = 0;
		}
		else if(key == KeyEvent.VK_A) {
			velX = 0;
			
		}
		else if(key == KeyEvent.VK_D) {
			velX = 0;
		}
		if( e.getKeyCode() == KeyEvent.VK_R) {
			restart =false;
		}
	}
	// check if player and enemy is collision
	public void checkco() {
		ArrayList<Enemy> list = GameFrame.getEnemyList();
		for(int i = 0; i <list.size(); i++) {
			Enemy e = list.get(i);
			if(getBounds().intersects(list.get(i).getBounds())) {
				hp--;
				GameFrame.remove(e);
			}
		}
	}
	// the range of player
	public Rectangle getBounds() {
		return new Rectangle(x,y,getPlayerImg1().getWidth(null),getPlayerImg1().getHeight(null));
	}
}