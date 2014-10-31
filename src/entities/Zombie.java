package entities;

import java.util.ArrayList;

import server.Location;
import voxel.Voxel;
import wireframe.Wireframe;
import wireframe.WireframePart;

public class Zombie extends Wireframe{
	private boolean leftArmAnimationMax = false;
	private boolean leftArmAnimationMin = true;
	private Location location;
	WireframePart leftArmTop;
	WireframePart leftArmBottom;
	ArrayList<Voxel> voxels = new ArrayList<Voxel>();
	public Zombie(Location location) {
		leftArmTop = new WireframePart(location,this, 5.0f, 3.0f, 3.0f, 0.25f);
		leftArmBottom = new WireframePart(location,this, 6.0f, 3.0f, 3.0f, 0.25f);
		leftArmBottom.setTexture("dirt");
		leftArmBottom.setOffsetX(-0.0f);
		leftArmBottom.setOffsetY(-2.75f);
		leftArmBottom.syncOffset();
		leftArmBottom.setRotateZ(180.0f);
		leftArmBottom.rotateZ(-20.0f);

	}
	public void animateRunForward(){
		leftArmRun();
	}
	private void leftArmRun() {
		if(this.leftArmAnimationMin){
			leftArmBottom.rotateZ(-0.2f);
			leftArmTop.rotateZ(-0.1f);
			if(leftArmBottom.getRotateZ() <= 100){
				this.leftArmAnimationMax = true;
				this.leftArmAnimationMin = false;
			}
		}else if(this.leftArmAnimationMax){
			leftArmBottom.rotateZ(0.2f);
			leftArmTop.rotateZ(0.1f);
			if(leftArmBottom.getRotateZ() >= 180){
				this.leftArmAnimationMin = true;
				this.leftArmAnimationMax = false;
			}
		}
	}
}

