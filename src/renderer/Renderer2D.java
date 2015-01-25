package renderer;


import java.util.ArrayList;

import loops.GameLoop;
import loops.StartLoop;

import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.newdawn.slick.Color;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.font.effects.ColorEffect;
import org.newdawn.slick.opengl.Texture;

import static org.lwjgl.opengl.GL11.*;

public class Renderer2D {
;
	static boolean initiated = false;
	private static int x = 0;
	private static int y = 0;
	static java.awt.Font awtFont1  = new java.awt.Font("Times New Roman", java.awt.Font.BOLD, 12);
	static java.awt.Font awtFont2  = new java.awt.Font("Times New Roman", java.awt.Font.BOLD, 20);
	static java.awt.Font awtFont3 = new java.awt.Font("Times New Roman", java.awt.Font.BOLD, 28);
	private static UnicodeFont font1 = new UnicodeFont(awtFont1);
	private static UnicodeFont font2 = new UnicodeFont(awtFont2);
	public static UnicodeFont font3 = new UnicodeFont(awtFont3);

	private static ArrayList<String> lines = new ArrayList<String>();
	public Renderer2D(){
		init();
	}
	@SuppressWarnings("unchecked")
	public static void init() {
		font1.addAsciiGlyphs();
		font2.addAsciiGlyphs();
		font3.addAsciiGlyphs();

		font1.getEffects().add(new ColorEffect(java.awt.Color.white));
		font2.getEffects().add(new ColorEffect(java.awt.Color.white));
		font3.getEffects().add(new ColorEffect(java.awt.Color.white));

		try{
			font1.loadGlyphs();
			font2.loadGlyphs();
			font3.loadGlyphs();
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
	public static void renderText(float x, float y, String text, Color color, int size) {
		draw2D();
		if(!(initiated)){
			init();
			initiated = true;
		}
		switch(size){
			case 1:	
				font1.drawString(x, y, text, color);
			break;
			case 2:  
				font2.drawString(x, y, text, color);
			break;
			default: 	
				font3.drawString(x, y, text, color);


		}
		reinitiatePreviousDrawState();
	}
	public static void reinitiatePreviousDrawState(){
		glDisable(GL_TEXTURE_2D);
		glDisable(GL_BLEND);
		glClearColor(1.0f, 1.0f, 1.0f, 1.0f); 
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
		glEnable(GL_TEXTURE_2D);
        glMatrixMode(GL_MODELVIEW);
        glMatrixMode(GL_PROJECTION);
        glLoadIdentity();
        glOrtho(0.0f, Display.getWidth(), Display.getHeight(), 0.0f, 0.0f, 1.0f);
        glMatrixMode(GL_MODELVIEW);
        glLoadIdentity();
		glEnable(GL_BLEND);
		GL11.glEnable(GL11.GL_BLEND);
	    GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		GL11.glAlphaFunc(GL11.GL_GREATER, 0);
		Color.white.bind();
	}
}
