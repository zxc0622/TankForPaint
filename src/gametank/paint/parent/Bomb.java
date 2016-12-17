package gametank.paint.parent;

import java.awt.Color;
import java.awt.Graphics;

public class Bomb {
	protected int x;
	protected int y;
	protected boolean life = true;
	protected int cycle = 16;
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public void setCycle(int cycle) {
		this.cycle = cycle;
	}

	public int getCycle() {
		return cycle;
	}

	public boolean isLife() {
		return life;
	}
	
	public Bomb(int x,int y) {
		this.x=x;
		this.y=y;
	}
	public Bomb() {}
	public void draw(Graphics g) {
		Color color = g.getColor();
		if (cycle > 0) {
			if (cycle > 12) {
				g.setColor(Color.GREEN);
				g.fillOval(x, y, 10, 10);
			} else if (cycle > 8) {
				g.setColor(Color.BLUE);
				g.fillOval(x+1, y+1, 8, 8);
			} else if (cycle > 4) {
				g.setColor(Color.YELLOW);
				g.fillOval(x + 2, y + 2, 6, 6);
			} else {
				g.setColor(Color.RED);
				g.fillOval(x + 4, y + 4, 2, 2);
			}
			cycle--;
			g.setColor(color);
		} else {
			life = false;
		}
	}
}
