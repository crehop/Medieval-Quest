package loops;
 
import static org.lwjgl.input.Keyboard.KEY_F;

import java.util.ArrayList;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

import renderer.Skybox;
import server.Location;
import wireframe.WireframePart;
import Main.Main;

public class GameLoop {
	public static ArrayList<WireframePart> renderMe = new ArrayList<WireframePart>();
	private static boolean pressed = false;
	static float expand = 0;
	static WireframePart check;
	static boolean debug = false;
	public static void loop(){
		if(Mouse.isGrabbed() == false){
			Mouse.setGrabbed(true);
			Location home = new Location(6.0f,0.0f,0.0f);
			WireframePart part = new WireframePart(home, null, 2.0f, 6.0f, 2.0f, 0.25f);
			check = part;
		}
		if(Keyboard.isKeyDown(KEY_F) && pressed == false){
			/*Location home2 = new Location(7.25f,0.0f,0.0f);
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
		}
		for(WireframePart part:renderMe){
			part.render();
			if(debug){
				if(part.getRotation() < 100){
					part.setRotation(part.getRotation() + 0.00005f);
				}
			}else if (!(debug)){
				if(part.getRotation() > 0){
					part.setRotation(part.getRotation() - 0.00005f);
				}
			}
		}
		Skybox.renderSkyBox(Main.cam);
	}
	public static void expand(){
		debug = true;
		System.out.println("DEBUG SET TO TRUE");
	}
	public static void contract(){
		debug = false;
		System.out.println("DEBUG SET TO FALSE");
	}
	public static void reset(){
		expand = 0.0f;
	}
}
