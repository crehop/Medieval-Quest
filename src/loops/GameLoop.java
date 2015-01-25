package loops;
 
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.util.glu.GLU.gluPerspective;

import gameElements.Model;
import gameElements.Skybox;

import java.util.ArrayList;
import java.util.Random;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.newdawn.slick.opengl.Texture;

import renderer.Renderer3D;
import server.Location;
import voxel.Voxel;
import voxel.WireframePart;
import Information.Controls;
import Information.FPS;
import Interface.RightClickMenu;

public class GameLoop {
	public static ArrayList<WireframePart> renderMe = new ArrayList<WireframePart>();
	static float expand = 0;
	static WireframePart check;
	static boolean debug = false;
	static boolean gravity = false;
	static Random rand = new Random();
	static boolean initiated = false;
	public static Texture health = utils.TextureHandler.getTexture("health");
	public static Voxel test = new Voxel(new Location(0,0,0), 0.15f, health, 0.0f, 0.0f, 0.0f);
	public static void loop(){
		if(!(initiated)){
			initProjection();
			toggleInitiate();
		}
		if(Mouse.isGrabbed() == false && Main.Main.isCameraLocked() == false){
			Mouse.setGrabbed(true);
		}
		Information.FPS.updateFPS();
		Main.Main.cam.useView();
		Controls.checkInput();
		Skybox.renderSkyBox(Main.Main.cam);	
		renderer.Renderer3D.renderActiveModels();
		RightClickMenu.render();
		Information.Console.setLine1("CAMERA [X =" + (int)Main.Main.cam.getLocation().getX() +" Y =" + (int)Main.Main.cam.getLocation().getY() + " Z =" + (int)Main.Main.cam.getLocation().getZ() + " Pitch =" + (int)Main.Main.cam.getPitch() + " Yaw =" + (int)Main.Main.cam.getYaw() + "]");
		Information.Console.setLine3("FPS = " + FPS.getFPS());
		Information.Console.setLine4("MODELS ON SCREEN [" + renderer.Renderer3D.models.size() + "]");
		Information.Console.renderConsole();
	}
	public static void physicsUpdates(){
		Model m = null;
		if(Renderer3D.models.size() > 0){
			if(m == null){
				//m = RenderModels.models.get(0);
				//m.move(0.0f, 0.0f, -0.03f);
			}
		}
	}
	public static void initProjection() {
		glClearColor(1.0f, 1.0f, 1.0f, 1.0f);   
		glMatrixMode(GL_PROJECTION);
	    glLoadIdentity();
		gluPerspective(70,(float)Display.getWidth()/(float)Display.getHeight(),0.0003f,1000);
	    glMatrixMode(GL_MODELVIEW);
	    GL11.glHint(GL11.GL_PERSPECTIVE_CORRECTION_HINT, GL11.GL_NICEST);
	    glLoadIdentity();
	    //glEnable(GL_DEPTH_TEST);
	    //glDepthFunc(GL11.GL_LEQUAL);
	}
	public static void toggleInitiate(){
		GameLoop.initProjection();
	}
}
