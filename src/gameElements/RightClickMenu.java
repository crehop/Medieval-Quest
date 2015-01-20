package gameElements;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;
import org.newdawn.slick.opengl.Texture;
import Information.Console;

import renderer.Renderer2D;
import utils.Pixel;
import utils.TextureHandler;

public class RightClickMenu {
	public static Texture menu = TextureHandler.getTexture("menuitem");
	public static int x = 0;
	public static int y = 0;
	public static int sizeX = 0;
	public static int sizeY = 0;
	private static int choice = 0;
	public static boolean hover = false;
	public static boolean menuOpen = false;
	public static boolean clicked = false;
	private static int count = 1;
	private static int menuSize = 0;
	private static double lastToggle = Main.Main.getTime();
	public static int[] xy = {0,43,44,85,86,128,129,171,215,256,25,475};
	private static String[] lines = {"null","null","null","null","null","null","null","null","null","null","null"};
	
	public static void openMenu(String[] args, int MouseX, int MouseY){
		if(Main.Main.getTime() - lastToggle > 4.0f){
			lastToggle = Main.Main.getTime();
			if(menuOpen){
				closeMenu();
			}else{
				x = MouseX;
				y = MouseY;
				count = 1;
				for(int i = 0; i<args.length -1; i++){
					lines[i] = args[i];
				}
				count = 1;
				menuSize = args.length;
				menuOpen = true;
			}
		}
	}
	public static void render(){
		Console.setLine5("Menu Status|| Menu = " + menuOpen + "|| MenuSize = " + menuSize + " || x = " +x +" y = " +y + " Lines = " + lines[0] + " " + lines[1]+" " + lines[2]+" " + lines[3]+" " + lines[4]);
		if(menuOpen){
			Renderer2D.draw2D();
			//INSET MENU DRAW CODE HERE!
			menu.bind();
			GL11.glBegin(GL11.GL_QUADS);
				GL11.glEnable(GL11.GL_TEXTURE_2D);
				hover = false;
				if(Mouse.getX() >= x && Mouse.getX() <= (x + sizeX)){
					if(Mouse.getY() >= y && Mouse.getY() <= (y + sizeY)){
						hover = true;
					}
				}
				count = 1;				
				GL11.glTexCoord2f(0,Pixel.getPixel(44, 256));
				GL11.glVertex2f(400, 400);
				GL11.glTexCoord2f(1,Pixel.getPixel(44, 256));
				GL11.glVertex2f(400, 425);
				GL11.glTexCoord2f(1,Pixel.getPixel(85, 256));
				GL11.glVertex2f(500, 425);
				GL11.glTexCoord2f(0,Pixel.getPixel(85, 256));
				GL11.glVertex2f(500, 400);
				for(String line:lines){
					if(!(line.equalsIgnoreCase("null"))){
						// UPPER LEFT ========================================================================
						if(count == 0){
							if(hover){
								GL11.glTexCoord2f(0,Pixel.getPixel(44, 256));
							}else{
								GL11.glTexCoord2f(0,Pixel.getPixel(86, 256));
							}
						}
						else if(count == menuSize){
							if(hover){
								GL11.glTexCoord2f(0,Pixel.getPixel(172, 256));
							}else{
								GL11.glTexCoord2f(0,Pixel.getPixel(86, 256));
							}
						}
						else{
							GL11.glTexCoord2f(Pixel.getPixel(25, 512), Pixel.getPixel(86, 256));
						}
						GL11.glVertex2f(x, y);
						// UPPER RIGHT ========================================================================
						if(count == 0){
							if(hover){
								GL11.glTexCoord2f(1,Pixel.getPixel(44, 256));
							}else{
								GL11.glTexCoord2f(1,Pixel.getPixel(86, 256));
							}
						}
						else if(count == menuSize){
							if(hover){
								GL11.glTexCoord2f(1,Pixel.getPixel(215, 256));
							}else{
								GL11.glTexCoord2f(1,Pixel.getPixel(128, 256));
							}
						}
						else{
							GL11.glTexCoord2f(Pixel.getPixel(475, 512), 128);
						}
						GL11.glVertex2f(x + 100, y);
						// BOTTOM RIGHT ========================================================================
						if(count == 0){
							if(hover){
								GL11.glTexCoord2f(1,Pixel.getPixel(85, 256));
							}else{
								GL11.glTexCoord2f(1,Pixel.getPixel(128, 256));
							}
						}
						else if(count == menuSize){
							if(hover){
								GL11.glTexCoord2f(1,Pixel.getPixel(171, 256));
							}else{
								GL11.glTexCoord2f(1,Pixel.getPixel(86, 256));
							}
						}
						else{
							GL11.glTexCoord2f(Pixel.getPixel(475, 512), Pixel.getPixel(86, 256));
						}
						GL11.glVertex2f(x + 100, y + (25 * count));
						// BOTTOM LEFT ========================================================================
						if(count == 0){
							if(hover){
								GL11.glTexCoord2f(0,Pixel.getPixel(85, 256));
							}else{
								GL11.glTexCoord2f(0,Pixel.getPixel(128, 256));
							}
						}
						else if(count == menuSize){
							if(hover){
								GL11.glTexCoord2f(0,Pixel.getPixel(171, 256));
							}else{
								GL11.glTexCoord2f(0,Pixel.getPixel(86, 256));
							}
						}
						else{
							GL11.glTexCoord2f(Pixel.getPixel(25, 512), Pixel.getPixel(86, 256));
						}
						GL11.glVertex2f(x, y +(25 * count));
						count++;
					}
				}
				GL11.glDisable(GL11.GL_TEXTURE_2D);
			GL11.glEnd();
			//END INSERT MENU DRAW CODE!
			
			Renderer2D.reinitiatePreviousDrawState();
		}else{
			Console.setLine7("NOT RUNNING");
		}
	}
	public static int getChoice(){
		return choice;
	}
	public static void closeMenu(){
		//for(int i = 0; i <lines.length; i++){
		//	lines[i] = "null";
		//}
	    x = 0;
		y = 0;
		sizeX = 0;
		sizeY = 0;
		choice = 0;
		hover = false;
		menuOpen = false;
		clicked = false;
		count = 1;
		menuSize = 0;
		for(int i = 0; i<lines.length -1; i++){
			lines[i] = "null";
		}
	}
	public static boolean isOpen() {
		return menuOpen;
	}
}
