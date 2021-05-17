import javax.swing.*;
import java.awt.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;

public class PongAnimationPanel extends JPanel{
	//Properties
	BufferedImage p1Wins;
	BufferedImage p2Wins;
	BufferedImage paddleimage;

	final int windowHeight = 680;
	final int windowWidth = 1280;
	final int ballSize = 20;
	final int paddleWidth = 20;
	final int paddleHeight = 150;
	
	int defaultSpeed = 20;

	// Paddles
	int rPaddleX = 1180;
	int rPaddleY = 270;
	int lPaddleX = 100;
	int lPaddleY = 270;
	
	// Ball
	int ballX = 120;
	int ballY = 300;
	boolean goDown = false;
	boolean goUp = true;
	boolean goLeft = false;
	boolean goRight = true;
	boolean p1Pt = false;
	boolean p2Pt = false;
	int printImage = 0;

	int random = (int)((defaultSpeed - 10) * Math.random());
	int p1Score = 0;
	int p2Score = 0;
	
	boolean p1Ready = false;
	boolean p2Ready = false;
	boolean gameOver = false;
	boolean instructions = false;

	final int smallFont = 40;
	final int regularFont = 50;

	//Methods
	public void paintComponent(Graphics g){
		g.setColor(Color.BLACK);
		g.fillRect(0,0,1280,720);
		g.setColor(Color.WHITE);
		g.setFont(new Font("Caitlins Font", Font.PLAIN, regularFont));
		if(instructions){
			g.drawImage(paddleimage, 100, 150, null);
			g.drawString("Instructions:", 645, 200);
			g.setFont(new Font("Caitlins Font", Font.PLAIN, smallFont));
			g.drawString("Your goal is to score ten goals on the", 645, 230);
			g.drawString("opposite before your opponent scores", 645, 255);
			g.drawString("ten goals on your side.", 645, 280);
			g.setFont(new Font("Caitlins Font", Font.PLAIN, regularFont));
			g.drawString("Controls:", 645, 330);
			g.setFont(new Font("Caitlins Font", Font.PLAIN, smallFont));
			g.drawString("P1: Type w to move up and s to move down", 645, 360);
			g.drawString("P2: Use the mouse to move up and down", 645, 385);
			g.drawString("Type h to return to the home screen.", 645, 455);
		}else if(p1Score == 10){
			g.setFont(new Font("Caitlins Font", Font.PLAIN, regularFont));
			gameOver = true;
			g.drawString("Congrats!", 500, 230);
			g.drawImage(p1Wins, 390, 300, null);
			g.drawString("Type h to return to the home screen.", 320, 600);
			g.drawString("Type r to replay.", 400, 625);
		}else if (p2Score == 10){
			g.setFont(new Font("Caitlins Font", Font.PLAIN, regularFont));
			gameOver = true;
			g.drawImage(p2Wins, 390, 300, null);
			g.drawString("Type h to return to the home screen.", 320, 600);
			g.drawString("Type r to replay.", 400, 625);
		}else{
			g.fillRect(rPaddleX, rPaddleY, paddleWidth, paddleHeight);
			g.fillRect(lPaddleX, lPaddleY, paddleWidth, paddleHeight);
			g.fillOval(ballX, ballY, ballSize, ballSize);

			if(p1Ready && p2Ready){
				g.setFont(new Font("Caitlins Font", Font.PLAIN, regularFont));
				g.drawString("P1: " + Integer.toString(p1Score), 100, 100);
				g.drawString("P2: " + Integer.toString(p2Score), 1010, 100);
	
				if((!p1Pt) && (!p2Pt)){
					//if ball reaches top/bottom walls:
					if(ballY <= 0){
						goUp = false;
						random = (int)(defaultSpeed * Math.random());
						goDown = true;
					}else if(ballY >= windowHeight - ballSize){
						goDown = false;
						random = (int)(defaultSpeed * Math.random());
						goUp = true;
					}
	
					//if ball reaches other player's side:
					if(ballX <= 0){
						p2Pt = true;
						printImage = 10;
						goDown = false;
						goLeft = false;
						random = (int)(defaultSpeed * Math.random());
						goUp = true;
						goRight = true;
						ballX = 20;
						ballY = 1;
					}else if(ballX + ballSize > windowWidth){
						p1Pt = true;
						printImage = 10;
						goUp = false;
						goRight = false;
						random = (int)(defaultSpeed * Math.random());
						goDown = true;
						goLeft = true;
						ballX = 1260;
						ballY = 1;
					}
	
					if(goDown){
						ballY = ballY + random;
					}
					if(goUp){
						ballY = ballY - random;
					}
					if(goLeft){
						ballX = ballX - defaultSpeed;
					}
					if(goRight){
						ballX = ballX + defaultSpeed;
					}
					
					//if ball reaches a paddle:
					if(ballX + ballSize == rPaddleX && (ballY >= rPaddleY && ballY <= rPaddleY + 150)){
						goRight = false;
						goDown = false;
						random = (int)(defaultSpeed * Math.random());
						goLeft = true;
						goUp = true;
					}else if(ballX == lPaddleX + paddleWidth && (ballY >= lPaddleY && ballY <= lPaddleY + 150)){
						goLeft = false;
						goUp = false;
						random = (int)(defaultSpeed * Math.random());
						goRight = true;
						goDown = true;
					}
				}else if(p1Pt){
					g.drawImage(p1Wins, 390, 300, null);
					printImage--;
					if(printImage == 0){
						p1Pt = false;
						p1Score++;
					}
				}else{
					g.drawImage(p2Wins, 390, 300, null);
					printImage--;
					if(printImage == 0){
						p2Pt = false;
						p2Score++;
					}
				}
			
			}else{
				g.setFont(new Font("Caitlins Font", Font.PLAIN, regularFont));
				g.drawString("Welcome to PONG!", 490, 150);
				g.setFont(new Font("Caitlins Font", Font.PLAIN, smallFont));
				g.drawString("Type i for instructions and controls.", 400, 200);
				if(!p1Ready){
					g.setColor(new Color(174, 230, 184));
					g.fillRoundRect(295, 455, 140, 50, 20, 20);
					g.setColor(Color.WHITE);
					g.setFont(new Font("Caitlins Font", Font.PLAIN, regularFont));
					g.drawString("Ready?", 320, 300);
					g.drawString("Start", 320, 500);
					g.setFont(new Font("Caitlins Font", Font.PLAIN, smallFont));
					g.drawString("(press x to begin)", 255, 340);
				}else{
					g.setColor(Color.WHITE);
					g.drawString("Ready!", 320, 400);
				}
				if(!p2Ready){
					g.setColor(new Color(174, 230, 184));
					g.fillRoundRect(810, 455, 140, 50, 20, 20);
					g.setColor(Color.WHITE);
					g.setFont(new Font("Caitlins Font", Font.PLAIN, regularFont));
					g.drawString("Ready?", 825, 300);
					g.drawString("Start", 835, 500);
					g.setFont(new Font("Caitlins Font", Font.PLAIN, smallFont));
					g.drawString("(Click Start to begin)", 740, 340);
				}else{
					g.setColor(Color.WHITE);
					g.setFont(new Font("Caitlins Font", Font.PLAIN, regularFont));
					g.drawString("Ready!", 825, 400);
				}
			}
		}
		
	}
	
	//Constructor
	public PongAnimationPanel(){
		super();
		try{
			p1Wins = ImageIO.read(new File("p1Wins.jpg"));
			p2Wins = ImageIO.read(new File("p2Wins.jpg"));
			paddleimage = ImageIO.read(new File("pingpongpaddle.png"));
		}catch(IOException e){
			System.out.println("Unable to load image");
		}
	}
	
	
}
