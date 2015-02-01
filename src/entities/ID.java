package entities;

public class ID {

	public static int ID = 0;
	public static int getID() {
		ID++;
		return ID;
	}
}