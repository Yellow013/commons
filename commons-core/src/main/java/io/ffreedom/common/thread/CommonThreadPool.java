package io.ffreedom.common.thread;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.function.BiConsumer;

public final class CommonThreadPool extends ThreadPoolExecutor {

	private BiConsumer<Thread, Runnable> beforeHandler;
	private boolean isBeforeHandle = false;

	private BiConsumer<Runnable, Throwable> afterHandler;
	private boolean isAfterHandle = false;

	private CommonThreadPool(ThreadPoolBuilder builder, BiConsumer<Thread, Runnable> beforeHandler,
			BiConsumer<Runnable, Throwable> afterHandler) {
		super(builder.corePoolSize, builder.maximumPoolSize, builder.keepAliveTime, builder.timeUnit,
				builder.workQueue);
		initHandler(beforeHandler, afterHandler);
	}

	private CommonThreadPool(ThreadPoolBuilder builder, ThreadFactory threadFactory,
			BiConsumer<Thread, Runnable> beforeHandler, BiConsumer<Runnable, Throwable> afterHandler) {
		super(builder.corePoolSize, builder.maximumPoolSize, builder.keepAliveTime, builder.timeUnit, builder.workQueue,
				threadFactory);
		initHandler(beforeHandler, afterHandler);
	}

	private CommonThreadPool(ThreadPoolBuilder builder, RejectedExecutionHandler rejectedHandler,
			BiConsumer<Thread, Runnable> beforeHandler, BiConsumer<Runnable, Throwable> afterHandler) {
		super(builder.corePoolSize, builder.maximumPoolSize, builder.keepAliveTime, builder.timeUnit, builder.workQueue,
				rejectedHandler);
		initHandler(beforeHandler, afterHandler);
	}

	private CommonThreadPool(ThreadPoolBuilder builder, ThreadFactory threadFactory,
			RejectedExecutionHandler rejectedHandler, BiConsumer<Thread, Runnable> beforeHandler,
			BiConsumer<Runnable, Throwable> afterHandler) {
		super(builder.corePoolSize, builder.maximumPoolSize, builder.keepAliveTime, builder.timeUnit, builder.workQueue,
				threadFactory, rejectedHandler);
		initHandler(beforeHandler, afterHandler);
	}

	private void initHandler(BiConsumer<Thread, Runnable> beforeHandler, BiConsumer<Runnable, Throwable> afterHandler) {
		if (beforeHandler != null) {
			this.beforeHandler = beforeHandler;
			this.isBeforeHandle = true;
		}
		if (afterHandler != null) {
			this.afterHandler = afterHandler;
			this.isAfterHandle = true;
		}
	}

	public static ThreadPoolBuilder newBuilder() {
		return new ThreadPoolBuilder();
	}

	@Override
	protected void beforeExecute(Thread thread, Runnable runnable) {
		if (isBeforeHandle)
			beforeHandler.accept(thread, runnable);
	}

	@Override
	protected void afterExecute(Runnable runnable, Throwable throwable) {
		if (isAfterHandle)
			afterHandler.accept(runnable, throwable);
	}

	public final static class ThreadPoolBuilder {

		private int corePoolSize = Runtime.getRuntime().availableProcessors();
		private int maximumPoolSize = Runtime.getRuntime().availableProcessors() * 2;
		private long keepAliveTime = 90;
		private TimeUnit timeUnit = TimeUnit.SECONDS;
		private BlockingQueue<Runnable> workQueue = new LinkedBlockingQueue<>();
		private ThreadFactory threadFactory;
		private RejectedExecutionHandler rejectedHandler;

		private BiConsumer<Thread, Runnable> beforeHandler;
		private BiConsumer<Runnable, Throwable> afterHandler;

		public ThreadPoolBuilder setCorePoolSize(int corePoolSize) {
			this.corePoolSize = corePoolSize;
			return this;
		}

		public ThreadPoolBuilder setMaximumPoolSize(int maximumPoolSize) {
			this.maximumPoolSize = maximumPoolSize;
			return this;
		}

		public ThreadPoolBuilder setKeepAliveTime(long keepAliveTime) {
			this.keepAliveTime = keepAliveTime;
			return this;
		}

		public ThreadPoolBuilder setTimeUnit(TimeUnit timeUnit) {
			this.timeUnit = timeUnit;
			return this;
		}

		public ThreadPoolBuilder setWorkQueue(BlockingQueue<Runnable> workQueue) {
			this.workQueue = workQueue;
			return this;
		}

		public ThreadPoolBuilder setThreadFactory(ThreadFactory threadFactory) {
			this.threadFactory = threadFactory;
			return this;
		}

		public ThreadPoolBuilder setRejectedHandler(RejectedExecutionHandler rejectedHandler) {
			this.rejectedHandler = rejectedHandler;
			return this;
		}

		public ThreadPoolBuilder setBeforeHandler(BiConsumer<Thread, Runnable> beforeHandler) {
			this.beforeHandler = beforeHandler;
			return this;
		}

		public ThreadPoolBuilder setAfterHandler(BiConsumer<Runnable, Throwable> afterHandler) {
			this.afterHandler = afterHandler;
			return this;
		}

		public CommonThreadPool build(String threadPoolName) {
			return threadFactory != null && rejectedHandler != null
					? new CommonThreadPool(this, threadFactory, rejectedHandler, beforeHandler, afterHandler)
					: threadFactory != null && rejectedHandler == null
							? new CommonThreadPool(this, threadFactory, beforeHandler, afterHandler)
							: threadFactory == null && rejectedHandler != null
									? new CommonThreadPool(this, rejectedHandler, beforeHandler, afterHandler)
									: new CommonThreadPool(this, beforeHandler, afterHandler);
		}

	}

}