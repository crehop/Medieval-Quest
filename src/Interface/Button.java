package Interface;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.newdawn.slick.opengl.Texture;

import renderer.Renderer2D;


public class Button {
	private  Texture button;
	private  int x = 0;
	private  int y = 0;
	private  float sizeX = 100.0f;
	private  float sizeY = 25.0f;
	private  boolean render = false;
	private  int count = 1;
	private  double lastToggle = Main.Main.getTime();
	
	public Button(Texture texture, String name, int X, int Y, int xSize, int ySize){
		if(Main.Main.getTime() - lastToggle > 4.0f){
			lastToggle = Main.Main.getTime();
			sizeX = xSize;
			sizeY = ySize;
			x = X;
			y = Y;
			render = true;
			button = texture;
		}
	}
	public void render(){
		if(render){
			Renderer2D.draw2D();

			count = 1;			
			//INSET MENU DRAW CODE HERE!		
			button.bind();
		    GL11.glTexCoord2f(0,0);
			GL11.glVertex2f(x, y + (sizeY * count) - sizeY);
			//UPPER RIGHT =========================================================================
			GL11.glTexCoord2f(1,0);
			GL11.glVertex2f(x + sizeX, y + (sizeY * count) - sizeY);
			//BOTTOM RIGHT ========================================================================
			GL11.glTexCoord2f(1, 1);
			GL11.glVertex2f(x + sizeX, y + (sizeY * count));
			//BOTTOM LEFT =========================================================================
			GL11.glTexCoord2f(0, 1);
			GL11.glVertex2f(x, y + (sizeY * count));
			while(x + sizeX >Display.getWidth()){
				x--;
				while(y + (sizeY * count) -sizeY > Display.getHeight()){
					y--;
				}
			}
			GL11.glEnd();
			GL11.glDisable(GL11.GL_TEXTURE_2D);
			GL11.glDisable(GL11.GL_BLEND); 
			Renderer2D.reinitiatePreviousDrawState();
		}
	}
	public boolean isRendered(){
		return render;
	}
}
