package renderer;

import static org.lwjgl.opengl.GL11.glPopMatrix;
import static org.lwjgl.opengl.GL11.glPushMatrix;

import entities.Trees;
import entities.Zombie;
import gameElements.Model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import Information.Console;

import utils.ModelLoader;

public class RenderModels {
	static boolean initiated = false;
	static boolean offsetXPlus = false;
	static boolean offsetXMinus = false;
	static boolean offsetYPlus = false;
	static boolean offsetYMinus = false;
	static Model test2;
	static Model test3;
	static Model test4;
	static float one = 0;
	static float two = 0;
	static float y = 0;
	static Model spam = null;
	static Zombie testZomb = null;
	public static ArrayList<Model> models = new ArrayList<Model>();
	static Random rand = new Random();
	public static void initiate(){
		try {
			testZomb = new Zombie(10.0f,-0.01f,10.0f);
			test2 = ModelLoader.loadModel(new File("res/models/wall/wall.obj"));
			test3 = ModelLoader.loadModel(new File("res/models/zombie/zombie.obj"));
			test2.setCollidable(true);
			test2.setMovable(true);
			test2.setStartLocation(0.0f, 2.0f, 0.0f);
			test3.setCollidable(true);
			test3.setMovable(true);
			test3.setStartLocation(0.0f, -12.0f, 2.0f);
			models.add(test3);
			models.add(test2);
			Trees.addTree(20.4f, 0.01f, 10.1f);
			Trees.addTree(30.4f, 0.01f, 20.1f);
			
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
		Trees.render();
		Console.setLine7("" + one);
		one++;
		testZomb.render();
		for(Model m:models){
			m.renderModel();
			if(m.getID() == 2){
				if(offsetXPlus){
					m.move(0.01f, 0, 0);
					testZomb.animateBackward();
					Console.setLine7("OFFSET - X - PLUS");
				}
				if(offsetXMinus){
					m.move(-0.01f, 0, 0);
					testZomb.animateForward();
					Console.setLine7("OFFSET - X - MINUS");
				}
				if(offsetYPlus){
					m.move(0.0f, 0.0f, 0.01f);
					Console.setLine7("OFFSET - Y - PLUS");
				}
				if(offsetYMinus){
					m.move(0.0f, 0.0f, -0.01f);
					Console.setLine7("OFFSET - Y - MINUS");
				}
			}
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
	public static boolean isOffsetXPlus() {
		return offsetXPlus;
	}
	public static void setOffsetXPlus(boolean offsetXPlus) {
		RenderModels.offsetXPlus = offsetXPlus;
	}
	public static boolean isOffsetXMinus() {
		return offsetXMinus;
	}
	public static void setOffsetXMinus(boolean offsetXMinus) {
		RenderModels.offsetXMinus = offsetXMinus;
	}
	public static boolean isOffsetYPlus() {
		return offsetYPlus;
	}
	public static void setOffsetYPlus(boolean offsetYPlus) {
		RenderModels.offsetYPlus = offsetYPlus;
	}
	public static boolean isOffsetYMinus() {
		return offsetYMinus;
	}
	public static void setOffsetYMinus(boolean offsetYMinus) {
		RenderModels.offsetYMinus = offsetYMinus;
	}
}
