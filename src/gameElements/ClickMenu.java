package gameElements;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.newdawn.slick.Color;
import org.newdawn.slick.opengl.Texture;

import renderer.Renderer2D;
import utils.Pixel;
import utils.TextureHandler;

public class ClickMenu {
	private  Texture menu = TextureHandler.getTexture("menuitem");
	private  int x = 0;
	private  int y = 0;
	private  float sizeX = 100.0f;
	private  float sizeY = 25.0f;
	private  int choice = 0;
	private  int clickChoice = 0;
	private  boolean menuOpen = false;
	private  int count = 1;
	private  int menuSize = 0;
	private  double lastToggle = 0.0f;
	private  String[] lines = {"null","null","null","null","null","null","null","null","null","null","null"};
	
	public ClickMenu(String[] args, int MouseX, int MouseY){
		Interface.addMenu(this);
		sizeX = 125.0f;
		sizeY = 25.0f;
		x = MouseX;
		y = MouseY;
		for(int i = 0; i<args.length; i++){
			lines[i + 1] = args[i];
		}
		menuSize = args.length;
	}
	public ClickMenu(String[] args, int MouseX, int MouseY, int xSize, int ySize){
		Interface.addMenu(this);
		clickChoice = 0;
		sizeX = xSize;
		sizeY = ySize;
		x = MouseX;
		y = MouseY;
		for(int i = 0; i<args.length; i++){
			lines[i + 1] = args[i];
		}
		menuSize = args.length;
	}
	public  void openMenu(){
		if(Main.Main.getTime() - lastToggle > 4.0f){
			lastToggle = Main.Main.getTime();
			menuOpen = true;
		}
		if(menuOpen){
			closeMenu();
		}
	}
	public  void render(){
		if(menuOpen){
			Mouse.setGrabbed(false);
			Main.Main.lockCamera(true);
			Renderer2D.draw2D();
			choice = getChoice();
			count = 1;			
			//INSET MENU DRAW CODE HERE!		
			menu.bind();
			GL11.glBegin(GL11.GL_QUADS);	
				for(String line:lines){
					if(!(line.equalsIgnoreCase("null"))){
						if(count == menuSize){
						}
						//UPPER LEFT ========================================================================
						if(count == 1){
							if(count == choice){
								GL11.glTexCoord2f(0,Pixel.getPixel(22, 128));
							}else{
								GL11.glTexCoord2f(0,Pixel.getPixel(43, 128));
							}
						}
						else if(count == menuSize){
							if(count == choice){
								GL11.glTexCoord2f(0,Pixel.getPixel(86, 128));
							}else{
								GL11.glTexCoord2f(0,Pixel.getPixel(107, 128));
							}
						}
						else{
							if(count == choice){
								GL11.glTexCoord2f(0, 0);
							}else{
								GL11.glTexCoord2f(0, Pixel.getPixel(64, 128));
							}
						}
						GL11.glVertex2f(x, y + (sizeY * count) - sizeY);
						//UPPER RIGHT ========================================================================
						if(count == 1){
							if(count == choice){
								GL11.glTexCoord2f(1,Pixel.getPixel(22, 128));
							}else{
								GL11.glTexCoord2f(1,Pixel.getPixel(43, 128));
							}
						}
						else if(count == menuSize){
							if(count == choice){
								GL11.glTexCoord2f(1,Pixel.getPixel(86, 128));
							}else{
								GL11.glTexCoord2f(1,Pixel.getPixel(107, 128));
							}
						}
						else{
							if(count == choice){
								GL11.glTexCoord2f(1, 0);
							}else{
								GL11.glTexCoord2f(1, Pixel.getPixel(64, 128));
							}
						}
						GL11.glVertex2f(x + sizeX, y + (sizeY * count) - sizeY);
						//BOTTOM RIGHT ========================================================================
						if(count == 1){
							if(count == choice){
								GL11.glTexCoord2f(1,Pixel.getPixel(42, 128));
							}else{
								GL11.glTexCoord2f(1,Pixel.getPixel(65, 128));
							}
						}
						else if(count == menuSize){
							if(count == choice){
								GL11.glTexCoord2f(1,Pixel.getPixel(106, 128));
							}else{
								GL11.glTexCoord2f(1,1);
							}
						}
						else{
							if(count == choice){
								GL11.glTexCoord2f(1,Pixel.getPixel(21, 128));
							}else{
								GL11.glTexCoord2f(1, Pixel.getPixel(86, 128));
							}
						}
						GL11.glVertex2f(x + sizeX, y + (sizeY * count));
						//BOTTOM LEFT ========================================================================
						if(count == 1){
							if(count == choice){
								GL11.glTexCoord2f(0,Pixel.getPixel(42, 128));
							}else{
								GL11.glTexCoord2f(0,Pixel.getPixel(65, 128));
							}
						}
						else if(count == menuSize){
							if(count == choice){
								GL11.glTexCoord2f(0,Pixel.getPixel(106, 128));
							}else{
								GL11.glTexCoord2f(0, 1);
							}
						}
						else{
							if(count == choice){
								GL11.glTexCoord2f(0,Pixel.getPixel(21, 128));
							}else{
								GL11.glTexCoord2f(0, Pixel.getPixel(86, 128));
							}
						}
						GL11.glVertex2f(x, y + (sizeY * count));
						count++;
					}
				}
			GL11.glEnd();
			GL11.glDisable(GL11.GL_TEXTURE_2D);
			GL11.glDisable(GL11.GL_BLEND); 
			//END INSERT MENU DRAW CODE!
			Renderer2D.reinitiatePreviousDrawState();
			choice = 0;
		}
	}
	private  int getChoice(){
		choice = 0;
		for(String line:lines){
			if(!(line.equalsIgnoreCase("null"))){
				count++;
			}
		}
		if(Mouse.getX() >= x  && Mouse.getX() <= (x + sizeX)){
			if((-(Mouse.getY() - Display.getHeight())) >= y && (-(Mouse.getY() - Display.getHeight())) <= (y + sizeY * count)){
				choice = (int) ((((-(Mouse.getY() - Display.getHeight())) - y) / sizeY) + 1);
			}
		}
		return choice;
	}
	public  void closeMenu(){
		for(int i = 0; i <lines.length; i++){
		 	lines[i] = "null";
		}
	    x = 0;
		y = 0;
		sizeX = 0;
		sizeY = 0;
		choice = 0;
		menuOpen = false;
		count = 1;
		menuSize = 0;
		for(int i = 0; i<lines.length -1; i++){
			lines[i] = "null";
		}
		Main.Main.lockCamera(false);
	}
	public  boolean isOpen() {
		return menuOpen;
	}
	public  int getSelection(){
		choice = 0;
		for(String line:lines){
			if(!(line.equalsIgnoreCase("null"))){
				count++;
			}
		}
		if(Mouse.getX() >= x  && Mouse.getX() <= (x + sizeX)){
			if((-(Mouse.getY() - Display.getHeight())) >= y && (-(Mouse.getY() - Display.getHeight())) <= (y + sizeY * count)){
				choice = (int) ((((-(Mouse.getY() - Display.getHeight())) - y) / sizeY) + 1);
			}
		}
		if(menuOpen){
			clickChoice = choice;
			closeMenu();
		}
		return clickChoice;
	}
}
