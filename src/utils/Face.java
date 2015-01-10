package utils;

import org.lwjgl.util.vector.Vector3f;
import org.newdawn.slick.geom.Vector2f;

public class Face {
	public Vector3f vertex = new Vector3f();
	public Vector3f normal = new Vector3f();
	public Vector2f texture = new Vector2f();
	public Face(Vector3f vertex, Vector3f normal){
		this.vertex = vertex;
		this.normal = normal;
	}
	public void addTexture(Vector2f vector){
		this.texture = vector;
	}
}
