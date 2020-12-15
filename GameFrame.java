import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.ArrayList;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

public class GameFrame extends JPanel implements ActionListener{
	Timer mainTimer;
	Player player;
	static ArrayList<Enemy> en = new ArrayList<Enemy>();
	Random r = new Random();
	// number of enemy 
	int enemy = 10;
	int time = 0;
	int last = 0;
	// constructor 
	public GameFrame() {
		setFocusable(true);
		add(new JLabel(new ImageIcon("beijing.png")));
		player = new Player(100,100);
		//add key listener
		addKeyListener(new KeyAdapt(player));
		mainTimer = new Timer(10, this);
		mainTimer.start();
		//create a enemy in a random axis
		for(int i = 0; i < enemy; i++) {
			addEnemy(new Enemy(r.nextInt(500), r.nextInt(400)));
		}
		
		
	}
	// paint once
	public void paint(Graphics g) {
		
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;
		player.draw(g2d);
		// print enemy
		for(int i = 0; i < en.size(); i++) {
			Enemy temp = en.get(i);
			temp.draw(g2d);
		}
		// update if player die
		if(player.hp <= 0) {
			ImageIcon ic = new ImageIcon("gameover.png");
			Image im = ic.getImage();
			g2d.drawImage(im, 0,0, null);
			
		}
		
		ImageIcon in = new ImageIcon("kuang.png");
		//counting the hp and time when the player still alive
		if(player.hp > 0) {
			
			Image image = in.getImage();	
			g2d.drawImage(image, 0,0, null);
			g2d.drawString("second: "+ time/100, 0, 10);
			g2d.drawString("hp: "+ player.hp, 0, 25);
		}
		//restart the game
		if(player.restart) {
			mainTimer.stop();
			restart();
		}
		
		if(time - last >= 1000) {
			en.add(new Enemy(r.nextInt(500), r.nextInt(400)));
			last = time;
		}
	}


	
	public void actionPerformed(ActionEvent arg0) {
		
		for(Enemy e : en) {
			e.update();
		}player.update();
		//repaint labal when the action happen
		repaint();
		time++;
		//restart the game
		if(player.restart) {
			mainTimer.stop();
			restart();
		}
	}
	
//restart the game
	public void restart() {
		
		en = new ArrayList<Enemy>();
		r = new Random();
		time = 0;
		 last = 0;
		 add(new JLabel(new ImageIcon("beijing.png")));
			player = new Player(100,100);
			addKeyListener(new KeyAdapt(player));
			mainTimer = new Timer(10, this);
			mainTimer.start();
			for(int i = 0; i < enemy; i++) {
				addEnemy(new Enemy(r.nextInt(500), r.nextInt(400)));
			}
	}
	
	public void addEnemy(Enemy e) {
		en.add(e);
		
	}
	//remove the enemy that hit player and create a new one in random place
	public static void remove(Enemy e) {
		Random r = new Random();
		en.remove(e);
		Enemy news = new Enemy(r.nextInt(500), r.nextInt(400));
		news.s = e.s;
		news.s2 = e.s2;
		en.add(news);
	}
	
	public static ArrayList<Enemy> getEnemyList(){
		return en;
	}
	
}