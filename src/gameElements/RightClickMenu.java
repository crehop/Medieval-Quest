package gameElements;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;
import org.newdawn.slick.opengl.Texture;

import Information.Console;

import renderer.Renderer2D;
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
	public static int[] xy = {0,43,44,85,86,128,129,171,215,256,25,475};
	private static String[] lines = {"null","null","null","null","null","null","null","null","null","null","null"};
	
	public static void openMenu(String[] lines, int MouseX, int MouseY){
		if(menuOpen){
			closeMenu();
		}else{
			System.out.println("OUT TRIGGERED");
			x = MouseX;
			y = MouseY;
			count = 0;
			for(String line: lines){
				lines[count] = line;
				System.out.println(line + " count = " + count);
				System.out.println(lines[count]);
				count++;
			}
			count = 1;
			menuSize = lines.length;
			menuOpen = true;
		}
	}
	public static void render(){
		lines[1]="FIXED ME";
		Console.setLine5("Menu Status|| Menu = " + menuOpen + "|| MenuSize = " + menuSize + " || x = " +x +" y = " +y + " Lines = " + lines[0] + " " + lines[1]+" " + lines[2]+" " + lines[3]+" " + lines[4]);
		
		if(menuOpen){
			Renderer2D.draw2D();
			//INSET MENU DRAW CODE HERE!
			Console.setLine7("RUNNING");
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
				GL11.glVertex2f(100, 100);
				GL11.glTexCoord2f(0,44);
				GL11.glVertex2f(100, 200);
				GL11.glTexCoord2f(512,44);
				GL11.glVertex2f(200, 200);
				GL11.glTexCoord2f(512,85);
				GL11.glVertex2f(200, 100);
				GL11.glTexCoord2f(0,85);
				for(String line:lines){
					if(!(line.equalsIgnoreCase("null"))){
						// UPPER LEFT ========================================================================
						GL11.glVertex2f(x, y);
						if(count == 0){
							if(hover){
								GL11.glTexCoord2f(0,44);
							}else{
								GL11.glTexCoord2f(0,86);
							}
						}
						else if(count == menuSize){
							if(hover){
								GL11.glTexCoord2f(0,172);
							}else{
								GL11.glTexCoord2f(0,86);
							}
						}
						else{
							GL11.glTexCoord2f(25, 86);
						}
						// UPPER RIGHT ========================================================================
						GL11.glVertex2f(x + 100, y + (25 * count));
						if(count == 0){
							if(hover){
								GL11.glTexCoord2f(512,44);
							}else{
								GL11.glTexCoord2f(512,86);
							}
						}
						else if(count == menuSize){
							if(hover){
								GL11.glTexCoord2f(512,215);
							}else{
								GL11.glTexCoord2f(512,128);
							}
						}
						else{
							GL11.glTexCoord2f(475, 128);
						}
						// BOTTOM RIGHT ========================================================================
						GL11.glVertex2f(x + 100, y + 25 + (25 * count));
						if(count == 0){
							if(hover){
								GL11.glTexCoord2f(512,85);
							}else{
								GL11.glTexCoord2f(512,128);
							}
						}
						else if(count == menuSize){
							if(hover){
								GL11.glTexCoord2f(512,171);
							}else{
								GL11.glTexCoord2f(512,86);
							}
						}
						else{
							GL11.glTexCoord2f(475, 86);
						}
						// BOTTOM LEFT ========================================================================
						GL11.glVertex2f(x, y + 25 + (25 * count));
						if(count == 0){
							if(hover){
								GL11.glTexCoord2f(0,85);
							}else{
								GL11.glTexCoord2f(0,128);
							}
						}
						else if(count == menuSize){
							if(hover){
								GL11.glTexCoord2f(0,171);
							}else{
								GL11.glTexCoord2f(0,86);
							}
						}
						else{
							GL11.glTexCoord2f(25, 86);
						}
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
	}
	public static boolean isOpen() {
		return menuOpen;
	}
}
