package gametank.paint.level;

import gametank.paint.entity.CommonWall;
import gametank.paint.entity.MetalWall;
import gametank.paint.panel.GamePanel;

public class Level2 extends GamePanel{

	private static final long serialVersionUID = 1L;

	public Level2() {
		for(int i=60;i<260;i+=22){
			CommonWall commonWall=new CommonWall(70,i);
			commonWalls.add(commonWall);
			CommonWall commonWall2=new CommonWall(220,i);
			commonWalls.add(commonWall2);
			CommonWall commonWall3=new CommonWall(360,i);
			commonWalls.add(commonWall3);
		}
		for(int j=60;j<260;j+=22){
			MetalWall metalWall=new MetalWall(150,j);
			metalWalls.add(metalWall);
			MetalWall metalWall2=new MetalWall(290,j);
			metalWalls.add(metalWall2);
			MetalWall metalWall3=new MetalWall(430,j);
			metalWalls.add(metalWall3);
		}
	}
}
