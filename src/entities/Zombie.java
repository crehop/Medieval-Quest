package entities;

import java.util.ArrayList;

import server.Location;
import voxel.Voxel;
import wireframe.Wireframe;
import wireframe.WireframePart;

public class Zombie extends Wireframe{
	private Location location;
	ArrayList<Voxel> voxels = new ArrayList<Voxel>();
	public Zombie(Location location) {
		WireframePart leftArmTop = new WireframePart(location,this, 5.0f, 3.0f, 3.0f, 0.25f);
		WireframePart leftArmbottom = new WireframePart(location,this, 6.0f, 3.0f, 3.0f, 0.25f);
		leftArmbottom.setOffsetX(5.3f);
		leftArmbottom.syncOffset();
	}

}
