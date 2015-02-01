package utils;
import gameElements.Model;
import java.util.ArrayList;
import java.util.HashMap;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.vector.Vector3f;
import org.newdawn.slick.geom.Vector2f;


public class ModelUtils {
	public static HashMap<String, ArrayList<Vector2f>> modelTex = new HashMap<String, ArrayList<Vector2f>>();
	public static HashMap<String, ArrayList<Vector3f>> modelVert = new HashMap<String, ArrayList<Vector3f>>();
	public static HashMap<String, ArrayList<Vector3f>> modelNorm = new HashMap<String, ArrayList<Vector3f>>();
	public static HashMap<String, ArrayList<Face>> modelFaces = new HashMap<String, ArrayList<Face>>();
	public static HashMap<String, Integer> displayListID = new HashMap<String, Integer>();
	
	private static Vector2f t1 = null;
	private static Vector3f n1 = null;
	private static Vector3f v1 = null;
	private static Vector2f t2 = null;
	private static Vector3f n2 = null;
	private static Vector3f v2 = null;
	private static Vector2f t3 = null;
	private static Vector3f n3 = null;
	private static Vector3f v3 = null;
	private static int displayListHandle;
	
    public static ArrayList<Vector3f> getVerticeArray(String string){
    	if(modelVert.containsKey(string)){
    		return modelVert.get(string);
    	}
    	else{
    		return null;
    	}
    }
    public static ArrayList<Vector3f> getNormalArray(String string){
    	if(modelNorm.containsKey(string)){
    		return modelNorm.get(string);
    	}
    	else{
    		return null;
    	}
    }
    public static ArrayList<Vector2f> getTexelArray(String string){
    	if(modelTex.containsKey(string)){
    		return modelTex.get(string);
    	}
    	else{
    		return null;
    	}
    }
	public static ArrayList<Face> getFaces(String string) {
    	if(modelFaces.containsKey(string)){
    		return modelFaces.get(string);
    	}
    	else{
    		return null;
    	}
	}
	public static void createModelVertArray(String name){
		if(!(modelVert.containsKey(name))){
			modelVert.put(name,new ArrayList<Vector3f>());
		}
	}
	public static void createModelNormalArray(String name){
		if(!(modelNorm.containsKey(name))){
			modelNorm.put(name,new ArrayList<Vector3f>());
		}
	}
	public static void createModelTexelArray(String name){
		if(!(modelTex.containsKey(name))){
			modelTex.put(name,new ArrayList<Vector2f>());
		}
	}
	public static void createModelFaceArray(String name){
		if(!(modelFaces.containsKey(name))){
			modelFaces.put(name,new ArrayList<Face>());
		}
	}
	public static void createDisplayList(Model m){
		if(displayListID.containsKey(m.getName())){
			m.setDisplayList(displayListID.get(m.getName()));
		}
		else if(m.getDisplayList() == false){
			displayListHandle = GL11.glGenLists(1);
			GL11.glNewList(displayListHandle, GL11.GL_COMPILE);
			GL11.glBegin(GL11.GL_TRIANGLES);
		 	for(Face face:ModelUtils.getFaces(m.getName())){
		 		//n=normal t=texel v=vertex
		 		n1 = ModelUtils.getNormalArray(m.getName()).get((int)face.normal.x - 1);
	            t1 = ModelUtils.getTexelArray(m.getName()).get((int)face.texture.x -1);
		 		v1 = ModelUtils.getVerticeArray(m.getName()).get((int)face.vertex.x - 1);
		 		n2 = ModelUtils.getNormalArray(m.getName()).get((int)face.normal.y - 1);
		 		t2 = ModelUtils.getTexelArray(m.getName()).get((int)face.texture.y -1);
		 		v2 = ModelUtils.getVerticeArray(m.getName()).get((int)face.vertex.y - 1);
		 		n3 = ModelUtils.getNormalArray(m.getName()).get((int)face.normal.z - 1);
	            t3 = ModelUtils.getTexelArray(m.getName()).get((int)face.texture.z -1);
		 		v3 = ModelUtils.getVerticeArray(m.getName()).get((int)face.vertex.z - 1);
		        GL11.glTexCoord2f(-t1.x,-t1.y);
		        GL11.glNormal3f((n1.x), (n1.y), (n1.z));
		 		GL11.glVertex3f((v1.x), (v1.y), (v1.z));	
		        GL11.glTexCoord2f(-t2.x,-t2.y);
		 		GL11.glVertex3f((v2.x), (v2.y), (v2.z));
		 		GL11.glNormal3f((n2.x), (n2.y), (n2.z));
			    GL11.glTexCoord2f(-t3.x,-t3.y);
		 		GL11.glVertex3f((v3.x), (v3.y), (v3.z));
		 		GL11.glNormal3f((n3.x), (n3.y), (n3.z));
		 	}
		 	GL11.glEnd();
	        GL11.glEndList();
	        m.setDisplayList(displayListHandle);
			displayListID.put(m.getName(),displayListHandle);
		}
	}
}
