package loops;

import static org.lwjgl.opengl.GL11.*;

import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.newdawn.slick.opengl.Texture;

import Main.Controls;

public class StartLoop {
	static Texture splash = renderer.TextureHandler.getTexture("splash");

	public static void render() {
		GL11.glBegin(GL_PROJECTION);
			splash.bind();
			glColor3f(1.0f,1.0f,1.0f);
			glRecti(0, 0, Display.getHeight(), Display.getWidth());
			glTexCoord2f(0, 0);
			glTexCoord2f(0, Display.getWidth());
			glTexCoord2f(Display.getHeight(), 0);
			glTexCoord2f(Display.getHeight(), Display.getWidth());
		GL11.glEnd();
		Controls.checkInput();
	}
}
