package utils;

import entities.ID;
import gameElements.Model;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.ArrayList;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;


public class ModelUtils {
	private static IntBuffer buffer;
	private static ByteBuffer byteBuf = null;
    private static final int FLOAT = Float.SIZE/Byte.SIZE;
	private static ArrayList<Float> V = new ArrayList();
	private static ArrayList<Float> N = new ArrayList();
	private static ArrayList<Float> T = new ArrayList();
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
}
