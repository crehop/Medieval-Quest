package Main;

import org.lwjgl.input.Keyboard;

import static org.lwjgl.input.Keyboard.*;

public class Controls {

	public static void checkInput() {
		boolean aKeyIsDown = false;
		if(Keyboard.isKeyDown(KEY_ESCAPE)){
			Main.scrub();
			aKeyIsDown = true;
		}
		if(Keyboard.isKeyDown(KEY_RETURN)){
			Main.setLoop("game");
			aKeyIsDown = true;
		}
		if(Keyboard.isKeyDown(KEY_W)){
			Main.cam.setZ(Main.cam.getZ() + 0.01f);
			aKeyIsDown = true;
		}
		if(Keyboard.isKeyDown(KEY_S)){
			Main.cam.setZ(Main.cam.getZ() - 0.01f);
			aKeyIsDown = true;
		}
		if(Keyboard.isKeyDown(KEY_A)){
			Main.cam.setX(Main.cam.getX() + 0.01f);
			aKeyIsDown = true;
		}
		if(Keyboard.isKeyDown(KEY_D)){
			Main.cam.setX(Main.cam.getX() - 0.01f);		
			aKeyIsDown = true;
		}
		if(Keyboard.isKeyDown(KEY_SPACE)){
			Main.cam.setY(Main.cam.getY() - 0.01f);
			aKeyIsDown = true;
		}
		if(Keyboard.isKeyDown(KEY_LSHIFT)){
			Main.cam.setY(Main.cam.getY() + 0.01f);
			aKeyIsDown = true;
		}
		if(Keyboard.isKeyDown(KEY_TAB)){
			Main.cam.stabalize();
			aKeyIsDown = true;
		}
		if(Keyboard.isKeyDown(KEY_Q)){
			Main.cam.setRY(Main.cam.getRY() - 0.01f);
			aKeyIsDown = true;
		}
		if(Keyboard.isKeyDown(KEY_E)){
			Main.cam.setRY(Main.cam.getRY() + 0.01f);
			aKeyIsDown = true;
		}
		if(!(aKeyIsDown)){
			Main.cam.decelerate();
		}
	}

}
