package Renderer;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;


public class TextureHandler {
	public static ArrayList<Texture> textures = new ArrayList<Texture>();
	public static void load(String textureName){
		FileInputStream stream;
		try {
			stream = new FileInputStream(new File("textures/" + textureName + ".png"));
			Texture texture = TextureLoader.getTexture("PNG", stream);	
			textures.add(texture);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void initialize() {
		load("splash");
	}
}
