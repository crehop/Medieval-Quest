package loops;
 
import static org.lwjgl.opengl.GL11.glLoadIdentity;

import java.util.ArrayList;
import java.util.Random;
import org.lwjgl.input.Mouse;
import renderer.Skybox;
import wireframe.WireframePart;
import Main.Controls;
import Main.Driver;
import Main.Main;

public class GameLoop {
	public static ArrayList<WireframePart> renderMe = new ArrayList<WireframePart>();
	static float expand = 0;
	static WireframePart check;
	static boolean debug = false;
	static boolean gravity = false;
	static Random rand = new Random();
	public static void loop(){
		glLoadIdentity();
		Controls.checkInput();
		Main.cam.useView();
		Driver.checkForTick();
		if(Mouse.isGrabbed() == false){
			Mouse.setGrabbed(true);
		}
		Skybox.renderSkyBox(Main.cam);
	}
}
