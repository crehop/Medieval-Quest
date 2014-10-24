package renderer;

import java.io.IOException;
import org.lwjgl.opengl.GL11;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;


public class TextureHandler
{

    // Used to deliver a texture to entities for quad-rendering.
    public static Texture getTexture(String name, String ext)
    {
        Texture texture = null;
        try
        {
            texture = TextureLoader.getTexture(ext.toUpperCase(), ResourceLoader.getResourceAsStream( "res/textures/" + name + "." + ext.toLowerCase()));
        } catch (IOException e)
        {
            e.printStackTrace();
            System.exit(0); // Exit the game if the texture isn't loaded properly.
        }

        return texture;
    }
}
