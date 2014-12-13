package loops;
 
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.util.glu.GLU.gluPerspective;

import gameElements.Skybox;

import java.util.ArrayList;
import java.util.Random;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.newdawn.slick.opengl.Texture;
//import server.Location;
//import voxel.Voxel;
import voxel.WireframePart;
import Information.Delta;
import Information.FPS;
import Main.Controls;
import TerrainGeneration.Terrain;

public class GameLoop {
	public static ArrayList<WireframePart> renderMe = new ArrayList<WireframePart>();
	static float expand = 0;
	static WireframePart check;
	static boolean debug = false;
	static boolean gravity = false;
	static Random rand = new Random();
	static boolean initiated = false;
	public static Terrain terrain;
	public static Texture grass = utils.TextureHandler.getTexture("grass");
	//static Voxel test = new Voxel(new Location(10,10,10), 10.0f, grass, 0.0f, 0.0f, 0.0f);
	public static void loop(){
		Delta.addDelta();
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
		if(!(initiated)){
			initProjection();
			toggleInitiate();
		}
		if(Mouse.isGrabbed() == false){
			Mouse.setGrabbed(true);
		}
		Information.FPS.updateFPS();
		Main.Main.cam.useView();
		Controls.checkInput();
		Skybox.renderSkyBox(Main.Main.cam);
		terrain.renderChunks(Main.Main.cam);	
		renderer.RenderModels.renderActiveModels();
		Main.Console.setLine1("CAMERA [X =" + Main.Main.cam.getLocation().getX() +" Y =" + Main.Main.cam.getLocation().getY() + " Z =" + Main.Main.cam.getLocation().getZ() + "]" );
		Main.Console.setLine2("DELTA = " + Delta.getDifference() );
		Main.Console.setLine3("FPS = " + FPS.getFPS());
		Main.Console.setLine4("MODELS ON SCREEN [" + renderer.RenderModels.models.size() + "]");
		Main.Console.renderConsole();
		//System.out.println(Main.cam.getLocation().getX() + " " + Main.cam.getLocation().getY() + " " + Main.cam.getLocation().getZ() );
		//STAY LAST IN THIS ORDER ++++++++++++++++++
		glLoadIdentity();
		//++++++++++++++++++++++++++++++++++++++++++
	}
	public static void initProjection() {
		glClearColor(0.0f, 0.0f, 0.0f, 0.0f);   
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
		GameLoop.initProjection();
	}
}
