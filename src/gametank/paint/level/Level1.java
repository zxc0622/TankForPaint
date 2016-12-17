package gametank.paint.level;

import gametank.paint.entity.CommonWall;
import gametank.paint.entity.MetalWall;
import gametank.paint.panel.GamePanel;

public class Level1 extends GamePanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Level1() {
//		commonWalls.addAll(Scene.getWall(CommonWall.class,"comms"));
//		metalWalls.addAll(Scene.getWall(MetalWall.class,"metas"));
		for(int i=50;i<500;i+=22){
			CommonWall commonWall=new CommonWall(i,100);
			commonWalls.add(commonWall);
			commonWall=new CommonWall(i,220);
			commonWalls.add(commonWall);
		}
		for(int j=50;j<500;j+=22){
			MetalWall metalWall=new MetalWall(j,160);
			metalWalls.add(metalWall);
		}
	}
}
