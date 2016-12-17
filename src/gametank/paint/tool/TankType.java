package gametank.paint.tool;

import java.awt.Color;

public enum TankType {
	EASYTANK(100,1,Color.RED),
	SIMPLETANK(200,2,Color.GREEN),
	HEAVYTANK(300,3,Color.CYAN),
	HARDTANK(400,4,Color.PINK);
	private TankType(Integer score,int blomlife,Color flag) {
		this.SCORCE=score;
		this.BLOMLIFE=blomlife;
		this.FLAG=flag;
	}
	//·ÖÊý
	private final Integer SCORCE;
	private final int BLOMLIFE;
	private final Color FLAG;
	public Integer getSCORCE() {
		return SCORCE;
	}
	public int getBLOMLIFE() {
		return BLOMLIFE;
	}
	public Color getFLAG() {
		return FLAG;
	}
}
