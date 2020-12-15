import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.ImageIcon;

public class Enemy extends Entity{
	int velX; int velY;
	int s = 3, s2 = 3;
	public Enemy(int x, int y) {
		super(x,y);
		Random r = new Random();
		int i  = r.nextInt(4);
		if(i == 0) {
			
		}
		else if( i == 1) {
			s= -s;
		}
		else if(i == 2) {
			s2 = -s2;
		}
		else if( i == 3) {
			s= -s;
			s2 = -s2;
		}
	}
	public void update() {
		y+= s;
		x+= s2;
		Random r = new Random();
		
		if(y <= 0|| y >= 550) {
			s = -s;
		}
		if(x <= 0|| x >= 770) {
			s2 = -s2;
		}
	}
	public void draw(Graphics2D g2d) {
		g2d.drawImage(getenImg(), x, y, null);
		//'g2d.draw(getBounds());
		
	}
	public Image getenImg() {
		ImageIcon ic = null;
		if(s > 0 && s2 > 0) {
			ic = new ImageIcon("btr1.png");
		}
		else if(s > 0 && s2 < 0) {
			ic = new ImageIcon("btr2.png");
		}
		else if(s < 0 && s2 < 0) {
			ic = new ImageIcon("btr3.png");
		}
		else if(s < 0 && s2 >0) {
			ic = new ImageIcon("btr4.png");
		}
		Image i = ic.getImage();
		return i;
	}
	public void direct() {
		Random r = new Random();
		int d = r.nextInt(4);
		if(d == 0) {
			
		}else if(d == 1) {
			s = -s;
			
		}else if(d == 2) {
			s = -s;
			s2 = -s2;
		}else if(d == 3) {
			
			s2 = -s2;
		}
		
	}
	public Rectangle getBounds() {
		return new Rectangle(x,y,getenImg().getWidth(null),getenImg().getHeight(null));
	}
}