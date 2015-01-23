package loops;

import static org.lwjgl.opengl.GL11.*;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.newdawn.slick.opengl.Texture;

public class StartLoop {
	public static boolean initiated = false;
	static Texture splash = utils.TextureHandler.getTexture("splash");

	public static void loop() {
		if(Mouse.isGrabbed() == true){
			Mouse.setGrabbed(false);
		}
		render();
		Information.Controls.checkInput();
	}
	private static void render(){
		if(initiated == false){
			initiateProjection();
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
	public static void toggleInitiate(){
		if(initiated){
			initiated = false;             
		}
		else if(!(initiated)){
			initiated = true;
		}
	}
	public static void initiateProjection() {
		glOrtho(0,1080,720,0,1,-1);
		toggleInitiate();
	}
}
