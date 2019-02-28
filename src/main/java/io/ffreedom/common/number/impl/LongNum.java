package io.ffreedom.common.number.impl;

import io.ffreedom.common.number.api.Num;

public final class LongNum implements Num<LongNum> {

	private long value;

	private LongNum(long value) {
		this.value = value;
	}

	public static LongNum of(long value) {
		return new LongNum(value);
	}

	@Override
	public LongNum copy() {
		return new LongNum(value);
	}

	@Override
	public LongNum plusBy(Num<?> augend) {
		value += augend.longValue();
		return this;
	}

	@Override
	public LongNum minusBy(Num<?> subtrahend) {
		value -= subtrahend.longValue();
		return this;
	}

	@Override
	public LongNum multipliedBy(Num<?> multiplicand) {
		value *= multiplicand.longValue();
		return this;
	}

	@Override
	public LongNum dividedBy(Num<?> divisor) {
		value /= divisor.longValue();
		return this;
	}

	@Override
	public LongNum powBy(int n) {
		value = (long) Math.pow(value, n);
		return this;
	}

	@Override
	public boolean isZero() {
		return value == Num.LongZero;
	}

	@Override
	public boolean isPositive() {
		return value > Num.LongZero;
	}

	@Override
	public boolean isPositiveOrZero() {
		return value >= Num.LongZero;
	}

	@Override
	public boolean isNegative() {
		return value < Num.LongZero;
	}

	@Override
	public boolean isNegativeOrZero() {
		return value <= Num.LongZero;
	}

	@Override
	public boolean isEqual(Num<?> other) {
		return value == other.longValue();
	}

	@Override
	public boolean isGreaterThan(Num<?> other) {
		return value > other.longValue();
	}

	@Override
	public boolean isGreaterThanOrEqual(Num<?> other) {
		return value >= other.longValue();
	}

	@Override
	public boolean isLessThan(Num<?> other) {
		return value < other.longValue();
	}

	@Override
	public boolean isLessThanOrEqual(Num<?> other) {
		return value <= other.longValue();
	}

	@Override
	public int intValue() {
		return (int) value;
	}

	@Override
	public long longValue() {
		return value;
	}

	@Override
	public double doubleValue() {
		return value;
	}

}
