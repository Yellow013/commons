package io.ffreedom.common.log;

import org.slf4j.Logger;

public final class ErrorLogger {

	public static void error(Logger logger, Exception e) {
		logger.error("***Throw -> [{}], ExceptionMessage -> [{}]", e.getClass().getName(), e.getMessage(), e);
	}

	public static void error(Logger logger, Exception e, String msg) {
		error(logger, msg);
		error(logger, e);
	}

	public static void error(Logger logger, String msg) {
		logger.error(msg);
	}

	public static void error(Logger logger, Exception e, String msgTemplate, Object... args) {
		error(logger, msgTemplate, args);
		error(logger, e);
	}

	public static void error(Logger logger, String msgTemplate, Object... args) {
		logger.error(msgTemplate, args);
	}

}
