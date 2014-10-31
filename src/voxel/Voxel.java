package voxel;
import static org.lwjgl.opengl.GL11.*;
import java.util.Random;
import org.newdawn.slick.Color;
import org.newdawn.slick.opengl.Texture;

import renderer.TextureHandler;
import server.Location;
import wireframe.WireframePart;

//import java.util.Random;

public class Voxel {
	private float size;
	private Location location;
	private Texture texture;
	private float offsetX;
	private float offsetY;
	private float offsetZ;
	private boolean physicsControlled;
	private Random rand = new Random();
	private float rotationX = 0.0f;
	private float rotationY = 0.0f;
	private float rotationZ = 0.0f;
	private WireframePart part = null;

	public Voxel(Location location){
		this.location = new Location(location);
		size = 0.5f;
		this.texture = TextureHandler.getTexture("grass", "png" );

	}
	public Voxel(Location location, float size) {
		this.location = location;
		this.size = size;
		this.texture = TextureHandler.getTexture("grass", "png");
	}
	public Voxel(Location location, float size,Texture texture) {
		this.location = location;
		this.size = size;
		this.texture = texture;
	}
	public Voxel(Location location, float size, float offsetX, float offsetY, float offsetZ ) {
		this.texture = TextureHandler.getTexture("grass", "png" );
		this.location = location;
		this.size = size;
		this.offsetX = offsetX;
		this.offsetY = offsetY;
		this.offsetZ = offsetZ;
	}
	public Voxel(Location location, float size,Texture texture, float offsetX, float offsetY, float offsetZ ) {
		this.location = location;
		this.size = size;
		this.texture = texture;
		this.offsetX = offsetX;
		this.offsetY = offsetY;
		this.offsetZ = offsetZ;
	}
	public Voxel(Location location2, float f, boolean top, boolean bottom, boolean left,
			boolean right, boolean forward, boolean back) {
		// TODO change rendering options so non-rendered sides render is not called.
	}
	public void render(){
		initiateRender();
		renderFinal();
	}
	
	private void initiateRender() {
		glPushMatrix();
		glTranslatef(-this.getLocation().getX(),-this.getLocation().getY(),-this.getLocation().getZ());
		glRotatef(rotationX,1.0f,0.0f,0.0f);
		glRotatef(rotationY,0.0f,1.0f,0.0f);
		glRotatef(rotationZ,0.0f,0.0f,1.0f);
		glTranslatef(-this.getLocation().getX(),-this.getLocation().getY(),-this.getLocation().getZ());
		if(physicsControlled == false){
			this.move(this.getLocation());
		}else{
			if(rand.nextFloat() > 0.08)
			this.offsetY-=rand.nextFloat()/100;
			
			this.move(this.getLocation());
		}
	    Color.white.bind();
	    if(this.texture == null){
			this.texture = TextureHandler.getTexture("grass", "png" );
	    }
	    texture.bind(); 
	    glEnable(GL_TEXTURE_2D);  
	    glClearColor(0.0f, 0.0f, 0.0f, 0.0f); 
	    glBegin(GL_QUADS);
		{
			renderFront(true);
			renderLeft(true);
			renderRight(true);
			renderBack(true);
			renderBottom(true);
			renderTop(true);
		}
	}
	
	private void move(float x, float y, float z) {
		glTranslatef(x + this.offsetX, y + this.offsetY, z + this.offsetZ);
	}
	private void renderFinal() {
		glEnd();
	    glPopMatrix();
	}

	private void renderTop(boolean render) {
		if(render){
			texture.bind();
			//Top Face
            glTexCoord2f(0, 0); // top left
			glVertex3f(size,-1 * (size),-1 * (size));
            glTexCoord2f(1, 0);			
			glVertex3f(size,-1 * (size),size);
            glTexCoord2f(1, 1);		
            glVertex3f(size,size,size);
            glTexCoord2f(0, 1);
            glVertex3f(size,size,-1 * (size));
		}
	}

