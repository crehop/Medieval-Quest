package physics;

import gameElements.Model;

import java.util.ArrayList;


public class PhysicsEngine {
	static boolean x = true;
	static boolean y = true;
	static boolean z = true;
	public static ArrayList<Model> modelList = new ArrayList<Model>();
	public static boolean checkForCollision(Model m) {
		return false;
	}
}
