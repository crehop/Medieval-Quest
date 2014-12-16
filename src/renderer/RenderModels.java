package renderer;

import static org.lwjgl.opengl.GL11.glPopMatrix;
import static org.lwjgl.opengl.GL11.glPushMatrix;

import gameElements.Model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import utils.ModelLoader;

public class RenderModels {
	static boolean initiated = false;
	static Model test;
	static Model test2;
	static Model test3;
	static Model test4;
	static Model test5;
	static Model test6;
	static Model test7;
	static Random rand = new Random();
	public static ArrayList<Model> models = new ArrayList<Model>();
	public static void initiate(){
		try {
			test2 = ModelLoader.loadModel(new File("res/models/zombie1/zombie1.obj"));
			test3 = ModelLoader.loadModel(new File("res/models/zombie1/zombie1.obj"));
			test4 = ModelLoader.loadModel(new File("res/models/zombie1/zombie1.obj"));
			test5 = ModelLoader.loadModel(new File("res/models/zombie1/zombie1.obj"));
			test6 = ModelLoader.loadModel(new File("res/models/zombie1/zombie1.obj"));
			test7 = ModelLoader.loadModel(new File("res/models/zombie1/zombie1.obj"));
			test2.setCollidable(false);
			test2.setMovable(true);
			test2.setLocation(0.0f, 2.0f, 0.0f);
			test3.setCollidable(false);
			test3.setMovable(true);
			test3.setLocation(0.0f, 2.0f, 10.0f);
			test4.setCollidable(false);
			test4.setMovable(true);
			test4.setLocation(10.0f, 2.0f, 0.0f);
			test5.setCollidable(false);
			test5.setMovable(true);
			test5.setLocation(20.0f, 2.0f, 0.0f);
			test6.setCollidable(false);
			test6.setMovable(true);
			test6.setLocation(0.0f, 2.0f, 20.0f);
			test7.setCollidable(false);
			test7.setMovable(true);
			test7.setLocation(10.0f, 2.0f, 10.0f);
			models.add(test3);
			models.add(test4);
			models.add(test5);
			models.add(test6);
			models.add(test7);
			models.add(test2);
			
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
		float one = (rand.nextFloat()/5);
		float two = (rand.nextFloat()/5);
		if(!(initiated)){
			initiate();
			initiated = true;
		}
		for(Model m:models){
			m.renderModel();
			if(rand.nextFloat() > 0.5f){
				one *= -1;
			}
			if(rand.nextFloat() > 0.5f){
				two *= -1;
			}
			m.move(one, 0.0f, two);
		}
	}
}
