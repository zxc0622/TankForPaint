package gametank.paint.parent;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Wall {
	protected int x;
	protected int y;
	protected int width=22;
	protected int height=22;
	protected boolean life=true;
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
	public boolean isLife() {
		return life;
	}
	public void setLife(boolean life) {
		this.life = life;
	}
	public Wall(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}
	public Wall() {}
	public void draw(Graphics g){
		Color c=g.getColor();
		g.setColor(Color.RED);
		g.fill3DRect(x, y,width,height,true);
		g.setColor(Color.WHITE);
		g.drawLine(x,y+10, x+20,y+10);
		g.drawLine(x+10,y,x+10, y+20);
		g.setColor(c);
	}
	public Rectangle getRect(){
		return new Rectangle(x, y,width,height);
	}
}
