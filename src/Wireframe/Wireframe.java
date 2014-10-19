package Wireframe;
import java.util.ArrayList;


import Main.Location;


public class Wireframe {
	private ArrayList<WireframePart> parts = new ArrayList<WireframePart>();
	private Location location;
	
	public Wireframe(ArrayList<WireframePart> parts,Location location){
		this.parts = parts;
		Main.Main.wireframes.add(this);
		this.setLocation(location);
	}
	public Wireframe(ArrayList<WireframePart> parts){
		this.parts = parts;
		Main.Main.wireframes.add(this);
		this.setLocation(new Location(0,0,0));
	}
	public Wireframe(){
		Main.Main.wireframes.add(this);
		this.setLocation(new Location(0,0,0));
	}
	public void Rendertick(){
		for(WireframePart part:parts){
			part.render();
		}
	}
	public ArrayList<WireframePart> getParts() {
		return parts;
	}
	public void setLocation(Location location) {
		this.location = location;
	}
	public float getZ() {
		return this.getLocation().getZ();
	}
	public float getY() {
		return this.getLocation().getY();
	}
	public float getX() {
		return this.getLocation().getX();
	}
	public Location getLocation(){
		return this.location;
	}
	public void addPart(WireframePart part) {
		this.parts.add(part);
	}
}
