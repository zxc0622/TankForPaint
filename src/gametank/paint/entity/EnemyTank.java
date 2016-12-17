package gametank.paint.entity;

import java.awt.Graphics;
import java.util.Random;

import gametank.paint.parent.Tank;
import gametank.paint.tool.Constant;
import gametank.paint.tool.TankType;

public class EnemyTank extends Tank {

	private static Random random=new Random();
	private static int sp_lg=Constant.SPEED_LONG;
	private static int enFire=Constant.FIRE_PRO;
	private int step=random.nextInt(sp_lg)+5;
	private TankType tankType;
	

	public TankType getTankType() {
		return tankType;
	}

	public void setTankType(TankType tankType) {
		this.tankType = tankType;
	}

	public EnemyTank(int x, int y, TankType tankType, int direction) {
		super(x, y, direction);
		this.setTankType(tankType);
		setColor(tankType.getFLAG());
		setBlomLife(tankType.getBLOMLIFE());
	}
	
	@Override
	public void draw(Graphics g) {
		drawTankWith(g);
		move();
	}
	
	@Override
	public void move() {
		this.oldX=x;
		this.oldY=y;
		if(step>0){
			switch(direction){
			case 1:
				if(y>0)
				y-=speed;
				break;
			case 2:
				if(y<425)
				y+=speed;
				break;
			case 3:
				if(x>0)
				x-=speed;
				break;
			case 4:
				if(x<525)
				x+=speed;
				break;
			}
			step--;
		}else{
			this.direction=random.nextInt(4)+1;
			step=random.nextInt(sp_lg)+5;
			if(random.nextInt(enFire)>10){
				this.fire();
			}
		}
	}
	
}
