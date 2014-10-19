package loops;
 
import static org.lwjgl.input.Keyboard.KEY_F;
import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_DEPTH_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.glClear;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glTranslatef;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;

import renderer.Camera;
import server.Location;
import voxel.Voxel;
import wireframe.Wireframe;
import wireframe.WireframePart;

import Main.Controls;
import Main.Main;

public class GameLoop {
	private static boolean pressed = false;
	public static void loop(){
		if(Keyboard.isKeyDown(KEY_F) && pressed == false){
			Location home = new Location(0.3f,0.1f,2.2f);
			Location start = new Location (1.3f,0.07f,0.03f);
			Voxel vox = new Voxel(home);
			Voxel vox2 = new Voxel(start);
			System.out.println("CONFIRM F PRESS");
			pressed = true;
		}
		Controls.checkInput();
		renderer.Renderer.renderFrame();
		Display.sync(60);
		Display.update();
		System.out.println("CONFIRM GAME LOOP");
	}
}
