package minitennis;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.Random;

public class Ball {
	private static final int DIAMETER = 30;
	
	int x = 0;
	int y = 0;
	int xa = 1;
	int ya = 1;
	private final Game game;

	public Ball(Game game) {
		this.game = game;
                x = getXStart();
	}

	void move() {
		boolean changeDirection = true;
		if (x + xa < 0)
			xa = game.speed;
		else if (x + xa > game.getWidth() - DIAMETER)
			xa = -game.speed;
		else if (y + ya < 0)
			ya = game.speed;
		else if (y + ya > game.getHeight() - DIAMETER)
			game.gameOver();
		else if (collision()){
			ya = -game.speed;
			y = game.racquet.getTopY() - DIAMETER;
			game.speed++;
		} else 
			changeDirection = false;
		
		if (changeDirection) 
			Sound.BALL.play();
		x = x + xa;
		y = y + ya;
	}

	private boolean collision() {
		return game.racquet.getBounds().intersects(getBounds());
	}

	public void paint(Graphics2D g) {
            g.setColor(Color.BLUE);
//            g.fillOval(x, y, DIAMETER, DIAMETER);
            // set to center of screen
            g.fillOval(x, y, DIAMETER, DIAMETER); 
	}

	public Rectangle getBounds() {
		return new Rectangle(x, y, DIAMETER, DIAMETER);
	}

        private int getXStart() {
            int min = 0;
            int max = 640;

            Random r = new Random();
            int i = r.nextInt(max - min + 1) + min;
            return i;
        }
}