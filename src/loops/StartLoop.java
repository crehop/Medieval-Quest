package loops;

import static org.lwjgl.opengl.GL11.glColor3f;
import static org.lwjgl.opengl.GL11.glRecti;
import static org.lwjgl.opengl.GL11.glTexCoord2f;

import org.lwjgl.opengl.Display;
import org.newdawn.slick.opengl.Texture;

import Main.Controls;

public class StartLoop {
	public static void render() {
		glColor3f(1.0f,1.0f,1.0f);
		glRecti(0, 0, Display.getHeight(), Display.getWidth());
		Texture splash = renderer.TextureHandler.load("splash");
		splash.bind();
		glTexCoord2f(0, 0);
		glTexCoord2f(0, Display.getWidth());
		glTexCoord2f(Display.getHeight(), 0);
		glTexCoord2f(Display.getHeight(), Display.getWidth());
		Controls.checkInput();
	}
}
