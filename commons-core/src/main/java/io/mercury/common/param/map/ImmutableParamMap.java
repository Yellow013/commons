package io.mercury.common.param.map;

import static io.mercury.common.util.Assertor.nonEmpty;
import static io.mercury.common.util.Assertor.nonNull;
import static io.mercury.common.util.Assertor.requiredLength;
import static java.lang.Boolean.parseBoolean;
import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;

import java.util.Map;
import java.util.Properties;

import javax.annotation.Nonnull;

import org.eclipse.collections.api.map.ImmutableMap;
import org.eclipse.collections.api.map.MutableMap;

import io.mercury.common.collections.MutableMaps;
import io.mercury.common.param.ParamKey;
import io.mercury.common.param.ParamType;

public final class ImmutableParamMap<K extends ParamKey> {

	private final ImmutableMap<K, String> immutableMap;

	/**
	 * 根据传入的Key获取Map中的相应字段
	 * 
	 * @param keys
	 * @param maps
	 * @throws NullPointerException
	 * @throws IllegalArgumentException
	 */
	public ImmutableParamMap(@Nonnull K[] keys, @Nonnull Map<?, ?> map)
			throws NullPointerException, IllegalArgumentException {
		requiredLength(keys, 1, "keys");
		nonEmpty(map, "maps");
		MutableMap<K, String> mutableMap = MutableMaps.newUnifiedMap();
		for (K key : keys) {
			if (map.containsKey(key.paramName()))
				mutableMap.put(key, map.get(key.paramName()).toString());
		}
		this.immutableMap = mutableMap.toImmutable();
	}

	/**
	 * 根据传入的Key获取Map中的相应字段
	 * 
	 * @param keys
	 * @param maps
	 * @throws NullPointerException
	 * @throws IllegalArgumentException
	 */
	public ImmutableParamMap(@Nonnull K[] keys, @Nonnull Properties properties)
			throws NullPointerException, IllegalArgumentException {
		requiredLength(keys, 1, "keys");
		nonNull(properties, "properties");
		MutableMap<K, String> mutableMap = MutableMaps.newUnifiedMap();
		for (K key : keys) {
			if (properties.containsKey(key.paramName()))
				mutableMap.put(key, properties.get(key.paramName()).toString());
		}
		this.immutableMap = mutableMap.toImmutable();
	}

	/**
	 * 
	 * @param key
	 * @return
	 */
	public boolean getBoolean(K key) throws IllegalArgumentException, NullPointerException {
		if (key.paramType() != ParamType.BOOLEAN)
			throw new IllegalArgumentException(
					"Key -> " + key + " ParamType is not BOOLEAN. paramType==" + key.paramType());
		return parseBoolean(nonNull(immutableMap.get(key.paramName()), key.paramName()));
	}

	/**
	 * 
	 * @param key
	 * @return
	 */
	public int getInt(K key) throws IllegalArgumentException, NullPointerException {
		if (key.paramType() != ParamType.INT)
			throw new IllegalArgumentException(
					"Key -> " + key + " ParamType is not [INT]. paramType==" + key.paramType());
		return parseInt(nonNull(immutableMap.get(key.paramName()), key.paramName()));
	}

	/**
	 * 
	 * @param key
	 * @return
	 */
	public double getDouble(K key) throws IllegalArgumentException, NullPointerException {
		if (key.paramType() != ParamType.DOUBLE)
			throw new IllegalArgumentException(
					"Key -> " + key + " ParamType is not [DOUBLE], paramType==" + key.paramType());
		return parseDouble(nonNull(immutableMap.get(key.paramName()), key.paramName()));
	}

	/**
	 * 
	 * @param key
	 * @return
	 */
	public String getString(K key) throws IllegalArgumentException, NullPointerException {
		if (key.paramType() != ParamType.STRING)
			throw new IllegalArgumentException(
					"Key -> " + key + " ParamType is not [STRING], paramType==" + key.paramType());
		return nonNull(immutableMap.get(key.paramName()), key.paramName());
	}

}