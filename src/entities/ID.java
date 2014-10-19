package entities;

public class ID {
	public static int partID = 0;
	public static int getPartID() {
		partID++;
		return partID;
	}
}
