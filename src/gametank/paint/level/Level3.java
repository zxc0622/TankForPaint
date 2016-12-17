package gametank.paint.level;

import gametank.paint.entity.CommonWall;
import gametank.paint.entity.MetalWall;
import gametank.paint.panel.GamePanel;

public class Level3 extends GamePanel{

	private static final long serialVersionUID = 1L;

	public Level3() {
		for(int i=0;i<220;i+=22){
			MetalWall metalWall=new MetalWall(i,350);
			metalWalls.add(metalWall);
		}
		for(int j=330;j<530;j+=22){
			CommonWall commonWall=new CommonWall(j,350);
			commonWalls.add(commonWall);
		}
		for(int k=170;k<370;k+=22){
			CommonWall commonWall=new CommonWall(k,250);
			commonWalls.add(commonWall);
			CommonWall commonWall2=new CommonWall(k,180);
			commonWalls.add(commonWall2);
			CommonWall commonWall3=new CommonWall(k,110);
			commonWalls.add(commonWall3);
		}
		for(int l=110;l<280;l+=22){
			MetalWall metalWall=new MetalWall(70,l);
			metalWalls.add(metalWall);
			MetalWall metalWall2=new MetalWall(450,l);
			metalWalls.add(metalWall2);
		}
	}
}
