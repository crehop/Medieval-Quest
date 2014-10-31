package loops;
 
import java.util.ArrayList;
import java.util.Random;
import org.lwjgl.input.Mouse;
import entities.Zombie;
import renderer.Skybox;
import server.Location;
import wireframe.WireframePart;
import Main.Main;

public class GameLoop {
	private static float rotationX = 0.0f;
	private static float rotationY = 0.0f;
	private static float rotationZ = 0.0f;
	public static ArrayList<WireframePart> renderMe = new ArrayList<WireframePart>();
	static float expand = 0;
	static WireframePart check;
	static boolean debug = false;
	static boolean gravity = false;
	static Random rand = new Random();
	static Zombie first;
	public static void loop(){
		if(Mouse.isGrabbed() == false){
			Mouse.setGrabbed(true);
			Location home21 = new Location(10.0f,0.0f,0.0f);			
			first = new Zombie(home21);
		}
		for(WireframePart part:renderMe){
			part.render();
			part.setRotateX(part.getRotateX());
			part.setRotateY(part.getRotateY());
			part.setRotateZ(part.getRotateZ());
			if(debug){
				first.animateRunForward();
			}
			if(gravity){
				float check = rand.nextFloat();
				
				if(check < 0.03){
					part.setDropcall(true);
				}
			}
			else{
				part.setDropcall(false);
			}
		}
		Skybox.renderSkyBox(Main.cam);
	}
	public static void expand(){
		debug = true;
	}
	public static void contract(){
		debug = false;
	}
	public static void reset(){
		gravity();
	}
	public static void gravity() {
		if(gravity == false){
			gravity = true;
		}else{
			gravity = false;
		}
	}
}
