package Main;

import org.lwjgl.input.Keyboard;
import static org.lwjgl.input.Keyboard.*;

public class Controls {
	
	public static void checkInput() {
		//Set check key boolean, then add if statement below to actuate
		boolean exitKey = Keyboard.isKeyDown(KEY_ESCAPE);
		boolean forward = Keyboard.isKeyDown(KEY_W);
		boolean back = Keyboard.isKeyDown(KEY_S);
		boolean strafeLeft = Keyboard.isKeyDown(KEY_A);
		boolean strafeRight = Keyboard.isKeyDown(KEY_D);
		boolean jump = Keyboard.isKeyDown(KEY_SPACE);
		boolean crouch = Keyboard.isKeyDown(KEY_LSHIFT);
		boolean stabalizeCam = Keyboard.isKeyDown(KEY_TAB);
		boolean rotateLeft = Keyboard.isKeyDown(KEY_Q);
		boolean rotateRight = Keyboard.isKeyDown(KEY_E);
		boolean aKeyIsDown = false;
		if(exitKey){
			Main.scrub();
			aKeyIsDown = true;
		}
		if(Keyboard.isKeyDown(KEY_RETURN)){
			Main.setLoop("game");
			aKeyIsDown = true;
		}
		if(forward){
			Main.cam.setZ(Main.cam.getZ() + 0.01f);
			aKeyIsDown = true;
		}
		if(back){
			Main.cam.setZ(Main.cam.getZ() - 0.01f);
			aKeyIsDown = true;
		}
		if(strafeLeft){
			Main.cam.setX(Main.cam.getX() + 0.01f);
			aKeyIsDown = true;
		}
		if(strafeRight){
			Main.cam.setX(Main.cam.getX() - 0.01f);		
			aKeyIsDown = true;
		}
		if(jump){
			Main.cam.setY(Main.cam.getY() - 0.01f);
			aKeyIsDown = true;
		}
		if(crouch){
			Main.cam.setY(Main.cam.getY() + 0.01f);
			aKeyIsDown = true;
		}
		if(stabalizeCam){
			Main.cam.stabalize();
			aKeyIsDown = true;
		}
		if(rotateLeft){
			Main.cam.setRY(Main.cam.getRY() - 0.01f);
			aKeyIsDown = true;
		}
		if(rotateRight){
			Main.cam.setRY(Main.cam.getRY() + 0.01f);
			aKeyIsDown = true;
		}
		if(!(aKeyIsDown)){
			Main.cam.decelerate();
		}
	}

}
