package renderer;

import java.awt.Font;

import loops.GameLoop;
import loops.StartLoop;

import org.lwjgl.opengl.Display;
import org.newdawn.slick.Color;
import org.newdawn.slick.TrueTypeFont;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.util.glu.GLU.gluPerspective;

public class TextRenderer {
	static TrueTypeFont font;
	static boolean initiated = false;
	public TextRenderer(){
		init();
	}
	public static void init() {
	    // load a default java font
	    Font awtFont = new Font("Times New Roman", Font.BOLD, 24);
	    font = new TrueTypeFont(awtFont, false);
	         
	    // load font from a .ttf file
	    //try {
	    //   InputStream inputStream = ResourceLoader.getResourceAsStream("myfont.ttf");
	    //     
	    //    Font awtFont2 = Font.createFont(Font.TRUETYPE_FONT, inputStream);
	    //    awtFont2 = awtFont2.deriveFont(24f); // set font size
	    //    font2 = new TrueTypeFont(awtFont2, false);
	    //         
	    //} catch (Exception e) {
	    //    e.printStackTrace();
	    //}   
	}
	public static void render(/*float x, float y, String text, Color color*/) {
		draw2D();
		if(!(initiated)){
			init();
			initiated = true;
		}
		//font.drawString(100, 50, "THE LIGHTWEIGHT JAVA GAMES LIBRARY");
		font.drawString(100, 50, "THE LIGHTWEIGHT JAVA GAMES LIBRARY", Color.yellow);
	    //font.drawString(x, y, text, color);
		reinitiatePreviousDrawState();
	}
	public static void reinitiatePreviousDrawState(){
		if(Main.Main.getLoop().equalsIgnoreCase("game")){
			GameLoop.toggleInitiate();
		}
		else if(Main.Main.getLoop().equalsIgnoreCase("start")){
			StartLoop.toggleInitiate();
		}
		else{
			
		}
	}
	public static void draw2D()
	{
		glDisable(GL_LIGHTING);
		// switch to 2d drawing
		glClearColor(0.0f, 0.0f, 0.0f, 0.0f);                
		glClearDepth(1); 
		glViewport(0,0,800,600);
		glMatrixMode(GL_MODELVIEW);
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		glOrtho(0, Display.getWidth(), Display.getHeight(), 0, 1, -1);
		glMatrixMode(GL_MODELVIEW);
		glLoadIdentity();
	}
}
