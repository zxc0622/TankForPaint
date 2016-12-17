package gametank.paint.level;

import gametank.paint.entity.CommonWall;
import gametank.paint.entity.MetalWall;
import gametank.paint.panel.GamePanel;

public class Level4 extends GamePanel{

	private static final long serialVersionUID = 1L;

	public Level4() {
		for(int i=0;i<250;i+=22){
			MetalWall metalWall=new MetalWall(i,40);
			metalWalls.add(metalWall);
			CommonWall commonWall=new CommonWall(i,120);
			commonWalls.add(commonWall);
		}
		for(int j=50;j<300;j+=22){
			MetalWall metalWall=new MetalWall(350,j);
			if(j!=160&&j!=182)metalWalls.add(metalWall);
			CommonWall commonWall=new CommonWall(450,j);
			commonWalls.add(commonWall);
		}
	}
}
