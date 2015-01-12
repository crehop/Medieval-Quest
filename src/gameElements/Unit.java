package gameElements;

public interface Unit {
	public void walkForward();
	public void walkBackward();
	public void spinLefT();
	public void spinRight();
	public void walkLeft();
	public void walkRight();
	public float getHeading();
	public float getX();
	public float getY();
	public float getZ();
	public void idleAnimation();
	public void hit();
	public void attack();
	public float getHealth();
	public void damage(float amount);
	public void setHealth(float health);
}
