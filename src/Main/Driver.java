package Main;

public class Driver {
	private static int count20 = 0;
	private static int count1 = 0;
	public static void checkForTick(){
		count20++;
		count1++;
		if(count20 == 2){
			tick20TimesASecond();
			count20 = 0;
		}
		if(count1 == 60){
			tick1TimesASecond();
			count1 = 0;
		}
	}
	private static void tick1TimesASecond(){
	}
	private static void tick20TimesASecond(){
	}
}
