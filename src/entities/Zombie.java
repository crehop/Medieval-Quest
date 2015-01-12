package entities;

import java.awt.List;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;

import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;

import Information.Console;

import server.Location;
import utils.ModelLoader;
import utils.TextureHandler;

import gameElements.Model;
import gameElements.Unit;

public class Zombie implements Unit{
	private HashMap<Integer,Model> animate = new HashMap<Integer,Model>();
	private Model interem = null;
	private Model baseModel = null;
	private String prefix = "00000";
	private Texture texture = TextureHandler.getModelTexture("res/models/zombie/zombie.png");
	private Location location;
	private int base = 0;
	public Zombie(float x, float y, float z) {
		this.location = new Location(x,y,z);
		for(int i = 1; i<240; i++){
			if(i>99){
				prefix = "000";
			}
			else if(i>9){
				prefix = "0000";
			}else{
				prefix = "00000";
			}
			try {
				baseModel = ModelLoader.loadModel(new File("res/models/zombie/zombie.obj"), this.texture);
				interem = ModelLoader.loadModel(new File("res/models/zombie/zombie_" + prefix + i + ".obj"), this.texture);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			animate.put(i, interem);
		}
	}
	public void render(){
		interem.setTexture(this.texture);
		interem.setLocation(this.location);
		Console.setLine7(" X=" + this.interem.getLocation().getX() + " Y=" + this.interem.getLocation().getY() + " Z=" + this.interem.getLocation().getZ() + " Base =" + base + " Interim = " + interem.getName());
		this.interem.renderModel();
	}
	public void animateForward(){
		if(base < animate.size()-1){
			base++;
		}
		interem = animate.get(base);
	}
	public void animateBackward(){
		if(base > 0){
			base--;
			if(base == 0){
				interem = baseModel;
			}else{
				interem = animate.get(base);
			}
		}
	}
	public void move(float x, float y, float z){
		this.location.setX(this.location.getX() + x);
		this.location.setY(this.location.getY() + y);
		this.location.setZ(this.location.getZ() + z);
	}
	@Override
	public void walkForward() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void walkBackward() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void spinLefT() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void spinRight() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void walkLeft() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void walkRight() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public float getHeading() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public float getX() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public float getY() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public float getZ() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void idleAnimation() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hit() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void attack() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public float getHealth() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void damage(float amount) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setHealth(float health) {
		// TODO Auto-generated method stub
		
	}

}
