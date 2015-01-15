package entities;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import org.newdawn.slick.opengl.Texture;

import server.Location;
import utils.ModelLoader;
import utils.TextureHandler;
import gameElements.Model;

public class Trees {
	private static Model baseModel = null;
	private static Texture texture = TextureHandler.getModelTexture("res/models/tree/trees.png");
	public static ArrayList<Location> trees = new ArrayList<Location>();
	public static void render(){
		if(baseModel == null){
			try {
				baseModel = ModelLoader.loadModel(new File("res/models/tree/trees.obj"), texture);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		for(Location location:trees){
			baseModel.setLocation(location);
			baseModel.renderModel();
		}
	}
	public static void addTree(float x, float y, float z){
		Location loc = new Location(x,y,z);
		trees.add(loc);
	}
}
