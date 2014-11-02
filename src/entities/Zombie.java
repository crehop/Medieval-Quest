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
		leftArmTop = new WireframePart(location,this, 3.0f, 1.25f, 1.25f, 0.25f);
		leftArmBottom = new WireframePart(location,this, 3.0f, 1.25f, 1.25f, 0.25f);
		leftArmBottom.setTexture("dirt");
		leftArmBottom.setOffsetX(-0.0f);
		leftArmBottom.setOffsetY(-1.00f
				
				);
		leftArmBottom.syncOffset();
		leftArmBottom.setRotateZ(180.0f);
		leftArmBottom.rotateZ(-20.0f);

	}
	public void animateRunForward(){
		leftArmRun();
	}
	private void leftArmRun() {
		if(this.leftArmAnimationMin){
			leftArmBottom.rotateZ(-0.5f);
			leftArmTop.rotateZ(-0.15f);
			leftArmBottom.moveY(-0.005f);
			leftArmTop.moveY(-0.005f);
			if(leftArmBottom.getRotateZ() <= 70){
				this.leftArmAnimationMax = true;
				this.leftArmAnimationMin = false;
			}	
		}else if(this.leftArmAnimationMax){
			leftArmBottom.rotateZ(0.5f);
			leftArmTop.rotateZ(0.15f);
			leftArmBottom.moveY(0.005f);
			leftArmTop.moveY(0.005f);
			if(leftArmBottom.getRotateZ() >= 210){
				this.leftArmAnimationMin = true;
				this.leftArmAnimationMax = false;
				
				
			}
		}
	}
}

