package renderer;


import loops.GameLoop;
import loops.StartLoop;

import org.lwjgl.opengl.Display;
import org.newdawn.slick.Color;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.font.effects.ColorEffect;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.util.glu.GLU.gluPerspective;

public class TextRenderer {
	private static UnicodeFont font;
	static boolean initiated = false;
	public TextRenderer(){
		init();
	}
	public static void init() {
		System.out.println("LOADED!");
		java.awt.Font awtFont = new java.awt.Font("Times New Roman", java.awt.Font.BOLD, 28);
		font = new UnicodeFont(awtFont);
		font.addAsciiGlyphs();
		font.getEffects().add(new ColorEffect(java.awt.Color.white));

		try{
			font.loadGlyphs();
		}catch(SlickException e){
			e.printStackTrace();
		}
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
		glEnable(GL_BLEND);
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
		font.drawString(200, 250, "TEXT FIXED", Color.red);
		Color.white.bind();
		reinitiatePreviousDrawState();
	}
	public static void reinitiatePreviousDrawState(){
		glDrawBuffer(GL_FRONT);
		glEnable(GL_DEPTH_TEST);
		glDisable(GL_TEXTURE_2D);
		glClearColor(0.0f, 0.0f, 0.0f, 0.0f); 
        glDepthFunc(GL_LEQUAL);
        glHint(GL_PERSPECTIVE_CORRECTION_HINT, GL_NICEST);
        Main.Main.cam.initProjection();
		if(Main.Main.getLoop().equalsIgnoreCase("game")){
			GameLoop.initProjection();
		}
		else if(Main.Main.getLoop().equalsIgnoreCase("start")){
			StartLoop.initiateProjection();
		}
		else{
			
		}
	}
	public static void draw2D()
	{
		glDrawBuffer(GL_BACK);
		glEnable(GL_TEXTURE_2D);
        glMatrixMode(GL_MODELVIEW);
        glMatrixMode(GL_PROJECTION);
        glLoadIdentity();
        glOrtho(0.0f, Display.getWidth(), Display.getHeight(), 0.0f, 0.0f, 1.0f);
        glMatrixMode(GL_MODELVIEW);
        glLoadIdentity();
	}
}
