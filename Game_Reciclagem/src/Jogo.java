import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Jogo extends JFrame {
	boolean mousePressionado = false;

	ImageIcon iconFundo = new ImageIcon(getClass().getResource("fundo.jpg"));
	ImageIcon iconLixeira = new ImageIcon(getClass().getResource("lixeira.png"));
	ImageIcon iconLixo = new ImageIcon(getClass().getResource("lixo.png"));

	JLabel LFundo = new JLabel(iconFundo);
	JLabel LLixeira = new JLabel(iconLixeira);
	JLabel LLixo = new JLabel(iconLixo);

	int posLixoX = 300;
	int posLixoY = 300;

	// Método Construtor
	public Jogo() {
		editarJanela();
		editarComponentes();
		addMovimentoTeclado();
		addMovimentoMouse();
	}

	public void addMovimentoMouse() {
		new Mover().start();
		LLixo.addMouseListener(new MouseListener() {

			public void mouseReleased(MouseEvent arg0) {// Parar de precionar mouse;
				mousePressionado = false;

			}

			public void mousePressed(MouseEvent arg0) {// Precionar mouse;
				mousePressionado = true;

			}

			public void mouseExited(MouseEvent arg0) {

			}

			public void mouseEntered(MouseEvent arg0) {

			}

			public void mouseClicked(MouseEvent arg0) {

			}
		});
	}

	// Criar Thread;
	public class Mover extends Thread {
		public void run() {
			while (true) {
				try {
					sleep(10);// a cada 10 milesimos, a imagem acompanha o mouse;
				} catch (Exception e) {
					e.printStackTrace();
				}
				if (mousePressionado) {
					Point ponto = getMousePosition();// Pega a coordenada atual do mouse;
					LLixo.setBounds(ponto.x - 90, ponto.y - 90, 180, 180);
				}
			}
		}
	}

	public void addMovimentoTeclado() {
		addKeyListener(new KeyListener() {

			public void keyTyped(KeyEvent arg0) {

			}

			public void keyReleased(KeyEvent arg0) {

			}

			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == 38) {// Para cima
					posLixoY -= 10;
				}
				if (e.getKeyCode() == 40) {// Para baixo
					posLixoY += 10;
				}
				if (e.getKeyCode() == 37) {// Esquerda
					posLixoX -= 10;
				}
				if (e.getKeyCode() == 39) {// Direita
					posLixoX += 10;
				}
				LLixo.setBounds(posLixoX, posLixoY, 180, 180);// Atualiza posição do lixo;

			}
		});
	}

	public void editarComponentes() {
		LFundo.setBounds(0, 0, 900, 529);// Editar tamanho e posição;
		LLixeira.setBounds(600, 250, 200, 229);
		LLixo.setBounds(posLixoX, posLixoY, 180, 180);
	}

	// Contém configuração da JFrame;
	public void editarJanela() {
		setTitle("Recicle");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(900, 529);// Define tamanho da janela;
		setResizable(false);// Tela não vai ser redimensionada;
		setLocationRelativeTo(null);// JFrame centralizada;
		setVisible(true);// JFrame visível;
		setLayout(null);// Sem layout na janela;
		add(LLixo);
		add(LLixeira);
		add(LFundo);

	}

	public static void main(String[] args) {
		new Jogo();

	}

}
