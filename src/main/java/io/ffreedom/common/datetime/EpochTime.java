package io.ffreedom.common.datetime;

public final class EpochTime {

	public static long milliseconds() {
		return System.currentTimeMillis();
	}

	public static long seconds() {
		return System.currentTimeMillis() / 1000;
	}

}