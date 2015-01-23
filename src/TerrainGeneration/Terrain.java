package TerrainGeneration;


import java.util.HashMap;

import org.newdawn.slick.opengl.Texture;

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
