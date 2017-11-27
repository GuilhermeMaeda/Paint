package jogoNave;

import java.awt.Color;
import java.awt.EventQueue;
import javax.swing.JFrame;


public class Movimento extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public Movimento() {
		initUI();
	}
	private void initUI() {
		add(new Board());
		setSize(500, 420);
		setResizable(false);
		setTitle("Jogo da Nave");
		setLocationRelativeTo(null);
		setBackground(Color.BLACK);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	public static void main (String [] args) {
		EventQueue.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				Movimento ex = new Movimento();
				ex.setVisible(true);
			}
			
		});
	}
}


	