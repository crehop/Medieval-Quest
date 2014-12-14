package physics;

import gameElements.Model;

import java.util.ArrayList;

import TerrainGeneration.Chunk;


public class PhysicsEngine {
	static int groundCount = 0;
	static boolean x = true;
	static boolean y = true;
	static boolean z = true;
	public static ArrayList<Model> modelList = new ArrayList<Model>();
	//Order = xmin[0],xmax[1],ymin[2],ymax[3],zmin[4],zmax[5]
	public static boolean checkForCollision(Model m) {
		//Collision on axis?
		for(Model model:modelList){
			if(model.isCollidable()){
				if(model.getID() != m.getID()){
					if(model.getXmax() < m.getXmin()) x=false;
					if(model.getXmin() > m.getXmax()) x=false;
					if(model.getZmax() < m.getZmin()) z=false;
					if(model.getZmin() > m.getZmax()) z=false;
					if(model.getYmax() < m.getYmin()) y=false;
					if(model.getXmin() > m.getXmax()) x=false;
				}
				if(m.getChunk().getLocation().getY() > m.getYmin()){
					groundCount++;
					m.fixCollisionGround();
					Main.Console.setLine7("COLLISION GROUND!" + model.getName() + " " + groundCount  + " X= " + x + " Y=" + y + " Z=" + z);
					return true;
				}
				if(x == false && y == false && z == false){
					Main.Console.setLine7("COLLISION!" + model.getName() + " X= " + x + " Y=" + y + " Z=" + z);
					return true;
				}
			}
		}
		Main.Console.setLine7("NO COLLISION");
		return false;
	}
}
