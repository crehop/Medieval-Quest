package TerrainGeneration;

import java.util.HashMap;
import java.util.Iterator;

import entities.Player;

import server.Location;

public class Terrain {
	HashMap<Integer,Chunk> world = new HashMap<Integer,Chunk>();
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
		for(int x = 0; x <=1000; x+=20){
			Chunk chunk = new Chunk(x,0,0);
			int key = Integer.parseInt((int)chunk.getLocation().getX() + "" + (int)chunk.getLocation().getY() + "" + (int)chunk.getLocation().getZ());
			world.put(key,chunk);
			chunks++;
			for(int y = 0; y <=1000; y+=20){
				Chunk chunk2 = new Chunk(x,0,y);
				int key2 = Integer.parseInt((int)chunk2.getLocation().getX() + "" + (int)chunk2.getLocation().getY() + "" + (int)chunk2.getLocation().getZ());
				world.put(key2,chunk2);
				chunks++;
			}
		}
	}
	public void renderChunks(Player player){
		for(int x = (int) player.getLocation().getX(); x < (int)player.getLocation().getX() + 100; x++											){
			if(x/20 == 0){
				int key = Integer.parseInt(x + "0" + "0");
				System.out.println("Attempted render = " + key);
				if(world.get(key) != null)	world.get(key).render();
			}
			for(int y = (int) player.getLocation().getY(); y < (int)player.getLocation().getY() + 100; y++){
				if(y/20 == 0){
					int key2 = Integer.parseInt(x + "0" + y);
					System.out.println("Attempted render = " + key2);
					if(world.get(key2) != null)	world.get(key2).render();
				}
			}
		}
		world.get(000).render();
	}
}
