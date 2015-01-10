package utils;

import java.io.IOException;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;


public class TextureHandler
{
    public static Texture texture = null;
    // Used to deliver a texture to entities for quad-rendering.
    public static Texture getTexture(String name)
    {
    	texture = null;
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
    public static Texture getModelTexture(String name)
    {
    	texture = null;
        try
        {
            texture = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream(name));
        } catch (IOException e)
        {
            e.printStackTrace();
            System.exit(0); // Exit the game if the texture isn't loaded properly.
        }
        return texture;
    }
}
