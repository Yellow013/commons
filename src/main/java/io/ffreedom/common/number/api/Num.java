package io.ffreedom.common.number.api;

public interface Num<N extends Num<?>> extends Comparable<N> {

	int IntZero = 0;
	float FloatZero = 0.0F;
	long LongZero = 0L;
	double DoubleZero = 0.0D;

	N plusBy(Num<?> augend);

	/**
	 * Returns a {@code num} whose value is {@code (this - augend)},
	 * 
	 * @param subtrahend value to be subtracted from this {@code num}.
	 * @return {@code this - subtrahend}, rounded as necessary
	 */
	N minusBy(Num<?> subtrahend);

	/**
	 * Returns a {@code num} whose value is {@code this * multiplicand},
	 * 
	 * @param multiplicand value to be multiplied by this {@code num}.
	 * @return {@code this * multiplicand}, rounded as necessary
	 */
	N multipliedBy(Num<?> multiplicand);

	/**
	 * Returns a {@code num} whose value is {@code (this / divisor)},
	 * 
	 * @param divisor value by which this {@code num} is to be divided.
	 * @return {@code this / divisor}, rounded as necessary
	 */
	N dividedBy(Num<?> divisor);

	/**
	 * Returns a {@code num} whose value is <tt>(this<sup>n</sup>)</tt>.
	 * 
	 * @param n power to raise this {@code num} to.
	 * @return <tt>this<sup>n</sup></tt>
	 */
	N powBy(int n);

	/**
	 * Returns a {@code num} whose value is <tt>√(this)</tt>.
	 * 
	 * @return <tt>this<sup>n</sup></tt>
	 */
	N sqrt();

	/**
	 * Returns a {@code num} whose value is <tt>√(this)</tt>.
	 * 
	 * @param precision to calculate.
	 * @return <tt>this<sup>n</sup></tt>
	 */
	N sqrt(int precision);

	/**
	 * Returns a {@code num} whose value is the absolute value of this {@code num}.
	 * 
	 * @return {@code abs(this)}
	 */
	double abs();

	/**
	 * Checks if the value is zero.
	 * 
	 * @return true if the value is zero, false otherwise
	 */
	boolean isZero();

	/**
	 * Checks if the value is greater than zero.
	 * 
	 * @return true if the value is greater than zero, false otherwise
	 */
	boolean isPositive();

	/**
	 * Checks if the value is zero or greater.
	 * 
	 * @return true if the value is zero or greater, false otherwise
	 */
	boolean isPositiveOrZero();

	/**
	 * Checks if the value is less than zero.
	 * 
	 * @return true if the value is less than zero, false otherwise
	 */
	boolean isNegative();

	/**
	 * Checks if the value is zero or less.
	 * 
	 * @return true if the value is zero or less, false otherwise
	 */
	boolean isNegativeOrZero();

	/**
	 * Checks if this value is equal to another.
	 * 
	 * @param other the other value, not null
	 * @return true is this is greater than the specified value, false otherwise
	 */
	boolean isEqual(Num<?> other);

	/**
	 * Checks if this value is greater than another.
	 * 
	 * @param other the other value, not null
	 * @return true is this is greater than the specified value, false otherwise
	 */
	boolean isGreaterThan(Num<?> other);

	/**
	 * Checks if this value is greater than or equal to another.
	 * 
	 * @param other the other value, not null
	 * @return true is this is greater than or equal to the specified value, false
	 *         otherwise
	 */
	boolean isGreaterThanOrEqual(Num<?> other);

	/**
	 * Checks if this value is less than another.
	 * 
	 * @param other the other value, not null
	 * @return true is this is less than the specified value, false otherwise
	 */
	boolean isLessThan(Num<?> other);

	/**
	 * Checks if this value is less than another.
	 * 
	 * @param other the other value, not null
	 * @return true is this is less than or equal the specified value, false
	 *         otherwise
	 */
	boolean isLessThanOrEqual(Num<?> other);

	/**
	 * Returns the minimum of this {@code num} and {@code other}.
	 * 
	 * @param other value with which the minimum is to be computed
	 * @return the {@code num} whose value is the lesser of this {@code num} and
	 *         {@code other}. If they are equal, method, {@code this} is returned.
	 */
	N min(Num<?> other);

	/**
	 * Returns the maximum of this {@code num} and {@code other}.
	 * 
	 * @param other value with which the maximum is to be computed
	 * @return the {@code num} whose value is the greater of this {@code num} and
	 *         {@code other}. If they are equal, method, {@code this} is returned.
	 */
	N max(Num<?> other);

	/**
	 * Only for NaN this should be true
	 * 
	 * @return false if this implementation is not NaN
	 */
	default boolean isNaN() {
		return false;
	}

	double doubleValue();

	int intValue();

	long longValue();

	float floatValue();

	@Override
	int hashCode();

	@Override
	String toString();

	/**
	 * {@inheritDoc}
	 * 
	 * @apiNote: This method should return true if `this` and `obj` are both NaN.
	 */
	@Override
	boolean equals(Object obj);

}
