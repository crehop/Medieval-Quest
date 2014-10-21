package Main;

import org.lwjgl.Sys;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

import static org.lwjgl.input.Keyboard.*;

public class Controls {
	static boolean exitKey;
	static boolean forward;
	static boolean back;
	static boolean strafeLeft;
	static boolean strafeRight;
	static boolean jump;
	static boolean crouch;
	static boolean stabalizeCam;
	static boolean rotateLeft;
	static boolean rotateRight;
	static boolean aKeyIsDown;
	
	//Mouse sensitivity
	static float mouseSensitivity = 0.05f;
	//change to adjust movement speed
	static float movementSpeed = 10.0f;
	
	static float lastTime = 0.0f;
	static float time = 0.0f;
	static float frameLength = 0.0f;
	
	static float mouseX = 0.0f;
	static float mouseY = 0.0f;
	
	public static void checkInput() {
		
		//Time calculation between ticks
		time = Sys.getTime();
		frameLength = (time - lastTime)/1000.0f;
		lastTime = time;
		
		//Set check key boolean, then add if statement below to actuate
		exitKey = Keyboard.isKeyDown(KEY_ESCAPE);
		forward = Keyboard.isKeyDown(KEY_W);
		back = Keyboard.isKeyDown(KEY_S);
		strafeLeft = Keyboard.isKeyDown(KEY_A);
		strafeRight = Keyboard.isKeyDown(KEY_D);
		jump = Keyboard.isKeyDown(KEY_SPACE);
		crouch = Keyboard.isKeyDown(KEY_LSHIFT);
		stabalizeCam = Keyboard.isKeyDown(KEY_TAB);
		rotateLeft = Keyboard.isKeyDown(KEY_Q);
		rotateRight = Keyboard.isKeyDown(KEY_E);
		aKeyIsDown = false;
		if(exitKey){
			Main.scrub();
			aKeyIsDown = true;
			return;
		}
		
		
		//MOUSE MOVE COMMANDS
		mouseY = Mouse.getDY() * -1;
		mouseX = Mouse.getDX();
		Main.cam.yaw(mouseX * mouseSensitivity);
		Main.cam.pitch(mouseY * mouseSensitivity);
		
		if(Keyboard.isKeyDown(KEY_RETURN)){
			Main.setLoop("game");
			aKeyIsDown = true;
		}
		if(forward){
			//Main.cam.setZ(Main.cam.getZ() + 0.01f);
			Main.cam.walkForward(movementSpeed * frameLength);
			aKeyIsDown = true;
		}
		if(back){
			//Main.cam.setZ(Main.cam.getZ() - 0.01f);
			Main.cam.walkBackward(movementSpeed * frameLength);
			aKeyIsDown = true;
		}
		if(strafeLeft){
			//Main.cam.setX(Main.cam.getX() + 0.01f);
			Main.cam.strafeLeft(movementSpeed * frameLength);
			aKeyIsDown = true;
		}
		if(strafeRight){
			//Main.cam.setX(Main.cam.getX() - 0.01f);		
			Main.cam.strafeRight(movementSpeed * frameLength);
			aKeyIsDown = true;
		}
		if(jump){
			//Main.cam.setY(Main.cam.getY() - 0.01f);
			Main.cam.moveUp(movementSpeed * frameLength);
			aKeyIsDown = true;
		}
		if(crouch){
			//Main.cam.setY(Main.cam.getY() + 0.01f);
			Main.cam.moveDown(movementSpeed * frameLength);
			aKeyIsDown = true;
		}
		if(stabalizeCam){
			//Main.cam.stabalize();
			aKeyIsDown = true;
		}
		if(rotateLeft){
			//Main.cam.setRY(Main.cam.getRY() - 0.01f);
			aKeyIsDown = true;
		}
		if(rotateRight){
			//Main.cam.setRY(Main.cam.getRY() + 0.01f);
			aKeyIsDown = true;
		}
		if(!(aKeyIsDown)){
			//Main.cam.decelerate();
		}
	}

}
