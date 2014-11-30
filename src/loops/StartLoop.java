package loops;

import static org.lwjgl.opengl.GL11.*;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.newdawn.slick.opengl.Texture;

import Main.Controls;

public class StartLoop {
	public static boolean initiated = false;
	static Texture splash = renderer.TextureHandler.getTexture("splash");

	public static void loop() {
		if(Mouse.isGrabbed() == true){
			Mouse.setGrabbed(false);
		}
		render();
		Main.Controls.checkInput();
	}
	private static void render(){
		if(initiated == false){
			initiate();
		}
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
	    glClearColor(0.0f, 0.0f, 0.0f, 0.0f); 
		splash.bind();
		glBegin(GL_QUADS);
			glTexCoord2f(0,0);
			glVertex2i(0,0);                                       //Upper-Left
			glTexCoord2f(1,0);
			glVertex2i(Display.getWidth(),0);                      //Upper-Right 
			glTexCoord2f(1,1);
			glVertex2i(Display.getWidth(),Display.getHeight());    //Lower-Right
			glTexCoord2f(0,1);
			glVertex2i(0,Display.getHeight());                     //Lower-Left
		glEnd();
		//Controls.checkInput();
	}
	private static void initiate(){
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		glOrtho(0,1080,720,0,1,-1);
		glMatrixMode(GL_MODELVIEW);
		glEnable(GL_TEXTURE_2D);
		toggleInitiate();
		System.out.println("Display Width = " + Display.getWidth() + " Display Height = " + Display.getHeight() + " Initiated set to "+ initiated);
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
