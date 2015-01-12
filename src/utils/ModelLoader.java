package utils;

import gameElements.Model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.lwjgl.util.vector.Vector3f;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.opengl.Texture;

import Information.Console;
public class ModelLoader {
	public static String line;
	private static Vector3f vertexIndices;
	private static Vector3f textureCall;
	private static Vector3f normalIndices;
	private static Vector2f textureIndices;
	private static float x = 0.0f;
	private static float y = 0.0f;
	private static float z = 0.0f;
	public static Model loadModel(File f) throws FileNotFoundException, IOException{
		Model m = new Model(0, 0, 0, f,false,true);
		physics.PhysicsEngine.modelList.add(m);
		BufferedReader reader = new BufferedReader(new FileReader(f));
		while((line = reader.readLine()) != null){
			if(line.startsWith("v ")){
				x = Float.valueOf(line.split(" ")[1]);
				y = Float.valueOf(line.split(" ")[2]);
				z = Float.valueOf(line.split(" ")[3]);
				m.vertices.add(new Vector3f(x,y,z));
			} else if(line.startsWith("vn ")){
				x = Float.valueOf(line.split(" ")[1]);
				y = Float.valueOf(line.split(" ")[2]);
				z = Float.valueOf(line.split(" ")[3]);
				m.normals.add(new Vector3f(x,y,z));
			} else if(line.startsWith("f ")){
				vertexIndices = new Vector3f(
						 Float.valueOf(line.split(" ")[1].split("/")[0])
						,Float.valueOf(line.split(" ")[2].split("/")[0])
						,Float.valueOf(line.split(" ")[3].split("/")[0]));
				normalIndices = new Vector3f(
						 Float.valueOf(line.split(" ")[1].split("/")[2])
						,Float.valueOf(line.split(" ")[2].split("/")[2])
						,Float.valueOf(line.split(" ")[3].split("/")[2]));
				textureCall = new Vector3f(
						 Float.valueOf(line.split(" ")[1].split("/")[1])
						,Float.valueOf(line.split(" ")[2].split("/")[1])
						,Float.valueOf(line.split(" ")[3].split("/")[1]));
				m.faces.add(new Face(vertexIndices,normalIndices,textureCall));
			}
			else if(line.startsWith("vt ")){
				textureIndices = new Vector2f(
						x = Float.valueOf(line.split(" ")[1]),
						y = Float.valueOf(line.split(" ")[2]));
				m.textures.add(textureIndices);
			}
		}
		m.setName("" + f.getName());
		reader.close();
		return m;
	}

	public static int[] createVBO(Model model) {
		return null;
	}

	public static Model loadModel(File f, Texture t) throws FileNotFoundException, IOException{
		Model m = new Model(f,t);
		physics.PhysicsEngine.modelList.add(m);
		BufferedReader reader = new BufferedReader(new FileReader(f));
		while((line = reader.readLine()) != null){
			if(line.startsWith("v ")){
				x = Float.valueOf(line.split(" ")[1]);
				y = Float.valueOf(line.split(" ")[2]);
				z = Float.valueOf(line.split(" ")[3]);
				m.vertices.add(new Vector3f(x,y,z));
			} else if(line.startsWith("vn ")){
				x = Float.valueOf(line.split(" ")[1]);
				y = Float.valueOf(line.split(" ")[2]);
				z = Float.valueOf(line.split(" ")[3]);
				m.normals.add(new Vector3f(x,y,z));
			} else if(line.startsWith("f ")){
				vertexIndices = new Vector3f(
						 Float.valueOf(line.split(" ")[1].split("/")[0])
						,Float.valueOf(line.split(" ")[2].split("/")[0])
						,Float.valueOf(line.split(" ")[3].split("/")[0]));
				normalIndices = new Vector3f(
						 Float.valueOf(line.split(" ")[1].split("/")[2])
						,Float.valueOf(line.split(" ")[2].split("/")[2])
						,Float.valueOf(line.split(" ")[3].split("/")[2]));
				textureCall = new Vector3f(
						 Float.valueOf(line.split(" ")[1].split("/")[1])
						,Float.valueOf(line.split(" ")[2].split("/")[1])
						,Float.valueOf(line.split(" ")[3].split("/")[1]));
				m.faces.add(new Face(vertexIndices,normalIndices,textureCall));
			}
			else if(line.startsWith("vt ")){
				textureIndices = new Vector2f(
						x = Float.valueOf(line.split(" ")[1]),
						y = Float.valueOf(line.split(" ")[2]));
				m.textures.add(textureIndices);
			}
		}
		m.setName("" + f.getName());
		reader.close();
		return m;
	}
}
