package Paint;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GradientPaint;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.Toolkit;

import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
import javax.swing.JButton;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class TrabalhoGuilhermeMaeda {

	// O usuário através de interface tenha a opção de criar os polígonos
	// (retângulo, círculo, arco)
	// e possa regular o tamanho, o ângulo e cores (16 cores)
	// Autor: Guilherme Bonfim Maeda
	// Ciência da computação - 6 Semestre ; Coputação Gráfica, Trabalho01

	static JButton Desenhar;
	static JButton Degrade;
	static JButton Limpar;
	static JButton Livre;
	static JButton Ajuda;
	static JButton Ok;
	static JButton Salvar;
	static JButton OkRec;
	static JButton OkCir;
	static JButton OkArc;
	static JButton OkDegrade;
	static JButton jbcor1, jbcor2, jbcor3, jbcor4, jbcor5, jbcor6, jbcor7, jbcor8, jbcor9, jbcor10, jbcor11, jbcor12,
			jbcor13, jbcor14, jbcor15, jbcor16;

	static JRadioButton retangulo;
	static JRadioButton circulo;
	static JRadioButton arco;
	static JRadioButton png;
	static JRadioButton jpg;
	static JRadioButton bitmap;
	static JRadioButton gif;

	static JFrame tela;
	static JFrame telaEscolha;
	static JFrame telaEscolhaDegrade;
	static JFrame telaRec;
	static JFrame telaCir;
	static JFrame telaArc;
	static JFrame telaDegrade;
	static JPanel panel;
	static JPanel panelCor;

	static JTextField jtXInicioCor1;
	static JTextField jtYInicioCor1;
	static JTextField jtXInicioCor2;
	static JTextField jtYInicioCor2;

	static Color cor1, cor2, cor3, cor4, cor5, cor6, cor7, cor8, cor9, cor10, cor11, cor12, cor13, cor14, cor15, cor16,
			corEscolhida, corEscolhidaInicial, corEscolhidaFinal = Color.BLACK;

	static Graphics2D g2dSave;
	static BufferedImage bri;
	static BufferedImage briBTMPEJPG;

	static ArrayList<Shape> shapes = new ArrayList<Shape>();
	static Point startDrag, endDrag;

	static int contador = 0;
	static int escolha;

	static Dimension Dimensao = Toolkit.getDefaultToolkit().getScreenSize();

	public static void main(String[] args) {
		Tela();
	}

	public static void Tela() {

		tela = new JFrame();
		tela.setTitle("Computação Gráfica");
		tela.setSize(1000, 610);
		tela.setLocationRelativeTo(null);
		tela.setResizable(false);
		tela.setLayout(null);
		tela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		tela.setBackground(new Color(250, 240, 230));
		tela.setVisible(true);

		Desenhar = new JButton("Desenhar");
		Desenhar.setBounds(870, 20, 100, 30);
		Desenhar.setBackground(new Color(250, 240, 230));
		tela.add(Desenhar);

		Degrade = new JButton("Degradê");
		Degrade.setBounds(870, 60, 100, 30);
		Degrade.setBackground(new Color(250, 240, 230));
		tela.add(Degrade);

		Livre = new JButton("Paint Livre");
		Livre.setBounds(870, 100, 100, 30);
		Livre.setBackground(new Color(250, 240, 230));
		tela.add(Livre);

		Limpar = new JButton("Limpar");
		Limpar.setBounds(870, 140, 100, 30);
		Limpar.setBackground(new Color(250, 240, 230));
		Limpar.setEnabled(false);
		tela.add(Limpar);

		Salvar = new JButton("Salvar");
		Salvar.setBounds(870, 180, 100, 30);
		Salvar.setBackground(new Color(250, 240, 230));
		Salvar.setEnabled(false);
		tela.add(Salvar);

		Ajuda = new JButton("Ajuda");
		Ajuda.setBounds(870, 220, 100, 30);
		Ajuda.setBackground(new Color(250, 240, 230));
		tela.add(Ajuda);

		panel = new JPanel();
		panel.setBounds(10, 10, 850, 560);
		panel.setBackground(new Color(255, 255, 255));
		tela.add(panel);

		Font letra = new Font("TIMES NEW ROMAN", Font.BOLD, 15);

		JLabel cores = new JLabel("Tabela de cores");
		tela.add(cores);
		cores.setBounds(870, 220, 150, 100);
		cores.setFont(letra);

		panelCor = new JPanel();
		panelCor.setLayout(new GridLayout(8, 10, 5, 5));
		panelCor.setBounds(870, 290, 110, 280);
		panelCor.setBackground(new Color(250, 240, 230));
		tela.add(panelCor);
		TabelaCores();

		AcaoBotao();
		DeshabilitaBotaoCor();
	}

	public static void AcaoBotao() {
		Desenhar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				HabilitaBotaoCor();
				JOptionPane.showMessageDialog(null, "Escolha uma cor para seu desenho");
				contador = 0;
				if (contador == 0) {
					EscolheCor();
				} else {

				}
			}
		});

		Degrade.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showMessageDialog(null, "Informe os valores de X e Y das duas cores");
				Degrade();
			}
		});

		Livre.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				DesenhoLivre();
			}
		});

		Salvar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Salvar();
			}
		});

		Limpar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				panel.repaint();
				Salvar.setEnabled(false);
				Limpar.setEnabled(false);
			}
		});

		Ajuda.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showMessageDialog(null,
						"Para começar a desenhar, clique em Desenhar,"
								+ " escolha a cor de seu desenho e depois, o que deseja desenhar e informe as medidas.\n"
								+ "Opção Degradê: Nesta opção, você pode escolher duas cores para seu desenho, experimente!"
								+ "Opção Paint Livre: Nesta opção, será aberta uma nova tela para que você possa desenhar livremente"
								+ "retângulos ou círculos com cores aleatórias");
			}
		});
	}

	public static void TelaEscolha() {

		telaEscolha = new JFrame();
		telaEscolha.setTitle("Escolha Normal");
		telaEscolha.setSize(250, 90);
		telaEscolha.setLocationRelativeTo(null);
		telaEscolha.setResizable(false);
		telaEscolha.setLayout(new FlowLayout());
		telaEscolha.setVisible(true);

		ButtonGroup grupo1 = new ButtonGroup();

		retangulo = new JRadioButton("Retângulo", false);
		circulo = new JRadioButton("Círculo", false);
		arco = new JRadioButton("Arco", false);

		grupo1.add(retangulo);
		grupo1.add(circulo);
		grupo1.add(arco);

		telaEscolha.add(retangulo);
		telaEscolha.add(circulo);
		telaEscolha.add(arco);

		Ok = new JButton("Ok");
		Ok.setBounds(100, 50, 100, 50);
		telaEscolha.add(Ok);

		AcaoRadioButton();
	}

	public static void AcaoRadioButton() {
		Ok.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				telaEscolha.dispose();
				VerificaEscolha();
			}
		});
	}

	public static void TelaEscolhaDegrade() {

		telaEscolhaDegrade = new JFrame();
		telaEscolhaDegrade.setTitle("Escolha Degrade");
		telaEscolhaDegrade.setSize(250, 90);
		telaEscolhaDegrade.setLocationRelativeTo(null);
		telaEscolhaDegrade.setResizable(false);
		telaEscolhaDegrade.setLayout(new FlowLayout());
		telaEscolhaDegrade.setVisible(true);

		ButtonGroup grupo1 = new ButtonGroup();

		retangulo = new JRadioButton("Retângulo", false);
		circulo = new JRadioButton("Círculo", false);
		arco = new JRadioButton("Arco", false);

		grupo1.add(retangulo);
		grupo1.add(circulo);
		grupo1.add(arco);

		telaEscolhaDegrade.add(retangulo);
		telaEscolhaDegrade.add(circulo);
		telaEscolhaDegrade.add(arco);

		Ok = new JButton("Ok");
		Ok.setBounds(100, 50, 100, 50);
		telaEscolhaDegrade.add(Ok);

		AcaoRadioButtonDegrade();
	}

	public static void AcaoRadioButtonDegrade() {
		Ok.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				telaEscolhaDegrade.dispose();
				VerificaEscolhaDegrade();
			}
		});
	}

	public static void TabelaCores() {
		jbcor1 = new JButton();
		// jbcor1.setBounds(20, 0, 30, 30);
		// jbcor1.setEnabled(false);
		jbcor1.setBackground(new Color(0, 0, 0));// preto
		cor1 = new Color(0, 0, 0);
		jbcor1.setVisible(true);
		panelCor.add(jbcor1);

		jbcor2 = new JButton();
		// jbcor2.setBounds(60, 0, 30, 30);
		// jbcor2.setEnabled(false);
		jbcor2.setBackground(new Color(128, 128, 128));// cinza
		cor2 = new Color(128, 128, 128);
		jbcor2.setVisible(true);
		panelCor.add(jbcor2);

		jbcor3 = new JButton();
		// jbcor3.setBounds(20, 35, 30, 30);
		// jbcor3.setEnabled(false);
		jbcor3.setBackground(new Color(0, 0, 139));// azul escuro
		cor3 = new Color(0, 0, 139);
		jbcor3.setVisible(true);
		panelCor.add(jbcor3);

		jbcor4 = new JButton();
		// jbcor4.setBounds(60, 35, 30, 30);
		// jbcor4.setEnabled(false);
		jbcor4.setBackground(new Color(0, 0, 255));// azul claro
		cor4 = new Color(0, 0, 255);
		jbcor4.setVisible(true);
		panelCor.add(jbcor4);

		jbcor5 = new JButton();
		// jbcor5.setBounds(60, 70, 30, 30);
		// jbcor5.setEnabled(false);
		jbcor5.setBackground(new Color(0, 128, 0));// verde claro
		cor5 = new Color(0, 128, 0);
		jbcor5.setVisible(true);
		panelCor.add(jbcor5);

		jbcor6 = new JButton();
		// jbcor6.setBounds(20, 70, 30, 30);
		// jbcor6.setEnabled(false);
		jbcor6.setBackground(new Color(0, 100, 0));// verde escuro
		cor6 = new Color(0, 100, 0);
		jbcor6.setVisible(true);
		panelCor.add(jbcor6);

		jbcor7 = new JButton();
		// jbcor7.setBounds(20, 105, 30, 30);
		// jbcor7.setEnabled(false);
		jbcor7.setBackground(new Color(128, 0, 0));// marrom
		cor7 = new Color(128, 0, 0);
		jbcor7.setVisible(true);
		panelCor.add(jbcor7);

		jbcor8 = new JButton();
		// jbcor8.setBounds(60, 105, 30, 30);
		// jbcor7.setEnabled(false);
		jbcor8.setBackground(new Color(255, 0, 0));// vermelho
		cor8 = new Color(255, 0, 0);
		jbcor8.setVisible(true);
		panelCor.add(jbcor8);

		jbcor9 = new JButton();
		// jbcor9.setBounds(20, 140, 30, 30);
		// jbcor7.setEnabled(false);
		jbcor9.setBackground(new Color(255, 69, 0));// orangeRed
		cor9 = new Color(255, 69, 0);
		jbcor9.setVisible(true);
		panelCor.add(jbcor9);

		jbcor10 = new JButton();
		// jbcor10.setBounds(60, 140, 30, 30);
		// jbcor7.setEnabled(false);
		jbcor10.setBackground(new Color(255, 165, 0));// laranja
		cor10 = new Color(255, 165, 0);
		jbcor10.setVisible(true);
		panelCor.add(jbcor10);

		jbcor11 = new JButton();
		// jbcor11.setBounds(20, 175, 30, 30);
		// jbcor7.setEnabled(false);
		jbcor11.setBackground(new Color(255, 215, 0));// Gold
		cor11 = new Color(255, 215, 0);
		jbcor11.setVisible(true);
		panelCor.add(jbcor11);

		jbcor12 = new JButton();
		// jbcor12.setBounds(60, 175, 30, 30);
		// jbcor7.setEnabled(false);
		jbcor12.setBackground(new Color(255, 255, 0));// Amarelo
		cor12 = new Color(255, 255, 0);
		jbcor12.setVisible(true);
		panelCor.add(jbcor12);

		jbcor13 = new JButton();
		// jbcor13.setBounds(20, 210, 30, 30);
		// jbcor7.setEnabled(false);
		jbcor13.setBackground(new Color(255, 20, 147));// RosaEscuro
		cor13 = new Color(255, 20, 147);
		jbcor13.setVisible(true);
		panelCor.add(jbcor13);

		jbcor14 = new JButton();
		// jbcor14.setBounds(60, 210, 30, 30);
		// jbcor7.setEnabled(false);
		jbcor14.setBackground(new Color(255, 192, 203));// RosaEscuro
		cor14 = new Color(255, 192, 203);
		jbcor14.setVisible(true);
		panelCor.add(jbcor14);

		jbcor15 = new JButton();
		// jbcor15.setBounds(20, 245, 30, 30);
		// jbcor7.setEnabled(false);
		jbcor15.setBackground(new Color(128, 0, 128));// Roxo
		cor15 = new Color(128, 0, 128);
		jbcor15.setVisible(true);
		panelCor.add(jbcor15);

		jbcor16 = new JButton();
		// jbcor16.setBounds(60, 245, 30, 30);
		// jbcor7.setEnabled(false);
		jbcor16.setBackground(new Color(238, 130, 238));// Violeta
		cor16 = new Color(238, 130, 238);
		jbcor16.setVisible(true);
		panelCor.add(jbcor16);

	}

	public static void DesenhaRetangulo() {
		telaRec = new JFrame();
		telaRec.setTitle("Dados Retâgulo");
		telaRec.setSize(300, 250);
		telaRec.setLocationRelativeTo(null);
		telaRec.setResizable(false);
		telaRec.setLayout(null);
		telaRec.setVisible(true);

		JLabel informacao = new JLabel("Informe as medidas do seu retângulo");
		telaRec.add(informacao);
		informacao.setBounds(35, 20, 250, 30);

		JLabel jlcomprimento = new JLabel("Comprimento");
		telaRec.add(jlcomprimento);
		jlcomprimento.setBounds(15, 60, 100, 30);

		JTextField comprimento = new JTextField(30);
		telaRec.add(comprimento);
		comprimento.setBounds(15, 90, 80, 20);

		JLabel jlaltura = new JLabel("Altura");
		telaRec.add(jlaltura);
		jlaltura.setBounds(15, 120, 100, 30);

		JTextField altura = new JTextField(20);
		telaRec.add(altura);
		altura.setBounds(15, 150, 80, 20);

		JLabel jposX = new JLabel("Posição X");
		telaRec.add(jposX);
		jposX.setBounds(150, 60, 100, 30);

		JTextField posX = new JTextField(20);
		telaRec.add(posX);
		posX.setBounds(150, 90, 80, 20);

		JLabel jposY = new JLabel("Posição Y");
		telaRec.add(jposY);
		jposY.setBounds(150, 120, 100, 30);

		JTextField posY = new JTextField(20);
		telaRec.add(posY);
		posY.setBounds(150, 150, 80, 20);

		// EscolheCor();

		OkRec = new JButton("Desenhar");
		OkRec.setBounds(100, 180, 100, 30);
		telaRec.add(OkRec);

		System.out.println(contador);
		OkRec.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {

				if (posX.getText().isEmpty() || posY.getText().isEmpty() || comprimento.getText().isEmpty()
						|| altura.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Informe todos os valores!");
				} else {

					Integer x = Integer.parseInt(posX.getText());
					Integer y = Integer.parseInt(posY.getText());
					Integer Icomprimento = Integer.parseInt(comprimento.getText());
					Integer Ialtura = Integer.parseInt(altura.getText());

					if (x > 850) {
						JOptionPane.showMessageDialog(null, "Valor limite excedido ou faltam valores!");
					} else if (y > 450) {
						JOptionPane.showMessageDialog(null, "Valor limite excedido ou faltam valores!");
					} else if (x > 850 && y > 450) {
						JOptionPane.showMessageDialog(null, "Valor limite excedido ou faltam valores!");
					} else if (Icomprimento > 850 || Icomprimento == 0) {
						JOptionPane.showMessageDialog(null, "Valor limite excedido ou faltam valores!");
					} else if (Ialtura > 450 || Ialtura == 0) {
						JOptionPane.showMessageDialog(null, "Valor limite excedido ou faltam valores!");
					} else if (Icomprimento > 850 && Ialtura > 450) {
						JOptionPane.showMessageDialog(null, "Valor limite excedido");
					} else {
						telaRec.dispose();
						telaEscolha.dispose();
						Graphics2D g2d = (Graphics2D) panel.getGraphics();
						g2d.setColor(corEscolhida);
						RenderingHints rh = new RenderingHints((RenderingHints.KEY_ANTIALIASING),
								(RenderingHints.VALUE_ANTIALIAS_ON));
						rh.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
						g2d.setRenderingHints(rh);
						g2d.fillRect(x, y, Icomprimento, Ialtura);

						DeshabilitaBotaoCor();

						bri = new BufferedImage(panel.getWidth(), panel.getHeight(), BufferedImage.TYPE_INT_ARGB);
						g2dSave = bri.createGraphics();
						g2dSave.setColor(corEscolhida);
						g2dSave.fillRect(x, y, Icomprimento, Ialtura);

						briBTMPEJPG = new BufferedImage(panel.getWidth(), panel.getHeight(),
								BufferedImage.TYPE_INT_RGB);
						g2dSave = briBTMPEJPG.createGraphics();
						g2dSave.setColor(corEscolhida);
						g2dSave.fillRect(x, y, Icomprimento, Ialtura);

						Salvar.setEnabled(true);
						Limpar.setEnabled(true);

						contador = 1;

						JOptionPane.showMessageDialog(null, "Que obra prima! Agora é possível salvar seu desenho.");
						System.out.println(contador);
					}
				}
			}

		});

	}

	public static void DesenhaRetanguloDegrade() {
		telaRec = new JFrame();
		telaRec.setTitle("Dados Retâgulo");
		telaRec.setSize(300, 250);
		telaRec.setLocationRelativeTo(null);
		telaRec.setResizable(false);
		telaRec.setLayout(null);
		telaRec.setVisible(true);

		JLabel informacao = new JLabel("Informe as medidas do seu retângulo");
		telaRec.add(informacao);
		informacao.setBounds(35, 20, 250, 30);

		JLabel jlcomprimento = new JLabel("Comprimento");
		telaRec.add(jlcomprimento);
		jlcomprimento.setBounds(15, 60, 100, 30);

		JTextField comprimento = new JTextField(30);
		telaRec.add(comprimento);
		comprimento.setBounds(15, 90, 80, 20);

		JLabel jlaltura = new JLabel("Altura");
		telaRec.add(jlaltura);
		jlaltura.setBounds(15, 120, 100, 30);

		JTextField altura = new JTextField(20);
		telaRec.add(altura);
		altura.setBounds(15, 150, 80, 20);

		JLabel jposX = new JLabel("Posição X");
		telaRec.add(jposX);
		jposX.setBounds(150, 60, 100, 30);

		JTextField posX = new JTextField(20);
		telaRec.add(posX);
		posX.setBounds(150, 90, 80, 20);

		JLabel jposY = new JLabel("Posição Y");
		telaRec.add(jposY);
		jposY.setBounds(150, 120, 100, 30);

		JTextField posY = new JTextField(20);
		telaRec.add(posY);
		posY.setBounds(150, 150, 80, 20);

		// EscolheCor();

		OkRec = new JButton("Desenhar");
		OkRec.setBounds(100, 180, 100, 30);
		telaRec.add(OkRec);

		OkRec.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {

				if (posX.getText().isEmpty() || posY.getText().isEmpty() || comprimento.getText().isEmpty()
						|| altura.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Informe todos os valores!");
				} else {

					Integer x = Integer.parseInt(posX.getText());
					Integer y = Integer.parseInt(posY.getText());
					Integer Icomprimento = Integer.parseInt(comprimento.getText());
					Integer Ialtura = Integer.parseInt(altura.getText());

					if (x > 850) {
						JOptionPane.showMessageDialog(null, "Valor limite excedido ou faltam valores!");
					} else if (y > 450) {
						JOptionPane.showMessageDialog(null, "Valor limite excedido ou faltam valores!");
					} else if (x > 850 && y > 450) {
						JOptionPane.showMessageDialog(null, "Valor limite excedido ou faltam valores!");
					} else if (Icomprimento > 850 || Icomprimento == 0) {
						JOptionPane.showMessageDialog(null, "Valor limite excedido ou faltam valores!");
					} else if (Ialtura > 450 || Ialtura == 0) {
						JOptionPane.showMessageDialog(null, "Valor limite excedido ou faltam valores!");
					} else if (Icomprimento > 850 && Ialtura > 450) {
						JOptionPane.showMessageDialog(null, "Valor limite excedido");
					} else {
						telaRec.dispose();
						Graphics2D g2d = (Graphics2D) panel.getGraphics();

						Integer XCor1 = Integer.parseInt(jtXInicioCor1.getText());
						Integer YCor1 = Integer.parseInt(jtYInicioCor1.getText());
						Integer XCor2 = Integer.parseInt(jtXInicioCor2.getText());
						Integer YCor2 = Integer.parseInt(jtYInicioCor2.getText());

						GradientPaint gfx = new GradientPaint(XCor1, YCor1, corEscolhidaInicial, XCor2, YCor2,
								corEscolhidaFinal, true);

						g2d.setPaint(gfx);
						RenderingHints rh = new RenderingHints((RenderingHints.KEY_ANTIALIASING),
								(RenderingHints.VALUE_ANTIALIAS_ON));
						rh.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
						g2d.setRenderingHints(rh);
						g2d.fillRect(x, y, Icomprimento, Ialtura);

						contador = 3;
						// corEscolhidaFinal != null;
						System.out.println(contador);

						DeshabilitaBotaoCor();

						bri = new BufferedImage(panel.getWidth(), panel.getHeight(), BufferedImage.TYPE_INT_ARGB);
						g2dSave = bri.createGraphics();
						g2dSave.setPaint(gfx);
						g2dSave.fillRect(x, y, Icomprimento, Ialtura);

						briBTMPEJPG = new BufferedImage(panel.getWidth(), panel.getHeight(),
								BufferedImage.TYPE_INT_RGB);
						g2dSave = briBTMPEJPG.createGraphics();
						g2dSave.setPaint(gfx);
						g2dSave.fillRect(x, y, Icomprimento, Ialtura);

						Salvar.setEnabled(true);
						Limpar.setEnabled(true);

						JOptionPane.showMessageDialog(null, "Que obra prima! Agora é possível salvar seu desenho.");
					}
				}
			}
		});

	}

	public static void DesenhaCirculo() {
		telaCir = new JFrame();
		telaCir.setTitle("Dados Circulo");
		telaCir.setSize(300, 320);
		telaCir.setLocationRelativeTo(null);
		telaCir.setResizable(false);
		telaCir.setLayout(null);
		telaCir.setVisible(true);

		JLabel informacao = new JLabel("Informe as medidas do seu círculo");
		telaCir.add(informacao);
		informacao.setBounds(50, 20, 250, 30);

		JLabel jposX = new JLabel("Posição X");
		telaCir.add(jposX);
		jposX.setBounds(15, 60, 100, 30);

		JTextField posX = new JTextField(20);
		telaCir.add(posX);
		posX.setBounds(15, 90, 80, 20);

		JLabel jposY = new JLabel("Posição Y");
		telaCir.add(jposY);
		jposY.setBounds(15, 120, 100, 30);

		JTextField posY = new JTextField(20);
		telaCir.add(posY);
		posY.setBounds(15, 150, 80, 20);

		JLabel jwidth = new JLabel("Largura");
		telaCir.add(jwidth);
		jwidth.setBounds(180, 60, 100, 30);

		JTextField jtwidth = new JTextField(20);
		telaCir.add(jtwidth);
		jtwidth.setBounds(180, 90, 80, 20);

		JLabel jheigh = new JLabel("Altura");
		telaCir.add(jheigh);
		jheigh.setBounds(180, 120, 100, 30);

		JTextField jtHeigh = new JTextField(20);
		telaCir.add(jtHeigh);
		jtHeigh.setBounds(180, 150, 80, 20);

		JLabel jarcWidth = new JLabel("Largura Arco");
		telaCir.add(jarcWidth);
		jarcWidth.setBounds(15, 180, 100, 30);

		JTextField arcWidth = new JTextField(20);
		telaCir.add(arcWidth);
		arcWidth.setBounds(15, 210, 80, 20);

		JLabel jarcHeigh = new JLabel("Altura Arco");
		telaCir.add(jarcHeigh);
		jarcHeigh.setBounds(180, 180, 100, 30);

		JTextField arcHeigh = new JTextField(20);
		telaCir.add(arcHeigh);
		arcHeigh.setBounds(180, 210, 80, 20);

		OkCir = new JButton("Desenhar");
		OkCir.setBounds(90, 240, 100, 30);
		telaCir.add(OkCir);

		OkCir.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {

				if (posX.getText().isEmpty() || posY.getText().isEmpty() || jtwidth.getText().isEmpty()
						|| jtHeigh.getText().isEmpty() || arcWidth.getText().isEmpty()
						|| arcHeigh.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Informe todos os valores!");
				} else {

					Integer x = Integer.parseInt(posX.getText());
					Integer y = Integer.parseInt(posY.getText());
					Integer ILargura = Integer.parseInt(jtwidth.getText());
					Integer Ialtura = Integer.parseInt(jtHeigh.getText());
					Integer IArclargura = Integer.parseInt(arcWidth.getText());
					Integer IArcaltura = Integer.parseInt(arcHeigh.getText());

					if (x > 850) {
						JOptionPane.showMessageDialog(null, "Valor limite excedido ou faltam valores!");
					} else if (y > 450) {
						JOptionPane.showMessageDialog(null, "Valor limite excedido ou faltam valores!");
					} else if (x > 850 && y > 450) {
						JOptionPane.showMessageDialog(null, "Valor limite excedido ou faltam valores!");
					} else if (ILargura > 850 || ILargura == 0) {
						JOptionPane.showMessageDialog(null, "Valor limite excedido ou faltam valores!");
					} else if (Ialtura > 450 || Ialtura == 0) {
						JOptionPane.showMessageDialog(null, "Valor limite excedido ou faltam valores!");
					} else if (ILargura > 850 && Ialtura > 450) {
						JOptionPane.showMessageDialog(null, "Valor limite excedido");
					} else {
						telaCir.dispose();
						Graphics2D g2d = (Graphics2D) panel.getGraphics();
						g2d.setColor(corEscolhida);
						RenderingHints rh = new RenderingHints((RenderingHints.KEY_ANTIALIASING),
								(RenderingHints.VALUE_ANTIALIAS_ON));
						rh.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
						g2d.setRenderingHints(rh);
						g2d.fillRoundRect(x, y, ILargura, Ialtura, IArclargura, IArcaltura);

						DeshabilitaBotaoCor();

						bri = new BufferedImage(panel.getWidth(), panel.getHeight(), BufferedImage.TYPE_INT_ARGB);
						g2dSave = bri.createGraphics();
						g2dSave.setColor(corEscolhida);
						g2dSave.fillRoundRect(x, y, ILargura, Ialtura, IArclargura, IArcaltura);

						briBTMPEJPG = new BufferedImage(panel.getWidth(), panel.getHeight(),
								BufferedImage.TYPE_INT_RGB);
						g2dSave = briBTMPEJPG.createGraphics();
						g2dSave.setColor(corEscolhida);
						g2dSave.fillRoundRect(x, y, ILargura, Ialtura, IArclargura, IArcaltura);

						// g2d.fillRoundRect(x, y, width, height,
						// arcWidth,arcHeight);

						Salvar.setEnabled(true);
						Limpar.setEnabled(true);

						JOptionPane.showMessageDialog(null, "Que obra prima! Agora é possível salvar seu desenho.");
					}
				}
			}
		});
	}

	public static void DesenhaCirculoDegrade() {
		telaCir = new JFrame();
		telaCir.setTitle("Dados Circulo");
		telaCir.setSize(300, 320);
		telaCir.setLocationRelativeTo(null);
		telaCir.setResizable(false);
		telaCir.setLayout(null);
		telaCir.setVisible(true);

		JLabel informacao = new JLabel("Informe as medidas do seu círculo");
		telaCir.add(informacao);
		informacao.setBounds(50, 20, 250, 30);

		JLabel jposX = new JLabel("Posição X");
		telaCir.add(jposX);
		jposX.setBounds(15, 60, 100, 30);

		JTextField posX = new JTextField(20);
		telaCir.add(posX);
		posX.setBounds(15, 90, 80, 20);

		JLabel jposY = new JLabel("Posição Y");
		telaCir.add(jposY);
		jposY.setBounds(15, 120, 100, 30);

		JTextField posY = new JTextField(20);
		telaCir.add(posY);
		posY.setBounds(15, 150, 80, 20);

		JLabel jwidth = new JLabel("Largura");
		telaCir.add(jwidth);
		jwidth.setBounds(180, 60, 100, 30);

		JTextField jtwidth = new JTextField(20);
		telaCir.add(jtwidth);
		jtwidth.setBounds(180, 90, 80, 20);

		JLabel jheigh = new JLabel("Altura");
		telaCir.add(jheigh);
		jheigh.setBounds(180, 120, 100, 30);

		JTextField jtHeigh = new JTextField(20);
		telaCir.add(jtHeigh);
		jtHeigh.setBounds(180, 150, 80, 20);

		JLabel jarcWidth = new JLabel("Largura Arco");
		telaCir.add(jarcWidth);
		jarcWidth.setBounds(15, 180, 100, 30);

		JTextField arcWidth = new JTextField(20);
		telaCir.add(arcWidth);
		arcWidth.setBounds(15, 210, 80, 20);

		JLabel jarcHeigh = new JLabel("Altura Arco");
		telaCir.add(jarcHeigh);
		jarcHeigh.setBounds(180, 180, 100, 30);

		JTextField arcHeigh = new JTextField(20);
		telaCir.add(arcHeigh);
		arcHeigh.setBounds(180, 210, 80, 20);

		OkCir = new JButton("Desenhar");
		OkCir.setBounds(90, 240, 100, 30);
		telaCir.add(OkCir);

		OkCir.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {

				if (posX.getText().isEmpty() || posY.getText().isEmpty() || jtwidth.getText().isEmpty()
						|| jtHeigh.getText().isEmpty() || arcWidth.getText().isEmpty()
						|| arcHeigh.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Informe todos os valores!");
				} else {

					Integer x = Integer.parseInt(posX.getText());
					Integer y = Integer.parseInt(posY.getText());
					Integer ILargura = Integer.parseInt(jtwidth.getText());
					Integer Ialtura = Integer.parseInt(jtHeigh.getText());
					Integer IArclargura = Integer.parseInt(arcWidth.getText());
					Integer IArcaltura = Integer.parseInt(arcHeigh.getText());

					if (x > 850) {
						JOptionPane.showMessageDialog(null, "Valor limite excedido ou faltam valores!");
					} else if (y > 450) {
						JOptionPane.showMessageDialog(null, "Valor limite excedido ou faltam valores!");
					} else if (x > 850 && y > 450) {
						JOptionPane.showMessageDialog(null, "Valor limite excedido ou faltam valores!");
					} else if (ILargura > 850 || ILargura == 0) {
						JOptionPane.showMessageDialog(null, "Valor limite excedido ou faltam valores!");
					} else if (Ialtura > 450 || Ialtura == 0) {
						JOptionPane.showMessageDialog(null, "Valor limite excedido ou faltam valores!");
					} else if (ILargura > 850 && Ialtura > 450) {
						JOptionPane.showMessageDialog(null, "Valor limite excedido");
					} else {
						telaCir.dispose();
						Graphics2D g2d = (Graphics2D) panel.getGraphics();
						Integer XCor1 = Integer.parseInt(jtXInicioCor1.getText());
						Integer YCor1 = Integer.parseInt(jtYInicioCor1.getText());
						Integer XCor2 = Integer.parseInt(jtXInicioCor2.getText());
						Integer YCor2 = Integer.parseInt(jtYInicioCor2.getText());

						GradientPaint gfx = new GradientPaint(XCor1, YCor1, corEscolhidaInicial, XCor2, YCor2,
								corEscolhidaFinal, true);

						g2d.setPaint(gfx);
						RenderingHints rh = new RenderingHints((RenderingHints.KEY_ANTIALIASING),
								(RenderingHints.VALUE_ANTIALIAS_ON));
						rh.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
						g2d.setRenderingHints(rh);
						g2d.fillRoundRect(x, y, ILargura, Ialtura, IArclargura, IArcaltura);

						DeshabilitaBotaoCor();

						bri = new BufferedImage(panel.getWidth(), panel.getHeight(), BufferedImage.TYPE_INT_ARGB);
						g2dSave = bri.createGraphics();
						g2dSave.setPaint(gfx);
						g2dSave.fillRoundRect(x, y, ILargura, Ialtura, IArclargura, IArcaltura);

						briBTMPEJPG = new BufferedImage(panel.getWidth(), panel.getHeight(),
								BufferedImage.TYPE_INT_RGB);
						g2dSave = briBTMPEJPG.createGraphics();
						g2dSave.setPaint(gfx);
						g2dSave.fillRoundRect(x, y, ILargura, Ialtura, IArclargura, IArcaltura);
						// g2d.fillRoundRect(x, y, width, height,
						// arcWidth,arcHeight);

						Salvar.setEnabled(true);
						Limpar.setEnabled(true);

						JOptionPane.showMessageDialog(null, "Que obra prima! Agora é possível salvar seu desenho.");
					}
				}
			}
		});
	}

	public static void DesenhaArco() {
		telaArc = new JFrame();
		telaArc.setTitle("Dados Arco");
		telaArc.setSize(300, 320);
		telaArc.setLocationRelativeTo(null);
		telaArc.setResizable(false);
		telaArc.setLayout(null);
		telaArc.setVisible(true);

		JLabel informacao = new JLabel("Informe as medidas do seu arco");
		telaArc.add(informacao);
		informacao.setBounds(50, 20, 250, 30);

		JLabel jposX = new JLabel("Posição X");
		telaArc.add(jposX);
		jposX.setBounds(15, 60, 100, 30);

		JTextField posX = new JTextField(20);
		telaArc.add(posX);
		posX.setBounds(15, 90, 80, 20);

		JLabel jposY = new JLabel("Posição Y");
		telaArc.add(jposY);
		jposY.setBounds(15, 120, 100, 30);

		JTextField posY = new JTextField(20);
		telaArc.add(posY);
		posY.setBounds(15, 150, 80, 20);

		JLabel jwidth = new JLabel("Largura");
		telaArc.add(jwidth);
		jwidth.setBounds(180, 60, 100, 30);

		JTextField jtwidth = new JTextField(20);
		telaArc.add(jtwidth);
		jtwidth.setBounds(180, 90, 80, 20);

		JLabel jheigh = new JLabel("Altura");
		telaArc.add(jheigh);
		jheigh.setBounds(180, 120, 100, 30);

		JTextField jtHeigh = new JTextField(20);
		telaArc.add(jtHeigh);
		jtHeigh.setBounds(180, 150, 80, 20);

		JLabel jStartAngle = new JLabel("Ângulo de início");
		telaArc.add(jStartAngle);
		jStartAngle.setBounds(15, 180, 100, 30);

		JTextField StartarcAngle = new JTextField(20);
		telaArc.add(StartarcAngle);
		StartarcAngle.setBounds(15, 210, 80, 20);

		JLabel jarcAngle = new JLabel("Ângulo do Arco");
		telaArc.add(jarcAngle);
		jarcAngle.setBounds(180, 180, 100, 30);

		JTextField jtarcAngle = new JTextField(20);
		telaArc.add(jtarcAngle);
		jtarcAngle.setBounds(180, 210, 80, 20);

		OkArc = new JButton("Desenhar");
		OkArc.setBounds(90, 240, 100, 30);
		telaArc.add(OkArc);

		OkArc.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {

				if (posX.getText().isEmpty() || posY.getText().isEmpty() || jtwidth.getText().isEmpty()
						|| jtHeigh.getText().isEmpty() || StartarcAngle.getText().isEmpty()
						|| jtarcAngle.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Informe todos os valores!");
				} else {

					Integer x = Integer.parseInt(posX.getText());
					Integer y = Integer.parseInt(posY.getText());
					Integer ILargura = Integer.parseInt(jtwidth.getText());
					Integer Ialtura = Integer.parseInt(jtHeigh.getText());
					Integer IStartAngle = Integer.parseInt(StartarcAngle.getText());
					Integer IArcAngle = Integer.parseInt(jtarcAngle.getText());

					if (x > 850) {
						JOptionPane.showMessageDialog(null, "Valor limite excedido ou faltam valores!");
					} else if (y > 450) {
						JOptionPane.showMessageDialog(null, "Valor limite excedido ou faltam valores!");
					} else if (x > 850 && y > 450) {
						JOptionPane.showMessageDialog(null, "Valor limite excedido ou faltam valores!");
					} else if (ILargura > 850 || ILargura == 0) {
						JOptionPane.showMessageDialog(null, "Valor limite excedido ou faltam valores!");
					} else if (Ialtura > 450 || Ialtura == 0) {
						JOptionPane.showMessageDialog(null, "Valor limite excedido ou faltam valores!");
					} else if (ILargura > 850 && Ialtura > 450) {
						JOptionPane.showMessageDialog(null, "Valor limite excedido");
					} else {
						telaArc.dispose();
						Graphics2D g2d = (Graphics2D) panel.getGraphics();
						g2d.setColor(corEscolhida);
						RenderingHints rh = new RenderingHints((RenderingHints.KEY_ANTIALIASING),
								(RenderingHints.VALUE_ANTIALIAS_ON));
						rh.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
						g2d.setRenderingHints(rh);
						g2d.fillArc(x, y, ILargura, Ialtura, IStartAngle, IArcAngle);

						DeshabilitaBotaoCor();

						bri = new BufferedImage(panel.getWidth(), panel.getHeight(), BufferedImage.TYPE_INT_ARGB);
						g2dSave = bri.createGraphics();
						g2dSave.setColor(corEscolhida);
						g2dSave.fillArc(x, y, ILargura, Ialtura, IStartAngle, IArcAngle);

						briBTMPEJPG = new BufferedImage(panel.getWidth(), panel.getHeight(),
								BufferedImage.TYPE_INT_RGB);
						g2dSave = briBTMPEJPG.createGraphics();
						g2dSave.setColor(corEscolhida);
						g2dSave.fillArc(x, y, ILargura, Ialtura, IStartAngle, IArcAngle);
						// g2d.fillArc(x, y, width, height, startAngle,
						// arcAngle);

						Salvar.setEnabled(true);
						Limpar.setEnabled(true);

						JOptionPane.showMessageDialog(null, "Que obra prima! Agora é possível salvar seu desenho.");
					}
				}
			}
		});
	}

	public static void DesenhaArcoDegrade() {
		telaArc = new JFrame();
		telaArc.setTitle("Dados Arco");
		telaArc.setSize(300, 320);
		telaArc.setLocationRelativeTo(null);
		telaArc.setResizable(false);
		telaArc.setLayout(null);
		telaArc.setVisible(true);

		JLabel informacao = new JLabel("Informe as medidas do seu arco");
		telaArc.add(informacao);
		informacao.setBounds(50, 20, 250, 30);

		JLabel jposX = new JLabel("Posição X");
		telaArc.add(jposX);
		jposX.setBounds(15, 60, 100, 30);

		JTextField posX = new JTextField(20);
		telaArc.add(posX);
		posX.setBounds(15, 90, 80, 20);

		JLabel jposY = new JLabel("Posição Y");
		telaArc.add(jposY);
		jposY.setBounds(15, 120, 100, 30);

		JTextField posY = new JTextField(20);
		telaArc.add(posY);
		posY.setBounds(15, 150, 80, 20);

		JLabel jwidth = new JLabel("Largura");
		telaArc.add(jwidth);
		jwidth.setBounds(180, 60, 100, 30);

		JTextField jtwidth = new JTextField(20);
		telaArc.add(jtwidth);
		jtwidth.setBounds(180, 90, 80, 20);

		JLabel jheigh = new JLabel("Altura");
		telaArc.add(jheigh);
		jheigh.setBounds(180, 120, 100, 30);

		JTextField jtHeigh = new JTextField(20);
		telaArc.add(jtHeigh);
		jtHeigh.setBounds(180, 150, 80, 20);

		JLabel jStartAngle = new JLabel("Ângulo de início");
		telaArc.add(jStartAngle);
		jStartAngle.setBounds(15, 180, 100, 30);

		JTextField StartarcAngle = new JTextField(20);
		telaArc.add(StartarcAngle);
		StartarcAngle.setBounds(15, 210, 80, 20);

		JLabel jarcAngle = new JLabel("Ângulo do Arco");
		telaArc.add(jarcAngle);
		jarcAngle.setBounds(180, 180, 100, 30);

		JTextField jtarcAngle = new JTextField(20);
		telaArc.add(jtarcAngle);
		jtarcAngle.setBounds(180, 210, 80, 20);

		OkArc = new JButton("Desenhar");
		OkArc.setBounds(90, 240, 100, 30);
		telaArc.add(OkArc);

		OkArc.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {

				if (posX.getText().isEmpty() || posY.getText().isEmpty() || jtwidth.getText().isEmpty()
						|| jtHeigh.getText().isEmpty() || StartarcAngle.getText().isEmpty()
						|| jtarcAngle.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Informe todos os valores!");
				} else {

					Integer x = Integer.parseInt(posX.getText());
					Integer y = Integer.parseInt(posY.getText());
					Integer ILargura = Integer.parseInt(jtwidth.getText());
					Integer Ialtura = Integer.parseInt(jtHeigh.getText());
					Integer IStartAngle = Integer.parseInt(StartarcAngle.getText());
					Integer IArcAngle = Integer.parseInt(jtarcAngle.getText());

					if (x > 850) {
						JOptionPane.showMessageDialog(null, "Valor limite excedido ou faltam valores!");
					} else if (y > 450) {
						JOptionPane.showMessageDialog(null, "Valor limite excedido ou faltam valores!");
					} else if (x > 850 && y > 450) {
						JOptionPane.showMessageDialog(null, "Valor limite excedido ou faltam valores!");
					} else if (ILargura > 850 || ILargura == 0) {
						JOptionPane.showMessageDialog(null, "Valor limite excedido ou faltam valores!");
					} else if (Ialtura > 450 || Ialtura == 0) {
						JOptionPane.showMessageDialog(null, "Valor limite excedido ou faltam valores!");
					} else if (ILargura > 850 && Ialtura > 450) {
						JOptionPane.showMessageDialog(null, "Valor limite excedido");
					} else {
						telaArc.dispose();
						Graphics2D g2d = (Graphics2D) panel.getGraphics();

						Integer XCor1 = Integer.parseInt(jtXInicioCor1.getText());
						Integer YCor1 = Integer.parseInt(jtYInicioCor1.getText());
						Integer XCor2 = Integer.parseInt(jtYInicioCor2.getText());
						Integer YCor2 = Integer.parseInt(jtYInicioCor2.getText());

						GradientPaint gfx = new GradientPaint(XCor1, YCor1, corEscolhidaInicial, XCor2, YCor2,
								corEscolhidaFinal, true);

						g2d.setPaint(gfx);
						RenderingHints rh = new RenderingHints((RenderingHints.KEY_ANTIALIASING),
								(RenderingHints.VALUE_ANTIALIAS_ON));
						rh.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
						g2d.setRenderingHints(rh);
						g2d.fillArc(x, y, ILargura, Ialtura, IStartAngle, IArcAngle);

						DeshabilitaBotaoCor();

						bri = new BufferedImage(panel.getWidth(), panel.getHeight(), BufferedImage.TYPE_INT_ARGB);
						g2dSave = bri.createGraphics();
						g2dSave.setPaint(gfx);
						g2dSave.fillArc(x, y, ILargura, Ialtura, IStartAngle, IArcAngle);

						briBTMPEJPG = new BufferedImage(panel.getWidth(), panel.getHeight(),
								BufferedImage.TYPE_INT_RGB);
						g2dSave = briBTMPEJPG.createGraphics();
						g2dSave.setPaint(gfx);
						g2dSave.fillArc(x, y, ILargura, Ialtura, IStartAngle, IArcAngle);
						// g2d.fillArc(x, y, width, height, startAngle,
						// arcAngle);

						Salvar.setEnabled(true);
						Limpar.setEnabled(true);

						JOptionPane.showMessageDialog(null, "Que obra prima! Agora é possível salvar seu desenho.");
					}
				}
			}
		});
	}

	public static void EscolheCor() {

		jbcor1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				corEscolhida = cor1;
				if (contador == 1) {
					telaEscolha.dispose();
					telaEscolha.setVisible(false);
					TelaEscolha();
				} else if (contador == 0) {
					TelaEscolha();
				}
				contador = 0;
			}
		});

		jbcor2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				corEscolhida = cor2;
				if (contador == 1) {
					TelaEscolha();
				} else if (contador == 0) {
					TelaEscolha();
				}
				contador = 0;
			}
		});

		jbcor3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				corEscolhida = cor3;
				if (contador == 1) {
					TelaEscolha();
					// contador--;
				} else if (contador == 0) {
					TelaEscolha();
				}
			}
		});

		jbcor4.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				corEscolhida = cor4;
				if (contador == 1) {
					TelaEscolha();
					// contador--;
				} else if (contador == 0) {
					TelaEscolha();
				}
			}
		});

		jbcor5.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				corEscolhida = cor5;
				if (contador == 1) {
					TelaEscolha();
					// contador--;
				} else if (contador == 0) {
					TelaEscolha();
				}
			}
		});

		jbcor6.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				corEscolhida = cor6;
				if (contador == 1) {
					TelaEscolha();
					// contador--;
				} else if (contador == 0) {
					TelaEscolha();
				}
			}
		});

		jbcor7.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				corEscolhida = cor7;
				if (contador == 1) {
					TelaEscolha();
					// contador--;
				} else if (contador == 0) {
					TelaEscolha();
				}
			}
		});

		jbcor8.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				corEscolhida = cor8;
				if (contador == 1) {
					TelaEscolha();
					// contador--;
				} else if (contador == 0) {
					TelaEscolha();
				}
			}
		});

		jbcor9.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				corEscolhida = cor9;
				if (contador == 1) {
					TelaEscolha();
					// contador--;
				} else if (contador == 0) {
					TelaEscolha();
				}
			}
		});

		jbcor10.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				corEscolhida = cor10;
				if (contador == 1) {
					TelaEscolha();
					// contador--;
				} else if (contador == 0) {
					TelaEscolha();
				}
			}
		});

		jbcor11.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				corEscolhida = cor11;
				if (contador == 1) {
					TelaEscolha();
					// contador--;
				} else if (contador == 0) {
					TelaEscolha();
				}
			}
		});

		jbcor12.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				corEscolhida = cor12;
				if (contador == 1) {
					TelaEscolha();
					// contador--;
				} else if (contador == 0) {
					TelaEscolha();
				}
			}
		});

		jbcor13.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				corEscolhida = cor13;
				if (contador == 1) {
					TelaEscolha();
					// contador--;
				} else if (contador == 0) {
					TelaEscolha();
				}
			}
		});

		jbcor14.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				corEscolhida = cor14;
				if (contador == 1) {
					TelaEscolha();
					// contador--;
				} else if (contador == 0) {
					TelaEscolha();
				}
			}
		});

		jbcor15.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				corEscolhida = cor15;
				if (contador == 1) {
					TelaEscolha();
					// contador--;
				} else if (contador == 0) {
					TelaEscolha();
				}
			}
		});

		jbcor16.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				corEscolhida = cor16;
				if (contador == 1) {
					TelaEscolha();
					// contador--;
				} else if (contador == 0) {
					TelaEscolha();
				}
			}
		});

	}

	public static void EscolheCorInicio() {
		jbcor1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.out.println(contador);
				if (contador == 0) {
					// terminar - fazer ele abrir e fechar esta tela
					if (corEscolhidaInicial == null) {
						corEscolhidaInicial = cor1;
						contador = 2;
					}

				} else if (contador == 1) {
					corEscolhidaInicial = cor1;
					// telaEscolha.setVisible(false);
					// telaEscolha.dispose();
					contador = 2;
				}  else if (corEscolhidaFinal != null) {
					corEscolhidaFinal = cor1;
					if (contador == 2) {
						// telaEscolha.setVisible(false);
						// telaEscolhaDegrade.setVisible(true);
						TelaEscolhaDegrade();
					}
				}
			}
		});

		jbcor2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.out.println(contador);
				if (contador == 0) {
					if (corEscolhidaInicial == null) {
						corEscolhidaInicial = cor2;
						contador = 2;
					}

				} else if (contador == 1) {
					// telaEscolha.setVisible(false);
					// telaEscolha.dispose();
					contador = 2;
				} else if (corEscolhidaFinal != null) {
					corEscolhidaFinal = cor2;
					if (contador == 2) {
						// telaEscolha.setVisible(false);
						// telaEscolhaDegrade.setVisible(true);
						TelaEscolhaDegrade();
					}
				}
			}
		});

		jbcor3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (contador == 0) {
					if (corEscolhidaInicial == null) {
						corEscolhidaInicial = cor3;
						contador = 2;
					}

				} else if (contador == 1) {
					// telaEscolha.setVisible(false);
					// telaEscolha.dispose();
					contador = 2;
				} else if (corEscolhidaFinal != null) {
					corEscolhidaFinal = cor3;
					if (contador == 2) {
						// telaEscolha.setVisible(false);
						// telaEscolhaDegrade.setVisible(true);
						TelaEscolhaDegrade();
					}
				}
			}
		});

		jbcor4.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (contador == 0) {
					if (corEscolhidaInicial == null) {
						corEscolhidaInicial = cor4;
						contador = 2;
					}

				} else if (contador == 1) {
					// telaEscolha.setVisible(false);
					// telaEscolha.dispose();
					contador = 2;
				} else if (corEscolhidaFinal != null) {
					corEscolhidaFinal = cor4;
					if (contador == 2) {
						// telaEscolha.setVisible(false);
						// telaEscolhaDegrade.setVisible(true);
						TelaEscolhaDegrade();
					}
				}
			}
		});

		jbcor5.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (contador == 0) {
					if (corEscolhidaInicial == null) {
						corEscolhidaInicial = cor5;
						contador = 2;
					}

				} else if (contador == 1) {
					// telaEscolha.setVisible(false);
					// telaEscolha.dispose();
					contador = 2;
				} else if (corEscolhidaFinal != null) {
					corEscolhidaFinal = cor5;
					if (contador == 2) {
						// telaEscolha.setVisible(false);
						// telaEscolhaDegrade.setVisible(true);
						TelaEscolhaDegrade();
					}
				}
			}
		});

		jbcor6.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (contador == 0) {
					if (corEscolhidaInicial == null) {
						corEscolhidaInicial = cor6;
						contador = 2;
					}

				} else if (contador == 1) {
					// telaEscolha.setVisible(false);
					// telaEscolha.dispose();
					contador = 2;
				} else if (corEscolhidaFinal != null) {
					corEscolhidaFinal = cor6;
					if (contador == 2) {
						// telaEscolha.setVisible(false);
						// telaEscolhaDegrade.setVisible(true);
						TelaEscolhaDegrade();
					}
				}
			}
		});

		jbcor7.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (contador == 0) {
					if (corEscolhidaInicial == null) {
						corEscolhidaInicial = cor7;
						contador = 2;
					}

				} else if (contador == 1) {
					// telaEscolha.setVisible(false);
					// telaEscolha.dispose();
					contador = 2;
				} else if (corEscolhidaFinal != null) {
					corEscolhidaFinal = cor7;
					if (contador == 2) {
						// telaEscolha.setVisible(false);
						// telaEscolhaDegrade.setVisible(true);
						TelaEscolhaDegrade();
					}
				}
			}
		});

		jbcor8.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (contador == 0) {
					if (corEscolhidaInicial == null) {
						corEscolhidaInicial = cor8;
						contador = 2;
					}

				} else if (contador == 1) {
					// telaEscolha.setVisible(false);
					// telaEscolha.dispose();
					contador = 2;
				} else if (corEscolhidaFinal != null) {
					corEscolhidaFinal = cor8;
					if (contador == 2) {
						// telaEscolha.setVisible(false);
						// telaEscolhaDegrade.setVisible(true);
						TelaEscolhaDegrade();
					}
				}
			}
		});

		jbcor9.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (contador == 0) {
					if (corEscolhidaInicial == null) {
						corEscolhidaInicial = cor9;
						contador = 2;
					}

				} else if (contador == 1) {
					// telaEscolha.setVisible(false);
					// telaEscolha.dispose();
					contador = 2;
				} else if (corEscolhidaFinal != null) {
					corEscolhidaFinal = cor9;
					if (contador == 2) {
						// telaEscolha.setVisible(false);
						// telaEscolhaDegrade.setVisible(true);
						TelaEscolhaDegrade();
					}
				}
			}
		});

		jbcor10.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (contador == 0) {
					if (corEscolhidaInicial == null) {
						corEscolhidaInicial = cor10;
						contador = 2;
					}

				} else if (contador == 1) {
					// telaEscolha.setVisible(false);
					// telaEscolha.dispose();
					contador = 2;
				} else if (corEscolhidaFinal != null) {
					corEscolhidaFinal = cor10;
					if (contador == 2) {
						// telaEscolha.setVisible(false);
						// telaEscolhaDegrade.setVisible(true);
						TelaEscolhaDegrade();
					}
				}
			}
		});

		jbcor11.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (contador == 0) {
					if (corEscolhidaInicial == null) {
						corEscolhidaInicial = cor11;
						contador = 2;
					}

				} else if (contador == 1) {
					// telaEscolha.setVisible(false);
					// telaEscolha.dispose();
					contador = 2;
				} else if (corEscolhidaFinal != null) {
					corEscolhidaFinal = cor11;
					if (contador == 2) {
						// telaEscolha.setVisible(false);
						// telaEscolhaDegrade.setVisible(true);
						TelaEscolhaDegrade();
					}
				}
			}
		});

		jbcor12.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (contador == 0) {
					if (corEscolhidaInicial == null) {
						corEscolhidaInicial = cor12;
						contador = 2;
					}

				} else if (contador == 1) {
					// telaEscolha.setVisible(false);
					// telaEscolha.dispose();
					contador = 2;
				} else if (corEscolhidaFinal != null) {
					corEscolhidaFinal = cor12;
					if (contador == 2) {
						// telaEscolha.setVisible(false);
						// telaEscolhaDegrade.setVisible(true);
						TelaEscolhaDegrade();
					}
				}
			}
		});

		jbcor13.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (contador == 0) {
					if (corEscolhidaInicial == null) {
						corEscolhidaInicial = cor13;
						contador = 2;
					}

				} else if (contador == 1) {
					// telaEscolha.setVisible(false);
					// telaEscolha.dispose();
					contador = 2;
				} else if (corEscolhidaFinal != null) {
					corEscolhidaFinal = cor13;
					if (contador == 2) {
						// telaEscolha.setVisible(false);
						// telaEscolhaDegrade.setVisible(true);
						TelaEscolhaDegrade();
					}
				}
			}
		});

		jbcor14.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (contador == 0) {
					if (corEscolhidaInicial == null) {
						corEscolhidaInicial = cor14;
						contador = 2;
					}

				} else if (contador == 1) {
					// telaEscolha.setVisible(false);
					// telaEscolha.dispose();
					contador = 2;
				} else if (corEscolhidaFinal != null) {
					corEscolhidaFinal = cor14;
					if (contador == 2) {
						// telaEscolha.setVisible(false);
						// telaEscolhaDegrade.setVisible(true);
						TelaEscolhaDegrade();
					}
				}
			}
		});

		jbcor15.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (contador == 0) {
					if (corEscolhidaInicial == null) {
						corEscolhidaInicial = cor15;
						contador = 2;
					}

				} else if (contador == 1) {
					// telaEscolha.setVisible(false);
					// telaEscolha.dispose();
					contador = 2;
				} else if (corEscolhidaFinal != null) {
					corEscolhidaFinal = cor15;
					if (contador == 2) {
						// telaEscolha.setVisible(false);
						// telaEscolhaDegrade.setVisible(true);
						TelaEscolhaDegrade();
					}
				}
			}
		});

		jbcor16.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (contador == 0) {
					if (corEscolhidaInicial == null) {
						corEscolhidaInicial = cor16;
						contador = 2;
					}

				} else if (contador == 1) {
					// telaEscolha.setVisible(false);
					// telaEscolha.dispose();
					contador = 2;
				} else if (corEscolhidaFinal != null) {
					corEscolhidaFinal = cor16;
					if (contador == 2) {
						// telaEscolha.setVisible(false);
						// telaEscolhaDegrade.setVisible(true);
						TelaEscolhaDegrade();
					}
				}
			}
		});

	}

	public static void EscolheCorFinal() {
		jbcor1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				corEscolhidaFinal = new Color(0, 0, 0);
				// TelaEscolhaDegrade();
			}
		});

		jbcor2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				corEscolhidaFinal = new Color(128, 128, 128);
				// TelaEscolhaDegrade();
			}
		});

		jbcor3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				corEscolhidaFinal = new Color(0, 0, 139);
				// TelaEscolhaDegrade();
			}
		});

		jbcor4.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				corEscolhidaFinal = new Color(0, 0, 255);
				// TelaEscolhaDegrade();
			}
		});

		jbcor5.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				corEscolhidaFinal = new Color(0, 128, 0);
				// TelaEscolhaDegrade();

			}
		});

		jbcor6.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				corEscolhidaFinal = new Color(0, 100, 0);
				// TelaEscolhaDegrade();

			}
		});

		jbcor7.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				corEscolhidaFinal = new Color(128, 0, 0);
				// TelaEscolhaDegrade();

			}
		});

		jbcor8.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				corEscolhidaFinal = new Color(255, 0, 0);
				// TelaEscolhaDegrade();

			}
		});

		jbcor9.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				corEscolhidaFinal = new Color(255, 69, 0);
				// TelaEscolhaDegrade();

			}
		});

		jbcor10.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				corEscolhidaFinal = new Color(255, 165, 0);
				// TelaEscolhaDegrade();
			}
		});

		jbcor11.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				corEscolhidaFinal = new Color(255, 215, 0);
				// TelaEscolhaDegrade();

			}
		});

		jbcor12.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				corEscolhidaFinal = new Color(255, 255, 0);
				// TelaEscolhaDegrade();

			}
		});

		jbcor13.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				corEscolhidaFinal = new Color(255, 20, 147);
				// TelaEscolhaDegrade();

			}
		});

		jbcor14.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				corEscolhidaFinal = new Color(255, 192, 203);
				// TelaEscolhaDegrade();

			}
		});

		jbcor15.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				corEscolhidaFinal = new Color(128, 0, 128);
				// TelaEscolhaDegrade();

			}
		});

		jbcor16.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				corEscolhidaFinal = new Color(238, 130, 238);
				// TelaEscolhaDegrade();

			}
		});

	}

	public static void VerificaEscolha() {
		if (retangulo.isSelected()) {
			telaEscolha.dispose();
			DesenhaRetangulo();
		} else if (circulo.isSelected()) {
			telaEscolha.dispose();
			DesenhaCirculo();
		} else if (arco.isSelected()) {
			telaEscolha.dispose();
			DesenhaArco();
		} else {
			JOptionPane.showMessageDialog(null, "Escolha uma opção!");
		}
	}

	public static void VerificaEscolhaDegrade() {
		if (retangulo.isSelected()) {
			telaEscolhaDegrade.dispose();
			DesenhaRetanguloDegrade();
		} else if (circulo.isSelected()) {
			telaEscolhaDegrade.dispose();
			DesenhaCirculoDegrade();
		} else if (arco.isSelected()) {
			telaEscolhaDegrade.dispose();
			DesenhaArcoDegrade();
		} else {
			JOptionPane.showMessageDialog(null, "Escolha uma opção!");
		}
	}

	public static void Degrade() {

		telaDegrade = new JFrame();
		telaDegrade.setTitle("Dados Degradê");
		telaDegrade.setSize(300, 250);
		telaDegrade.setLocationRelativeTo(null);
		telaDegrade.setResizable(false);
		telaDegrade.setLayout(null);
		telaDegrade.setVisible(true);

		JLabel informacao = new JLabel("Dados primeira e segunda cor");
		telaDegrade.add(informacao);
		informacao.setBounds(55, 20, 280, 30);

		JLabel jlXInicioCor1 = new JLabel("X início Primeira cor");
		telaDegrade.add(jlXInicioCor1);
		jlXInicioCor1.setBounds(15, 60, 150, 30);

		jtXInicioCor1 = new JTextField(30);
		telaDegrade.add(jtXInicioCor1);
		jtXInicioCor1.setBounds(15, 90, 80, 20);

		JLabel jlYInicioCor1 = new JLabel("Y início Primeira cor");
		telaDegrade.add(jlYInicioCor1);
		jlYInicioCor1.setBounds(15, 120, 150, 30);

		jtYInicioCor1 = new JTextField(20);
		telaDegrade.add(jtYInicioCor1);
		jtYInicioCor1.setBounds(15, 150, 80, 20);

		JLabel jlXInicioCor2 = new JLabel("X início Segunda cor");
		telaDegrade.add(jlXInicioCor2);
		jlXInicioCor2.setBounds(150, 60, 150, 30);

		jtXInicioCor2 = new JTextField(20);
		telaDegrade.add(jtXInicioCor2);
		jtXInicioCor2.setBounds(150, 90, 80, 20);

		JLabel jlYInicioCor2 = new JLabel("Y início Segunda cor");
		telaDegrade.add(jlYInicioCor2);
		jlYInicioCor2.setBounds(150, 120, 150, 30);

		jtYInicioCor2 = new JTextField(20);
		telaDegrade.add(jtYInicioCor2);
		jtYInicioCor2.setBounds(150, 150, 80, 20);

		OkDegrade = new JButton("Ok");
		OkDegrade.setBounds(100, 180, 100, 30);
		telaDegrade.add(OkDegrade);

		OkDegrade.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				telaDegrade.dispose();
				HabilitaBotaoCor();
				JOptionPane.showMessageDialog(null, "Escolha a inicial e cor final do desenho respectivamente, "
						+ "em seguida, selecione o polígono que deseja desenhar");
				System.out.println(contador);
				if (contador == 0) {
					// contador = 0;
					EscolheCorInicio();
				} else {
					contador = 1;
					EscolheCorInicio();

				}
			}
		});
	}

	public static void Salvar() {
		telaEscolha = new JFrame();
		telaEscolha.setTitle("Escolha");
		telaEscolha.setSize(285, 90);
		telaEscolha.setLocationRelativeTo(null);
		telaEscolha.setResizable(false);
		telaEscolha.setLayout(new GridLayout(1, 2));
		telaEscolha.setVisible(true);

		ButtonGroup grupo1 = new ButtonGroup();

		png = new JRadioButton("PNG", false);
		jpg = new JRadioButton("JPEG", false);
		bitmap = new JRadioButton("BITMAP", false);
		gif = new JRadioButton("GIF", false);

		grupo1.add(png);
		grupo1.add(jpg);
		grupo1.add(bitmap);
		grupo1.add(gif);

		telaEscolha.add(png);
		telaEscolha.add(jpg);
		telaEscolha.add(bitmap);
		telaEscolha.add(gif);

		png.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				telaEscolha.dispose();
				try {
					String caminho = "C:/temp/Desenho.png";
					ImageIO.write(bri, "PNG", new File(caminho));
					JOptionPane.showMessageDialog(null, "Desenho salvo com sucesso em C:/temp/");
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});

		jpg.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				telaEscolha.dispose();
				try {
					String caminho = "C:/temp/Desenho.jpg";
					ImageIO.write(briBTMPEJPG, "JPG", new File(caminho));
					JOptionPane.showMessageDialog(null, "Desenho salvo com sucesso em C:/temp/");
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});

		bitmap.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				telaEscolha.dispose();

				try {
					String caminho = "C:/temp/Desenho.bmp";
					ImageIO.write(briBTMPEJPG, "BMP", new File(caminho));
					JOptionPane.showMessageDialog(null, "Desenho salvo com sucesso em C:/temp/");
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});

		gif.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				telaEscolha.dispose();
				try {
					String caminho = "C:/temp/Desenho.gif";
					ImageIO.write(bri, "GIF", new File(caminho));
					JOptionPane.showMessageDialog(null, "Desenho salvo com sucesso em C:/temp/");
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
	}

	public static void DesenhoLivre() {
		JFrame tela = new JFrame();
		tela.setTitle("Desenho Livre");
		tela.setSize(300, 300);
		tela.setLocationRelativeTo(null);
		tela.add(new PaintSurface(), BorderLayout.CENTER);
		tela.setVisible(true);

		if (JOptionPane.showConfirmDialog(null,
				"Deseja desenhar retângulos?" + "Se sim, clique em sim, se quiser círculos, clique em não",
				"Confirmação", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
			escolha = 1;
		} else {
			escolha = 2;
		}
	}

	public static void DeshabilitaBotaoCor() {
		jbcor1.setEnabled(false);
		jbcor2.setEnabled(false);
		jbcor3.setEnabled(false);
		jbcor4.setEnabled(false);
		jbcor5.setEnabled(false);
		jbcor6.setEnabled(false);
		jbcor7.setEnabled(false);
		jbcor8.setEnabled(false);
		jbcor9.setEnabled(false);
		jbcor10.setEnabled(false);
		jbcor11.setEnabled(false);
		jbcor12.setEnabled(false);
		jbcor13.setEnabled(false);
		jbcor14.setEnabled(false);
		jbcor15.setEnabled(false);
		jbcor16.setEnabled(false);
	}

	public static void HabilitaBotaoCor() {
		jbcor1.setEnabled(true);
		jbcor2.setEnabled(true);
		jbcor3.setEnabled(true);
		jbcor4.setEnabled(true);
		jbcor5.setEnabled(true);
		jbcor6.setEnabled(true);
		jbcor7.setEnabled(true);
		jbcor8.setEnabled(true);
		jbcor9.setEnabled(true);
		jbcor10.setEnabled(true);
		jbcor11.setEnabled(true);
		jbcor12.setEnabled(true);
		jbcor13.setEnabled(true);
		jbcor14.setEnabled(true);
		jbcor15.setEnabled(true);
		jbcor16.setEnabled(true);
	}
}
