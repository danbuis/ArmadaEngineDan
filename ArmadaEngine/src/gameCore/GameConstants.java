package gameCore;

public class GameConstants {
	/*
	 * Ranges and distances were sourced from FFG user leerat at
	 * https://community.fantasyflightgames.com/topic/173990-exact-measurements-on-range-ruler/
	 * 13/2/2017
	 */
	public static final double distance1 = 71.5;
	public static final double distance2 = 125;
	public static final double distance3 = 185;
	public static final double distance4 = 245;
	public static final double distance5 = 305;
	
	public static final double rangeClose = 123;
	public static final double rangeMedium = 187;
	public static final double rangeLong = 305;
	/*
	 * Ship sizes were sourced from 
	 * http://starwars-armada.wikia.com/wiki/Size_Class
	 * 13/2/2017
	 */
	public static final double shipSmallWidth = 41;
	public static final double shipSmallLength =71;
	public static final double shipMediumWidth = 61;
	public static final double shipMediumLength = 102;
	public static final double shipLargeWidth = 76;
	public static final double shipLargeLength = 129;
	
	public static final double shipBaseRails = 2;
	
	public static enum DiceResult {
		BLANK, ACCURACY, HIT, CRIT, HITHIT, HITCRIT
	};

}
