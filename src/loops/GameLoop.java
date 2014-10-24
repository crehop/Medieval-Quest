package loops;
 
import static org.lwjgl.input.Keyboard.KEY_F;

import java.util.ArrayList;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;

import renderer.Skybox;
import server.Location;
import voxel.Voxel;
import Main.Controls;
import Main.Main;

public class GameLoop {
	public static ArrayList<Voxel> renderMe = new ArrayList<Voxel>();
	private static boolean pressed = false;
	static float expand = 0;
	public static void loop(){
		if(Mouse.isGrabbed() == false){
			Mouse.setGrabbed(true);
		}
		if(Keyboard.isKeyDown(KEY_F) && pressed == false){
			/*Location home = new Location(6.0f,0.0f,0.0f);
			Location home2 = new Location(7.25f,0.0f,0.0f);
			Location home3 = new Location(8.0f,0.0f,0.0f);
			Location home4 = new Location(8.50f,0.0f,0.0f);
			Location home5 = new Location(8.6f,0.0f,0.0f);
			Location home6 = new Location(8.65f,0.0f,0.0f);
			Voxel vox = new Voxel(home,0.5f);
			Voxel vox2 = new Voxel(home2,0.25f);
			Voxel vox3 = new Voxel(home3,0.10f);
			Voxel vox4 = new Voxel(home4,0.05f);
			Voxel vox5 = new Voxel(home5,0.025f);
			Voxel vox6 = new Voxel(home6,0.005f);
			System.out.println("CONFIRM F PRESS");
			pressed = true;
			renderMe.add(vox);
			renderMe.add(vox2);
			renderMe.add(vox3);
			renderMe.add(vox4);
			renderMe.add(vox5);
			renderMe.add(vox6);
			*/
			for(float x = 0; x <= 0.05f; x+=0.005f){
				Voxel newVoxX = new Voxel(new Location(x,0,0),0.0025f);
				renderMe.add(newVoxX);
				for(float z = 0; z <= 0.05f; z+=0.005f){
					Voxel newVoxY = new Voxel(new Location(x,0,z),0.0025f);
					renderMe.add(newVoxY);
					for(float y = 0; y <= 0.15f; y+=0.005f){
						Voxel newVoxZ = new Voxel(new Location(x,y,z),0.0025f);
						renderMe.add(newVoxZ);
					}
				}
			}
		}
		for(Voxel vox:renderMe){
			vox.getLocation().setX(expand + vox.getLocation().getX());
			vox.render();
		}
		System.out.println(expand);
		Skybox.renderSkyBox(Main.cam);
	}
	public static void expand(){
		expand += 0.0005f;
	}
	public static void contract(){
		expand -= 0.0005f;
	}
	public static void reset(){
		expand = 0.0f;
	}
}
