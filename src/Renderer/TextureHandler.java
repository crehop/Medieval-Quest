package renderer;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;


public class TextureHandler {
	public static ArrayList<Texture> textures = new ArrayList<Texture>();
	public static Texture load(String textureName){
		FileInputStream stream;
		try {
			stream = new FileInputStream(new File("textures/" + textureName + ".png"));
			Texture texture = TextureLoader.getTexture("PNG", stream);	
			return texture;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public static Texture getTexture(String name){
			return load(name);
	}
}
