package utils;

import entities.ID;
import gameElements.Model;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.HashMap;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.lwjgl.util.vector.Vector3f;
import org.newdawn.slick.geom.Vector2f;


public class ModelUtils {
	private static IntBuffer buffer;
	private static ByteBuffer byteBuf = null;
    private static final int FLOAT = Float.SIZE/Byte.SIZE;
	private static ArrayList<Float> V = new ArrayList();
	private static ArrayList<Float> N = new ArrayList();
	private static ArrayList<Float> T = new ArrayList();
	
	public static HashMap<String, ArrayList<Vector2f>> modelTex = new HashMap<String, ArrayList<Vector2f>>();
	public static HashMap<String, ArrayList<Vector3f>> modelVert = new HashMap<String, ArrayList<Vector3f>>();
	public static HashMap<String, ArrayList<Vector3f>> modelNorm = new HashMap<String, ArrayList<Vector3f>>();
	public static HashMap<String, ArrayList<Face>> modelFaces = new HashMap<String, ArrayList<Face>>();

    private static Float[] normal = null;
    private static Float[] vertice = null;
    private static Float[] texel = null;
    static FloatBuffer normals = null;
    static FloatBuffer vertices = null;
    static FloatBuffer texels = null;
    static int[] vbo = new int[3];
    public static void convertToVBO(Model model){
    	if(!(ID.checkForModel(model.getName()))){
	    	for(Face face:model.getFaces()){
	    		V.add(face.vertex.x);
	    		V.add(face.vertex.y);
	    		V.add(face.vertex.z);
	    		V.add(face.normal.x);
	    		V.add(face.normal.y);
	    		V.add(face.normal.z);
	    		T.add(face.texture.x);
	    		T.add(face.texture.y);
	    		T.add(face.texture.z);
	    	}
	    	vertice = V.toArray(new Float[V.size()]);
	    	normal = N.toArray(new Float[N.size()]);
	    	texel = T.toArray(new Float[T.size()]);
	    	
	    	normals = BufferUtils.createFloatBuffer(normal.length);
	    	byteBuf = ByteBuffer.allocateDirect(normal.length * 4); //4 bytes per float
	    	byteBuf.order(ByteOrder.nativeOrder());
	    	normals.put(byteBuf.asFloatBuffer());
	    	normals.flip();
	    	
	    	vertices = BufferUtils.createFloatBuffer(vertice.length);
	    	byteBuf = ByteBuffer.allocateDirect(vertice.length * 4); //4 bytes per float
	    	byteBuf.order(ByteOrder.nativeOrder());
	    	vertices.put(byteBuf.asFloatBuffer());
	    	vertices.flip();
	    	
	    	texels = BufferUtils.createFloatBuffer(texel.length);
	    	byteBuf = ByteBuffer.allocateDirect(texel.length * 4); //4 bytes per float
	    	byteBuf.order(ByteOrder.nativeOrder());
	    	texels.put(byteBuf.asFloatBuffer());
	    	texels.flip();
	    	
	        // define the size and function of the buffer.
	    	// Vertices
	    	createNewVBOID();
	        System.out.println("" + buffer.get(0));
	        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, buffer.get(0));
	        GL15.glBufferData(GL15.GL_ARRAY_BUFFER, vertices, GL15.GL_STATIC_DRAW);
	        vbo[0] = buffer.get(0);
	    	
	        // Normals
	        createNewVBOID();
	        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, buffer.get(0));
	        GL15.glBufferData(GL15.GL_ARRAY_BUFFER, normals, GL15.GL_STATIC_DRAW);
	        vbo[1] = buffer.get(0);
	        
	        // Texels
	        createNewTexVBOID();
	        //System.out.println("" + buffer.get(0));
	        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, buffer.get(0));
	        GL15.glBufferData(GL15.GL_ARRAY_BUFFER, texels, GL15.GL_STATIC_DRAW);
	        vbo[2] = buffer.get(0);
	        
		    model.setVBOInfo(vbo);
	        ID.putVBO(vbo, model.getName());
    	}else{
    		model.setVBOInfo(ID.getVBO(model.getName()));
    	}
    }
    public static void createNewVBOID() {
        buffer = BufferUtils.createIntBuffer(1);
        GL15.glGenBuffers(buffer);
    }
    public static void createNewTexVBOID() {
        buffer = BufferUtils.createIntBuffer(1);
        GL11.glGenTextures(buffer);
    }
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
			System.out.println("VERTS MADE" + name);
			modelVert.put(name,new ArrayList<Vector3f>());
		}
	}
	public static void createModelNormalArray(String name){
		if(!(modelNorm.containsKey(name))){
			System.out.println("NORMAL MADE" + name);
			modelNorm.put(name,new ArrayList<Vector3f>());
		}
	}
	public static void createModelTexelArray(String name){
		if(!(modelTex.containsKey(name))){
			System.out.println("TEXEL MADE" + name);
			modelTex.put(name,new ArrayList<Vector2f>());
		}
	}
	public static void createModelFaceArray(String name){
		if(!(modelFaces.containsKey(name))){
			System.out.println("FACE MADE" + name);
			modelFaces.put(name,new ArrayList<Face>());
		}
	}
}
