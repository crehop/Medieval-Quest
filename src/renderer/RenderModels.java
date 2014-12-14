package renderer;

import static org.lwjgl.opengl.GL11.glPopMatrix;
import static org.lwjgl.opengl.GL11.glPushMatrix;

import gameElements.Model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import utils.ModelLoader;

public class RenderModels {
	static boolean initiated = false;
	static Model test;
	static Model test2;
	public static ArrayList<Model> models = new ArrayList<Model>();
	public static void initiate(){
		try {
			test2 = ModelLoader.loadModel(new File("res/models/zombie1/zombie1.obj"));
			models.add(test2);
			test2.setCollidable(true);
			test2.setMovable(true);
			test2.setLocation(0.0f, -1.0f, 0.0f);
			//System.out.println("MODEL LOADED V= " + test.vertices.size() + " N= " + test.normals.size() );
			//System.out.println("MODEL2 LOADED V= " + test2.vertices.size() + " N= " + test2.normals.size() );
			
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
			m.renderModel();
			m.move(0.05f, 0.00f, 0.05f);
		}
	}
}
