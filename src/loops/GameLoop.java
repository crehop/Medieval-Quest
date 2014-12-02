package loops;
 
import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_DEPTH_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_DEPTH_TEST;
import static org.lwjgl.opengl.GL11.GL_MODELVIEW;
import static org.lwjgl.opengl.GL11.GL_PROJECTION;
import static org.lwjgl.opengl.GL11.glClear;
import static org.lwjgl.opengl.GL11.glEnable;
import static org.lwjgl.opengl.GL11.glLoadIdentity;
import static org.lwjgl.opengl.GL11.glMatrixMode;
import static org.lwjgl.util.glu.GLU.gluPerspective;

import java.util.ArrayList;
import java.util.Random;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.newdawn.slick.opengl.Texture;

import renderer.Skybox;
import server.Location;
import voxel.Voxel;
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
	static boolean initiated = false;
	static Texture grass = renderer.TextureHandler.getTexture("grass");
	static Voxel test = new Voxel(new Location(0,0,0), 10.0f, grass, 0, 0, 0);
	public static void loop(){
		if(!(initiated)){
			initProjection();
			toggleInitiate();
		}
		glLoadIdentity();
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
		Controls.checkInput();
		Main.cam.useView();
		Driver.checkForTick();
		if(Mouse.isGrabbed() == false){
			Mouse.setGrabbed(true);
		}
		Skybox.renderSkyBox(Main.cam);
		test.render();
		System.out.println(Main.cam.getLocation().getX() + " " + Main.cam.getLocation().getY() + " " + Main.cam.getLocation().getZ() );
	}
	private static void initProjection() {
		glMatrixMode(GL_PROJECTION);
		gluPerspective(70,(float)Display.getWidth()/(float)Display.getHeight(),0.0003f,1000);
		glMatrixMode(GL_MODELVIEW);
		glEnable(GL_DEPTH_TEST);
	}
	public static void toggleInitiate(){
		if(initiated == true){
			initiated = false;
		}
		if(initiated == false){
			initiated = true;
		}
	}
}
