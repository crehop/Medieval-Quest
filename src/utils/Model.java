package utils;

import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glPopMatrix;
import static org.lwjgl.opengl.GL11.glPushMatrix;

import java.util.ArrayList;
import java.util.List;

import loops.GameLoop;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL11.*;
import org.lwjgl.util.vector.Vector3f;

import entities.Player;

import physics.PhysicsEngine;

import TerrainGeneration.Chunk;

import server.Location;

@SuppressWarnings("unused")
public class Model {
	boolean render = true;
	public Location location;
	public List<Vector3f> vertices = new ArrayList<Vector3f>();
	public List<Vector3f> normals = new ArrayList<Vector3f>();
	public List<Face> faces = new ArrayList<Face>();
	public int renderloop = 0;
	private float xmax = 0.0f;
	private float xmin = 0.0f;
	private float ymin = 0.0f;
	private float ymax = 0.0f;
	private float zmin = 0.0f;
	private float zmax = 0.0f;
	private Chunk currentChunk;
	private final int ID;
	private String name = " NULL ";
	private ArrayList<Float> bounding = new ArrayList<Float>();
	private boolean collidable = true;
	private boolean movable = false;
	
	public Model(float x,float y,float z,String name, boolean movable, boolean collidable){
		this.ID = entities.ID.getID();
		this.location = new Location(x,y,z);
		this.movable = movable;
		this.collidable = collidable;
	}
	public void renderModel(){
		if(render){
			glPushMatrix();
			 	GL11.glBegin(GL11.GL_TRIANGLES);
			 	for(Face face:faces){
			 		float xmax2 = -10000000000.0f;
			 		float xmin2 = 10000000000.0f;
			 		float ymin2 = 10000000000.0f;
			 		float ymax2 = -10000000000.0f;
			 		float zmin2 = 10000000000.0f;
			 		float zmax2 = -10000000000.0f;
			 		renderloop++;
			 		Vector3f n1 = this.normals.get((int)face.normal.x - 1);
			 		GL11.glNormal3f(n1.x + this.location.getX(), n1.y + this.location.getY(), n1.z + this.location.getZ());
			 		Vector3f v1 = this.vertices.get((int)face.vertex.x - 1);
			 		GL11.glVertex3f(v1.x + this.location.getX(), v1.y + this.location.getY(), v1.z + this.location.getZ());
			 		Vector3f n2 = this.normals.get((int)face.normal.y - 1);
			 		GL11.glNormal3f(n2.x + this.location.getX(), n2.y + this.location.getY(), n2.z + this.location.getZ());
			 		Vector3f v2 = this.vertices.get((int)face.vertex.y - 1);
			 		GL11.glVertex3f(v2.x + this.location.getX(), v2.y + this.location.getY(), v2.z + this.location.getZ());
			 		Vector3f n3 = this.normals.get((int)face.normal.z - 1);
			 		GL11.glNormal3f(n3.x + this.location.getX(), n3.y + this.location.getY(), n3.z + this.location.getZ());
			 		Vector3f v3 = this.vertices.get((int)face.vertex.z - 1);
			 		GL11.glVertex3f(v3.x + this.location.getX(), v3.y + this.location.getY(), v3.z + this.location.getZ());
			 		if(n1.x + this.location.getX() > xmax2)xmax2 = (n1.x + this.location.getX()) ;
			 		if(n1.x + this.location.getX() < xmin2)xmin2 = (n1.x + this.location.getX()) ;
			 		if(n2.x + this.location.getX() > xmax2)xmax2 = (n2.x + this.location.getX()) ;
			 		if(n2.x + this.location.getX() < xmin2)xmin2 = (n2.x + this.location.getX()) ;
			 		if(n3.x + this.location.getX() > xmax2)xmax2 = (n3.x + this.location.getX()) ;
			 		if(n3.x + this.location.getX() < xmin2)xmin2 = (n3.x + this.location.getX()) ;

			 		if(n1.y + this.location.getY() > ymax2)ymax2 = (n1.y + this.location.getY());
			 		if(n1.y + this.location.getY() < ymin2)ymin2 = (n1.y + this.location.getY());
			 		if(n2.y + this.location.getY() > ymax2)ymax2 = (n2.y + this.location.getY());
			 		if(n2.y + this.location.getY() < ymin2)ymin2 = (n2.y + this.location.getY());
			 		if(n3.y + this.location.getY() > ymax2)ymax2 = (n3.y + this.location.getY());
			 		if(n3.y + this.location.getY() < ymin2)ymin2 = (n3.y + this.location.getY());
			 		
			 		if(n1.z + this.location.getZ() > zmax2)zmax2 = (n1.z + this.location.getZ());
			 		if(n1.z + this.location.getZ() < zmin2)zmin2 = (n1.z + this.location.getZ());
			 		if(n2.z + this.location.getZ() > zmax2)zmax2 = (n2.z + this.location.getZ());
			 		if(n2.z + this.location.getZ() < zmin2)zmin2 = (n2.z + this.location.getZ());
			 		if(n3.z + this.location.getZ() > zmax2)zmax2 = (n3.z + this.location.getZ());
			 		if(n3.z + this.location.getZ() < zmin2)zmin2 = (n3.z + this.location.getZ());
			 		
			 		if(v1.x + this.location.getX() > xmax2)xmax2 = (v1.x + this.location.getX()) ;
			 		if(v1.x + this.location.getX() < xmin2)xmin2 = (v1.x + this.location.getX()) ;
			 		if(v2.x + this.location.getX() > xmax2)xmax2 = (v2.x + this.location.getX()) ;
			 		if(v2.x + this.location.getX() < xmin2)xmin2 = (v2.x + this.location.getX()) ;
			 		if(v3.x + this.location.getX() > xmax2)xmax2 = (v3.x + this.location.getX()) ;
			 		if(v3.x + this.location.getX() < xmin2)xmin2 = (v3.x + this.location.getX()) ;

			 		if(v1.y + this.location.getY() > ymax2)ymax2 = (v1.y + this.location.getY());
			 		if(v1.y + this.location.getY() < ymin2)ymin2 = (v1.y + this.location.getY());
			 		if(v2.y + this.location.getY() > ymax2)ymax2 = (v2.y + this.location.getY());
			 		if(v2.y + this.location.getY() < ymin2)ymin2 = (v2.y + this.location.getY());
			 		if(v3.y + this.location.getY() > ymax2)ymax2 = (v3.y + this.location.getY());
			 		if(v3.y + this.location.getY() < ymin2)ymin2 = (v3.y + this.location.getY());
			 		
			 		if(v1.z + this.location.getZ() > zmax2)zmax2 = (v1.z + this.location.getZ());
			 		if(v1.z + this.location.getZ() < zmin2)zmin2 = (v1.z + this.location.getZ());
			 		if(v2.z + this.location.getZ() > zmax2)zmax2 = (v2.z + this.location.getZ());
			 		if(v2.z + this.location.getZ() < zmin2)zmin2 = (v2.z + this.location.getZ());
			 		if(v3.z + this.location.getZ() > zmax2)zmax2 = (v3.z + this.location.getZ());
			 		if(v3.z + this.location.getZ() < zmin2)zmin2 = (v3.z + this.location.getZ());
			 		xmin=xmin2;
			 		xmax=xmax2;
			 		ymin=ymin2;
			 		ymax=ymax2;
			 		zmin=zmin2;
			 		zmax=zmax2;
			 		bounding.clear();
					bounding.add(xmax);
					bounding.add(xmin);
					bounding.add(ymax);
					bounding.add(ymin);
					bounding.add(zmax);
					bounding.add(zmin);
			 	}
		        glEnd();
			glPopMatrix();
		}
	}
	public Location getLocation(){
		return location;
	}
	public void setLocation(Location location){
		if(this.isMovable() & physics.PhysicsEngine.checkForCollision(this)){
			this.location.setX(location.getX());
			this.location.setY(location.getY());
			this.location.setZ(location.getZ());
		}else{
			this.location.setX(location.getX());
			this.location.setY(location.getY());
			this.location.setZ(location.getZ());
		}
	}
	public void setLocation(float x, float y, float z){
			Main.Console.setLine5("[XMIN = " + xmin + " YMIN = " + ymin + " ZMIN = " + zmin + "]");
			Main.Console.setLine6("[XMAX= " + xmax + " YMAX = " + ymax + " ZMAX = " + zmax + "]" + "CHUNK Y = " + this.getChunk().getLocation().getY());
			float currentX = this.location.getX();
			float currentY = this.location.getY();
			float currentZ = this.location.getZ();
			this.location.setX(x);
			this.location.setY(y);
			this.location.setZ(z);
		if(this.isMovable() && physics.PhysicsEngine.checkForCollision(this)){
			
		}else{
			this.location.setX(currentX);
			this.location.setY(currentY);
			this.location.setZ(currentZ);
		}
	}
	public ArrayList<Float> getBoundingBox(){
		if(bounding.size() != 6){
			bounding.clear();
			bounding.add(xmax);
			bounding.add(xmin);
			bounding.add(ymax);
			bounding.add(ymin);
			bounding.add(zmax);
			bounding.add(zmin);
		}
		return bounding;
	}
	/**
	 * The Chunk in which the model currently resides.
	 * @return
	 */
	public Chunk getChunk(){
		int x = (int) this.getLocation().getX() - ((int) this.getLocation().getX()%20);
		int z = (int) this.getLocation().getZ() - ((int) this.getLocation().getZ()%20);
		Chunk chunk = GameLoop.terrain.world.get("0,0");
		String key = "" + x + "," + z;
		if(GameLoop.terrain.world.get(key) != null)chunk = GameLoop.terrain.world.get(key);
		return chunk;
		
	}
	public String getName() {
		if(name == null){
			name = " NULL ";
		}
		return name;
	}
	public boolean isCollidable() {
		return collidable;
	}
	public void setCollidable(boolean collidable){
		this.collidable = collidable;
	}
	public boolean isMovable() {
		return movable;
	}
	public void setMovable(boolean movable) {
		this.movable = movable;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getID() {
		return ID;
	}
	
}
