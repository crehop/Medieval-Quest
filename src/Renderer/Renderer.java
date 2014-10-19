package renderer;
import static org.lwjgl.opengl.GL11.*;
import wireframe.Wireframe;
import wireframe.WireframePart;


public class Renderer {
	public static void renderFrame(){
		for(Wireframe frame: Main.Main.wireframes){
			for(WireframePart part:frame.getParts()){
				part.render();
			}
		}
	}
}
