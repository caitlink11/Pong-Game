import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class pongprogram implements ActionListener, KeyListener, MouseListener, MouseMotionListener{
	//Properties
	JFrame theframe;
	PongAnimationPanel thepanel;
	Timer thetimer;
	
	//Methods
	public void mouseMoved(MouseEvent evt){
		if(thepanel.p1Ready && thepanel.p2Ready){
			thepanel.rPaddleY = evt.getY();
		}
	}
	public void mouseDragged(MouseEvent evt){
		
	}
	
	public void mouseClicked(MouseEvent evt){
		if(!thepanel.p2Ready && !thepanel.instructions && (evt.getX() >= 810 && evt.getX() <= 950) && (evt.getY() >= 455 && evt.getY() <= 505)) {
			thepanel.p2Ready = true;
		}
	}

	public void mouseEntered(MouseEvent evt){

	}

	public void mouseExited(MouseEvent evt){
		
	}

	public void mousePressed(MouseEvent evt){

	}

	public void mouseReleased(MouseEvent evt){

	}

	public void actionPerformed(ActionEvent evt){
		if(evt.getSource() == thetimer){
			thepanel.repaint();
		}
	}
	public void keyReleased(KeyEvent evt){
		
	}
	public void keyPressed(KeyEvent evt){ 
		if(evt.getKeyChar() == 'x' && !thepanel.instructions && !thepanel.gameOver && !thepanel.p1Ready){
			thepanel.p1Ready = true;
		}
		if(evt.getKeyChar() == 'w' && thepanel.p1Ready && thepanel.p2Ready && thepanel.lPaddleY >= -50 && !thepanel.gameOver){
			thepanel.lPaddleY -= 80;
		}
		if(evt.getKeyChar() == 's' && thepanel.p1Ready && thepanel.p2Ready && thepanel.lPaddleY <= 550 && !thepanel.gameOver ){
			thepanel.lPaddleY += 80;
		}
		if(evt.getKeyChar() == 'i' && (!thepanel.p1Ready || !thepanel.p2Ready)){
			thepanel.instructions = true;
		}
		if(evt.getKeyChar() == 'h' && thepanel.instructions){
			thepanel.instructions = false;
		}
		if(evt.getKeyChar() == 'h' && thepanel.gameOver){
			thepanel.p1Score = 0;
			thepanel.p2Score = 0;
			thepanel.p1Ready = false;
			thepanel.p2Ready = false;
			thepanel.gameOver = false;
		}
		if(evt.getKeyChar() == 'r' && thepanel.gameOver){
			thepanel.p1Score = 0;
			thepanel.p2Score = 0;
			thepanel.gameOver = false;
		}
	}
	public void keyTyped(KeyEvent evt){
		
	}
	
	
	
	//Constructor
	public pongprogram(){
		theframe = new JFrame("Pong");
		thepanel = new PongAnimationPanel();
		thepanel.setLayout(null);
		thepanel.setPreferredSize(new Dimension(1280, 680));
		thepanel.addMouseListener(this);
		thepanel.addMouseMotionListener(this);
		theframe.addKeyListener(this);
		
		thetimer = new Timer(1000/60, this);
		thetimer.start();
		
		theframe.setContentPane(thepanel);
		theframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		theframe.pack();
		theframe.setVisible(true);
		
	}
	
	public static void main(String[] args){
		new pongprogram();
	}
	
	
}
