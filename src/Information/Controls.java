package Information;

import org.lwjgl.Sys;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

import Main.Main;

import static org.lwjgl.input.Keyboard.*;

public class Controls {
	static boolean exitKey;
	static boolean forward;
	static boolean back;
	static boolean strafeLeft;
	static boolean strafeRight;
	static boolean jump;
	static boolean crouch;
	static boolean speedUpCam;
	static boolean speedDownCam;
	static boolean rotateLeft;
	static boolean rotateRight;
	static boolean aKeyIsDown;
	static boolean test1;
	static boolean test2;
	static boolean test3;
	static boolean test4;
	static boolean zoomOut;
	static boolean zoomIn;
	static boolean offsetXPlus;
	static boolean offsetXMinus;
	static boolean offsetYPlus;
	static boolean offsetYMinus;
	static boolean offsetZPlus;
	static boolean offsetZMinus;

	
	//Mouse sensitivity
	static float mouseSensitivity = 0.05f;
	static int dwheel = Mouse.getDWheel();
	//change to adjust movement speed
	static final float defaultMovementSpeed = 0.5f;
	static float movementSpeed = defaultMovementSpeed;
	
	static float lastTime = 0.0f;
	static float time = 0.0f;
	static float frameLength = 0.0f;
	
	static float mouseX = 0.0f;
	static float mouseY = 0.0f;
	
	public static long getTime() {
		return (Sys.getTime() * 1000) / Sys.getTimerResolution();
	}
	public static void checkInput() {
		
		//Time calculation between ticks
		time = getTime();
		frameLength = (time - lastTime);
		lastTime = time;
		
		//Set check key boolean, then add if statement below to actuate
		exitKey = Keyboard.isKeyDown(KEY_ESCAPE);
		forward = Keyboard.isKeyDown(KEY_W);
		back = Keyboard.isKeyDown(KEY_S);
		strafeLeft = Keyboard.isKeyDown(KEY_A);
		strafeRight = Keyboard.isKeyDown(KEY_D);
		jump = Keyboard.isKeyDown(KEY_SPACE);
		crouch = Keyboard.isKeyDown(KEY_LSHIFT);
		speedUpCam = Keyboard.isKeyDown(KEY_TAB);
		speedDownCam = Keyboard.isKeyDown(KEY_LCONTROL);
		rotateLeft = Keyboard.isKeyDown(KEY_Q);
		rotateRight = Keyboard.isKeyDown(KEY_E);
		test1 = Keyboard.isKeyDown(KEY_ADD);
		test2 = Keyboard.isKeyDown(KEY_SUBTRACT);
		test3 = Keyboard.isKeyDown(KEY_EQUALS);
		test4 = Keyboard.isKeyDown(KEY_F);
		offsetXPlus = Keyboard.isKeyDown(KEY_UP);
		offsetXMinus = Keyboard.isKeyDown(KEY_DOWN);
		offsetYPlus = Keyboard.isKeyDown(KEY_LEFT);
		offsetYMinus = Keyboard.isKeyDown(KEY_RIGHT);
		offsetZPlus = Keyboard.isKeyDown(KEY_RSHIFT);
		offsetZMinus = Keyboard.isKeyDown(KEY_RCONTROL);
		dwheel = Mouse.getDWheel();
		aKeyIsDown = false;
		if(exitKey){
			Main.scrub();
			aKeyIsDown = true;
			return;
		}
		if(dwheel > 0){
			//Main.cam.minusZoom();
		}
		if(dwheel < 0){
			//Main.cam.plusZoom();
		}
		mouseY = Mouse.getDY() * -1;
		mouseX = Mouse.getDX();
		Main.cam.setYaw(mouseX * mouseSensitivity);
		Main.cam.setPitch(mouseY * mouseSensitivity);
		
		if(Keyboard.isKeyDown(KEY_RETURN)){
			Main.setLoop("game");
			aKeyIsDown = true;
		}
		if(forward){
			Main.cam.walkForward((float)(movementSpeed));
			aKeyIsDown = true;
		}
		if(back){
			Main.cam.walkBackward((float)(movementSpeed));
			aKeyIsDown = true;
		}
		if(strafeLeft){
			//Main.cam.setX(Main.cam.getX() + 0.01f);
			Main.cam.strafeLeft((float)(movementSpeed));
			aKeyIsDown = true;
		}
		if(strafeRight){
			//Main.cam.setX(Main.cam.getX() - 0.01f);		
			Main.cam.strafeRight((float)(movementSpeed));
			aKeyIsDown = true;
		}
		if(jump){
			//Main.cam.setY(Main.cam.getY() - 0.01f);
			Main.cam.moveUp((float)(movementSpeed));
			aKeyIsDown = true;
		}
		if(crouch){
			//Main.cam.setY(Main.cam.getY() + 0.01f);
			Main.cam.moveDown((float)(movementSpeed));
			aKeyIsDown = true;
		}
		if(speedUpCam){
			movementSpeed = defaultMovementSpeed * 5;
			aKeyIsDown = true;
		}
		else if(speedDownCam){
			movementSpeed = defaultMovementSpeed / 100;
			aKeyIsDown = true;
		}
		else{
			movementSpeed = defaultMovementSpeed;
		}
		if(rotateLeft){
			//Main.cam.Rotate90Left();
			aKeyIsDown = true;
		}
		if(rotateRight){
			//Main.cam.Rotate90Right();
			aKeyIsDown = true;
		}
		if(!(aKeyIsDown)){
			//Main.cam.decelerate();
		}
		if(test1){
		}
		if(test2){
		}
		if(test3){
		}
		if(test4){
		}
		renderer.RenderModels.setOffsetXPlus(offsetXPlus);
		renderer.RenderModels.setOffsetXMinus(offsetXMinus);
		renderer.RenderModels.setOffsetYPlus(offsetYPlus);
		renderer.RenderModels.setOffsetYMinus(offsetYMinus);
		renderer.RenderModels.setOffsetZPlus(offsetZPlus);
		renderer.RenderModels.setOffsetZMinus(offsetZMinus);
	}

}
