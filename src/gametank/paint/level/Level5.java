package gametank.paint.level;

import gametank.paint.entity.CommonWall;
import gametank.paint.entity.MetalWall;
import gametank.paint.panel.GamePanel;

public class Level5 extends GamePanel{

	private static final long serialVersionUID = 1L;

	public Level5() {
		for(int i=50;i<230;i+=22){
			CommonWall commonWall=new CommonWall(i,180);
			if(i!=138)commonWalls.add(commonWall);
			CommonWall commonWall2=new CommonWall(138,i+50);
			commonWalls.add(commonWall2);
		}
		for(int i=50;i<230;i+=22){
			MetalWall metalWall=new MetalWall(i+250,180);
			if(i!=138)metalWalls.add(metalWall);
			MetalWall metalWall2=new MetalWall(388,i+50);
			metalWalls.add(metalWall2);
		}
		for(int k=60;k<450;k+=22){
			CommonWall commonWall=new CommonWall(k,350);
			commonWalls.add(commonWall);
		}
	}
}
