package utils;

public class Pixel {
	public static float getPixel(int pixel, int size){
		return (1.0f/(float)size) * (float)pixel;
	}
}
