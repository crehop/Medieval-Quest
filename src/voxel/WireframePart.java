package voxel;

import java.util.ArrayList;
import java.util.Random;

import loops.GameLoop;

import server.Location;

public class WireframePart{
	private final int ID;
	private Wireframe body;
	private Location location;
	private String texture;
	private float rotation = 0.0f;
	private float yaw = 0.0f;
	private float length = 0.0f;
	private float width = 0.0f;
	private float height = 0.0f;
	private float blockSize = 0.0f;
	private boolean dropcall;
	private Random rand = new Random();
	private float rotationX = 0.0f;
	private float rotationY = 0.0f;
	private float rotationZ = 0.0f;
	private float offsetX = 0.0f;
	private float offsetY = 0.0f;
	private float offsetZ = 0.0f;
	private ArrayList<Voxel> voxels = new ArrayList<Voxel>();
	private boolean textureHasChanged = false;
	public WireframePart(Location location, Wireframe body, float width, float length, float height,float blockSize){
		this.ID = entities.ID.getID();
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
		this.ID = entities.ID.getID();
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
	private void assemblePart(){
		for(float x = 0.0f; x < width; x += getBlockSize() ){
			Voxel newVoxX = new Voxel(location,this.getBlockSize()/2,x,0,0);
			voxels.add(newVoxX);
			for(float y = 0.0f; y < length; y += getBlockSize() ){
				Voxel newVoxY = new Voxel(location,this.getBlockSize()/2, null, x,y,0);
				voxels.add(newVoxY);
				for(float z = 0.0f; z < height; z += getBlockSize() ){
					Voxel newVoxZ = new Voxel(location,this.getBlockSize()/2,x,y,z);
					voxels.add(newVoxZ);
				}
			}
				
		}
	}
	public void render() {
		for(Voxel vox:voxels){
			vox.rotateX(this.rotationX);
			vox.rotateY(this.rotationY);
			vox.rotateZ(this.rotationZ);
			if(this.textureHasChanged){
				vox.setTexture(texture);
			}
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
		this.textureHasChanged  = false;
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
	public void setRotateX(float rotate){
		this.rotationX = rotate;
	}
	public void setRotateY(float rotate){
		this.rotationY = rotate;
	}
	public void setRotateZ(float rotate){
		this.rotationZ = rotate;
	}
	public float getOffsetZ() {
		return offsetZ;
	}
	public void setOffsetZ(float offsetZ) {
		this.offsetZ = offsetZ;
	}
	public float getOffsetY() {
		return offsetY;
	}
	public void setOffsetY(float offsetY) {
		this.offsetY = offsetY;
	}
	public float getOffsetX() {
		return offsetX;
	}
	public void setOffsetX(float offsetX) {
		this.offsetX = offsetX;
	}
	public void syncOffset(){
		for(Voxel vox:voxels){
			vox.setOffsetX(vox.getOffsetX() + this.offsetX);
			vox.setOffsetY(vox.getOffsetY() + this.offsetY);
			vox.setOffsetZ(vox.getOffsetZ() + this.offsetZ);
		}
	}
	public float getRotateX() {
		return this.rotationX;
	}
	public float getRotateY() {
		return this.rotationY;
	}
	public float getRotateZ() {
		return this.rotationZ;
	}
	public String getTexture() {
		return texture;
	}
	public void setTexture(String texture) {
		this.texture = texture;
		this.textureHasChanged = true;
	}
	public void rotateX(float rotate){
		this.rotationX += rotate;
	}
	public void rotateY(float rotate){
		this.rotationY += rotate;
	}
	public void rotateZ(float rotate){
		this.rotationZ += rotate;
	}
	public ArrayList<Voxel> getVoxels(){
		return this.voxels;
	}
	public void moveX(float f) {
		this.location.setX(this.getLocation().getX() + f);		
	}
	public void moveY(float f) {
		this.location.setY(this.getLocation().getY() + f);
	}
	public void moveZ(float f) {
		this.location.setZ(this.getLocation().getZ() + f);
	}
}
