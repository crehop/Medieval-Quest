package entities;

import java.util.HashMap;

public class ID {
	public static HashMap<String, int[]> models = new HashMap<String, int[]>();
	public static int ID = 0;
	public static int getID() {
		ID++;
		return ID;
	}
	public static boolean checkForModel(String string){
		if(models.containsKey(string)){
			return true;
		}
		else{
			return false;
		}
	}
	public static int[] getVBO(String key){
		return models.get(key);
	}
	public static void putVBO(int[] buffer,String key){
		models.put(key, buffer);
	}
}