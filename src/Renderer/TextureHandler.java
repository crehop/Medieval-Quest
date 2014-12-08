package renderer;

import java.io.IOException;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;


public class TextureHandler
{

    // Used to deliver a texture to entities for quad-rendering.
    public static Texture getTexture(String name)
    {
        Texture texture = null;
        try
        {
            texture = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream( "res/textures/" + name + ".png"));
        } catch (IOException e)
        {
            e.printStackTrace();
            System.exit(0); // Exit the game if the texture isn't loaded properly.
        }

        return texture;
    }
}
