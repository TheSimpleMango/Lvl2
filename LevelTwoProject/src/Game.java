import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

@SuppressWarnings("serial")
public class Game extends JPanel implements MouseListener, ActionListener, KeyListener, Runnable{
	JFrame menuFrame = new JFrame("Pong");
	BufferedImage gameImage;
	Ball ball = new Ball(275, 275, 50, 50, 1);
	Paddle topPaddle = new Paddle(250, 0, 169, 50, "/bluebirds.png");
	Paddle botPaddle = new Paddle(250, 550, 100, 26, "/squirrels.png");
	Paddle leftPaddle = new Paddle(0, 250, 50, 100, "/leftbunny.png");
	Paddle rightPaddle = new Paddle(550, 250, 50, 100, "/bunny.png");
	Timer timer = new Timer(20, this);

	public void start() {
		try {
			gameImage = ImageIO.read(Game.class
					.getResourceAsStream("image.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		menuFrame.setSize(600, 600);
		menuFrame.setVisible(true);
		menuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		menuFrame.add(this);
		menuFrame.validate();
		menuFrame.repaint();
		
		this.setLayout(null);
		this.addMouseListener(this);
		menuFrame.addKeyListener(this);
		timer.start();
	}

	Font backFont = new Font("Arial", Font.PLAIN, 12);

	public void paint(Graphics g)
	{
		g.drawImage(gameImage, 0, 0, 601, 601, null);
		g.setColor(Color.black);
		g.fillRect(10, 10, 40, 15);

		g.setColor(Color.white);
		g.setFont(backFont);
		g.drawString("<back", 10, 22);
		
		topPaddle.paint(g);
		leftPaddle.paint(g);
		rightPaddle.paint(g);
		botPaddle.paint(g);
		
		ball.paint(g);
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		int mX = e.getX();
		int mY = e.getY();
		System.out.println(mX + ", " + mY);
		if (mX > 10 && mX < 50 && mY > 10 && mY < 25) {
			System.out.println("gameBackButton" + 10 + ", " + 10 + ", " + 50
					+ ", " + 25);
			repaint();
			MainMenu newMenu = new MainMenu();
			newMenu.start();
			menuFrame.dispose();
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if (ball.started) {
			ball.update(topPaddle, botPaddle, leftPaddle, rightPaddle);
		}
		else{
			System.out.println("start 1");
			ball.start(topPaddle, botPaddle, leftPaddle, rightPaddle);
		}
		topPaddle.update();
		leftPaddle.update();
		rightPaddle.update();
		botPaddle.update();
		repaint();
		
		if(ball.ballX >= 551 || ball.ballX <= 0 || ball.ballY >= 551 || ball.ballY <= 0){
			timer.stop();
			System.out.println(System.currentTimeMillis());
			int choice = JOptionPane.showConfirmDialog(null, "GAME OVER\nRESTART?", "End.", JOptionPane.YES_NO_OPTION);
			switch (choice) {
			case JOptionPane.YES_OPTION:
				ball.xSpeed = 0;
				ball.ySpeed = 0;
				Game newGame = new Game();
				newGame.start();
				menuFrame.dispose();
			break;
			case 1:
				MainMenu newMenu = new MainMenu();
				newMenu.start();
				menuFrame.dispose();
			break;
			
			default:
			break;
			}
		}
	}

	@Override
	public void keyPressed(KeyEvent keyPressed) {
		System.out.println("keyPressed");
		switch (keyPressed.getKeyCode()) {
		case KeyEvent.VK_W:
			leftPaddle.up = true;
			break;
		case KeyEvent.VK_S:
			leftPaddle.down = true;
			break;
		case KeyEvent.VK_A:
			topPaddle.left = true;
			break;
		case KeyEvent.VK_D:
			topPaddle.right = true;
			break;
		case KeyEvent.VK_UP:
			rightPaddle.up = true;
			break;
		case KeyEvent.VK_DOWN:
			rightPaddle.down = true;
			break;
		case KeyEvent.VK_LEFT:
			botPaddle.left = true;
			break;
		case KeyEvent.VK_RIGHT:
			botPaddle.right = true;
			break;
		case KeyEvent.VK_R:
			ball.xSpeed = 0;
			ball.ySpeed = 0;
			Game newGame = new Game();
			newGame.start();
			menuFrame.dispose();
			/*
			ball.ballX = 275;
			ball.ballY = 275;
			ball.ballSpeed = 1;
			ball.started = false;
			ball.score = "0";
			topPaddle.paddleX = 250;
			topPaddle.paddleY = 0;
			botPaddle.paddleX = 250;
			botPaddle.paddleY = 550;
			leftPaddle.paddleX = 0;
			leftPaddle.paddleY = 250;
			rightPaddle.paddleX = 550;
			rightPaddle.paddleY = 250;
			collisionTimer = 0;
			*/
			break;

		default:
			break;
		}
		this.repaint();
	}

	@Override
	public void keyReleased(KeyEvent keyReleased) {
		System.out.println("keyReleased");
		switch (keyReleased.getKeyCode()) {
		case KeyEvent.VK_W:
			leftPaddle.up = false;
			break;
		case KeyEvent.VK_S:
			leftPaddle.down = false;
			break;
		case KeyEvent.VK_A:
			topPaddle.left = false;
			break;
		case KeyEvent.VK_D:
			topPaddle.right = false;
			break;
		case KeyEvent.VK_UP:
			rightPaddle.up = false;
			break;
		case KeyEvent.VK_DOWN:
			rightPaddle.down = false;
			break;
		case KeyEvent.VK_LEFT:
			botPaddle.left = false;
			break;
		case KeyEvent.VK_RIGHT:
			botPaddle.right = false;
			break;

		default:
			break;
		}
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}

}