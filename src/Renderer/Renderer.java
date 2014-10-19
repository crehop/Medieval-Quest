package Renderer;
import static org.lwjgl.opengl.GL11.*;

import Wireframe.Wireframe;
import Wireframe.WireframePart;

public class Renderer {
	public static void renderFrame(){
		for(Wireframe frame: Main.Main.wireframes){
			for(WireframePart part:frame.getParts()){
				part.render();
			}
		}
	}
}
