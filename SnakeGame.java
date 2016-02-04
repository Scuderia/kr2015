import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

public class SnakeGame extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;
	private int scale = 32;
	private int width = 20;
	private int height = 20;
	private int speed = 7;
	private boolean gridOnOff = true;
	private int snakeColors, fontColors = 0;
	private int score = 0;

	Apple apple;
	Snake snake;
	Timer timer;
	Time snakeTimer = new Time(null);

	public SnakeGame(int scale, int width, int height, int speed, boolean gridOnOff, int snakeColors, int fontColors) {
		this.scale = scale;
		this.width = width;
		this.height = height;
		this.speed = speed;
		this.gridOnOff = gridOnOff;
		this.snakeColors = snakeColors;
		this.fontColors = fontColors;
		apple = new Apple((int) (Math.random() * width), (int) (Math.random() * height));
		snake = new Snake(width / 2, height / 2, width / 2 - 1, height / 2);
		timer = new Timer(1000 / speed, this);
		timer.start();
		addKeyListener(new Keys());
		setFocusable(true);
	}

	public SnakeGame() {
		apple = new Apple((int) (Math.random() * width), (int) (Math.random() * height));
		snake = new Snake(width / 2, width / 2, height / 2 - 1, height / 2);
		timer = new Timer(1000 / speed, this);
		timer.start();
		addKeyListener(new Keys());
		setFocusable(true);
	}

	public void paint(Graphics g) {

		if (snake.length != width * height) {
			if (fontColors == 0) {
				g.setColor(color(100, 200, 50));
				g.fillRect(0, 0, width * scale, height * scale);
			}
			if (fontColors == 1) {
				g.setColor(color(255, 215, 0));
				g.fillRect(0, 0, width * scale, height * scale);
			}
			if (fontColors == 2) {
				g.setColor(color(190, 190, 190));
				g.fillRect(0, 0, width * scale, height * scale);
			}

			if (gridOnOff == true) {
				g.setColor(color(50, 100, 25));
				for (int x = 0; x <= scale * width; x += scale)
					g.drawLine(x, 0, x, scale * height);
				for (int y = 0; y <= scale * height; y += scale)
					g.drawLine(0, y, scale * width, y);
			}

			g.setColor(color(255, 255, 0));
			g.fillRect(snake.snakeX[0] * scale + 1, snake.snakeY[0] * scale + 1, scale - 1, scale - 1);

			if (snakeColors == 0) {
				g.setColor(color(50, 100, 25));
				for (int d = 1; d < snake.length; d++) {
					g.fillRect(snake.snakeX[d] * scale + 1, snake.snakeY[d] * scale + 1, scale - 1, scale - 1);
				}
			}
			if (snakeColors == 1) {
				g.setColor(color(160, 82, 45));
				for (int d = 1; d < snake.length; d++) {
					g.fillRect(snake.snakeX[d] * scale + 1, snake.snakeY[d] * scale + 1, scale - 1, scale - 1);
				}
			}
			if (snakeColors == 2) {
				g.setColor(color(165, 42, 42));
				for (int d = 1; d < snake.length; d++) {
					g.fillRect(snake.snakeX[d] * scale + 1, snake.snakeY[d] * scale + 1, scale - 1, scale - 1);
				}
			}

			g.setColor(color(255, 0, 0));
			g.fillRect(apple.posX * scale + 1, apple.posY * scale + 1, scale - 1, scale - 1);

			g.setColor(color(255, 255, 255));
			g.fillRect(0, height * scale + 1, width * scale, scale * 2);
			g.setColor(color(0, 0, 0));
			g.setFont(new Font("Serif", Font.BOLD, scale));
			g.drawString("Score:  " + score, scale / 2, height * scale + scale / 3 + scale);
			g.drawString("Time:  " + snakeTimer.getMinutes() + ":" + snakeTimer.getSeconds(), scale * width - scale * 5,
					height * scale + scale / 3 + scale);
		} else {
			timer.stop();
			JOptionPane.showMessageDialog(new Frame(), "Поздравляю! Вы прошли игру!\nВаш счет: " + score
					+ " Ваше время: " + snakeTimer.getMinutes() + ":" + snakeTimer.getSeconds());
		}
	}

	public Color color(int red, int green, int blue) {
		return new Color(red, green, blue);
	}

	public void actionPerformed(ActionEvent e) {
		snake.move();
		if ((snake.snakeX[0] == apple.posX) & (snake.snakeY[0] == apple.posY)) {
			apple.setRandomPosition();
			snake.length++;
			score++;
		}
		for (int k = 1; k < snake.length; k++) {
			if ((snake.snakeX[k] == apple.posX) & (snake.snakeY[k] == apple.posY)) {
				apple.setRandomPosition();
			}
		}
		if ((snake.snakeX[0] == apple.posX) & (snake.snakeY[0] == apple.posY)) {
			apple.setRandomPosition();
			snake.length++;
			score++;
		}
		repaint();
	}

	private class Snake {

		public int direction = 0;
		public int length = 2;
		public int snakeX[] = new int[width * height];
		public int snakeY[] = new int[width * height];

		public Snake(int x0, int y0, int x1, int y1) {
			snakeX[0] = x0;
			snakeX[1] = x1;
			snakeY[0] = y0;
			snakeY[1] = y1;
		}

		public void move() {
			for (int d = length; d > 0; d--) {
				snakeX[d] = snakeX[d - 1];
				snakeY[d] = snakeY[d - 1];
			}
			if (direction == 0)
				snakeX[0]++;
			if (direction == 1)
				snakeY[0]++;
			if (direction == 2)
				snakeX[0]--;
			if (direction == 3)
				snakeY[0]--;

			for (int d = length; d > 0; d--) {
				if ((snakeX[0] == snakeX[d]) & (snakeY[0] == snakeY[d])) {
					length = d - 2;
					score = 0;
				}
			}
			if (snakeX[0] > width - 1)
				snakeX[0] = 0;
			if (snakeX[0] < 0)
				snakeX[0] = width - 1;
			if (snakeY[0] > height - 1)
				snakeY[0] = 0;
			if (snakeY[0] < 0)
				snakeY[0] = height - 1;
			if (length < 2)
				length = 2;
		}
	}

	private class Apple {

		public int posX;
		public int posY;

		public Apple(int x, int y) {
			posX = x;
			posY = y;
		}

		public void setRandomPosition() {
			posX = (int) (Math.random() * width);
			posY = (int) (Math.random() * height);
		}
	}

	private class Keys extends KeyAdapter {

		public void keyPressed(KeyEvent kE) {
			int key = kE.getKeyCode();
			if ((key == KeyEvent.VK_RIGHT) & snake.direction != 2)
				snake.direction = 0;
			if ((key == KeyEvent.VK_DOWN) & snake.direction != 3)
				snake.direction = 1;
			if ((key == KeyEvent.VK_LEFT) & snake.direction != 0)
				snake.direction = 2;
			if ((key == KeyEvent.VK_UP) & snake.direction != 1)
				snake.direction = 3;
		}
	}
}
