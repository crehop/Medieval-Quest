package Information;

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
	public static String line8 = "8";
	public static String line9 = "9";
	public static String line10 = "10";
	public static int x = 5;
	public static int y = 5;
	public static void renderConsole(){
		if(consoleOn){
			if(line1 == null){
				line1 = "null";
			}
			if(line2 == null){
				line2 = "null";
			}
			if(line3 == null){
				line3 = "null";
			}
			if(line4 == null){
				line4 = "null";
			}
			if(line5 == null){
				line5 = "null";
			}
			if(line6 == null){
				line6 = "null";
			}
			if(line7 == null){
				line7 = "null";
			}
			if(line8 == null){
				line8 = "null";
			}
			if(line9 == null){
				line9 = "null";
			}
			if(line10 == null){
				line10 = "null";
			}
			x = 5;
			y = 5;
			renderer.Renderer2D.renderText(x, y, line1, Color.white,3);
			y += (renderer.Renderer2D.font3.getHeight(line1) + 5);
			renderer.Renderer2D.renderText(x, y, line2, Color.white,3);
			y += (renderer.Renderer2D.font3.getHeight(line2) + 5);
			renderer.Renderer2D.renderText(x, y, line3, Color.white,3);
			y += (renderer.Renderer2D.font3.getHeight(line3) + 5);
			renderer.Renderer2D.renderText(x, y, line4, Color.white,3);
			y += (renderer.Renderer2D.font3.getHeight(line4) + 5);
			renderer.Renderer2D.renderText(x, y, line5, Color.white,3);
			y += (renderer.Renderer2D.font3.getHeight(line5) + 5);
			renderer.Renderer2D.renderText(x, y, line6, Color.white,3);
			y += (renderer.Renderer2D.font3.getHeight(line6) + 5);
			renderer.Renderer2D.renderText(x, y, line7, Color.white,3);
			y += (renderer.Renderer2D.font3.getHeight(line7) + 5);
			renderer.Renderer2D.renderText(x, y, line8, Color.white,3);
			y += (renderer.Renderer2D.font3.getHeight(line8) + 5);
			renderer.Renderer2D.renderText(x, y, line9, Color.white,3);
			y += (renderer.Renderer2D.font3.getHeight(line9) + 5);
			renderer.Renderer2D.renderText(x, y, line10, Color.white,3);
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
	public static String getLine8() {
		return line8;
	}
	public static void setLine8(String line8) {
		Console.line8 = line8;
	}
	public static String getLine9() {
		return line9;
	}
	public static void setLine9(String line9) {
		Console.line9 = line9;
	}
	public static String getLine10() {
		return line10;
	}
	public static void setLine10(String line10) {
		Console.line10 = line10;
	}
}
