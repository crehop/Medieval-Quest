package Main;

import org.lwjgl.input.Keyboard;
import static org.lwjgl.input.Keyboard.*;

public class Controls {

	public static void checkInput() {
		if(Keyboard.isKeyDown(KEY_ESCAPE)){
			Main.scrub();
		}
		if(Keyboard.isKeyDown(KEY_W)){
			Main.setLoop("game");
		}
		if(Keyboard.isKeyDown(KEY_S)){
			Main.cam.setZ(Main.cam.getZ() - 0.01f);
		}
		if(Keyboard.isKeyDown(KEY_A)){
			Main.cam.setX(Main.cam.getX() + 0.01f);
		}
		if(Keyboard.isKeyDown(KEY_D)){
			Main.cam.setX(Main.cam.getX() - 0.01f);
		}
		if(Keyboard.isKeyDown(KEY_SPACE)){
			Main.cam.setX(Main.cam.getY() + 0.01f);
		}
		if(Keyboard.isKeyDown(KEY_LSHIFT)){
			Main.cam.setX(Main.cam.getY() - 0.01f);
		}
		if(Keyboard.isKeyDown(KEY_R)){
		}
	}

}
