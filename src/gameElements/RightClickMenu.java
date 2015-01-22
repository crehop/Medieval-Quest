package gameElements;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;
import org.newdawn.slick.Color;
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
				for(int i = 0; i<args.length; i++){
					lines[i + 1] = args[i];
				}
				menuSize = args.length;
				menuOpen = true;
			}
		}
	}
	public static void render(){
		if(menuOpen){
			Mouse.setGrabbed(false);
			Main.Main.lockCamera(true);
			Renderer2D.draw2D();


			//INSET MENU DRAW CODE HERE!
			menu.bind();
			GL11.glBegin(GL11.GL_QUADS);
				GL11.glEnable(GL11.GL_TEXTURE_2D);
				GL11.glEnable(GL11.GL_DEPTH_TEST);
				hover = false;
				//if(Mouse.getX() >= x && Mouse.getX() <= (x + sizeX)){
					//if(Mouse.getY() >= y && Mouse.getY() <= (y + sizeY)){
					//	hover = true;
					//}
				//}
				count = 1;				
				for(String line:lines){
					if(!(line.equalsIgnoreCase("null"))){
						if(count == menuSize){
						}
						//UPPER LEFT ========================================================================
						if(count == 1){
							if(hover){
								GL11.glTexCoord2f(0,Pixel.getPixel(22, 128));
							}else{
								GL11.glTexCoord2f(0,Pixel.getPixel(43, 128));
							}
						}
						else if(count == menuSize){
							if(hover){
								GL11.glTexCoord2f(0,Pixel.getPixel(86, 128));
							}else{
								GL11.glTexCoord2f(0,Pixel.getPixel(107, 128));
							}
						}
						else{
							if(hover){
								GL11.glTexCoord2f(0, 0);
							}else{
								GL11.glTexCoord2f(0, Pixel.getPixel(64, 128));
							}
						}
						GL11.glVertex2f(x, y + (25 * count) - 25);
						//UPPER RIGHT ========================================================================
						if(count == 1){
							if(hover){
								GL11.glTexCoord2f(1,Pixel.getPixel(22, 128));
							}else{
								GL11.glTexCoord2f(1,Pixel.getPixel(43, 128));
							}
						}
						else if(count == menuSize){
							if(hover){
								GL11.glTexCoord2f(1,Pixel.getPixel(86, 128));
							}else{
								GL11.glTexCoord2f(1,Pixel.getPixel(107, 128));
							}
						}
						else{
							if(hover){
								GL11.glTexCoord2f(1, 0);
							}else{
								GL11.glTexCoord2f(1, Pixel.getPixel(64, 128));
							}
						}
						GL11.glVertex2f(x + 100, y + (25 * count) - 25);
						//BOTTOM RIGHT ========================================================================
						if(count == 1){
							if(hover){
								GL11.glTexCoord2f(1,Pixel.getPixel(42, 128));
							}else{
								GL11.glTexCoord2f(1,Pixel.getPixel(65, 128));
							}
						}
						else if(count == menuSize){
							if(hover){
								GL11.glTexCoord2f(1,Pixel.getPixel(106, 128));
							}else{
								GL11.glTexCoord2f(1,1);
							}
						}
						else{
							if(hover){
								GL11.glTexCoord2f(1,Pixel.getPixel(21, 128));
							}else{
								GL11.glTexCoord2f(1, Pixel.getPixel(86, 128));
							}
						}
						GL11.glVertex2f(x + 100, y + (25 * count));
						//BOTTOM LEFT ========================================================================
						if(count == 1){
							if(hover){
								GL11.glTexCoord2f(0,Pixel.getPixel(42, 128));
							}else{
								GL11.glTexCoord2f(0,Pixel.getPixel(65, 128));
							}
						}
						else if(count == menuSize){
							if(hover){
								GL11.glTexCoord2f(0,Pixel.getPixel(106, 128));
							}else{
								GL11.glTexCoord2f(0, 1);
							}
						}
						else{
							if(hover){
								GL11.glTexCoord2f(0,Pixel.getPixel(21, 128));
							}else{
								GL11.glTexCoord2f(0, Pixel.getPixel(86, 128));
							}
						}
						GL11.glVertex2f(x, y + (25 * count));
						count++;
					}
				}
				GL11.glDisable(GL11.GL_DEPTH_TEST);
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
		Main.Main.lockCamera(false);
	}
	public static boolean isOpen() {
		return menuOpen;
	}
}
