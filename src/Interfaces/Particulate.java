package Interfaces;

public interface Particulate {
	void render();
	float getExpireTime();
	void setExpireTime(float time);
	void destroy();
	String getType();
}
