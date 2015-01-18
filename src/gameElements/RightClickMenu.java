package gameElements;
import org.newdawn.slick.opengl.Texture;
import utils.TextureHandler;

public class RightClickMenu {
	public static Texture menu = TextureHandler.getTexture("res/textures/menuitem.png");
	public static int x = 0;
	public static int y = 0;
	private static int choice = 0;
	public static boolean menuOpen = false;
	public static boolean clicked = false;
	public static int[] xy = {0,43,44,85,86,128,129,171,25,475};
	
	
	public static void openMenu(String[] lines, Object obj){
		if(menuOpen){
			closeMenu();
		}
	}
	
	public static int getChoice(){
		return choice;
	}
	public static void closeMenu(){
		
	}
	public static void render(){
		if(menuOpen){
			
		}
	}
}
