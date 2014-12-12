package TerrainGeneration;

import static org.lwjgl.opengl.GL11.GL_QUADS;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glPopMatrix;
import static org.lwjgl.opengl.GL11.glPushMatrix;
import static org.lwjgl.opengl.GL11.glTexCoord2f;
import static org.lwjgl.opengl.GL11.glVertex3f;

import org.newdawn.slick.opengl.Texture;

import server.Location;

public class Chunk {
	Texture terrain = utils.TextureHandler.getTexture("grass");
	Location location;
	public Chunk(int x,int y,int z){
		this.location = new Location(x,y,z);
	}
	public Chunk(int x,int y,int z,Texture texture){
		this.location = new Location(x,y,z);
		this.terrain = texture;
	}
	public void render(){
		glPushMatrix();
			terrain.bind();
			glBegin(GL_QUADS);
	        glTexCoord2f(1f, 0f);
	        glVertex3f(this.getLocation().getX() - 10, this.getLocation().getY(), this.getLocation().getZ() - 10);
	        glTexCoord2f(1f, 1f);
	        glVertex3f(this.getLocation().getX() - 10, this.getLocation().getY(), this.getLocation().getZ() + 10);
	        glTexCoord2f(0f, 1f);
	        glVertex3f(this.getLocation().getX() + 10, this.getLocation().getY(), this.getLocation().getZ() + 10);
	        glTexCoord2f(0f, 0f);
	        glVertex3f(this.getLocation().getX() + 10, this.getLocation().getY(), this.getLocation().getZ() - 10);
	        glEnd();
		glPopMatrix();
	}
	public Location getLocation(){
		return location;
	}
}
