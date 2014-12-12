package utils;

import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glPopMatrix;
import static org.lwjgl.opengl.GL11.glPushMatrix;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL11.*;
import org.lwjgl.util.vector.Vector3f;

import server.Location;

@SuppressWarnings("unused")
public class Model {
	public Location location;
	public List<Vector3f> vertices = new ArrayList<Vector3f>();
	public List<Vector3f> normals = new ArrayList<Vector3f>();
	public List<Face> faces = new ArrayList<Face>();
	public int renderloop = 0;
	public Model(float x,float y,float z){
		this.location = new Location(x,y,z);
	}
	public void renderModel(float x,float y,float z){
		glPushMatrix();
		 	GL11.glBegin(GL11.GL_TRIANGLES);
		 	for(Face face:faces){
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
		 	}
	        glEnd();
		glPopMatrix();
	}
	public Location getLocation(){
		return location;
	}
	public void setLocation(Location location){
		this.location.setX(location.getX());
		this.location.setY(location.getY());
		this.location.setZ(location.getZ());
	}
}
