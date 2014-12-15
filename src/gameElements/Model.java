package gameElements;

import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glPopMatrix;
import static org.lwjgl.opengl.GL11.glPushMatrix;

import java.util.ArrayList;
import java.util.List;

import loops.GameLoop;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL11.*;
import org.lwjgl.util.vector.Vector3f;
import org.newdawn.slick.opengl.Texture;

import entities.Player;

import physics.PhysicsEngine;

import TerrainGeneration.Chunk;

import server.Location;
import utils.Face;
import utils.TextureHandler;

@SuppressWarnings("unused")
public class Model {
	boolean render = true;
	public Location location;
	public List<Vector3f> vertices = new ArrayList<Vector3f>();
	public List<Vector3f> normals = new ArrayList<Vector3f>();
	public List<Face> faces = new ArrayList<Face>();
	public int renderloop = 0;
	private float xmax = -100000000.0f;
	private float xmin = 100000000.0f;
	private float ymin = 100000000.0f;
	private float ymax = -100000000.0f;
	private float zmin = 100000000.0f;
	private float zmax = -100000000.0f;
	private boolean moved = true;
	private boolean collided = false;
	private boolean firstRun = true;
	private Chunk currentChunk;
	private final int ID;
	private String name = " NULL ";
	private boolean collidable = true;
	private boolean movable = false;
	private int renderRun;
	private Texture texture;
	
