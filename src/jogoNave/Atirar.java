package jogoNave;

import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class Atirar {

	private Image imagem;
	private int x, y;
	private int largura, altura;
	private boolean isVisivel;

	private static int contador = 0;
	private static int contadorTiro = 0;
	
	private static final int LARGURA_TELA = 500;
	private static int VELOCIDADE = 1;

	public Atirar(int x, int y) {

		this.x = x;
		this.y = y;
		
		ImageIcon tiro;
		
		if (contador++ % 3 == 0) {
			tiro = new ImageIcon("missil2.png");
			contadorTiro = 1;
		} else {
			tiro = new ImageIcon("missil3.png ");
			contadorTiro = 2;
		}

		if (contadorTiro == 1){
			VELOCIDADE = 5 ;
		}
		imagem = tiro.getImage();
		

		this.largura = imagem.getWidth(null);
		this.altura = imagem.getHeight(null);
		
		isVisivel = true;
	}

	public void movimentaTiro(){
		
		this.x += VELOCIDADE;
		if(this.x > LARGURA_TELA){
			isVisivel = false;
		}
		
	}
	
	public boolean isVisible() {
		return isVisivel;
	}

	public void setVisible(boolean isVisivel) {
		this.isVisivel = isVisivel;
	}

	public Image getImagem() {
		return imagem;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
	
	public Rectangle getBounds(){
		return new Rectangle(x, y, largura, altura);
	}

	
}
