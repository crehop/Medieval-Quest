package physics;

import java.util.ArrayList;

import TerrainGeneration.Chunk;

import utils.Model;

public class PhysicsEngine {
	public static ArrayList<Model> modelList = new ArrayList<Model>();
	//Order = xmin[0],xmax[1],ymin[2],ymax[3],zmin[4],zmax[5]
	public static boolean checkForCollision(Model m) {
		//Collision on axis?
		boolean x = true;
		boolean y = true;
		boolean z = true;
		boolean collision = true;
		for(Model model:modelList){
			if(model.isCollidable()){
				if(model.getID() != m.getID()){
					if(model.getBoundingBox().get(1) < m.getBoundingBox().get(0)) x=false;
					if(model.getBoundingBox().get(0) > m.getBoundingBox().get(1)) x=false;
					if(model.getBoundingBox().get(5) < m.getBoundingBox().get(4)) z=false;
					if(model.getBoundingBox().get(4) > m.getBoundingBox().get(5)) z=false;
					if(model.getBoundingBox().get(3) < m.getBoundingBox().get(2)) y=false;
					if(model.getBoundingBox().get(0) > m.getBoundingBox().get(1)) x=false;
				}
				System.out.println("CHUNK Y = " + m.getChunk().getLocation().getY() + " MODEL MINY = " + model.getBoundingBox().get(3));
				if(m.getChunk().getLocation().getY() > model.getBoundingBox().get(3)){
					if(m.getChunk().getLocation().getY() > model.getBoundingBox().get(3)){
						model.getLocation().setY(model.getLocation().getY() + 1.0f);
					}
					System.out.println("COLLISION! GROUND");
					return true;
				}
				if(x == false && y == false && z == false){
					System.out.println("COLLISION!" + model.getName());
					return true;
				}
			}
		}
		return false;
	}
}
