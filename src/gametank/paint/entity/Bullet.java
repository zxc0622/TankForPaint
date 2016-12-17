package gametank.paint.entity;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.List;

import gametank.paint.panel.GamePanel;
import gametank.paint.parent.Bomb;
import gametank.paint.parent.Tank;

public class Bullet {
	private GamePanel tc;
	private int x;
	private int y;
	private Color color=Color.WHITE;
	private int direction;
	private int speed=6;
	private boolean life=true;
	public void setLife(boolean life) {
		this.life = life;
	}
	public void setColor(Color color) {
		this.color = color;
	}
	public boolean isLife() {
		return life;
	}
	public void setDirection(int direction) {
		this.direction = direction;
	}
	public int getDirection() {
		return direction;
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public void setTc(GamePanel tc) {
		this.tc = tc;
	}
	public GamePanel getTc() {
		return tc;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public Bullet(int x, int y,int direction) {
		super();
		this.x = x;
		this.y = y;
		this.direction=direction;
	}
	public Bullet() {}
	public void draw(Graphics g){
		Color c=g.getColor();
		g.setColor(color);
		g.fillOval(x, y,3,3);
		g.setColor(c);
//		System.err.println("cone");
		bulletMove();
	}
	public void bulletMove(){
		if(x<0||y<0||x>547||y>447)life=false;
		switch(this.direction){
		case 1:
			y-=speed;
			break;
		case 2:
			y+=speed;
			break;
		case 3:
			x-=speed;
			break;
		case 4:
			x+=speed;
			break;
		}
	}
	public Rectangle getRect(){
		return new Rectangle(x, y, 3, 3);
	}
	public void hitCommWall(List<CommonWall> commonWalls){
		for(CommonWall commonWall:commonWalls){
			Rectangle rectangle=commonWall.getRect();
			if(getRect().intersects(rectangle)){
//				int l_x=commonWall.getX()+((int)rectangle.getWidth()/2);
//				int l_y=commonWall.getY()+((int)rectangle.getHeight()/2);
//				BigBomb bigBomb=new BigBomb(l_x-5,l_y-5);
//				tc.getBigBombs().add(bigBomb);
				this.setLife(false);
				commonWall.setLife(false);
				commonWalls.remove(commonWall);
				return;
			}
		}
	}
	public void hitMetal(List<MetalWall> metalWalls){
		for(MetalWall metalWall:metalWalls){
			if(metalWall.getRect().intersects(getRect())){
				this.life=false;
				return;
			}
		}
	}
	
	public void hitTank(List<EnemyTank> enemyTanks){
		for(int i=0;i<(enemyTanks.size()>tc.count?tc.count:enemyTanks.size());i++){
			EnemyTank enemyTank=enemyTanks.get(i);
			if(getRect().intersects(enemyTank.getRect())){
				this.setLife(false);
				enemyTank.leeBolb();
				if(enemyTank.getBlomLife()==0) {
					//加分数
					int record=GamePanel.tank.getScore();
					GamePanel.tank.setScore(record+enemyTank.getTankType().getSCORCE());
					Rectangle rectangle=enemyTank.getRect();
					enemyTank.setLife(false);
					//清空已死敌人的子弹,防止浪费内存
					enemyTank.getBullets().removeAllElements();
					tc.getEnemyTanks().remove(enemyTank);
					int l_x=enemyTank.getX()+((int)rectangle.getWidth()/2);
					int l_y=enemyTank.getY()+((int)rectangle.getHeight()/2);
					addBombToPanel(l_x, l_y);
				}
				return;
			}
		}
	}
	
	public void addBombToPanel(int x,int y){
		Bomb bigBomb=new BigBomb(x-5,y-5);
		tc.getBigBombs().add(bigBomb);
	}
	
	public void hitMe(Tank tank){
		if(this.getRect().intersects(tank.getRect())){
			setLife(false);
			tank.leeBolb();
			if(tank.getBlomLife()==0){
				Rectangle rectangle=tank.getRect();
				int l_x=tank.getX()+((int)rectangle.getWidth()/2);
				int l_y=tank.getY()+((int)rectangle.getHeight()/2); 
				tank.setLife(false);
				GamePanel.tank=null;
				addBombToPanel(l_x, l_y);
				//移除我的所有子弹,避免浪费内存
				tank.getBullets().removeAllElements();
				//移动键盘监听事件,防止出现死亡以后还可以进行控制导致出现异常
				tc.getFrame().removeKeyListener(tc.getFrame().getListener());
			}
		}
	}
}
