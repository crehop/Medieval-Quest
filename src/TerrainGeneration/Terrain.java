package TerrainGeneration;

import static org.lwjgl.opengl.GL11.GL_QUADS;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glPopMatrix;
import static org.lwjgl.opengl.GL11.glPushMatrix;

import java.util.HashMap;

import org.newdawn.slick.opengl.Texture;

import entities.Player;

public class Terrain {
	public HashMap<String,Chunk> world = new HashMap<String,Chunk>();
	public int chunks = 0;
	private String key = "";
	private String key2 = "";
	private Chunk chunk;
	private Chunk chunk2;
	private Texture terrain = utils.TextureHandler.getTexture("grass");
	public Terrain() {
		initiateTerrain();
	}
	public Terrain(boolean loadOld){
		if(loadOld){
			//TODO Load old terrain
		}
		initiateTerrain();
	}

	private void initiateTerrain() {
		for(int x = -20000; x <=20000; x+=200){
			chunk = new Chunk(x,0,0);
			key = "" + ((int)chunk.getLocation().getX() + "," + (int)chunk.getLocation().getZ());
			world.put(key,chunk);
			chunks++;
			for(int z = -20000; z <=20000; z+=200){
				chunk2 = new Chunk(x,0,z);
				key2 = "" + ((int)chunk2.getLocation().getX() + "," + (int)chunk2.getLocation().getZ());
				world.put(key2,chunk2);
				chunks++;
			}
		}
	}
	public void renderChunks(Player player){
		int x = ((int) player.getLocation().getX() - (int) player.getLocation().getX()%200);
		int z = ((int) player.getLocation().getZ() - (int) player.getLocation().getZ()%200);
		key = "" + x + "," + z;
		glPushMatrix();
		terrain.bind();
		glBegin(GL_QUADS);
		if(world.get(key) != null)world.get(key).render();
		for(int X = x-2000; X < x+2000; X+=20){
			key = "" + X + "," + z;
			if(world.get(key) != null)world.get(key).render();
			for(int Z = z-2000; Z < z+2000; Z+=20){
				key = "" + X + "," + Z;
				if(world.get(key) != null)world.get(key).render();
			}
		}
		glPopMatrix();
		}
	
	

}
