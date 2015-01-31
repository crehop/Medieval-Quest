package gameElements;

import static org.lwjgl.opengl.GL11.GL_BLEND;
import static org.lwjgl.opengl.GL11.GL_ONE_MINUS_SRC_ALPHA;
import static org.lwjgl.opengl.GL11.GL_SRC_ALPHA;
import static org.lwjgl.opengl.GL11.glBlendFunc;
import static org.lwjgl.opengl.GL11.glEnable;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glPopMatrix;
import static org.lwjgl.opengl.GL11.glPushMatrix;
import static org.lwjgl.opengl.GL11.glRotatef;
import static org.lwjgl.opengl.GL11.glTranslatef;

import java.io.File;
import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.List;

import loops.GameLoop;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL11.*;
import org.lwjgl.util.vector.Vector3f;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;

import entities.Player;

import physics.PhysicsEngine;

import Information.Console;

import server.Location;
import utils.Face;
import utils.ModelUtils;
import utils.TextureHandler;

@SuppressWarnings("unused")
public class Model {
	int faceCount = 0;
	boolean render = true;
	public Location location;
	public List<Vector3f> vertices = new ArrayList<Vector3f>();
	public List<Vector3f> normals = new ArrayList<Vector3f>();
	public List<Vector2f> textures = new ArrayList<Vector2f>();
	public List<Face> faces = new ArrayList<Face>();
	public int renderloop = 0;
	private boolean moved = true;
	private boolean collided = false;
	private boolean firstRun = true;
	private final int ID;
	private String name = " NULL ";
	private boolean collidable = true;
	private boolean movable = false;
	private int renderRun;
	protected Texture texture;
	private String key = "";
	private Vector2f t1 = null;
	private Vector3f n1 = null;
	private Vector3f v1 = null;
	private Vector2f t2 = null;
	private Vector3f n2 = null;
	private Vector3f v2 = null;
	private Vector2f t3 = null;
	private Vector3f n3 = null;
	private Vector3f v3 = null;
	private float roll = 0.0f;
	private float yaw = 0.0f;
	private float pitch = 0.0f;
    private int[] vbo = new int[3];
    private int rendercall = 0;


	
	private int count = 0;
	public Model(float x,float y,float z,File f, boolean movable, boolean collidable){
		this.ID = entities.ID.getID();
		this.location = new Location(x,y,z);
		this.movable = movable;
		this.collidable = collidable;
		this.name = f.getName().replace(".obj", "");
		texture = TextureHandler.getModelTexture(f.getPath().replace(".obj",".png"));	
		ModelUtils.convertToVBO(this);
	}
	public Model(File f, Texture texture2) {
		this.name = f.getName().replace(".obj", "");
		this.texture = texture2;	
		this.ID = -1;
		ModelUtils.convertToVBO(this);
	}
	public void renderModel(){
		rendercall++;
		if(render){
				glPushMatrix();
				    glTranslatef(this.location.getX(),this.location.getY(),this.location.getZ());
					glRotatef(pitch,1,0,0);
					glRotatef(yaw,0,1,0);
					glRotatef(roll,0,0,1);
				    glTranslatef(-this.location.getX(),-this.location.getY(),-this.location.getZ());
				    //READ http://en.wikipedia.org/wiki/Wavefront_.obj_file#Texture_maps
					this.texture.bind();
					GL11.glClearColor(1.0f, 1.0f, 1.0f, 1.0f); 
					GL11.glEnable(GL11.GL_TEXTURE_2D);
					GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_NEAREST);
			        glEnable(GL_BLEND);
			        glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
				 	GL11.glBegin(GL11.GL_TRIANGLES);
				 	for(Face face:faces){
				 		//n=normal t=texel v=vertex
				 		n1 = this.normals.get((int)face.normal.x - 1);
			            t1 = this.textures.get((int)face.texture.x -1);
				 		v1 = this.vertices.get((int)face.vertex.x - 1);
				 		n2 = this.normals.get((int)face.normal.y - 1);
				 		t2 = this.textures.get((int)face.texture.y -1);
				 		v2 = this.vertices.get((int)face.vertex.y - 1);
				 		n3 = this.normals.get((int)face.normal.z - 1);
			            t3 = this.textures.get((int)face.texture.z -1);
				 		v3 = this.vertices.get((int)face.vertex.z - 1);
				        GL11.glTexCoord2f(-t1.x,-t1.y);
				        GL11.glNormal3f((n1.x + this.location.getX()), (n1.y + this.location.getY()), (n1.z + this.location.getZ()));
				 		GL11.glVertex3f((v1.x + this.location.getX()), (v1.y + this.location.getY()), (v1.z + this.location.getZ()));	
				        GL11.glTexCoord2f(-t2.x,-t2.y);
				 		GL11.glVertex3f((v2.x + this.location.getX()), (v2.y + this.location.getY()), (v2.z + this.location.getZ()));
				 		GL11.glNormal3f((n2.x + this.location.getX()), (n2.y + this.location.getY()), (n2.z + this.location.getZ()));
					    GL11.glTexCoord2f(-t3.x,-t3.y);
				 		GL11.glVertex3f((v3.x + this.location.getX()), (v3.y + this.location.getY()), (v3.z + this.location.getZ())); 
				 		GL11.glNormal3f((n3.x + this.location.getX()), (n3.y + this.location.getY()), (n3.z + this.location.getZ()));

				 	}
					if(moved)this.moved = false;
			        glEnd(); 
				GL11.glPopMatrix();
			this.faceCount = faces.size();
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
			this.location.setX(x);
			this.location.setY(y);
			this.location.setZ(z);
			this.moved = true;
		}
	}
	public void setLocation(Location location){
		if(this.location == null){
			this.location = new Location(location.getX(),location.getY(),location.getZ());
		}
		else if(this.isMovable() && physics.PhysicsEngine.checkForCollision(this) && this.isCollidable()){
			this.location.setX(this.location.getX());
			this.location.setY(this.location.getY());
			this.location.setZ(this.location.getZ());
			if(this.collidable){
				this.collided = true;
			}
		}else{
			this.location.setX(location.getX());
			this.location.setY(location.getY());
			this.location.setZ(location.getZ());
			this.moved = true;
		}
	}
	public String getName() {
		if(name == null){
			name = "2 NULL ";
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
	/**
	 * The location where the model will be spawned (triggers firstrun).
	 * @return
	 */
	public void setStartLocation(float x, float y, float z) {
		this.location.setX(x);
		this.location.setY(y);
		this.location.setZ(z);
		this.firstRun = true;
	}
	public List<Face> getFaces() {
		return faces;
	}
	public void setTexture(Texture texture2) {
		this.texture = texture2;
	}
	public int faceCount() {
		return faceCount;
	}
	public void setPitch(float f){
		if(f > 360){
			this.pitch = 0 + f-360;
		}
		else if(f < 0){
			this.pitch = 360 + f; 
		}
		else{
			this.pitch = f;
		}
	}
	public void setYaw(float f){
		if(f > 360){
			this.yaw = 0 + f-360;
		}
		else if(f < 0){
			this.yaw = 360 + f; 
		}
		else{
			this.yaw = f;
		}
	}
	public void setRoll(float f){
		if(f > 360){
			this.roll = 0 + f-360;
		}
		else if(f < 0){
			this.roll = 360 + f; 
		}
		else{
			this.roll = f;
		}
	}
	public float getYaw(){
		return yaw;
	}
	public float getPitch(){
		return pitch;
	}
	public float getRoll(){
		return roll;
	}
	public void setVBOInfo(int[] vbo){
		this.vbo[0] = vbo[0];
		this.vbo[1] = vbo[1];
		this.vbo[2] = vbo[2];
	}
	public void renderAsVBO(){
		/*glPushMatrix();
			Console.setLine6("VBO = " + vbo[0] + "," + vbo[1] + "," + vbo[2] + "times rendered =" + rendercall);
			glRotatef(pitch,1,0,0);
			glRotatef(yaw,0,1,0);
			glRotatef(roll,0,0,1);
			GL11.glClearColor(1.0f, 1.0f, 1.0f, 1.0f); 
	        glEnable(GL_BLEND);
	        glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
			Console.setLine9("GL ERROR ? = " + GL11.glGetError());
			GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_NEAREST);
			
		    GL11.glEnableClientState(GL11.GL_VERTEX_ARRAY);
			GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, this.vbo[0]); 
			GL11.glVertexPointer(3, GL11.GL_FLOAT, 0,0);
		   
			GL11.glEnableClientState(GL11.GL_NORMAL_ARRAY);
			GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, this.vbo[1]); 
			GL11.glVertexPointer(3, GL11.GL_FLOAT, 0,0);
		   
			GL11.glEnableClientState(GL11.GL_TEXTURE_COORD_ARRAY);
			GL11.glBindTexture(GL11.GL_TEXTURE_2D, this.vbo[2]);
			GL11.glVertexPointer(3, GL11.GL_FLOAT, 0,0);

			GL11.glDrawArrays(GL11.GL_TRIANGLES, 0, 0);

			Console.setLine10("GL ERROR ? = " + GL11.glGetError());
		glPopMatrix();*/
	}
}
