package wireframe;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import static org.lwjgl.input.Keyboard.*;


import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.*;
import org.lwjgl.util.Color;

import server.Location;


import Main.Main;
import static org.lwjgl.opengl.GL11.*;

public class WireframePart{
	private final int ID = entities.ID.getPartID();
	private boolean draw = true;
	private Wireframe body;
	private Location start;
	private Location end;
	private Color color;
	public WireframePart(Location start, Location end, Wireframe body, int width, int length){
		if(this.body == null){
			ArrayList<WireframePart> parts = new ArrayList<WireframePart>();
			body = new Wireframe(parts);
			this.end = end;
			this.start = start;
		}
		else{
			this.body = body;
			this.end = end;
			this.start = start;
		}
	}
	public void render() {
		{
		}
	}
	public Location getStart() {
		return start;
	}
	public void setStart(Location start) {
		this.start = start;
	}
	public Location getEnd() {
		return end;
	}
	public void setEnd(Location end) {
		this.end = end;
	}
	private static void glRandomColor() {
		Random rand = new Random();
		glColor3f(rand.nextFloat(),rand.nextFloat(),rand.nextFloat());
	}

}
