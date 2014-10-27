package renderer;

import static org.lwjgl.opengl.GL11.*;
import org.newdawn.slick.opengl.Texture;

import entities.Player;

import renderer.TextureHandler;

public class Skybox {
	private static int size = 500;
	public static void renderSkyBox(Player player){
		Texture sides = TextureHandler.getTexture("skybox", "png");
		Texture top = TextureHandler.getTexture("skyboxtop", "png");
		Texture bottom = TextureHandler.getTexture("skyboxbottom", "png");

		glPushMatrix();
			sides.bind();
			glBegin(GL_QUADS);
			glTexCoord2f(1f, 0.0f);
			glVertex3f(-player.getLocation().getX() - size -1, -player.getLocation().getY() - size -1, -player.getLocation().getZ()- size -1);
	        glTexCoord2f(1f, 1f);
	        glVertex3f(-player.getLocation().getX() - size -1, -player.getLocation().getY() + size + 1, -player.getLocation().getZ() - size -1);
	        glTexCoord2f(0.0f, 1f);
	        glVertex3f(-player.getLocation().getX() + size + 1, -player.getLocation().getY() + size + 1, -player.getLocation().getZ() - size -1);
	        glTexCoord2f(0.0f, 0.0f);
	        glVertex3f(-player.getLocation().getX() + size + 1, -player.getLocation().getY() - size -1, -player.getLocation().getZ() - size -1);
	        glEnd();
		glPopMatrix();

	    
	    // Back Face
	    glPushMatrix();
	    	sides.bind();
	    	glBegin(GL_QUADS);
	        glTexCoord2f(0.0f, 0.0f);
	        glVertex3f(-player.getLocation().getX() - size -1, -player.getLocation().getY() - size -1, -player.getLocation().getZ() + size + 1);
	        glTexCoord2f(1f, 0.0f);
	        glVertex3f(-player.getLocation().getX() + size + 1, -player.getLocation().getY() - size -1, -player.getLocation().getZ() + size + 1);
	        glTexCoord2f(1f, 1f);
	        glVertex3f(-player.getLocation().getX() + size + 1, -player.getLocation().getY() + size + 1, -player.getLocation().getZ() + size + 1);
	        glTexCoord2f(0.0f, 1f);
	        glVertex3f(-player.getLocation().getX() - size -1, -player.getLocation().getY() + size + 1, -player.getLocation().getZ() + size + 1);
	        glEnd();
		glPopMatrix();

	
	    // Top Face
		glPushMatrix();
			bottom.bind();
			glBegin(GL_QUADS);
	        glTexCoord2f(1f, 1f);
	        glVertex3f(-player.getLocation().getX() - size -1, -player.getLocation().getY() - size -1, -player.getLocation().getZ() - size -1);
	        glTexCoord2f(0.0f, 1f);
	        glVertex3f(-player.getLocation().getX() + size + 1, -player.getLocation().getY() - size -1, -player.getLocation().getZ() - size -1);
	        glTexCoord2f(0.0f, 0.0f);
	        glVertex3f(-player.getLocation().getX() + size + 1, -player.getLocation().getY() - size -1, -player.getLocation().getZ() + size + 1);
	        glTexCoord2f(1f, 0.0f);
	        glVertex3f(-player.getLocation().getX() - size -1, -player.getLocation().getY() - size -1, -player.getLocation().getZ() + size + 1);
	        glEnd();
		glPopMatrix();

	    // Bottom Face
		glPushMatrix();
			top.bind();
			glBegin(GL_QUADS);
	        glTexCoord2f(1f, 0f);
	        glVertex3f(-player.getLocation().getX() - size -1, -player.getLocation().getY() + size + 1, -player.getLocation().getZ() - size -1);
	        glTexCoord2f(1f, 1f);
	        glVertex3f(-player.getLocation().getX() - size -1, -player.getLocation().getY() + size + 1, -player.getLocation().getZ() + size + 1);
	        glTexCoord2f(0f, 1f);
	        glVertex3f(-player.getLocation().getX() + size + 1, -player.getLocation().getY() + size + 1, -player.getLocation().getZ() + size + 1);
	        glTexCoord2f(0f, 0f);
	        glVertex3f(-player.getLocation().getX() + size + 1, -player.getLocation().getY() + size + 1, -player.getLocation().getZ() - size -1);
	        glEnd();
		glPopMatrix();
		
	    // Right face
		glPushMatrix();
			sides.bind();
			glBegin(GL_QUADS);
	        glTexCoord2f(0.0f, 0.0f);
	        glVertex3f(-player.getLocation().getX() - size -1, -player.getLocation().getY() - size -1, -player.getLocation().getZ() - size -1);
	        glTexCoord2f(1f, 0.0f);
	        glVertex3f(-player.getLocation().getX() - size -1, -player.getLocation().getY() - size -1, -player.getLocation().getZ() + size + 1);
	        glTexCoord2f(1f, 1f);
	        glVertex3f(-player.getLocation().getX() - size -1, -player.getLocation().getY() + size + 1, -player.getLocation().getZ() + size + 1);
	        glTexCoord2f(0.0f, 1f);
	        glVertex3f(-player.getLocation().getX() - size -1, -player.getLocation().getY() + size + 1, -player.getLocation().getZ() - size -1);
	        glEnd();
		glPopMatrix();

	    // Left Face
		glPushMatrix();
			sides.bind();
			glBegin(GL_QUADS);
	        glTexCoord2f(1f, 0.0f);
	        glVertex3f(-player.getLocation().getX() + size + 1, -player.getLocation().getY() - size -1, -player.getLocation().getZ() - size -1);
	        glTexCoord2f(1f, 1f);
	        glVertex3f(-player.getLocation().getX() + size + 1, -player.getLocation().getY() + size + 1, -player.getLocation().getZ() - size -1);
	        glTexCoord2f(0.0f, 1f);
	        glVertex3f(-player.getLocation().getX() + size + 1, -player.getLocation().getY() + size + 1, -player.getLocation().getZ() + size + 1);
	        glTexCoord2f(0.0f, 0.0f);
	        glVertex3f(-player.getLocation().getX() + size + 1, -player.getLocation().getY() - size -1, -player.getLocation().getZ() + size + 1);
	        glEnd();
		glPopMatrix();
	}
}