	public Model(float x,float y,float z,String name, boolean movable, boolean collidable){
		this.ID = entities.ID.getID();
		this.location = new Location(x,y,z);
		this.movable = movable;
		this.collidable = collidable;
		this.texture = TextureHandler.getTexture("dirt");
	}
	public void renderModel(){
		if(render){
			glPushMatrix();
			 	GL11.glBegin(GL11.GL_TRIANGLES);
			 	for(Face face:faces){
			 		Vector3f n1 = this.normals.get((int)face.normal.x - 1);
			 		GL11.glNormal3f((n1.x + this.location.getX()), (n1.y + this.location.getY()), (n1.z + this.location.getZ()));
			 		Vector3f v1 = this.vertices.get((int)face.vertex.x - 1);
			 		GL11.glVertex3f((v1.x + this.location.getX()), (v1.y + this.location.getY()), (v1.z + this.location.getZ()));
			 		Vector3f n2 = this.normals.get((int)face.normal.y - 1);
			 		GL11.glNormal3f((n2.x + this.location.getX()), (n2.y + this.location.getY()), (n2.z + this.location.getZ()));
			 		Vector3f v2 = this.vertices.get((int)face.vertex.y - 1);
			 		GL11.glVertex3f((v2.x + this.location.getX()), (v2.y + this.location.getY()), (v2.z + this.location.getZ()));
			 		Vector3f n3 = this.normals.get((int)face.normal.z - 1);
			 		GL11.glNormal3f((n3.x + this.location.getX()), (n3.y + this.location.getY()), (n3.z + this.location.getZ()));
			 		Vector3f v3 = this.vertices.get((int)face.vertex.z - 1);
			 		GL11.glVertex3f((v3.x + this.location.getX()), (v3.y + this.location.getY()), (v3.z + this.location.getZ()));
			 		
			 		if(n1.x + this.location.getX() > xmax){
			 			xmax = (n1.x + this.location.getX());
			 		}
			 		if(n1.x + this.location.getX() < xmin){
			 			xmin = (n1.x + this.location.getX()) ;
			 		}
			 		if(n2.x + this.location.getX() > xmax){
			 			xmax = (n2.x + this.location.getX()) ;
			 		}
			 		if(n2.x + this.location.getX() < xmin){
			 			xmin = (n2.x + this.location.getX()) ;
			 		}
			 		if(n3.x + this.location.getX() > xmax){
			 			xmax = (n3.x + this.location.getX()) ;
			 		}
			 		if(n3.x + this.location.getX() < xmin){
			 			xmin = (n3.x + this.location.getX()) ;
			 		}
			 		if(firstRun){
				 		if(n1.x + this.location.getX() > xmax)xmax = (n1.x + this.location.getX());
				 		if(n1.x + this.location.getX() < xmin)xmin = (n1.x + this.location.getX());
				 		if(n2.x + this.location.getX() > xmax)xmax = (n2.x + this.location.getX());
				 		if(n2.x + this.location.getX() < xmin)xmin = (n2.x + this.location.getX());
				 		if(n3.x + this.location.getX() > xmax)xmax = (n3.x + this.location.getX());
				 		if(n3.x + this.location.getX() < xmin)xmin = (n3.x + this.location.getX());
				 		
				 		if(v1.x + this.location.getX() > xmax)xmax = (v1.x + this.location.getX());
				 		if(v1.x + this.location.getX() < xmin)xmin = (v1.x + this.location.getX());
				 		if(v2.x + this.location.getX() > xmax)xmax = (v2.x + this.location.getX());
				 		if(v2.x + this.location.getX() < xmin)xmin = (v2.x + this.location.getX());
				 		if(v3.x + this.location.getX() > xmax)xmax = (v3.x + this.location.getX());
				 		if(v3.x + this.location.getX() < xmin)xmin = (v3.x + this.location.getX());
				 		
				 		if(n1.y + this.location.getY() > ymax)ymax = (n1.y + this.location.getY());
				 		if(n1.y + this.location.getY() < ymin)ymin = (n1.y + this.location.getY());
				 		if(n2.y + this.location.getY() > ymax)ymax = (n2.y + this.location.getY());
				 		if(n2.y + this.location.getY() < ymin)ymin = (n2.y + this.location.getY());
				 		if(n3.y + this.location.getY() > ymax)ymax = (n3.y + this.location.getY());
				 		if(n3.y + this.location.getY() < ymin)ymin = (n3.y + this.location.getY());
				 		
				 		if(v1.y + this.location.getY() > ymax)ymax = (v1.y + this.location.getY());
				 		if(v1.y + this.location.getY() < ymin)ymin = (v1.y + this.location.getY());
				 		if(v2.y + this.location.getY() > ymax)ymax = (v2.y + this.location.getY());
				 		if(v2.y + this.location.getY() < ymin)ymin = (v2.y + this.location.getY());
				 		if(v3.y + this.location.getY() > ymax)ymax = (v3.y + this.location.getY());
				 		if(v3.y + this.location.getY() < ymin)ymin = (v3.y + this.location.getY());
				 		
				 		if(n1.z + this.location.getZ() > zmax)zmax = (n1.z + this.location.getZ());
				 		if(n1.z + this.location.getZ() < zmin)zmin = (n1.z + this.location.getZ());
				 		if(n2.z + this.location.getZ() > zmax)zmax = (n2.z + this.location.getZ());
				 		if(n2.z + this.location.getZ() < zmin)zmin = (n2.z + this.location.getZ());
				 		if(n3.z + this.location.getZ() > zmax)zmax = (n3.z + this.location.getZ());
				 		if(n3.z + this.location.getZ() < zmin)zmin = (n3.z + this.location.getZ());
				 		
				 		if(v1.z + this.location.getZ() > zmax)zmax = (v1.z + this.location.getZ());
				 		if(v1.z + this.location.getZ() < zmin)zmin = (v1.z + this.location.getZ());
				 		if(v2.z + this.location.getZ() > zmax)zmax = (v2.z + this.location.getZ());
				 		if(v2.z + this.location.getZ() < zmin)zmin = (v2.z + this.location.getZ());
				 		if(v3.z + this.location.getZ() > zmax)zmax = (v3.z + this.location.getZ());
				 		if(v3.z + this.location.getZ() < zmin)zmin = (v3.z + this.location.getZ());
						renderRun++;
						this.firstRun = false;
						if(this.isMovable() && this.isCollidable()){
							physics.PhysicsEngine.checkForCollision(this);
						}

				 	}
			 	}
			 	Main.Console.setLine5("[XMIN = " + xmin + " YMIN = " + ymin + " ZMIN = " + zmin + "]");
				Main.Console.setLine6("[XMAX= " + xmax + " YMAX = " + ymax + " ZMAX = " + zmax + "]" + "CHUNK Y = " + this.getChunk().getLocation().getY());
				if(moved)this.moved = false;
		        glEnd(); 
			glPopMatrix();
	        GameLoop.test.getLocation().setX((xmin));
	        GameLoop.test.getLocation().setY(ymin);
	        GameLoop.test.getLocation().setZ((zmin));
			GameLoop.test.render();
		}
	}
	public Location getLocation(){
		return location;
	}
	public void move(float x, float y, float z){
		if(this.isMovable() && physics.PhysicsEngine.checkForCollision(this) && this.isCollidable()){
			this.location.setX(this.location.getX());
			this.location.setY(this.location.getY());
			this.location.setZ(this.location.getZ());
			if(this.collidable){
				this.collided = true;
			}
		}else{
			this.xmin += x;
			this.xmax += x;
			this.ymin += y;
			this.ymax += y;
			this.zmin += z;
			this.zmax += z;
			this.location.setX(this.location.getX() + x);
			this.location.setY(this.location.getY() + y);
			this.location.setZ(this.location.getZ() + z);
			this.moved = true;
		}
	}
	public void setLocation(float x, float y, float z){
		if(this.isMovable() && physics.PhysicsEngine.checkForCollision(this) && this.isCollidable()){
			this.location.setX(this.location.getX());
			this.location.setY(this.location.getY());
			this.location.setZ(this.location.getZ());
			if(this.collidable){
				this.collided = true;
			}
		}else{
			this.xmin += this.getLocation().getX() - x;
			this.xmax += this.getLocation().getX() - x;
			this.ymin += this.getLocation().getY() - y;
			this.ymax += this.getLocation().getY() - y;
			this.zmin += this.getLocation().getZ() - z;
			this.zmax += this.getLocation().getZ() - z;
			this.location.setX(x);
			this.location.setY(y);
			this.location.setZ(z);
			this.moved = true;
		}
	}
	public void setLocation(Location location){
		if(this.isMovable() && physics.PhysicsEngine.checkForCollision(this) && this.isCollidable()){
			this.location.setX(this.location.getX());
			this.location.setY(this.location.getY());
			this.location.setZ(this.location.getZ());
			if(this.collidable){
				this.collided = true;
			}
		}else{
			this.xmin += this.getLocation().getX() - location.getX();
			this.xmax += this.getLocation().getX() - location.getX();
			this.ymin += this.getLocation().getY() - location.getY();
			this.ymax += this.getLocation().getY() - location.getY();
			this.zmin += this.getLocation().getZ() - location.getZ();
			this.zmax += this.getLocation().getZ() - location.getZ();
			this.location.setX(location.getX());
			this.location.setY(location.getY());
			this.location.setZ(location.getZ());
			this.moved = true;
		}
	}
	public float getXmax() {
		return xmax;
	}
	public void setXmax(float xmax) {
		this.xmax = xmax;
	}
	public float getXmin() {
		return xmin;
	}
	public void setXmin(float xmin) {
		this.xmin = xmin;
	}
	public float getYmin() {
		return ymin;
	}
	public void setYmin(float ymin) {
		this.ymin = ymin;
	}
	public float getYmax() {
		return ymax;
	}
	public void setYmax(float ymax) {
		this.ymax = ymax;
	}
	public float getZmin() {
		return zmin;
	}
	public void setZmin(float zmin) {
		this.zmin = zmin;
	}
	public float getZmax() {
		return zmax;
	}
	public void setZmax(float zmax) {
		this.zmax = zmax;
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
	public void fixCollisionGround() {
		while(ymin < this.getChunk().getLocation().getY()){
			this.getLocation().setY(this.getLocation().getY() + 0.3f);
			this.ymin += 0.3f;
			this.ymax += 0.3f;
		}
		this.moved = true;
		this.renderModel();
	}
	public boolean isMoved() {
		return moved;
	}
	public void setMoved(boolean moved) {
		this.moved = moved;
	}
	public boolean isCollided() {
		return collided;
	}
	public void setCollided(boolean collided) {
		this.collided = collided;
	}
}
