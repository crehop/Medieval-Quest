package loops;
 
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.util.glu.GLU.gluPerspective;

import gameElements.Model;
import gameElements.Skybox;

import java.util.ArrayList;
import java.util.Random;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.newdawn.slick.opengl.Texture;

import renderer.RenderModels;
import server.Location;
import voxel.Voxel;
import voxel.WireframePart;
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
	public static Texture health = utils.TextureHandler.getTexture("health");
	public static Voxel test = new Voxel(new Location(0,0,0), 0.15f, health, 0.0f, 0.0f, 0.0f);
	public static void loop(){
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
		terrain.renderChunks(Main.Main.cam);	
		renderer.RenderModels.renderActiveModels();
		Skybox.renderSkyBox(Main.Main.cam);
		Main.Console.setLine1("CAMERA [X =" + Main.Main.cam.getLocation().getX() +" Y =" + Main.Main.cam.getLocation().getY() + " Z =" + Main.Main.cam.getLocation().getZ() + " Pitch =" + Main.Main.cam.getPitch() + " Yaw =" + Main.Main.cam.getYaw() + "]");
		Main.Console.setLine3("FPS = " + FPS.getFPS());
		Main.Console.setLine4("MODELS ON SCREEN [" + renderer.RenderModels.models.size() + "]");
		Main.Console.renderConsole();
		//STAY LAST IN THIS ORDER ++++++++++++++++++
		glLoadIdentity();
		//++++++++++++++++++++++++++++++++++++++++++
	}
	public static void physicsUpdates(){
		Model m = null;
		if(RenderModels.models.size() > 0){
			if(m == null){
				//m = RenderModels.models.get(0);
				//m.move(0.0f, 0.0f, -0.03f);
			}
		}
	}
	public static void initProjection() {
		glClearColor(0.0f, 0.0f, 0.0f, 0.0f);   
		glMatrixMode(GL_PROJECTION);
	    glLoadIdentity();
		gluPerspective(70,(float)Display.getWidth()/(float)Display.getHeight(),0.0003f,1000);
	    glMatrixMode(GL_MODELVIEW);
	    glLoadIdentity();
	    glEnable(GL_DEPTH_TEST);
	    glCullFace(GL_BACK);
	    if(terrain == null){
	    	terrain = new Terrain(false);
	    }
	}
	public static void toggleInitiate(){
		GameLoop.initProjection();
	}
}
