package TerrainGeneration;

import static org.lwjgl.opengl.GL11.GL_QUADS;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glPopMatrix;
import static org.lwjgl.opengl.GL11.glPushMatrix;

import java.util.HashMap;

import org.newdawn.slick.opengl.Texture;

import entities.Player;

public class Terrain {
	public int chunks = 0;
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
	}
}
