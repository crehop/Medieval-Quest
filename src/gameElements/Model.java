package gameElements;
import java.io.File;
import java.util.List;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.opengl.Texture;

import server.Location;
import utils.Face;
import utils.ModelUtils;
import utils.TextureHandler;

public class Model {
	int faceCount = 0;
	boolean render = true;
	public Location location;
	public int renderloop = 0;
	private boolean moved = true;
	private boolean collided = false;
	private final int ID;
	private String name = " NULL ";
	private boolean collidable = true;
	private boolean movable = false;
	protected Texture texture;
	private float roll = 0.0f;
	private float yaw = 0.0f;
	private float pitch = 0.0f;
    private int list = 0;
	public Model(float x,float y,float z,File f, boolean movable, boolean collidable){
		this.ID = entities.ID.getID();
		this.location = new Location(x,y,z);
		this.movable = movable;
		this.collidable = collidable;
		this.name = f.getName().replace(".obj", "");
		texture = TextureHandler.getModelTexture(f.getPath().replace(".obj",".png"));
		//ModelUtils.convertToVBO(this);
	}
	public Model(File f, Texture texture2) {
		this.name = f.getName().replace(".obj", "");
		this.texture = texture2;	
		this.ID = entities.ID.getID();
		if(this.getDisplayList() == false){
			ModelUtils.createDisplayList(this);
		}
		//ModelUtils.convertToVBO(this);
	}
	public void renderModel(){
		if(this.getDisplayList() == false){
			ModelUtils.createDisplayList(this);
		}
		if(render){
			GL11.glPushMatrix();
				GL11.glTranslatef(this.location.getX(),this.location.getY(),this.location.getZ());
				    GL11.glRotatef(pitch,1,0,0);
					GL11.glRotatef(yaw,0,1,0);
					GL11.glRotatef(roll,0,0,1);
					GL11.glTranslatef(-this.location.getX(),-this.location.getY(),-this.location.getZ());
				    //READ http://en.wikipedia.org/wiki/Wavefront_.obj_file#Texture_maps
					this.texture.bind();
					GL11.glClearColor(1.0f, 1.0f, 1.0f, 1.0f); 
					GL11.glEnable(GL11.GL_TEXTURE_2D);
					GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_NEAREST);
					GL11.glEnable(GL11.GL_BLEND);
					GL11.glEnable(GL11.GL_CULL_FACE);
					GL11.glCullFace(GL11.GL_BACK);
					GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
			        GL11.glCallList(list);
					GL11.glDisable(GL11.GL_CULL_FACE);
				GL11.glPopMatrix();
			this.faceCount = ModelUtils.getFaces(name).size();
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
	}
	public List<Face> getFaces() {
		return ModelUtils.getFaces(name);
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
	/*public void setVBOInfo(int[] vbo){
		this.vbo[0] = vbo[0];
		this.vbo[1] = vbo[1];
		this.vbo[2] = vbo[2];
	}
	public void renderAsVBO(){
		glPushMatrix();
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
		glPopMatrix();
	}*/
	public boolean getDisplayList(){
		if(list == 0){
			return false;
		}
		return true;
	}
	public void setList(int displayListHandle) {
		this.list = displayListHandle;
	}
}
