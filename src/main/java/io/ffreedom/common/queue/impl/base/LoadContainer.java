package io.ffreedom.common.queue.impl.base;

public class LoadContainer<T> {

	private T t;

	public void loading(T t) {
		this.t = t;
	}

	public T unloading() {
		return t;
	}

}