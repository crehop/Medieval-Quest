package gameElements;

import java.util.ArrayList;

public class Interface {
	
	private static ArrayList<ClickMenu> menus = new ArrayList<ClickMenu>();
	
	public static void renderMenus(){
		for(ClickMenu menu :menus){
			menu.render();
		}
	}

	public static void addMenu(ClickMenu clickMenu) {
		menus.add(clickMenu);
	}
}
