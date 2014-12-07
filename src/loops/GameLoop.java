package loops;
 
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.util.glu.GLU.gluPerspective;

import java.util.ArrayList;
import java.util.Random;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.newdawn.slick.opengl.Texture;

import renderer.Skybox;
import server.Location;
import voxel.Voxel;
import wireframe.WireframePart;
import Main.Controls;
import Main.Driver;
import Main.Main;
import TerrainGeneration.Terrain;

public class GameLoop {
	public static ArrayList<WireframePart> renderMe = new ArrayList<WireframePart>();
	static float expand = 0;
	static WireframePart check;
	static boolean debug = false;
	static boolean gravity = false;
	static Random rand = new Random();
	static boolean initiated = false;
	static Terrain terrain;
	static Texture grass = renderer.TextureHandler.getTexture("grass");
	static Voxel test = new Voxel(new Location(10,10,10), 10.0f, grass, 0.0f, 0.0f, 0.0f);
	public static void loop(){

		if(!(initiated)){
			initProjection();
			toggleInitiate();
		}
		if(Mouse.isGrabbed() == false){
			Mouse.setGrabbed(true);
		}
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
		Main.cam.useView();
		Controls.checkInput();
		Skybox.renderSkyBox(Main.cam);
		terrain.renderChunks(Main.cam);
		glLoadIdentity();
		//System.out.println(Main.cam.getLocation().getX() + " " + Main.cam.getLocation().getY() + " " + Main.cam.getLocation().getZ() );
	}
	private static void initProjection() {
	    GL11.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
	    glMatrixMode(GL_PROJECTION);
	    glLoadIdentity();
		gluPerspective(70,(float)Display.getWidth()/(float)Display.getHeight(),0.0003f,1000);
	    glMatrixMode(GL_MODELVIEW);
	    glLoadIdentity();
	    glEnable(GL_DEPTH_TEST);
	    if(terrain == null){
	    	terrain = new Terrain(false);
	    }
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
