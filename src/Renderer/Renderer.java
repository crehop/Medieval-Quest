package renderer;
import voxel.Wireframe;
import voxel.WireframePart;


public class Renderer {
	public static void renderFrame(){
		for(Wireframe frame: Main.Main.wireframes){
			for(WireframePart part:frame.getParts()){
				part.render();
			}
		}
	}
}
