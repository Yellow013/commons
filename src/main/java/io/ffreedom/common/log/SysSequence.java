package io.ffreedom.common.log;

import org.slf4j.Logger;

public final class SysSequence {

	public static long millisecond() {
		return System.nanoTime() / 1000_000;
	}

	public static long microsecond() {
		return System.nanoTime() / 1000;
	}

	public static long nanosecond() {
		return System.nanoTime();
	}

	public static void main(String[] args) {

		Logger log = LoggerFactory.getLogger(SysSequence.class);

		for (int i = 0; i < 20; i++) {
			log.debug(String.valueOf(SysSequence.microsecond()));
		}
		
	}

}
