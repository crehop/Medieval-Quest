package TerrainGeneration;

import java.util.HashMap;

import entities.Player;

public class Terrain {
	public HashMap<String,Chunk> world = new HashMap<String,Chunk>();
	public int chunks = 0;
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
		for(int x = -200; x <=200; x+=20){
			Chunk chunk = new Chunk(x,0,0);
			String key = "" + ((int)chunk.getLocation().getX() + "," + (int)chunk.getLocation().getZ());
			world.put(key,chunk);
			chunks++;
			for(int z = -200; z <=200; z+=20){
				Chunk chunk2 = new Chunk(x,0,z);
				String key2 = "" + ((int)chunk2.getLocation().getX() + "," + (int)chunk2.getLocation().getZ());
				world.put(key2,chunk2);
				chunks++;
			}
		}
	}
	public void renderChunks(Player player){
		int x = (int) player.getLocation().getX() - ((int) player.getLocation().getX()%20);
		int z = (int) player.getLocation().getZ() - ((int) player.getLocation().getZ()%20);
		String key = "" + x + "," + z;
		if(world.get(key) != null)world.get(key).render();
		for(int X = x-200; X < x+200; X+=20){
			key = "" + X + "," + z;
			if(world.get(key) != null)world.get(key).render();
			for(int Z = z-200; Z < z+200; Z+=20){
				key = "" + X + "," + Z;
				if(world.get(key) != null)world.get(key).render();
			}
		}
		world.get("0,0").render();
	}
}
