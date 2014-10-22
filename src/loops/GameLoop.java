package loops;
 
import static org.lwjgl.input.Keyboard.KEY_F;

import java.util.ArrayList;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;

import server.Location;
import voxel.Voxel;
import Main.Controls;

public class GameLoop {
	public static ArrayList<Voxel> renderMe = new ArrayList<Voxel>();
	private static boolean pressed = false;
	public static void loop(){
		if(Mouse.isGrabbed() == false){
			Mouse.setGrabbed(true);
		}
		if(Keyboard.isKeyDown(KEY_F) && pressed == false){
			Location home = new Location(4.0f,0.0f,0.0f);
			Location home2 = new Location(8.0f,0.0f,0.0f);
			Location home3 = new Location(12.0f,0.0f,0.0f);
			Location home4 = new Location(16.0f,0.0f,0.0f);
			Voxel vox = new Voxel(home,0.5f);
			Voxel vox2 = new Voxel(home2,0.25f);
			Voxel vox3 = new Voxel(home3,0.10f);
			Voxel vox4 = new Voxel(home4,0.05f);
			System.out.println("CONFIRM F PRESS");
			pressed = true;
			renderMe.add(vox);
			renderMe.add(vox2);
			renderMe.add(vox3);
			renderMe.add(vox4);
		}
		for(Voxel vox:renderMe){
			vox.render();
		}
	}
}
