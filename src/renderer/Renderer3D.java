package renderer;

import entities.Trees;
import entities.Zombie;
import gameElements.Model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import org.lwjgl.opengl.GL11;

import Information.Console;

import utils.ModelLoader;

public class Renderer3D {
	static boolean initiated = false;
	static boolean offsetXPlus = false;
	static boolean offsetXMinus = false;
	static boolean offsetYPlus = false;
	static boolean offsetYMinus = false;
	static boolean offsetZPlus = false;
	static boolean offsetZMinus = false;
	static Model test2;
	static Model test3;
	static Model test4;
	static float one = 0;
	static float two = 0;
	static float y = 0;
	static Model spam = null;
	static Zombie testZomb = null;
	static int facesOnScreen = 0;
	public static ArrayList<Model> models = new ArrayList<Model>();
	static Random rand = new Random();
	public static void initiate(){
		try {
			//testZomb = new Zombie(10.0f,-0.01f,10.0f);
			test2 = ModelLoader.loadModel(new File("res/models/wall/wall.obj"));
			//test3 = ModelLoader.loadModel(new File("res/models/test/test.obj"));
			//test2.setCollidable(true);
			//test2.setMovable(true);
			//test2.setStartLocation(0.0f, 2.0f, 0.0f);
			//test3.setCollidable(true);
			//test3.setMovable(true);
			//test3.setStartLocation(0.0f, -12.0f, 2.0f);
			//models.add(test3);
			models.add(test2);
			Trees.addTree(20.4f, 0.01f, 10.1f);
			//Trees.addTree(30.4f, 0.01f, 20.1f);
			
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
		facesOnScreen = 0;
		for(Model m:models){
			facesOnScreen += m.faceCount();
			m.renderModel();
			m.setYaw(m.getYaw() + 1.0f);
			m.setPitch(m.getPitch() + 0.01f);
			m.setRoll(m.getRoll() + 0.10f);
			if(m.getID() == 2){
				if(offsetXPlus){
					m.move(0.1f, 0, 0);
					//testZomb.animateBackward();
					Console.setLine7("OFFSET - X - PLUS");
				}
				if(offsetXMinus){
					m.move(-0.1f, 0, 0);
					//testZomb.animateForward();
					Console.setLine7("OFFSET - X - MINUS");
				}
				if(offsetYPlus){
					m.move(0.0f, 0.0f, -0.1f);
					Console.setLine7("OFFSET - Z - PLUS");
				}
				if(offsetYMinus){
					m.move(0.0f, 0.0f, 0.1f);
					Console.setLine7("OFFSET - Z - MINUS");
				}
				if(offsetZPlus){
					m.move(0.0f, -0.1f, -0.0f);
					Console.setLine7("OFFSET - Y - PLUS");
				}
				if(offsetZMinus){
					m.move(0.0f, 0.1f, 0.0f);
					Console.setLine7("OFFSET - Y - MINUS");
				}
			}
		}
		//DRAW TRANSPARENT MODELS HERE=======================================
		GL11.glEnable(GL11.GL_BLEND);
	    GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		GL11.glAlphaFunc(GL11.GL_GREATER, 0);
		//Trees.render();
		GL11.glDisable(GL11.GL_BLEND);
		//END TRANSPARENT MODEL==============================================
		Console.setLine2("Faces on screen = " + facesOnScreen);
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
		Renderer3D.offsetXPlus = offsetXPlus;
	}
	public static boolean isOffsetXMinus() {
		return offsetXMinus;
	}
	public static void setOffsetXMinus(boolean offsetXMinus) {
		Renderer3D.offsetXMinus = offsetXMinus;
	}
	public static boolean isOffsetYPlus() {
		return offsetYPlus;
	}
	public static void setOffsetYPlus(boolean offsetYPlus) {
		Renderer3D.offsetYPlus = offsetYPlus;
	}
	public static boolean isOffsetYMinus() {
		return offsetYMinus;
	}
	public static void setOffsetYMinus(boolean offsetYMinus) {
		Renderer3D.offsetYMinus = offsetYMinus;
	}
	public static boolean isOffsetZMinus() {
		return offsetZMinus;
	}
	public static void setOffsetZMinus(boolean offsetZMinus) {
		Renderer3D.offsetZMinus = offsetZMinus;
	}
	public static boolean isOffsetZPlus() {
		return offsetZPlus;
	}
	public static void setOffsetZPlus(boolean offsetZPlus) {
		Renderer3D.offsetZPlus = offsetZPlus;
	}
}
