import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.Random;

import javax.swing.JComponent;

@SuppressWarnings("serial")
public class Ball extends JComponent{
	int ballX;
	int ballY;
	int ballWidth;
	int ballHeight;
	double ballSpeed;
	String score="0";
	int scoreInt;
	boolean ballUp;
	boolean ballDown;
	boolean ballRight;
	boolean ballLeft;
	Random rand = new Random();
	boolean started = false;
	double xSpeed;
	double ySpeed;
	double changeY;
	double changeX;
	boolean checkTop=true;
	boolean checkBot=true;
	boolean checkLeft=true;
	boolean checkRight=true;
	public Ball(int ballX, int ballY, int ballWidth, int ballHeight, int ballSpeed){
		this.ballX = ballX;
		this.ballY = ballY;
		this.ballWidth = ballWidth;
		this.ballHeight = ballHeight;
		this.ballSpeed = ballSpeed;
		xSpeed += rand.nextInt(4);
		ySpeed += rand.nextInt(4);
	}
	int i = 0;
	public void start(Paddle topPaddle, Paddle botPaddle, Paddle leftPaddle, Paddle rightPaddle){
		System.out.println("start 2");
		if (xSpeed >= -1 && xSpeed <= 1){
			xSpeed += rand.nextInt(4);
		}
		if (ySpeed >= -1 && ySpeed <= 1){
			ySpeed += rand.nextInt(4);
		}
		if (((ySpeed/xSpeed) > 3.0/4.0 && (ySpeed/xSpeed) < 4.0/3.0) || ((ySpeed/xSpeed) < -3.0/4.0 && ySpeed/xSpeed > -4.0/3.0)) {
			xSpeed = rand.nextInt(4);
			ySpeed = rand.nextInt(4);
			System.out.println("reset speeds");
		}
		else{
		ballX += xSpeed/2;
		ballY += ySpeed/2;
		}
		if (checkTop) {
			calculateTop(topPaddle);
		}
		if (checkBot) {
			calculateBot(botPaddle);
		}
		if (checkLeft) {
			calculateLeft(leftPaddle);
		}
		if (checkRight) {
			calculateRight(rightPaddle);
		}
	}
	int i1 = 0;
	public void update(Paddle topPaddle, Paddle botPaddle, Paddle leftPaddle, Paddle rightPaddle){
		if (((ySpeed/xSpeed) > 3.0/4.0 && (ySpeed/xSpeed) < 4.0/3.0) || ((ySpeed/xSpeed) < -3.0/4.0 && ySpeed/xSpeed > -4.0/3.0)) {
			xSpeed++;
			ySpeed--;
			System.out.println("averaged speed");
		}
		ballVertical();
		ballHorizontal();
		score = String.valueOf(scoreInt);
		if (checkTop) {
			calculateTop(topPaddle);
		}
		if (checkBot) {
			calculateBot(botPaddle);
		}
		if (checkLeft) {
			calculateLeft(leftPaddle);
		}
		if (checkRight) {
			calculateRight(rightPaddle);
		}
	}
	/*
	Ball ball = new Ball(275, 275, 50, 50, 5);
	Paddle botPaddle = new Paddle(250, 500, 100, 50, "/botPaddle.png");
	Paddle topPaddle = new Paddle(250, 50, 100, 50, "/topPaddle.png");
	Paddle leftPaddle = new Paddle(50, 250, 50, 100, "/botPaddle.png");
	Paddle rightPaddle = new Paddle(550, 50, 50, 100, "/umturtle.png");
	*/
	public void calculateTop(Paddle topPaddle){
		//top
		if (ballY<=topPaddle.paddleY+50 && ballY>=0 && ballX+25>=topPaddle.paddleX && ballX+25<=topPaddle.paddleX+169) {
			ySpeed = -ySpeed;
			started = true;
			System.out.println("vertical ball: " + ballX + ", " + ballY + "; paddle: " + topPaddle.paddleX + ", " + topPaddle.paddleY);
			checkTop = false;
			scoreInt++;
			checkBot = true;
			checkLeft = true;
			checkRight = true;
			if (ballSpeed < 10) {
				ballSpeed++;
			}
		}
	}
	public void calculateBot(Paddle botPaddle){
		//bottom
		if (ballY+50>=botPaddle.paddleY && ballY<=600 && ballX+25>=botPaddle.paddleX && ballX+25<=botPaddle.paddleX+100) {
			ySpeed = -ySpeed;
			started = true;
			System.out.println("vertical ball: " + ballX + ", " + ballY + "; paddle: " + botPaddle.paddleX + ", " + botPaddle.paddleY);
			checkBot = false;
			scoreInt++;
			checkTop = true;
			checkLeft = true;
			checkRight = true;
		}
	}
	public void calculateLeft(Paddle leftPaddle){
		//left
		if (ballX<=leftPaddle.paddleX+50 && ballX>=0 && ballY+25>=leftPaddle.paddleY && ballY+25<=leftPaddle.paddleY+85) {
			xSpeed = -xSpeed;
			started = true;
			System.out.println("horizontal ball: " + ballX + ", " + ballY + "; paddle: " + leftPaddle.paddleX + ", " + leftPaddle.paddleY);
			checkLeft = false;
			scoreInt++;
			checkTop = true;
			checkBot = true;
			checkRight = true;
		}
	}
	public	 void calculateRight(Paddle rightPaddle){
		//right
		if (ballX+50>=rightPaddle.paddleX && ballX<=600 && ballY+25>=rightPaddle.paddleY && ballY+25<=rightPaddle.paddleY+85) {
			xSpeed = -xSpeed;
			started = true;
			System.out.println("horizontal ball: " + ballX + ", " + ballY + "; paddle: " + rightPaddle.paddleX + ", " + rightPaddle.paddleY);
			checkRight = false;
			scoreInt++;
			checkTop = true;
			checkBot = true;
			checkLeft = true;
		}
	}

	public void ballVertical(){
		if (ySpeed > 0) {
			changeY = ySpeed + ballSpeed;
		}
		else if (ySpeed < 0) {
			changeY = ySpeed - ballSpeed;
		}
		else {
			System.out.println("error Ball.ballvertical");
			changeY = ySpeed;
		}
		ballY += changeY/2;
	}
	public void ballHorizontal(){
		if (xSpeed > 0) {
			changeX = xSpeed + ballSpeed;
		}
		else if (xSpeed < 0) {
			changeX = xSpeed - ballSpeed;
		}
		else {
			System.out.println("error Ball.ballhorizontal");
			changeX = xSpeed;
		}
		ballX += changeX/2;
	}
	
	Font scoreFont = new Font("Arial", Font.BOLD, 20);
	public void paint(Graphics g){
		g.setColor(Color.white);
		g.fillOval(ballX, ballY, ballWidth, ballHeight);
		g.setColor(Color.black);
		g.setFont(scoreFont);
		g.drawString(score, ballX+5, ballY+35);
	}
}
