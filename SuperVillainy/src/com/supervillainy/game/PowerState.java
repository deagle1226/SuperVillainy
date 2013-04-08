package com.supervillainy.game;

public class PowerState {

	public static final int TECH = 0;
	public static final int MUTATION = 1;
	public static final int MAGIC = 2;
	public static final int NATURAL = 3;
	
	public static final String[] powers = new String[] {
		"Technology", "Mutation", "Magic", "Natural Training"
	};
	
	public static int state = 0;
	
	public static void setState(int s) {
		state = s;
	}
	
}
