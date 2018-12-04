package io.ffreedom.common.utils;

import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;

import io.ffreedom.common.log.ErrorLogger;
import io.ffreedom.common.log.LoggerFactory;

public final class ThreadUtil {

	private ThreadUtil() {
	}

	private static Logger logger = LoggerFactory.getLogger(ThreadUtil.class);

	public final static void sleep(long millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			ErrorLogger.error(logger, e, "Call method ThreadUtil.sleep(millis==[{}]) throw InterruptedException -> {}",
					millis, e.getMessage());
		}
	}

	public final static void sleep(long millis, int nanos) {
		try {
			Thread.sleep(millis, nanos);
		} catch (InterruptedException e) {
			ErrorLogger.error(logger, e,
					"Call method ThreadUtil.sleep(millis==[{}], nanos==[{}]) throw InterruptedException -> {}", millis,
					nanos, e.getMessage());
		}
	}

	public final static void sleep(long time, TimeUnit timeUnit) {
		try {
			timeUnit.sleep(time);
		} catch (InterruptedException e) {
			ErrorLogger.error(logger, e,
					"Call method ThreadUtil.sleep(time==[{}], timeUnit==[{}]) throw InterruptedException -> {}", time,
					timeUnit, e.getMessage());
		}
	}

	public final static Thread newThread(Runnable runnable) {
		return new Thread(runnable);
	}

	public final static Thread newMaxPriorityThread(Runnable runnable) {
		return setThreadPriority(newThread(runnable), Thread.MAX_PRIORITY);
	}

	public final static Thread newMinPriorityThread(Runnable runnable) {
		return setThreadPriority(newThread(runnable), Thread.MIN_PRIORITY);
	}

	private final static Thread setThreadPriority(Thread thread, int priority) {
		thread.setPriority(priority);
		return thread;
	}

	public final static Thread startNewThread(Runnable runnable) {
		return startThread(newThread(runnable));
	}

	public final static Thread startNewMaxPriorityThread(Runnable runnable) {
		return startThread(newMaxPriorityThread(runnable));
	}

	public final static Thread startNewMinPriorityThread(Runnable runnable) {
		return startThread(newMinPriorityThread(runnable));
	}

	private final static Thread startThread(Thread thread) {
		thread.start();
		return thread;
	}

	public final static Thread newThread(Runnable runnable, String threadName) {
		return new Thread(runnable, threadName);
	}

	public final static Thread startNewThread(Runnable runnable, String threadName) {
		return startThread(newThread(runnable, threadName));
	}

}