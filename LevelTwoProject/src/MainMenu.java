import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class MainMenu extends JPanel implements MouseListener{

	JFrame menuFrame = new JFrame("Pong");
	BufferedImage backgroundImage;

	public void start() {
		try {
			backgroundImage = ImageIO.read(MainMenu.class.getResourceAsStream("menuArt.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		menuFrame.setSize(601, 601);
		menuFrame.setVisible(true);
		menuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		menuFrame.add(this);
		menuFrame.validate();
		menuFrame.repaint();
		
		this.setLayout(null);
		this.addMouseListener(this);
	}

	int buttonWidth = 100;
	int buttonHeight = 30;
	Font font = new Font("Arial", Font.PLAIN, 20);

	public void paint(Graphics g) {
		g.drawImage(backgroundImage, 0, 0, 601, 601, null);
		g.setColor(Color.black);
		g.fillRect(300 - (buttonWidth / 2), 300 - (buttonHeight / 2)
				+ buttonHeight, buttonWidth, buttonHeight);
		g.fillRect(300 - (buttonWidth / 2), 300 - (buttonHeight / 2) - 20,
				buttonWidth, buttonHeight);
		g.fillRect(300 - (buttonWidth / 2), 300 - (buttonHeight / 2)
				- buttonHeight - 40, buttonWidth, buttonHeight);

		g.setColor(Color.white);
		g.setFont(font);
		g.drawString("START", 300 - (buttonWidth / 2) + 17,
				300 - (buttonHeight / 2) - 40 - 8);
		g.drawString("HELP", 300 - (buttonWidth / 2) + 25,
				300 - (buttonHeight / 2) - 20 + 22);
		g.drawString("EXIT", 300 - (buttonWidth / 2) + 29, 300
				- (buttonHeight / 2) + buttonHeight + 22);
	}

	int firstButtonX1 = 300 - (buttonWidth / 2);
	int firstButtonY1 = 300 - (buttonHeight / 2) - buttonHeight - 40;
	int firstButtonX2 = 300 - (buttonWidth / 2) + buttonWidth;
	int firstButtonY2 = 300 - (buttonHeight / 2) - buttonHeight - 40
			+ buttonHeight;
	int secondButtonX1 = 300 - (buttonWidth / 2);
	int secondButtonY1 = 300 - (buttonHeight / 2) - 20;
	int secondButtonX2 = 300 - (buttonWidth / 2) + buttonWidth;
	int secondButtonY2 = 300 - (buttonHeight / 2) - 20 + buttonHeight;
	int thirdButtonX1 = 300 - (buttonWidth / 2);
	int thirdButtonY1 = 300 - (buttonHeight / 2) + buttonHeight;
	int thirdButtonX2 = 300 - (buttonWidth / 2) + buttonWidth;
	int thirdButtonY2 = 300 - (buttonHeight / 2) + buttonHeight + buttonHeight;

	@Override
	public void mouseClicked(MouseEvent e) {
		int mX = e.getX();
		int mY = e.getY();
		System.out.println(mX + ", " + mY);
		if (mX > firstButtonX1 && mX < firstButtonX2 && mY > firstButtonY1
				&& mY < firstButtonY2) {
			System.out.println("startButton" + firstButtonX1 + ", "
					+ firstButtonX2 + ", " + firstButtonY1 + ", "
					+ firstButtonY2);
			Game newGame = new Game();
			newGame.start();
			menuFrame.dispose();
		} else if (mX > secondButtonX1 && mX < secondButtonX2
				&& mY > secondButtonY1 && mY < secondButtonY2) {
			System.out.println("settingsButton" + secondButtonX1 + ", "
					+ secondButtonX2 + ", " + secondButtonY1 + ", "
					+ secondButtonY2);
			Help helpScreen = new Help();
			helpScreen.setup();
			menuFrame.dispose();
		} else if (mX > thirdButtonX1 && mX < thirdButtonX2
				&& mY > thirdButtonY1 && mY < thirdButtonY2) {
			System.out.println("exitButton" + thirdButtonX1 + ", "
					+ thirdButtonX2 + ", " + thirdButtonY1 + ", "
					+ thirdButtonY2);
			System.exit(0);
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
}