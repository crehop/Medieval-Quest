package renderer;

import static org.lwjgl.opengl.GL11.glPopMatrix;
import static org.lwjgl.opengl.GL11.glPushMatrix;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import utils.Model;
import utils.ModelLoader;

public class RenderModels {
	static boolean initiated = false;
	static Model test;
	static Model test2;
	public static ArrayList<Model> models = new ArrayList<Model>();
	public static void initiate(){
		try {
			test = ModelLoader.loadModel(new File("res/models/copcar/copcar.obj"), 0, 0, 0);
			test2 = ModelLoader.loadModel(new File("res/models/zombie1/zombie1.obj"), 0, 0, 0);
			models.add(test);
			models.add(test2);
			System.out.println("MODEL LOADED V= " + test.vertices.size() + " N= " + test.normals.size() );
			System.out.println("MODEL2 LOADED V= " + test2.vertices.size() + " N= " + test2.normals.size() );
			
		} catch (FileNotFoundException e) {
			System.out.println("NOT FOUND EXCEPTION");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("IO EXCEPTION");
			e.printStackTrace();
		}
		
	}
	public static void renderActiveModels(){
		if(!(initiated)){
			initiate();
			initiated = true;
		}
		for(Model m:models){
			m.renderModel(m.getLocation().getX(), m.getLocation().getY(), m.getLocation().getZ());
		}
	}
}
