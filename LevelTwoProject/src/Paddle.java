import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Paddle{
	final int paddleSpeed = 18;
	int paddleX;
	int paddleY;
	int paddleWidth;
	int paddleHeight;
	boolean up;
	boolean down;
	boolean left;
	boolean right;
	String imageName;
	
	BufferedImage paddleImage;
	public Paddle(){}
	public Paddle(int paddleX, int paddleY, int paddleWidth, int paddleHeight, String imageName){
		this.paddleX = paddleX;
		this.paddleY = paddleY;
		this.paddleWidth = paddleWidth;
		this.paddleHeight = paddleHeight;
		this.imageName = imageName;
		
		try {
			paddleImage = ImageIO.read(this.getClass().getResourceAsStream(imageName));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void update(){
		if (up && paddleY > 0) {
			paddleY-=paddleSpeed;
		}
		else if (down && paddleY < 500) {
			paddleY+=paddleSpeed;
		}
		else if (left && paddleX > 0) {
			paddleX-=paddleSpeed;
		}
		else if (right && paddleX < 500) {
			paddleX+=paddleSpeed;
		}
	}
	public void paint(Graphics g){
		g.drawImage(paddleImage, paddleX, paddleY, paddleWidth, paddleHeight, null);
	}
}