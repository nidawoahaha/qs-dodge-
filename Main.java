import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Main {
	public static void main (String[] args) {
		JFrame frame = new JFrame("dodge");
		frame.setSize(800,600);
		
		frame.add(new JLabel(new ImageIcon("linzi")));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GameFrame g = new GameFrame();
		frame.add(g);
		
		frame.setResizable(false);
		frame.setVisible(true);
	}
}