package wireframe;

import java.util.ArrayList;
import java.util.Random;

import loops.GameLoop;

import server.Location;
import voxel.Voxel;

public class WireframePart{
	private final int ID;
	private Wireframe body;
	private Location location;
	private float rotation = 0.0f;
	private float yaw = 0.0f;
	private float length = 0.0f;
	private float width = 0.0f;
	private float height = 0.0f;
	private float blockSize = 0.0f;
	private boolean dropcall;
	private Random rand = new Random();
	private ArrayList<Voxel> voxels = new ArrayList<Voxel>();
	public WireframePart(Location location, Wireframe body, float width, float length, float height,float blockSize){
		this.ID = entities.ID.getPartID();
		if(this.body == null){
			ArrayList<WireframePart> parts = new ArrayList<WireframePart>();
			parts.add(this);
			this.width = (float)(width);
			this.length = (float)(length);
			this.height = (float)(height);
			this.blockSize = blockSize;
			this.body = new Wireframe(parts);
			this.body.setLocation(location);
		}else{
			this.body = body;
			this.width = (float)(width);
			this.length = (float)(length);
			this.height = (float)(height);
			this.blockSize = blockSize;
		}
		this.setLocation(location);
		assemblePart();
		GameLoop.renderMe.add(this);
	}
	public WireframePart(Wireframe body, float width, float length, float height,float blockSize) {
		this.ID = entities.ID.getPartID();
		this.body = body;
		body.addPart(this);
		this.width = (float)(width);
		this.length = (float)(length);
		this.height = (float)(height);
		this.blockSize = blockSize;
		this.setLocation(body.getLocation());
		assemblePart();
		GameLoop.renderMe.add(this);
	}
	private int attempt;
	private void assemblePart(){
		for(float x = 0.0f; x < width; x += getBlockSize() ){
			Voxel newVoxX = new Voxel(location,this.getBlockSize()/2,x,0,0);
			voxels.add(newVoxX);
			attempt++;
			System.out.println("ATTEMPTED " + attempt + " WIDTH " + width + " X :" + x);
			for(float y = 0.0f; y < length; y += getBlockSize() ){
				Voxel newVoxY = new Voxel(location,this.getBlockSize()/2, null, x,y,0);
				voxels.add(newVoxY);
				attempt++;
				System.out.println("ATTEMPTED " + attempt + " LENGTH " + length + " Y :" + y);
				for(float z = 0.0f; z < height; z += getBlockSize() ){
					Voxel newVoxZ = new Voxel(location,this.getBlockSize()/2,x,y,z);
					voxels.add(newVoxZ);
					attempt++;
					System.out.println("ATTEMPTED " + attempt + " HEIGHT " + height + " Z :" + z);
				}
			}
				
		}
	}
	public void render() {
		for(Voxel vox:voxels){
			vox.rotate(this.getRotation());
			vox.render();
			if(dropcall){
				if(rand.nextFloat() < 0.008){
					vox.setPhysicsControlled(true);
					vox.setOffsetX(vox.getOffsetX() + 10.0f);
				}
			}else{
				vox.setPhysicsControlled(false);
			}
		}
	}
	public Location getLocation() {
		return location;
	}
	public int getID(){
		return this.ID;
	}
	public void setLocation(Location location) {
		this.location = location;
	}
	public float getYaw() {
		return yaw;
	}
	public void setYaw(float yaw) {
		this.yaw = yaw;
	}
	public float getLength() {
		return length;
	}
	public void setLength(float length) {
		this.length = length;
	}
	public float getHeight() {
		return height;
	}
	public void setHeight(float height) {
		this.height = height;
	}
	public float getBlockSize() {
		return blockSize;
	}
	public void setBlockSize(float blockSize) {
		this.blockSize = blockSize;
	}
	public float getRotation() {
		return rotation;
	}
	public void setRotation(float rotation) {
		this.rotation = rotation;
	}
	public boolean isDropcall() {
		return dropcall;
	}
	public void setDropcall(boolean dropcall) {
		this.dropcall = dropcall;
	}
	public Wireframe getFrame() {
		return this.body;
	}

}
