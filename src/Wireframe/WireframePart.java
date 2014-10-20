package wireframe;

import java.util.ArrayList;

import server.Location;

public class WireframePart{
	private final int ID;
	private Wireframe body;
	private Location start;
	private Location end;
	public WireframePart(Location start, Location end, Wireframe body, int width, int length){
		this.ID = entities.ID.getPartID();
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
	public int getID(){
		return this.ID;
	}

}
