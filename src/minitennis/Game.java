package minitennis;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Game extends JPanel {

	Ball ball = new Ball(this);
	Racquet racquet = new Racquet(this);
        static JFrame frame;
	int speed = 1;

	private int getScore() {
		return speed - 1;
	}

	public Game() {
		addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
			}

			@Override
			public void keyReleased(KeyEvent e) {
				racquet.keyReleased(e);
			}

			@Override
			public void keyPressed(KeyEvent e) {
				racquet.keyPressed(e);
			}
		});
		setFocusable(true);
		Sound.BACK.loop();
	}

	private void move() {
		ball.move();
		racquet.move();
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		ball.paint(g2d);
		racquet.paint(g2d);

                // Score
		g2d.setColor(Color.GRAY);
		g2d.setFont(new Font("Verdana", Font.BOLD, 30));
		g2d.drawString(String.valueOf(getScore()), 10, 30);
	}

	public void gameOver() {
		Sound.BACK.stop();
		Sound.GAMEOVER.play();
		JOptionPane.showMessageDialog(this, "your score is: " + getScore(),
				"Game Over", JOptionPane.YES_NO_OPTION);
		System.exit(ABORT);
	}

	public static void main(String[] args) throws InterruptedException {
//		JFrame frame = new JFrame("Mini Tennis");
            frame = new JFrame("Mini Tennis");

            //flowlayout breaks it
//                frame.setLayout(new FlowLayout());
		Game game = new Game();
                
                JButton button640x480 = new JButton("640 X 480");
                JButton button800x600 = new JButton("800 X 600");
                JButton button1024x768 = new JButton("1024 X 768");
                //frame.add(button);
                //button.addActionListener();
                
		
//                frame.add(button640x480);
//                frame.add(button800x600);
//                frame.add(button1024x768);
                frame.add(game);

                
                button640x480.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        frame.setSize(640, 480);
                    }
                });
                button800x600.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        frame.setSize(800, 600);
                    }
                });
                button1024x768.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        frame.setSize(1024, 768);
                    }
                });
                
                
		frame.setSize(640, 480);
		
                frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            
		while (true) {
			game.move();
			game.repaint();
			Thread.sleep(10);
		}
	}
        
        
}