	private void renderBottom(boolean render) {
		if(render){
			//Bottom Face
            glTexCoord2f(0, 0); // top left
			glVertex3f(-1 * (size),-1 * (size),-1 * (size));
            glTexCoord2f(1, 0);			
			glVertex3f(-1 * (size),-1 * (size),size);
            glTexCoord2f(1, 1);		
			glVertex3f(-1 * (size),size,size);
            glTexCoord2f(0, 1);
			glVertex3f(-1 * (size),size,-1 * (size));	
		}
	}
	
	private void renderFront(boolean render) {
		if(render){
			//Front Face
            glTexCoord2f(0, 0); // top left
			glVertex3f(-1 * (size),-1 * (size),size);
            glTexCoord2f(1, 0);			
			glVertex3f(-1 * (size),size,size);
            glTexCoord2f(1, 1);		
			glVertex3f(size,size,size);
            glTexCoord2f(0, 1);
			glVertex3f(size,-1 * (size),size);
		}
	}

	private void renderBack(boolean render) {
		if(render){
		//Back Face;
            glTexCoord2f(0, 0); // top left
			glVertex3f(-1 * (size),-1 * (size),-1 * (size));
            glTexCoord2f(1, 0);			
			glVertex3f(-1 * (size),size,-1 * (size));
            glTexCoord2f(1, 1);		
			glVertex3f(size,size,-1 * (size));
            glTexCoord2f(0, 1);
			glVertex3f(size,-1 * (size),-1 * (size));
		}
	}

	private void renderRight(boolean render) {
		if(render){
			//Right Face
            glTexCoord2f(0, 0); // top left
			glVertex3f(-1 * (size),size,-1 * (size));
            glTexCoord2f(1, 0);			
			glVertex3f(size,size,-1 * (size));
            glTexCoord2f(1, 1);		
			glVertex3f(size,size,size);
            glTexCoord2f(0, 1);
			glVertex3f(-1 * (size),size,size);		
		}
	}

	private void renderLeft(boolean render) {
		if(render){
			//Left Face
            glTexCoord2f(0, 0); // top left
			glVertex3f(-1 * (size),-1 * (size),-1 * (size));
            glTexCoord2f(1, 0);			
			glVertex3f(size,-1 * (size),-1 * (size));
            glTexCoord2f(1, 1);		
			glVertex3f(size,-1 * (size),size);
            glTexCoord2f(0, 1);
			glVertex3f(-1 * (size),-1 * (size),size);	
		}
	}
	public Location getLocation(){
		return location;
	}
	
	private void move(Location location){
		if(this.offsetX != 0 || this.offsetY != 0 || this.offsetZ != 0){
			glTranslatef(location.getX() + this.offsetX,location.getY() + this.offsetY,location.getZ() + this.offsetZ);
		}else{
			glTranslatef(location.getX(),location.getY(),location.getZ());
		}
	}
	public void rotateX(float rotate){
		this.rotationX = rotate;
	}
	public void rotateY(float rotate){
		this.rotationY = rotate;
	}
	public void rotateZ(float rotate){
		this.rotationZ = rotate;
	}
	public void setPhysicsControlled(boolean controlled){
		this.physicsControlled = controlled;
	}
	//private static void glRandomColor() {
	//	Random rand = new Random();
	//	glColor3f(rand.nextFloat(),rand.nextFloat(),rand.nextFloat());
	//}
	public void setOffsetX(float f) {
		this.offsetX = f;
	}
	public float getOffsetX() {
		return this.offsetX;
	}
	public WireframePart getPart() {
		return part;
	}
	public void setPart(WireframePart part) {
		this.part = part;
	}
	public float getOffsetY() {
		return this.offsetY;
	}
	public void setOffsetY(float offsetY) {
		this.offsetY = offsetY;
	}
	public float getOffsetZ() {
		return this.offsetZ;
	}
	public void setOffsetZ(float offsetZ) {
		this.offsetZ = offsetZ;
	}
}
