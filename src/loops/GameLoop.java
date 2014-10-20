package loops;
 
import static org.lwjgl.input.Keyboard.KEY_F;

import java.util.ArrayList;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import server.Location;
import voxel.Voxel;
import Main.Controls;

public class GameLoop {
	public static ArrayList<Voxel> renderMe = new ArrayList<Voxel>();
	private static boolean pressed = false;
	public static void loop(){
		if(Keyboard.isKeyDown(KEY_F) && pressed == false){
			Location home = new Location(0.3f,0.1f,2.2f);
			Location start = new Location (1.3f,0.07f,0.03f);
			Voxel vox = new Voxel(home);
			Voxel vox2 = new Voxel(start);
			System.out.println("CONFIRM F PRESS");
			pressed = true;
			renderMe.add(vox);
			renderMe.add(vox2);
		}
		for(Voxel vox:renderMe){
			vox.render();
		}
		Controls.checkInput();
		renderer.Renderer.renderFrame();
		Display.sync(60);
		Display.update();
	}
}
