package utils;

import java.nio.ByteBuffer;
import java.nio.FloatBuffer;

import javax.tools.DocumentationTool.Location;

import org.lwjgl.opengl.GL11;

public class Lighting {
	private static ByteBuffer temp = ByteBuffer.allocateDirect(16);
    private static float lightAmbient[] = { 1.0f, 1.0f, 1.0f, 1.0f };
	private static float lightDiffuse[] = { 0.5f, 0.5f, 0.5f, 1.0f };
	private static float lightPosition[] = { 0.0f, 1.0f, 1.0f, 0.0f };

	public static void createLight(Location location, float intensity){
            GL11.glLight(GL11.GL_LIGHT1, GL11.GL_AMBIENT, (FloatBuffer)temp.asFloatBuffer().put(lightAmbient).flip());              // Setup The Ambient Light
            GL11.glLight(GL11.GL_LIGHT1, GL11.GL_DIFFUSE, (FloatBuffer)temp.asFloatBuffer().put(lightDiffuse).flip());              // Setup The Diffuse Light         
            GL11.glLight(GL11.GL_LIGHT1, GL11.GL_POSITION,(FloatBuffer)temp.asFloatBuffer().put(lightPosition).flip());  
            
            GL11.glEnable(GL11.GL_LIGHT1); 
            GL11.glEnable (GL11.GL_LIGHTING ) ;
	}
}
