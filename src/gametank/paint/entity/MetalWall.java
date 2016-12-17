package gametank.paint.entity;

import java.awt.Color;
import java.awt.Graphics;

import gametank.paint.parent.Wall;

public class MetalWall extends Wall{
	@Override
	public void draw(Graphics g) {
		Color c=g.getColor();
		g.setColor(Color.WHITE);
		g.fill3DRect(x, y,width,height,false);
		g.setColor(Color.WHITE);
		g.drawLine(x,y+10, x+20,y+10);
		g.drawLine(x+10,y,x+10, y+20);
		g.setColor(c);
	}
	public MetalWall() {}
	public MetalWall(int x,int y) {
		super(x, y);
	}
}
