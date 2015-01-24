package gameElements;

import static org.lwjgl.opengl.GL11.*;
import org.lwjgl.opengl.GL11;
import org.newdawn.slick.opengl.Texture;

import Information.Console;
import entities.Player;
import utils.TextureHandler;

public class Skybox {
	private static float size = 256;
	private static Texture night = TextureHandler.getTexture("skybox");
	private static float X = 0.0f;
	private static float Y = 0.0f;
	private static float Z = 0.0f;
	private static boolean init = false;
	private static float x = 0;
	private static float y = 0;
	
	public static void renderSkyBox(Player player){
		X = player.getLocation().getX();
		Y = player.getLocation().getY();
		Z = player.getLocation().getZ();
		
		glPushMatrix();
			if(!(init)){
				GL11.glRotatef(90.0f, 0, 0, 0);
				init = true;
			}
			GL11.glPushAttrib(GL11.GL_DEPTH_TEST);
			GL11.glDisable(GL11.GL_DEPTH_TEST);
			GL11.glEnable(GL11.GL_CLAMP);
			//Front Face
			night.bind();
			Console.setLine5("X=" + getX() + " Y=" + getY());
			glBegin(GL_QUADS);
				glTexCoord2f(0.25f + getX(),0.333333f + getY()); //1
				glVertex3f(X - size , Y + size , Z - size );
				glTexCoord2f(0.5f + getX(),0.333333f + getY()); //2
				glVertex3f(X + size , Y + size , Z - size );
				glTexCoord2f(0.5f + getX(),0.666666f + getY()); //3
				glVertex3f(X + size , Y - size , Z - size );
				glTexCoord2f(0.25f + getX(),0.666666f + getY());  //4
				glVertex3f(X - size , Y - size , Z - size );
			
	        // Right face
				glTexCoord2f(0.5f + getX(),0.333333f + getY()); //1
				glVertex3f(X + size , Y + size , Z - size );
				glTexCoord2f(0.75f + getX(),0.333333f + getY()); //2
				glVertex3f(X + size , Y + size , Z + size );
				glTexCoord2f(0.75f + getX(),0.666666f + getY()); //3
				glVertex3f(X + size , Y - size , Z + size );
				glTexCoord2f(0.5f + getX(),0.666666f + getY());  //4
				glVertex3f(X + size , Y - size , Z - size );
			// Top Face
				glTexCoord2f(0.25f + getX(),0f + getY()); //1
				glVertex3f(X - size , Y + size , Z + size );
				glTexCoord2f(0.5f + getX(),0f + getY()); //2
				glVertex3f(X + size , Y + size , Z + size );
				glTexCoord2f(0.5f + getX(),0.333333f + getY()); //3
				glVertex3f(X + size , Y + size , Z - size );
				glTexCoord2f(0.25f + getX(),0.333333f + getY()); //4
				glVertex3f(X - size , Y + size , Z - size );
			// Bottom Face
				glTexCoord2f(0.25f + getX(),0.666666f + getY()); //1
				glVertex3f(X - size , Y - size , Z - size );
				glTexCoord2f(0.5f + getX(),0.666666f  + getY()); //2
				glVertex3f(X + size , Y - size , Z - size );
				glTexCoord2f(0.5f + getX(),1f  + getY()); //3
				glVertex3f(X + size , Y - size , Z + size );
				glTexCoord2f(0.25f + getX(), 1f + getY()); //4
				glVertex3f(X - size , Y - size , Z + size );
			// Back Face
				glTexCoord2f(0.75f + getX(),0.333333f + getY()); //1
				glVertex3f(X + size , Y + size , Z + size );
				glTexCoord2f(1.0f + getX(),0.333333f + getY()); //2
				glVertex3f(X - size , Y + size , Z + size );
				glTexCoord2f(1.0f + getX(),0.666666f + getY()); //3
				glVertex3f(X - size , Y - size , Z + size );
				glTexCoord2f(0.75f + getX(),0.666666f + getY());  //4
				glVertex3f(X + size , Y - size , Z + size );
		    // Left Face
				glTexCoord2f(0f + getX(),0.333333f + getY()); //1
				glVertex3f(X - size , Y + size , Z + size );
				glTexCoord2f(0.25f + getX(),0.333333f + getY()); //2
				glVertex3f(X - size , Y + size , Z - size );
				glTexCoord2f(0.25f + getX(),0.666666f + getY()); //3
				glVertex3f(X - size , Y - size , Z - size );
				glTexCoord2f(0f + getX(),0.666666f + getY());  //4
				glVertex3f(X - size , Y - size , Z + size );
			glEnd();
			GL11.glPopAttrib();
		glPopMatrix();
	}

	public static float getX() {
		return x;
	}

	public static void setX(float X) {
		x = X;
	}

	public static float getY() {
		return y;
	}

	public static void setY(float Y) {
		y = Y;
	}
}
