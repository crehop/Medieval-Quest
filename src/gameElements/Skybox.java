package gameElements;

import static org.lwjgl.opengl.GL11.*;
import org.newdawn.slick.opengl.Texture;

import entities.Player;

import utils.TextureHandler;

public class Skybox {
	private static int size = 500;
	public static void renderSkyBox(Player player){
		Texture sides = TextureHandler.getTexture("skybox");
		Texture top = TextureHandler.getTexture("skyboxtop");
		Texture bottom = TextureHandler.getTexture("skyboxbottom");

		glPushMatrix();
			sides.bind();
			glBegin(GL_QUADS);
				glTexCoord2f(1f, 0.0f);
				glVertex3f(player.getLocation().getX() - size -1, player.getLocation().getY() - size -1, player.getLocation().getZ()- size -1);
				glTexCoord2f(1f, 1f);
				glVertex3f(player.getLocation().getX() - size -1, player.getLocation().getY() + size + 1, player.getLocation().getZ() - size -1);
				glTexCoord2f(0.0f, 1f);
				glVertex3f(player.getLocation().getX() + size + 1, player.getLocation().getY() + size + 1, player.getLocation().getZ() - size -1);
				glTexCoord2f(0.0f, 0.0f);
				glVertex3f(player.getLocation().getX() + size + 1, player.getLocation().getY() - size -1, player.getLocation().getZ() - size -1);
	        glEnd();
	    
	    // Back Face
	    	sides.bind();
			glBegin(GL_QUADS);
	        glTexCoord2f(0.0f, 0.0f);
	        glVertex3f(player.getLocation().getX() - size -1, player.getLocation().getY() - size -1, player.getLocation().getZ() + size + 1);
	        glTexCoord2f(1f, 0.0f);
	        glVertex3f(player.getLocation().getX() + size + 1, player.getLocation().getY() - size -1, player.getLocation().getZ() + size + 1);
	        glTexCoord2f(1f, 1f);
	        glVertex3f(player.getLocation().getX() + size + 1, player.getLocation().getY() + size + 1, player.getLocation().getZ() + size + 1);
	        glTexCoord2f(0.0f, 1f);
	        glVertex3f(player.getLocation().getX() - size -1, player.getLocation().getY() + size + 1, player.getLocation().getZ() + size + 1);
	        glEnd();

	
	    // Top Face
			bottom.bind();
			glBegin(GL_QUADS);
				glTexCoord2f(1f, 1f);
				glVertex3f(player.getLocation().getX() - size -1, player.getLocation().getY() - size -1, player.getLocation().getZ() - size -1);
				glTexCoord2f(0.0f, 1f);
				glVertex3f(player.getLocation().getX() + size + 1, player.getLocation().getY() - size -1, player.getLocation().getZ() - size -1);
				glTexCoord2f(0.0f, 0.0f);
				glVertex3f(player.getLocation().getX() + size + 1, player.getLocation().getY() - size -1, player.getLocation().getZ() + size + 1);
				glTexCoord2f(1f, 0.0f);
				glVertex3f(player.getLocation().getX() - size -1, player.getLocation().getY() - size -1, player.getLocation().getZ() + size + 1);
	        glEnd();

	    // Bottom Face
			top.bind();
			glBegin(GL_QUADS);
				glTexCoord2f(1f, 0f);
				glVertex3f(player.getLocation().getX() - size -1, player.getLocation().getY() + size + 1, player.getLocation().getZ() - size -1);
				glTexCoord2f(1f, 1f);
				glVertex3f(player.getLocation().getX() - size -1, player.getLocation().getY() + size + 1, player.getLocation().getZ() + size + 1);
				glTexCoord2f(0f, 1f);
				glVertex3f(player.getLocation().getX() + size + 1, player.getLocation().getY() + size + 1, player.getLocation().getZ() + size + 1);
				glTexCoord2f(0f, 0f);
				glVertex3f(player.getLocation().getX() + size + 1, player.getLocation().getY() + size + 1, player.getLocation().getZ() - size -1);
				glEnd();

	    // Right face
			sides.bind();
			glBegin(GL_QUADS);
				glTexCoord2f(0.0f, 0.0f);
				glVertex3f(player.getLocation().getX() - size -1, player.getLocation().getY() - size -1, player.getLocation().getZ() - size -1);
				glTexCoord2f(1f, 0.0f);
				glVertex3f(player.getLocation().getX() - size -1, player.getLocation().getY() - size -1, player.getLocation().getZ() + size + 1);
				glTexCoord2f(1f, 1f);
				glVertex3f(player.getLocation().getX() - size -1, player.getLocation().getY() + size + 1, player.getLocation().getZ() + size + 1);
	        	glTexCoord2f(0.0f, 1f);
	        	glVertex3f(player.getLocation().getX() - size -1, player.getLocation().getY() + size + 1, player.getLocation().getZ() - size -1);
	        glEnd();

	    // Left Face
			sides.bind();
			glBegin(GL_QUADS);
	        	glTexCoord2f(1f, 0.0f);
	        	glVertex3f(player.getLocation().getX() + size + 1, player.getLocation().getY() - size -1, player.getLocation().getZ() - size -1);
	        	glTexCoord2f(1f, 1f);
	        	glVertex3f(player.getLocation().getX() + size + 1, player.getLocation().getY() + size + 1, player.getLocation().getZ() - size -1);
	        	glTexCoord2f(0.0f, 1f);
	        	glVertex3f(player.getLocation().getX() + size + 1, player.getLocation().getY() + size + 1, player.getLocation().getZ() + size + 1);
	        	glTexCoord2f(0.0f, 0.0f);
	        	glVertex3f(player.getLocation().getX() + size + 1, player.getLocation().getY() - size -1, player.getLocation().getZ() + size + 1);
	        glEnd();
		glPopMatrix();
	}
}
