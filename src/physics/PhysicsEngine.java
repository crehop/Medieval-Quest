package physics;

import gameElements.Model;

import java.util.ArrayList;

import TerrainGeneration.Chunk;


public class PhysicsEngine {
	static boolean x = true;
	static boolean y = true;
	static boolean z = true;
	public static ArrayList<Model> modelList = new ArrayList<Model>();
	public static boolean checkForCollision(Model m) {
		for(Model model:modelList){
			if(model.isCollidable()){
				if(model.getID() != m.getID()){
					if(model.getXmax() < m.getXmin() || model.getXmin() > m.getXmax()) x=false;
					if(model.getZmax() < m.getZmin() || model.getZmin() > m.getZmax()) z=false;
					if(model.getYmax() < m.getYmin() || model.getXmin() > m.getXmax()) y=false;
					if(x == true && y == true && z == true){
						//Main.Console.setLine7("COLLISION! " + model.getName() + " X= " + x + " Y=" + y + " Z=" + z + " ID=" + m.getID()  + " SECOND MODEL =" + model.getID());
						return true;
					}
				}
				if(m.getChunk().getLocation().getY() > m.getYmin()){
					m.fixCollisionGround();
					//Main.Console.setLine7("COLLISION GROUND!" + model.getName() + " "  + " X= " + x + " Y=" + y + " Z=" + z + "ID =" + m.getID() + "SECOND MODEL =" + model.getID());
					return true;
				}
			}
		}
		return false;
	}
}
