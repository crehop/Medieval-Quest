package gameElements;

import static org.lwjgl.opengl.GL11.*;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.opengl.Texture;

import Information.Console;

import entities.Player;

import utils.TextureHandler;

public class Skybox {
	private static float size = 100;
	private static Texture side1 = TextureHandler.getTexture("skybox1");
	private static Texture side2 = TextureHandler.getTexture("skybox2");
	private static Texture side3 = TextureHandler.getTexture("skybox3");
	private static Texture side4 = TextureHandler.getTexture("skybox4");
	private static Texture top = TextureHandler.getTexture("skyboxtop");
	private static Texture bottom = TextureHandler.getTexture("skyboxbottom");
	private static float X = 0.0f;
	private static float Y = 0.0f;
	private static float Z = 0.0f;
	private static float var = 2.0f;

	public static void renderSkyBox(Player player){
		X = player.getLocation().getX();
		Y = player.getLocation().getY();
		Z = player.getLocation().getZ();

		glPushMatrix();

			Console.setLine6(X + " , " +  Y + " , " + Z);
			GL11.glEnable(GL11.GL_TEXTURE_2D);
			
			//Front Face
			side4.bind();
			glBegin(GL_QUADS);
				glTexCoord2f(0f, 0f); //1
				glVertex3f(X - size - var, Y + size + var, Z - size - var);
				glTexCoord2f(0f, 1f); //2
				glVertex3f(X + size + var, Y + size + var, Z - size - var);
				glTexCoord2f(1f, 1f); //3
				glVertex3f(X + size + var, Y - size - var, Z - size - var);
				glTexCoord2f(1, 0f);  //4
				glVertex3f(X - size - var, Y - size - var, Z - size - var);
			glEnd(); 
			
	        // Right face
			side3.bind();
			glBegin(GL_QUADS);
				glTexCoord2f(0f, 0f); //1
				glVertex3f(X + size + var, Y + size + var, Z - size - var);
				glTexCoord2f(0f, 1f); //2
				glVertex3f(X + size + var, Y + size + var, Z + size + var);
				glTexCoord2f(1f, 1f); //3
				glVertex3f(X + size + var, Y - size - var, Z + size + var);
				glTexCoord2f(1, 0f);  //4
				glVertex3f(X + size + var, Y - size - var, Z - size - var);
			glEnd();
			
			// Top Face
			top.bind();
			glBegin(GL_QUADS);
				glTexCoord2f(0f, 0f); //1
				glVertex3f(X - size - var, Y + size + var, Z + size + var);
				glTexCoord2f(0f, 1f); //2
				glVertex3f(X + size + var, Y + size + var, Z + size + var);
				glTexCoord2f(1f, 1f); //3
				glVertex3f(X + size + var, Y + size + var, Z - size - var);
				glTexCoord2f(1, 0f);  //4
				glVertex3f(X - size - var, Y + size + var, Z - size - var);
			glEnd();

			// Bottom Face
			bottom.bind();
			glBegin(GL_QUADS);
			glTexCoord2f(0f, 0f); //1
				glVertex3f(X - size - var, Y - size - var, Z - size - var);
				glTexCoord2f(0f, 1f); //2
				glVertex3f(X + size + var, Y - size - var, Z - size - var);
				glTexCoord2f(1f, 1f); //3
				glVertex3f(X + size + var, Y - size - var, Z + size + var);
				glTexCoord2f(1, 0f);  //4
				glVertex3f(X - size - var, Y - size - var, Z + size + var);
			glEnd();
			
			
	     
			// Back Face
	    	side2.bind();
			glBegin(GL_QUADS);
				glTexCoord2f(0f, 0f); //1
				glVertex3f(X + size + var, Y + size + var, Z + size + var);
				glTexCoord2f(0f, 1f); //2
				glVertex3f(X - size - var, Y + size + var, Z + size + var);
				glTexCoord2f(1f, 1f); //3
				glVertex3f(X - size - var, Y - size - var, Z + size + var);
				glTexCoord2f(1, 0f);  //4
				glVertex3f(X + size + var, Y - size - var, Z + size + var);
	        glEnd();

		    // Left Face
			side1.bind();
			glBegin(GL_QUADS);
				glTexCoord2f(0f, 0f); //1
				glVertex3f(X - size - var, Y + size + var, Z + size + var);
				glTexCoord2f(0f, 1f); //2
				glVertex3f(X - size - var, Y + size + var, Z - size - var);
				glTexCoord2f(1f, 1f); //3
				glVertex3f(X - size - var, Y - size - var, Z - size - var);
				glTexCoord2f(1, 0f);  //4
				glVertex3f(X - size - var, Y - size - var, Z + size + var);
			glEnd();

	

			GL11.glDisable(GL11.GL_TEXTURE_2D);
		glPopMatrix();
	}
}
