package utils;

import org.lwjgl.util.vector.Vector3f;

public class Face {
	public Vector3f vertex = new Vector3f();
	public Vector3f normal = new Vector3f();
	public Vector3f textureCall = new Vector3f();
	public Face(Vector3f vertex, Vector3f normal, Vector3f textureCall){
		this.vertex = vertex;
		this.normal = normal;
		this.textureCall = textureCall;
	}
}
