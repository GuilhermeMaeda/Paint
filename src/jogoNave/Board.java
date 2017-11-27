package jogoNave;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Timer;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Board extends JPanel implements ActionListener {

	private Image fundo;
	private Nave nave;
	private Timer timer;

	private boolean play = true;
	
	private List<Inimigos> inimigo;

	private int[][] coordenates = {

			{ 2380, 29 }, { 2600, 59 }, { 1380, 89 }, { 780, 109 }, { 580, 139 }, { 880, 239 }, { 790, 259 },
			{ 760, 50 }, { 790, 150 }, { 1980, 209 }, { 560, 45 }, { 510, 70 }, { 930, 159 }, { 590, 80 }, { 530, 60 },
			{ 940, 59 }, { 990, 30 }, { 920, 200 }, { 900, 259 }, { 660, 50 }, { 540, 90 }, { 810, 220 }, { 860, 20 },
			{ 740, 180 }, { 820, 128 }, { 490, 170 }, { 700, 30 }, { 920, 300 }, { 856, 328 }, { 456, 320 }

	};

	public Board() {

		setFocusable(true);
		setDoubleBuffered(true);
		setSize(600,350);
		addKeyListener(new TecladoAdpter());

		ImageIcon referencia = new ImageIcon("fundo.png");
		fundo = referencia.getImage();
		nave = new Nave();

		nave = new Nave();
		timer = new Timer(5, this);
		timer.start();
		Inimigos();

	}

	public void Inimigos() {
		inimigo = new ArrayList<Inimigos>();

		for (int i = 0; i < coordenates.length; i++) {
			inimigo.add(new Inimigos(coordenates[i][0], coordenates[i][1]));

		}

	}

	public void paint(Graphics g) {

		Graphics graficos = (Graphics2D) g;
		graficos.drawImage(fundo, 0, 0, null);

		if (play) {
			graficos.drawImage(nave.getImagem(), nave.getX(), nave.getY(), this);

			List<Atirar> Tiro = nave.getMisseis();

			for (int i = 0; i < Tiro.size(); i++) {

				Atirar At = (Atirar) Tiro.get(i);
				graficos.drawImage(At.getImagem(), At.getX(), At.getY(), this);

			}
			for (int i = 0; i < inimigo.size(); i++) {

				Inimigos ini = inimigo.get(i);
				graficos.drawImage(ini.getImagem(), ini.getX(), ini.getY(), this);

			}
			graficos.setColor(Color.WHITE);
			graficos.drawString("INIMIGOS: " + inimigo.size(), 5, 15);
		} else {
			System.exit(0);
		}
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if (inimigo.size() == 0) {
			play = false;
		}
		
		List<Atirar> tiro = nave.getMisseis();

		for (int i = 0; i < tiro.size(); i++) {

			Atirar At = (Atirar) tiro.get(i);

			if (At.isVisible()) {
				At.movimentaTiro();
			} else {
				tiro.remove(i);

			}
		}
		for (int i = 0; i < inimigo.size(); i++) {

			Inimigos ini = inimigo.get(i);

			if (ini.isVisible()) {
				ini.move();
			} else {
				inimigo.remove(i);

			}
		}

		nave.move();
		bateu();
		repaint();
	}

	public void bateu() {

		Rectangle Nave = nave.getBounds();
		Rectangle Inimigo;
		Rectangle Missil;

		for (int i = 0; i < inimigo.size(); i++) {

			Inimigos tInimigo = inimigo.get(i);
			Inimigo = tInimigo.getBounds();

			if (Nave.intersects(Inimigo)) {

				nave.setVisivel(false);
				tInimigo.setVisible(false);
				JOptionPane.showMessageDialog(null, "GAME OVER!");
				System.exit(0);
			}

		}

		List<Atirar> tiro = nave.getMisseis();

		for (int i = 0; i < tiro.size(); i++) {

			Atirar tMissil = tiro.get(i);
			Missil = tMissil.getBounds();

			for (int j = 0; j < inimigo.size(); j++) {

				Inimigos tInimigo = inimigo.get(j);
				Inimigo = tInimigo.getBounds();

				if (Missil.intersects(Inimigo)) {

					tInimigo.setVisible(false);
					tMissil.setVisible(false);
				}

			}

		}
	}

	private class TecladoAdpter extends KeyAdapter {

		@Override
		public void keyPressed(KeyEvent e) {
			nave.keyPressed(e);
		}

		@Override
		public void keyReleased(KeyEvent e) {

			nave.keyReleased(e);
		}

	}
}
