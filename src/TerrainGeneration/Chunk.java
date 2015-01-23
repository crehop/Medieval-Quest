package TerrainGeneration;

import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glTexCoord2f;
import static org.lwjgl.opengl.GL11.glVertex3f;

import server.Location;

public class Chunk {
	private Location location;
	public Chunk(int x,int y,int z){
		this.location = new Location(x,y,z);
	}
	public void render(){

	        glTexCoord2f(1f, 0f);
	        glVertex3f(this.getLocation().getX() - 50, this.getLocation().getY(), this.getLocation().getZ() - 50);
	        glTexCoord2f(1f, 1f);
	        glVertex3f(this.getLocation().getX() - 50, this.getLocation().getY(), this.getLocation().getZ() + 50);
	        glTexCoord2f(0f, 1f);
	        glVertex3f(this.getLocation().getX() + 50, this.getLocation().getY(), this.getLocation().getZ() + 50);
	        glTexCoord2f(0f, 0f);
	        glVertex3f(this.getLocation().getX() + 50, this.getLocation().getY(), this.getLocation().getZ() - 50);
	        glEnd();
	}
	public Location getLocation(){
		return location;
	}
}
