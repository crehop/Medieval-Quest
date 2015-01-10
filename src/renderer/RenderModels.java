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
	static Model test2;
	static Model test3;
	static float one = 0;
	static float two = 0;
	static float y = 0;
	static Model spam = null;
	public static ArrayList<Model> models = new ArrayList<Model>();
	static Random rand = new Random();
	public static void initiate(){
		try {
			//test2 = ModelLoader.loadModel(new File("res/models/wall/wall.obj"));
			test3 = ModelLoader.loadModel(new File("res/models/zombie/zombie.obj"));
			//test2.setCollidable(true);
			//test2.setMovable(true);
			//test2.setStartLocation(0.0f, 2.0f, 0.0f);
			test3.setCollidable(true);
			test3.setMovable(true);
			test3.setStartLocation(0.0f, -12.0f, 2.0f);
			models.add(test3);
			//models.add(test2);
			
			//System.out.println("MODEL LOADED V= " + test.vertices.size() + " N= " + test.normals.size() );
			//System.out.println("MODEL2 LOADED V= " + test2.vertices.size() + " N= " + test2.normals.size() );
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static void renderActiveModels(){
		if(!(initiated)){
			initiate();
			initiated = true;
		}
		for(Model m:models){
			one = rand.nextFloat();
			two = rand.nextFloat();
			y = rand.nextFloat();
			if(rand.nextFloat() < 0.5f){
				one *= -1;
			}
			if(rand.nextFloat() < 0.5f){
				two *= -1;
			}
			m.renderModel();
			//m.move(one/5, 0.0f, two/5);
		}
	}
	public static void spam(){
		try {
			if(spam == null)spam = ModelLoader.loadModel(new File("res/models/zombie/zombie.obj"));
			test3.setCollidable(true);
			test3.setMovable(true);
			test3.setStartLocation(0.0f, 2.0f, (models.get(models.size()-1).getLocation().getZ() + 2.0f));
			models.add(spam);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
