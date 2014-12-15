package Main;

import org.newdawn.slick.Color;

public class Console {
	public static boolean consoleOn = true;
	public static String line1 = "1";
	public static String line2 = "2";
	public static String line3 = "3";
	public static String line4 = "4";
	public static String line5 = "5";
	public static String line6 = "6";
	public static String line7 = "7";
	public static void renderConsole(){
		if(consoleOn){
			int x = 5;
			int y = 5;
			renderer.TextRenderer.render(x, y, line1, Color.white);
			y += (renderer.TextRenderer.font.getHeight(line1) + 5);
			renderer.TextRenderer.render(x, y, line2, Color.white);
			y += (renderer.TextRenderer.font.getHeight(line2) + 5);
			renderer.TextRenderer.render(x, y, line3, Color.white);
			y += (renderer.TextRenderer.font.getHeight(line3) + 5);
			renderer.TextRenderer.render(x, y, line4, Color.white);
			y += (renderer.TextRenderer.font.getHeight(line4) + 5);
			renderer.TextRenderer.render(x, y, line5, Color.white);
			y += (renderer.TextRenderer.font.getHeight(line5) + 5);
			renderer.TextRenderer.render(x, y, line6, Color.white);
			y += (renderer.TextRenderer.font.getHeight(line6) + 5);
			renderer.TextRenderer.render(x, y, line7, Color.white);
		}
	}
	public static boolean isConsoleOn() {
		return consoleOn;
	}
	public static void setConsoleOn(boolean consoleOn) {
		Console.consoleOn = consoleOn;
	}
	public static String getLine1() {
		return line1;
	}
	public static void setLine1(String line1) {
		Console.line1 = line1;
	}
	public static String getLine2() {
		return line2;
	}
	public static void setLine2(String line2) {
		Console.line2 = line2;
	}
	public static String getLine3() {
		return line3;
	}
	public static void setLine3(String line3) {
		Console.line3 = line3;
	}
	public static String getLine4() {
		return line4;
	}
	public static void setLine4(String line4) {
		Console.line4 = line4;
	}
	public static String getLine5() {
		return line5;
	}
	public static void setLine5(String line5) {
		Console.line5 = line5;
	}
	public static String getLine6() {
		return line6;
	}
	public static void setLine6(String line6) {
		Console.line6 = line6;
	}
	public static String getLine7() {
		return line7;
	}
	public static void setLine7(String line7) {
		Console.line7 = line7;
	}
}
